import React from 'react';
import {BrowserRouter , Routes, Route } from 'react-router-dom';
import './App.css'
import HomePage from './pages/public/HomePage';

function App() {
  return (
    <BrowserRouter> {/*Hier beginnt die BrowserRouter */}
      <Routes>       {/* Hier werden die Routen definiert */}
        <Route path="/" element={<HomePage />} />  {/* Route für die Startseite */}
      </Routes>
    </BrowserRouter>
  );
}

export default App
