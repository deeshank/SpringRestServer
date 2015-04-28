package com.deeshank.server;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deeshank.models.BookList;
import com.deeshank.models.Books;
import com.deeshank.models.Status;
import com.deeshank.models.Test;

@Controller
@RequestMapping("/views")
public class Server {

	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	@ResponseBody
	public String sayHello(@PathVariable String name) {
		return "Hello";
	}

	@RequestMapping(value = "/test/{name}", method = RequestMethod.GET)
	@ResponseBody
	public Test testJson(@PathVariable String name) {
		Test t = new Test();
		t.setName(name);
		return t;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	@ResponseBody
	public BookList testDB() {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createSQLQuery("Select * from books").addEntity(
				Books.class);
		List<Books> d = query.list();
		BookList books = new BookList();
		books.setBooks(d);
		session.getTransaction().commit();
		return books;

	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Books selectDb(@PathVariable String id) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createSQLQuery(
				"Select * from books where id=" + Integer.valueOf(id))
				.addEntity(Books.class);
		List<Books> d = query.list();
		Books book = null;
		for (Books b : d) {
			book = b;
		}
		session.getTransaction().commit();
		return book;

	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status removeBook(@PathVariable String id) {
		try {
			Session session = SessionFactoryUtil.getSessionFactory()
					.openSession();
			session.beginTransaction();
			// Query query = session.createSQLQuery(
			// "Delete from books where id=" + Integer.valueOf(id)).addEntity(
			// Books.class);
			Query query = session.createQuery("delete Books where id=:id");
			query.setParameter("id", Integer.valueOf(id));
			int res = query.executeUpdate();
			session.getTransaction().commit();
			if (res > 0)
				return new Status("OK");
			else
				throw new Exception("invalid Id");
		} catch (Exception e) {
			System.out.println(e.toString());
			return new Status(e.toString());

		}

	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Status insertBook(@RequestBody Books book) {
		try {
			Session session = SessionFactoryUtil.getSessionFactory()
					.openSession();
			session.beginTransaction();
			session.save(book);
			session.getTransaction().commit();
			return new Status("OK");
		} catch (Exception e) {
			System.out.println(e.toString());
			return new Status("Error");
		}

	}

	@RequestMapping(value = "/del/title/{title}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status deleteBookbyname(@PathVariable String title) {
		try {
			Session session = SessionFactoryUtil.getSessionFactory()
					.openSession();
			session.beginTransaction();
			Query query = session
					.createQuery("delete Books where title = :title");
			query.setParameter("title", title);
			int res = query.executeUpdate();
			session.getTransaction().commit();
			if (res > 0)
				return new Status("OK");
			else
				throw new Exception("Invalid Title");
		} catch (Exception e) {
			System.out.println(e.toString());
			return new Status("Err - " + e.toString());
		}

	}

}
