import React, { useState, useEffect } from "react";
import GamesList from "../../components/GamesList";
import useFetch from "../../custom-hooks/useFetch";

const Games = () => {
  const url = "http://localhost:8080/games";
  const { data: games } = useFetch(url);

  return (
    <>
      <div className="content-list">{games && <GamesList games={games} />}</div>
    </>
  );
};

export default Games;
