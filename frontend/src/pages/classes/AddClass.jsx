import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';

export default function AddClass() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({ name: '', price: '', professorId: '' });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Submitting new class:", formData);
    navigate('/classes'); 
  };

  return (
    <div className="bg-light min-vh-100 py-5 d-flex align-items-center">
      <div className="container" style={{ maxWidth: '600px' }}>
        <div className="card shadow-sm border-0 rounded-4 p-4 p-md-5">
          <h3 className="fw-bold text-dark mb-4">Add New Class</h3>
          
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label className="form-label fw-semibold">Class Name</label>
              <input type="text" className="form-control form-control-lg bg-light" name="name" value={formData.name} onChange={handleChange} required placeholder="e.g. Data Structures" />
            </div>

            <div className="mb-3">
              <label className="form-label fw-semibold">Price ($)</label>
              <input type="number" step="0.01" className="form-control form-control-lg bg-light" name="price" value={formData.price} onChange={handleChange} required placeholder="e.g. 49.99" />
            </div>

            <div className="mb-4">
              <label className="form-label fw-semibold">Professor ID</label>
              <input type="text" className="form-control form-control-lg bg-light" name="professorId" value={formData.professorId} onChange={handleChange} required placeholder="Enter UUID or ID" />
            </div>

            <div className="d-flex gap-2">
              <button type="submit" className="btn btn-primary btn-lg flex-grow-1 fw-semibold shadow-sm">
                Save Class
              </button>
              <Link to="/classes" className="btn btn-outline-secondary btn-lg fw-semibold">
                Cancel
              </Link>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}