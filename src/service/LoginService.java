package service;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import twitter_t.DatabaseConnection;

public class LoginService {

    public static boolean login(String userId, String password) {
        String query = "SELECT * FROM user WHERE user_id = ? AND password = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setString(1, userId);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // 로그인 성공 메시지 수정
                JOptionPane.showMessageDialog(null, "로그인 성공", "알림", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                // 로그인 실패
                JOptionPane.showMessageDialog(null, "로그인 실패: 사용자 ID 또는 비밀번호가 잘못되었습니다.", "경고", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "로그인 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
