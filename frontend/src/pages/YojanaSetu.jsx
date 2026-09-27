import React, { useState, useEffect } from 'react';
import { useLanguage } from '../context/LanguageContext';
import { useAuth } from '../context/AuthContext';
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
  Clock,
  Zap,
  RefreshCw,
  Languages,
  CheckCircle2,
  FileText
} from 'lucide-react';
import { ALL_INDIAN_DISTRICTS } from '../data/allDistricts';

// Extract all unique Indian States & Union Territories dynamically
const ALL_INDIAN_STATES = [
  'All India',
  ...Array.from(new Set(ALL_INDIAN_DISTRICTS.map((d) => d.state))).sort()
];

const CATEGORY_CHIPS = [
  'All Schemes',
  'Income Support & Pensions',
  'Credit, Loans & KCC',
  'Crop Insurance & Relief',
  'Technology & Drones',
  'Irrigation & Solar Energy',
  'Natural & Bio Farming',
  'Horticulture & Orchards',
  'Livestock, Dairy & Fisheries',
  'Soil Health & Marketing',
  'Post-Harvest & Processing',
  'State Top-Up & Subsidies'
];

export default function YojanaSetu() {
  const { t, language } = useLanguage();
  const { user, geminiApiKey } = useAuth();

  const [schemes, setSchemes] = useState([]);
  const [selectedState, setSelectedState] = useState('All India');
  const [selectedCategory, setSelectedCategory] = useState('All Schemes');
  const [searchQuery, setSearchQuery] = useState('');
  const [loading, setLoading] = useState(true);
  const [isLiveFetching, setIsLiveFetching] = useState(false);
  const [liveFetchSuccess, setLiveFetchSuccess] = useState(false);

  // Per-card translation overrides: { [schemeId]: 'hi' | 'en' }
  const [cardLangOverrides, setCardLangOverrides] = useState({});

  // Auto-detect user state if logged in
  useEffect(() => {
    if (user?.state && ALL_INDIAN_STATES.includes(user.state)) {
      setSelectedState(user.state);
    }
  }, [user]);

  const fetchSchemes = () => {
    setLoading(true);
    let url = '/api/schemes';
    const params = new URLSearchParams();

    if (selectedState !== 'All India') {
      params.append('state', selectedState);
    }
    if (selectedCategory !== 'All Schemes') {
      params.append('category', selectedCategory);
    }
    if (searchQuery.trim()) {
      params.append('search', searchQuery.trim());
    }

    const queryString = params.toString();
    if (queryString) {
      url += `?${queryString}`;
    }

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
  }, [selectedState, selectedCategory]);

  const handleSearchSubmit = (e) => {
    e.preventDefault();
    fetchSchemes();
  };

  // Live Scheme API Discovery trigger
  const handleLiveFetch = async () => {
    setIsLiveFetching(true);
    setLiveFetchSuccess(false);

    try {
      const res = await fetch('/api/schemes/live-fetch', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          query: searchQuery.trim() || 'Latest agricultural subsidies, welfare schemes and financial grants',
          state: selectedState,
          apiKey: geminiApiKey || ''
        })
      });

      if (res.ok) {
        const data = await res.json();
        setSchemes(data);
        setLiveFetchSuccess(true);
        setTimeout(() => setLiveFetchSuccess(false), 5000);
      }
    } catch (err) {
      console.error('Live scheme fetch failed:', err);
    } finally {
      setIsLiveFetching(false);
    }
  };

  const toggleCardLanguage = (schemeId, currentEffectiveLang) => {
    setCardLangOverrides((prev) => ({
      ...prev,
      [schemeId]: currentEffectiveLang === 'hi' ? 'en' : 'hi'
    }));
  };

  // Client-side quick filter for instantaneous responsiveness as user types
  const filteredSchemes = schemes.filter((s) => {
    if (!searchQuery.trim()) return true;
    const query = searchQuery.toLowerCase();
    const nameMatch = (s.name && s.name.toLowerCase().includes(query)) ||
                      (s.nameHi && s.nameHi.toLowerCase().includes(query));
    const descMatch = (s.description && s.description.toLowerCase().includes(query)) ||
                      (s.descriptionHi && s.descriptionHi.toLowerCase().includes(query));
    const catMatch = s.category && s.category.toLowerCase().includes(query);
    const eligMatch = (s.eligibility && s.eligibility.toLowerCase().includes(query)) ||
                      (s.eligibilityHi && s.eligibilityHi.toLowerCase().includes(query));
    return nameMatch || descMatch || catMatch || eligMatch;
  });

  return (
    <div className="space-y-6 max-w-6xl mx-auto animate-fadeIn">
      {/* Page Header */}
      <div className="bg-white rounded-3xl p-6 sm:p-8 border border-[#E2D9CC] shadow-sm relative overflow-hidden">
        <div className="absolute inset-0 furrow-pattern pointer-events-none opacity-40" />
        <div className="relative">
          <div className="flex flex-wrap items-center justify-between gap-3 mb-2">
            <div className="inline-flex items-center space-x-1.5 px-3 py-1 rounded-full text-xs font-semibold bg-[#FDF2E9] text-[#C46A2B] border border-[#F6DCC7]">
              <Landmark size={14} />
              <span>
                {language === 'hi'
                  ? `सरकारी कृषि कल्याण सेतु (${schemes.length}+ योजनाएं)`
                  : `National & State Welfare Directory (${schemes.length}+ Active Schemes)`}
              </span>
            </div>

            {/* Quick Live API Fetch Button */}
            <button
              type="button"
              onClick={handleLiveFetch}
              disabled={isLiveFetching}
              className="inline-flex items-center space-x-1.5 px-3.5 py-1.5 rounded-xl bg-[#4C7A3D] hover:bg-[#3F6632] disabled:opacity-50 text-white text-xs font-bold shadow-sm transition-all transform active:scale-95 cursor-pointer"
              title="Query official portals and AI for latest 2024-2026 schemes"
            >
              {isLiveFetching ? (
                <>
                  <RefreshCw size={13} className="animate-spin" />
                  <span>{language === 'hi' ? 'लाइव पोर्टल खोज जारी...' : 'Live Fetching Schemes...'}</span>
                </>
              ) : (
                <>
                  <Zap size={13} className="text-yellow-300" />
                  <span>{language === 'hi' ? '⚡ नए लाइव योजनाएं खोजें' : '⚡ Live Scheme Discovery API'}</span>
                </>
              )}
            </button>
          </div>

          <h1 className="text-2xl sm:text-3xl font-bold text-[#6B4423] font-['Poppins']">
            {t('yojanaTitle')}
          </h1>
          <p className="text-xs sm:text-sm text-[#5C4533] mt-1 max-w-3xl">
            {language === 'hi'
              ? 'पीएम-किसान, सोलर पंप (कुसुम), किसान ड्रोन, फसल बीमा व राज्य टॉप-अप सहित भारत सरकार और सभी राज्यों की 65+ कल्याणकारी योजनाएं।'
              : 'Direct access to 65+ official welfare programs across all 36 Indian States & UTs — covering PM-KISAN, Solar Pumps (KUSUM), Drones, Crop Insurance, Dairy, and State Top-Up Subsidies.'}
          </p>

          {/* User state context badge */}
          {user?.state && (
            <div className="mt-2.5 inline-flex items-center space-x-1.5 text-xs text-[#4C7A3D] font-medium bg-[#EAF3E7]/80 px-2.5 py-1 rounded-lg border border-[#D1E6CC]">
              <CheckCircle2 size={13} />
              <span>
                {language === 'hi'
                  ? `आपकी प्रोफ़ाइल राज्य: ${user.state} (राज्य अनुसार योजनाएं सक्रिय हैं)`
                  : `Profile State: ${user.state} (Filtered for your region)`}
              </span>
            </div>
          )}

          {/* Live Fetch Success Notification */}
          {liveFetchSuccess && (
            <div className="mt-3 p-2.5 rounded-xl bg-[#EAF3E7] border border-[#D1E6CC] text-xs text-[#4C7A3D] font-bold flex items-center space-x-2 animate-fadeIn">
              <Sparkles size={14} />
              <span>
                {language === 'hi'
                  ? 'लाइव सरकारी डेटाबेस व एआई द्वारा योजना सूची को अद्यतन कर दिया गया है!'
                  : 'Successfully queried live welfare registries! Latest scheme details refreshed.'}
              </span>
            </div>
          )}

          {/* Filter and Search Bar */}
          <form onSubmit={handleSearchSubmit} className="mt-6 pt-4 border-t border-[#EFE8DC] grid grid-cols-1 md:grid-cols-3 gap-3">
            {/* State Selector */}
            <div className="flex items-center space-x-2 bg-[#F7F2E9] px-3.5 py-2.5 rounded-2xl border border-[#DECDBE]">
              <Filter size={16} className="text-[#6B4423] shrink-0" />
              <div className="flex-1">
                <span className="block text-[10px] font-bold uppercase text-[#8A7463]">
                  {t('yojanaFilterState')} ({ALL_INDIAN_STATES.length - 1} States/UTs)
                </span>
                <select
                  value={selectedState}
                  onChange={(e) => setSelectedState(e.target.value)}
                  className="bg-transparent text-sm font-semibold text-[#6B4423] focus:outline-none w-full cursor-pointer"
                >
                  {ALL_INDIAN_STATES.map((st) => (
                    <option key={st} value={st}>
                      {st === 'All India' ? (language === 'hi' ? '🇮🇳 संपूर्ण भारत (All India)' : '🇮🇳 All India (National)') : st}
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
                placeholder={
                  language === 'hi'
                    ? 'योजना या उपकरण खोजें (उदा. ड्रोन, सोलर पंप, जैविक, ट्रैक्टर, केसीसी, डेयरी)...'
                    : 'Search schemes by keyword (e.g. drone, solar pump, subsidy, KCC, organic, dairy, tractor)...'
                }
                className="bg-transparent text-sm text-[#2C1E14] placeholder-[#8A7463] focus:outline-none w-full"
              />
              <button
                type="submit"
                className="px-3 py-1.5 rounded-xl bg-[#4C7A3D] text-white text-xs font-bold hover:bg-[#3F6632] transition-colors shrink-0"
              >
                {language === 'hi' ? 'खोजें' : 'Search'}
              </button>
            </div>
          </form>

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
          {filteredSchemes.map((scheme) => {
            const effectiveLang = cardLangOverrides[scheme.id] || language;
            const isHi = effectiveLang === 'hi';

            const displayTitle = isHi && scheme.nameHi ? scheme.nameHi : scheme.name;
            const displayDesc = isHi && scheme.descriptionHi ? scheme.descriptionHi : scheme.description;
            const displayBenefits = isHi && scheme.benefitsHi ? scheme.benefitsHi : scheme.benefits;
            const displayEligibility = isHi && scheme.eligibilityHi ? scheme.eligibilityHi : scheme.eligibility;

            return (
              <div
                key={scheme.id}
                className="bg-white rounded-3xl p-6 border border-[#E2D9CC] shadow-sm flex flex-col justify-between hover:shadow-md hover:border-[#DECDBE] transition-all duration-300 relative"
              >
                <div>
                  {/* Header Category & State Tag */}
                  <div className="flex items-center justify-between gap-2 pb-3 border-b border-[#EFE8DC]">
                    <span className="text-[10px] font-bold uppercase tracking-wider px-2.5 py-0.5 rounded-full bg-[#EAF3E7] text-[#4C7A3D] border border-[#D1E6CC]">
                      {scheme.category}
                    </span>

                    <div className="flex items-center space-x-1">
                      {scheme.liveFetched && (
                        <span className="text-[9px] font-bold px-1.5 py-0.5 rounded-md bg-yellow-100 text-yellow-800 border border-yellow-300 flex items-center gap-0.5">
                          <Zap size={9} />
                          Live API
                        </span>
                      )}
                      <span className="text-[10px] font-semibold text-[#8A7463] px-2 py-0.5 rounded-md bg-[#F7F2E9]">
                        {scheme.isNational
                          ? (isHi ? '🇮🇳 राष्ट्रीय योजना' : '🇮🇳 National')
                          : `🚩 ${scheme.applicableStates ? scheme.applicableStates[0] : 'State'}`}
                      </span>
                    </div>
                  </div>

                  {/* Scheme Title */}
                  <h3 className="text-base font-bold text-[#6B4423] font-['Poppins'] mt-3 leading-snug">
                    {displayTitle}
                  </h3>

                  {/* One-line Description */}
                  <p className="text-xs text-[#5C4533] mt-2 leading-relaxed">
                    {displayDesc}
                  </p>

                  {/* Card Inline Translation Switcher */}
                  <div className="mt-2.5">
                    <button
                      type="button"
                      onClick={() => toggleCardLanguage(scheme.id, effectiveLang)}
                      className="inline-flex items-center space-x-1 text-[10px] px-2 py-0.5 rounded-lg bg-[#F7F2E9] border border-[#DECDBE] text-[#6B4423] hover:text-[#4C7A3D] hover:border-[#4C7A3D] font-semibold transition-colors"
                      title={isHi ? 'View in English' : 'हिंदी अनुवाद देखें'}
                    >
                      <Languages size={11} className="text-[#4C7A3D]" />
                      <span>{isHi ? 'View English' : 'हिंदी अनुवाद'}</span>
                    </button>
                  </div>

                  {/* Key Benefits Callout */}
                  <div className="mt-3.5 p-3 rounded-2xl bg-[#FDF2E9] border border-[#F6DCC7] space-y-1">
                    <span className="text-[10px] font-bold uppercase tracking-wider text-[#C46A2B] block">
                      🎁 {t('yojanaBenefits')}:
                    </span>
                    <p className="text-xs font-semibold text-[#7C3E14] leading-snug">
                      {displayBenefits}
                    </p>
                  </div>

                  {/* Eligibility Note */}
                  <div className="mt-3 p-2.5 rounded-xl bg-[#F7F2E9] border border-[#DECDBE] text-[11px] text-[#5C4533] space-y-0.5">
                    <span className="font-bold text-[#6B4423] block">
                      ✓ {t('yojanaEligibility')}:
                    </span>
                    <p className="leading-snug">
                      {displayEligibility}
                    </p>
                  </div>
                </div>

                {/* Official Govt Link Button */}
                <div className="mt-6 pt-3 border-t border-[#EFE8DC] flex items-center gap-2">
                  <a
                    href={scheme.officialUrl}
                    target="_blank"
                    rel="noopener noreferrer"
                    className="flex-1 flex items-center justify-center space-x-2 py-2.5 px-4 rounded-xl bg-[#4C7A3D] hover:bg-[#3F6632] text-white text-xs font-bold shadow-sm transition-all transform active:scale-95"
                  >
                    <span>{t('yojanaApplyBtn')}</span>
                    <ExternalLink size={13} />
                  </a>

                  <a
                    href="https://www.myscheme.gov.in/schemes"
                    target="_blank"
                    rel="noopener noreferrer"
                    className="p-2.5 rounded-xl bg-[#F7F2E9] border border-[#DECDBE] text-[#6B4423] hover:text-[#4C7A3D] text-xs font-semibold transition-colors"
                    title="Open on myScheme.gov.in"
                  >
                    <FileText size={15} />
                  </a>
                </div>
              </div>
            );
          })}
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
          <p className="text-xs text-[#8A7463] max-w-md mx-auto">
            {language === 'hi'
              ? 'आप लाइव खोज बटन का उपयोग करके सरकारी पोर्टल और एआई से नई योजनाएं खोज सकते हैं।'
              : 'Try searching across all states or use the Live Discovery API button above to query official portals.'}
          </p>
          <div className="flex items-center justify-center space-x-2 pt-2">
            <button
              type="button"
              onClick={handleLiveFetch}
              disabled={isLiveFetching}
              className="px-4 py-2 rounded-xl bg-[#4C7A3D] text-white text-xs font-bold hover:bg-[#3F6632] flex items-center space-x-1.5"
            >
              <Zap size={14} className="text-yellow-300" />
              <span>{language === 'hi' ? '⚡ लाइव खोज चलाएं' : '⚡ Search Live with AI'}</span>
            </button>
            <button
              type="button"
              onClick={() => {
                setSearchQuery('');
                setSelectedCategory('All Schemes');
                setSelectedState('All India');
              }}
              className="px-4 py-2 rounded-xl bg-[#F7F2E9] border border-[#DECDBE] text-[#6B4423] text-xs font-bold"
            >
              {language === 'hi' ? 'फ़िल्टर हटाएं' : 'Reset Filters'}
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
