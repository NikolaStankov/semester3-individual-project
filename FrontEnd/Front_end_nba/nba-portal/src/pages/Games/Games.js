import React, { useState, useEffect } from "react";
import GameTable from "../../components/GameTable";
import useFetch from "../../custom-hooks/useFetch";

const Games = () => {
  const url = "http://localhost:8080/games";
  const { data: games } = useFetch(url);

  return <>{games && <GameTable games={games} />}</>;
};

export default Games;
