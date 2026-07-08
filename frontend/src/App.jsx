import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import Dashboard from './pages/Dashboard';
import Users from './pages/userpages/Users';
import Settings from './pages/Settings';
import Home from './pages/Home';
import Login from './pages/Login';
import Signup from './pages/Signup';
import Pricing from './pages/Pricing';
import AboutUs from './pages/AboutUs';
import AddProfessor from './pages/userpages/AddProfessor';
import Professors from './pages/userpages/Professors';
import EditProfessor from './pages/userpages/EditProfessor';
import RemoveProfessor from './pages/userpages/RemoveProfessor';
import Class from './pages/classes/Class';
import EditClass from './pages/classes/EditClass';
import RemoveClass from './pages/classes/RemoveClass';
import AddClass from './pages/classes/AddClass';
import ProtectedRoute from './components/ProtectedRoute';

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/pricing" element={<Pricing />} />
        <Route path="/aboutus" element={<AboutUs />} />
        <Route element={<ProtectedRoute />}>
          <Route path="/dashboard" element={<Layout />}>
            <Route index element={<Dashboard />} />
            <Route path="/dashboard/userpages/users" element={<Users />} />
            <Route path="/dashboard/settings" element={<Settings />} />
            <Route path="/dashboard/products/*" element={<div className="p-4">Products Module</div>} />
            <Route path="/dashboard/reports/*" element={<div className="p-4">Reports Module</div>} />
            <Route path="/dashboard/userpages/addProfessor" element={<AddProfessor />} />
            <Route path="/dashboard/userpages/professors" element={<Professors />} />
            <Route path="/dashboard/classes/classes" element={<Class />} />
            <Route path="/dashboard/classes/addClass" element={<AddClass />} />
          </Route>
          <Route path="/classes/editClass/:id" element={<EditClass />} />
          <Route path="/classes/removeClass/:id" element={<RemoveClass />} />
          <Route path="/userpages/editProfessor/:id" element={<EditProfessor />} />
          <Route path="/userpages/removeProfessor/:id" element={<RemoveProfessor />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}