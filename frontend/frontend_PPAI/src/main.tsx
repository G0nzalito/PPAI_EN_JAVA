import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import { Route, Routes } from 'react-router-dom'
import PaginaInicio from './components/PaginaInicio'


createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <Routes>
      <Route path="/" element={<PaginaInicio />} />
    </Routes>
    <App />
  </StrictMode>,
)
