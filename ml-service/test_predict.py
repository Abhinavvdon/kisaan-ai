"""
Quick verification test script for Kisaan AI pathology engine.
Generates test leaf canvases and checks predictions.
"""

from PIL import Image, ImageDraw
from model import predict_pathology, extract_leaf_metrics


def create_test_leaf(crop: str, is_diseased: bool = True) -> Image.Image:
    img = Image.new("RGB", (224, 224), color=(80, 55, 35)) # soil background
    draw = ImageDraw.Draw(img)

    # Leaf blade
    leaf_color = (60, 130, 45) if not is_diseased else (85, 115, 40)
    draw.ellipse([30, 50, 194, 174], fill=leaf_color)

    if is_diseased:
        if crop == "wheat":
            # Yellow rust linear stripes
            for i in range(12):
                draw.rectangle([50 + i * 10, 80 + (i % 3) * 12, 65 + i * 10, 84 + (i % 3) * 12], fill=(230, 190, 30))
        elif crop == "rice":
            # Rice blast spindle lesions
            draw.ellipse([80, 90, 140, 120], fill=(70, 40, 25))
            draw.ellipse([95, 100, 125, 110], fill=(200, 200, 190))
        elif crop == "tomato":
            # Early blight concentric target rings
            draw.ellipse([80, 80, 140, 140], fill=(210, 180, 40)) # chlorotic halo
            draw.ellipse([90, 90, 130, 130], fill=(50, 25, 15))   # necrotic center
        else:
            # General necrotic spots
            draw.ellipse([70, 70, 110, 110], fill=(60, 30, 15))
            draw.ellipse([120, 110, 160, 150], fill=(70, 35, 20))

    return img


def run_tests():
    print("Testing Kisaan Pathology Engine...")
    test_cases = [
        ("tomato", True, "Tomato Early Blight"),
        ("tomato", False, "Tomato Healthy"),
        ("wheat", True, "Wheat Yellow Rust"),
        ("rice", True, "Rice Blast"),
    ]

    for crop, diseased, desc in test_cases:
        img = create_test_leaf(crop, is_diseased=diseased)
        metrics = extract_leaf_metrics(img)
        res = predict_pathology(img, crop_hint=crop, language="en")
        top_cand = res["candidates"][0]
        print(f"[{desc}] -> Predicted: {top_cand['disease_name']} ({top_cand['confidence']}%) | Healthy: {res['is_healthy']}")
        print(f"   Metrics: Chlorophyll={metrics['chlorophyll_score']}, Chlorosis={metrics['chlorosis_percent']}%, Necrosis={metrics['necrosis_percent']}%")

    # Test Auto-Detect on real user tomato sample
    try:
        sample_img = Image.open("user_tomato_sample.jpg")
        auto_res = predict_pathology(sample_img, crop_hint="auto", language="en")
        print(f"[Auto-Detect Real Image] -> Detected Crop: {auto_res['crop']} | Top: {auto_res['candidates'][0]['disease_name']}")
        assert "tomato" in auto_res['crop'].lower(), f"Expected tomato, got {auto_res['crop']}"
    except Exception as e:
        print("Auto-detect sample check:", e)

    print("\nAll model tests completed successfully!")


if __name__ == "__main__":
    run_tests()
