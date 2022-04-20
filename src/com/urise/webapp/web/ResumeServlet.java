package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().createNewSqlStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String name = request.getParameter("name");
        response.getWriter().write(
                "<html>\n" +
                        "<head>\n" +
                            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                            "<link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                            "<title>Резюме</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<table border=\"1\" cellpadding=\"8\" cellspacing=\"0\">\n" +
                            "<tr>\n" +
                                "<th>UUID</th>\n" +
                                "<th>Name</th>\n" +
                            "</tr>\n");
        for (Resume r : storage.getAllSorted()) {
            response.getWriter().write(
                    "<tr>\n" +
                            "<td>" + r.getUuid() + "</td>\n" +
                            "<td>" + r.getFullName() + "</td>\n" +
                       "</tr>\n"
            );
        }
        response.getWriter().write(
                    "</table>\n" +
                "</body>\n" +
                "</html>\n");
    }
}
