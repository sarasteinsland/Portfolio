import React, { useState } from 'react';
import { useMerchContext } from '../context/MerchContext';
import ImageUploadService from '../Services/ImageUploadService';
import { TrumpMerchandise } from '../interfaces/TrumpMerch';

const AddMerchandise: React.FC = () => {
    const { addMerch } = useMerchContext();
    const UploadStatus = {
        Idle: "Idle",
        Uploading: "Uploading",
        Finishing: "Finishing",
        Error: "Error"
    };

    const [image, setImage] = useState<File | null>(null);
    const [newMerch, setNewMerch] = useState<TrumpMerchandise>({
        id: undefined, 
        name: "",
        description: "",
        price: 0,
        image: ""
    });
    const [status, setStatus] = useState<string>(UploadStatus.Idle);

    const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files ? e.target.files[0] : null;
        setImage(file);
    };

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setNewMerch((prevState) => ({
            ...prevState,
            [name]: name === "price" ? parseFloat(value) : value,
        }));
    };

    const handleAddNewMerchandise = async () => {
        setStatus(UploadStatus.Uploading);
        try {
            if (image) {
                const response = await ImageUploadService.uploadImage(image);
                const merch: TrumpMerchandise = {
                    ...newMerch,
                    id: generateUniqueId(), 
                    image: response.fileName,
                };
                console.log("Adding merchandise:", merch);
                await addMerch(merch, image); 
                setNewMerch({
                    id: undefined, 
                    name: "",
                    description: "",
                    price: 0,
                    image: ""
                });
                setStatus(UploadStatus.Finishing);
            } else {
                setStatus(UploadStatus.Error);
            }
        } catch (error) {
            console.error("Upload error:", error);
            setStatus(UploadStatus.Error);
        }
    };

    const generateUniqueId = (): number => {
        return Math.floor(Math.random() * 1000000); 
    };

    return (
        <div className="bg-red-600 min-h-screen flex flex-col items-center justify-center p-6">
            <h1 className="text-4xl font-extrabold text-white mb-6">Add Merchandise</h1>
            <div className="w-full max-w-md space-y-4">
                <input
                    type="text"
                    placeholder="Name"
                    className="w-full p-3 border-2 border-white rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none"
                    value={newMerch.name}
                    onChange={handleChange}
                    name="name"
                />
                <input
                    type="text"
                    placeholder="Description"
                    className="w-full p-3 border-2 border-white rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none"
                    value={newMerch.description}
                    onChange={handleChange}
                    name="description"
                />
                <input
                    type="number"
                    placeholder="Price"
                    className="w-full p-3 border-2 border-white rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none"
                    value={newMerch.price}
                    onChange={handleChange}
                    name="price"
                />
                <input
                    type="file"
                    accept="image/*"
                    onChange={handleImageChange}
                    className="w-full p-3 border-2 border-white rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none"
                />
                <button
                    onClick={handleAddNewMerchandise}
                    className="w-full bg-blue-600 text-white hover:bg-blue-700 font-bold p-3 rounded-lg"
                >
                    Add New Merchandise
                </button>
            </div>
            {status && <p className="mt-4 text-yellow-300 text-center">{status}</p>}
        </div>
    );
};

export default AddMerchandise;