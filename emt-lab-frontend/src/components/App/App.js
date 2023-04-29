import logo from '../../logo.svg';
import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import Countries from "../Countries/countries"
import Authors from "../Authors/authors"
import Categories from "../Categories/categories"
import Books from "../Books/BookList/books"
import LibraryService from "../../repository/libraryRepository";
import Header from '../Header/header';
import BookAdd from '../Books/BookAdd/bookAdd';
import BookEdit from "../Books/BookEdit/bookEdit";

// import Login from "../Login/login";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            countries: [], authors: [], books: [], categories: [], selectedBook: {}
        }
    }

    render() {
        return (<Router>
            <Header/>
            <main>
                <div className="container">
                    <Route path={"/countries"} exact render={() => <Countries countries={this.state.countries}/>}/>
                    <Route path={"/authors"} exact render={() => <Authors authors={this.state.authors}/>}/>
                    <Route path={"/categories"} exact render={() => <Categories categories={this.state.categories}/>}/>
                    <Route path={"/books/add"} exact render={() => <BookAdd countries={this.state.countries}
                                                                            authors={this.state.authors}
                                                                            onAddBook={this.addBook}/>}/>
                    <Route path={"/books/edit/:id"} exact render={() => <BookEdit countries={this.state.countries}
                                                                                  authors={this.state.authors}
                                                                                  onEditBook={this.editBook}
                                                                                  book={this.state.selectedBook}/>}/>
                    <Route path={"/books"} exact render={() => <Books books={this.state.books}
                                                                      onDelete={this.deleteBook}
                                                                      onEdit={this.getBook}
                                                                      onBorrowBook={this.borrowBook}/>}/>
                    {/*<Route path={"/login"} exact render={() => <Login onLogin={this.fetchData}/>}/>*/}
                    {/*<Redirect to={"/books"}/>*/}
                </div>
            </main>
        </Router>);
    }

    componentDidMount() {
        this.fetchData()
    }

    fetchData = () => {
        this.loadCountries();
        this.loadAuthors();
        this.loadBooks();
        this.loadCategories();
    }
    loadCountries = () => {
        LibraryService.fetchCountries()
            .then((data) => {
                this.setState({
                    countries: data.data
                })
            });
    }
    loadAuthors = () => {
        LibraryService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }
    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }
    loadBooks = () => {
        LibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    deleteBook = (id) => {
        LibraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    addBook = (name, category, author, availableCopies) => {
        LibraryService.addBook(name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        LibraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }

    editBook = (id, name, category, author, availableCopies) => {
        LibraryService.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    borrowBook = (id) => {
        LibraryService.borrowBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }
}

export default App;
