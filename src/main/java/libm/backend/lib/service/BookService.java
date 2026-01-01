package libm.backend.lib.service;

import libm.backend.lib.dto.BookDto;
import java.util.List;

public interface BookService {
    BookDto getBookById(Long id);
    List<BookDto> getAllBooks();
    BookDto addBook(BookDto bookDto);
    BookDto updateBook(Long id, BookDto bookDto);
    void deleteBook(Long id);
    BookDto getBookByIsbn(String isbn);
}




