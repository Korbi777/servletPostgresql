package com.kobyakov.servlet.servletpostgresql;
import java.io.*;
import java.sql.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Servlet", value = "/servlet")
public class Servlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        PrintWriter out = resp.getWriter();

        // Обработка исключений
        try { // Защищенный код, определяет блок кода, в котором может произойти исключение
            String url = "jdbc:postgresql://localhost:5432/test_db_servlet";
            String username = "postgres";
            String password = "KKV123789";
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM cities WHERE population >= 83000"); // результат запроса

                while (rs.next()) { // Прогоняется цикл, чтобы вывести все столбцы с бд
                    out.println("<b>City</b>: " + rs.getString("city") + "<b> Population</b>: " + rs.getString("population")+"<br>");
                }
            }
        }
        catch(Exception ex){  // определяет блок кода, в котором происходит обработка исключения
            writer.println("Connection failed...");
            writer.println(ex);
        }
        finally { // определяет блок кода, который является необязательным, но при его наличии выполняется в любом случае независимо от результатов выполнения блока try
            writer.close();
        }

    }
}
