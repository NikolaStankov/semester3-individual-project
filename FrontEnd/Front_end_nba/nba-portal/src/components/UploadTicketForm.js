import { useState } from "react";
import useFetch from "../custom-hooks/useFetch";

const UploadTicketForm = () => {
  const [ticketType, setTicketType] = useState("");
  const [price, setPrice] = useState();
  const [specification, setSpecification] = useState();

  const { data: types } = useFetch("http://localhost:8080/tickets/types");

  return (
    <div>
      <form>
        <div className="input-container">
          <label>Ticket type </label>
          <select
            value={ticketType}
            onChange={(e) => setTicketType(e.target.value)}
          >
            <option value={""}></option>
            {types && types.map((type) => <option value={type}>{type}</option>)}
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
      </form>
      <div>Ticket type: {ticketType}</div>
      <div>Price: {price}</div>
      <div>Specification: {specification}</div>
    </div>
  );
};

export default UploadTicketForm;
