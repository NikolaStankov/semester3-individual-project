import React from "react";
import { useParams } from "react-router-dom";
import useFetch from "../../custom-hooks/useFetch";

const GameDetails = () => {
  let { id } = useParams();
  const url = "http://localhost:8080/games/" + id;

  const { data: game } = useFetch(url);

  return (
    <>
      {game && (
        <div className="game-details">
          <div>Game details - id:{id}</div>
          <div>Date: {game.date}</div>
          <div>
            {game.home_team.full_name} vs {game.visitor_team.full_name}
          </div>
        </div>
      )}
    </>
  );
};

export default GameDetails;
