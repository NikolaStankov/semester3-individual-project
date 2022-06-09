import { useNavigate } from "react-router-dom";

const SingleTeamEvent = (props) => {
  let navigate = useNavigate();

  return (
    <div className="team-event">
      <div>{props.teamEvent.home_team.full_name}</div>
      <div>VS</div>
      <div>{props.teamEvent.visitor_team.full_name}</div>
      <div>{props.teamEvent.date}</div>
      <button
        className="standard-button-red"
        onClick={() => {
          navigate("/games/" + props.teamEvent.id + "/tickets");
        }}
      >
        Tickets
      </button>
    </div>
  );
};

export default SingleTeamEvent;
