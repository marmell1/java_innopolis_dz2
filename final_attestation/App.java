


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import org.flywaydb.core.Flyway;

public class App {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(App.class.getClassLoader().getResourceAsStream("application.properties"));
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }   

    }

    public static void main(String[] args) {
        try (Connection connection = createConnection()){
            if (connection != null){
                flywayMigrate(connection);
                performCrudOperation(connection);
            }
        }
        catch (Exception e ) {
            e.printStackTrace();
        }

    }


    private static Connection createConnection() throws Exception{
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(
            properties.getProperty("url"),
            properties.getProperty("user"),
            properties.getProperty("password")
        );
    }

    private static void flywayMigrate(Connection connection){

        Flyway.configure()
            .dataSource(properties.getProperty("url"),properties.getProperty("user"),properties.getProperty("password"))
            .load()
            .migrate();

    }

    private static void performCrudOperation(Connection connection) throws Exception{
        try {
            connection.setAutoCommit(false);

            insertNewProductAndCustomer(connection);


            connection.commit();
            
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }


private static void insertNewProductAndCustomer(Connection connection) throws Exception{
    PreparedStatement ps = connection.prepareStatement("INSERT INTO product(description, price, quantity, category, brend) VALUES (?,?,?,?,?)");
    ps.setString(1,"Мука");
    ps.setDouble(2,50.00);
    ps.setInt(3,10);
    ps.setString(4,"Продукты питания");
    ps.setString(5,"Макфа");
    
    ps.executeUpdate();

    ps = connection.prepareStatement("INSERT INTO customer(first_name, last_name, phone, email) VALUES (?,?,?,?)");
    ps.setString(1,"Михаил");
    ps.setString(2,"Иванов");
    ps.setString(3,"+79856248913");
    ps.setString(4,"mih@example.com");

    ps.executeUpdate();


}

    };





//         // URL подключения, логин, пароль

//         String url = "jdbc:postgresql://localhost:5433/postgres"; // для PostgreSQL
//         String username = "postgres";
//         String password = "0000";

//         Connection conn = null;
//         Statement stmt = null;
//         ResultSet rs = null;

//         try {
//             // Соединение с базой
//             conn = DriverManager.getConnection(url, username, password);
//             System.out.println("Подключено к базе данных");

//             // Создаем statement
//             stmt = conn.createStatement();

//             // Выполняем запрос
//             rs = stmt.executeQuery("SELECT * FROM customer");

//             // Обработка результата
//             while (rs.next()) {
//                 System.out.println("Результат: " + rs.getString("first_name"));
//             }

//         } catch (SQLException e) {
//             e.printStackTrace();

//         } finally {

//             // закрываем ресурсы
//             try {

//                 if (rs != null) rs.close();
//                 if (stmt != null) stmt.close();
//                 if (conn != null) conn.close();

//             } catch (SQLException e) {
//                 e.printStackTrace();

//             }

//         }

//     }

// }