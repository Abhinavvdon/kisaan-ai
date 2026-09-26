import React, { useState, useEffect } from 'react';
import { useLanguage } from '../context/LanguageContext';
import { useAuth } from '../context/AuthContext';
import {
  CloudSun,
  Droplets,
  Wind,
  CloudRain,
  Sprout,
  AlertTriangle,
  ShieldAlert,
  Thermometer,
  Calendar,
  Compass,
  ChevronRight,
  Info,
  RefreshCw,
  MapPin,
  Navigation,
  Search,
  Sparkles,
  CheckCircle,
  UserCheck
} from 'lucide-react';
import Scanner from './Scanner';
import { ALL_INDIAN_DISTRICTS } from '../data/allDistricts';

export default function Dashboard() {
  const { t, language } = useLanguage();
  const { user } = useAuth();

  const [selectedLocation, setSelectedLocation] = useState(() => {
    try {
      const saved = localStorage.getItem('kisaan_selected_location');
      if (saved) return JSON.parse(saved);
    } catch (e) {
      console.warn('Failed to parse saved location', e);
    }
    if (user && user.district) {
      const match = ALL_INDIAN_DISTRICTS.find((l) => l.name.toLowerCase() === user.district.toLowerCase());
      if (match) return match;
    }
    // Default to Nashik, Maharashtra or first district alphabetically
    return ALL_INDIAN_DISTRICTS.find((l) => l.name === 'Nashik') || ALL_INDIAN_DISTRICTS[0];
  });

  const [districtSearch, setDistrictSearch] = useState('');

  const filteredDistricts = React.useMemo(() => {
    if (!districtSearch.trim()) return ALL_INDIAN_DISTRICTS;
    const q = districtSearch.toLowerCase().trim();
    return ALL_INDIAN_DISTRICTS.filter((d) =>
      d.name.toLowerCase().includes(q) || d.state.toLowerCase().includes(q)
    );
  }, [districtSearch]);

  const [dashboardData, setDashboardData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [gpsDetecting, setGpsDetecting] = useState(false);
  const [gpsStatus, setGpsStatus] = useState(null);
  const [error, setError] = useState(null);

  const fetchDashboard = () => {
    setLoading(true);
    fetch(`/api/dashboard?lat=${selectedLocation.lat}&lon=${selectedLocation.lon}&district=${encodeURIComponent(selectedLocation.name)}&state=${encodeURIComponent(selectedLocation.state)}`)
      .then((res) => {
        if (!res.ok) throw new Error('HTTP ' + res.status);
        return res.json();
      })
      .then((data) => {
        setDashboardData(data);
        setError(null);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchDashboard();
  }, [selectedLocation]);

  // Real-time Browser GPS Geolocation Detector
  const handleDetectLocation = () => {
    if (!navigator.geolocation) {
      alert('Geolocation is not supported by your browser.');
      return;
    }

    setGpsDetecting(true);
    setGpsStatus('Acquiring high-accuracy GPS coordinates...');

    navigator.geolocation.getCurrentPosition(
      (position) => {
        const lat = position.coords.latitude;
        const lon = position.coords.longitude;

        // Find closest Indian district in our 786-district dataset
        let closest = ALL_INDIAN_DISTRICTS[0];
        let minDistance = 999999;

        ALL_INDIAN_DISTRICTS.forEach((loc) => {
          const d = Math.sqrt(Math.pow(loc.lat - lat, 2) + Math.pow(loc.lon - lon, 2));
          if (d < minDistance) {
            minDistance = d;
            closest = loc;
          }
        });

        const newLoc = {
          name: closest.name,
          state: closest.state,
          lat: Math.round(lat * 10000) / 10000,
          lon: Math.round(lon * 10000) / 10000,
          isGpsLive: true
        };

        setSelectedLocation(newLoc);
        try {
          localStorage.setItem('kisaan_selected_location', JSON.stringify(newLoc));
        } catch (e) {}
        setGpsDetecting(false);
        setGpsStatus(`GPS Active: ${newLoc.name} (${lat.toFixed(3)}°N, ${lon.toFixed(3)}°E)`);
        setTimeout(() => setGpsStatus(null), 4000);
      },
      (err) => {
        console.warn('GPS location request denied or timed out:', err);
        setGpsDetecting(false);
        setGpsStatus('GPS Permission Denied. Defaulting to state coordinates.');
        setTimeout(() => setGpsStatus(null), 3500);
      },
      { timeout: 10000, enableHighAccuracy: true }
    );
  };

  return (
    <div className="space-y-6 animate-fadeIn">
      {/* Logged-in Farmer Welcome Banner */}
      {user && (
        <div className="p-4 rounded-2xl bg-[#EAF3E7] border border-[#D1E6CC] flex flex-col sm:flex-row sm:items-center sm:justify-between gap-3 shadow-sm">
          <div className="flex items-center space-x-3">
            <div className="w-10 h-10 rounded-2xl bg-[#4C7A3D] text-white flex items-center justify-center font-bold text-base shadow-inner">
              {user.fullName.charAt(0)}
            </div>
            <div>
              <div className="flex items-center space-x-2">
                <span className="font-bold text-sm text-[#2E4C24]">{user.fullName}</span>
                <span className="text-[10px] font-bold px-2 py-0.5 rounded-full bg-white text-[#4C7A3D] border border-[#D1E6CC]">
                  {user.district}, {user.state}
                </span>
              </div>
              <p className="text-xs text-[#557849] mt-0.5">
                Farm Holding: {user.landSizeAcres || '4.5'} Acres &bull; Crops: {user.primaryCrops || 'Vegetables'}
              </p>
            </div>
          </div>
          <div className="flex items-center space-x-2 self-start sm:self-auto text-xs text-[#4C7A3D] font-semibold">
            <CheckCircle size={15} />
            <span>Profile Synced</span>
          </div>
        </div>
      )}

      {/* Dashboard Top Header with Real-Time Location Detector & Manual Selector */}
      <div className="bg-white rounded-3xl p-5 sm:p-7 border border-[#E2D9CC] shadow-sm relative overflow-hidden">
        <div className="absolute inset-0 furrow-pattern pointer-events-none opacity-40" />

        <div className="relative flex flex-col lg:flex-row lg:items-center lg:justify-between gap-4">
          <div>
            <div className="flex items-center space-x-2">
              <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-semibold bg-[#EAF3E7] text-[#4C7A3D] border border-[#D1E6CC]">
                🌱 {language === 'hi' ? 'सक्रिय कृषि टेलीमेट्री' : 'Active Farm Telemetry'}
              </span>
              {selectedLocation.isGpsLive && (
                <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-[10px] font-bold bg-[#EBF3F7] text-[#4F778B] border border-[#D5E5ED] animate-pulse">
                  📍 GPS Live
                </span>
              )}
            </div>
            <h1 className="text-2xl sm:text-3xl font-bold text-[#6B4423] font-['Poppins'] mt-1.5">
              {t('dashTitle')}
            </h1>
            <p className="text-xs sm:text-sm text-[#5C4533] mt-0.5">
              {t('dashSubtitle')}
            </p>
          </div>

          {/* Location Controls: 1) GPS Detect Button, 2) Searchable Alphabetical District Picker */}
          <div className="flex flex-col sm:flex-row items-stretch sm:items-center gap-2.5 self-start lg:self-auto">
            {/* GPS Detect Button */}
            <button
              type="button"
              onClick={handleDetectLocation}
              disabled={gpsDetecting}
              className="inline-flex items-center justify-center space-x-1.5 px-3.5 py-2.5 rounded-2xl bg-[#4C7A3D] hover:bg-[#3F6632] text-white text-xs font-bold shadow-sm transition-all transform active:scale-95 disabled:opacity-60 shrink-0"
            >
              <Navigation size={14} className={gpsDetecting ? 'animate-spin' : ''} />
              <span>{gpsDetecting ? 'Locating...' : language === 'hi' ? '📍 मेरी लोकेशन' : '📍 Detect GPS'}</span>
            </button>

            {/* Searchable Alphabetical District Picker (786 Districts A-Z) */}
            <div className="flex items-center space-x-2 bg-[#F7F2E9] px-3 py-1.5 rounded-2xl border border-[#DECDBE]">
              <MapPin size={17} className="text-[#C46A2B] shrink-0" />
              <div className="flex flex-col">
                <div className="flex items-center justify-between space-x-2">
                  <span className="text-[9px] uppercase font-bold text-[#8A7463] tracking-wider">
                    {language === 'hi' ? `भारत के सभी जिले (${ALL_INDIAN_DISTRICTS.length})` : `All Indian Districts (${ALL_INDIAN_DISTRICTS.length})`}
                  </span>
                  <span className="text-[8px] font-bold text-[#4C7A3D] bg-white px-1.5 py-0.5 rounded border border-[#DECDBE]">
                    A &rarr; Z
                  </span>
                </div>

                <div className="flex items-center space-x-1.5 mt-1">
                  <div className="relative flex items-center">
                    <Search size={11} className="absolute left-1.5 text-[#8A7463] pointer-events-none" />
                    <input
                      type="text"
                      value={districtSearch}
                      onChange={(e) => setDistrictSearch(e.target.value)}
                      placeholder={language === 'hi' ? 'खोजें...' : 'Filter...'}
                      className="pl-5 pr-1.5 py-1 w-20 sm:w-24 text-[11px] font-medium bg-white rounded-lg border border-[#DECDBE] text-[#6B4423] focus:outline-none focus:border-[#4C7A3D]"
                    />
                  </div>

                  <select
                    value={selectedLocation.name}
                    onChange={(e) => {
                      const loc = ALL_INDIAN_DISTRICTS.find((d) => d.name === e.target.value);
                      if (loc) {
                        setSelectedLocation(loc);
                        try {
                          localStorage.setItem('kisaan_selected_location', JSON.stringify(loc));
                        } catch (err) {}
                      }
                    }}
                    className="bg-white px-2 py-1 rounded-lg border border-[#DECDBE] text-xs font-bold text-[#6B4423] focus:outline-none focus:border-[#4C7A3D] cursor-pointer max-w-[180px] sm:max-w-[210px]"
                  >
                    {filteredDistricts.length === 0 ? (
                      <option disabled>No district found</option>
                    ) : (
                      filteredDistricts.map((loc) => (
                        <option key={`${loc.name}-${loc.state}`} value={loc.name}>
                          {loc.name}, {loc.state}
                        </option>
                      ))
                    )}
                  </select>
                </div>
              </div>

              <button
                type="button"
                onClick={fetchDashboard}
                title="Refresh Telemetry"
                className="p-1.5 text-[#6B4423] hover:text-[#4C7A3D] rounded-lg transition-colors ml-0.5"
              >
                <RefreshCw size={14} className={loading ? 'animate-spin' : ''} />
              </button>
            </div>
          </div>
        </div>

        {/* GPS Status feedback toast */}
        {gpsStatus && (
          <div className="mt-3 text-xs px-3 py-1.5 rounded-xl bg-[#EAF3E7] text-[#2E4C24] font-medium border border-[#D1E6CC] inline-flex items-center space-x-1 animate-fadeIn">
            <CheckCircle size={14} className="text-[#4C7A3D]" />
            <span>{gpsStatus}</span>
          </div>
        )}
      </div>

      {/* Loading & Error States */}
      {loading && !dashboardData && (
        <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
          {[1, 2, 3].map((i) => (
            <div key={i} className="bg-white rounded-3xl p-6 border border-[#E2D9CC] h-64 animate-pulse" />
          ))}
        </div>
      )}

      {error && (
        <div className="p-4 rounded-2xl bg-red-50 border border-red-200 text-red-700 text-sm flex items-center space-x-2">
          <AlertTriangle size={18} />
          <span>Error loading telemetry: {error}</span>
        </div>
      )}

      {/* THREE WIDGET CARDS RESPONSIVE GRID */}
      {dashboardData && (
        <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
          {/* 1. WEATHER WIDGET CARD */}
          <div className="bg-white rounded-3xl p-6 border border-[#E2D9CC] shadow-sm flex flex-col justify-between hover:shadow-md transition-shadow">
            <div>
              <div className="flex items-center justify-between pb-3 border-b border-[#EFE8DC]">
                <div className="flex items-center space-x-2.5">
                  <div className="w-10 h-10 rounded-2xl bg-[#EBF3F7] text-[#6E97AC] flex items-center justify-center shadow-inner">
                    <CloudSun size={22} />
                  </div>
                  <div>
                    <h2 className="text-base font-bold text-[#6B4423] font-['Poppins']">
                      {t('weatherWidgetTitle')}
                    </h2>
                    <span className="text-[11px] text-[#8A7463]">
                      {dashboardData.weather.condition}
                    </span>
                  </div>
                </div>
                <span className="text-xs px-2.5 py-1 rounded-full bg-[#EBF3F7] text-[#4F778B] font-semibold border border-[#D5E5ED]">
                  {dashboardData.weather.forecastBrief ? 'Live' : 'Cached'}
                </span>
              </div>

              {/* Main Temp & Condition */}
              <div className="mt-4 flex items-baseline justify-between">
                <div>
                  <span className="text-4xl font-extrabold text-[#2C1E14] tracking-tight">
                    {Math.round(dashboardData.weather.temperature)}°C
                  </span>
                  <span className="text-xs text-[#8A7463] ml-2">
                    {language === 'hi' ? 'महसूस होता है' : 'Feels'} {Math.round(dashboardData.weather.feelsLike)}°C
                  </span>
                </div>
                <div className="text-right">
                  <span className="text-sm font-semibold text-[#6E97AC]">
                    {dashboardData.weather.condition}
                  </span>
                </div>
              </div>

              {/* Weather Telemetry Matrix */}
              <div className="grid grid-cols-3 gap-2 mt-4 pt-4 border-t border-[#EFE8DC]">
                <div className="bg-[#F7F2E9] p-2.5 rounded-2xl text-center">
                  <Droplets size={16} className="mx-auto text-[#6E97AC] mb-1" />
                  <span className="block text-[10px] text-[#8A7463] uppercase font-semibold">
                    {t('weatherHumidity')}
                  </span>
                  <span className="text-sm font-bold text-[#2C1E14]">
                    {dashboardData.weather.humidity}%
                  </span>
                </div>

                <div className="bg-[#F7F2E9] p-2.5 rounded-2xl text-center">
                  <Wind size={16} className="mx-auto text-[#6E97AC] mb-1" />
                  <span className="block text-[10px] text-[#8A7463] uppercase font-semibold">
                    {t('weatherWind')}
                  </span>
                  <span className="text-sm font-bold text-[#2C1E14]">
                    {dashboardData.weather.windSpeed} <span className="text-[10px]">km/h</span>
                  </span>
                </div>

                <div className="bg-[#F7F2E9] p-2.5 rounded-2xl text-center">
                  <CloudRain size={16} className="mx-auto text-[#6E97AC] mb-1" />
                  <span className="block text-[10px] text-[#8A7463] uppercase font-semibold">
                    {t('weatherRainRisk')}
                  </span>
                  <span className="text-sm font-bold text-[#2C1E14]">
                    {dashboardData.weather.rainChance}%
                  </span>
                </div>
              </div>
            </div>

            {/* 48-Hour Short Forecast */}
            <div className="mt-5 pt-3 border-t border-[#EFE8DC]">
              <span className="text-[11px] font-bold text-[#6B4423] uppercase tracking-wide flex items-center gap-1 mb-2">
                <Calendar size={13} />
                {t('weatherForecast')}
              </span>
              <div className="grid grid-cols-2 gap-2">
                {dashboardData.weather.forecast && dashboardData.weather.forecast.map((day, idx) => (
                  <div key={idx} className="bg-[#EBF3F7]/50 p-2.5 rounded-2xl text-xs flex justify-between items-center border border-[#D5E5ED]">
                    <div>
                      <p className="font-semibold text-[#2C1E14]">
                        {language === 'hi' ? (idx === 0 ? 'कल' : 'परसों') : day.day}
                      </p>
                      <p className="text-[10px] text-[#6E97AC]">{day.condition}</p>
                    </div>
                    <div className="text-right">
                      <span className="font-bold text-[#2C1E14]">{Math.round(day.tempMax)}°</span>
                      <span className="text-[10px] text-[#8A7463] block">/{Math.round(day.tempMin)}°</span>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          </div>

          {/* 2. SOIL MOISTURE WIDGET CARD */}
          <div className="bg-white rounded-3xl p-6 border border-[#E2D9CC] shadow-sm flex flex-col justify-between hover:shadow-md transition-shadow">
            <div>
              <div className="flex items-center justify-between pb-3 border-b border-[#EFE8DC]">
                <div className="flex items-center space-x-2.5">
                  <div className="w-10 h-10 rounded-2xl bg-[#EAF3E7] text-[#4C7A3D] flex items-center justify-center shadow-inner">
                    <Sprout size={22} />
                  </div>
                  <div>
                    <h2 className="text-base font-bold text-[#6B4423] font-['Poppins']">
                      {t('soilWidgetTitle')}
                    </h2>
                    <span className="text-[11px] text-[#8A7463]">
                      {t('soilDepth')}
                    </span>
                  </div>
                </div>

                <span
                  className={`text-xs px-2.5 py-1 rounded-full font-semibold border ${
                    dashboardData.soilMoisture.status === 'OPTIMAL'
                      ? 'bg-[#EAF3E7] text-[#4C7A3D] border-[#D1E6CC]'
                      : dashboardData.soilMoisture.status === 'LOW'
                      ? 'bg-[#FDF2E9] text-[#C46A2B] border-[#F6DCC7]'
                      : 'bg-blue-50 text-blue-700 border-blue-200'
                  }`}
                >
                  {dashboardData.soilMoisture.status === 'OPTIMAL'
                    ? t('soilStatusOptimal')
                    : dashboardData.soilMoisture.status === 'LOW'
                    ? t('soilStatusLow')
                    : t('soilStatusHigh')}
                </span>
              </div>

              {/* Moisture Gauge Display */}
              <div className="mt-4">
                <div className="flex items-baseline justify-between">
                  <span className="text-4xl font-extrabold text-[#2C1E14] tracking-tight">
                    {dashboardData.soilMoisture.moisturePercentage}%
                  </span>
                  <span className="text-xs font-medium text-[#4C7A3D]">
                    {dashboardData.soilMoisture.status === 'OPTIMAL' ? '🌿 ' + (language === 'hi' ? 'स्वस्थ जड़ वातावरण' : 'Optimal Root Band') : ''}
                  </span>
                </div>

                {/* Visual Moisture Bar */}
                <div className="w-full bg-[#EFE8DC] h-3.5 rounded-full mt-3 overflow-hidden p-0.5">
                  <div
                    className={`h-full rounded-full transition-all duration-700 ${
                      dashboardData.soilMoisture.status === 'OPTIMAL'
                        ? 'bg-gradient-to-r from-[#649953] to-[#4C7A3D]'
                        : dashboardData.soilMoisture.status === 'LOW'
                        ? 'bg-gradient-to-r from-[#DFA16E] to-[#C46A2B]'
                        : 'bg-gradient-to-r from-[#6E97AC] to-[#4F778B]'
                    }`}
                    style={{ width: `${Math.min(100, Math.max(10, dashboardData.soilMoisture.moisturePercentage * 2.5))}%` }}
                  />
                </div>
                <div className="flex justify-between text-[10px] text-[#8A7463] mt-1 font-medium">
                  <span>Dry (&lt;20%)</span>
                  <span>Optimal (23-30%)</span>
                  <span>Saturated (&gt;32%)</span>
                </div>
              </div>

              {/* Extra Soil Metrics */}
              <div className="grid grid-cols-2 gap-2 mt-4 pt-4 border-t border-[#EFE8DC]">
                <div className="bg-[#F7F2E9] p-2.5 rounded-2xl flex items-center space-x-2">
                  <Thermometer size={16} className="text-[#6B4423]" />
                  <div>
                    <span className="block text-[10px] text-[#8A7463] uppercase font-semibold">
                      {language === 'hi' ? 'मृदा तापमान' : 'Soil Temp'}
                    </span>
                    <span className="text-xs font-bold text-[#2C1E14]">
                      {dashboardData.soilMoisture.soilTemperature}°C
                    </span>
                  </div>
                </div>

                <div className="bg-[#F7F2E9] p-2.5 rounded-2xl flex items-center space-x-2">
                  <Compass size={16} className="text-[#6B4423]" />
                  <div>
                    <span className="block text-[10px] text-[#8A7463] uppercase font-semibold">
                      {language === 'hi' ? 'विद्युत चालकता' : 'Soil EC'}
                    </span>
                    <span className="text-xs font-bold text-[#2C1E14]">
                      {dashboardData.soilMoisture.electricalConductivity} <span className="text-[9px]">dS/m</span>
                    </span>
                  </div>
                </div>
              </div>
            </div>

            {/* Agronomic Soil Advisory */}
            <div className="mt-5 p-3.5 rounded-2xl bg-[#EAF3E7]/60 border border-[#D1E6CC] text-xs text-[#2E4C24]">
              <div className="flex items-start space-x-1.5">
                <Info size={14} className="text-[#4C7A3D] shrink-0 mt-0.5" />
                <p className="leading-relaxed">
                  {language === 'hi' && dashboardData.soilMoisture.advisoryHi && !dashboardData.soilMoisture.advisoryHi.includes('?')
                    ? dashboardData.soilMoisture.advisoryHi
                    : dashboardData.soilMoisture.advisoryEn}
                </p>
              </div>
            </div>
          </div>

          {/* 3. ACTIVE CROP ALERTS WIDGET CARD */}
          <div className="bg-white rounded-3xl p-6 border border-[#E2D9CC] shadow-sm flex flex-col justify-between hover:shadow-md transition-shadow">
            <div>
              <div className="flex items-center justify-between pb-3 border-b border-[#EFE8DC]">
                <div className="flex items-center space-x-2.5">
                  <div className="w-10 h-10 rounded-2xl bg-[#FDF2E9] text-[#C46A2B] flex items-center justify-center shadow-inner">
                    <ShieldAlert size={22} />
                  </div>
                  <div>
                    <h2 className="text-base font-bold text-[#6B4423] font-['Poppins']">
                      {t('alertsWidgetTitle')}
                    </h2>
                    <span className="text-[11px] text-[#8A7463]">
                      {dashboardData.district} ({dashboardData.activeAlerts.length} {language === 'hi' ? 'सक्रिय' : 'Active'})
                    </span>
                  </div>
                </div>

                <span className="text-xs px-2.5 py-1 rounded-full bg-[#FDF2E9] text-[#C46A2B] font-semibold border border-[#F6DCC7]">
                  {language === 'hi' ? 'जिला चेतावनी' : 'District Alert'}
                </span>
              </div>

              {/* Alert Items List */}
              <div className="mt-4 space-y-3">
                {dashboardData.activeAlerts && dashboardData.activeAlerts.length > 0 ? (
                  dashboardData.activeAlerts.map((alert) => (
                    <div
                      key={alert.id}
                      className="p-3.5 rounded-2xl border border-[#DECDBE] bg-[#F7F2E9]/70 space-y-2 hover:bg-[#F7F2E9] transition-colors"
                    >
                      <div className="flex items-center justify-between">
                        <span className="font-bold text-xs text-[#6B4423]">
                          {alert.crop}
                        </span>
                        <span
                          className={`text-[10px] px-2 py-0.5 rounded-full font-bold uppercase tracking-wider ${
                            alert.severity === 'HIGH'
                              ? 'bg-[#C46A2B] text-white'
                              : 'bg-[#DFA16E] text-white'
                          }`}
                        >
                          {alert.severity === 'HIGH' ? t('alertSeverityHigh') : t('alertSeverityMedium')}
                        </span>
                      </div>

                      <p className="text-xs font-semibold text-[#2C1E14]">
                        {alert.pestOrDisease}
                      </p>

                      <p className="text-[11px] text-[#5C4533] leading-relaxed">
                        {language === 'hi' && alert.messageHi && !alert.messageHi.includes('?')
                          ? alert.messageHi
                          : alert.messageEn}
                      </p>

                      <div className="pt-2 border-t border-[#DECDBE]/60 text-[11px] text-[#4C7A3D] font-medium flex items-start space-x-1">
                        <span className="font-bold shrink-0">{language === 'hi' ? 'उपचार:' : 'Action:'}</span>
                        <span>
                          {language === 'hi' && alert.preventativeActionHi && !alert.preventativeActionHi.includes('?')
                            ? alert.preventativeActionHi
                            : alert.preventativeActionEn}
                        </span>
                      </div>
                    </div>
                  ))
                ) : (
                  <p className="text-xs text-[#8A7463] py-6 text-center">
                    {t('noAlerts')}
                  </p>
                )}
              </div>
            </div>

            {/* Quick Link to Scanner */}
            <div className="mt-4 pt-3 border-t border-[#EFE8DC]">
              <a
                href="#crop-scanner-section"
                className="w-full flex items-center justify-center space-x-1.5 py-2.5 rounded-xl bg-[#4C7A3D] hover:bg-[#3F6632] text-white text-xs font-semibold shadow-sm transition-colors"
              >
                <span>{language === 'hi' ? 'फसल पत्ती की फोटो नीचे स्कैन करें' : 'Scroll Down to AI Scanner'}</span>
                <ChevronRight size={14} />
              </a>
            </div>
          </div>
        </div>
      )}

      {/* Scanner Embedded below Dashboard on Scroll */}
      <div id="crop-scanner-section" className="pt-6 border-t-2 border-dashed border-[#DECDBE]">
        <Scanner />
      </div>
    </div>
  );
}
