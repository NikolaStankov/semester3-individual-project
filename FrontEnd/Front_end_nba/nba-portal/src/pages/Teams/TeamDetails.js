import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import SinglePlayer from "../../components/SinglePlayer";
import useFetch from "../../custom-hooks/useFetch";

const TeamDetails = () => {
  let { teamId } = useParams();
  const playersURL = "http://localhost:8080/teams/" + teamId + "/players";
  const teamURL = "http://localhost:8080/teams/" + teamId;

  console.log("URL is:", teamURL);

  const { data: players } = useFetch(playersURL);
  const { data: team } = useFetch(teamURL);

  return (
    <>
      <div className="table-wrapper">
        <div>
          <h3>Team info:</h3>
          {team && (
            <table className="content-table">
              <tr>
                <th>Full name</th>
                <th>Name</th>
                <th>Abbreviation</th>
                <th>City</th>
                <th>Conference</th>
                <th>Division</th>
              </tr>
              <tr>
                <td>{team.full_name}</td>
                <td>{team.name}</td>
                <td>{team.abbreviation}</td>
                <td>{team.city}</td>
                <td>{team.conference}</td>
                <td>{team.division}</td>
              </tr>
            </table>
          )}
        </div>
        <div>
          <h3>Players info:</h3>
          {players && (
            <table className="content-table">
              <tr>
                <th>First name</th>
                <th>Last Name</th>
                <th>Position</th>
              </tr>
              {players.map((player) => (
                <SinglePlayer key={player.id} player={player} />
              ))}
            </table>
          )}
        </div>
      </div>
    </>
  );
};

export default TeamDetails;
