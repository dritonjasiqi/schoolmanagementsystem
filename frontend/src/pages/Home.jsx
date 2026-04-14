import React ,{useState} from "react";

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
                </div>
            </nav>
        </div>
    );
}