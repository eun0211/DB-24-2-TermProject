/*
 * 이다창(20241115)
 * 데이터베이스와연결하는 로직 정의
 * 싱글턴 패턴으로 정의하여 여러개의 인스턴스를 생성하지 않도록 정의
 * 연결이 닫히게 되면 자동으로 메세지 띄움
 */

package twitter_t;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/twitter_t";
    private static final String USER = "root";
    private static final String PASSWORD = "01234567890";

    private static Connection connection = null;

    // DB 연결을 가져오는 메서드 (싱글턴 패턴)
    public static Connection getConnection() {
        try {
            // 기존 연결이 없거나 연결이 끊어진 경우에만 새로운 연결을 생성
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                // JOptionPane 메시지 제거
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("데이터베이스 연결에 실패했습니다.");
        }

        return connection;
    }

    // 프로그램 종료 시 DB 연결을 닫는 메서드
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("데이터베이스 연결이 닫혔습니다.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
