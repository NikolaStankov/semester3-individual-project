import React from "react";
import SingleTicket from "./SingleTicket";

const TicketsList = (props) => {
  return (
    <ul>
      {props.tickets.map((ticket) => (
        <SingleTicket key={ticket.ticketId} ticket={ticket} />
      ))}
    </ul>
  );
};

export default TicketsList;
