import SingleTeamEvent from "./SingleTeamEvent";

const TeamEventsList = (props) => {
  const logo = require("../images/logos/teamlogo_" + props.teamId + ".png");

  return (
      <div className="event-list">
        {props.teamEvents.map((teamEvent) => (
          <SingleTeamEvent key={teamEvent.id} teamEvent={teamEvent} />
        ))}
      </div>
  );
};

export default TeamEventsList;
