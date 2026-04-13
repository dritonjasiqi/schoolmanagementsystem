import { Card, Form, Button } from 'react-bootstrap';

export default function Settings() {
  return (
    <div>
      <h3 className="mb-4 fw-bold">Settings</h3>
      <Card className="border-0 shadow-sm rounded-3">
        <Card.Body>
          <Form>
            <Form.Group className="mb-3">
              <Form.Label className="fw-semibold">Site Name</Form.Label>
              <Form.Control type="text" defaultValue="My Dashboard" />
            </Form.Group>
            <Form.Group className="mb-4">
              <Form.Check type="switch" label="Enable Email Notifications" defaultChecked />
            </Form.Group>
            <Button variant="primary" className="px-4">Save Changes</Button>
          </Form>
        </Card.Body>
      </Card>
    </div>
  );
}