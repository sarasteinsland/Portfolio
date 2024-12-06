import React, { useContext, useEffect, useState } from 'react';
import AddMerchForm from '../components/AddMerchForm';
import { MerchContext, MerchItem } from '../context/MerchContext';

const CreateMerch: React.FC = () => {
  const context = useContext(MerchContext);

  if (!context) {
    throw new Error('CreateMerch must be used within a MerchProvider');
  }

  const { merch, addMerch, deleteMerch, fetchMerch } = context;
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchMerch().then(() => setLoading(false));
  }, [fetchMerch]);

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div className="p-8 bg-white shadow-md rounded-lg max-w-4xl mx-auto">
      <h2 className="text-3xl font-bold mb-6 text-center">Create New Merchandise</h2>
      <AddMerchForm onAdd={addMerch} />
      <div className="mt-8">
        <h3 className="text-2xl font-bold mb-4">Existing Merchandise</h3>
        <ul>
          {merch.map((item: MerchItem) => (
            <li key={item.id} className="flex justify-between items-center mb-4">
              <span>{item.name}</span>
              <button
                onClick={() => deleteMerch(item.id)}
                className="bg-red-500 text-white px-4 py-2 rounded"
              >
                Delete
              </button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};
export default CreateMerch;