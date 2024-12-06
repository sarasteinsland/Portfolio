import React, { useState } from 'react';

interface AddMerchFormProps {
  onAdd: (newItem: { name: string; description: string; price: number; image: File }) => void;
}

const AddMerchForm: React.FC<AddMerchFormProps> = ({ onAdd }) => {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [price, setPrice] = useState(0);
  const [image, setImage] = useState<File | null>(null);

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    
    if (image && name && description && price > 0) {
      onAdd({ name, description, price, image });
      setName('');
      setDescription('');
      setPrice(0);
      setImage(null);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="mb-6">
      <div>
        <label htmlFor="name" className="block text-sm font-medium text-gray-700">Name</label>
        <input
          type="text"
          id="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
          className="w-full p-2 border border-gray-300 rounded-md"
        />
      </div>
      <div className="mt-4">
        <label htmlFor="description" className="block text-sm font-medium text-gray-700">Description</label>
        <input
          type="text"
          id="description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
          className="w-full p-2 border border-gray-300 rounded-md"
        />
      </div>
      <div className="mt-4">
        <label htmlFor="price" className="block text-sm font-medium text-gray-700">Price</label>
        <input
          type="number"
          id="price"
          value={price}
          onChange={(e) => setPrice(Number(e.target.value))}
          required
          className="w-full p-2 border border-gray-300 rounded-md"
        />
      </div>
      <div className="mt-4">
        <label htmlFor="image" className="block text-sm font-medium text-gray-700">Image</label>
        <input
          type="file"
          id="image"
          onChange={(e) => setImage(e.target.files ? e.target.files[0] : null)}
          required
          className="w-full p-2 border border-gray-300 rounded-md"
        />
      </div>
      <button type="submit" className="mt-6 bg-blue-500 text-white p-2 rounded-lg">
        Add Merchandise
      </button>
    </form>
  );
};

export default AddMerchForm;