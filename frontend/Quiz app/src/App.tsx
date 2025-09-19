import React, { useState, useEffect } from "react";
import AuthPages from "./components/AuthPages";
import Dashboard from "./components/Dashboard";
import "./index.css";

const App: React.FC = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [username, setUsername] = useState<string | null>(null);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) setIsAuthenticated(true);
  }, []);

  const handleAuthSuccess = (user?: string | null) => {
    setUsername(user || null);
    setIsAuthenticated(true);
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    setIsAuthenticated(false);
    setUsername(null);
  };

  return (
    <div className="app">
      {isAuthenticated ? (
        <Dashboard userame={username} onLogout={handleLogout} />
      ) : (
        <div className="auth-wrapper">
          <AuthPages onAuthSuccess={handleAuthSuccess} />
        </div>
      )}
    </div>
  );
};

export default App;
