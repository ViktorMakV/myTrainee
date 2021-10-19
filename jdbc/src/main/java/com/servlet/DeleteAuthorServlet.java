package com.servlet;

import com.model.Author;
import com.service.AuthorService;
import com.service.AuthorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Viktor Makarov
 */
public class DeleteAuthorServlet extends HttpServlet {
    private final AuthorService authorService = new AuthorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/listAuthors");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authorName = req.getParameter("authorName");
        Author author = null;
        if (authorName != null) {
            author = authorService.findAuthor(authorName);
        }
        if (author != null) {
            authorService.deleteAuthor(author);
        }
        doGet(req, resp);
    }
}
