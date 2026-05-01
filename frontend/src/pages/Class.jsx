import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { PlusLg, PencilSquare, Trash } from 'react-bootstrap-icons';

export default function Class() {
  const [classes, setClasses] = useState([
    { id: 'c1', name: 'Advanced Java', price: 59.99, professorId: 'p101' },
    { id: 'c2', name: 'Cybersecurity 101', price: 49.99, professorId: 'p102' }
  ]);

  return (
    <div className="bg-light min-vh-100 py-5">
      <div className="container">
        <div className="d-flex justify-content-between align-items-center mb-4">
          <h2 className="fw-bold text-dark mb-0">Manage Classes</h2>
        </div>

        <div className="card shadow-sm border-0 rounded-4 overflow-hidden">
          <div className="table-responsive">
            <table className="table table-hover align-middle mb-0">
              <thead className="table-light">
                <tr>
                  <th className="py-3 px-4">ID</th>
                  <th className="py-3 px-4">Name</th>
                  <th className="py-3 px-4">Price ($)</th>
                  <th className="py-3 px-4">Professor ID</th>
                  <th className="py-3 px-4 text-center">Actions</th>
                </tr>
              </thead>
              <tbody>
                {classes.map((cls) => (
                  <tr key={cls.id}>
                    <td className="px-4 text-muted">{cls.id}</td>
                    <td className="px-4 fw-semibold">{cls.name}</td>
                    <td className="px-4 text-success fw-bold">{cls.price}</td>
                    <td className="px-4 text-muted">{cls.professorId}</td>
                    <td className="px-4 text-center">
                      <Link to={`/classes/edit/${cls.id}`} className="btn btn-sm btn-outline-primary me-2 d-inline-flex align-items-center">
                        <PencilSquare className="me-1" /> Edit
                      </Link>
                      <Link to={`/classes/delete/${cls.id}`} className="btn btn-sm btn-outline-danger d-inline-flex align-items-center">
                        <Trash className="me-1" /> Remove
                      </Link>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
            {classes.length === 0 && (
              <div className="text-center p-5 text-muted">No classes found.</div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}