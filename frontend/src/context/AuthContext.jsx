import React, { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    try {
      const saved = localStorage.getItem('kisaan_user');
      return saved ? JSON.parse(saved) : null;
    } catch {
      return null;
    }
  });

  const [token, setToken] = useState(() => {
    return localStorage.getItem('kisaan_token') || null;
  });

  const [geminiApiKey, setGeminiApiKeyState] = useState(() => {
    return localStorage.getItem('kisaan_gemini_key') || '';
  });

  const [isAuthModalOpen, setIsAuthModalOpen] = useState(false);
  const [isKeyModalOpen, setIsKeyModalOpen] = useState(false);

  const setGeminiApiKey = (key) => {
    setGeminiApiKeyState(key);
    if (key) {
      localStorage.setItem('kisaan_gemini_key', key);
    } else {
      localStorage.removeItem('kisaan_gemini_key');
    }
  };

  const login = async (loginId, password) => {
    const res = await fetch('/api/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ loginId, password })
    });

    if (!res.ok) {
      const err = await res.json();
      throw new Error(err.error || 'Login failed');
    }

    const data = await res.json();
    setUser(data.user);
    setToken(data.token);
    localStorage.setItem('kisaan_user', JSON.stringify(data.user));
    localStorage.setItem('kisaan_token', data.token);
    setIsAuthModalOpen(false);
    return data;
  };

  const register = async (userData) => {
    const res = await fetch('/api/auth/register', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(userData)
    });

    if (!res.ok) {
      const err = await res.json();
      throw new Error(err.error || 'Registration failed');
    }

    const data = await res.json();
    setUser(data.user);
    setToken(data.token);
    localStorage.setItem('kisaan_user', JSON.stringify(data.user));
    localStorage.setItem('kisaan_token', data.token);
    setIsAuthModalOpen(false);
    return data;
  };

  const logout = () => {
    setUser(null);
    setToken(null);
    localStorage.removeItem('kisaan_user');
    localStorage.removeItem('kisaan_token');
  };

  const updateProfile = async (profileData) => {
    if (!user) return;
    const res = await fetch(`/api/auth/profile?userId=${user.id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(profileData)
    });

    if (res.ok) {
      const updated = await res.json();
      setUser(updated);
      localStorage.setItem('kisaan_user', JSON.stringify(updated));
      return updated;
    }
  };

  return (
    <AuthContext.Provider
      value={{
        user,
        token,
        isAuthenticated: !!user,
        login,
        register,
        logout,
        updateProfile,
        geminiApiKey,
        setGeminiApiKey,
        isAuthModalOpen,
        setIsAuthModalOpen,
        isKeyModalOpen,
        setIsKeyModalOpen
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
}
