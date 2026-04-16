import React from "react";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";

const professors = [
    {
        id: 1,
        title: "Prof. Dr.",
        name: "Alan Turing",
        description: "Specializes in Formal Languages, Automata Theory, and mathematical foundations.",
        image: "https://images.unsplash.com/photo-1568602471122-7832951cc4c5?auto=format&fit=crop&w=300&q=80"
    },
    {
        id: 2,
        title: "Dr.",
        name: "Grace Hopper",
        description: "Expert in Software Engineering, advanced Java, and enterprise architecture.",
        image: "https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?auto=format&fit=crop&w=300&q=80"
    },
    {
        id: 3,
        title: "Prof.",
        name: "Kevin Mitnick",
        description: "Leads our Cybersecurity and Network Defense research labs.",
        image: "https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?auto=format&fit=crop&w=300&q=80"
    },
    {
        id: 4,
        title: "Dr.",
        name: "Linus Torvalds",
        description: "Focuses on Linux System Administration, Cloud Computing, and OS optimization.",
        image: "https://images.unsplash.com/photo-1519085360753-af0119f7cbe7?auto=format&fit=crop&w=300&q=80"
    }
];

export default function AboutUs() {
    return (
        <div>
            <Navbar />
            <div className="bg-light min-vh-100 py-5">
                {/* Custom CSS for modern animations and hover effects */}
                <style>
                    {`
          /* Hero Image Animation */
          @keyframes float {
            0% { transform: translateY(0px); }
            50% { transform: translateY(-15px); }
            100% { transform: translateY(0px); }
          }
          .hero-img {
            animation: float 6s ease-in-out infinite;
            border-radius: 24px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.1);
            object-fit: cover;
            width: 100%;
            height: auto;
            max-height: 500px;
          }

          /* Professor Card Styling */
          .prof-card {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            border: none;
            border-radius: 16px;
            overflow: hidden;
            background-color: #ffffff;
          }
          .prof-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 15px 30px rgba(0,0,0,0.1) !important;
          }
          
          /* Profile Image Styling */
          .prof-img-wrapper {
            padding: 30px 0 15px 0;
            text-align: center;
          }
          .prof-img {
            width: 140px;
            height: 140px;
            border-radius: 50%;
            object-fit: cover;
            border: 4px solid #f8f9fa;
            box-shadow: 0 8px 16px rgba(0,0,0,0.1);
            transition: transform 0.3s ease;
          }
          .prof-card:hover .prof-img {
            transform: scale(1.08);
          }

          /* Button Hover Effect */
          .hover-btn {
            transition: all 0.3s ease;
            border-radius: 8px;
          }
          .hover-btn:hover {
            transform: scale(1.05);
          }
        `}
                </style>

                {/* --- HERO SECTION --- */}
                <div className="container mt-5 mb-5 pb-4">
                    <div className="row align-items-center g-5">
                        {/* Left Side: Animated Image */}
                        <div className="col-12 col-lg-6">
                            <img
                                src="https://images.unsplash.com/photo-1523050854058-8df90110c9f1?auto=format&fit=crop&w=1000&q=80"
                                alt="Our Campus"
                                className="hero-img"
                            />
                        </div>
                        {/* Right Side: Title and Dynamic Text */}
                        <div className="col-12 col-lg-6 d-flex flex-column justify-content-center">
                            <h1 className="display-4 fw-bold text-dark mb-4">About Us</h1>
                            <p className="lead text-muted mb-4">
                                We are a premier institution dedicated to shaping the next generation of software engineers, system administrators, and cybersecurity experts. Our modern curriculum bridges the gap between theoretical computer science and hands-on industry application.
                            </p>
                            <p className="text-muted">
                                Whether you are mastering Linux terminal commands, exploring deep concepts in automata theory, or securing enterprise networks, our dynamic learning environment adapts to your goals. Join us to innovate, build, and secure the digital future.
                            </p>
                        </div>
                    </div>
                </div>

                {/* --- SECTION 2: SCHOOL OVERVIEW --- */}
                <div className="container mb-5 pb-5">
                    <div className="card shadow-sm border-0 rounded-4 p-4 p-md-5 bg-white">
                        <div className="text-center max-w-75 mx-auto">
                            <i className="bi bi-bank2 display-1 text-primary mb-3"></i>
                            <h2 className="fw-bold mb-4">Our Mission & Vision</h2>
                            <p className="fs-5 text-muted mb-0">
                                Founded on the principles of academic excellence and technological innovation, our school focuses heavily on practical skill development alongside robust theoretical foundations. We pride ourselves on offering an agile curriculum that stays ahead of industry trends—from advanced cloud infrastructure to modern web frameworks. We believe that education should be accessible, highly responsive, and strictly aligned with real-world technological demands.
                            </p>
                        </div>
                    </div>
                </div>

                {/* --- PROFESSOR SECTION --- */}
                <div className="container">
                    <div className="text-center mb-5">
                        <h2 className="fw-bold text-dark">Our Professors</h2>
                        <p className="text-muted">Learn from industry veterans and renowned academics.</p>
                    </div>

                    <div className="row g-4">
                        {professors.map((prof) => (
                            <div key={prof.id} className="col-12 col-md-6 col-lg-3">
                                <div className="card h-100 shadow-sm prof-card">

                                    {/* Profile Image */}
                                    <div className="prof-img-wrapper">
                                        <img
                                            src={prof.image}
                                            alt={`${prof.title} ${prof.name}`}
                                            className="prof-img"
                                            loading="lazy"
                                        />
                                    </div>

                                    {/* Card Body */}
                                    <div className="card-body d-flex flex-column text-center px-4 pb-4">
                                        <h5 className="card-title fw-bold mb-1">
                                            <span className="text-primary fs-6 d-block mb-1">{prof.title}</span>
                                            {prof.name}
                                        </h5>
                                        <p className="card-text text-muted mt-3 mb-4 flex-grow-1" style={{ fontSize: '0.95rem' }}>
                                            {prof.description}
                                        </p>

                                        {/* Link Button */}
                                        <a href={`/professors/${prof.id}`} className="btn btn-outline-primary w-100 hover-btn py-2 fw-semibold mt-auto">
                                            <i className="bi bi-person-lines-fill me-2"></i>
                                            View Profile
                                        </a>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    );
}