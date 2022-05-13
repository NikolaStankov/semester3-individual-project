import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import homePic from "../../images/home-pic.jpeg";

const Home = (props) => {
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
      {props.loggedUser && <h2>Logged in as {props.loggedUser.username} </h2>}
    </div>
  );
};

export default Home;
