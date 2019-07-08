package com.fstehle.counter;

import org.junit.*;

import java.sql.*;

public class CounterTest {

  private static final String jdbcUrl = "jdbc:h2:mem:;mode=MySQL";

  private Connection con;

  @Before
  public void initConnection() throws SQLException {
    this.con = DriverManager.getConnection(jdbcUrl);
  }

  @After
  public void closeConnection() throws SQLException {
    this.con.close();
    this.con = null;
  }

  @Test
  public void testCounterCount() throws Exception {
    Counter counter = new Counter(this.con);
    counter.createTable();

    int count = counter.count();
    Assert.assertEquals(1, count);
    int nextCount = counter.count();
    Assert.assertEquals(2, nextCount);
  }

}