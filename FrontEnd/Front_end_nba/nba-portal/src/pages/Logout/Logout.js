import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Cookies from "universal-cookie";

const Logout = (props) => {
  let navigate = useNavigate();

  useEffect(() => {
    localStorage.removeItem("accessToken");
    console.log(
      "Local storage removed accessToken:",
      localStorage.getItem("accessToken")
    );
    localStorage.removeItem("expirationDate");
    console.log(
      "Local storage removed expirationDate:",
      localStorage.getItem("expirationDate")
    );
    props.updateLoggedUserProps(null);
    navigate("/");
  }, [navigate, props]);

  return <></>;
};

export default Logout;
