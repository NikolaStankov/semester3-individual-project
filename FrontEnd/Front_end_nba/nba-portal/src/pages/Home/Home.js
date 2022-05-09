import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import homePic from "../../images/home-pic.jpeg";

const Home = () => {
  return (
    <div className="home-text">
      <h2>Welcome to NBA-portal.</h2>
      <h3>
        <Link to="/login" className="login">
          Sign in
        </Link>{" "}
        now and get a ticket for your favourite's team game
      </h3>
      <p>
        You don't have an account? Sign up{" "}
        <Link to="/register" className="sign-up">
          {" "}
          here
        </Link>
      </p>
      <img className="home-pic" src={homePic} alt="NBA arena" />
    </div>
  );
};

export default Home;
