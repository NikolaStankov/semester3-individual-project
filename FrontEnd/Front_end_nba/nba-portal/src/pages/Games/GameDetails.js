import axios from "axios";
import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import TicketsList from "../../components/TicketsList";
import useFetch from "../../custom-hooks/useFetch";

const GameDetails = (props) => {
  const [tickets, setTickets] = useState([]);
  const [selectedTicket, setSelectedTicket] = useState(null);
  const [quantity, setQuantity] = useState(null);
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

  const handleTicketSubmit = (e) => {
    e.preventDefault();

    const purchaseRequest = JSON.stringify({
      ticket_id: selectedTicket,
      game_id: id,
      quantity: quantity,
      user_id: loggedUser.id,
    });

    const token = localStorage.getItem("accessToken");

    var config = {
      method: "post",
      url: "http://localhost:8080/purchases",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      data: purchaseRequest,
    };

    axios(config)
      .then(function (response) {
        console.log("Response: ", response);
      })
      .catch(function (error) {
        if (error.response) {
          console.log(error.message);
        }
      });
  };

  console.log(localStorage.getItem("accessToken"));

  return (
    <>
      {game && (
        <>
          <div className="content-details">
            <div className="details">
              <div>
                <span className="details-title">Date:</span> {game.date}
              </div>
              <div>
                <span className="details-title">Home team:</span>{" "}
                {game.home_team.full_name}
              </div>
              <div>
                <span className="details-title">Visitor team:</span>{" "}
                {game.visitor_team.full_name}
              </div>
            </div>

            <div className="list-container">
              <button className="button-big" onClick={getTickets}>
                See tickets
              </button>
              {tickets && (
                <TicketsList
                  tickets={tickets}
                  updateSelectedTicketProps={setSelectedTicket}
                />
              )}
              <form onSubmit={handleTicketSubmit}>
                <div>
                  <label htmlFor="quantity">Quantity:</label>
                  <input
                    type="number"
                    id="quantity"
                    name="qunatity"
                    min="1"
                    value={quantity}
                    onChange={(e) => setQuantity(e.target.value)}
                  />
                  <button type="submit">Buy tickets</button>
                </div>
              </form>
            </div>
          </div>
        </>
      )}
    </>
  );
};

export default GameDetails;
