# 🛍️ TrumpVerse – Merchandise Management Platform

**TrumpVerse** is a full-stack web application designed to manage merchandise items efficiently. Built as part of my final exam in *Web Development* (DS3103), this project demonstrates my ability to create responsive, accessible, and scalable solutions using modern technologies.

---

## 🌐 Technologies Used

### Frontend
- React with functional components and hooks  
- Axios for API communication  
- Tailwind CSS for styling and responsive layout  
- React Router for navigation  

### Backend
- ASP.NET Core Web API  
- Entity Framework Core with SQLite  
- CORS, Swagger, and static file handling  
- RESTful endpoints for full CRUD operations  

---

## 📦 Features

- **Add Merchandise**: Upload images and enter product details  
- **Edit Merchandise**: Update product info and replace images  
- **Delete Merchandise**: Remove items by ID  
- **View Merchandise**: Browse all products in a responsive grid  
- **Accessibility**: Semantic HTML, keyboard navigation, alt text, and high contrast design  

---

## 🎓 Academic Context and Evaluation

- **Course**: DS3103 – Web Development  
- **Institution**: Kristiania University College, Bergen  
- **Semester**: Fall 2024  
- **Grade**: A  

---

## 🧠 Project Structure

```plaintext
TrumpVerse/
├── frontend/              # React application with Tailwind CSS and Axios
│   ├── components/        # Reusable UI components
│   ├── pages/             # Home, Merch, Add Merch views
│   ├── assets/            # Static files and images
│   └── App.jsx            # Main application entry point
├── backend/               # ASP.NET Core Web API
│   ├── Controllers/       # MerchController and UploadImageController
│   ├── Models/            # Data models for merchandise items
│   ├── Data/              # Database context and configuration
│   └── Program.cs         # Application setup and middleware
├── TrumpVerse.sln         # Solution file for backend
└── README.md              # Project documentation

