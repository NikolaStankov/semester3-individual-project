import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Cookies from "universal-cookie";
import axios from "axios";

const LoginForm = () => {
  const [errorMessages, setErrorMessages] = useState({});
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  let navigate = useNavigate();

  const renderErrorMessage = (name) =>
    name === errorMessages.name && (
      <div className="error">{errorMessages.message}</div>
    );

  const handleSubmit = (event) => {
    event.preventDefault();
    const cookies = new Cookies();
    var data = JSON.stringify({
      username: username,
      password: password,
    });

    var config = {
      method: "post",
      url: "http://localhost:8080/login",
      headers: {
        "Content-Type": "application/json",
      },
      data: data,
    };

    axios(config)
      .then(function (response) {
        cookies.set("accessToken", response.data, { path: "/" });
        navigate("/");
      })
      .catch(function (error) {
        setErrorMessages(true);
        console.log(error);
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
          {renderErrorMessage("uname")}
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
          {renderErrorMessage("pass")}
        </div>
        <div className="button-container">
          <input type="submit" value="Login" />
        </div>
      </form>
    </div>
  );
};

export default LoginForm;
