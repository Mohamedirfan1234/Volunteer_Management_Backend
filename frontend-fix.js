import React, { useState } from "react";
import axios from "axios";
import "../css/Login.css"; // Reuse login styles for signup too

const Signup = () => {
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
    role: "USER",
  });

  const [successMsg, setSuccessMsg] = useState("");
  const [errorMsg, setErrorMsg] = useState("");
  const [animationType, setAnimationType] = useState("");

  // API URL - Update this to your Render deployment URL
  const API_BASE_URL = "https://volunteer-management-backend-7.onrender.com";

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSuccessMsg("");
    setErrorMsg("");
    setAnimationType("");

    try {
      const response = await axios.post(`${API_BASE_URL}/api/auth/signup`, form);
      
      if (response.data.message) {
        setSuccessMsg(response.data.message);
      } else {
        setSuccessMsg("✅ Registration successful!");
      }
      setAnimationType("success");
      
      // Clear form after successful registration
      setForm({
        name: "",
        email: "",
        password: "",
        role: "USER",
      });
    } catch (err) {
      console.error("Signup error:", err);
      if (err.response && err.response.data) {
        setErrorMsg(err.response.data);
      } else {
        setErrorMsg("❌ Registration failed! Please try again.");
      }
      setAnimationType("error");
    }
  };

  return (
    <div className="login-container">
      <h2 className="login-title">Sign Up</h2>

      {successMsg && (
        <div className={`animated-line-left success-box`}>{successMsg}</div>
      )}
      {errorMsg && (
        <div className={`animated-line-right error-box`}>{errorMsg}</div>
      )}

      <form onSubmit={handleSubmit}>
        <input
          className="login-input"
          type="text"
          name="name"
          placeholder="Name"
          value={form.name}
          onChange={handleChange}
          required
        />
        <input
          className="login-input"
          type="email"
          name="email"
          placeholder="Email"
          value={form.email}
          onChange={handleChange}
          required
        />
        <input
          className="login-input"
          type="password"
          name="password"
          placeholder="Password"
          value={form.password}
          onChange={handleChange}
          required
        />

        <select
          name="role"
          className="login-input"
          value={form.role}
          onChange={handleChange}
          required
        >
          <option value="USER">User</option>
          <option value="ADMIN">Admin</option>
        </select>

        <button className="login-button" type="submit">
          Register
        </button>
      </form>
    </div>
  );
};

export default Signup; 