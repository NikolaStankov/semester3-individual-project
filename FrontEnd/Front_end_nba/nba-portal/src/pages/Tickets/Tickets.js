import React, { useState, useEffect } from "react";
import TicketsList from "../../components/TicketsList";
import useFetch from "../../custom-hooks/useFetch";

const Tickets = () => {
  const url = "http://localhost:8080/tickets";
  const { data: tickets } = useFetch(url);

  return (
    <>
      {tickets && <TicketsList tickets={tickets} />}
      <div> ***THESE COME FROM BACK END***</div>
    </>
  );
};

export default Tickets;
