import React from "react";
import { Link } from "react-router-dom";

const SingleGame = (props) => {
  const { id, date } = props.game;
  const { home_team, visitor_team } = props.game;
  let url = "/games/" + id;
  return (
    <li>
      <Link to={url}>
        <span>{date}</span>{" "}
        <span>
          {home_team.full_name} vs {visitor_team.full_name}{" "}
        </span>
      </Link>
    </li>
  );
};

export default SingleGame;
