import React, { useState } from 'react';

const ImageUpload = () => {
  const [file, setFile] = useState<File | null>(null);
  const [message, setMessage] = useState<string | null>(null);

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files) {
      setFile(event.target.files[0]);
    }
  };

  const handleUpload = async () => {
    if (!file) {
      setMessage("Please select a file to upload.");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      const response = await fetch("http://localhost:5236/api/uploadimage", {
        method: "POST",
        body: formData,
      });

      if (!response.ok) {
        throw new Error("Failed to upload image.");
      }

      const data = await response.json();
      setMessage(`Image uploaded successfully! File name: ${data.FileName}`);
    } catch (error) {
      console.error("Error uploading image:", error);
      setMessage("Error uploading image.");
    }
  };

  return (
    <div>
      <h2>Upload Image</h2>
      <input type="file" onChange={handleFileChange} />
      <button onClick={handleUpload}>Upload</button>
      {message && <p>{message}</p>}
      
      {/* Display the uploaded image with fixed size */}
      {file && (
        <div>
          <h3>Uploaded Image</h3>
          <img 
            src={URL.createObjectURL(file)} 
            alt="Uploaded" 
            className="w-32 h-32 object-cover"  
          />
        </div>
      )}
    </div>
  );
};

export default ImageUpload;