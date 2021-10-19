package com.servlet;

import com.service.AuthorService;
import com.service.AuthorServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Viktor Makarov
 */
public class AddBookServlet extends HttpServlet {
    private final AuthorService authorService = new AuthorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/addBook.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authorName = req.getParameter("author");
        String bookName = req.getParameter("book");
        boolean result = false;
        if (!authorName.equals("") && !bookName.equals("")) {
            result = authorService.addBookAndAuthor(authorName, bookName);
        } else if (!authorName.equals("")) {
            result = authorService.newAuthor(authorName);
        }
        req.setAttribute("result", result);
        doGet(req, resp);
    }
}
