package Servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entity.ChatMessage;
import Entity.ChatUser;

public class LogoutServlet extends ChatServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        String name = (String) request.getSession().getAttribute("name");

        if (name!=null) {

            ChatUser aUser = activeUsers.get(name);

            if (aUser.getSessionId().equals((String)
                    request.getSession().getId())) {

                synchronized (activeUsers) {
                    activeUsers.remove(name);
                }

                request.getSession().setAttribute("name", null);
                messages.add(new ChatMessage((String) name + " left the chat!", 
            			new ChatUser("System", Calendar.getInstance().getTimeInMillis(), request.getSession().getId()), Calendar.getInstance().getTimeInMillis()));
                response.addCookie(new Cookie("sessionId", null));

                response.sendRedirect(response.encodeRedirectURL("/mychat/"));
            } else {

                response.sendRedirect(response.encodeRedirectURL("/mychat/view.htm"));
            }
        } else {

            response.sendRedirect(response.encodeRedirectURL("/mychat/view.htm"));
        }
    }
}
