package uz.pdp.bookhomework.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bookhomework.entity.Book;
import uz.pdp.bookhomework.payload.ApiResponse;
import uz.pdp.bookhomework.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(bookService.getAll());
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody Book book){
        ApiResponse apiResponse=bookService.save(book);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/edit")
    public HttpEntity<?> edit(@RequestBody Book book){
        ApiResponse apiResponse=bookService.save(book);
        return ResponseEntity.ok(apiResponse);
    }


    @DeleteMapping("/fake/{id}")
    public HttpEntity<?> fake(@PathVariable Long id){
        ApiResponse apiResponse=bookService.fake(id);
        return ResponseEntity.ok(apiResponse);
    }



    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse delete = bookService.delete(id);
        return ResponseEntity.ok(delete);
    }

}
