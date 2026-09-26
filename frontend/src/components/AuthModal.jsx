import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { useLanguage } from '../context/LanguageContext';
import { X, LogIn, UserPlus, Sparkles, Check, AlertCircle } from 'lucide-react';

export default function AuthModal() {
  const { isAuthModalOpen, setIsAuthModalOpen, login, register } = useAuth();
  const { language } = useLanguage();
  const [tab, setTab] = useState('login'); // 'login' or 'register'

  // Login state
  const [loginId, setLoginId] = useState('');
  const [password, setPassword] = useState('');

  // Register state
  const [regFullName, setRegFullName] = useState('');
  const [regEmail, setRegEmail] = useState('');
  const [regPhone, setRegPhone] = useState('');
  const [regPassword, setRegPassword] = useState('');
  const [regDistrict, setRegDistrict] = useState('Nashik');
  const [regState, setRegState] = useState('Maharashtra');
  const [regLandSize, setRegLandSize] = useState('3.5');
  const [regCrops, setRegCrops] = useState('Onion, Tomato');

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  if (!isAuthModalOpen) return null;

  const handleLogin = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    try {
      await login(loginId, password);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    try {
      await register({
        fullName: regFullName,
        email: regEmail,
        phoneNumber: regPhone,
        password: regPassword,
        district: regDistrict,
        state: regState,
        landSizeAcres: parseFloat(regLandSize) || 2.0,
        primaryCrops: regCrops
      });
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const quickDemoLogin = (email, pass) => {
    setLoginId(email);
    setPassword(pass);
    login(email, pass).catch((err) => setError(err.message));
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-[#2C1E14]/70 backdrop-blur-sm p-4 animate-fadeIn">
      <div className="bg-[#F7F2E9] border border-[#DECDBE] rounded-3xl max-w-md w-full p-6 shadow-2xl relative max-h-[90vh] overflow-y-auto">
        {/* Close Button */}
        <button
          type="button"
          onClick={() => setIsAuthModalOpen(false)}
          className="absolute top-4 right-4 p-2 text-[#8A7463] hover:text-[#2C1E14] rounded-full hover:bg-[#EFE8DC] transition-colors"
        >
          <X size={18} />
        </button>

        {/* Modal Header */}
        <div className="text-center pt-2">
          <div className="w-12 h-12 rounded-2xl bg-[#4C7A3D] text-white flex items-center justify-center mx-auto text-xl shadow-md">
            🌱
          </div>
          <h2 className="text-xl font-bold text-[#6B4423] font-['Poppins'] mt-2">
            {language === 'hi' ? 'किसान खाता व प्रोफाइल' : 'Farmer Account Portal'}
          </h2>
          <p className="text-xs text-[#5C4533] mt-0.5">
            {language === 'hi'
              ? 'खेत की निगरानी व रोग जांच इतिहास सुरक्षित रखें'
              : 'Save farm telemetry, soil metrics & crop scan history'}
          </p>
        </div>

        {/* 1-Click Demo Account Shortcuts for Hackathon Judges */}
        <div className="mt-4 p-3 rounded-2xl bg-[#EAF3E7]/80 border border-[#D1E6CC]">
          <span className="text-[10px] font-bold uppercase tracking-wider text-[#2E4C24] block mb-1.5 flex items-center gap-1">
            <Sparkles size={12} className="text-[#4C7A3D]" />
            {language === 'hi' ? 'हैकथॉन डेमो तुरंत लॉगिन:' : 'Hackathon Quick Demo Logins:'}
          </span>
          <div className="grid grid-cols-2 gap-2">
            <button
              type="button"
              onClick={() => quickDemoLogin('ramesh@kisaan.ai', 'kisaan123')}
              className="text-left p-2 rounded-xl bg-white hover:bg-[#EAF3E7] border border-[#D1E6CC] text-xs transition-colors"
            >
              <span className="font-bold text-[#2C1E14] block">Ramesh Patil</span>
              <span className="text-[10px] text-[#4C7A3D]">Nashik (4.5 Acres)</span>
            </button>
            <button
              type="button"
              onClick={() => quickDemoLogin('harpreet@kisaan.ai', 'kisaan123')}
              className="text-left p-2 rounded-xl bg-white hover:bg-[#EAF3E7] border border-[#D1E6CC] text-xs transition-colors"
            >
              <span className="font-bold text-[#2C1E14] block">Harpreet Singh</span>
              <span className="text-[10px] text-[#4C7A3D]">Ludhiana (8.0 Acres)</span>
            </button>
          </div>
        </div>

        {/* Tab Switcher */}
        <div className="flex bg-[#EFE8DC] p-1 rounded-2xl mt-4">
          <button
            type="button"
            onClick={() => setTab('login')}
            className={`flex-1 py-2 text-xs font-bold rounded-xl transition-all ${
              tab === 'login'
                ? 'bg-white text-[#6B4423] shadow-sm'
                : 'text-[#8A7463] hover:text-[#2C1E14]'
            }`}
          >
            {language === 'hi' ? 'लॉगिन करें' : 'Sign In'}
          </button>
          <button
            type="button"
            onClick={() => setTab('register')}
            className={`flex-1 py-2 text-xs font-bold rounded-xl transition-all ${
              tab === 'register'
                ? 'bg-white text-[#6B4423] shadow-sm'
                : 'text-[#8A7463] hover:text-[#2C1E14]'
            }`}
          >
            {language === 'hi' ? 'नया खाता बनाएं' : 'Create Account'}
          </button>
        </div>

        {/* Error message */}
        {error && (
          <div className="mt-3 p-3 rounded-xl bg-red-50 border border-red-200 text-red-700 text-xs flex items-center space-x-1.5">
            <AlertCircle size={15} className="shrink-0" />
            <span>{error}</span>
          </div>
        )}

        {/* Sign In Form */}
        {tab === 'login' ? (
          <form onSubmit={handleLogin} className="mt-4 space-y-3">
            <div>
              <label className="block text-[11px] font-bold uppercase text-[#6B4423] mb-1">
                {language === 'hi' ? 'ईमेल या फोन नंबर' : 'Email or Phone'}
              </label>
              <input
                type="text"
                required
                value={loginId}
                onChange={(e) => setLoginId(e.target.value)}
                placeholder="ramesh@kisaan.ai or +91 98220..."
                className="w-full px-3.5 py-2.5 rounded-xl border border-[#DECDBE] bg-white text-xs text-[#2C1E14] focus:outline-none focus:border-[#4C7A3D]"
              />
            </div>

            <div>
              <label className="block text-[11px] font-bold uppercase text-[#6B4423] mb-1">
                {language === 'hi' ? 'पासवर्ड' : 'Password'}
              </label>
              <input
                type="password"
                required
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="••••••••"
                className="w-full px-3.5 py-2.5 rounded-xl border border-[#DECDBE] bg-white text-xs text-[#2C1E14] focus:outline-none focus:border-[#4C7A3D]"
              />
            </div>

            <button
              type="submit"
              disabled={loading}
              className="w-full mt-2 py-3 rounded-xl bg-[#4C7A3D] hover:bg-[#3F6632] disabled:opacity-50 text-white font-bold text-xs shadow-md transition-all flex items-center justify-center space-x-1.5"
            >
              <LogIn size={15} />
              <span>{loading ? 'Signing in...' : language === 'hi' ? 'लॉगिन करें' : 'Sign In to Farm Portal'}</span>
            </button>
          </form>
        ) : (
          /* Sign Up Form */
          <form onSubmit={handleRegister} className="mt-4 space-y-2.5">
            <div>
              <label className="block text-[10px] font-bold uppercase text-[#6B4423] mb-0.5">
                {language === 'hi' ? 'पूरा नाम' : 'Farmer Full Name'}
              </label>
              <input
                type="text"
                required
                value={regFullName}
                onChange={(e) => setRegFullName(e.target.value)}
                placeholder="e.g. Ramesh Patil"
                className="w-full px-3 py-2 rounded-xl border border-[#DECDBE] bg-white text-xs text-[#2C1E14] focus:outline-none focus:border-[#4C7A3D]"
              />
            </div>

            <div className="grid grid-cols-2 gap-2">
              <div>
                <label className="block text-[10px] font-bold uppercase text-[#6B4423] mb-0.5">
                  Email
                </label>
                <input
                  type="email"
                  required
                  value={regEmail}
                  onChange={(e) => setRegEmail(e.target.value)}
                  placeholder="farmer@mail.com"
                  className="w-full px-3 py-2 rounded-xl border border-[#DECDBE] bg-white text-xs text-[#2C1E14] focus:outline-none focus:border-[#4C7A3D]"
                />
              </div>
              <div>
                <label className="block text-[10px] font-bold uppercase text-[#6B4423] mb-0.5">
                  Phone
                </label>
                <input
                  type="tel"
                  value={regPhone}
                  onChange={(e) => setRegPhone(e.target.value)}
                  placeholder="+91 98..."
                  className="w-full px-3 py-2 rounded-xl border border-[#DECDBE] bg-white text-xs text-[#2C1E14] focus:outline-none focus:border-[#4C7A3D]"
                />
              </div>
            </div>

            <div>
              <label className="block text-[10px] font-bold uppercase text-[#6B4423] mb-0.5">
                Password
              </label>
              <input
                type="password"
                required
                value={regPassword}
                onChange={(e) => setRegPassword(e.target.value)}
                placeholder="Choose a password"
                className="w-full px-3 py-2 rounded-xl border border-[#DECDBE] bg-white text-xs text-[#2C1E14] focus:outline-none focus:border-[#4C7A3D]"
              />
            </div>

            <div className="grid grid-cols-2 gap-2">
              <div>
                <label className="block text-[10px] font-bold uppercase text-[#6B4423] mb-0.5">
                  District
                </label>
                <input
                  type="text"
                  required
                  value={regDistrict}
                  onChange={(e) => setRegDistrict(e.target.value)}
                  placeholder="e.g. Nashik, Pune"
                  className="w-full px-3 py-2 rounded-xl border border-[#DECDBE] bg-white text-xs text-[#2C1E14] focus:outline-none focus:border-[#4C7A3D]"
                />
              </div>
              <div>
                <label className="block text-[10px] font-bold uppercase text-[#6B4423] mb-0.5">
                  Land Size (Acres)
                </label>
                <input
                  type="number"
                  step="0.5"
                  value={regLandSize}
                  onChange={(e) => setRegLandSize(e.target.value)}
                  placeholder="e.g. 4.5"
                  className="w-full px-3 py-2 rounded-xl border border-[#DECDBE] bg-white text-xs text-[#2C1E14] focus:outline-none focus:border-[#4C7A3D]"
                />
              </div>
            </div>

            <div>
              <label className="block text-[10px] font-bold uppercase text-[#6B4423] mb-0.5">
                Primary Crops Cultivated
              </label>
              <input
                type="text"
                value={regCrops}
                onChange={(e) => setRegCrops(e.target.value)}
                placeholder="e.g. Wheat, Tomato, Onion"
                className="w-full px-3 py-2 rounded-xl border border-[#DECDBE] bg-white text-xs text-[#2C1E14] focus:outline-none focus:border-[#4C7A3D]"
              />
            </div>

            <button
              type="submit"
              disabled={loading}
              className="w-full mt-2 py-3 rounded-xl bg-[#4C7A3D] hover:bg-[#3F6632] disabled:opacity-50 text-white font-bold text-xs shadow-md transition-all flex items-center justify-center space-x-1.5"
            >
              <UserPlus size={15} />
              <span>{loading ? 'Creating...' : language === 'hi' ? 'खाता बनाएं' : 'Register Farm Profile'}</span>
            </button>
          </form>
        )}
      </div>
    </div>
  );
}
