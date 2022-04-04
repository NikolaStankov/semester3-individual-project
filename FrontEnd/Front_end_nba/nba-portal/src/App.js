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
import Teams from "./pages/Teams/Teams"

function App() {

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
            <Route path="/teams" element={<Teams />} />
          </Routes>
        </Router>
      </div>
    </>
  );
}

export default App;
