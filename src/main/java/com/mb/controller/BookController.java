package com.mb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mb.entity.Book;
import com.mb.service.BookService;

@Controller
public class BookController {
	@Autowired
	private BookService service;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
//		Sending empty object for form binding
		mav.addObject("book", new Book());
		mav.setViewName("index");
		return mav;
	}
	
	@PostMapping("/book")
	public ModelAndView saveBook(Book book) {
		ModelAndView mav = new ModelAndView();
		boolean status = service.saveBook(book);
		if(status) {
			mav.addObject("successMsg", "Book saved");
		}else {
			mav.addObject("errorMsg", "Failed to save");
		}
		mav.setViewName("index");
		return mav;
		
	}
	
	
	
	@GetMapping("/books")
	public ModelAndView getBooks() {
		ModelAndView mav = new ModelAndView();
		List<Book> allBooks = service.getAllBooks();
		mav.addObject("books", allBooks);
		mav.setViewName("booksView");
		return mav;
		
	}
	
	@GetMapping("/delete")
	public ModelAndView deleteBook(@RequestParam("bookId") Integer bookId) {
		service.deleteBook(bookId);
		ModelAndView mav = new ModelAndView();
		List<Book> allBooks = service.getAllBooks();
		mav.addObject("books", allBooks);
		mav.setViewName("booksView");
		return mav;
	}
	
	@GetMapping("/edit")
	public ModelAndView editBook(@RequestParam("bookId") Integer bookId) {
		 Book bookObj = service.getBookById(bookId);
		ModelAndView mav = new ModelAndView();
//		Sending empty object for form binding
		mav.addObject("book", bookObj);
		mav.setViewName("index");
		return mav;
	}

}
