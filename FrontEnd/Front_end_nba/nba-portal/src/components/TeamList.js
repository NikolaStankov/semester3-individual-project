import React from "react";
import SingleTeam from "./SingleTeam";

const TeamList = (props) => {
  return (
    <ul>
      {props.teams.map((team) => (
        <SingleTeam key={team.id} team={team} />
      ))}
    </ul>
  );
};

export default TeamList;
