import TeamLogo from "./TeamLogo";

const TeamTicketCover = (props) => {
  return (
    <div className="team-ticket-cover">
      <TeamLogo teamId={props.team.id} />
      <h3>{props.team.full_name}</h3>
      <button className="standard-button-red">Find tickets</button>
    </div>
  );
};

export default TeamTicketCover;
