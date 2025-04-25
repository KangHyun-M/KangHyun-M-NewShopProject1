import React from "react";
import { useNavigate } from "react-router-dom";

export default function Home() {
  const navigate = useNavigate();

  const goToLogin = () => {
    navigate("/login");
  };

  const goToSignup = () => {
    navigate("/signup");
  };

  const goToTest = () => {
    navigate("/test");
  };

  return (
    <div>
      <button onClick={goToLogin}>Login</button>
      <button onClick={goToSignup}>Signup</button>
      <button onClick={goToTest}>Test</button>
    </div>
  );
}
