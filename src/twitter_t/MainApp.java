
/*이다니엘창민(20241115)
//GUI 전반을 전부 정의하는 함수
//swing 으로 구현하였음
//크게 왼쪽 중간 오른쪽 패널 구현
// 왼쪽에는 사이드바 구현 >클릭불가
// 중간에는 포스트들 구현> 스크롤까지 구현> 좋아요 버튼 동작 정의 x
// 오른쪽에는 트랜드 프레임 구현 > 알고리즘도 생각 X 그냥 만들어만 둠.
*/

package twitter_t;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp extends JFrame {
    private String currentUserId;

    public MainApp(String userId) {
        this.currentUserId = userId; // 로그인한 사용자 ID 저장
        setTitle("Twitter Clone");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // 왼쪽 사이드바 패널 생성
        JPanel sidebarPanel = createSidebarPanel();
        add(sidebarPanel, BorderLayout.WEST);

        // 중앙 메인 패널 생성
        JPanel mainPanel = createMainPanel();
        add(mainPanel, BorderLayout.CENTER);

        // 오른쪽 트렌드 패널 생성
        JPanel trendsPanel = createTrendsPanel();
        add(trendsPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private JPanel createSidebarPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        // 메뉴 아이콘과 트윗 버튼 추가
        String[] menuItems = {"Home", "Explore", "Notifications", "Messages", "Bookmarks", "Lists", "Profile", "More"};
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setPreferredSize(new Dimension(150, 50));
            panel.add(button);
            panel.add(Box.createVerticalStrut(10));
        }

        JButton tweetButton = new JButton("Tweet");
        tweetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        tweetButton.setPreferredSize(new Dimension(150, 50));
        panel.add(tweetButton);

        // 사용자 ID 및 로그아웃 버튼 추가
        panel.add(Box.createVerticalGlue()); // 아래쪽으로 밀기
        JLabel userIdLabel = new JLabel("사용자 ID: " + currentUserId);
        userIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(userIdLabel);

        JButton logoutButton = new JButton("로그아웃");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 로그아웃 처리: 로그인 화면으로 돌아가기
                dispose(); // 현재 창 닫기
                SwingUtilities.invokeLater(LoginFrame::new); // 로그인 화면 호출
            }
        });
        panel.add(logoutButton);

        return panel;
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 트윗 작성 필드 및 버튼
        JPanel tweetPanel = new JPanel();
        tweetPanel.setLayout(new BorderLayout());
        JTextField tweetField = new JTextField("What's happening?");
        JButton postButton = new JButton("Tweet");

        tweetPanel.add(tweetField, BorderLayout.CENTER);
        tweetPanel.add(postButton, BorderLayout.EAST);
        panel.add(tweetPanel, BorderLayout.NORTH);

        // 타임라인 패널
        JPanel timelinePanel = new JPanel();
        timelinePanel.setLayout(new BoxLayout(timelinePanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(timelinePanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createTrendsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(300, 700));
        panel.setBackground(Color.WHITE);

        // 트렌드 검색 필드
        JTextField searchField = new JTextField("Search Twitter");
        panel.add(searchField, BorderLayout.NORTH);

        JPanel trendsListPanel = new JPanel();
        trendsListPanel.setLayout(new BoxLayout(trendsListPanel, BoxLayout.Y_AXIS));
        JScrollPane trendsScrollPane = new JScrollPane(trendsListPanel);
        panel.add(trendsScrollPane, BorderLayout.CENTER);

        return panel;
    }
}
