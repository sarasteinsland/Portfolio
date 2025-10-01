import React, { useState} from 'react';
import { TrumpMerchandise } from '../interfaces/TrumpMerch';

interface EditMerchModalProps {
  item: TrumpMerchandise;
  onSave: (updatedItem: TrumpMerchandise, imageFile: File | null) => void;
  onClose: () => void;
}

const EditMerchModal: React.FC<EditMerchModalProps> = ({ item, onSave, onClose }) => {
  const [name, setName] = useState(item.name);
  const [description, setDescription] = useState(item.description);
  const [price, setPrice] = useState(item.price);
  const [image, setImage] = useState<File | null>(null);


  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSave({ ...item, name, description, price }, image);
    onClose();
  };

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
      <div className="bg-white p-6 rounded-lg shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold mb-4">Edit Merchandise</h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700">Name</label>
            <input
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
              className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm"
            />
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700">Description</label>
            <textarea
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm"
            />
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700">Price</label>
            <input
              type="number"
              value={price}
              onChange={(e) => setPrice(Number(e.target.value))}
              className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm"
            />
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700">Image</label>
            <input
              type="file"
              onChange={(e) => setImage(e.target.files ? e.target.files[0] : null)}
              className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm"
            />
          </div>
          <div className="flex justify-end space-x-4">
            <button type="button" onClick={onClose} className="bg-gray-500 text-white px-4 py-2 rounded">
              Cancel
            </button>
            <button type="submit" className="bg-blue-700 text-white px-4 py-2 rounded">
              Save
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default EditMerchModal;
