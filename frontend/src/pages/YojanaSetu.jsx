import React, { useState, useEffect } from 'react';
import { useLanguage } from '../context/LanguageContext';
import {
  Landmark,
  ExternalLink,
  Search,
  Filter,
  ShieldCheck,
  CheckCircle,
  HelpCircle,
  Sparkles,
  ArrowRight,
  Award,
  Layers,
  Clock
} from 'lucide-react';

const STATE_LIST = [
  'All India',
  'Maharashtra',
  'Andhra Pradesh',
  'Telangana',
  'Madhya Pradesh',
  'Himachal Pradesh',
  'Punjab',
  'Haryana',
  'Uttar Pradesh',
  'Gujarat',
  'Karnataka'
];

const CATEGORY_CHIPS = [
  'All Schemes',
  'Recent (Last 5 Years)',
  'Technology & Drones',
  'Renewable Energy',
  'Natural & Bio Farming',
  'Income Support',
  'Credit & Loans',
  'Post-Harvest & Processing',
  'State Top-Up'
];

export default function YojanaSetu() {
  const { t, language } = useLanguage();
  const [schemes, setSchemes] = useState([]);
  const [selectedState, setSelectedState] = useState('All India');
  const [selectedCategory, setSelectedCategory] = useState('All Schemes');
  const [searchQuery, setSearchQuery] = useState('');
  const [loading, setLoading] = useState(true);

  const fetchSchemes = () => {
    setLoading(true);
    const url = selectedState === 'All India'
      ? '/api/schemes'
      : `/api/schemes?state=${encodeURIComponent(selectedState)}`;

    fetch(url)
      .then((res) => res.json())
      .then((data) => {
        setSchemes(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error('Failed to load schemes:', err);
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchSchemes();
  }, [selectedState]);

  const filteredSchemes = schemes.filter((s) => {
    // Category filter
    if (selectedCategory === 'Recent (Last 5 Years)') {
      const recentKeywords = ['2024', '2023', 'drone', 'digital', 'clean plant', 'pranam', 'gobardhan', 'surya ghar', 'agri-sure', 'natural farming', 'oil palm', 'pmfme'];
      const text = ((s.name || '') + ' ' + (s.description || '')).toLowerCase();
      const isRecent = recentKeywords.some((k) => text.includes(k));
      if (!isRecent) return false;
    } else if (selectedCategory !== 'All Schemes') {
      if (s.category && !s.category.toLowerCase().includes(selectedCategory.toLowerCase())) {
        return false;
      }
    }

    // Keyword search
    if (!searchQuery.trim()) return true;
    const query = searchQuery.toLowerCase();
    const nameMatch = (s.name && s.name.toLowerCase().includes(query)) ||
                      (s.nameHi && s.nameHi.toLowerCase().includes(query));
    const descMatch = (s.description && s.description.toLowerCase().includes(query)) ||
                      (s.descriptionHi && s.descriptionHi.toLowerCase().includes(query));
    const catMatch = s.category && s.category.toLowerCase().includes(query);
    return nameMatch || descMatch || catMatch;
  });

  return (
    <div className="space-y-6 max-w-6xl mx-auto animate-fadeIn">
      {/* Page Header */}
      <div className="bg-white rounded-3xl p-6 sm:p-8 border border-[#E2D9CC] shadow-sm relative overflow-hidden">
        <div className="absolute inset-0 furrow-pattern pointer-events-none opacity-40" />
        <div className="relative">
          <div className="inline-flex items-center space-x-1.5 px-3 py-1 rounded-full text-xs font-semibold bg-[#FDF2E9] text-[#C46A2B] border border-[#F6DCC7] mb-2">
            <Landmark size={14} />
            <span>
              {language === 'hi'
                ? 'सरकारी कृषि कल्याण योजनाएं (30+ योजनाएं)'
                : 'Central & State Agriculture Welfare Directory (30+ Schemes)'}
            </span>
          </div>
          <h1 className="text-2xl sm:text-3xl font-bold text-[#6B4423] font-['Poppins']">
            {t('yojanaTitle')}
          </h1>
          <p className="text-xs sm:text-sm text-[#5C4533] mt-1">
            {language === 'hi'
              ? 'डिजिटल कृषि मिशन, किसान ड्रोन, क्लीन प्लांट और पीएम-किसान सहित पिछले 5 वर्षों की प्रमुख योजनाएं'
              : 'Direct access to high-impact welfare programs from the last 5 years including Digital Agri Mission, Kisan Drones & Solar Pumps'}
          </p>

          {/* Filter and Search Bar */}
          <div className="mt-6 pt-4 border-t border-[#EFE8DC] grid grid-cols-1 md:grid-cols-3 gap-3">
            {/* State Selector */}
            <div className="flex items-center space-x-2 bg-[#F7F2E9] px-3.5 py-2.5 rounded-2xl border border-[#DECDBE]">
              <Filter size={16} className="text-[#6B4423] shrink-0" />
              <div className="flex-1">
                <span className="block text-[10px] font-bold uppercase text-[#8A7463]">
                  {t('yojanaFilterState')}
                </span>
                <select
                  value={selectedState}
                  onChange={(e) => setSelectedState(e.target.value)}
                  className="bg-transparent text-sm font-semibold text-[#6B4423] focus:outline-none w-full cursor-pointer"
                >
                  {STATE_LIST.map((st) => (
                    <option key={st} value={st}>
                      {st === 'All India' ? t('yojanaAllStates') : st}
                    </option>
                  ))}
                </select>
              </div>
            </div>

            {/* Keyword Search Input */}
            <div className="md:col-span-2 flex items-center space-x-2 bg-[#F7F2E9] px-3.5 py-2.5 rounded-2xl border border-[#DECDBE]">
              <Search size={18} className="text-[#8A7463] shrink-0" />
              <input
                type="text"
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                placeholder={language === 'hi' ? 'योजना खोजें (उदा. ड्रोन, सोलर, डिजिटल, सब्सिडी)...' : 'Search schemes by keyword (e.g. drone, solar, digital, subsidy, credit)...'}
                className="bg-transparent text-sm text-[#2C1E14] placeholder-[#8A7463] focus:outline-none w-full"
              />
            </div>
          </div>

          {/* Quick Category Filter Chips */}
          <div className="mt-4 flex flex-wrap gap-1.5">
            {CATEGORY_CHIPS.map((cat) => (
              <button
                key={cat}
                type="button"
                onClick={() => setSelectedCategory(cat)}
                className={`text-xs px-3 py-1.5 rounded-xl font-medium transition-all ${
                  selectedCategory === cat
                    ? 'bg-[#4C7A3D] text-white shadow-sm font-bold'
                    : 'bg-[#F7F2E9] text-[#5C4533] border border-[#DECDBE] hover:bg-[#EFE8DC]'
                }`}
              >
                {cat}
              </button>
            ))}
          </div>
        </div>
      </div>

      {/* Loading Skeleton */}
      {loading && (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {[1, 2, 3, 4, 5, 6].map((i) => (
            <div key={i} className="bg-white rounded-3xl p-6 border border-[#E2D9CC] h-72 animate-pulse" />
          ))}
        </div>
      )}

      {/* Schemes Card Grid */}
      {!loading && filteredSchemes.length > 0 && (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {filteredSchemes.map((scheme) => (
            <div
              key={scheme.id}
              className="bg-white rounded-3xl p-6 border border-[#E2D9CC] shadow-sm flex flex-col justify-between hover:shadow-md hover:border-[#DECDBE] transition-all duration-300"
            >
              <div>
                {/* Header Category & State Tag */}
                <div className="flex items-center justify-between gap-2 pb-3 border-b border-[#EFE8DC]">
                  <span className="text-[10px] font-bold uppercase tracking-wider px-2.5 py-0.5 rounded-full bg-[#EAF3E7] text-[#4C7A3D] border border-[#D1E6CC]">
                    {scheme.category}
                  </span>
                  <span className="text-[10px] font-semibold text-[#8A7463] px-2 py-0.5 rounded-md bg-[#F7F2E9]">
                    {scheme.isNational
                      ? (language === 'hi' ? '🇮🇳 राष्ट्रीय योजना' : '🇮🇳 National')
                      : `🚩 ${scheme.applicableStates ? scheme.applicableStates[0] : 'State'}`}
                  </span>
                </div>

                {/* Scheme Title */}
                <h3 className="text-base font-bold text-[#6B4423] font-['Poppins'] mt-3 leading-snug">
                  {language === 'hi' && scheme.nameHi ? scheme.nameHi : scheme.name}
                </h3>

                {/* One-line Description */}
                <p className="text-xs text-[#5C4533] mt-2 leading-relaxed">
                  {language === 'hi' && scheme.descriptionHi ? scheme.descriptionHi : scheme.description}
                </p>

                {/* Key Benefits Callout */}
                <div className="mt-4 p-3 rounded-2xl bg-[#FDF2E9] border border-[#F6DCC7] space-y-1">
                  <span className="text-[10px] font-bold uppercase tracking-wider text-[#C46A2B] block">
                    🎁 {t('yojanaBenefits')}:
                  </span>
                  <p className="text-xs font-semibold text-[#7C3E14] leading-snug">
                    {language === 'hi' && scheme.benefitsHi ? scheme.benefitsHi : scheme.benefits}
                  </p>
                </div>

                {/* Eligibility Note */}
                <div className="mt-3 p-2.5 rounded-xl bg-[#F7F2E9] border border-[#DECDBE] text-[11px] text-[#5C4533] space-y-0.5">
                  <span className="font-bold text-[#6B4423] block">
                    ✓ {t('yojanaEligibility')}:
                  </span>
                  <p className="leading-snug">
                    {language === 'hi' && scheme.eligibilityHi ? scheme.eligibilityHi : scheme.eligibility}
                  </p>
                </div>
              </div>

              {/* Official Govt Link Button */}
              <div className="mt-6 pt-3 border-t border-[#EFE8DC]">
                <a
                  href={scheme.officialUrl}
                  target="_blank"
                  rel="noopener noreferrer"
                  className="w-full flex items-center justify-center space-x-2 py-2.5 px-4 rounded-xl bg-[#4C7A3D] hover:bg-[#3F6632] text-white text-xs font-bold shadow-sm transition-all transform active:scale-95"
                >
                  <span>{t('yojanaApplyBtn')}</span>
                  <ExternalLink size={13} />
                </a>
              </div>
            </div>
          ))}
        </div>
      )}

      {/* Empty State */}
      {!loading && filteredSchemes.length === 0 && (
        <div className="bg-white rounded-3xl p-12 text-center border border-[#E2D9CC] space-y-3">
          <div className="w-16 h-16 mx-auto rounded-3xl bg-[#F7F2E9] text-[#6B4423] flex items-center justify-center text-2xl">
            🏛️
          </div>
          <h2 className="text-lg font-bold text-[#6B4423]">
            {language === 'hi' ? 'कोई योजना नहीं मिली' : 'No matching schemes found'}
          </h2>
          <p className="text-xs text-[#8A7463]">
            {language === 'hi' ? 'कृपया अन्य श्रेणी चुनें या खोज शब्द बदलें।' : 'Try resetting your category filter or selecting "All India".'}
          </p>
          <button
            type="button"
            onClick={() => {
              setSearchQuery('');
              setSelectedCategory('All Schemes');
              setSelectedState('All India');
            }}
            className="px-4 py-2 rounded-xl bg-[#4C7A3D] text-white text-xs font-bold"
          >
            {language === 'hi' ? 'फ़िल्टर हटाएं' : 'Reset Filters'}
          </button>
        </div>
      )}
    </div>
  );
}
