import React, { useState, useEffect } from "react";
import TeamTable from "../../components/TeamTable";
import useFetch from "../../custom-hooks/useFetch";

const Teams = () => {
  const url = "http://localhost:8080/teams";
  const { data: teams } = useFetch(url);

  return (
    <>
      {teams && <TeamTable teams={teams} />}
    </>
  );
};

export default Teams;
