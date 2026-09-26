import React from 'react';
import { NavLink } from 'react-router-dom';
import { LayoutDashboard, ScanLine, Users, Landmark, Globe } from 'lucide-react';
import { useLanguage } from '../context/LanguageContext';

export default function Navbar() {
  const { language, setLanguage, openLanguageModal, t } = useLanguage();

  const navItems = [
    { to: '/', label: t('navDashboard'), icon: LayoutDashboard },
    { to: '/scanner', label: t('navScanner'), icon: ScanLine },
    { to: '/saathi', label: t('navSaathi'), icon: Users },
    { to: '/schemes', label: t('navSchemes'), icon: Landmark },
  ];

  const toggleLanguage = () => {
    setLanguage(language === 'en' ? 'hi' : 'en');
  };

  return (
    <header className="sticky top-0 z-40 bg-[#6B4423] text-white shadow-md border-b border-[#55361B]">
      <div className="max-w-6xl mx-auto px-4 h-16 flex items-center justify-between">
        <NavLink to="/" className="flex items-center space-x-2.5">
          <div className="w-10 h-10 rounded-xl bg-[#4C7A3D] flex items-center justify-center text-xl shadow-inner border border-[#649953]">
            🌱
          </div>
          <div>
            <div className="font-bold text-lg tracking-tight text-white font-['Poppins'] flex items-center gap-1.5">
              <span>{t('appName')}</span>
              <span className="text-[10px] font-semibold uppercase px-1.5 py-0.5 rounded bg-[#4C7A3D] text-[#EAF3E7]">
                Live
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

        {/* Persistent Language Toggle (Top Right) */}
        <div className="flex items-center space-x-2">
          <div className="bg-[#523319] p-0.5 rounded-xl border border-[#7C502B] flex items-center text-xs">
            <button
              type="button"
              onClick={() => setLanguage('en')}
              className={`px-2.5 py-1 rounded-lg font-medium transition-all ${
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
              className={`px-2.5 py-1 rounded-lg font-medium transition-all ${
                language === 'hi'
                  ? 'bg-[#4C7A3D] text-white shadow-sm font-bold'
                  : 'text-[#DECDBE] hover:text-white'
              }`}
            >
              हिंदी
            </button>
          </div>

          {/* Quick open language modal button */}
          <button
            type="button"
            onClick={openLanguageModal}
            title="Change language / भाषा बदलें"
            className="p-1.5 rounded-lg text-[#DECDBE] hover:text-white hover:bg-[#58371B] transition-colors"
          >
            <Globe size={18} />
          </button>
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
