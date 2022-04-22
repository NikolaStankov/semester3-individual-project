import React from "react";
import SingleGame from "./SingleGame";

const GameTable = (props) => {
  return (
    <table className="content-table">
      <tr>
        <th>Date</th>
        <th>Home team</th>
        <th>Visitor team</th>
      </tr>
      {props.games.map((game) => (
        <SingleGame key={game.id} game={game} />
      ))}
    </table>
  );
};

export default GameTable;
