import React, { useState } from "react";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";

export default function Home() {

    const courses = [
        { id: 1, title: 'Introduction to Computer Science', desc: 'Learn the basics of algorithms, data structures, and logic.', price: 'Free', img: 'https://images.unsplash.com/photo-1517694712202-14dd9538aa97?auto=format&fit=crop&w=600&q=80' },
        { id: 2, title: 'Advanced Web Development', desc: 'Master React, Node.js, and modern frontend frameworks.', price: '$49.99', img: 'https://images.unsplash.com/photo-1547658719-da2b51169166?auto=format&fit=crop&w=600&q=80' },
        { id: 3, title: 'Cybersecurity Fundamentals', desc: 'Understand network security, cryptography, and ethical hacking.', price: '$59.99', img: 'https://images.unsplash.com/photo-1550751827-4bd374c3f58b?auto=format&fit=crop&w=600&q=80' },
        { id: 4, title: 'Cloud Computing with AWS', desc: 'Prepare for your AWS Cloud Practitioner certification.', price: '$39.99', img: 'https://images.unsplash.com/photo-1451187580459-43490279c0fa?auto=format&fit=crop&w=600&q=80' }
    ];

    const reviews = [
        { id: 1, name: 'Alex Johnson', text: 'This system completely transformed how I manage my studies.', stars: 5 },
        { id: 2, name: 'Sarah Miller', text: 'The courses are top-notch and the platform is so easy to use.', stars: 4 },
        { id: 3, name: 'David Lee', text: 'Excellent UI and great support. Highly recommend to any student.', stars: 5 },
        { id: 4, name: 'Emma Wilson', text: 'The best school management tool I have ever used. Very responsive!', stars: 5 },
        { id: 5, name: 'Michael Brown', text: 'Helped me track my grades and courses flawlessly.', stars: 4 },
        { id: 6, name: 'Jessica Davis', text: 'A must-have platform for modern education and e-learning.', stars: 5 }
    ];

    // Reviews Carousel Logic
    const [activeSlide, setActiveSlide] = useState(0);
    const totalSlides = Math.ceil(reviews.length / 2); // 2 reviews per slide = 3 slides

    const nextSlide = () => {
        setActiveSlide((prev) => (prev + 1) % totalSlides);
    };

    const prevSlide = () => {
        setActiveSlide((prev) => (prev - 1 + totalSlides) % totalSlides);
    };

    return (
        <div>
            <Navbar />
            {/* HERO SECTION */}
            <section id="home" className="hero-section d-flex align-items-center">
                <div className="container">
                    <div className="row align-items-center flex-column-reverse flex-lg-row">
                        <div className="col-lg-6 mt-5 mt-lg-0 text-center text-lg-start">
                            <h1 className="display-4 fw-bold text-dark mb-4">Empower Your Learning Journey</h1>
                            <p className="lead text-muted mb-5">
                                The ultimate modern school management system designed to streamline your courses, track your progress, and help you succeed academically.
                            </p>
                            <button className="btn btn-primary btn-lg px-5 py-3 btn-modern shadow">
                                Get Started Today <i className="bi bi-arrow-right ms-2"></i>
                            </button>
                        </div>
                        <div className="col-lg-6 text-center">
                            <img
                                src="https://images.unsplash.com/photo-1522202176988-66273c2fd55f?auto=format&fit=crop&w=800&q=80"
                                alt="Students studying"
                                className="img-fluid hero-img"
                            />
                        </div>
                    </div>
                </div>
            </section>

            {/* ABOUT US SECTION */}
            <section id="about" className="py-5 bg-white">
                <div className="container py-5">
                    <div className="row justify-content-center text-center mb-5">
                        <div className="col-lg-8">
                            <h2 className="fw-bold mb-3">About Us</h2>
                            <p className="text-muted fs-5">
                                We believe education should be accessible, organized, and inspiring. Our platform bridges the gap between students and educators by providing world-class management tools.
                            </p>
                            <p className="text-muted fs-5">
                                Built by a team of passionate developers and educators, EduManage integrates seamless course tracking, interactive learning, and community engagement in one beautiful, responsive interface.
                            </p>
                        </div>
                    </div>
                    {/* Reviews Custom React Carousel */}
                    <div id="reviewsCarousel" className="carousel slide pb-5">
                        <div className="carousel-inner px-2 px-md-5">
                            {Array.from({ length: totalSlides }).map((_, slideIndex) => (
                                <div
                                    className={`carousel-item ${slideIndex === activeSlide ? 'active d-block' : 'd-none'}`}
                                    key={slideIndex}
                                >
                                    <div className="row justify-content-center g-4">
                                        {reviews.slice(slideIndex * 2, slideIndex * 2 + 2).map((review) => (
                                            <div className="col-md-6" key={review.id}>
                                                <div className="review-card d-flex flex-column justify-content-between">
                                                    <p className="text-muted fst-italic mb-4">"{review.text}"</p>
                                                    <div className="d-flex align-items-center">
                                                        <img src={`https://i.pravatar.cc/150?u=${review.id}`} alt={review.name} className="profile-img me-3 shadow-sm" />
                                                        <div>
                                                            <h6 className="mb-0 fw-bold">{review.name}</h6>
                                                            <div className="text-warning mt-1">
                                                                {[...Array(5)].map((_, i) => (
                                                                    <i key={i} className={`bi ${i < review.stars ? 'bi-star-fill' : 'bi-star'} me-1`}></i>
                                                                ))}
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        ))}
                                    </div>
                                </div>
                            ))}
                        </div>
                        {/* Custom Carousel Controls */}
                        <button className="carousel-control-prev" type="button" onClick={prevSlide}>
                            <span className="carousel-control-prev-icon shadow" aria-hidden="true"></span>
                            <span className="visually-hidden">Previous</span>
                        </button>
                        <button className="carousel-control-next" type="button" onClick={nextSlide}>
                            <span className="carousel-control-next-icon shadow" aria-hidden="true"></span>
                            <span className="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
            </section>
            {/* COURSES SECTION */}
            <section id="courses" className="py-5">
                <div className="container py-5">
                    <div className="text-center mb-5">
                        <h2 className="fw-bold">Most Popular Courses</h2>
                        <p className="text-muted">Expand your knowledge with our top-rated modules</p>
                    </div>
                    <div className="row g-4">
                        {courses.map((course) => (
                            <div className="col-md-6 col-lg-3" key={course.id}>
                                <div className="card h-100 hover-card shadow-sm">
                                    <img src={course.img} className="card-img-top" alt={course.title} style={{ height: '200px', objectFit: 'cover' }} />
                                    <div className="card-body d-flex flex-column">
                                        <div className="d-flex justify-content-between align-items-start mb-2">
                                            <h5 className="card-title fw-bold mb-0">{course.title}</h5>
                                        </div>
                                        <p className="card-text text-muted flex-grow-1 mt-2">{course.desc}</p>
                                        <div className="d-flex justify-content-between align-items-center mt-3 pt-3 border-top">
                                            <span className={`fw-bold fs-5 ${course.price === 'Free' ? 'text-success' : 'text-primary'}`}>
                                                {course.price}
                                            </span>
                                            <button className="btn btn-outline-primary btn-sm btn-modern">
                                                View Course
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </section>

            {/* CONTACT SECTION */}
            <section id="contact" className="py-5 bg-white">
                <div className="container py-5">
                    <div className="row justify-content-center">
                        <div className="col-lg-6 col-md-8">
                            <div className="text-center mb-4">
                                <h2 className="fw-bold">Get in Touch</h2>
                                <p className="text-muted">Have any questions? We'd love to hear from you.</p>
                            </div>
                            <form className="shadow-sm p-4 p-md-5 rounded-4 border bg-light">
                                <div className="mb-3">
                                    <label htmlFor="name" className="form-label fw-semibold">Full Name</label>
                                    <input type="text" className="form-control form-control-lg bg-white" id="name" placeholder="John Doe" />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="email" className="form-label fw-semibold">Email Address</label>
                                    <input type="email" className="form-control form-control-lg bg-white" id="email" placeholder="name@example.com" />
                                </div>
                                <div className="mb-4">
                                    <label htmlFor="message" className="form-label fw-semibold">Message</label>
                                    <textarea className="form-control bg-white" id="message" rows="4" placeholder="How can we help you?"></textarea>
                                </div>
                                <button type="submit" className="btn btn-primary w-100 btn-lg btn-modern fw-bold">
                                    Send Message <i className="bi bi-send ms-2"></i>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
            {/* FOOTER */}
            <Footer />
        </div>
    );
}