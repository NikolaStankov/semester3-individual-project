import React, { useState, useEffect } from "react";
import TeamList from "../../components/TeamList";
import useFetch from "../../custom-hooks/useFetch";

const Teams = () => {
  const url = "http://localhost:8080/teams";
  const { data: teams } = useFetch(url);

  return <>{teams && <TeamList teams={teams} />}</>;
};

export default Teams;
