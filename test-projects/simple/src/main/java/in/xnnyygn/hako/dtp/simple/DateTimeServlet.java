package in.xnnyygn.hako.dtp.simple;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

public class DateTimeServlet extends HttpServlet {

    private static final long serialVersionUID = -6325543815514744605L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
                                                                          IOException {
        resp.getWriter().write(DigestUtils.md5Hex(new Date().toString()));
    }

}
