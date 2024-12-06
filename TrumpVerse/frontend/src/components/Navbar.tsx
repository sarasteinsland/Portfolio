import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => {
  return (
    <nav className="bg-gray-800 p-4">
      <div className="container mx-auto flex justify-between items-center">
        <div className="text-white text-lg font-bold">
          <Link to="/">MyApp</Link>
        </div>
        <div className="flex space-x-4">
          <Link to="/" className="text-gray-300 hover:text-white">Home</Link>
          <Link to="/our-merch" className="text-gray-300 hover:text-white">Our Merch</Link>
          <Link to="/create-merch" className="text-gray-300 hover:text-white">Create Merch</Link>
          <div className="relative group">
            <button className="text-gray-300 hover:text-white">More</button>
            <div className="absolute hidden group-hover:block bg-gray-700 text-white mt-2 rounded shadow-lg">
              <Link to="/about" className="block px-4 py-2 hover:bg-gray-600">About</Link>
              <Link to="/contact" className="block px-4 py-2 hover:bg-gray-600">Contact</Link>
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;