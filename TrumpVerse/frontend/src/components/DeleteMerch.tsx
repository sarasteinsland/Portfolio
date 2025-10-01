import React, { useState } from 'react';
import MerchService from '../Services/MerchService';
import { TrumpMerchandise } from '../interfaces/TrumpMerch';

const DeleteMerchById: React.FC = () => {
    const [id, setId] = useState<number | null>(null);
    const [merch, setMerch] = useState<TrumpMerchandise | null>(null);
    const [message, setMessage] = useState<string | null>(null);

    const handleFetchMerch = async () => {
        if (id !== null) {
            try {
                const fetchedMerch = await MerchService.getMerchById(id);
                setMerch(fetchedMerch);
                setMessage(null);
            } catch (error) {
                setMerch(null);
                setMessage(`No merchandise found with ID ${id}.`);
                console.error(error);
            }
        } else {
            setMerch(null);
            setMessage('Please enter a valid ID.');
        }
    };

    const handleDelete = async () => {
        if (id !== null) {
            try {
                await MerchService.deleteMerch(id);
                setMessage(`Merchandise with ID ${id} has been deleted.`);
                setMerch(null);
            } catch (error) {
                setMessage(`Failed to delete merchandise with ID ${id}.`);
                console.error(error);
            }
        } else {
            setMessage('Please enter a valid ID.');
        }
    };

    return (
        <div className="delete-merch">
            <h2>Delete Merchandise by ID</h2>
            <input
                type="number"
                placeholder="Enter ID"
                value={id !== null ? id : ''}
                onChange={(e) => setId(parseInt(e.target.value))}
                className="w-full p-2 border-gray-300 rounded"
            />
            <button
                onClick={handleFetchMerch}
                className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition mt-4"
            >
                Fetch Merchandise
            </button>
            {merch && (
                <div className="merch-info mt-4">
                    <h3 className="text-xl font-bold">{merch.name}</h3>
                    <p>{merch.description}</p>
                    <p>Price: ${merch.price.toFixed(2)}</p>
                    <img src={`/images/${merch.image}`} alt={merch.name} className="w-full h-48 object-cover rounded-md mb-4" />
                    <button
                        onClick={handleDelete}
                        className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600 transition mt-4"
                    >
                        Delete
                    </button>
                </div>
            )}
            {message && <p className="mt-4 text-indigo-600">{message}</p>}
        </div>
    );
};

export default DeleteMerchById;