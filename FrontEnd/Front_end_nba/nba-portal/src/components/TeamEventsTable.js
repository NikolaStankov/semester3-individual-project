import SingleTeamEvent from "./SingleTeamEvent";

const TeamEventsTable = (props) => {
  return (
    <table className="content-table event-table">
      <tr>
        <th>Home team</th>
        <th>Visitor team</th>
        <th>Date</th>
        <th>Tickets</th>
      </tr>
      {props.teamEvents.map((teamEvent) => (
        <SingleTeamEvent key={teamEvent.id} teamEvent={teamEvent} />
      ))}
    </table>
  );
};

export default TeamEventsTable;
