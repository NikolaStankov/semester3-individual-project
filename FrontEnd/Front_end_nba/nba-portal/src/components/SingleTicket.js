import React from "react";

const SingleTicket = (props) => {
  const { id, price, ticket_type, specification } = props.ticket;

  return (
    <li>
      Ticket: {id}, price : {price}, type: {ticket_type} - {specification}
    </li>
  );
};

export default SingleTicket;
