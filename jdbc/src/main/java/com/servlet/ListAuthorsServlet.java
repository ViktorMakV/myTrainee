package com.servlet;

import com.model.Author;
import com.service.AuthorService;
import com.service.AuthorServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Viktor Makarov
 */
public class ListAuthorsServlet extends HttpServlet {
    private final AuthorService service = new AuthorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = service.getAllAuthors();
        req.setAttribute("authors", authors);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/listAuthors.jsp");
        requestDispatcher.forward(req, resp);
    }
}
