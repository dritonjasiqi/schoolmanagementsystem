import { Card, Table } from 'react-bootstrap';

export default function Users() {
  return (
    <div>
      <h3 className="mb-4 fw-bold">User Management</h3>
      <Card className="border-0 shadow-sm rounded-3">
        <Card.Body>
          <Table responsive hover className="mb-0">
            <thead>
              <tr><th>ID</th><th>Name</th><th>Email</th><th>Role</th></tr>
            </thead>
            <tbody>
              <tr><td>1</td><td>John Doe</td><td>john@example.com</td><td>Admin</td></tr>
              <tr><td>2</td><td>Jane Smith</td><td>jane@example.com</td><td>Editor</td></tr>
            </tbody>
          </Table>
        </Card.Body>
      </Card>
    </div>
  );
}