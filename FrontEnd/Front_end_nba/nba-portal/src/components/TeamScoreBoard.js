import ScoreLogMessage from "./ScoreLogMessage";

const TeamScoreBoard = (props) => {
  return (
    <div className="team-container">
      <div className="team-full-name"><h2>{props.team}</h2></div>
      <div className="score"><h2>Points: {props.teamScore}</h2></div>
      <div className="score-message-container">
        {props.teamScorelog.map((scoreLogMessage) => (
          <ScoreLogMessage scoreLogMessage={scoreLogMessage} />
        ))}
      </div>
    </div>
  );
};

export default TeamScoreBoard;
