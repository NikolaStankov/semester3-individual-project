import React, { useState, useEffect } from "react";
import TeamList from "../../components/TeamList";

const Teams = () => {
    const [teams, setTeams] = useState(null);

    const getTeams = () => {
    var axios = require("axios").default;

    axios.get("http://localhost:8080/teams").then((response) => {
      const teamsData = response.data || [];
      setTeams(teamsData);
      console.log(response.data);
      console.log(teams);
    });
    }

    useEffect(() => {
        getTeams();
    }, [])

    return ( 
        <>
        {teams && <TeamList teams={teams} />}
        </>
     );
}
 
export default Teams;