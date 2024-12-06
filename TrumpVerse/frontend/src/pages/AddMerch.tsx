import React, { useContext } from 'react';
import AddMerchForm from '../components/AddMerchForm';
import { MerchContext } from '../context/MerchContext';

const AddMerch = () => {
  const context = useContext(MerchContext);

  if (!context) {
    throw new Error('AddMerch must be used within a MerchProvider');
  }

  const { addMerch } = context;

  return (
    <div className="p-8 bg-white shadow-md rounded-lg max-w-4xl mx-auto">
      <h2 className="text-3xl font-bold mb-6 text-center">Add New Merchandise</h2>
      <AddMerchForm onAdd={addMerch} />
    </div>
  );
};

export default AddMerch;