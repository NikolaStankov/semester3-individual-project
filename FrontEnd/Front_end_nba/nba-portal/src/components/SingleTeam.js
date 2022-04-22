import React from "react";
import { Link } from "react-router-dom";

const SingleTeam = (props) => {
  const { abbreviation, city, conference, division, full_name } =
    props.team;

  return (
    <tr>
      <td>{abbreviation}</td>
      <td>{city}</td>
      <td>{conference}</td>
      <td>{division}</td>
      <td>{full_name}</td>
    </tr>
  );
};

export default SingleTeam;
