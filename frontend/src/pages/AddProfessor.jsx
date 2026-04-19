import { useState } from 'react';
import { Card, Form, Row, Col, Button, ToggleButton, ToggleButtonGroup } from 'react-bootstrap';
import { Person, Envelope, Key, Hash, CalendarDate, JournalBookmark,PersonPlus } from 'react-bootstrap-icons';

const moduleOptions = [
  'Mathematics',
  'Computer Science',
  'Physics',
  'Chemistry',
  'Biology',
  'English',
  'History',
  'Design',
];

export default function AddProfessor() {
  const [formData, setFormData] = useState({
    name: '',
    lastName: '',
    email: '',
    password: '',
    idEmail: '',
    dateOfBirth: '',
    modules: [],
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  // Handles the ToggleButtonGroup array of selected values
  const handleModulesChange = (val) => {
    setFormData((prev) => ({
      ...prev,
      modules: val,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Professor submitted:', formData);
  };

  return (
    <div className="py-2">
      <div className="mb-4">
        <h1 className="fw-bold mb-2 text-primary d-flex align-items-center justify-content-center gap-2">
          <PersonPlus size={40} /> Add Professor
        </h1>
        <p className="text-muted fs-5 mb-0 text-center">
          Create a new professor profile using the form below.
        </p>
      </div>

      <Card className="border-0 rounded-4" style={{ boxShadow: '0 8px 30px rgba(0,0,0,0.08)' }}>
        <Card.Body className="p-4 p-md-5">
          <Form onSubmit={handleSubmit}>
            <Row className="g-4">
              <Col xs={12} md={6}>
                <Form.Group controlId="name">
                  <Form.Label className="fw-semibold d-flex align-items-center gap-2">
                    <Person className="text-primary" /> Name
                  </Form.Label>
                  <Form.Control
                    type="text"
                    name="name"
                    placeholder="Enter first name"
                    value={formData.name}
                    onChange={handleChange}
                    className="py-2 shadow-sm border-0 bg-light"
                  />
                </Form.Group>
              </Col>

              <Col xs={12} md={6}>
                <Form.Group controlId="lastName">
                  <Form.Label className="fw-semibold d-flex align-items-center gap-2">
                    <Person className="text-primary" /> Last Name
                  </Form.Label>
                  <Form.Control
                    type="text"
                    name="lastName"
                    placeholder="Enter last name"
                    value={formData.lastName}
                    onChange={handleChange}
                    className="py-2 shadow-sm border-0 bg-light"
                  />
                </Form.Group>
              </Col>

              <Col xs={12} md={6}>
                <Form.Group controlId="email">
                  <Form.Label className="fw-semibold d-flex align-items-center gap-2">
                    <Envelope className="text-primary" /> Email
                  </Form.Label>
                  <Form.Control
                    type="email"
                    name="email"
                    placeholder="Enter email address"
                    value={formData.email}
                    onChange={handleChange}
                    className="py-2 shadow-sm border-0 bg-light"
                  />
                </Form.Group>
              </Col>

              <Col xs={12} md={6}>
                <Form.Group controlId="password">
                  <Form.Label className="fw-semibold d-flex align-items-center gap-2">
                    <Key className="text-primary" /> Password
                  </Form.Label>
                  <Form.Control
                    type="password"
                    name="password"
                    placeholder="Enter password"
                    value={formData.password}
                    onChange={handleChange}
                    className="py-2 shadow-sm border-0 bg-light"
                  />
                </Form.Group>
              </Col>

              <Col xs={12} md={6}>
                <Form.Group controlId="dateOfBirth">
                  <Form.Label className="fw-semibold d-flex align-items-center gap-2">
                    <CalendarDate className="text-primary" /> Date of Birth
                  </Form.Label>
                  <Form.Control
                    type="date"
                    name="dateOfBirth"
                    value={formData.dateOfBirth}
                    onChange={handleChange}
                    className="py-2 shadow-sm border-0 bg-light"
                  />
                </Form.Group>
              </Col>

              <Col xs={12}>
                <Form.Group controlId="modules">
                  <Form.Label className="fw-semibold d-flex align-items-center gap-2 mb-3">
                    <JournalBookmark className="text-primary" /> Select Modules
                  </Form.Label>
                  
                  {/* Phone-friendly touch toggle buttons */}
                  <div className="d-block bg-light p-3 rounded-3 shadow-sm">
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
                  <Form.Text className="text-muted mt-2 d-block">
                    Tap to select or deselect modules.
                  </Form.Text>
                </Form.Group>
              </Col>

              <Col xs={12} className="pt-4 mt-2 border-top">
                <div className="d-flex justify-content-end">
                  <Button 
                    type="submit" 
                    variant="primary" 
                    size="lg"
                    className="px-5 shadow-sm rounded-pill fw-bold"
                  >
                    Save Professor
                  </Button>
                </div>
              </Col>
            </Row>
          </Form>
        </Card.Body>
      </Card>
    </div>
  );
}