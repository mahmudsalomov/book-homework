package uz.pdp.bookhomework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.bookhomework.entity.Book;
import uz.pdp.bookhomework.payload.ApiResponse;
import uz.pdp.bookhomework.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

//    public boolean existById(Long id){
//        return bookRepository.existsById(id);
//    }

    public ApiResponse save(Book book){
        try {
            if (book.getId()!=null){

                if (bookRepository.existsById(book.getId())){
                    bookRepository.save(book);
                    return new ApiResponse("Edited",true);
                } else return new ApiResponse("Bunaqa id li kitob mavjud emas",false);


            }
            else {
                bookRepository.save(book);
                return new ApiResponse("Added",true);
            }
        } catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error",false);
        }
    }


    public ApiResponse getAll(){
        return new ApiResponse("Succes",true,bookRepository.findAll());
    }
    
    public ApiResponse delete(Long id){
        
        try {
            bookRepository.deleteById(id);
            return new ApiResponse("Deleted",true);
        } catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error",false);
        }
        
    }



    public ApiResponse fake(Long id) {
        Book book=bookRepository.getOne(id);
        book.setActive(false);
        try {
            save(book);
            return new ApiResponse("Deleted",true);
        } catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }

    public Book getOne(Long id) {
        return bookRepository.getOne(id);
    }
}
