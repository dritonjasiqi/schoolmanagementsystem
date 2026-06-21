import React from "react";
import { Container, Row, Col, Form, Button } from "react-bootstrap";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import { Google, Microsoft, Envelope, LockFill } from "react-bootstrap-icons";

export default function Signup() {
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

                            <Form className="shadow-sm p-4 p-md-5 rounded-4 border bg-light">
                                <Form.Group className="mb-3" controlId="fullName">
                                    <Form.Label className="fw-bold">Full Name</Form.Label>
                                    <Form.Control type="text" placeholder="Enter your full name" />
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="email">
                                    <Form.Label className="fw-bold">
                                        <Envelope className="m-2 fs-5" />
                                        Email address
                                    </Form.Label>
                                    <Form.Control type="email" placeholder="Enter your email" />
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="password">
                                    <Form.Label className="fw-bold">
                                        <LockFill className="m-2 fs-5" />
                                        Password
                                    </Form.Label>
                                    <Form.Control type="password" placeholder="Create a password" />
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="confirmPassword">
                                    <Form.Label className="fw-bold">Confirm Password</Form.Label>
                                    <Form.Control type="password" placeholder="Confirm your password" />
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