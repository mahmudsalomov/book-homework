package uz.pdp.bookhomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.bookhomework.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
