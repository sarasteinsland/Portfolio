import axios from 'axios';
import { TrumpMerchandise } from '../interfaces/TrumpMerch';

const MerchService = (() => {
    const productUrl = 'http://localhost:5236/api/TrumpMerchandise';

    
    const addMerch = async (newMerch: TrumpMerchandise): Promise<void> => {
        try {
            console.log("Sending new merchandise:", newMerch);
            const response = await axios.post(productUrl, newMerch);
            console.log("Response from server:", response.data);
        } catch (error) {
            if (axios.isAxiosError(error)) {
                console.error("Axios error response data:", error.response?.data);
                if (error.response?.data?.errors) {
                    console.error("Validation errors:", error.response.data.errors);
                }
            }
            console.error("Error adding merchandise:", error);
        }
    };

  
    const deleteMerch = async (id: number): Promise<void> => {
        try {
            await axios.delete(`${productUrl}/${id}`);
        } catch (error) {
            console.error("There was an error deleting the merchandise:", error);
        }
    };

   
    const updateMerch = async (updatedMerch: TrumpMerchandise): Promise<void> => {
        try {
            await axios.put(`${productUrl}/${updatedMerch.id}`, updatedMerch);
        } catch (error) {
            console.error("There was an error updating the merchandise:", error);
        }
    };

    
    const getAllMerch = async (): Promise<TrumpMerchandise[]> => {
        try {
            const response = await axios.get(productUrl);
            return response.data;
        } catch (error) {
            console.error("Error fetching merchandise:", error);
            throw new Error("Failed to fetch merchandise.");
        }
    };

   
    const getMerchById = async (id: number): Promise<TrumpMerchandise> => {
        try {
            const response = await axios.get(`${productUrl}/${id}`);
            return response.data;
        } catch (error) {
            console.error("Error fetching merchandise by ID:", error);
            throw new Error("Failed to fetch merchandise by ID.");
        }
    };

   
    const getMerchByName = async (title: string): Promise<TrumpMerchandise[]> => {
        try {
            const response = await axios.get(`${productUrl}/name/${title}`);
            return response.data;
        } catch (error) {
            console.error("Error fetching merchandise by name:", error);
            throw new Error("Failed to fetch merchandise by name.");
        }
    };
    
    const uploadImage = async (image: File): Promise<{ fileName: string }> => {
        const formData = new FormData();
        formData.append('file', image);
    
        try {
            const response = await axios.post('http://localhost:5236/api/UploadImage', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });
            console.log("Upload response data:", response.data);
            return response.data;
        } catch (error) {
            console.error("Error uploading image:", error);
            throw new Error("Failed to upload image.");
        }
    };
  

    return {
        addMerch,
        deleteMerch,
        updateMerch,
        getAllMerch,
        getMerchById,
        getMerchByName,
        uploadImage,
    };
})();

export default MerchService;