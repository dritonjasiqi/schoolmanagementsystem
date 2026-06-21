import React, { useState } from "react";
import { Container, Row, Col, Form, Button, Alert } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import { Google, Microsoft, Envelope, LockFill } from "react-bootstrap-icons";
import myApi from "../api/axiosConfig"; // Wichtig: Unser konfigurierter Axios-Client

export default function Login() {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const [errorMsg, setErrorMsg] = useState('');
    const [successMsg, setSuccessMsg] = useState('');

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault(); 

        setErrorMsg('');
        setSuccessMsg('');

        try {
            await myApi.post('/auth/login', {
                email: email,
                password: password
            });
            setSuccessMsg("Login successful! Redirecting to dashboard...");
            setTimeout(() => {
                navigate('/dashboard');
            }, 1500);

        } catch (error) {
            if (error.response && error.response.status === 401) {
                setErrorMsg("Invalid credentials. Please check your email and password.");
            } else {
                setErrorMsg("An error occurred during login. Please try again.");
            }
            console.error("Login error:", error);
        }
    };

    return (
        <div>
            <Navbar />
            <section className="py-5" style={{ minHeight: "80vh" }}>
                <Container className="py-5">
                    <Row className="justify-content-center">
                        <Col lg={6} md={8}>
                            <div className="text-center mb-4">
                                <h2 className="fw-bold">Welcome Back</h2>
                                <p className="text-muted">Login to access your dashboard.</p>
                            </div>

                            {/* Fehlermeldungen / Erfolgsmeldungen */}
                            {errorMsg && <Alert variant="danger">{errorMsg}</Alert>}
                            {successMsg && <Alert variant="success">{successMsg}</Alert>}

                            {/* Formular mit onSubmit */}
                            <Form className="shadow-sm p-4 p-md-5 rounded-4 border bg-light" onSubmit={handleSubmit}>

                                <Form.Group className="mb-3" controlId="email">
                                    <Form.Label className="fw-bold">
                                        <Envelope className="m-2 fs-5" />
                                        Email address
                                    </Form.Label>
                                    <Form.Control
                                        type="email"
                                        placeholder="Enter your email"
                                        value={email}
                                        onChange={(e) => setEmail(e.target.value)}
                                        required
                                    />
                                </Form.Group>

                                <Form.Group className="mb-4" controlId="password">
                                    <Form.Label className="fw-bold">
                                        <LockFill className="m-2 fs-5" />
                                        Password
                                    </Form.Label>
                                    <Form.Control
                                        type="password"
                                        placeholder="Enter your password"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                        required
                                    />
                                </Form.Group>

                                <div className="d-flex justify-content-between mb-4">
                                    <a href="/forgot-password" className="text-decoration-none text-muted small">
                                        Forgot Password?
                                    </a>
                                    <a href="/signup" className="text-decoration-none small">
                                        Don't have an account? Sign Up
                                    </a>
                                </div>

                                <Button type="submit" variant="primary" className="w-100 fw-bold mb-3">
                                    Login
                                </Button>

                                <div className="text-center text-muted mb-3 small">OR</div>

                                <Button type="button" variant="outline-danger" className="w-100 mb-2 fw-bold">
                                    <Google className="me-2" /> Login with Google
                                </Button>

                                <Button type="button" variant="outline-dark" className="w-100 mb-1 fw-bold">
                                    <Microsoft className="me-2" /> Login with Microsoft
                                </Button>
                            </Form>
                        </Col>
                    </Row>
                </Container>
            </section>
            <Footer />
        </div>
    );
}