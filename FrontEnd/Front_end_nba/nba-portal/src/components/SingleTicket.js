import React from "react";

const SingleTicket = (props) => {
  const { id, price, game } = props.ticket;
  const { home_team, visitor_team } = game;

  return (
    <li>
      Ticket: {id}, price : {price}, game: {home_team.name} vs{" "}
      {visitor_team.name}
    </li>
  );
};

export default SingleTicket;
