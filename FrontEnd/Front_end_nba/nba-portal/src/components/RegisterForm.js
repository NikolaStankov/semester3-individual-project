import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const RegisterForm = () => {
  const [errorMessages, setErrorMessages] = useState({});

  let navigate = useNavigate();

  const renderErrorMessage = (name) =>
    name === errorMessages.name && (
      <div className="error">{errorMessages.message}</div>
    );

  const handleSubmit = (event) => {
    event.preventDefault();
    navigate("/");
  };

  return (
    <div className="form">
      <form onSubmit={handleSubmit}>
        <div className="input-container">
          <label>Username </label>
          <input type="text" name="uname" required />
          {renderErrorMessage("uname")}
        </div>
        <div className="input-container">
          <label>Password </label>
          <input type="password" name="pass" required />
          {renderErrorMessage("pass")}
        </div>
        <div className="input-container">
          <label>Repeat password </label>
          <input type="password" name="pass-repeat" required />
          {renderErrorMessage("pass-repeat")}
        </div>
        <div className="button-container">
          <input type="submit" value="Register" />
        </div>
      </form>
    </div>
  );
};

export default RegisterForm;
