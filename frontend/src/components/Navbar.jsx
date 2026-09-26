import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';
import {
  LayoutDashboard,
  ScanLine,
  Users,
  Landmark,
  Globe,
  User as UserIcon,
  LogIn,
  LogOut,
  Key,
  Shield,
  MapPin,
  ChevronDown
} from 'lucide-react';
import { useLanguage } from '../context/LanguageContext';
import { useAuth } from '../context/AuthContext';

export default function Navbar() {
  const { language, setLanguage, openLanguageModal, t } = useLanguage();
  const { user, isAuthenticated, logout, setIsAuthModalOpen, setIsKeyModalOpen, geminiApiKey } = useAuth();
  const [profileDropdownOpen, setProfileDropdownOpen] = useState(false);

  const navItems = [
    { to: '/', label: t('navDashboard'), icon: LayoutDashboard },
    { to: '/scanner', label: t('navScanner'), icon: ScanLine },
    { to: '/saathi', label: t('navSaathi'), icon: Users },
    { to: '/schemes', label: t('navSchemes'), icon: Landmark },
  ];

  return (
    <header className="sticky top-0 z-40 bg-[#6B4423] text-white shadow-md border-b border-[#55361B]">
      <div className="max-w-6xl mx-auto px-4 h-16 flex items-center justify-between">
        {/* Brand Logo */}
        <NavLink to="/" className="flex items-center space-x-2.5">
          <div className="w-10 h-10 rounded-2xl bg-[#4C7A3D] flex items-center justify-center text-xl shadow-inner border border-[#649953]">
            🌱
          </div>
          <div>
            <div className="font-bold text-lg tracking-tight text-white font-['Poppins'] flex items-center gap-1.5">
              <span>{t('appName')}</span>
              <span className="text-[10px] font-semibold uppercase px-1.5 py-0.5 rounded bg-[#4C7A3D] text-[#EAF3E7]">
                AI 2.0
              </span>
            </div>
            <p className="hidden sm:block text-[11px] text-[#DECDBE] uppercase tracking-wider font-medium">
              {t('appTagline')}
            </p>
          </div>
        </NavLink>

        {/* Desktop Navigation */}
        <nav className="hidden md:flex items-center space-x-1">
          {navItems.map((item) => {
            const Icon = item.icon;
            return (
              <NavLink
                key={item.to}
                to={item.to}
                className={({ isActive }) =>
                  `flex items-center space-x-2 px-3.5 py-2 rounded-xl text-sm font-medium transition-all ${
                    isActive
                      ? 'bg-[#4C7A3D] text-white shadow-sm font-semibold'
                      : 'text-[#DECDBE] hover:text-white hover:bg-[#58371B]'
                  }`
                }
              >
                <Icon size={16} />
                <span>{item.label}</span>
              </NavLink>
            );
          })}
        </nav>

        {/* Right Action Icons: Language Toggle + User Account */}
        <div className="flex items-center space-x-2.5">
          {/* Language Toggle */}
          <div className="bg-[#523319] p-0.5 rounded-xl border border-[#7C502B] flex items-center text-xs">
            <button
              type="button"
              onClick={() => setLanguage('en')}
              className={`px-2 py-1 rounded-lg font-medium transition-all ${
                language === 'en'
                  ? 'bg-[#4C7A3D] text-white shadow-sm font-bold'
                  : 'text-[#DECDBE] hover:text-white'
              }`}
            >
              EN
            </button>
            <button
              type="button"
              onClick={() => setLanguage('hi')}
              className={`px-2 py-1 rounded-lg font-medium transition-all ${
                language === 'hi'
                  ? 'bg-[#4C7A3D] text-white shadow-sm font-bold'
                  : 'text-[#DECDBE] hover:text-white'
              }`}
            >
              हिंदी
            </button>
          </div>

          {/* User Account / Login Button */}
          {isAuthenticated ? (
            <div className="relative">
              <button
                type="button"
                onClick={() => setProfileDropdownOpen(!profileDropdownOpen)}
                className="flex items-center space-x-2 px-3 py-1.5 rounded-xl bg-[#523319] hover:bg-[#5D3A1C] border border-[#7C502B] text-xs font-semibold text-[#EAF3E7] transition-colors"
              >
                <div className="w-6 h-6 rounded-full bg-[#4C7A3D] text-white flex items-center justify-center font-bold text-xs">
                  {user.fullName ? user.fullName.charAt(0) : '👨‍🌾'}
                </div>
                <span className="hidden sm:inline-block max-w-[100px] truncate">
                  {user.fullName.split(' ')[0]}
                </span>
                <ChevronDown size={14} className="text-[#DECDBE]" />
              </button>

              {/* Profile Dropdown Menu */}
              {profileDropdownOpen && (
                <div className="absolute right-0 mt-2 w-64 rounded-2xl bg-white text-[#2C1E14] shadow-2xl border border-[#DECDBE] p-3 z-50 animate-fadeIn">
                  <div className="pb-3 border-b border-[#EFE8DC]">
                    <p className="font-bold text-sm text-[#6B4423]">{user.fullName}</p>
                    <p className="text-xs text-[#8A7463]">{user.email || user.phoneNumber}</p>
                    <span className="inline-flex items-center gap-1 mt-1 text-[11px] px-2 py-0.5 rounded-md bg-[#EAF3E7] text-[#4C7A3D] font-semibold">
                      <MapPin size={11} />
                      {user.district}, {user.state}
                    </span>
                  </div>

                  <div className="py-2 space-y-1 text-xs">
                    <div className="px-2 py-1 text-[#5C4533]">
                      <span className="font-bold text-[#6B4423]">Land Holding:</span> {user.landSizeAcres || '4.5'} Acres
                    </div>
                    <div className="px-2 py-1 text-[#5C4533]">
                      <span className="font-bold text-[#6B4423]">Primary Crops:</span> {user.primaryCrops || 'Vegetables'}
                    </div>
                  </div>

                  <div className="pt-2 border-t border-[#EFE8DC] space-y-1">
                    <button
                      type="button"
                      onClick={() => {
                        setProfileDropdownOpen(false);
                        setIsKeyModalOpen(true);
                      }}
                      className="w-full flex items-center space-x-2 px-2.5 py-2 rounded-xl hover:bg-[#F7F2E9] text-xs font-semibold text-[#6B4423] transition-colors"
                    >
                      <Key size={14} className="text-[#C46A2B]" />
                      <span>{geminiApiKey ? 'Gemini Key Configured' : 'Configure Gemini Key'}</span>
                    </button>

                    <button
                      type="button"
                      onClick={() => {
                        setProfileDropdownOpen(false);
                        logout();
                      }}
                      className="w-full flex items-center space-x-2 px-2.5 py-2 rounded-xl hover:bg-red-50 text-xs font-semibold text-red-600 transition-colors"
                    >
                      <LogOut size={14} />
                      <span>Log Out</span>
                    </button>
                  </div>
                </div>
              )}
            </div>
          ) : (
            <button
              type="button"
              onClick={() => setIsAuthModalOpen(true)}
              className="flex items-center space-x-1.5 px-3 py-1.5 rounded-xl bg-[#4C7A3D] hover:bg-[#3F6632] text-white text-xs font-bold shadow-sm transition-all"
            >
              <LogIn size={14} />
              <span>{language === 'hi' ? 'लॉगिन' : 'Sign In'}</span>
            </button>
          )}
        </div>
      </div>

      {/* Mobile Bottom Navigation Bar */}
      <nav className="md:hidden fixed bottom-0 left-0 right-0 z-40 bg-[#6B4423] border-t border-[#55361B] px-2 py-1 flex justify-around items-center shadow-lg">
        {navItems.map((item) => {
          const Icon = item.icon;
          return (
            <NavLink
              key={item.to}
              to={item.to}
              className={({ isActive }) =>
                `flex flex-col items-center justify-center py-1.5 px-3 rounded-xl transition-all ${
                  isActive
                    ? 'text-white bg-[#4C7A3D] font-semibold'
                    : 'text-[#DECDBE] hover:text-white'
                }`
              }
            >
              <Icon size={18} />
              <span className="text-[11px] mt-0.5 leading-tight">{item.label}</span>
            </NavLink>
          );
        })}
      </nav>
    </header>
  );
}
