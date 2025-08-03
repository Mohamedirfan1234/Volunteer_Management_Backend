import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useLocation } from "react-router-dom";
import "../css/Login.css";

// Include animation styles here

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMsg, setErrorMsg] = useState("");
  const [successMsg, setSuccessMsg] = useState("");
  const [animationType, setAnimationType] = useState(""); // 'success' or 'error'

  const navigate = useNavigate();
  const location = useLocation();

  // API URL - Update this to your Render deployment URL
  const API_BASE_URL = "https://volunteer-management-backend-7.onrender.com";

  // Check for signup success message in URL params
  useEffect(() => {
    const urlParams = new URLSearchParams(location.search);
    const message = urlParams.get('message');
    if (message) {
      setSuccessMsg(message);
      setAnimationType("success");
      // Clear the message from URL after displaying
      navigate(location.pathname, { replace: true });
    }
  }, [location, navigate]);

  const handleLogin = async (e) => {
    e.preventDefault();
    setErrorMsg("");
    setSuccessMsg("");
    setAnimationType("");

    try {
      const res = await axios.post(`${API_BASE_URL}/api/auth/login`, {
        email,
        password,
      });

      const { token, role } = res.data;

      localStorage.setItem("token", token);
      localStorage.setItem("role", role); // store the role
      setSuccessMsg("✅ Welcome back!");
      setAnimationType("success");

      setTimeout(() => {
        // All users redirect to the same page
        navigate("/");
        window.location.reload(); // update navbar
      }, 1500);
    } catch (err) {
      console.error("Login error:", err);
      if (err.response && err.response.data) {
        setErrorMsg(err.response.data);
      } else {
        setErrorMsg("❌ Invalid email or password");
      }
      setAnimationType("error");
    }
  };

  return (
    <div className="login-container">
      <h2 className="login-title">Login</h2>

      {/* Success Message Box */}
      {successMsg && (
        <div className={`animated-line-left success-box`}>{successMsg}</div>
      )}

      {/* Error Message Box */}
      {errorMsg && (
        <div className={`animated-line-right error-box`}>{errorMsg}</div>
      )}

      <form onSubmit={handleLogin}>
        <input
          className="login-input"
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <input
          className="login-input"
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button className="login-button" type="submit">
          Login
        </button>
      </form>
    </div>
  );
};

export default Login; 