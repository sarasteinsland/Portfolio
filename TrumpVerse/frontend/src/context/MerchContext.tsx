import React, { createContext, useState, useEffect, ReactNode, useCallback } from 'react';

export type MerchItem = {
  id: number;
  name: string;
  description: string;
  price: number;
  image: string;
};

interface MerchContextProps {
  merch: MerchItem[];
  setMerch: React.Dispatch<React.SetStateAction<MerchItem[]>>;
  error: string | null;
  setError: React.Dispatch<React.SetStateAction<string | null>>;
  addMerch: (newItem: { name: string; description: string; price: number; image: File }) => void;
  deleteMerch: (id: number) => void;
  fetchMerch: () => Promise<void>;
}

const MerchContext = createContext<MerchContextProps | undefined>(undefined);

const MerchProvider = ({ children }: { children: ReactNode }) => {
  const [merch, setMerch] = useState<MerchItem[]>([]);
  const [error, setError] = useState<string | null>(null);

  const fetchMerch = useCallback(async () => {
    try {
      const response = await fetch('http://localhost:5236/api/TrumpMerchandise');
      if (!response.ok) throw new Error('Failed to fetch merchandise');
      const data = await response.json();
      setMerch(data);
    } catch (error) {
      if (error instanceof Error) {
        setError(error.message);
      } else {
        setError('An unknown error occurred');
      }
      console.error('Error fetching merchandise:', error);
    }
  }, []);

  useEffect(() => {
    fetchMerch();
  }, [fetchMerch]);

  const addMerch = async (newItem: { name: string; description: string; price: number; image: File }) => {
    const formData = new FormData();
    formData.append('Name', newItem.name);
    formData.append('Description', newItem.description);
    formData.append('Price', newItem.price.toString());
    formData.append('Image', newItem.image);

    try {
      const response = await fetch('http://localhost:5236/api/TrumpMerchandise', {
        method: 'POST',
        body: formData,
      });
      if (!response.ok) throw new Error('Failed to add merchandise');
      const data = await response.json();
      setMerch((prevMerch) => [...prevMerch, data]);
    } catch (error) {
      if (error instanceof Error) {
        setError(error.message);
      } else {
        setError('An unknown error occurred');
      }
      console.error('Error adding merchandise:', error);
    }
  };

  const deleteMerch = async (id: number) => {
    try {
      const response = await fetch(`http://localhost:5236/api/TrumpMerchandise/${id}`, {
        method: 'DELETE',
      });
      if (!response.ok) throw new Error('Failed to delete merchandise');
      setMerch((prevMerch) => prevMerch.filter(item => item.id !== id));
    } catch (error) {
      if (error instanceof Error) {
        setError(error.message);
      } else {
        setError('An unknown error occurred');
      }
      console.error('Error deleting merchandise:', error);
    }
  };

  return (
    <MerchContext.Provider value={{ merch, setMerch, error, setError, addMerch, deleteMerch, fetchMerch }}>
      {children}
    </MerchContext.Provider>
  );
};

export { MerchContext, MerchProvider };