import React, { useState } from "react";
import Login from "./Login";
import Signup from "./Signup";

interface AuthPagesProps {
  onAuthSuccess: (username?: string | null) => void;
}

const AuthPages: React.FC<AuthPagesProps> = ({ onAuthSuccess }) => {
  const [showLogin, setShowLogin] = useState(true);
  return showLogin ? (
    <Login switchToSignup={() => setShowLogin(false)} onAuthSuccess={onAuthSuccess} />
  ) : (
    <Signup switchToLogin={() => setShowLogin(true)} />
  );
};

export default AuthPages;
