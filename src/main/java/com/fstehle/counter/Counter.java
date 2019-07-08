package com.fstehle.counter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Counter {
  private final Connection connection;

  public Counter(Connection connection) {
    this.connection = connection;
  }

  public void createTable() throws SQLException {
    Statement stmt = this.connection.createStatement();
    stmt.execute("CREATE TABLE IF NOT EXISTS counter (id INTEGER, val INTEGER, PRIMARY KEY (id))");
  }

  public int count() throws Exception {
    Statement incrementStmt = this.connection.createStatement();
    incrementStmt.executeUpdate("INSERT INTO counter (id, val) VALUES(1, 1) ON DUPLICATE KEY UPDATE val=val+1");

    return this.getCurrentCount();
  }

  public int getCurrentCount() throws Exception {
    Statement queryStmt = this.connection.createStatement();
    ResultSet rs = queryStmt.executeQuery("SELECT val FROM counter WHERE id=1 LIMIT 1");

    if (rs.next()) {
      return rs.getInt(1);
    }

    return 0;
  }

}
