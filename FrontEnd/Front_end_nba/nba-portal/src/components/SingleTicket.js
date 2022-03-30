import React from "react";

const SingleTicket = (props) => {
  const { ticketId, price, game } = props.ticket;

  return (
    <li>
      Ticket: {ticketId}, price : {price}, game: {game}
    </li>
  );
};

export default SingleTicket;
