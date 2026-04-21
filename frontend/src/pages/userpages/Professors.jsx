import { Card, Table } from 'react-bootstrap';

export default function Professors() {
    return (
        <div>
            <h3 className="mb-4 fw-bold">Professor Management</h3>
            <Card className="border rounded-3" style={{ boxShadow: '0 8px 30px rgba(0,0,0,0.08)' }}>
                <Card.Body>
                    <Table responsive hover className="mb-0">
                        <thead>
                            <tr><th>Email</th><th>Name</th><th>Birthday</th><th>Edit User</th><th>Remove User</th></tr>
                        </thead>
                        <tbody>
                            <tr><td>johndoe@example.com</td><td>John Doe</td><td>1980-01-01</td><td><a className='btn btn-success' href="/userpages/editProfessor/1">Edit</a></td><td><a className='btn btn-danger' href="/userpages/removeProfessor/1">Remove</a></td></tr>
                            <tr><td>janesmith@example.com</td><td>Jane Smith</td><td>1985-05-05</td><td><a className='btn btn-success' href="/userpages/editProfessor/2">Edit</a></td><td><a className='btn btn-danger' href="/userpages/removeProfessor/2">Remove</a></td></tr>
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        </div>
    );
}