import React, { useState, useRef } from 'react';
import { useLanguage } from '../context/LanguageContext';
import { useAuth } from '../context/AuthContext';
import {
  Camera,
  Upload,
  AlertTriangle,
  CheckCircle2,
  Sparkles,
  ShieldCheck,
  RefreshCw,
  Droplet,
  CloudSun,
  Flame,
  FileQuestion,
  Leaf,
  ExternalLink,
  Key,
  Layers,
  History,
  Activity
} from 'lucide-react';

const CROP_OPTIONS = [
  { id: 'auto', name: 'Auto Detect (स्वतः पहचान)', icon: '✨' },
  { id: 'tomato', name: 'Tomato (टमाटर)', icon: '🍅' },
  { id: 'wheat', name: 'Wheat (गेहूं)', icon: '🌾' },
  { id: 'rice', name: 'Rice / Paddy (धान)', icon: '🌾' },
  { id: 'cotton', name: 'Cotton (कपास)', icon: '🌱' },
  { id: 'chilli', name: 'Chilli (मिर्च)', icon: '🌶️' },
  { id: 'potato', name: 'Potato (आलू)', icon: '🥔' },
  { id: 'corn', name: 'Corn / Maize (मक्का)', icon: '🌽' },
  { id: 'grape', name: 'Grapes (अंगूर)', icon: '🍇' },
  { id: 'sugarcane', name: 'Sugarcane (गन्ना)', icon: '🎋' },
  { id: 'apple', name: 'Apple (सेब)', icon: '🍎' },
  { id: 'mango', name: 'Mango (आम)', icon: '🥭' },
  { id: 'soybean', name: 'Soybean (सोयाबीन)', icon: '🌱' },
  { id: 'mustard', name: 'Mustard (सरसों)', icon: '🌼' },
  { id: 'healthy', name: 'Healthy Leaf (स्वस्थ पत्ती)', icon: '🌿' }
];

export default function Scanner() {
  const { t, language } = useLanguage();
  const { user, geminiApiKey, setIsKeyModalOpen } = useAuth();

  const [selectedCrop, setSelectedCrop] = useState(CROP_OPTIONS[0].id);
  const [file, setFile] = useState(null);
  const [previewUrl, setPreviewUrl] = useState(null);
  const [loading, setLoading] = useState(false);
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);
  const fileInputRef = useRef(null);

  const handleFileChange = (e) => {
    if (e.target.files && e.target.files[0]) {
      const selectedFile = e.target.files[0];
      setFile(selectedFile);
      setPreviewUrl(URL.createObjectURL(selectedFile));
      setResult(null);
      setError(null);
    }
  };

  const handleDrop = (e) => {
    e.preventDefault();
    if (e.dataTransfer.files && e.dataTransfer.files[0]) {
      const droppedFile = e.dataTransfer.files[0];
      setFile(droppedFile);
      setPreviewUrl(URL.createObjectURL(droppedFile));
      setResult(null);
      setError(null);
    }
  };

  const loadSampleLeaf = (cropId) => {
    setSelectedCrop(cropId);
    const canvas = document.createElement('canvas');
    canvas.width = 400;
    canvas.height = 300;
    const ctx = canvas.getContext('2d');

    // Background farm ground
    ctx.fillStyle = '#604229';
    ctx.fillRect(0, 0, 400, 300);

    // Leaf base
    ctx.beginPath();
    ctx.ellipse(200, 150, 140, 70, 0.4, 0, 2 * Math.PI);
    ctx.fillStyle = cropId === 'healthy' ? '#3B7A2B' : '#556B2F';
    ctx.fill();

    // Leaf main vein
    ctx.beginPath();
    ctx.moveTo(90, 110);
    ctx.quadraticCurveTo(200, 150, 310, 190);
    ctx.lineWidth = 4;
    ctx.strokeStyle = '#8DA351';
    ctx.stroke();

    // Specific symptom markers
    if (cropId === 'wheat') {
      ctx.fillStyle = '#E5A91E'; // yellow stripe rust
      for (let i = 0; i < 30; i++) {
        ctx.fillRect(110 + i * 5, 120 + (i % 3) * 14, 18, 3);
      }
    } else if (cropId === 'rice') {
      ctx.fillStyle = '#D6CEAA'; // bacterial blight wavy edge
      ctx.fillRect(90, 95, 200, 12);
    } else if (cropId === 'cotton') {
      ctx.fillStyle = '#795548'; // bacterial angular leaf spot
      for (let i = 0; i < 20; i++) {
        ctx.fillRect(130 + (i % 5) * 25, 115 + Math.floor(i / 5) * 18, 10, 8);
      }
    } else if (cropId === 'chilli') {
      ctx.fillStyle = '#3E2723';
      for (let i = 0; i < 15; i++) {
        ctx.beginPath();
        ctx.arc(140 + i * 8, 130 + (i % 4) * 10, 3, 0, 2 * Math.PI);
        ctx.fill();
      }
    } else if (cropId === 'grape') {
      ctx.fillStyle = '#EFEBE9'; // downy mildew
      ctx.beginPath();
      ctx.arc(180, 145, 25, 0, 2 * Math.PI);
      ctx.arc(220, 160, 20, 0, 2 * Math.PI);
      ctx.fill();
    } else if (cropId === 'apple') {
      ctx.fillStyle = '#212121'; // apple scab
      for (let i = 0; i < 8; i++) {
        ctx.beginPath();
        ctx.arc(150 + i * 16, 135 + (i % 3) * 12, 10, 0, 2 * Math.PI);
        ctx.fill();
      }
    } else if (cropId === 'sugarcane') {
      ctx.fillStyle = '#B71C1C'; // red rot
      ctx.fillRect(120, 145, 160, 10);
    } else if (cropId !== 'healthy' && cropId !== 'auto') {
      // General necrotic spots (blight)
      ctx.fillStyle = '#3D2012';
      ctx.beginPath();
      ctx.arc(170, 140, 24, 0, 2 * Math.PI);
      ctx.arc(230, 170, 18, 0, 2 * Math.PI);
      ctx.fill();
    }

    canvas.toBlob((blob) => {
      const sampleFile = new File([blob], `${cropId}_sample_leaf.jpg`, { type: 'image/jpeg' });
      setFile(sampleFile);
      setPreviewUrl(URL.createObjectURL(blob));
      setResult(null);
      setError(null);
    }, 'image/jpeg');
  };

  const runAnalysis = async () => {
    if (!file) return;
    setLoading(true);
    setError(null);

    let activeLat = '19.9975';
    let activeLon = '73.7898';
    try {
      const savedLoc = localStorage.getItem('kisaan_selected_location');
      if (savedLoc) {
        const parsed = JSON.parse(savedLoc);
        if (parsed.lat && parsed.lon) {
          activeLat = String(parsed.lat);
          activeLon = String(parsed.lon);
        }
      }
    } catch (e) {}

    const formData = new FormData();
    formData.append('file', file);
    formData.append('lat', activeLat);
    formData.append('lon', activeLon);
    formData.append('language', language === 'hi' ? 'hi' : 'en');
    formData.append('cropHint', selectedCrop);
    formData.append('engine', 'icar');
    if (geminiApiKey) {
      formData.append('apiKey', geminiApiKey);
    }

    try {
      const res = await fetch('/api/scan', {
        method: 'POST',
        body: formData,
      });
      if (!res.ok) throw new Error('HTTP ' + res.status);
      const data = await res.json();
      setResult(data);

      // Save to user scan history if user is logged in
      if (user && data.candidates && data.candidates.length > 0) {
        fetch('/api/auth/scan-history', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            userId: user.id,
            cropName: selectedCrop.toUpperCase(),
            diseaseName: data.candidates[0].disease_name,
            confidence: data.candidates[0].confidence,
            severity: data.candidates[0].severity,
            recommendedAction: data.recommended_action,
            weatherRisk: data.weather_risk_note
          })
        }).catch((e) => console.log('History save:', e));
      }
    } catch (err) {
      setError(err.message || 'Scan failed. Please check network connection.');
    } finally {
      setLoading(false);
    }
  };

  const getSeverityBadgeClass = (severity) => {
    const s = (severity || '').toLowerCase();
    if (s === 'severe' || s.includes('गंभीर')) return 'bg-red-500 text-white';
    if (s === 'moderate' || s.includes('मध्यम')) return 'bg-[#C46A2B] text-white';
    return 'bg-[#4C7A3D] text-white';
  };

  return (
    <div className="space-y-6 max-w-4xl mx-auto animate-fadeIn">
      {/* Page Header with Gemini API Key setup button */}
      <div className="bg-white rounded-3xl p-6 sm:p-8 border border-[#E2D9CC] shadow-sm relative overflow-hidden">
        <div className="absolute inset-0 furrow-pattern pointer-events-none opacity-40" />
        <div className="relative flex flex-col md:flex-row md:items-center md:justify-between gap-4">
          <div>
            <div className="inline-flex items-center space-x-1.5 px-3 py-1 rounded-full text-xs font-semibold bg-[#EAF3E7] text-[#4C7A3D] border border-[#D1E6CC] mb-2">
              <Sparkles size={14} />
              <span>
                🌿 Plant Pathology AI (Active - PlantVillage & ICAR Dataset) - 100% Free / Zero API Key Required
              </span>
            </div>
            <h1 className="text-2xl sm:text-3xl font-bold text-[#6B4423] font-['Poppins']">
              {t('scannerTitle')}
            </h1>
            <p className="text-xs sm:text-sm text-[#5C4533] mt-1">
              {t('scannerSubtitle')}
            </p>
          </div>

          {/* Gemini API Key Configuration Button (Optional Cloud Fallback) */}
          <button
            type="button"
            onClick={() => setIsKeyModalOpen(true)}
            className="self-start md:self-auto inline-flex items-center space-x-1.5 px-4 py-2.5 rounded-2xl bg-[#F7F2E9] hover:bg-[#EFE8DC] border border-[#DECDBE] text-[#6B4423] text-xs font-bold shadow-sm transition-all"
          >
            <Key size={14} className="text-[#C46A2B]" />
            <span>{geminiApiKey ? 'Cloud Fallback Configured' : 'Optional Cloud Fallback Key'}</span>
          </button>
        </div>

        {/* Dynamic Crop Category Selector Chips */}
        <div className="mt-5 pt-4 border-t border-[#EFE8DC]">
          <span className="block text-xs font-bold uppercase text-[#8A7463] mb-2 tracking-wide">
            {language === 'hi' ? 'फसल का प्रकार चुनें:' : 'Select Target Crop:'}
          </span>
          <div className="flex flex-wrap gap-2">
            {CROP_OPTIONS.map((c) => (
              <button
                key={c.id}
                type="button"
                onClick={() => loadSampleLeaf(c.id)}
                className={`px-3 py-1.5 rounded-xl text-xs font-semibold flex items-center gap-1.5 transition-all ${
                  selectedCrop === c.id
                    ? 'bg-[#4C7A3D] text-white shadow-sm ring-2 ring-[#4C7A3D]/30'
                    : 'bg-[#F7F2E9] text-[#6B4423] border border-[#DECDBE] hover:bg-[#EFE8DC]'
                }`}
              >
                <span>{c.icon}</span>
                <span>{c.name}</span>
              </button>
            ))}
          </div>
        </div>
      </div>

      {/* Upload & Capture Zone */}
      <div className="bg-white rounded-3xl p-6 sm:p-8 border border-[#DECDBE] shadow-sm">
        <input
          type="file"
          ref={fileInputRef}
          onChange={handleFileChange}
          accept="image/*"
          capture="environment"
          className="hidden"
        />

        {!previewUrl ? (
          <div>
            <div
              onDragOver={(e) => e.preventDefault()}
              onDrop={handleDrop}
              onClick={() => fileInputRef.current && fileInputRef.current.click()}
              className="border-2 border-dashed border-[#4C7A3D]/40 hover:border-[#4C7A3D] bg-[#F7F2E9]/70 hover:bg-[#F7F2E9] rounded-3xl p-8 sm:p-12 text-center cursor-pointer transition-all active:scale-[0.99] group"
            >
              <div className="w-20 h-20 mx-auto rounded-3xl bg-[#EAF3E7] group-hover:bg-[#4C7A3D] text-[#4C7A3D] group-hover:text-white flex items-center justify-center transition-all duration-300 shadow-md">
                <Camera size={36} strokeWidth={2} />
              </div>

              <h2 className="mt-5 text-lg sm:text-xl font-bold text-[#6B4423] font-['Poppins']">
                {t('scannerUploadPrompt')}
              </h2>
              <p className="text-xs sm:text-sm text-[#8A7463] mt-1.5 max-w-sm mx-auto">
                {t('scannerUploadSub')}
              </p>

              <button
                type="button"
                className="mt-6 inline-flex items-center justify-center space-x-2 px-6 py-3.5 rounded-2xl bg-[#4C7A3D] hover:bg-[#3F6632] text-white font-bold text-sm shadow-md transition-all transform group-hover:scale-105"
              >
                <Camera size={18} />
                <span>{t('scannerCameraBtn')}</span>
              </button>
            </div>
          </div>
        ) : (
          <div className="space-y-4">
            {/* Image Preview with scanner effect */}
            <div className="relative rounded-2xl overflow-hidden bg-black/5 border border-[#DECDBE] max-h-80 flex items-center justify-center">
              <img
                src={previewUrl}
                alt="Selected Crop Leaf"
                className="max-h-80 w-full object-contain"
              />

              {loading && (
                <div className="absolute inset-0 bg-[#4C7A3D]/20 backdrop-blur-[1px] flex flex-col items-center justify-center text-white">
                  <div className="w-full h-1 bg-[#4C7A3D] shadow-[0_0_15px_#4C7A3D] animate-bounce absolute top-1/2" />
                  <div className="bg-[#2C1E14]/85 px-4 py-3 rounded-2xl text-center shadow-lg border border-[#DECDBE]">
                    <RefreshCw size={24} className="animate-spin mx-auto text-[#4C7A3D] mb-1.5" />
                    <p className="text-sm font-bold">{t('scannerAnalyzing')}</p>
                    <p className="text-xs text-[#DECDBE] mt-0.5">{t('scannerAnalyzingSub')}</p>
                  </div>
                </div>
              )}
            </div>

            {/* Action Buttons */}
            <div className="flex flex-col sm:flex-row items-center gap-3">
              <button
                type="button"
                disabled={loading}
                onClick={runAnalysis}
                className="w-full sm:flex-1 py-3.5 px-6 rounded-2xl bg-[#4C7A3D] hover:bg-[#3F6632] disabled:opacity-50 text-white font-bold text-sm shadow-md flex items-center justify-center space-x-2 transition-all"
              >
                <Sparkles size={18} />
                <span>{language === 'hi' ? 'एआई जांच शुरू करें' : 'Diagnose Disease with AI'}</span>
              </button>

              <button
                type="button"
                disabled={loading}
                onClick={() => {
                  setFile(null);
                  setPreviewUrl(null);
                  setResult(null);
                  setError(null);
                }}
                className="w-full sm:w-auto py-3.5 px-5 rounded-2xl bg-[#EFE8DC] hover:bg-[#DECDBE] text-[#6B4423] font-semibold text-sm transition-colors"
              >
                {t('scannerScanAnother')}
              </button>
            </div>
          </div>
        )}
      </div>

      {/* Error Notice */}
      {error && (
        <div className="p-4 rounded-2xl bg-red-50 border border-red-200 text-red-700 text-sm flex items-center space-x-2">
          <AlertTriangle size={18} />
          <span>{error}</span>
        </div>
      )}

      {/* DIAGNOSTIC RESULTS CARD */}
      {result && (
        <div className="bg-white rounded-3xl p-6 sm:p-8 border border-[#DECDBE] shadow-lg space-y-6 animate-fadeIn">
          {/* Top diagnostic header */}
          <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-3 pb-5 border-b border-[#EFE8DC]">
            <div className="flex items-center space-x-3">
              <div
                className={`w-12 h-12 rounded-2xl flex items-center justify-center text-white shadow-inner ${
                  result.healthy ? 'bg-[#4C7A3D]' : 'bg-[#C46A2B]'
                }`}
              >
                {result.healthy ? <ShieldCheck size={28} /> : <AlertTriangle size={28} />}
              </div>
              <div>
                <span className="text-xs font-bold uppercase tracking-wider text-[#8A7463]">
                  {t('scannerResultsTitle')} &bull; {(result.crop || selectedCrop).toUpperCase()}
                </span>
                <h2 className="text-xl sm:text-2xl font-bold text-[#6B4423] font-['Poppins']">
                  {result.healthy
                    ? t('scannerHealthyPlant')
                    : result.candidates && result.candidates[0]
                    ? result.candidates[0].disease_name
                    : 'Diagnosis Complete'}
                </h2>
              </div>
            </div>

            {/* Severity Badge */}
            {result.candidates && result.candidates[0] && !result.healthy && (
              <div className="flex items-center space-x-2 self-start sm:self-auto">
                <span className="text-xs font-semibold text-[#8A7463] uppercase">
                  {t('scannerSeverity')}:
                </span>
                <span
                  className={`text-xs px-3 py-1 rounded-full font-bold uppercase tracking-wider shadow-sm ${getSeverityBadgeClass(
                    result.candidates[0].severity
                  )}`}
                >
                  {result.candidates[0].severity}
                </span>
              </div>
            )}
          </div>

          {/* Model info banner */}
          <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-1 text-[11px] text-[#8A7463] bg-[#F7F2E9] px-3.5 py-2 rounded-xl border border-[#DECDBE]">
            <span className="font-bold text-[#6B4423]">
              🔬 Model: {result.model_used || 'PyTorch MobileNetV3 (PlantVillage & ICAR Dataset)'}
            </span>
            <span className="text-[#4C7A3D] font-bold">
              ✓ 45+ Crop Pathology Classes &bull; Microclimatic Root-Zone Grounded
            </span>
          </div>

          {/* LEAF TISSUE BIOMARKERS ANALYSIS */}
          {result.metrics && (
            <div className="bg-[#FAF7F2] rounded-2xl p-4 sm:p-5 border border-[#DECDBE] space-y-3">
              <div className="flex items-center justify-between">
                <span className="text-xs font-bold uppercase tracking-wider text-[#6B4423] flex items-center gap-1.5">
                  <Activity size={15} className="text-[#4C7A3D]" />
                  {language === 'hi' ? 'पत्ती ऊतक बायोमार्कर विश्लेषण (ExG स्पेक्ट्रल)' : 'Leaf Tissue Biomarker Metrics (ExG Spectral Analysis)'}
                </span>
                <span className="text-[10px] text-[#4C7A3D] font-bold bg-[#EAF3E7] border border-[#D1E6CC] px-2.5 py-0.5 rounded-full">
                  Automated Multi-Spectral
                </span>
              </div>

              <div className="grid grid-cols-2 sm:grid-cols-4 gap-3">
                {/* Chlorophyll */}
                <div className="bg-white p-3 rounded-xl border border-[#E2D9CC] text-center shadow-xs">
                  <div className="text-[11px] text-[#8A7463] font-semibold flex items-center justify-center gap-1">
                    <Leaf size={12} className="text-[#4C7A3D]" />
                    {language === 'hi' ? 'क्लोरोफिल इंडेक्स' : 'Chlorophyll'}
                  </div>
                  <div className="text-lg font-black text-[#2E4C24] mt-1">
                    {result.metrics.chlorophyll_score ?? 85}%
                  </div>
                  <div className="text-[10px] text-[#4C7A3D] font-medium">
                    {result.metrics.chlorophyll_score > 70 ? (language === 'hi' ? 'सक्रिय हरित लवक' : 'Vigorous') : (language === 'hi' ? 'कम' : 'Depleted')}
                  </div>
                </div>

                {/* Chlorosis (Yellowing) */}
                <div className="bg-white p-3 rounded-xl border border-[#E2D9CC] text-center shadow-xs">
                  <div className="text-[11px] text-[#8A7463] font-semibold flex items-center justify-center gap-1">
                    <Flame size={12} className="text-amber-500" />
                    {language === 'hi' ? 'पीलापन (Chlorosis)' : 'Chlorosis'}
                  </div>
                  <div className="text-lg font-black text-amber-700 mt-1">
                    {result.metrics.chlorosis_percent ?? 0}%
                  </div>
                  <div className="text-[10px] text-amber-600 font-medium">
                    {result.metrics.chlorosis_percent > 15 ? (language === 'hi' ? 'पीलापन दृश्यमान' : 'Visible Yellowing') : (language === 'hi' ? 'न्यूनतम' : 'Normal')}
                  </div>
                </div>

                {/* Necrosis (Dead Tissue) */}
                <div className="bg-white p-3 rounded-xl border border-[#E2D9CC] text-center shadow-xs">
                  <div className="text-[11px] text-[#8A7463] font-semibold flex items-center justify-center gap-1">
                    <AlertTriangle size={12} className="text-red-500" />
                    {language === 'hi' ? 'नेक्रोसिस (सड़न)' : 'Necrosis'}
                  </div>
                  <div className="text-lg font-black text-red-700 mt-1">
                    {result.metrics.necrosis_percent ?? 0}%
                  </div>
                  <div className="text-[10px] text-red-600 font-medium">
                    {result.metrics.necrosis_percent > 10 ? (language === 'hi' ? 'ऊतक क्षति' : 'Tissue Damage') : (language === 'hi' ? 'सुरक्षित' : 'Healthy')}
                  </div>
                </div>

                {/* Spot Density */}
                <div className="bg-white p-3 rounded-xl border border-[#E2D9CC] text-center shadow-xs">
                  <div className="text-[11px] text-[#8A7463] font-semibold flex items-center justify-center gap-1">
                    <Droplet size={12} className="text-blue-500" />
                    {language === 'hi' ? 'धब्बों का घनत्व' : 'Spot Density'}
                  </div>
                  <div className="text-lg font-black text-[#6B4423] mt-1">
                    {result.metrics.spot_density ?? 0}
                  </div>
                  <div className="text-[10px] text-[#8A7463] font-medium">
                    {language === 'hi' ? 'धब्बे / सेमी²' : 'lesions / cm²'}
                  </div>
                </div>
              </div>
            </div>
          )}

          {/* STACKED CANDIDATES WITH CONFIDENCE BARS */}
          {result.candidates && result.candidates.length > 0 && (
            <div>
              <h3 className="text-sm font-bold text-[#6B4423] uppercase tracking-wide mb-3 flex items-center gap-1.5">
                <Leaf size={16} className="text-[#4C7A3D]" />
                {t('scannerCandidates')}
              </h3>

              <div className="space-y-3.5">
                {result.candidates.map((cand, idx) => (
                  <div
                    key={idx}
                    className="p-4 rounded-2xl border border-[#E2D9CC] bg-[#F7F2E9]/60 hover:bg-[#F7F2E9] transition-all space-y-2"
                  >
                    <div className="flex items-center justify-between">
                      <div className="flex items-center space-x-2">
                        <span className="w-5 h-5 rounded-full bg-[#6B4423] text-white text-[10px] font-bold flex items-center justify-center">
                          {idx + 1}
                        </span>
                        <span className="font-bold text-sm text-[#2C1E14]">
                          {cand.disease_name}
                        </span>
                      </div>
                      <span className="text-sm font-extrabold text-[#6B4423]">
                        {cand.confidence}% {t('scannerConfidence')}
                      </span>
                    </div>

                    <p className="text-xs text-[#5C4533] leading-relaxed">
                      {cand.affected_area_description}
                    </p>

                    <div className="w-full bg-[#EFE8DC] h-3 rounded-full overflow-hidden p-0.5">
                      <div
                        className={`h-full rounded-full transition-all duration-700 ${
                          idx === 0
                            ? 'bg-gradient-to-r from-[#C46A2B] to-[#4C7A3D]'
                            : 'bg-[#6E97AC]'
                        }`}
                        style={{ width: `${cand.confidence}%` }}
                      />
                    </div>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* WEATHER RISK NOTE */}
          {result.weather_risk_note && (
            <div className="p-4 sm:p-5 rounded-2xl bg-[#FDF2E9] border-2 border-[#F6DCC7] space-y-2 shadow-sm">
              <div className="flex items-center space-x-2 text-xs font-bold uppercase tracking-wider text-[#C46A2B]">
                <CloudSun size={17} />
                <span>{t('scannerWeatherRisk')}</span>
              </div>
              <p className="text-xs sm:text-sm font-medium text-[#7C3E14] leading-relaxed">
                {result.weather_risk_note}
              </p>
            </div>
          )}

          {/* ACTION & ORGANIC ALTERNATIVE */}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4 pt-2">
            <div className="p-4 sm:p-5 rounded-2xl bg-[#EAF3E7]/60 border border-[#D1E6CC] space-y-2">
              <div className="flex items-center space-x-2 text-xs font-bold uppercase tracking-wide text-[#2E4C24]">
                <ShieldCheck size={16} className="text-[#4C7A3D]" />
                <span>{t('scannerAction')}</span>
              </div>
              <p className="text-xs sm:text-sm text-[#2E4C24] leading-relaxed font-medium">
                {result.recommended_action}
              </p>
            </div>

            <div className="p-4 sm:p-5 rounded-2xl bg-[#F7F2E9] border border-[#DECDBE] space-y-2">
              <div className="flex items-center space-x-2 text-xs font-bold uppercase tracking-wide text-[#6B4423]">
                <Leaf size={16} className="text-[#4C7A3D]" />
                <span>{t('scannerOrganic')}</span>
              </div>
              <p className="text-xs sm:text-sm text-[#5C4533] leading-relaxed font-medium">
                {result.organic_alternative}
              </p>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
