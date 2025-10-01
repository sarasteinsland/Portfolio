import React from 'react';
import { Link } from 'react-router-dom';

const Navbar: React.FC = () => {
  return (
    <nav className="bg-blue-700 p-4 shadow-lg">
      <div className="container mx-auto flex justify-between items-center">
     
        <h1 className="text-white text-3xl font-extrabold">TrumpVerse</h1>
        
       
        <div className="space-x-6">
          <Link 
            to="/" 
            className="text-white text-lg font-medium hover:text-blue-200 transition duration-200"
          >
            Home
          </Link>
          <Link 
            to="/add-merch" 
            className="text-white text-lg font-medium hover:text-blue-200 transition duration-200"
          >
            Add Merch
          </Link>
          <Link 
            to="/merch" 
            className="text-white text-lg font-medium hover:text-blue-200 transition duration-200"
          >
            Merch
          </Link>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
