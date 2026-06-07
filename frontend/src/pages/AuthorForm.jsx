import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { createAuthor, getAuthor, updateAuthor } from '../services/api.js';

export default function AuthorForm() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [form, setForm] = useState({ name: '', nationality: '', dateOfBirth: '' });

  useEffect(() => {
    if (id) getAuthor(id).then(res => setForm(res.data));
  }, [id]);

  const change = e => setForm({ ...form, [e.target.name]: e.target.value });

  const submit = async e => {
    e.preventDefault();
    if (id) await updateAuthor(id, form); else await createAuthor(form);
    navigate('/authors');
  };

  return <section className="form-card">
    <h1>{id ? 'Επεξεργασία Συγγραφέα' : 'Προσθήκη Συγγραφέα'}</h1>
    <form onSubmit={submit}>
      <label>Όνομα<input name="name" value={form.name} onChange={change} required /></label>
      <label>Εθνικότητα<input name="nationality" value={form.nationality} onChange={change} required /></label>
      <label>Ημερομηνία Γέννησης<input type="date" name="dateOfBirth" value={form.dateOfBirth} onChange={change} required /></label>
      <button className="button">Αποθήκευση</button>
    </form>
  </section>;
}
