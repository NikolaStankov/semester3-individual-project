import React, { useState, useEffect } from "react";
import TeamTicketCoverList from "../../components/TeamTicketCoverList";
import useFetch from "../../custom-hooks/useFetch";

const Tickets = () => {
  const url = "http://localhost:8080/teams";
  const { data: teams } = useFetch(url);

  return <>{teams && <TeamTicketCoverList teams={teams} />}</>;
};

export default Tickets;
