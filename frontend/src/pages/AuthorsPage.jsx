import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { deleteAuthor, getAuthors, getAuthorBooks } from '../services/api.js';

export default function AuthorsPage() {
  const [authors, setAuthors] = useState([]);
  const [booksByAuthor, setBooksByAuthor] = useState({});

  const loadAuthors = () => getAuthors().then(res => setAuthors(res.data));
  useEffect(() => { loadAuthors(); }, []);

  const showBooks = async id => {
    const res = await getAuthorBooks(id);
    setBooksByAuthor(prev => ({ ...prev, [id]: res.data }));
  };

  const remove = async id => {
    if (confirm('Να διαγραφεί ο συγγραφέας;')) {
      await deleteAuthor(id);
      loadAuthors();
    }
  };

  return <section>
    <h1>Λίστα Συγγραφέων</h1>
    <Link className="button" to="/authors/new">Προσθήκη Συγγραφέα</Link>
    <table>
      <thead><tr><th>Όνομα</th><th>Εθνικότητα</th><th>Ημ. Γέννησης</th><th>Βιβλία</th><th>Ενέργειες</th></tr></thead>
      <tbody>{authors.map(author => <tr key={author.id}>
        <td>{author.name}</td><td>{author.nationality}</td><td>{author.dateOfBirth}</td>
        <td><button onClick={() => showBooks(author.id)}>Προβολή</button><br />{booksByAuthor[author.id]?.map(b => b.title).join(', ')}</td>
        <td><Link to={`/authors/edit/${author.id}`}>Επεξεργασία</Link> | <button onClick={() => remove(author.id)}>Διαγραφή</button></td>
      </tr>)}</tbody>
    </table>
  </section>;
}
