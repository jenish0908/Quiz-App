import React, { useState } from "react";
import { apiRequest } from "../api/apiClient";

interface SignupProps {
  switchToLogin: () => void;
}

const Signup: React.FC<SignupProps> = ({ switchToLogin }) => {
  const [form, setForm] = useState({
    username: "",
    password: "",
    email: "",
    firstName: "",
    lastName: "",
    role: "STUDENT",
  });
  const [message, setMessage] = useState<string | null>(null);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSignup = async () => {
    setMessage(null);

    // basic validations
    const passwordRegex = /^(?=.*[A-Z])(?=.*[^A-Za-z0-9]).{8,}$/;
    const emailRegex = /^[^\s@]+@gmail\.com$/;
    const nameRegex = /^\S+$/;
    if (!passwordRegex.test(form.password)) return setMessage("Password must have 8+ chars, 1 capital & 1 special char");
    if (!emailRegex.test(form.email)) return setMessage("Email must be a valid Gmail");
    if (!nameRegex.test(form.firstName) || !nameRegex.test(form.lastName)) return setMessage("Names cannot have spaces");

    try {
      const result = await apiRequest({
        url: "http://localhost:8081/user/signup",
        method: "POST",
        body: form,
      });

      setMessage(result); // backend returns plain text
    } catch (err: any) {
      setMessage(err?.message || "Signup failed");
    }
  };

  return (
    <div className="auth-container">
      <h2>Signup</h2>
      <input name="username" placeholder="Username" value={form.username} onChange={handleChange} />
      <input name="password" type="password" placeholder="Password" value={form.password} onChange={handleChange} />
      <input name="email" type="email" placeholder="Email" value={form.email} onChange={handleChange} />
      <input name="firstName" placeholder="First Name" value={form.firstName} onChange={handleChange} />
      <input name="lastName" placeholder="Last Name" value={form.lastName} onChange={handleChange} />

      <div style={{ display: "flex", gap: "10px", margin: "10px 0" }}>
        Role: 
        <label>
          <input type="radio" name="role" value="STUDENT" checked={form.role === "STUDENT"} onChange={handleChange} />
          Student
        </label>
        <label>
          <input type="radio" name="role" value="PROFESSOR" checked={form.role === "PROFESSOR"} onChange={handleChange} />
          Professor
        </label>
      </div>

      <button onClick={handleSignup}>Signup</button>
      {message && <div className="error">{message}</div>}

      <p>
        Already have an account?{" "}
        <button className="link-btn" onClick={switchToLogin}>
          Login
        </button>
      </p>
    </div>
  );
};

export default Signup;
