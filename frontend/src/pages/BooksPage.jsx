import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { deleteBook, getBooks } from '../services/api.js';

export default function BooksPage() {
  const [books, setBooks] = useState([]);

  const loadBooks = () => getBooks().then(res => setBooks(res.data));

  useEffect(() => { loadBooks(); }, []);

  const remove = async id => {
    if (confirm('Να διαγραφεί το βιβλίο;')) {
      await deleteBook(id);
      loadBooks();
    }
  };

  return <section>
    <h1>Λίστα Βιβλίων</h1>
    <Link className="button" to="/books/new">Προσθήκη Βιβλίου</Link>
    <table>
      <thead><tr><th>ISBN</th><th>Τίτλος</th><th>Κατηγορία</th><th>Έτος</th><th>Συγγραφείς</th><th>Ενέργειες</th></tr></thead>
      <tbody>{books.map(book => <tr key={book.id}>
        <td>{book.isbn}</td><td>{book.title}</td><td>{book.category}</td><td>{book.publicationYear}</td>
        <td>{book.authors?.map(a => a.name).join(', ') || '-'}</td>
        <td><Link to={`/books/edit/${book.id}`}>Επεξεργασία</Link> | <button onClick={() => remove(book.id)}>Διαγραφή</button></td>
      </tr>)}</tbody>
    </table>
  </section>;
}
