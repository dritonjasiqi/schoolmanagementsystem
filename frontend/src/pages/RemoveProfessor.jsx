import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Card, Button } from 'react-bootstrap';
import { ExclamationTriangleFill } from 'react-bootstrap-icons';

export default function RemoveProfessor() {
    // Extract the ID from the URL (e.g., /remove-professor/1)
    const { id } = useParams();
    const navigate = useNavigate();

    const [professorName, setProfessorName] = useState('Loading...');

    // Simulate fetching existing data when the component loads
    useEffect(() => {
        // In a real app, you would fetch data from your API using the 'id'
        // fetch(`/api/professors/${id}`).then(...)
        
        // Simulating the data fetched from your backend
        const fetchedData = {
            id: id,
            name: 'John',
            lastName: 'Doe'
        };

        setProfessorName(`${fetchedData.name} ${fetchedData.lastName}`);
    }, [id]);

    const handleRemove = () => {
        console.log(`Deleting professor with ID: ${id}`);
        // Add your API DELETE logic here
        // fetch(`/api/professors/${id}`, { method: 'DELETE' }).then(() => {
        //     navigate('/professors');
        // });
        
        // Simulate redirecting back to the professor list after deletion
        navigate(-1); 
    };

    const handleCancel = () => {
        // Go back to the previous page without deleting
        navigate(-1);
    };

    return (
        <div className="py-3">
            <div className="mb-4">
                <h3 className="fw-bold mb-1">Remove Professor</h3>
                <p className="text-muted">Confirm deletion of professor profile.</p>
            </div>
            
            <Card className="border-0 rounded-4" style={{ boxShadow: '0 8px 30px rgba(0,0,0,0.08)' }}>
                <Card.Body className="p-5 text-center">
                    <ExclamationTriangleFill className="text-danger mb-3" size={60} />
                    
                    <h2 className='text-danger fw-bold mb-3'>
                        Are you sure you want to remove Professor {professorName}?
                    </h2>
                    
                    <p className="text-muted fs-5 mb-5">
                        This action cannot be undone. All data associated with ID <strong>{id}</strong> will be permanently deleted from the system.
                    </p>
                    
                    <div className="d-flex justify-content-center gap-4">
                        <Button 
                            variant="danger" 
                            size="lg" 
                            className="px-5 rounded-pill fw-bold shadow-sm" 
                            onClick={handleRemove}
                        >
                            Yes, Remove
                        </Button>
                        <Button 
                            variant="secondary" 
                            size="lg" 
                            className="px-5 rounded-pill fw-bold shadow-sm" 
                            onClick={handleCancel}
                        >
                            No, Keep
                        </Button>
                    </div>
                </Card.Body>
            </Card>
        </div>
    );
}