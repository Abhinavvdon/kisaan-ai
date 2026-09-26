import React from 'react';
import { useLanguage } from '../context/LanguageContext';
import { Check, Globe } from 'lucide-react';

export default function LanguageModal() {
  const { language, setLanguage, showModal } = useLanguage();

  if (!showModal) return null;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-[#2C1E14]/70 backdrop-blur-sm p-4 animate-fadeIn">
      <div className="bg-[#F7F2E9] border border-[#DECDBE] rounded-3xl max-w-lg w-full p-6 sm:p-8 shadow-2xl relative overflow-hidden">
        {/* Decorative background accent */}
        <div className="absolute top-0 right-0 -mt-8 -mr-8 w-32 h-32 bg-[#4C7A3D]/10 rounded-full blur-xl pointer-events-none" />
        <div className="absolute bottom-0 left-0 -mb-8 -ml-8 w-32 h-32 bg-[#C46A2B]/10 rounded-full blur-xl pointer-events-none" />

        <div className="relative text-center">
          <div className="inline-flex items-center justify-center w-14 h-14 rounded-2xl bg-[#4C7A3D] text-white text-2xl shadow-md mb-3">
            🌱
          </div>
          <h2 className="text-2xl sm:text-3xl font-bold text-[#6B4423] font-['Poppins']">
            स्वागत है | Welcome
          </h2>
          <p className="text-sm text-[#5C4533] mt-2 font-medium">
            कृपया अपनी पसंदीदा भाषा चुनें / Choose preferred language
          </p>
        </div>

        <div className="grid grid-cols-1 sm:grid-cols-2 gap-4 mt-6">
          {/* English Card */}
          <button
            type="button"
            onClick={() => setLanguage('en')}
            className={`group relative p-5 rounded-2xl text-left border-2 transition-all transform active:scale-95 focus:outline-none ${
              language === 'en'
                ? 'border-[#4C7A3D] bg-white shadow-lg ring-2 ring-[#4C7A3D]/30'
                : 'border-[#DECDBE] bg-[#EFE8DC]/60 hover:bg-white hover:border-[#6B4423]'
            }`}
          >
            <div className="flex items-center justify-between">
              <span className="text-3xl font-serif font-bold text-[#6B4423]">A</span>
              {language === 'en' && (
                <div className="w-6 h-6 rounded-full bg-[#4C7A3D] text-white flex items-center justify-center">
                  <Check size={14} strokeWidth={3} />
                </div>
              )}
            </div>
            <div className="mt-3">
              <h3 className="text-lg font-bold text-[#6B4423] font-['Poppins']">
                English
              </h3>
              <p className="text-xs text-[#5C4533] mt-1 line-clamp-2">
                Access weather telemetry, AI scanner & schemes in English
              </p>
            </div>
            <div className="mt-4 pt-2 border-t border-[#DECDBE]/60 text-[11px] font-semibold text-[#4C7A3D] flex items-center justify-between">
              <span>Select English</span>
              <span>&rarr;</span>
            </div>
          </button>

          {/* Hindi Card */}
          <button
            type="button"
            onClick={() => setLanguage('hi')}
            className={`group relative p-5 rounded-2xl text-left border-2 transition-all transform active:scale-95 focus:outline-none ${
              language === 'hi'
                ? 'border-[#4C7A3D] bg-white shadow-lg ring-2 ring-[#4C7A3D]/30'
                : 'border-[#DECDBE] bg-[#EFE8DC]/60 hover:bg-white hover:border-[#6B4423]'
            }`}
          >
            <div className="flex items-center justify-between">
              <span className="text-3xl font-serif font-bold text-[#C46A2B]">अ</span>
              {language === 'hi' && (
                <div className="w-6 h-6 rounded-full bg-[#4C7A3D] text-white flex items-center justify-center">
                  <Check size={14} strokeWidth={3} />
                </div>
              )}
            </div>
            <div className="mt-3">
              <h3 className="text-lg font-bold text-[#6B4423] font-['Poppins']">
                हिंदी (Hindi)
              </h3>
              <p className="text-xs text-[#5C4533] mt-1 line-clamp-2">
                मौसम, रोग निदान, उपचार और सरकारी योजनाओं की पूरी जानकारी हिंदी में
              </p>
            </div>
            <div className="mt-4 pt-2 border-t border-[#DECDBE]/60 text-[11px] font-semibold text-[#C46A2B] flex items-center justify-between">
              <span>हिंदी चुनें</span>
              <span>&rarr;</span>
            </div>
          </button>
        </div>

        <div className="mt-6 text-center text-xs text-[#8A7463]">
          <span className="inline-flex items-center gap-1">
            <Globe size={13} />
            आप इसे कभी भी ऊपर दाईं ओर से बदल सकते हैं / Change anytime from top toggle
          </span>
        </div>
      </div>
    </div>
  );
}
