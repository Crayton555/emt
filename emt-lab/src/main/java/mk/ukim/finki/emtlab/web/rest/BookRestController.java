package mk.ukim.finki.emtlab.web.rest;

import mk.ukim.finki.emtlab.model.Book;
import mk.ukim.finki.emtlab.model.dto.BookDto;
import mk.ukim.finki.emtlab.model.enumerations.Category;
import mk.ukim.finki.emtlab.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    private List<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

//    @PostMapping("/add")
//    public ResponseEntity<Book> save(@RequestParam String name,
//                                     @RequestParam Category category,
//                                     @RequestParam Long authorId,
//                                     @RequestParam Integer availableCopies) {
//        return this.bookService.save(name, category, authorId, availableCopies)
//                .map(book -> ResponseEntity.ok().body(book))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return this.bookService.edit(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.deleteById(id);
        if (this.bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("borrowBook/{id}")
    public ResponseEntity borrowBook(@PathVariable Long id){
        Optional<Book> book = this.bookService.findById(id);
        if (book.isEmpty())
            return ResponseEntity.badRequest().build();
        this.bookService.borrowBook(id);
        return ResponseEntity.ok().body(book);
    }
}