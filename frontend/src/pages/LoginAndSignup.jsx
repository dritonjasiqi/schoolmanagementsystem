import React from "react";
import { Container, Row, Col, Form, Button } from "react-bootstrap";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import { Google, Microsoft, Envelope, LockFill } from "react-bootstrap-icons";

export default function LoginAndSignup() {
    return (
        <div>
            <Navbar />
            <section className="py-5" style={{ minHeight: '80vh' }}> 
                <Container className="py-5">
                    <Row className="justify-content-center">
                        <Col lg={6} md={8}>
                            <div className="text-center mb-4">
                                <h2 className="fw-bold">Login/Signup Page</h2>
                                <p className="text-muted">Please login or signup to access your account.</p>
                            </div>
                            <Form className="shadow-sm p-4 p-md-5 rounded-4 border bg-light">
                                <Form.Group className="mb-3" controlId="email">
                                    <Form.Label className="fw-bold">
                                        <Envelope className="m-2 fs-5" />Email address
                                    </Form.Label>
                                    <Form.Control type="email" placeholder="Enter your email" />
                                </Form.Group>
                                
                                <Form.Group className="mb-3" controlId="password">
                                    <Form.Label className="fw-bold">
                                        <LockFill className="m-2 fs-5" />Password
                                    </Form.Label>
                                    <Form.Control type="password" placeholder="Enter your password" />
                                </Form.Group>
                                
                                <a href="/forgot-password" className="text-decoration-none">Forgot password?</a>
                                
                                <Button type="submit" variant="primary" className="w-100 fw-bold mb-5 mt-3">
                                    Login
                                </Button>
                                
                                <Button type="submit" variant="danger" className="w-100 mb-1 fw-bold">
                                    <Google className="me-1" /> Sign in with Google
                                </Button>
                                
                                <Button type="submit" variant="warning" className="w-100 mb-1 fw-bold text-white">
                                    <Microsoft className="me-1" /> Sign in with Microsoft
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