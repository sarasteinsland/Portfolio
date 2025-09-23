import React, { createContext, useContext, useState, useEffect, ReactNode } from 'react';
import MerchService from '../Services/MerchService';
import { TrumpMerchandise } from '../interfaces/TrumpMerch';

interface MerchContextProps {
    merch: TrumpMerchandise[];
    setMerch: React.Dispatch<React.SetStateAction<TrumpMerchandise[]>>;
    error: string | null;
    setError: React.Dispatch<React.SetStateAction<string | null>>;
    addMerch: (newItem: TrumpMerchandise, image: File | null) => Promise<void>;
    updateMerch: (id: number, updatedItem: TrumpMerchandise, image: File | null) => Promise<void>;
    deleteMerch: (id: number) => Promise<void>;
    fetchMerch: () => Promise<void>;
}

const MerchContext = createContext<MerchContextProps | undefined>(undefined);

export const useMerchContext = (): MerchContextProps => {
    const context = useContext(MerchContext);
    if (!context) {
        throw new Error('useMerchContext må brukes innenfor en MerchProvider');
    }
    return context;
};

interface MerchProviderProps {
    children: ReactNode;
}

const MerchProvider: React.FC<MerchProviderProps> = ({ children }) => {
    const [merch, setMerch] = useState<TrumpMerchandise[]>([]);
    const [error, setError] = useState<string | null>(null);

    const fetchMerch = async () => {
        try {
            const merchItems = await MerchService.getAllMerch();
            if (JSON.stringify(merchItems) !== JSON.stringify(merch)) {
                setMerch(merchItems);
            }
        } catch (error) {
            setError('There was an issue fetching the merchandise. Please try again later.');
            console.error(error);
        }
    };

    useEffect(() => {
        fetchMerch();
    }, []);

    const addMerch = async (newItem: TrumpMerchandise, image: File | null) => {
        try {
            console.log("Context addMerch:", newItem, image);
            if (image) {
                const uploadResponse = await MerchService.uploadImage(image);
                console.log("Upload response:", uploadResponse);
                newItem.image = uploadResponse.fileName; 
                console.log("Updated newItem with image:", newItem);
            }

            console.log("Context addMerch sender:", newItem);

            await MerchService.addMerch(newItem);
            setMerch((prev) => [...prev, newItem]); 
        } catch (error) {
            setError('could not add merch');
            console.error(error);
        }
    };

    const updateMerch = async (id: number, updatedItem: TrumpMerchandise, image: File | null) => {
        try {
          
            if (image) {
                const uploadResponse = await MerchService.uploadImage(image);
                updatedItem.image = uploadResponse.fileName; 
            }

           
            await MerchService.updateMerch(updatedItem);
            setMerch((prev) =>
                prev.map((item) => (item.id === id ? { ...item, ...updatedItem } : item))
            ); 
        } catch (error) {
            setError('Could not update the merch');
            console.error(error);
        }
    };

    const deleteMerch = async (id: number) => {
        try {
            await MerchService.deleteMerch(id);
            setMerch((prev) => prev.filter((item) => item.id !== id)); 
        } catch (error) {
            setError('Could not delete merch');
            console.error(error);
        }
    };

    return (
        <MerchContext.Provider
            value={{
                merch,
                setMerch,
                error,
                setError,
                addMerch,
                updateMerch,
                deleteMerch,
                fetchMerch,
            }}
        >
            {children}
        </MerchContext.Provider>
    );
};

export { MerchContext, MerchProvider };