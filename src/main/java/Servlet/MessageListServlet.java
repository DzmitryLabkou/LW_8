package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entity.ChatMessage;
import Entity.ChatUser;


public class MessageListServlet extends ChatServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        response.setCharacterEncoding("utf8");

        PrintWriter pw = response.getWriter();

        pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><meta http-equiv='refresh' content='10'></head>");
        pw.println("<body>");

        for (int i=messages.size()-1; i>=0; i--) {
            ChatMessage aMessage = messages.get(i);
            if(aMessage.getAuthor().getName().equals("System")){
//            	//pw.println();
            	pw.println("<div style='color:#FF0000'><strong>" + aMessage.getAuthor().getName()
                        + "</strong>: " + aMessage.getMessage() +  "</div>");
            } else {
            	pw.println("<div><strong>" + aMessage.getAuthor().getName()
                        + "</strong>: " + aMessage.getMessage() + "</div>");
            }
            
            
        }
        pw.println("</body></html>");
    }
}
