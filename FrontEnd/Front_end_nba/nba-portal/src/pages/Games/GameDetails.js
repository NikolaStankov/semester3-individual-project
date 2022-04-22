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
        <div className="content-details">
          <div>
            <span className="details-title">Date:</span> {game.date}
          </div>
          <div>
            <spin className="details-title">Home team:</spin>{" "}
            {game.home_team.full_name}
          </div>
          <div>
            <span className="details-title">Visitor team:</span>{" "}
            {game.visitor_team.full_name}
          </div>
        </div>
      )}
    </>
  );
};

export default GameDetails;
