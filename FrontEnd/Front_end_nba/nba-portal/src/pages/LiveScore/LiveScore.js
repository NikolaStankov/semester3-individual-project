import { useState, useEffect } from "react";
import useFetch from "../../custom-hooks/useFetch";
import TeamScoreBoard from "../../components/TeamScoreBoard";
import SockJS from "sockjs-client";
import Stomp from "stompjs";

const ENDPOINT = "http://localhost:8080/ws";

const LiveScore = () => {
  const [team1, setTeam1] = useState();
  const [team2, setTeam2] = useState();
  const [scorePlayedUntil, setScorePlayedUntil] = useState(0);
  const [team1Score, setTeam1Score] = useState(0);
  const [team2Score, setTeam2Score] = useState(0);
  const [team1ScoreLog, setTeam1ScoreLog] = useState([]);
  const [team2ScoreLog, setTeam2ScoreLog] = useState([]);

  const [stompClient, setStompClient] = useState(null);

  const url = "http://localhost:8080/teams";
  const { data: teams } = useFetch(url);
  console.log("Teams fetched:", teams);

  useEffect(() => {
    const socket = SockJS(ENDPOINT);
    const stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
      stompClient.subscribe("/topic/simulation", (payload) => {
        console.log(payload);
        onMessageReceived(payload);
      });
    });
    setStompClient(stompClient);
  }, []);

  function onMessageReceived(payload) {
    console.log("The response is: ", payload);
    const result = JSON.parse(payload.body);
    let scoreLogMessage = "";

    if (result.team1) {
      scoreLogMessage = result.player + " scored " + result.points + " point(s)!";
      team1ScoreLog.push(scoreLogMessage);
      setTeam1ScoreLog([...team1ScoreLog]);
      console.log("Team 1 scorelog: ", team1ScoreLog);

      setTeam1Score((team1Score) => team1Score + result.points);
    } else if (result.team2) {
      scoreLogMessage = result.player + " scored " + result.points + " point(s)!";
      team2ScoreLog.push(scoreLogMessage);
      setTeam2ScoreLog([...team2ScoreLog]);
      console.log("Team 2 scorelog: ", team2ScoreLog);

      setTeam2Score((team2Score) => team2Score + result.points);
    }
  }

  function sendData() {
    stompClient.send(
      "/app/simulation",
      {},
      JSON.stringify({
        team1: team1,
        team2: team2,
        score: scorePlayedUntil,
      })
    );
  }

  return (
    <>
      <>
        <div className="live-game-menu-container">
          {teams && (
            <div className="team-menu-cotnainer">
              <h2>Team 1:</h2>
              <select value={team1} onChange={(e) => setTeam1(e.target.value)}>
                <option value=" "></option>
                {teams.map((team) => (
                  <option key={team.id} value={team.full_name}>
                    {team.full_name}
                  </option>
                ))}
              </select>
            </div>
          )}
          <div className="target-score-container">
            <label>Score to play until: </label>
            <input
              type="number"
              required
              value={scorePlayedUntil}
              min="1"
              onChange={(e) => setScorePlayedUntil(e.target.value)}
            />
          </div>
          {teams && (
            <div className="team-menu-cotnainer">
              <h2>Team 2:</h2>
              <select value={team2} onChange={(e) => setTeam2(e.target.value)}>
                <option value=" "></option>
                {teams.map((team) => (
                  <option key={team.id} value={team.full_name}>
                    {team.full_name}
                  </option>
                ))}
              </select>
            </div>
          )}
        </div>
        {team1 && team2 && scorePlayedUntil && (
          <button className="live-button" onClick={sendData}>
            Start game
          </button>
        )}
      </>
      {team1ScoreLog && team2ScoreLog && (
        <div className="scoreBoard">
          <TeamScoreBoard
            team={team1}
            teamScore={team1Score}
            teamScorelog={team1ScoreLog}
          />
          <TeamScoreBoard
            team={team2}
            teamScore={team2Score}
            teamScorelog={team2ScoreLog}
          />
        </div>
      )}
    </>
  );
};

export default LiveScore;
