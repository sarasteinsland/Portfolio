import React from 'react';
import DeleteMerchById from '../components/DeleteMerch'; // Import DeleteMerchById component

const Home: React.FC = () => {
  return (
    <div className="bg-red-100 min-h-screen flex flex-col items-center">
     
      <header className="bg-red-800 w-full p-6 shadow-lg">
        <h1 className="text-white text-4xl font-extrabold text-center">
          Welcome to TrumpVerse
        </h1>
      </header>

     
      <main className="flex flex-col items-center w-full p-6 sm:px-10 lg:px-20">
     
        <section className="bg-white shadow-lg rounded-xl p-8 w-full max-w-3xl mb-10 border-4 border-blue-600 flex flex-col lg:flex-row items-center lg:items-start">
          <div className="lg:w-1/2 mb-6 lg:mb-0">
            <h2 className="text-3xl font-bold text-blue-800 mb-4">About Us</h2>
            <p className="text-gray-700 text-lg">
              Welcome to TrumpVerse America! We offer a wide range of merchandise that you can browse and make.
            </p>
          </div>
          <div className="lg:w-1/2">
            <img
              src="/Images/Trump.png"
              alt="About Us"
              className="w-full h-64 object-cover rounded-md shadow-lg"
            />
          </div>
        </section>

   
        <section className="bg-white shadow-lg rounded-xl p-8 w-full max-w-3xl mb-10 border-4 border-blue-600 flex flex-col lg:flex-row items-center lg:items-start">
          <div className="lg:w-1/2 mb-6 lg:mb-0">
            <h2 className="text-3xl font-bold text-blue-800 mb-4">Our Products</h2>
            <p className="text-gray-700 text-lg">
              Check out our latest merchandise in the "Merch" section. You can also create your own custom merchandise in the "Add Merch" section.
            </p>
          </div>
          <div className="lg:w-1/2">
            <img
              src="/Images/Merch.png"
              alt="Our Products"
              className="w-full h-64 object-cover rounded-md shadow-lg"
            />
          </div>
        </section>

       
        <section className="bg-white shadow-lg rounded-xl p-8 w-full max-w-3xl mb-10 border-4 border-blue-600">
          <h2 className="text-3xl font-bold text-blue-800 mb-4">Delete Merchandise</h2>
          <DeleteMerchById />
        </section>
      </main>

   
      <footer className="bg-blue-800 w-full p-4 mt-auto">
        <p className="text-white text-center text-lg">&copy; 2024 TrumpVerse.</p>
      </footer>
    </div>
  );
};

export default Home;
