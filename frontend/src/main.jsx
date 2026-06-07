import React from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import './style.css';
import BooksPage from './pages/BooksPage.jsx';
import AuthorsPage from './pages/AuthorsPage.jsx';
import BookForm from './pages/BookForm.jsx';
import AuthorForm from './pages/AuthorForm.jsx';

function App() {
  return (
    <BrowserRouter>
      <nav className="navbar">
        <h2>Library Manager</h2>
        <div>
          <Link to="/">Βιβλία</Link>
          <Link to="/authors">Συγγραφείς</Link>
          <Link to="/books/new">Νέο Βιβλίο</Link>
          <Link to="/authors/new">Νέος Συγγραφέας</Link>
        </div>
      </nav>
      <main className="container">
        <Routes>
          <Route path="/" element={<BooksPage />} />
          <Route path="/authors" element={<AuthorsPage />} />
          <Route path="/books/new" element={<BookForm />} />
          <Route path="/books/edit/:id" element={<BookForm />} />
          <Route path="/authors/new" element={<AuthorForm />} />
          <Route path="/authors/edit/:id" element={<AuthorForm />} />
        </Routes>
      </main>
    </BrowserRouter>
  );
}

createRoot(document.getElementById('root')).render(<App />);
