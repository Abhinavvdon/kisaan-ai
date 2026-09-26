# 🌱 KISAAN.AI (किसान.AI)

> **An AI-powered digital agriculture companion for Indian farmers.**

KISAAN.AI is an end-to-end full-stack web platform engineered to empower Indian farmers with real-time farm telemetry, multimodal AI crop disease diagnostics, local community knowledge exchange, and direct access to Central and State agricultural welfare schemes.

---

## ✨ Features

- **🌐 Bilingual by Default (English & हिंदी)**:
  - First-load language selection with persistent local storage.
  - Quick toggle in navigation header.
  - Multi-candidate AI diagnosis returned directly in the farmer's selected language.

- **🌦️ Farm Telemetry & Dashboard**:
  - Live weather tracking and short 48-hour forecasts powered by OpenWeatherMap.
  - Root-zone soil moisture telemetry (volumetric water content, soil temperature, and EC).
  - Curated active pest and disease outbreak alerts for 15+ Indian districts (e.g., Downy Mildew in Nashik, Black Thrips in Guntur, Yellow Rust in Ludhiana).

- **🔬 AI Crop Disease Scanner (Powered by Google Gemini)**:
  - Large thumb-reachable mobile camera capture and file upload zone.
  - Structured multimodal prompt demanding the top 2–3 candidate diagnoses with individual confidence scores (0–100) and severity ratings.
  - Resilient model fallback chain (`gemini-3.6-flash` → `gemini-2.5-flash` → `gemini-flash-latest` → offline fallback).
  - Weather-correlated 48-hour disease propagation risk note.
  - Recommended chemical actions paired with bio-control/organic alternatives (Neem oil, *Trichoderma*).

- **👨‍🌾 SAATHI (Community Forum)**:
  - Peer-to-peer farmer discussion board with district-level filtering.
  - In-memory H2 database persistence with Spring Data JPA.
  - Discussion threads with expandable replies.

- **🏛️ Yojana Setu (Government Welfare Portal)**:
  - Directory of 16 real Central and State agricultural schemes (PM-KISAN, PMFBY, Soil Health Card, PMKSY, PKVY, KCC, PM-KUSUM, e-NAM, AIF, and state top-ups).
  - Real-time keyword search and state filtering with official portal links.

- **🎨 Agricultural Earthy Design System**:
  - Soil-themed aesthetic (`#F7F2E9` cream, `#6B4423` soil brown, `#4C7A3D` leaf green, `#C46A2B` turmeric orange, `#6E97AC` sky blue).
  - Subtle furrow-line patterns and responsive mobile-first navigation tested at 375px viewport.

---

## 🛠️ Tech Stack

- **Backend**: Java 21, Spring Boot 3.3.5 (REST API, Spring Data JPA, H2 Database)
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
*H2 Console accessible at `http://localhost:8085/h2-console` (JDBC URL: `jdbc:h2:mem:kisaandb`).*

### 3. Run the Frontend
```bash
cd ../frontend
npm install
npm run dev
```
*Frontend runs on `http://localhost:5173`.*

---

## 🔑 Environment Variables (Optional)

The application includes built-in realistic fallbacks and operates immediately out of the box. To connect live keys:

```properties
# backend/src/main/resources/application.properties or OS environment variables
GEMINI_API_KEY=your_gemini_api_key_here
OPENWEATHERMAP_API_KEY=your_openweather_api_key_here
```

---

## 📄 License
MIT License &copy; 2026 KISAAN.AI Team.
