import React from "react";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import { Google, Microsoft, Envelope , LockFill} from "react-bootstrap-icons";

export default function LoginAndSignup(){
    return(
        <div>
            <Navbar />
            <section className="py-5" style={{ minHeight: '80vh' }}> 
                <div className="container py-5">
                    <div className="row justify-content-center">
                        <div className="col-lg-6 col-md-8">
                            <div className="text-center mb-4">
                                <h2 className="fw-bold">Login/Signup Page</h2>
                                <p className="text-muted">Please login or signup to access your account.</p>
                            </div>
                            <form className="shadow-sm p-4 p-md-5 rounded-4 border bg-light">
                                <div className="mb-3">
                                    <label htmlFor="email" className="form-label fw-bold"><Envelope  className="m-2 fs-5"/>Email address</label>
                                    <input type="email" className="form-control " id="email" placeholder="Enter your email" />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="password" className="form-label fw-bold"><LockFill className="m-2 fs-5"/>Password</label>
                                    <input type="password" className="form-control" id="password" placeholder="Enter your password" />
                                </div>
                                <a href="/forgot-password" className="text-decoration-none">Forgot password?</a>
                                <button type="submit" className="btn btn-primary w-100 fw-bold mb-5">Login</button>
                                <button type="submit" className="btn btn-danger w-100 mb-1 fw-bold"><Google  className="mr-1"/> Sign in with Google</button>
                                <button type="submit" className = "btn btn-warning w-100 mb-1 fw-bold text-white"><Microsoft /> Sign in with Microsoft</button>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
            <Footer />
        </div>
    );
}