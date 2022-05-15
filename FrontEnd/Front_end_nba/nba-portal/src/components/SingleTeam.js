import React from "react";
import { useNavigate } from "react-router-dom";

const SingleTeam = (props) => {
  const { id, abbreviation, city, conference, division, full_name, name } =
    props.team;

  let navigate = useNavigate();

  let url = "/teams/" + id;

  return (
    <tr
      className="clickable"
      onClick={() => {
        navigate(url);
      }}
    >
      <td>{name}</td>
      <td>{abbreviation}</td>
      <td>{city}</td>
    </tr>
  );
};

export default SingleTeam;
