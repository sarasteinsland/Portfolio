import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import { MerchProvider } from './context/MerchContext';
import reportWebVitals from './reportWebVitals';

const container = document.getElementById('root');
const root = ReactDOM.createRoot(container!);

root.render(
  <React.StrictMode>
    <MerchProvider>
      <App />
    </MerchProvider>
  </React.StrictMode>
);

reportWebVitals();
