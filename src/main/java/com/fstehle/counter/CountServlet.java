package com.fstehle.counter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple CountServlet servlet.
 */

public final class CountServlet extends HttpServlet {

  private static final Logger log = Logger.getLogger(CountServlet.class.getName());

  public CountServlet() {
    try {
      Class.forName ("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      log.log(Level.SEVERE, "error loading mysql jdbc driver", e);
    }

    try (Connection connection = getConnection()) {
      Counter counter = new Counter(connection);
      counter.createTable();
    } catch (SQLException e) {
      log.log(Level.SEVERE, "error while creating table", e);
    }
  }

  /**
   * Respond to a GET request for the content produced by
   * this servlet.
   *
   * @param request  The servlet request we are processing
   * @param response The servlet response we are producing
   * @throws IOException      if an input/output error occurs
   * @throws ServletException if a servlet error occurs
   */
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws IOException, ServletException {

    try (Connection connection = getConnection()) {
      Counter counter = new Counter(connection);

      int count;

      if (request.getQueryString().contains("increase=true")) {
        count = counter.count();
      } else {
        count = counter.getCurrentCount();
      }

      response.setContentType("text/plain");
      PrintWriter writer = response.getWriter();
      writer.println(count);

    } catch (Exception e) {
      log.log(Level.SEVERE, "error while counting", e);
      response.setStatus(500);
      response.setContentType("text/plain");
      PrintWriter writer = response.getWriter();
      writer.println("error while counting");
    }
  }

  private Connection getConnection() throws SQLException {
    String jdbcUrl = getJdbcUrl();
    String mysqlUser = getMysqlUser();
    String mysqlPassword = getMysqlPassword();
    return DriverManager.getConnection(jdbcUrl, mysqlUser, mysqlPassword);
  }

  private String getJdbcUrl() {
    return String.format("jdbc:mysql://%s/%s", getMysqlHost(), getMysqlDatabase());
  }

  private String getMysqlHost() {
    return System.getenv("MYSQL_HOST");
  }

  private String getMysqlDatabase() {
    return System.getenv("MYSQL_DATABASE");
  }

  private String getMysqlUser() {
    return System.getenv("MYSQL_USER");
  }

  private String getMysqlPassword() {
    return System.getenv("MYSQL_PASSWORD");
  }
}