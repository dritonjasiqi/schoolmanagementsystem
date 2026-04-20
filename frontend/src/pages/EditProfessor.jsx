import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Card, Form, Row, Col, Button, ToggleButtonGroup, ToggleButton } from 'react-bootstrap';
import { PersonLinesFill, Person, Envelope, Hash, CalendarDate, JournalBookmark } from 'react-bootstrap-icons';

const moduleOptions = [
    'Mathematics',
    'Computer Science',
    'Physics',
    'Chemistry',
    'Biology',
    'English',
    'History',
    'Design'
];

export default function EditProfessor() {
    // Extract the ID from the URL (e.g., /edit-professor/1)
    const { id } = useParams();
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: '',
        lastName: '',
        email: '',
        idEmail: '',
        dateOfBirth: '',
        modules: []
    });

    // Simulate fetching existing data when the component loads
    useEffect(() => {
        // In a real app, you would fetch data from your API using the 'id'
        // fetch(`/api/professors/${id}`).then(...)

        // Simulating the data from your table row:
        // <tr><td>johndoe@example.com</td><td>John Doe</td><td>1980-01-01</td>...
        const fetchedData = {
            name: 'John',
            lastName: 'Doe',
            email: 'johndoe@example.com',
            idEmail: 'jdoe@institution.edu',
            dateOfBirth: '1980-01-01',
            modules: ['Computer Science', 'Mathematics'] // Simulated existing modules
        };

        setFormData(fetchedData);
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value
        }));
    };

    const handleModulesChange = (val) => {
        setFormData((prev) => ({
            ...prev,
            modules: val
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(`Updated Professor (ID: ${id}) Data:`, formData);
        // Add API PUT/PATCH logic here, then redirect back
    };
        // navigate('/professors');
    return (
        <div className="py-3">
            <div className="mb-4">
                <h2 className="fw-bold mb-2 text-primary d-flex align-items-center">
                    <PersonLinesFill className="me-2" size={32} /> Edit Professor Profile
                </h2>
                <p className="text-muted fs-5">
                    Update the information for professor ID: <strong>{id}</strong>
                </p>
            </div>

            <Card className="border-0 shadow-sm rounded-4">
                <Card.Body className="p-4 p-md-5 bg-white">
                    <Form onSubmit={handleSubmit}>
                        <Row className="g-4">

                            <Col xs={12} md={6}>
                                <Form.Group controlId="name">
                                    <Form.Label className="fw-semibold">
                                        <Person className="text-primary me-2" />Name
                                    </Form.Label>
                                    <Form.Control
                                        type="text"
                                        name="name"
                                        value={formData.name}
                                        onChange={handleChange}
                                        className="py-2 bg-light border-0"
                                        required
                                    />
                                </Form.Group>
                            </Col>

                            <Col xs={12} md={6}>
                                <Form.Group controlId="lastName">
                                    <Form.Label className="fw-semibold">
                                        <Person className="text-primary me-2" />Last Name
                                    </Form.Label>
                                    <Form.Control
                                        type="text"
                                        name="lastName"
                                        value={formData.lastName}
                                        onChange={handleChange}
                                        className="py-2 bg-light border-0"
                                        required
                                    />
                                </Form.Group>
                            </Col>

                            <Col xs={12} md={6}>
                                <Form.Group controlId="email">
                                    <Form.Label className="fw-semibold">
                                        <Envelope className="text-primary me-2" />Email Address
                                    </Form.Label>
                                    <Form.Control
                                        type="email"
                                        name="email"
                                        value={formData.email}
                                        onChange={handleChange}
                                        className="py-2 bg-light border-0"
                                        required
                                    />
                                </Form.Group>
                            </Col>

                            <Col xs={12} md={6}>
                                <Form.Group controlId="dateOfBirth">
                                    <Form.Label className="fw-semibold">
                                        <CalendarDate className="text-primary me-2" />Date of Birth
                                    </Form.Label>
                                    <Form.Control
                                        type="date"
                                        name="dateOfBirth"
                                        value={formData.dateOfBirth}
                                        onChange={handleChange}
                                        className="py-2 bg-light border-0"
                                        required
                                    />
                                </Form.Group>
                            </Col>

                            <Col xs={12}>
                                <Form.Group controlId="modules">
                                    <Form.Label className="fw-semibold mb-3">
                                        <JournalBookmark className="text-primary me-2" />Assigned Modules
                                    </Form.Label>
                                    <div className="d-block bg-light p-3 rounded-3">
                                        <ToggleButtonGroup
                                            type="checkbox"
                                            value={formData.modules}
                                            onChange={handleModulesChange}
                                            className="d-flex flex-wrap gap-2"
                                        >
                                            {moduleOptions.map((module) => (
                                                <ToggleButton
                                                    key={module}
                                                    id={`module-${module}`}
                                                    value={module}
                                                    variant={formData.modules.includes(module) ? 'primary' : 'outline-secondary'}
                                                    className="rounded-pill px-4 py-2 border-0 shadow-sm"
                                                    style={{ transition: 'all 0.2s ease-in-out' }}
                                                >
                                                    {module}
                                                </ToggleButton>
                                            ))}
                                        </ToggleButtonGroup>
                                    </div>
                                    <Form.Text className="text-muted mt-2">
                                        Tap to add or remove modules.
                                    </Form.Text>
                                </Form.Group>
                            </Col>

                            <Col xs={12} className="pt-4 mt-2 border-top d-flex justify-content-between">
                                <Button
                                    variant="outline-secondary"
                                    size="lg"
                                    className="px-4 rounded-pill shadow-sm fw-bold"
                                    onClick={() => navigate(-1)}
                                >
                                    Cancel
                                </Button>
                                <Button
                                    type="submit"
                                    variant="success"
                                    size="lg"
                                    className="px-5 rounded-pill shadow-sm fw-bold"
                                >
                                    Save Changes
                                </Button>
                            </Col>

                        </Row>
                    </Form>
                </Card.Body>
            </Card>
        </div>
    );
}