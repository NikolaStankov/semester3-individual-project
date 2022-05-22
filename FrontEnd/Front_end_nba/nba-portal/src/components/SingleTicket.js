import React from "react";

const SingleTicket = (props) => {
  const { id, price, ticket_type, specification } = props.ticket;

  const handleClick = (e) => {
    const selectedTicketRBtn = e.target;
    selectedTicketRBtn.checked = true;
    selectedTicketRBtn.style.color = 'var(--nba-blue)';
    props.updateSelectedTicketProps(selectedTicketRBtn.value);
  };

  return (
    <div className="single-ticket" onClick={handleClick}>
      <input
        className="invisible"
        type="radio"
        id={id}
        name="ticket"
        value={id}
      />
      <label htmlFor={id}>
        Ticket: {ticket_type}, price : {price} - {specification}
      </label>
    </div>
  );
};

export default SingleTicket;
