import React, { useState } from "react";
import { Navbar as BootstrapNavbar, Container, Nav, Button } from "react-bootstrap";
import { useLocation } from "react-router-dom";

export default function Navbar() {
    const [isNavCollapsed, setIsNavCollapsed] = useState(true);
    const location = useLocation();
    const currentPath = location.pathname;

    const handleNavCollapse = () => setIsNavCollapsed(!isNavCollapsed);

    // Function that adds an underline and turns the active page text blue
    const getActiveStyle = (path) => {
        return currentPath === path 
            ? "text-primary text-decoration-underline" 
            : "text-dark";
    };

    return (
        <BootstrapNavbar 
            expand="lg" 
            fixed="top" 
            className="shadow-sm py-3 bg-white" 
            expanded={!isNavCollapsed}
        >
            <Container>
                <BootstrapNavbar.Brand href="/" className="d-flex align-items-center fw-bold text-primary">
                    <i className="bi bi-mortarboard-fill fs-3 me-2"></i>
                    AuroraSchool
                </BootstrapNavbar.Brand>
                
                <BootstrapNavbar.Toggle 
                    className="border-0" 
                    aria-controls="navbarNav" 
                    onClick={handleNavCollapse} 
                />
                
                <BootstrapNavbar.Collapse id="navbarNav">
                    <Nav className="ms-auto align-items-center">
                        <Nav.Item className="me-3">
                            <Nav.Link href="/" className={`fw-semibold ${getActiveStyle("/")}`}>
                                <i className="bi bi-house-door me-1"></i>Home
                            </Nav.Link>
                        </Nav.Item>
                        
                        <Nav.Item className="me-3">
                            <Nav.Link href="/aboutus" className={`fw-semibold ${getActiveStyle("/aboutus")}`}>
                                <i className="bi bi-info-circle me-1"></i>About
                            </Nav.Link>
                        </Nav.Item>
                        
                        <Nav.Item className="me-3">
                            <Nav.Link href="/pricing" className={`fw-semibold ${getActiveStyle("/pricing")}`}>
                                <i className="bi bi-tags me-1"></i>Pricing
                            </Nav.Link>
                        </Nav.Item>
                        
                        <Nav.Item className="me-2 mt-2 mt-lg-0">
                            <Button href="/login" variant="outline-primary" className="px-4 py-2 btn-modern fw-semibold shadow-sm">
                                <i className="bi bi-person-circle me-2"></i>Login
                            </Button>
                        </Nav.Item>

                        <Nav.Item className="mt-2 mt-lg-0">
                            <Button href="/signup" variant="primary" className="px-4 py-2 btn-modern fw-semibold shadow-sm">
                                Sign Up
                            </Button>
                        </Nav.Item>
                    </Nav>
                </BootstrapNavbar.Collapse>
            </Container>
        </BootstrapNavbar>
    );
}