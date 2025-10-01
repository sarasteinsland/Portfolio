import React, { useContext, useState } from 'react';
import { MerchContext } from '../context/MerchContext';
import EditMerchModal from '../components/EditMerch';
import { TrumpMerchandise } from '../interfaces/TrumpMerch';

const UpdateMerch: React.FC = () => {
  const context = useContext(MerchContext);

  if (!context) {
    throw new Error('UpdateMerch must be used within a MerchProvider');
  }

  const { merch, updateMerch } = context;
  const [selectedItem, setSelectedItem] = useState<TrumpMerchandise | null>(null);
  const [successMessage, setSuccessMessage] = useState<string | null>(null);

  const handleSuccess = () => {
    setSuccessMessage('Merchandise successfully updated!');
    setTimeout(() => setSuccessMessage(null), 3000);
  };

  const handleSave = async (updatedMerch: TrumpMerchandise, imageFile: File | null) => {
    try {
      await updateMerch(updatedMerch.id!, updatedMerch, imageFile);
      handleSuccess();
    } catch (error) {
      console.error('Error updating merchandise:', error);
    }
  };

  return (
    <div className="bg-blue-100 min-h-screen flex flex-col items-center py-8">
      <h2 className="text-4xl font-bold mb-6 text-center text-blue-900">Update Merchandise</h2>
      {successMessage && (
        <div className="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-4">
          {successMessage}
          <button
            onClick={() => setSuccessMessage(null)}
            className="absolute top-0 bottom-0 right-0 px-4 py-3"
          >
            <span className="text-green-700">×</span>
          </button>
        </div>
      )}
      <div className="bg-white p-6 rounded-lg shadow-lg w-full max-w-md">
        {selectedItem ? (
          <EditMerchModal item={selectedItem} onSave={handleSave} onClose={() => setSelectedItem(null)} />
        ) : (
          <div>Select an item to update</div>
        )}
      </div>
      <div className="mt-12 w-full max-w-4xl">
        <h3 className="text-3xl font-bold mb-6 text-center text-blue-900">Existing Merchandise</h3>
        <ul className="space-y-4">
          {merch.map((item: TrumpMerchandise) => (
            <li key={item.id} className="flex justify-between items-center bg-white p-4 rounded-lg shadow-md">
              <span className="text-lg font-medium">{item.name}</span>
              <button
                onClick={() => setSelectedItem(item)}
                className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition">
                Edit </button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default UpdateMerch;