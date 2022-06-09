const SingleTeamEvent = (props) => {
  return (
    <div className="team-event">
      <div>{props.teamEvent.home_team.full_name}</div>
      <div>VS</div>
      <div>{props.teamEvent.visitor_team.full_name}</div>
      <div>{props.teamEvent.date}</div>
        <button className="standard-button-red">Tickets</button>
    </div>
  );
};

export default SingleTeamEvent;
