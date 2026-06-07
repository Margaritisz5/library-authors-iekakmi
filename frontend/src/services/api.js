import axios from 'axios';

const api = axios.create({ baseURL: 'http://localhost:8080' });

export const getBooks = () => api.get('/books');
export const getBook = id => api.get(`/books/${id}`);
export const createBook = data => api.post('/books', data);
export const updateBook = (id, data) => api.put(`/books/${id}`, data);
export const deleteBook = id => api.delete(`/books/${id}`);
export const assignAuthors = (id, authorIds) => api.put(`/books/${id}/authors`, authorIds);

export const getAuthors = () => api.get('/authors');
export const getAuthor = id => api.get(`/authors/${id}`);
export const createAuthor = data => api.post('/authors', data);
export const updateAuthor = (id, data) => api.put(`/authors/${id}`, data);
export const deleteAuthor = id => api.delete(`/authors/${id}`);
export const getAuthorBooks = id => api.get(`/authors/${id}/books`);
