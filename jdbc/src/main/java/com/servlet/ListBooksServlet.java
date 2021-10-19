package com.servlet;

import com.model.Author;
import com.model.Storage;
import com.service.AuthorService;
import com.service.AuthorServiceImpl;
import com.service.StorageService;
import com.service.StorageServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Viktor Makarov
 */
public class ListBooksServlet extends HttpServlet {
    private final AuthorService service = new AuthorServiceImpl();
    private final StorageService storageService = new StorageServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authorName = req.getParameter("authorName");
        Author author = null;
        List<Storage> storageList = new ArrayList<>();
        if (authorName != null) {
            author = service.findAuthor(authorName);
        }
        if (author != null) {
            author.getBooks().forEach(b -> storageList.add(storageService.getStorage(b)));
        }
        req.setAttribute("storageList", storageList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/listBooks.jsp");
        requestDispatcher.forward(req, resp);
    }
}
