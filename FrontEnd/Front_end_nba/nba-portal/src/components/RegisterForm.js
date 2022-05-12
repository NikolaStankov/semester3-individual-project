import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const RegisterForm = (props) => {
  const [errorMessage, setErrorMessage] = useState("");
  const [response, setResponse] = useState(null);

  let navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();

    const username = props.username;
    const password = props.password;
    const repeatedPassword = props.repeatedPassword;

    const userRequest = JSON.stringify({
      username: username,
      password: password,
      repeatedPassword: repeatedPassword,
      role: "USER",
    });

    var config = {
      method: "post",
      url: "http://localhost:8080/users",
      headers: {
        "Content-Type": "application/json",
      },
      data: userRequest,
    };

    axios(config)
      .then((response) => {
        console.log(response);
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
            value={props.username}
            onChange={(e) => props.updateUsernameProps(e.target.value)}
            required
          />
        </div>
        <div className="input-container">
          <label>Password </label>
          <input
            type="password"
            name="pass"
            value={props.password}
            onChange={(e) => props.updatePasswordProps(e.target.value)}
            required
          />
        </div>
        <div className="input-container">
          <label>Repeat password </label>
          <input
            type="password"
            name="pass-repeat"
            value={props.repeatedPassword}
            onChange={(e) => props.updateRepeatedPasswordProps(e.target.value)}
            required
          />
        </div>
        {errorMessage && <div className="text-error">* {errorMessage}</div>}
        <div className="button-container">
          <input type="submit" value="Register" />
        </div>
      </form>
    </div>
  );
};

export default RegisterForm;
