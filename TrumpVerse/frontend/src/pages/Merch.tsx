import React, { useState } from 'react';
import { useMerchContext } from '../context/MerchContext';
import ImageCard from '../components/ImageCard';
import EditMerchModal from '../components/EditMerch';
import { TrumpMerchandise } from '../interfaces/TrumpMerch';

const Merch: React.FC = () => {
  const { merch, error, updateMerch } = useMerchContext();
  const [editingItem, setEditingItem] = useState<TrumpMerchandise | null>(null);
  const [searchQuery, setSearchQuery] = useState<string>(''); 

  
  const filteredMerch = merch.filter((item) =>
    item.name.toLowerCase().includes(searchQuery.toLowerCase()) 
  );

  const handleEdit = (item: TrumpMerchandise) => {
    setEditingItem(item);
  };

  const handleSave = async (updatedItem: TrumpMerchandise, imageFile: File | null) => {
    try {
      await updateMerch(updatedItem.id!, updatedItem, imageFile); 
      setEditingItem(null); 
    } catch (error) {
      console.error('Error updating merchandise:', error);
    }
  };

  return (
    <div className="bg-red-600 min-h-screen flex flex-col items-center justify-center p-6">
      <h2 className="text-4xl font-extrabold text-white mb-6 text-center">
        Our Merchandise
      </h2>
      
      <input
        type="text"
        placeholder="Search for merch"
        value={searchQuery}
        onChange={(e) => setSearchQuery(e.target.value)}
        className="mb-6 p-3 w-full max-w-md border-2 border-white rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none"
      />
      
      {error && <p className="text-yellow-300 text-center mb-6 text-xl">{error}</p>}
      
      {filteredMerch.length === 0 ? (
        <p className="text-center text-lg text-white">No merchandise available.</p>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-8">
          {filteredMerch.map((item: TrumpMerchandise) => (
            <ImageCard
              key={item.id}
              id={item.id?.toString()} 
              imageUrl={`http://localhost:5236/images/${item.image}`}
              title={item.name}
              description={item.description}
              price={item.price}
              onEdit={() => handleEdit(item)}
            />
          ))}
        </div>
      )}
      
      {editingItem && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
          <EditMerchModal
            item={editingItem}
            onSave={handleSave}
            onClose={() => setEditingItem(null)}
          />
        </div>
      )}
    </div>
  );
};

export default Merch;