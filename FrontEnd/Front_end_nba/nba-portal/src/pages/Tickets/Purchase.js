import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import TicketsList from "../../components/TicketsList";

const Purchase = (props) => {
  let { gameId } = useParams();
  let navigate = useNavigate();

  const [tickets, setTickets] = useState([]);
  const [selectedTicketId, setSelectedTicketId] = useState();
  const [quantity, setQuantity] = useState(1);

  const getTickets = () => {
    const tickets_url = "http://localhost:8080/tickets";
    const token = localStorage.getItem("accessToken");

    axios
      .get(tickets_url, { headers: { Authorization: `Bearer ${token}` } })
      .then((response) => {
        const responseData = response.data;
        setTickets(responseData);
      })
      .catch(function (error) {
        if (error.response) {
          console.log(error.response.errorMessage);
        }
      });
  };

  useEffect(() => {
    getTickets();
  }, []);

  const handleTicketSubmit = (e) => {
    console.log("Submitted the form");
    e.preventDefault();
    const purchaseRequest = JSON.stringify({
      ticket_id: selectedTicketId,
      game_id: gameId,
      quantity: quantity,
      user_id: props.loggedUser.id,
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
        navigate("/profile");
      })
      .catch(function (error) {
        if (error.response) {
          console.log(error.message);
        }
      });
  };

  return (
    <div className="purchase-form">
      {tickets && (
        <form onSubmit={handleTicketSubmit}>
          <h2>Select a ticket: </h2>
          <TicketsList
            tickets={tickets}
            selectedTicketId={selectedTicketId}
            setSelectedTicketId={setSelectedTicketId}
          />
          <label htmlFor="quantity">Quantity:</label>
          <input
            type="number"
            id="quantity"
            name="qunatity"
            min="1"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
          />
          <button
            type="submit"
            className="standard-button-red"
            style={{ width: "60%", marginTop: "20px", marginBottom: "20px" }}
          >
            Buy ticket(s)
          </button>
        </form>
      )}
    </div>
  );
};

export default Purchase;
