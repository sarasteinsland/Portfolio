import axios from "axios";


interface UploadResponse {
    fileName: string;
}

const ImageUploadService = (() => {
    const imageUploadEndpoint = "http://localhost:5236/api/uploadImage";

    const uploadImage = async (image: File): Promise<UploadResponse> => {
        const formData = new FormData();
        formData.append("file", image);

        try {
            const response = await axios({
                url: imageUploadEndpoint,
                method: "POST",
                data: formData,
                headers: { "Content-Type": "multipart/form-data" }, 
            });

            return response.data; 
        } finally {
            formData.delete("file"); 
        }
    };

    return {
        uploadImage,
    };
})();

export default ImageUploadService;