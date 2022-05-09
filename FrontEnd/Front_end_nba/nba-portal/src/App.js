import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.css";
import "./App.css";
import NavBar from "./components/NavBar";
import Home from "./pages/Home/Home";
import Games from "./pages/Games/Games";
import Tickets from "./pages/Tickets/Tickets";
import GameDetails from "./pages/Games/GameDetails";
import Teams from "./pages/Teams/Teams";
import Players from "./pages/Players/Players";
import Login from "./pages/Login";
import Register from "./pages/Register";

function App() {
  const [loggedUser, setLoggedUser] = useState(null);

  

  return (
    <>
      <div className="content">
        <Router>
          <NavBar />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/games" element={<Games />} />
            <Route path="/games/:id" element={<GameDetails />} />
            <Route path="/tickets" element={<Tickets />} />
            <Route path="/teams" element={<Teams />} />
            <Route path="/players" element={<Players />} />
            <Route
              path="/login"
              element={<Login savedURL={window.location.pathname} />}
            />
            <Route path="/register" element={<Register />} />
          </Routes>
        </Router>
      </div>
    </>
  );
}

export default App;
