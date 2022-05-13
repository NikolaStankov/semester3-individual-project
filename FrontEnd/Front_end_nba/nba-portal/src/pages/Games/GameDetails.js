import axios from "axios";
import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import useFetch from "../../custom-hooks/useFetch";

const GameDetails = () => {
  const [tickets, setTickets] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");

  let navigate = useNavigate();

  let { id } = useParams();
  const url = "http://localhost:8080/games/" + id;

  const { data: game } = useFetch(url);

  const getTickets = () => {
    const tickets_url = "http://localhost:8080/tickets";

    axios
      .get(tickets_url)
      .then((response) => {
        const responseData = response.data;
        setTickets(responseData);
        console.log(tickets);
      })
      .catch(function (error) {
        if (error.response) {
          if (error.response.status === 401) {
            navigate("/login", { state: { page: window.location.pathname } });
          }
        }
      });
  };

  return (
    <>
      {game && (
        <>
          <div className="content-details">
            <div>
              <span className="details-title">Date:</span> {game.date}
            </div>
            <div>
              <spin className="details-title">Home team:</spin>{" "}
              {game.home_team.full_name}
            </div>
            <div>
              <span className="details-title">Visitor team:</span>{" "}
              {game.visitor_team.full_name}
            </div>
          </div>

          <button className="button-big" onClick={getTickets}>
            See tickets
          </button>
        </>
      )}
    </>
  );
};

export default GameDetails;
