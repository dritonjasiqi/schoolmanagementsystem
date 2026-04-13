import { NavLink } from 'react-router-dom';
import { Collapse } from 'react-bootstrap';
import { useState } from 'react';
import { Speedometer2, People, Gear, BoxSeam, BarChart, Circle } from 'react-bootstrap-icons';

export default function Sidebar({ isOpen, closeMobile }) {
  const [openMenus, setOpenMenus] = useState({});

  const toggleMenu = (menu) => {
    setOpenMenus(prev => ({ ...prev, [menu]: !prev[menu] }));
  };

  return (
    <div className={`sidebar ${isOpen ? 'open' : 'closed'}`}>
      <div className="d-flex flex-column py-3">
        
        {/* Normal Links */}
        <NavLink to="/" className="nav-link" end onClick={closeMobile}>
          <Speedometer2 /> Dashboard
        </NavLink>
        <NavLink to="/users" className="nav-link" onClick={closeMobile}>
          <People /> Users
        </NavLink>
        
        {/* Dropdown 1 */}
        <div 
          className="nav-link dropdown-toggle mt-2" 
          onClick={() => toggleMenu('products')}
        >
          <BoxSeam /> Products
        </div>
        <Collapse in={openMenus['products']}>
          <div className="ps-4 pe-2">
            <NavLink to="/products/list" className="nav-link small py-2 mt-1" onClick={closeMobile}>
              <Circle size={8} /> Product List
            </NavLink>
            <NavLink to="/products/add" className="nav-link small py-2" onClick={closeMobile}>
              <Circle size={8} /> Add Product
            </NavLink>
          </div>
        </Collapse>

        {/* Dropdown 2 */}
        <div 
          className="nav-link dropdown-toggle mt-1" 
          onClick={() => toggleMenu('reports')}
        >
          <BarChart /> Reports
        </div>
        <Collapse in={openMenus['reports']}>
          <div className="ps-4 pe-2">
            <NavLink to="/reports/sales" className="nav-link small py-2 mt-1" onClick={closeMobile}>
              <Circle size={8} /> Sales Data
            </NavLink>
            <NavLink to="/reports/traffic" className="nav-link small py-2" onClick={closeMobile}>
              <Circle size={8} /> Web Traffic
            </NavLink>
          </div>
        </Collapse>

        {/* Normal Link */}
        <NavLink to="/settings" className="nav-link mt-2" onClick={closeMobile}>
          <Gear /> Settings
        </NavLink>

      </div>
    </div>
  );
}