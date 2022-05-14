import { Nav, Navbar } from "react-bootstrap";
import { IoBasketballOutline } from "react-icons/io5";
import { BrowserRouter as Router, Link } from "react-router-dom";

const NavBar = (props) => {
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
        <Nav className="container-fluid">
          <Nav.Link as={Link} to="/">
            Home
          </Nav.Link>
          <Nav.Link as={Link} to="/teams">
            Teams
          </Nav.Link>
          <Nav.Link as={Link} to="/games">
            Games
          </Nav.Link>
        </Nav>
        <Nav>
          {props.loggedUser ? (
            <Nav.Link as={Link} to="/logout" className="float-right">
              Logout
            </Nav.Link>
          ) : (
            <Nav.Link as={Link} to="/login" className="float-right">
              Login
            </Nav.Link>
          )}
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default NavBar;
