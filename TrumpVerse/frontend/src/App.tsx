import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar';
import Forside from './pages/Home';
import OurMerch from './pages/Merch';
import CreateMerch from './pages/CreateMerch';

const App = () => {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Forside />} />
        <Route path="/our-merch" element={<OurMerch />} />
        <Route path="/create-merch" element={<CreateMerch />} />
      </Routes>
    </Router>
  );
};

export default App;