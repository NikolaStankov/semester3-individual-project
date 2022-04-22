import React from "react";
import SingleTeam from "./SingleTeam";

const TeamTable = (props) => {
  return (
    <table className="content-table">
      <tr>
        <th>Abbreviation</th>
        <th>City</th>
        <th>Conference</th>
        <th>Division</th>
        <th>Full name</th>
      </tr>
      {props.teams.map((team) => (
        <SingleTeam key={team.id} team={team} />
      ))}
    </table>
  );
};

export default TeamTable;
