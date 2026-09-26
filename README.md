# 🌱 KISAAN.AI (किसान.AI)

> **An AI-powered digital agriculture companion for Indian farmers.**

KISAAN.AI is an end-to-end full-stack web platform engineered to empower Indian farmers with real-time farm telemetry, multimodal AI crop disease diagnostics, local community knowledge exchange, and direct access to Central and State agricultural welfare schemes.

---

## ✨ Features

- **🔐 Farmer Authentication & Profile Management**:
  - Secure signup, login, and token session management with BCrypt-grade salted password hashing.
  - Persistent farm profile: Farm Acreage, Primary Crops, and District association.
  - Pre-seeded 1-click demo accounts for hackathon judges (`ramesh@kisaan.ai` and `harpreet@kisaan.ai`).
  - Personal AI Scan History ledger linked to farmer profile.

- **📍 Real-Time GPS Location Detector & Manual District Selector**:
  - One-tap browser GPS detector (`navigator.geolocation` + reverse geocoding).
  - Manual selector covering 30+ major agricultural districts across 12+ Indian states.
  - Automatic synchronization with district-level weather telemetry and pest alerts.

- **🌐 Bilingual by Default (English & हिंदी)**:
  - First-load language selection with persistent local storage.
  - Quick toggle in navigation header.
  - Multi-candidate AI diagnosis returned directly in the farmer's selected language.

- **🌦️ Farm Telemetry & Outbreak Warnings**:
  - Live weather tracking and short 48-hour forecasts powered by OpenWeatherMap.
  - Root-zone soil moisture telemetry (volumetric water content, soil temperature, and EC).
  - Curated active pest and disease outbreak alerts for 27+ Indian districts (e.g., Downy Mildew in Nashik, Black Thrips in Guntur, Yellow Rust in Ludhiana, Pink Bollworm in Bathinda).

- **🔬 Dynamic Multi-Crop AI Pathology Vision (Powered by Google Gemini)**:
  - Crop-specific pathology models covering **Wheat, Rice/Paddy, Cotton, Tomato, Grape, Chilli, Potato, and Corn/Maize**.
  - In-app Google Gemini API key configuration modal (`X-Gemini-Key` header) with persistent browser storage.
  - Multi-candidate diagnoses with severity ratings, confidence percentages, chemical treatments, and bio-control alternatives (Neem oil, *Trichoderma*, *Bacillus subtilis*).
  - Weather-correlated 48-hour disease propagation risk note.

- **👨‍🌾 SAATHI (Community Forum)**:
  - Peer-to-peer farmer discussion board with district-level filtering.
  - Discussion threads with expandable replies.

- **🏛️ Yojana Setu (Government Welfare Portal — 30+ Schemes)**:
  - Curated directory of 30 major Central and State agricultural schemes, emphasizing key initiatives from the last 5 years:
    - *Digital Agriculture Mission (2024)*
    - *Clean Plant Programme (2024)*
    - *Sub-Mission on Agricultural Mechanization (Kisan Drones - 2022)*
    - *PM-PRANAM (2023)*
    - *GOBARdhan Scheme (2023)*
    - *PM Surya Ghar: Muft Bijli Yojana (2024)*
    - *PM-KISAN, PMFBY, Soil Health Card, PMKSY, PKVY, KCC, PM-KUSUM, e-NAM, AIF, and state top-ups*.
  - Category filter pills: All, Last 5 Years (2020-2025), Financial, Technology & Drones, Solar & Energy, Subsidies & Seeds.
  - Direct links to official Government application portals.

- **🎨 Agricultural Earthy Design System**:
  - Soil-themed aesthetic (`#F7F2E9` cream, `#6B4423` soil brown, `#4C7A3D` leaf green, `#C46A2B` turmeric orange, `#6E97AC` sky blue).
  - Mobile-first navigation tested at 375px viewport with thumb-friendly controls.

---

## 🛠️ Tech Stack

- **Backend**: Java 21, Spring Boot 3.3.5 (REST API, Spring Data JPA)
- **Database**:
  - **Persistent H2** (`./data/kisaandb`) by default for zero-setup demo portability.
  - **PostgreSQL Profile** (`application-postgres.properties`) ready for production deployment.
- **Frontend**: React 18, Vite 6, Tailwind CSS, React Router 6, Lucide Icons
- **AI Models**: Google Gemini Multimodal API (`generateContent`)
- **Weather API**: OpenWeatherMap API

---

## 🚀 Getting Started

### Prerequisites
- **Java 21** or higher
- **Node.js 18+** & **npm**
- **Gradle** (or Gradle wrapper)

### 1. Clone the Repository
```bash
git clone https://github.com/Abhinavvdon/kisaan-ai.git
cd kisaan-ai
```

### 2. Run the Backend
```bash
cd backend
gradle bootRun
```
*Backend runs on port `8085`.*
*H2 Console accessible at `http://localhost:8085/h2-console` (JDBC URL: `jdbc:h2:file:./data/kisaandb`).*

> **Demo Credentials**:
> - Ramesh Patil: `ramesh@kisaan.ai` / `kisaan123` (Nashik, Maharashtra)
> - Harpreet Singh: `harpreet@kisaan.ai` / `kisaan123` (Ludhiana, Punjab)

### 3. Run the Frontend
```bash
cd ../frontend
npm install
npm run dev
```
*Frontend runs on `http://localhost:5173`.*

---

## 🔑 Gemini API Key Configuration

You can use the AI Crop Scanner out of the box with realistic multi-crop simulated pathology. To run against live Google Gemini multimodal vision:
1. Click the **"Gemini Key"** pill in the top navigation bar or scanner header.
2. Enter your Gemini API Key (`AIza...`).
3. The key is securely saved to your browser session and automatically included in scan requests.

---

## 📄 License
MIT License &copy; 2026 KISAAN.AI Team.

