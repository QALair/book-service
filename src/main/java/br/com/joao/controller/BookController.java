package br.com.joao.controller;

import java.util.HashMap;

import br.com.joao.proxy.CambioProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.joao.model.Book;
import br.com.joao.repository.BookRepository;
import br.com.joao.response.Cambio;

@RestController
@RequestMapping("book-service")
public class BookController {
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookRepository repository;

	@Autowired
	private CambioProxy proxy;
	
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
