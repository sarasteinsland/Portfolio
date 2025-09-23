import React, { useState } from "react";
import ImageUploadService from "../Services/ImageUploadService";
import axios from "axios";

interface UploadResponse {
    fileName: string;  
}

const ImageUpload = () => {
    const [selectedImage, setSelectedImage] = useState<File | null>(null);
    const [uploading, setUploading] = useState(false);
    const [uploadResult, setUploadResult] = useState<string | null>(null);
    const [error, setError] = useState<string | null>(null);
    const [merchandise, setMerchandise] = useState({
        name: '',
        description: '',
        price: 0,
        image: '', 
    });

    const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files?.[0];
        if (file) {
            setSelectedImage(file);
            setUploadResult(null);
            setError(null);
        }
    };

    const handleUpload = async () => {
        if (!selectedImage) {
            setError("Please select an image first.");
            return;
        }

        setUploading(true);
        try {
            const imageUrlResponse: UploadResponse = await ImageUploadService.uploadImage(selectedImage);
            setUploadResult(imageUrlResponse.fileName);  
            setMerchandise((prev) => ({ ...prev, image: imageUrlResponse.fileName })); 
            setSelectedImage(null);
        } catch (error) {
            setError("Failed to upload image. Please try again.");
            console.error("Upload error:", error);
        } finally {
            setUploading(false);
        }
    };

    const handleAddNewMerchandise = async () => {
        try {
            console.log("Adding merchandise:", merchandise);
            await axios.post('http://localhost:5236/api/TrumpMerchandise', merchandise);
        } catch (error) {
            console.error("Error adding merchandise:", error);
        }
    };

    return (
        <div>
            <h2>Upload Image</h2>
            <input type="file" accept="image/*" onChange={handleImageChange} />
            {selectedImage && <p>Selected file: {selectedImage.name}</p>}
            <button onClick={handleUpload} disabled={uploading}>
                {uploading ? "Uploading..." : "Upload"}
            </button>
            {uploadResult && <p>Upload successful: {uploadResult}</p>}
            {error && <p style={{ color: "red" }}>{error}</p>}
            <button onClick={handleAddNewMerchandise}>Add Merchandise</button>
        </div>
    );
};

export default ImageUpload;