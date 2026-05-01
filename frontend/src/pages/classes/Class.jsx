import React, { useState } from 'react';
import { Card, Table } from 'react-bootstrap';

export default function Class() {
    const [classes, setClasses] = useState([
        { id: '1', name: 'Advanced Java', price: 59.99, professorId: 'p101' },
        { id: '2', name: 'Cybersecurity 101', price: 49.99, professorId: 'p102' }
    ]);

    return (
        <div>
            <h3 className="mb-4 fw-bold">Manage Classes</h3>
            <Card className="border rounded-3" style={{ boxShadow: '0 8px 30px rgba(0,0,0,0.08)' }}>
                <Card.Body>
                    <Table responsive hover className="mb-0">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Price ($)</th>
                                <th>Professor ID</th>
                                <th>Edit Class</th>
                                <th>Remove Class</th>
                            </tr>
                        </thead>
                        <tbody>
                            {classes.map((cls) => (
                                <tr key={cls.id}>
                                    <td>{cls.id}</td>
                                    <td>{cls.name}</td>
                                    <td>{cls.price}</td>
                                    <td>{cls.professorId}</td>
                                    <td>
                                        <a className='btn btn-success' href={`/userpages/editClass/${cls.id}`}>
                                            Edit
                                        </a>
                                    </td>
                                    <td>
                                        <a className='btn btn-danger' href={`/userpages/removeClass/${cls.id}`}>
                                            Remove
                                        </a>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        </div>
    );
}