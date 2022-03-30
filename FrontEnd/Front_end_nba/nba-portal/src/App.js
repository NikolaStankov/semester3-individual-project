import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.css";
import { Nav, Navbar } from "react-bootstrap";
import { IoBasketballOutline } from "react-icons/io5";
//import background from "./images/nba-background.jpg";
import "./App.css";
import Home from "./pages/Home/Home";
import Games from "./pages/Games/Games";
import Tickets from "./pages/Tickets/Tickets";
import GameDetails from "./pages/Games/GameDetails";

function App() {
  /*const [teams, setTeams] = useState();
  const [pageCount, setPageCount] = useState(1);

  var axios = require("axios").default;

  const getPlayers = () => {
    setPageCount(pageCount + 1);
    console.log(pageCount);

    var options = {
      method: "GET",
      url: "https://free-nba.p.rapidapi.com/players",
      params: { page: pageCount, per_page: "25" },
      headers: {
        "x-rapidapi-host": "free-nba.p.rapidapi.com",
        "x-rapidapi-key": "d5e4bbdd15msh4006a737ac378b4p14971ajsnfea34a9968a3",
      },
    };

    axios
      .request(options)
      .then(function (response) {
        console.log(response.data);
      })
      .catch(function (error) {
        console.error(error);
      });
  };*/

  return (
    <>
      <Navbar bg="dark" variant="dark" fixed="top" expand="sm">
        <Navbar.Brand>
          <IoBasketballOutline
            style={{ color: "#ed8021", fontSize: "40px", marginInline: "10px" }}
          />
        </Navbar.Brand>
        <Navbar.Toggle />
        <Navbar.Collapse>
          <Nav>
            <Nav.Link href="/" className="nav-link">
              Home
            </Nav.Link>
            <Nav.Link href="http://localhost:3000/games" className="nav-link">
              Games
            </Nav.Link>
            <Nav.Link href="http://localhost:3000/teams" className="nav-link">
              Teams
            </Nav.Link>
            <Nav.Link href="http://localhost:3000/tickets" className="nav-link">
              Tickets
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
      <div className="content">
        <Router>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route exact path="/games" element={<Games />} />
            <Route path="/games/:id" element={<GameDetails />} />
            <Route path="/tickets" element={<Tickets />} />
          </Routes>
        </Router>
      </div>
    </>
  );
}

export default App;
