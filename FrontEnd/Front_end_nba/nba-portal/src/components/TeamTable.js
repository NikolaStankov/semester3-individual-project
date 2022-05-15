import React from "react";
import SingleTeam from "./SingleTeam";

const TeamTable = (props) => {
  return (
    <table className="content-table">
      <tr>
        <th>Name</th>
        <th>Abbreviaton</th>
        <th>City</th>
      </tr>
      {props.teams.map((team) => (
        <SingleTeam key={team.id} team={team} />
      ))}
    </table>
  );
};

export default TeamTable;
