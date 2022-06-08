import TeamTicketCover from "./TeamTicketCover";

const TeamTicketCoverList = (props) => {
  return (
    <div className="team-ticket-cover-container">
      {props.teams.map((team) => (
        <TeamTicketCover key={team.id} team={team} />
      ))}
    </div>
  );
};

export default TeamTicketCoverList;
