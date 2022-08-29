package br.com.joao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joao.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
