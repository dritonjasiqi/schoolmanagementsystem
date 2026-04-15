import React from "react";
import { FaFacebook, FaInstagram, FaLinkedin, FaTwitter } from 'react-icons/fa';
export default function Footer() {
    return (
        <footer className="bg-dark text-white py-5">
            <div className="container">
                <div className="row gy-4">
                    <div className="col-lg-4 col-md-6">
                        <h4 className="fw-bold text-primary mb-3"><i className="bi bi-mortarboard-fill me-2"></i>EduManage</h4>
                        <p className="text-light opacity-75 pe-lg-4">
                            Providing top-tier educational tools and management systems to institutions worldwide.
                        </p>
                    </div>

                    <div className="col-lg-2 col-md-6">
                        <h5 className="mb-3">Quick Links</h5>
                        <ul className="list-unstyled">
                            <li className="mb-2"><a href="#home" className="text-decoration-none text-light opacity-75 hover-white">Home</a></li>
                            <li className="mb-2"><a href="#about" className="text-decoration-none text-light opacity-75 hover-white">About Us</a></li>
                            <li className="mb-2"><a href="#courses" className="text-decoration-none text-light opacity-75 hover-white">Courses</a></li>
                            <li className="mb-2"><a href="#pricing" className="text-decoration-none text-light opacity-75 hover-white">Pricing</a></li>
                        </ul>
                    </div>

                    <div className="col-lg-3 col-md-6">
                        <h5 className="mb-3">Contact Info</h5>
                        <ul className="list-unstyled text-light opacity-75">
                            <li className="mb-2"><i className="bi bi-geo-alt me-2"></i> 123 University Blvd, Tech City</li>
                            <li className="mb-2"><i className="bi bi-envelope me-2"></i> support@edumanage.com</li>
                            <li className="mb-2"><i className="bi bi-telephone me-2"></i> +1 (555) 123-4567</li>
                        </ul>
                    </div>

                    <div className="col-lg-3 col-md-6">
                        <h5 className="mb-3">Follow Us</h5>
                        <div className="d-flex gap-3">
                            <a href="#!" className="text-white fs-4 opacity-75 social-icon" aria-label="Facebook">
                                <FaFacebook />
                            </a>
                            <a href="#!" className="text-white fs-4 opacity-75 social-icon" aria-label="Twitter">
                                <FaTwitter />
                            </a>
                            <a href="#!" className="text-white fs-4 opacity-75 social-icon" aria-label="Instagram">
                                <FaInstagram />
                            </a>
                            <a href="#!" className="text-white fs-4 opacity-75 social-icon" aria-label="LinkedIn">
                                <FaLinkedin />
                            </a>
                        </div>
                    </div>
                </div>

                <div className="row mt-5 pt-4 border-top border-secondary">
                    <div className="col-12 text-center text-light opacity-50">
                        <p className="mb-0">&copy; {new Date().getFullYear()} EduManage System. All rights reserved.</p>
                    </div>
                </div>
            </div>
        </footer>
    );
}