package twitter_t;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.LoginService;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Login - Twitter Clone");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = createLoginPanel();
        add(panel);

        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel userIdLabel = new JLabel("User ID:");
        JTextField userIdField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (LoginService.login(userId, password)) {
                    dispose(); // 로그인 창 닫기
                    new MainApp(userId); // 로그인된 사용자 ID 전달
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "로그인 실패: 사용자 ID 또는 비밀번호가 잘못되었습니다.", "경고", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(userIdLabel, gbc);
        gbc.gridx = 1;
        panel.add(userIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(loginButton, gbc);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
