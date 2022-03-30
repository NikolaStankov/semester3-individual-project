import React from "react";
import SingleGame from "./SingleGame";

const GamesList = (props) => {
  return (
    <ul>
      {props.games.map((game) => (
        <SingleGame key={game.id} game={game} />
      ))}
    </ul>
  );
};

export default GamesList;
