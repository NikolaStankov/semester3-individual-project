import React from "react";

const SingleTicket = (props) => {
  return (
    <div className="ticket-entity">
      <input
        type="radio"
        id={props.ticket.id}
        value={props.selectedTicketId}
        name="ticket"
        onChange={(e) => props.setSelectedTicketId(e.target.id)}
      />
      <label>
        {props.ticket.ticket_type} - price: {props.ticket.price}(
        {props.ticket.specification})
      </label>
    </div>
  );
};

export default SingleTicket;
