import React from "react";
import SingleTicket from "./SingleTicket";

const TicketsList = (props) => {
  return (
    <div className="ticket-list">
      {props.tickets.map((ticket) => (
        <SingleTicket
          key={ticket.id}
          ticket={ticket}
          selectedTicketId={props.selectedTicketId}
          setSelectedTicketId={props.setSelectedTicketId}
        />
      ))}
    </div>
  );
};

export default TicketsList;
