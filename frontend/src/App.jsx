
import React from 'react';
import './App.css';
import Sidebar from './components/layout/Sidebar';
import Header from './components/layout/Header';

function App() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-50 via-blue-50 to-indigo-50 dark:from-slate-900 dark:via-slate-800 dark:from-slate-900 transition-all duration-500 transition-all">
      <div className="flex h-screen overflow-hidden">
        <Sidebar />
        <div className='flex-1 flex flex-col overflow-hidden'>
          <Header />
        </div>
      </div>
    </div>
  );
}

export default App