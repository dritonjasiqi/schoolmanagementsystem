import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import Dashboard from './pages/Dashboard';
import Users from './pages/Users';
import Settings from './pages/Settings';
import Home from './pages/Home';
import LoginAndSignup from './pages/LoginAndSignup';
import Pricing from './pages/Pricing';

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/dashboard" element={<Layout />}>
          <Route index element={<Dashboard />} />
          <Route path="/dashboard/users" element={<Users />} />
          <Route path="/dashboard/settings" element={<Settings />} />
          {/* Placeholder routes for dropdown sub-items */}
          <Route path="/dashboard/products/*" element={<div className="p-4">Products Module</div>} />
          <Route path="/dashboard/reports/*" element={<div className="p-4">Reports Module</div>} />
        </Route>
        <Route path="/login" element={<LoginAndSignup />} />
        <Route path="/pricing" element={<Pricing />} />
      </Routes>
    </BrowserRouter>
  );
}