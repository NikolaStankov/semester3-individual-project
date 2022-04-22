import React from "react";
import { useNavigate } from "react-router-dom";

const SingleGame = (props) => {
  const { id, date } = props.game;
  const { home_team, visitor_team } = props.game;

  let navigate = useNavigate();

  let url = "/games/" + id;

  return (
    <tr
      className="clickable"
      onClick={() => {
        navigate(url);
      }}
    >
      <td>{date}</td>
      <td>{home_team.full_name}</td>
      <td>{visitor_team.full_name}</td>
    </tr>
  );
};

export default SingleGame;