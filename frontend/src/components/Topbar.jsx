import { Navbar, Nav, NavDropdown, Button } from 'react-bootstrap';
import { List, PersonCircle } from 'react-bootstrap-icons';

export default function Topbar({ toggleSidebar }) {
  return (
    <Navbar bg="white" className="topbar shadow-sm px-3 border-bottom">
      <div className="d-flex align-items-center w-100">
        <Button variant="light" className="me-3 border-0 bg-transparent" onClick={toggleSidebar}>
          <List size={26} />
        </Button>
        <Navbar.Brand href="/" className="fw-bold text-primary m-0">DashKit</Navbar.Brand>
        
        <Nav className="ms-auto">
          <NavDropdown 
            title={<PersonCircle size={28} className="text-secondary" />} 
            id="profile-dropdown" 
            align="end"
          >
            <NavDropdown.Item href="#profile">Profile</NavDropdown.Item>
            <NavDropdown.Item href="#settings">Settings</NavDropdown.Item>
            <NavDropdown.Divider />
            <NavDropdown.Item href="#logout">Logout</NavDropdown.Item>
          </NavDropdown>
        </Nav>
      </div>
    </Navbar>
  );
}