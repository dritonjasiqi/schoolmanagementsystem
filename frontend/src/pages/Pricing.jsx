import React from "react";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";

const courses = [
    {
        id: 1,
        title: "Linux System Administration",
        description: "Master terminal commands, system optimization, and bash scripting.",
        price: "Free",
        image: "https://images.unsplash.com/photo-1629654297299-c8506221ca97?auto=format&fit=crop&w=600&q=80"
    },
    {
        id: 2,
        title: "Cybersecurity Fundamentals",
        description: "Learn the core concepts of system security and network defense.",
        price: "$49.99",
        image: "https://images.unsplash.com/photo-1550751827-4bd374c3f58b?auto=format&fit=crop&w=600&q=80"
    },
    {
        id: 3,
        title: "Advanced Java & Spring Boot",
        description: "Build scalable, secure enterprise applications and APIs.",
        price: "$59.99",
        image: "https://images.unsplash.com/photo-1517694712202-14dd9538aa97?auto=format&fit=crop&w=600&q=80"
    },
    {
        id: 4,
        title: "AWS Cloud Practitioner",
        description: "A complete guide to cloud computing infrastructure and AWS services.",
        price: "$79.99",
        image: "https://images.unsplash.com/photo-1451187580459-43490279c0fa?auto=format&fit=crop&w=600&q=80"
    },
    {
        id: 5,
        title: "Data Structures & Algorithms",
        description: "Deep dive into trees, graphs, and algorithmic problem solving.",
        price: "$39.99",
        image: "https://images.unsplash.com/photo-1504639725590-34d0984388bd?auto=format&fit=crop&w=600&q=80"
    },
    {
        id: 6,
        title: "Digital Logic & Hardware",
        description: "Understand CMOS, DFA, NFA, and hardware design principles.",
        price: "$44.99",
        image: "https://images.unsplash.com/photo-1518770660439-4636190af475?auto=format&fit=crop&w=600&q=80"
    },
    {
        id: 7,
        title: "CompTIA Security+ Prep",
        description: "Comprehensive preparation for your Security+ certification exam.",
        price: "$89.99",
        image: "https://images.unsplash.com/photo-1614064641936-3899f193eb77?auto=format&fit=crop&w=600&q=80"
    },
    {
        id: 8,
        title: "Formal Languages & Automata",
        description: "Explore the mathematical foundations of computer science.",
        price: "$34.99",
        image: "https://images.unsplash.com/photo-1635070041078-e363dbe005cb?auto=format&fit=crop&w=600&q=80"
    }
];

export default function Pricing() {
    return (<div>
        <Navbar />
        <div className="bg-light min-vh-90 py-5 mt-5">
            {/* Custom CSS injected for hover effects and clean transitions */}
            <style>
                {`
          .course-card {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            border: none;
            border-radius: 12px;
            overflow: hidden;
          }
          .course-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 12px 24px rgba(0,0,0,0.1) !important;
          }
          .course-btn {
            transition: all 0.3s ease;
            border-radius: 8px;
          }
          .course-btn:hover {
            transform: scale(1.03);
          }
          .card-img-wrapper {
            height: 200px;
            overflow: hidden;
          }
          .card-img-wrapper img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            transition: transform 0.5s ease;
          }
          .course-card:hover .card-img-wrapper img {
            transform: scale(1.05);
          }
        `}
            </style>

            <div className="container">
                {/* Header Section */}
                <div className="text-center mb-5">
                    <h1 className="fw-bold text-dark mb-3">Courses</h1>
                    <p className="text-muted fs-5">
                        Expand your knowledge with our industry-leading curriculum.
                    </p>
                </div>

                {/* Courses Grid */}
                <div className="row g-4">
                    {courses.map((course) => (
                        <div key={course.id} className="col-12 col-md-6 col-lg-3">
                            <div className="card h-100 shadow-sm course-card bg-white">

                                <div className="card-img-wrapper">
                                    <img
                                        src={course.image}
                                        alt={course.title}
                                        loading="lazy"
                                    />
                                </div>

                                <div className="card-body d-flex flex-column p-4">
                                    <h5 className="card-title fw-bold mb-2">
                                        {course.title}
                                    </h5>
                                    <p className="card-text text-muted flex-grow-1" style={{ fontSize: '0.9rem' }}>
                                        {course.description}
                                    </p>

                                    <div className="d-flex align-items-center justify-content-between mt-3 mb-4">
                                        <span className={`fs-5 fw-bold ${course.price === 'Free' ? 'text-success' : 'text-primary'}`}>
                                            {course.price === 'Free' ? (
                                                <><i className="bi bi-gift-fill me-2"></i>Free</>
                                            ) : (
                                                course.price
                                            )}
                                        </span>
                                    </div>

                                    <a href={`/courses/${course.id}`} className="btn btn-primary w-100 course-btn py-2 fw-semibold">
                                        <i className="bi bi-book-half me-2"></i>
                                        View Course
                                    </a>
                                </div>

                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
        <Footer />
    </div>);
}