import React, { useState, useEffect } from "react";
import GamesList from "../../components/GamesList";

const Games = () => {
  const [games, setGames] = useState([]);
  const [pageCount, setPageCount] = useState(1);

  const getGames = () => {
    var axios = require("axios").default;
    const today = new Date();
    const dateURL =
      today.getFullYear() +
      "-" +
      (today.getMonth() + 1) +
      "-" +
      today.getDate();

    var options = {
      method: "GET",
      url: "https://free-nba.p.rapidapi.com/games?start_date=" + dateURL,
      params: { page: pageCount, per_page: 25 },
      headers: {
        "x-rapidapi-host": "free-nba.p.rapidapi.com",
        "x-rapidapi-key": "d5e4bbdd15msh4006a737ac378b4p14971ajsnfea34a9968a3",
      },
    };

    axios
      .request(options)
      .then(function (response) {
        console.log(response.data);
        const gamesData = response.data.data || [];
        setGames(gamesData);
      })
      .catch(function (error) {
        console.error(error);
      });
  };

  useEffect(() => {
    getGames();
  }, [pageCount]);

  const incrementPageCount = () => setPageCount(pageCount + 1);
  const decrementPageCount = () => setPageCount(pageCount - 1);

  return (
    <>
      <GamesList games={games} />
      <button onClick={decrementPageCount}>See previous</button>
      <button onClick={incrementPageCount}>See more</button>
    </>
  );
};

export default Games;
