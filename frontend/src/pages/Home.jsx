import React, { useState } from "react";

export default function Home() {
    const [isNavCollapsed, setIsNavCollapsed] = useState(true);

    const handleNavCollapse = () => setIsNavCollapsed(!isNavCollapsed);

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

    return (
        <div>
            <nav className="navbar navbar-expand-lg fixed-top shadow-sm py-3">
                <div className="container">
                    <a className="navbar-brand d-flex align-items-center fw-bold text-primary" href="/">
                        <i className="bi bi-mortarboard-fill fs-3 me-2"></i>
                        AuroraSchool
                    </a>
                    <button className="navbar-toggler border-0" type="button" onClick={handleNavCollapse} aria-expanded={!isNavCollapsed} aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className={`${isNavCollapsed ? 'collapse' : ''} navbar-collapse`} id="navbarNav">
                        <ul className="navbar-nav ms-auto align-items-center">
                            <li className="nav-item me-3"><a className="nav-link text-dark fw-semibold" href="#home"><i className="bi bi-house-door me-1"></i>Home</a></li>
                            <li className="nav-item me-3"><a className="nav-link text-dark fw-semibold" href="#about"><i className="bi bi-info-circle me-1"></i>About</a></li>
                            <li className="nav-item me-3"><a className="nav-link text-dark fw-semibold" href="#pricing"><i className="bi bi-tags me-1"></i>Pricing</a></li>
                            <li className="nav-item mt-2 mt-lg-0">
                                <button className="btn btn-primary px-4 py-2 btn-modern fw-semibold shadow-sm">
                                    <i className="bi bi-person-circle me-2"></i>Login / Sign Up
                                </button>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

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

                    {/* Reviews Carousel */}
                    <div id="reviewsCarousel" className="carousel slide pb-5" data-bs-ride="carousel">
                        <div className="carousel-inner px-2 px-md-5">
                            {/* Grouping reviews into slides (2 per slide for demo, adapting via Bootstrap grid inside) */}
                            {[0, 2, 4].map((startIndex, idx) => (
                                <div className={`carousel-item ${idx === 0 ? 'active' : ''}`} key={idx}>
                                    <div className="row justify-content-center g-4">
                                        {reviews.slice(startIndex, startIndex + 2).map((review) => (
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
                        <button className="carousel-control-prev" type="button" data-bs-target="#reviewsCarousel" data-bs-slide="prev">
                            <span className="carousel-control-prev-icon shadow" aria-hidden="true"></span>
                            <span className="visually-hidden">Previous</span>
                        </button>
                        <button className="carousel-control-next" type="button" data-bs-target="#reviewsCarousel" data-bs-slide="next">
                            <span className="carousel-control-next-icon shadow" aria-hidden="true"></span>
                            <span className="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
            </section>
        </div>
    );
}