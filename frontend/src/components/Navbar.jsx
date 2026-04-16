import React, {useState} from "react";

export default function Navbar(){
    const [isNavCollapsed, setIsNavCollapsed] = useState(true);
    const handleNavCollapse = () => setIsNavCollapsed(!isNavCollapsed);
    return(
        <nav className="navbar navbar-expand-lg fixed-top shadow-sm py-3">
                <div className="container">
                    <a className="navbar-brand d-flex align-items-center fw-bold text-primary" href="/">
                        <i className="bi bi-mortarboard-fill fs-3 me-2"></i>
                        AuroraSchool
                    </a>
                    <button className="navbar-toggler border-0" type="button" onClick={handleNavCollapse} aria-expanded={!isNavCollapsed} aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className={`${isNavCollapsed ? 'collapse' : ''} navbar-collapse`} id="navbarNav">
                        <ul className="navbar-nav ms-auto align-items-center">
                            <li className="nav-item me-3"><a className="nav-link text-dark fw-semibold" href="/"><i className="bi bi-house-door me-1"></i>Home</a></li>
                            <li className="nav-item me-3"><a className="nav-link text-dark fw-semibold" href="/aboutus"><i className="bi bi-info-circle me-1"></i>About</a></li>
                            <li className="nav-item me-3"><a className="nav-link text-dark fw-semibold" href="/pricing"><i className="bi bi-tags me-1"></i>Pricing</a></li>
                            <li className="nav-item mt-2 mt-lg-0">
                                <a className="btn btn-primary px-4 py-2 btn-modern fw-semibold shadow-sm" href="/login">
                                    <i className="bi bi-person-circle me-2"></i>Login / Sign Up
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
    );
}