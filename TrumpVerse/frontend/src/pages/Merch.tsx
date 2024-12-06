import React, { useContext, useEffect } from 'react';
import { MerchContext, MerchItem } from '../context/MerchContext';
import ImageCard from '../components/ImageCard';
import ImageUpload from '../components/ImageUpload';

const Merch: React.FC = () => {
  const context = useContext(MerchContext);

  if (!context) {
    throw new Error('Merch must be used within a MerchProvider');
  }

  const { merch, setMerch, error, setError, deleteMerch } = context;

  useEffect(() => {
    const fetchMerch = async () => {
      try {
        const response = await fetch('http://localhost:5236/api/TrumpMerchandise');
        if (!response.ok) throw new Error('Failed to fetch merchandise');
        
        const merchData = await response.json();
        
        if (Array.isArray(merchData)) {
          setMerch(merchData);
        } else {
          throw new Error('Received invalid data format');
        }
      } catch (err) {
        setError('Failed to fetch merchandise');
        console.error('Error fetching merchandise:', err);
      }
    };

    fetchMerch();
  }, [setMerch, setError]);

  return (
    <div className="p-8 bg-white shadow-md rounded-lg max-w-4xl mx-auto">
      <h2 className="text-3xl font-bold mb-6 text-center">Our Merchandise</h2>
      {error && <p className="text-red-500 text-center mb-4">{error}</p>}
      {merch.length === 0 ? (
        <p className="text-center text-lg text-gray-600">No merchandise available.</p>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
          {merch.map((item: MerchItem) => (
            <ImageCard
              key={item.id}
              imageUrl={`http://localhost:5236/images/Merch/${item.image}`}
              title={item.name}
              description={item.description}
              price={item.price}
            />
          ))}
        </div>
      )}
      <ImageUpload />
    </div>
  );
};

export default Merch;