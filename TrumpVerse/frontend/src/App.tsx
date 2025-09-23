import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { MerchProvider } from './context/MerchContext';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import AddMerch from './pages/AddMerch';
import MerchPage from './pages/Merch';
import './App.css';  

const App: React.FC = () => {
  return (
    <MerchProvider>
      <Router>
        <Navbar />
        <main className="container mx-auto p-4">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/add-merch" element={<AddMerch />} />
            <Route path="/merch" element={<MerchPage />} />
            <Route
              path="*"
              element={
                <div className="flex items-center justify-center min-h-screen bg-red-100">
                  <div className="text-center bg-white shadow-xl rounded-lg p-8 max-w-sm w-full">
                    <h2 className="text-5xl font-extrabold text-red-600 mb-4">404</h2>
                    <p className="text-2xl font-semibold text-blue-800 mb-4">Page Not Found</p>
                    <p className="text-lg text-gray-700 mb-6">Sorry, the page you are looking for does not exist.</p>
                    <a
                      href="/"
                      className="text-white bg-blue-600 hover:bg-blue-700 px-6 py-3 rounded-full text-lg font-semibold transition duration-200"
                    >
                      Go Home
                    </a>
                  </div>
                </div>
              }
            />
          </Routes>
        </main>
      </Router>
    </MerchProvider>
  );
};

export default App;
