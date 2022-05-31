import { useState } from "react";
import useFetch from "../custom-hooks/useFetch";
import axios from "axios";

const UploadTicketForm = () => {
  const [ticketType, setTicketType] = useState("");
  const [price, setPrice] = useState();
  const [specification, setSpecification] = useState();

  const [ticketMessage, setTicketMessage] = useState("");

  const [date, setDate] = useState("");
  const [season, setSeason] = useState(2022);
  const [homeTeamId, setHomeTeamId] = useState();
  const [visitorTeamId, setVisitorTeamId] = useState();

  const [gameMessage, setGameMessage] = useState();

  const { data: types } = useFetch("http://localhost:8080/tickets/types");
  const { data: teams } = useFetch("http://localhost:8080/teams");

  const handleGameSubmit = (event) => {
    event.preventDefault();
    const gameRequest = JSON.stringify({
      date: date,
      season: season,
      homeTeamId: homeTeamId,
      visitorTeamId: visitorTeamId,
    });

    const token = localStorage.getItem("accessToken");

    var config = {
      method: "post",
      url: "http://localhost:8080/games",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      data: gameRequest,
    };

    axios(config)
      .then(function (response) {
        let message = "Added a game with id: " + response.data.gameId;
        setGameMessage(message);
      })
      .catch(function (error) {
        if (error.response) {
          console.log(error.response.data.message);
        }
      });
  };

  const handleTicketSubmit = (event) => {
    event.preventDefault();
    const ticketRequest = JSON.stringify({
      ticket_type: ticketType,
      price: price,
      specification: specification,
    });

    const token = localStorage.getItem("accessToken");

    var config = {
      method: "post",
      url: "http://localhost:8080/tickets",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      data: ticketRequest,
    };

    axios(config)
      .then(function (response) {
        let message = "Added a ticket with id: " + response.data.ticketId;
        setTicketMessage(message);
      })
      .catch(function (error) {
        if (error.response) {
          console.log(error.response.data.message);
        }
      });
  };

  return (
    <>
      <div>
        <h2>Games</h2>
        <div className="form">
          <form onSubmit={handleGameSubmit}>
            <div className="input-container">
              <label>Date </label>
              <input
                type="text"
                name="date"
                value={date}
                required
                onChange={(e) => setDate(e.target.value)}
              />
            </div>
            <div className="input-container">
              <label>Season </label>
              <input
                type="number"
                name="season"
                value={season}
                required
                min={2022}
                max={2023}
                onChange={(e) => setSeason(e.target.value)}
              />
            </div>
            <div className="input-container">
              <label>Home team</label>
              <select
                value={homeTeamId}
                onChange={(e) => setHomeTeamId(e.target.value)}
                required
              >
                <option value={""}></option>
                {teams &&
                  teams.map((team) => (
                    <option value={team.id}>{team.full_name}</option>
                  ))}
              </select>
            </div>
            <div className="input-container">
              <label>Visitor team</label>
              <select
                value={visitorTeamId}
                onChange={(e) => setVisitorTeamId(e.target.value)}
                required
              >
                <option value={""}></option>
                {teams &&
                  teams.map((team) => (
                    <option value={team.id}>{team.full_name}</option>
                  ))}
              </select>
            </div>
            {gameMessage && (
              <div className="message-success">{gameMessage}</div>
            )}
            <div className="button-container">
              <input type="submit" value="Add game" />
            </div>
          </form>
        </div>
      </div>
      <div>
        <h2>Tickets</h2>
        <div className="form">
          <form onSubmit={handleTicketSubmit}>
            <div className="input-container">
              <label>Ticket type </label>
              <select
                value={ticketType}
                onChange={(e) => setTicketType(e.target.value)}
                required
              >
                <option value={""}></option>
                {types &&
                  types.map((type) => <option value={type}>{type}</option>)}
              </select>
            </div>
            <div className="input-container">
              <label>Price </label>
              <input
                type="number"
                name="price"
                value={price}
                required
                onChange={(e) => setPrice(e.target.value)}
              />
            </div>
            <div className="input-container">
              <label>Specification </label>
              <input
                type="text"
                name="spec"
                value={specification}
                required
                onChange={(e) => setSpecification(e.target.value)}
              />
            </div>
            {ticketMessage && (
              <div className="message-success">{ticketMessage}</div>
            )}
            <div className="button-container">
              <input type="submit" value="Add ticket" />
            </div>
          </form>
        </div>
      </div>
    </>
  );
};

export default UploadTicketForm;
