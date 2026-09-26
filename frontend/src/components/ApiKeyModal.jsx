import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { useLanguage } from '../context/LanguageContext';
import { X, Key, Sparkles, Check, Trash2, ExternalLink } from 'lucide-react';

export default function ApiKeyModal() {
  const { isKeyModalOpen, setIsKeyModalOpen, geminiApiKey, setGeminiApiKey } = useAuth();
  const { language } = useLanguage();
  const [inputKey, setInputKey] = useState(geminiApiKey || '');
  const [savedSuccess, setSavedSuccess] = useState(false);

  if (!isKeyModalOpen) return null;

  const handleSave = (e) => {
    e.preventDefault();
    setGeminiApiKey(inputKey.trim());
    setSavedSuccess(true);
    setTimeout(() => {
      setSavedSuccess(false);
      setIsKeyModalOpen(false);
    }, 900);
  };

  const handleClear = () => {
    setInputKey('');
    setGeminiApiKey('');
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-[#2C1E14]/70 backdrop-blur-sm p-4 animate-fadeIn">
      <div className="bg-[#F7F2E9] border border-[#DECDBE] rounded-3xl max-w-md w-full p-6 shadow-2xl relative">
        <button
          type="button"
          onClick={() => setIsKeyModalOpen(false)}
          className="absolute top-4 right-4 p-2 text-[#8A7463] hover:text-[#2C1E14] rounded-full hover:bg-[#EFE8DC] transition-colors"
        >
          <X size={18} />
        </button>

        <div className="text-center pt-1">
          <div className="w-12 h-12 rounded-2xl bg-[#4C7A3D] text-white flex items-center justify-center mx-auto text-xl shadow-md">
            <Key size={22} />
          </div>
          <h2 className="text-xl font-bold text-[#6B4423] font-['Poppins'] mt-2">
            {language === 'hi' ? 'जेमिनी एआई सेटिंग्स' : 'Google Gemini API Setup'}
          </h2>
          <p className="text-xs text-[#5C4533] mt-0.5">
            {language === 'hi'
              ? 'लाइव फसल रोग विश्लेषण के लिए अपनी जेमिनी एपीआई की (Key) दर्ज करें'
              : 'Enter your Gemini API key for live multimodal crop disease diagnosis'}
          </p>
        </div>

        {/* Current Status Pill */}
        <div className="mt-4 p-3 rounded-2xl bg-[#EAF3E7] border border-[#D1E6CC] text-xs">
          <div className="flex items-center space-x-2 font-bold text-[#2E4C24]">
            <Sparkles size={14} className="text-[#4C7A3D]" />
            <span>
              {geminiApiKey
                ? (language === 'hi' ? 'सक्रिय स्थिति: लाइव जेमिनी एआई मॉडल' : 'Active: Live Google Gemini 2.5/Flash AI')
                : (language === 'hi' ? 'सक्रिय स्थिति: इन-बिल्ट मल्टी-क्रॉप विज़न इंजन' : 'Active: Built-in Multi-Crop Pathology Engine')}
            </span>
          </div>
          <p className="text-[11px] text-[#4C7A3D] mt-1 leading-relaxed">
            {geminiApiKey
              ? 'Your custom API key is stored locally in your browser session.'
              : 'Even without an API key, our rich 12+ crop pathology model recognizes Wheat, Rice, Cotton, Tomato, Grape, Chilli, and Corn leaves dynamically.'}
          </p>
        </div>

        <form onSubmit={handleSave} className="mt-4 space-y-3">
          <div>
            <label className="block text-[11px] font-bold uppercase text-[#6B4423] mb-1">
              Google Gemini API Key
            </label>
            <input
              type="password"
              value={inputKey}
              onChange={(e) => setInputKey(e.target.value)}
              placeholder="AIzaSy..."
              className="w-full px-3.5 py-2.5 rounded-xl border border-[#DECDBE] bg-white text-xs text-[#2C1E14] focus:outline-none focus:border-[#4C7A3D]"
            />
          </div>

          <div className="flex items-center justify-between text-[11px] text-[#8A7463]">
            <a
              href="https://aistudio.google.com/app/apikey"
              target="_blank"
              rel="noopener noreferrer"
              className="text-[#4C7A3D] hover:underline flex items-center gap-1 font-semibold"
            >
              <span>Get a free key from Google AI Studio</span>
              <ExternalLink size={11} />
            </a>

            {geminiApiKey && (
              <button
                type="button"
                onClick={handleClear}
                className="text-red-600 hover:text-red-700 flex items-center gap-1 font-semibold"
              >
                <Trash2 size={11} />
                <span>Clear</span>
              </button>
            )}
          </div>

          <button
            type="submit"
            className="w-full mt-2 py-3 rounded-xl bg-[#4C7A3D] hover:bg-[#3F6632] text-white font-bold text-xs shadow-md transition-all flex items-center justify-center space-x-1.5"
          >
            {savedSuccess ? (
              <>
                <Check size={16} />
                <span>Saved Successfully!</span>
              </>
            ) : (
              <span>Save & Activate Gemini AI</span>
            )}
          </button>
        </form>
      </div>
    </div>
  );
}
