const TeamScoreBoard = (props) => {
  return (
    <div className="team-container">
      <div className="team-full-name">{props.team}</div>
      <div className="score">{props.score}</div>
      <div className="score-message-container">
        {props.teamScorelog.map((scoreLogMessage) => {
          <div className="score-message"><p>{scoreLogMessage}</p></div>
        })}
      </div>
    </div>
  );
};

export default TeamScoreBoard;
