import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const LoginForm = () => {
  const [errorMessage, setErrorMessage] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  let navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    const loginRequest = JSON.stringify({
      username: username,
      password: password,
    });

    var config = {
      method: "post",
      url: "http://localhost:8080/login",
      headers: {
        "Content-Type": "application/json",
      },
      data: loginRequest,
    };

    axios(config)
      .then(function (response) {
        localStorage.setItem("accessToken", response.data.accessToken);
        navigate("/");
      })
      .catch(function (error) {
        if (error.response) {
          setErrorMessage(error.response.data.message);
        }
      });
  };

  return (
    <div className="form">
      <form onSubmit={handleSubmit}>
        <div className="input-container">
          <label>Username </label>
          <input
            type="text"
            name="uname"
            value={username}
            required
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className="input-container">
          <label>Password </label>
          <input
            type="password"
            name="pass"
            value={password}
            required
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        {errorMessage && <div className="text-error">* {errorMessage}</div>}
        <div className="button-container">
          <input type="submit" value="Login" />
        </div>
      </form>
    </div>
  );
};

export default LoginForm;
