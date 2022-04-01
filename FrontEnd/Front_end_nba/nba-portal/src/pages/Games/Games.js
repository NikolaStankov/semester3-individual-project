import React, { useState, useEffect } from "react";
import GamesList from "../../components/GamesList";

const Games = () => {
  const [games, setGames] = useState(null);
  const [pageCount, setPageCount] = useState(1);

  const getGames = () => {
    var axios = require("axios").default;

    axios.get("http://localhost:8080/games").then((response) => {
      const gamesData = response.data || [];
      setGames(gamesData);
      console.log(response.data);
      console.log(games);
    });
  };

  useEffect(() => {
    getGames();
  }, [pageCount]);

  const incrementPageCount = () => setPageCount(pageCount + 1);
  const decrementPageCount = () => setPageCount(pageCount - 1);

  return (
    <>
      {games && <GamesList games={games} />}
      <button onClick={decrementPageCount}>See previous</button>
      <button onClick={incrementPageCount}>See more</button>
    </>
  );
};

export default Games;
