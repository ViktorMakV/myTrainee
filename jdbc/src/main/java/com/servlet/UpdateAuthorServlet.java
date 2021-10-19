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

/**
 * @author Viktor Makarov
 */
public class UpdateAuthorServlet extends HttpServlet {
    private final AuthorService service = new AuthorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authorName = req.getParameter("authorName");
        Author author = service.findAuthor(authorName);
        req.setAttribute("author", author);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/updateAuthor.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");
        long id = 0;
        String name = req.getParameter("name");
        if (idString != null && !idString.equals("")) {
            id = Long.parseLong(idString);
        }
        Author author = service.findAuthor(id);
        if (author != null && !name.equals("")) {
            author.setName(name);
            service.updateAuthor(author);
        }
        resp.sendRedirect("/listAuthors");
    }
}
