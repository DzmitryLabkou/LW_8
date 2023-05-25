package Servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Entity.ChatMessage;
import Entity.ChatUser;

public class NewMessageServlet extends ChatServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String message = (String)request.getParameter("message");

        if (message!=null && !"".equals(message)) {

            ChatUser author = activeUsers.get((String)
                    request.getSession().getAttribute("name"));
            author.SetTimeOut1(true);
            author.SetTimeOut2(true);
            author.SetTimeOut3(true);
            author.setLastSendMsg(Calendar.getInstance().getTimeInMillis());
            
            synchronized (messages) {
            	
                messages.add(new ChatMessage(message, author,
                        Calendar.getInstance().getTimeInMillis())); 
            }
        }
        response.sendRedirect("/mychat/compose_message.htm");
    }
}