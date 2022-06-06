const SinglePurchase = (props) => {
  return (
    <div className="purchase-container">
      <div>
        <h2>Game:</h2>
        <p>
          {props.purchase.game.home_team.full_name} VS{" "}
          {props.purchase.game.visitor_team.full_name} -{" "}
          {props.purchase.game.date}
        </p>
      </div>
      <div>
        <h2>Ticket:</h2>
        <p>
          {props.purchase.ticket.ticket_type} - {props.purchase.ticket.price}$
        </p>
        <h4>Tickets bought: {props.purchase.quantity}</h4>
      </div>
    </div>
  );
};

export default SinglePurchase;
