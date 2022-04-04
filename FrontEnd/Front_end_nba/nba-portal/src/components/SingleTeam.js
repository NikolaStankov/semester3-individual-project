import React from "react";
import { Link } from "react-router-dom";

const SingleTeam = (props) => {
  const { id, abbreviation, city, conference, division, full_name, name } =
    props.team;

  let url = "/teams/" + id;

  return (
    <li>
      <Link to={url}>
        <span>{name}</span>{" - "}
        <span>
          Conference: {conference}, Division: {division}{" "}
        </span>
      </Link>
    </li>
  );
};

export default SingleTeam;
