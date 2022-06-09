import TeamLogo from "./TeamLogo";
import { useNavigate } from "react-router-dom";

const TeamTicketCover = (props) => {
  let navigate = useNavigate();

  return (
    <div className="team-ticket-cover">
      <TeamLogo teamId={props.team.id} />
      <h3>{props.team.full_name}</h3>
      <button
        className="standard-button-red"
        onClick={() => {
          navigate("/teams/" + props.team.id + "/events");
        }}
      >
        Find tickets
      </button>
    </div>
  );
};

export default TeamTicketCover;
