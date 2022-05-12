import RegisterForm from "../components/RegisterForm";
import React, { useState } from "react";

const Register = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [repeatedPassword, setRepeatedPassword] = useState("");

  return (
    <>
      <RegisterForm
        username={username}
        password={password}
        repeatedPassword={repeatedPassword}
        updateUsernameProps={setUsername}
        updatePasswordProps={setPassword}
        updateRepeatedPasswordProps={setRepeatedPassword}
      />
    </>
  );
};

export default Register;
