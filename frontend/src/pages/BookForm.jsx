import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { createBook, getAuthors, getBook, updateBook } from '../services/api.js';

export default function BookForm() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [authors, setAuthors] = useState([]);
  const [form, setForm] = useState({ isbn: '', title: '', category: '', publicationYear: '', authorIds: [] });

  useEffect(() => {
    getAuthors().then(res => setAuthors(res.data));
    if (id) getBook(id).then(res => setForm({ ...res.data, authorIds: res.data.authorIds || [] }));
  }, [id]);

  const change = e => setForm({ ...form, [e.target.name]: e.target.value });
  const toggleAuthor = authorId => {
    const exists = form.authorIds.includes(authorId);
    setForm({ ...form, authorIds: exists ? form.authorIds.filter(x => x !== authorId) : [...form.authorIds, authorId] });
  };

  const submit = async e => {
    e.preventDefault();
    const payload = { ...form, publicationYear: Number(form.publicationYear) };
    if (id) await updateBook(id, payload); else await createBook(payload);
    navigate('/');
  };

  return <section className="form-card">
    <h1>{id ? 'Επεξεργασία Βιβλίου' : 'Προσθήκη Βιβλίου'}</h1>
    <form onSubmit={submit}>
      <label>ISBN<input name="isbn" value={form.isbn} onChange={change} required /></label>
      <label>Τίτλος<input name="title" value={form.title} onChange={change} required /></label>
      <label>Κατηγορία<input name="category" value={form.category} onChange={change} required /></label>
      <label>Έτος Έκδοσης<input type="number" name="publicationYear" value={form.publicationYear} onChange={change} required /></label>
      <fieldset><legend>Συγγραφείς</legend>{authors.map(a => <label key={a.id} className="check">
        <input type="checkbox" checked={form.authorIds.includes(a.id)} onChange={() => toggleAuthor(a.id)} /> {a.name}
      </label>)}</fieldset>
      <button className="button">Αποθήκευση</button>
    </form>
  </section>;
}
