package vote.sample;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sample")
public class SampleServlet extends HttpServlet {

    @Inject
    private SampleEjb ejb;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.ejb.persist(req.getParameter("title"), req.getParameter("summary"));
        this.ejb.showAll();
    }
}
