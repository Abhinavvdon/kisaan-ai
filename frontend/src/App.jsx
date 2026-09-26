import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import LanguageModal from './components/LanguageModal';
import AuthModal from './components/AuthModal';
import ApiKeyModal from './components/ApiKeyModal';
import Dashboard from './pages/Dashboard';
import Scanner from './pages/Scanner';
import Saathi from './pages/Saathi';
import YojanaSetu from './pages/YojanaSetu';
import { LanguageProvider } from './context/LanguageContext';
import { AuthProvider } from './context/AuthContext';

export default function App() {
  return (
    <LanguageProvider>
      <AuthProvider>
        <div className="min-h-screen flex flex-col bg-[#F7F2E9] pb-20 md:pb-8 selection:bg-[#4C7A3D] selection:text-white">
          <LanguageModal />
          <AuthModal />
          <ApiKeyModal />
          <Navbar />
          <main className="flex-1 max-w-6xl w-full mx-auto px-4 py-6">
            <Routes>
              <Route path="/" element={<Dashboard />} />
              <Route path="/scanner" element={<Scanner />} />
              <Route path="/saathi" element={<Saathi />} />
              <Route path="/schemes" element={<YojanaSetu />} />
            </Routes>
          </main>
          <footer className="hidden md:block py-6 border-t border-[#DECDBE] text-center text-xs text-[#6B4423]/70 font-medium">
            🌱 KISAAN.AI &copy; {new Date().getFullYear()} — Digital Agriculture Platform for Indian Farmers
          </footer>
        </div>
      </AuthProvider>
    </LanguageProvider>
  );
}
