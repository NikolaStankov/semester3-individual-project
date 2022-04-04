import { Nav, Navbar } from "react-bootstrap";
import { IoBasketballOutline } from "react-icons/io5";
import { BrowserRouter as Router, Link } from "react-router-dom";

const NavBar = () => {
  return (
    <Navbar bg="dark" variant="dark" fixed="top" expand="sm">
      <Navbar.Brand>
        <IoBasketballOutline
          style={{
            color: "#ed8021",
            fontSize: "40px",
            marginInline: "10px",
          }}
        />
      </Navbar.Brand>
      <Navbar.Toggle />
      <Navbar.Collapse>
        <Nav>
          <Nav.Link as={Link} to="/" className="nav-link">
            Home
          </Nav.Link>
          <Nav.Link as={Link} to="/games" className="nav-link">
            Games
          </Nav.Link>
          <Nav.Link as={Link} to="/teams" className="nav-link">
            Teams
          </Nav.Link>
          <Nav.Link as={Link} to="/tickets" className="nav-link">
            Tickets
          </Nav.Link>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default NavBar;
