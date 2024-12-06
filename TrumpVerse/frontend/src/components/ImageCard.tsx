import React from "react";

interface ImageCardProps {
  imageUrl: string;
  title: string;
  description: string;
  price?: number;
  role?: string;
  email?: string;
  phone?: string;
}

const ImageCard = ({
  imageUrl,
  title,
  description,
  price,
  role,
  email,
  phone
}: ImageCardProps) => {
  return (
    <div className="bg-white p-4 border border-gray-200 rounded-lg shadow-md hover:shadow-xl transition-shadow duration-300">
      <div className="flex flex-col items-center">
        {/* Image */}
        <div className="w-34 h-34 mb-4">
        <img
          src={imageUrl}
             alt={title}
             className="w-full h-full object-cover rounded-md"
            />
        </div>

        {/* Text */}
        <div className="text-center">
          <h3 className="text-md font-semibold mb-1">{title}</h3>
          <p className="text-xs text-gray-500 mb-2">{description}</p>

          {/* Conditional Fields */}
          {role && <p className="text-xs text-gray-500 mb-1">Role: {role}</p>}
          {email && <p className="text-xs text-gray-500 mb-1">Email: {email}</p>}
          {phone && <p className="text-xs text-gray-500 mb-1">Phone: {phone}</p>}

          {/* Price */}
          {price !== undefined && (
            <p className="font-semibold text-lg text-green-600">${price.toFixed(2)}</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default ImageCard;
