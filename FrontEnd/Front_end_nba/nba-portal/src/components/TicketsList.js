import React from "react";
import SingleTicket from "./SingleTicket";

const TicketsList = (props) => {
  return (
    <ul>
      {props.tickets.map((ticket) => (
        <SingleTicket key={ticket.id} ticket={ticket} />
      ))}
    </ul>
  );
};

export default TicketsList;