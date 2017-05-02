/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ip300
 */

//@WebServlet(urlPatterns = {"/SSEController1"}, asyncSupported = true)
public final class SSEController1 extends HttpServlet {
    private final Queue<AsyncContext> longReqs = new ConcurrentLinkedQueue<>();
    private ScheduledExecutorService service;
    private Runnable notifier;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
       notifier = new Runnable() {
        @Override
        public void run() {
          final Iterator<AsyncContext> iterator = longReqs.iterator();
          while (iterator.hasNext()) {
            AsyncContext ac = iterator.next();
            Random random = new Random();
            final ServletResponse res = ac.getResponse();
            PrintWriter out;
            try {
              out = res.getWriter();
              String next = "data: " + 
                String.valueOf(random.nextInt(100) + 1) +
                "holllaa = " + longReqs.size() + "\n\n";
              out.write(next);
              if (out.checkError()) { 
                iterator.remove();
              }
            } catch (IOException ignored) {
              iterator.remove();
            }
          }
        }
      };
      

    }
//     @Override
//     public void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        response.getWriter().println("Hello");
//    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
      res.setContentType("text/event-stream");
      res.setCharacterEncoding("UTF-8");

      final AsyncContext ac = req.startAsync();
      ac.setTimeout(60 * 1000);
      ac.addListener(new AsyncListener() 
          { 
              @Override public void onComplete(AsyncEvent event) throws IOException {longReqs.remove(ac);}
              @Override public void onTimeout(AsyncEvent event) throws  IOException {longReqs.remove(ac);}
              @Override public void onError(AsyncEvent event) throws    IOException {longReqs.remove(ac);}
              @Override public void onStartAsync(AsyncEvent event) throws IOException {}
          });
      longReqs.add(ac);
        String[] par = req.getParameterValues("par");
      if (par[0].equals("true")){
//        service = Executors.newScheduledThreadPool(10);
//        service.scheduleAtFixedRate(notifier, 1, 1, TimeUnit.SECONDS);
        notifier.run();
      }      
    }
}
