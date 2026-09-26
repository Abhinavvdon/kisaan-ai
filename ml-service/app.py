"""
KISAAN AI - Dedicated Plant Pathology Microservice
Provides high-accuracy inference using the PlantVillage + ICAR Dataset model.
Zero external API key required.
"""

from fastapi import FastAPI, UploadFile, File, Form, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from PIL import Image
import io
import uvicorn
from dataset import PLANT_PATHOLOGY_DATASET
from model import predict_pathology, extract_leaf_metrics

app = FastAPI(
    title="Kisaan Plant Pathology AI Service",
    description="High-accuracy crop disease and pest identification microservice powered by PlantVillage and ICAR datasets.",
    version="1.0.0"
)

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.get("/health")
def health_check():
    return {
        "status": "healthy",
        "service": "kisaan-pathology-ai",
        "model": "MobileNetV3-PlantVillage-ICAR",
        "total_classes": len(PLANT_PATHOLOGY_DATASET),
        "offline_ready": True
    }


@app.get("/crops")
def list_supported_crops():
    crops = {}
    for key, info in PLANT_PATHOLOGY_DATASET.items():
        c = info["crop"]
        if c not in crops:
            crops[c] = {
                "crop": c,
                "crop_hi": info["crop_hi"],
                "diseases": []
            }
        crops[c]["diseases"].append({
            "key": key,
            "name": info["disease_name"],
            "name_hi": info["disease_name_hi"],
            "severity": info["severity"],
            "is_healthy": info["is_healthy"]
        })
    return {"total_crops": len(crops), "crops": list(crops.values())}


@app.post("/predict")
async def predict_crop_disease(
    file: UploadFile = File(...),
    crop_hint: str = Form(None),
    language: str = Form("en")
):
    try:
        contents = await file.read()
        image = Image.open(io.BytesIO(contents))
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Invalid image file: {str(e)}")

    prediction = predict_pathology(image, crop_hint=crop_hint, language=language)
    return prediction


if __name__ == "__main__":
    uvicorn.run("app:app", host="0.0.0.0", port=8088, reload=False)
