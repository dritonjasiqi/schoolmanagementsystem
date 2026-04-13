import { useState, useEffect } from 'react';
import { Outlet } from 'react-router-dom';
import Topbar from './Topbar';
import Sidebar from './Sidebar';

export default function Layout() {
  const [isOpen, setIsOpen] = useState(window.innerWidth >= 992);

  // Allow charts to naturally resize when the sidebar animation finishes
  useEffect(() => {
    const handleResize = () => window.dispatchEvent(new Event('resize'));
    const timer = setTimeout(handleResize, 350); 
    return () => clearTimeout(timer);
  }, [isOpen]);

  const toggleSidebar = () => setIsOpen(!isOpen);
  const closeSidebarOnMobile = () => {
    if (window.innerWidth < 992) setIsOpen(false);
  };

  return (
    <div>
      <Topbar toggleSidebar={toggleSidebar} />
      <Sidebar isOpen={isOpen} closeMobile={closeSidebarOnMobile} />
      
      {/* Dimmed backdrop for mobile overlay */}
      <div 
        className={`sidebar-backdrop ${isOpen ? 'd-block' : 'd-none'} d-lg-none`}
        onClick={() => setIsOpen(false)}
      ></div>

      <main className={`main-content ${isOpen ? 'sidebar-open' : 'sidebar-closed'}`}>
        <div className="container-fluid p-4">
          <Outlet />
        </div>
      </main>
    </div>
  );
}