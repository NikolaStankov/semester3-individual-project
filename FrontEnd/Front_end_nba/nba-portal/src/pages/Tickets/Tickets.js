import React, { useState, useEffect } from "react";
import TicketsList from "../../components/TicketsList";

const Tickets = () => {
  const [tickets, setTickets] = useState([]);
  var axios = require("axios").default;

  const getTicketsFromBackend = () => {
    axios.get("http://localhost:8080/tickets").then((response) => {
      setTickets(response.data || []);
    });
  };

  useEffect(() => {
    getTicketsFromBackend();
  }, []);

  return (
    <>
      <TicketsList tickets={tickets} />
      <div> ***THESE COME FROM BACK END***</div>
    </>
  );
};

export default Tickets;
