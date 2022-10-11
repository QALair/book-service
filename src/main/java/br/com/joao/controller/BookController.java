package br.com.joao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joao.model.Book;
import br.com.joao.proxy.CambioProxy;
import br.com.joao.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "book endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookRepository repository;

	@Autowired
	private CambioProxy proxy;
	
	@Operation(summary = "Find a specific book by its ID")	
	@GetMapping(value = "/{id}/{currency}")
	public Book findBook(
			@PathVariable("id") Long id,
			@PathVariable("currency") String currency) {
		var book = repository.getById(id);
		if (book == null) throw new RuntimeException("Book not Found");
		

		var port = environment.getProperty("local.server.port");
		var cambio = proxy.getCambio(book.getPrice(),"USD", currency);
		book.setEnvironment("book port:"+port+ 
				"Cambio port"+ cambio.getEnvironment());

		book.setPrice(cambio.getConvertedValue());
		return book;
	}
//	@GetMapping(value = "/{id}/{currency}")
//	public Book findBook(
//			@PathVariable("id") Long id,
//			@PathVariable("currency") String currency) {
//		var book = repository.getById(id);
//		if (book == null) throw new RuntimeException("Book not Found");
//
//		HashMap<String, String> params = new HashMap<>();
//		params.put("amount", book.getPrice().toString());
//		params.put("from", "USD");
//		params.put("to", currency);
//
//		var response = new RestTemplate()
//			.getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}",
//					Cambio.class,
//					params);
//		var port = environment.getProperty("local.server.port");
//		book.setEnvironment(port);
//		var cambio = response.getBody();
//		book.setPrice(cambio.getConvertedValue());
//		return book;
//	}

}
