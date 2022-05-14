import axios from "axios";
import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import useFetch from "../../custom-hooks/useFetch";

const GameDetails = (props) => {
  const [tickets, setTickets] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");

  const loggedUser = props.loggedUser;

  let navigate = useNavigate();

  let { id } = useParams();
  const url = "http://localhost:8080/games/" + id;

  const { data: game } = useFetch(url);

  const getTickets = () => {
    const tickets_url = "http://localhost:8080/tickets";
    const token = localStorage.getItem("accessToken");

    if (loggedUser) {
      axios
        .get(tickets_url, { headers: { Authorization: `Bearer ${token}` } })
        .then((response) => {
          const responseData = response.data;
          console.log("Response data: ", responseData);
          setTickets(responseData);
        })
        .catch(function (error) {
          if (error.response) {
            console.log(error.response.errorMessage);
          }
        });
    } else {
      navigate("/login", { state: { page: window.location.pathname } });
    }
  };

  console.log(localStorage.getItem("accessToken"));

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
