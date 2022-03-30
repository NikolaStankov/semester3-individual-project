import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

const GameDetails = () => {
  let { id } = useParams();
  const [game, setGame] = useState({});

  const getGame = () => {
    var axios = require("axios").default;
    console.log(id);

    var options = {
      method: "GET",
      url: "https://free-nba.p.rapidapi.com/games/" + id,
      headers: {
        "x-rapidapi-host": "free-nba.p.rapidapi.com",
        "x-rapidapi-key": "d5e4bbdd15msh4006a737ac378b4p14971ajsnfea34a9968a3",
      },
    };

    axios
      .request(options)
      .then(function (response) {
        setGame(response.data);
      })
      .catch(function (error) {
        console.error(error);
      });
  };

  useEffect(() => {
    getGame();
  }, []);

  const { date } = game;
  const { home_team, visitor_team } = game;

  return <></>;
};

/*      <div>Date: {date};</div>
      <div>Home team: {home_team.full_name};</div>
      <div>Visitor team: {visitor_team.full_name};</div> */

export default GameDetails;
