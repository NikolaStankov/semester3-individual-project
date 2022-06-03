import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import axios from "axios";
import jwtDecode from "jwt-decode";

const LoginForm = (props) => {
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
        var decodedToken = jwtDecode(response.data.accessToken);
        localStorage.setItem("expirationDate", decodedToken.exp);
        const userId = decodedToken.userId;
        getUser(userId);
        navigate("/");
      })
      .catch(function (error) {
        if (error.response) {
          setErrorMessage(error.response.data.message);
        }
      });
  };

  const getUser = (id) => {
    const userURL = "http://localhost:8080/users/" + id;

    axios
      .get(userURL)
      .then((response) => {
        props.updateLoggedUserProps(response.data);
        localStorage.setItem("username", response.data.username);
      })
      .catch((error) => {
        console.log(error.response.message);
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
        <p>
          You don't have an account? Sign up{" "}
          <Link to="/register" className="sign-up">
            {" "}
            here
          </Link>
        </p>
      </form>
    </div>
  );
};

export default LoginForm;
