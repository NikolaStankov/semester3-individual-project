import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import homePic from "../../images/home-page-img.jpg";

const Home = (props) => {
  return (
    <div className="home-container">
      <h1>NBA-portal.</h1>
      <h3>
        See all of your favourite teams and players. Get a ticket to watch them
        compete.
      </h3>
      <h4>
        Enjoy a variety of tickets and prices for the ultimate NBA experience
      </h4>
      {props.loggedUser && <h2>Hello, {props.loggedUser.username} </h2>}
      <img src={homePic} alt="Basketball in a basket" className="img-bg-tr home-pic"/>
    </div>
  );
};

export default Home;