import { useParams } from "react-router-dom";
import TeamEventsList from "../../components/TeamEventsList";
import useFetch from "../../custom-hooks/useFetch";

const TeamEvents = () => {
  let { teamId } = useParams();

  const urlEvents = "http://localhost:8080/teams/" + teamId + "/events";
  const { data: teamEvents } = useFetch(urlEvents);

  const logo = require("../../images/logos/teamlogo_" + teamId + ".png");

  return (
    <div className="events-content">
      <div className="logo-container-big">
        <img src={logo} alt="Logo" />
      </div>
      <h2>Scheduled games:</h2>
      {teamEvents && <TeamEventsList teamId={teamId} teamEvents={teamEvents} />}
    </div>
  );
};

export default TeamEvents;
