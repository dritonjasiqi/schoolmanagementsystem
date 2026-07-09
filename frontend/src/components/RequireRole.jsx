import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';

export default function RequireRole({ allowedRoles }) {
    const userString = localStorage.getItem('user');
    if (!userString) {
        return <Navigate to="/login" replace />;
    }
    const user = JSON.parse(userString);
    if (!allowedRoles.includes(user.role)) {
        return <Navigate to="/dashboard" replace />;
    }
    return <Outlet />;
}