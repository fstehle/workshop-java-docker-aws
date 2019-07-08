package com.fstehle.counter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * HealthCheck servlet.
 */

public final class HealthCheckServlet extends HttpServlet {

  /**
   * Respond to a GET request for the content produced by
   * this servlet.
   *
   * @param request  The servlet request we are processing
   * @param response The servlet response we are producing
   * @throws IOException      if an input/output error occurs
   * @throws ServletException if a servlet error occurs
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setStatus(200);
    response.setContentType("text/plain");
    PrintWriter writer = response.getWriter();
    writer.println("ok");
  }
}