import React from 'react';

type ImageCardProps = {
    id?: string; 
    imageUrl: string;
    title: string;
    description: string;
    price: number;
    onEdit: () => void;
};

const ImageCard: React.FC<ImageCardProps> = ({ id, imageUrl, title, description, price, onEdit }) => {
    return (
        <div className="bg-white p-4 rounded-lg shadow-md">
            <img src={imageUrl} alt={title} className="w-full h-48 object-cover rounded-md mb-4" />
            <h3 className="text-xl font-bold">{title}</h3>
            <p className="text-gray-600">{description}</p>
            <p className="text-blue-500 font-semibold mt-2">${price.toFixed(2)}</p>
            {id && <p className="text-gray-500 mt-2">ID: {id}</p>}
            <button
                onClick={onEdit}
                className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition mt-4"
            >
                Edit
            </button>
        </div>
    );
};

export default ImageCard;