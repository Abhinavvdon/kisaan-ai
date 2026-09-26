import React, { createContext, useContext, useState, useEffect } from 'react';
import { translations } from '../i18n/translations';

const LanguageContext = createContext();

export function LanguageProvider({ children }) {
  const [language, setLanguageState] = useState(() => {
    return localStorage.getItem('kisaan_language') || 'en';
  });

  const [showModal, setShowModal] = useState(() => {
    // Show on first load if user hasn't made a choice yet
    return !localStorage.getItem('kisaan_language');
  });

  const setLanguage = (lang) => {
    setLanguageState(lang);
    localStorage.setItem('kisaan_language', lang);
    setShowModal(false);
  };

  const openLanguageModal = () => setShowModal(true);
  const closeLanguageModal = () => setShowModal(false);

  // Translation helper
  const t = (key) => {
    const langDict = translations[language] || translations.en;
    return langDict[key] || translations.en[key] || key;
  };

  return (
    <LanguageContext.Provider
      value={{
        language,
        setLanguage,
        showModal,
        openLanguageModal,
        closeLanguageModal,
        t,
      }}
    >
      {children}
    </LanguageContext.Provider>
  );
}

export function useLanguage() {
  const context = useContext(LanguageContext);
  if (!context) {
    throw new Error('useLanguage must be used within a LanguageProvider');
  }
  return context;
}
