import React, { useState } from "react";
import { apiRequest } from "../api/apiClient";

interface LoginProps {
  switchToSignup: () => void;
  onAuthSuccess: (userName?: string | null) => void;
}

const Login: React.FC<LoginProps> = ({ switchToSignup, onAuthSuccess }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState<string | null>(null);

  const handleLogin = async () => {
    setMessage("");
    setLoading(true);

    try {
      const result = await apiRequest({
        url: "http://localhost:8081/user/login",
        method: "POST",
        body: { userName: email, password },
      });
      console.log(result);
      
      // backend returns JSON { message: "User authenticated", token: "..." }
      if (result.msg == "User authenticated" && result.token) {
        localStorage.setItem("token", result.token);
        onAuthSuccess(result.username || null);
      } else {
        setMessage(result.msg || "Login failed");
      }
    } catch (err: any) {
      console.log(err);
      setMessage(err?.msg || "Network error");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-container">
      <h2>Login</h2>
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button onClick={handleLogin} disabled={loading}>
        {loading ? "Logging in..." : "Login"}
      </button>

      {message && <div className="error">{message}</div>}

      <p>
        Don't have an account?{" "}
        <button className="link-btn" onClick={switchToSignup}>
          Signup
        </button>
      </p>
    </div>
  );
};

export default Login;
