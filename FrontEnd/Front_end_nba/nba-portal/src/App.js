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
import Logout from "./pages/Logout/Logout";
import TeamDetails from "./pages/Teams/TeamDetails";
import LiveScore from "./pages/LiveScore/LiveScore";
import AdminPanel from "./pages/AdminPanel/AdminPanel";
import Profile from "./pages/Profile/Profile";
import TeamEvents from "./pages/Tickets/TeamEvents";

function App() {
  const [loggedUser, setLoggedUser] = useState(null);

  return (
    <>
      <div className="content">
        <Router>
          <NavBar loggedUser={loggedUser} />
          <Routes>
            <Route path="/" element={<Home loggedUser={loggedUser} />} />
            <Route path="/games" element={<Games />} />
            <Route
              path="/games/:id"
              element={<GameDetails loggedUser={loggedUser} />}
            />
            <Route path="/tickets" element={<Tickets />} />
            <Route path="/teams" element={<Teams />} />
            <Route path="/teams/:teamId" element={<TeamDetails />} />
            <Route path="/players" element={<Players />} />
            <Route path="/liveSimulation" element={<LiveScore />} />
            <Route
              path="/login"
              element={<Login updateLoggedUserProps={setLoggedUser} />}
            />
            <Route
              path="/logout"
              element={<Logout updateLoggedUserProps={setLoggedUser} />}
            />
            <Route path="/register" element={<Register />} />
            <Route
              path="/adminPanel"
              element={<AdminPanel loggedUser={loggedUser} />}
            />
            <Route
              path="/profile"
              element={<Profile loggedUser={loggedUser} />}
            />
            <Route
              path="/teams/:teamId/events"
              element={<TeamEvents loggedUser={loggedUser} />}
            />
          </Routes>
        </Router>
      </div>
    </>
  );
}

export default App;
