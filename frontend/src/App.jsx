import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import Dashboard from './pages/Dashboard';
import Users from './pages/Users';
import Settings from './pages/Settings';

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Dashboard />} />
          <Route path="users" element={<Users />} />
          <Route path="settings" element={<Settings />} />
          {/* Placeholder routes for dropdown sub-items */}
          <Route path="products/*" element={<div className="p-4">Products Module</div>} />
          <Route path="reports/*" element={<div className="p-4">Reports Module</div>} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}