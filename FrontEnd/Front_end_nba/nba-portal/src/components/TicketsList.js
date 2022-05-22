import React from "react";
import SingleTicket from "./SingleTicket";

const TicketsList = (props) => {
  return (
    <div className="list">
      {props.tickets.map((ticket) => (
        <SingleTicket
          key={ticket.id}
          ticket={ticket}
          updateSelectedTicketProps={props.updateSelectedTicketProps}
        />
      ))}
    </div>
  );
};

export default TicketsList;
