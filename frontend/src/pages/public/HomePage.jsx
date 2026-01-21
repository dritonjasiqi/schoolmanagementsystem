import {useState} from "react";

import "../../assets/styles/styles.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import MenuIcon from "@mui/icons-material/Menu";
import MenuOpenIcon from "@mui/icons-material/MenuOpen";


function HomePage() {
  const [isExpanded, setIsExpanded] = useState(false);
  const handleToggle = () => {
    setIsExpanded(!isExpanded);
  };
  return (
      <div className="wrapper">
        <aside id="sidebar" className={isExpanded ? "expand" : ""}>
          <div className="d-flex justify-content-between p-4">
            <div className="sidebarLogo">
              <a href="/">Aurora School</a>
            </div> {/* End of sidebar-logo div */}
            <button className="toggleBtn border-0" onClick={handleToggle}>
              {isExpanded ? <MenuOpenIcon fontSize="medium"/> : <MenuIcon fontSize="medium" />}
            </button> {/* End of toggle button */}
          </div> {/* End of top div */}
          <ul className="sidebarNav">
            <li className="sidebarNavItem">
              <a href="/" className="sidebarLink d-flex align-items-start">
                <AccountCircleIcon fontSize="medium" />
                <span>Profile</span>
              </a>
            </li> {/* End of sidebarNavItem li */}
            <li className="sidebarNavItem">
              <a href="/" className="sidebarLink d-flex align-items-start">
                <AccountCircleIcon fontSize="medium" />
                <span>Profilec</span>
              </a>
            </li> {/* End of sidebarNavItem li */}
            <li className="sidebarNavItem">
              <a href="/" className="sidebarLink collapsed has-dropdown d-flex align-items-start"  data-bs-toggle="collapse" data-bs-target="#auth" aria-expanded="false" aria-controls="auth">
                <AccountCircleIcon fontSize="medium" />
                <span>Profi</span>
              </a>
              <ul id="auth" className="sidebarDropdown list-unstyled collapse" data-bs-parent="#sidebar">
                <li className="sidebarNavItem">
                  <a href="/" className="sidebarLink d-flex align-items-start">
                  <span>d</span>
                  </a>
                </li>
                <li className="sidebarNavItem">
                  <a href="/" className="sidebarLink d-flex align-items-start">
                  <span>e</span>
                  </a>
                </li>
              </ul>
            </li> {/* End of sidebarNavItem li */}
          </ul> {/* End of sidebarNav ul */}
        </aside> {/* End of sidebar aside */}
        <div className="main">
          
        </div> {/* End of main div */}
      </div> /* End of wrapper div */
  );
}

export default HomePage;