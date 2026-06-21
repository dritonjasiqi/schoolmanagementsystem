import React, { useState } from "react";
import { Container, Row, Col, Form, Button, Alert } from "react-bootstrap";
import { useNavigate } from "react-router-dom"; // Um zum Login zu wechseln
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import { Google, Microsoft, Envelope, LockFill } from "react-bootstrap-icons";
import myApi from "../api/axiosConfig"; // Unseren konfigurierten Axios-Client importieren

export default function Signup() {
    // 1. State-Variablen für unsere Formularfelder erstellen
    const [fullName, setFullName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    
    // Status-Meldungen (Erfolg oder Fehler)
    const [errorMsg, setErrorMsg] = useState('');
    const [successMsg, setSuccessMsg] = useState('');
    
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault(); 
        setErrorMsg('');
        setSuccessMsg('');

        // Passwörter vergleichen
        if (password !== confirmPassword) {
            setErrorMsg("Passwords do not match!");
            return;
        }

        try {
            await myApi.post('/auth/register/Student', {
                fullName: fullName,
                email: email,
                password: password,
                enrollmentNumber: Math.floor(Math.random() * 1000000) 
            });
            setSuccessMsg("Registration successful! Redirecting to login...");
            setTimeout(() => {
                navigate('/login');
            }, 2000);

        } catch (error) {
            if (error.response && error.response.status === 409) {
                setErrorMsg("This email is already registered. Please login.");
            } else {
                setErrorMsg("Registration failed. Please try again later.");
            }
            console.error("Signup error:", error);
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
                                <h2 className="fw-bold">Sign Up</h2>
                                <p className="text-muted">Create an account to get started.</p>
                            </div>

                            {errorMsg && <Alert variant="danger">{errorMsg}</Alert>}
                            {successMsg && <Alert variant="success">{successMsg}</Alert>}

                            <Form className="shadow-sm p-4 p-md-5 rounded-4 border bg-light" onSubmit={handleSubmit}>
                                
                                <Form.Group className="mb-3" controlId="fullName">
                                    <Form.Label className="fw-bold">Full Name</Form.Label>
                                    {/* Den Wert aus dem State auslesen und bei Änderung aktualisieren */}
                                    <Form.Control 
                                        type="text" 
                                        placeholder="Enter your full name" 
                                        value={fullName}
                                        onChange={(e) => setFullName(e.target.value)}
                                        required 
                                    />
                                </Form.Group>

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

                                <Form.Group className="mb-3" controlId="password">
                                    <Form.Label className="fw-bold">
                                        <LockFill className="m-2 fs-5" />
                                        Password
                                    </Form.Label>
                                    <Form.Control 
                                        type="password" 
                                        placeholder="Create a password" 
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                        required 
                                    />
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="confirmPassword">
                                    <Form.Label className="fw-bold">Confirm Password</Form.Label>
                                    <Form.Control 
                                        type="password" 
                                        placeholder="Confirm your password" 
                                        value={confirmPassword}
                                        onChange={(e) => setConfirmPassword(e.target.value)}
                                        required 
                                    />
                                </Form.Group>

                                <div className="d-flex justify-content-end mb-3">
                                    <a href="/login" className="text-decoration-none">
                                        Already have an account? Login
                                    </a>
                                </div>

                                <Button type="submit" variant="primary" className="w-100 fw-bold mb-3">
                                    Sign Up
                                </Button>

                                <Button type="button" variant="danger" className="w-100 mb-1 fw-bold">
                                    <Google className="me-1" /> Sign up with Google
                                </Button>

                                <Button type="button" variant="warning" className="w-100 mb-1 fw-bold text-white">
                                    <Microsoft className="me-1" /> Sign up with Microsoft
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