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
      stompClient.subscribe("/topic/simulation", (data) => {
        console.log(data);
        onMessageReceived(data);
      });
    });
    setStompClient(stompClient);
  }, []);

  useEffect(() => {
    if (team1Score < scorePlayedUntil && team2Score < scorePlayedUntil) {
      sendData();
    }
  }, [team1Score, team2Score, team1ScoreLog, team2ScoreLog]);

  function onMessageReceived(data) {
    const result = JSON.parse(data.body);
    let sumOfPoints = 0;
    let scoreLogMessage = "";

    if (result.team1) {
      scoreLogMessage = result.player + " scored" + result.points + " points!";
      team1ScoreLog.push(scoreLogMessage);
      setTeam1ScoreLog([...team1ScoreLog]);

      sumOfPoints = team1Score + result.points;
      setTeam1Score(sumOfPoints);
    } else if (result.team2) {
      scoreLogMessage = result.player + " scored" + result.points + " points!";
      team2ScoreLog.push(scoreLogMessage);
      setTeam2ScoreLog([...team2ScoreLog]);

      sumOfPoints = team2Score + result.points;
      setTeam2Score(sumOfPoints);
    }
  }

  function sendData() {
    stompClient.send(
      "/app/scoreboard",
      {},
      JSON.stringify({ team1: team1, team2: team2, score: scorePlayedUntil })
    );
  }

  return (
    <>
      {teams && (
        <>
          <div className="live-game-menu-container">
            <div className="team-menu-cotnainer">
              <h2>Team 1:</h2>
              <select value={team1} onChange={(e) => setTeam1(e.target.value)}>
                {teams.map((team) => (
                  <option key={team.id} value={team.full_name}>
                    {team.full_name}
                  </option>
                ))}
              </select>
            </div>
            <div>
              <label>Score to play until: </label>
              <input
                type="number"
                required
                value={scorePlayedUntil}
                onChange={(e) => setScorePlayedUntil(e.target.value)}
              />
            </div>
            <div className="team-menu-cotnainer">
              <h2>Team 2:</h2>
              <select value={team2} onChange={(e) => setTeam2(e.target.value)}>
                {teams.map((team) => (
                  <option key={team.id} value={team.full_name}>
                    {team.full_name}
                  </option>
                ))}
              </select>
            </div>
          </div>
          <button className="live-button" onClick={sendData}>
            Start game
          </button>
        </>
      )}
      <div className="scoreBoard">
        <TeamScoreBoard
          key={1}
          team={team1}
          teamScore={team1Score}
          teamScorelog={team1ScoreLog}
        />
        <TeamScoreBoard
          key={2}
          team={team2}
          teamScore={team2Score}
          teamScorelog={team2ScoreLog}
        />
      </div>
    </>
  );
};

export default LiveScore;
