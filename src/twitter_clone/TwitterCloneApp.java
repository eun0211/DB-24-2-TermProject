package twitter_clone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.LoginService;
import service.PostService;
import model.Post;

public class TwitterCloneApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private String loggedInUser = null;

    public TwitterCloneApp() {
        setTitle("Twitter Clone");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Adding different views to mainPanel
        mainPanel.add(createHomePanel(), "Home");
        mainPanel.add(createForYouPanel(), "ForYou");
        mainPanel.add(createFollowingPanel(), "Following");
        mainPanel.add(createLoginPanel(), "Login");
        mainPanel.add(createMyPostsPanel(), "MyPosts");
        mainPanel.add(createPostCreationPanel(), "PostCreation");

        add(createTopNavigationBar(), BorderLayout.NORTH);
        add(mainPanel);
        add(createBottomNavigationBar(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createHomePanel() {
        JPanel homePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to Twitter Clone!", SwingConstants.CENTER);
        homePanel.add(welcomeLabel, BorderLayout.CENTER);
        return homePanel;
    }

    private JPanel createForYouPanel() {
        JPanel forYouPanel = new JPanel();
        forYouPanel.setLayout(new BoxLayout(forYouPanel, BoxLayout.Y_AXIS));

        // Public posts available for all users (even without login)
        for (Post post : PostService.getPostsForYou("anonymous")) {
            forYouPanel.add(new JLabel(post.getContent()));
        }

        return forYouPanel;
    }

    private JPanel createFollowingPanel() {
        JPanel followingPanel = new JPanel();
        followingPanel.setLayout(new BoxLayout(followingPanel, BoxLayout.Y_AXIS));

        // Following posts are only available for logged-in users
        if (loggedInUser != null) {
            for (Post post : PostService.getFollowingPosts(loggedInUser)) {
                followingPanel.add(new JLabel(post.getContent()));
            }
        } else {
            followingPanel.add(new JLabel("Please log in to see following posts."));
        }

        return followingPanel;
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel userIdLabel = new JLabel("User ID:");
        JTextField userIdField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String userId = userIdField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            if (!userId.isEmpty() && !password.isEmpty()) {
                boolean loginSuccess = LoginService.login(userId, password);
                if (loginSuccess) {
                    loggedInUser = userId;
                    cardLayout.show(mainPanel, "Home");
                } else {
                    JOptionPane.showMessageDialog(this, "Login failed. Please check your User ID and Password.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a User ID and Password.");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(userIdLabel, gbc);
        gbc.gridx = 1;
        loginPanel.add(userIdField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(loginButton, gbc);

        return loginPanel;
    }

    private JPanel createMyPostsPanel() {
        JPanel myPostsPanel = new JPanel();
        myPostsPanel.setLayout(new BoxLayout(myPostsPanel, BoxLayout.Y_AXIS));

        if (loggedInUser != null) {
            for (Post post : PostService.getMyPosts(loggedInUser)) {
                myPostsPanel.add(new JLabel(post.getContent())); // Post 내용 표시
            }
        } else {
            myPostsPanel.add(new JLabel("Please log in to view your posts."));
        }

        return myPostsPanel;
    }

    private JPanel createPostCreationPanel() {
        JPanel postCreationPanel = new JPanel(new BorderLayout());
        JTextArea postContentArea = new JTextArea(10, 30);
        JButton submitButton = new JButton("Post");

        submitButton.addActionListener(e -> {
            String content = postContentArea.getText().trim();
            if (loggedInUser != null && !content.isEmpty()) {
                PostService.addPost(loggedInUser, content);
                JOptionPane.showMessageDialog(this, "Your post has been created.");
                postContentArea.setText("");
                cardLayout.show(mainPanel, "MyPosts");
            } else if (loggedInUser == null) {
                JOptionPane.showMessageDialog(this, "Please log in to create a post.");
            } else {
                JOptionPane.showMessageDialog(this, "Post content cannot be empty.");
            }
        });

        postCreationPanel.add(new JScrollPane(postContentArea), BorderLayout.CENTER);
        postCreationPanel.add(submitButton, BorderLayout.SOUTH);

        return postCreationPanel;
    }

    private JPanel createTopNavigationBar() {
        JPanel topNavPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton forYouButton = new JButton("For You");
        JButton followingButton = new JButton("Following");

        forYouButton.addActionListener(new NavigationButtonListener("ForYou"));
        followingButton.addActionListener(new NavigationButtonListener("Following"));

        topNavPanel.add(forYouButton);
        topNavPanel.add(followingButton);

        return topNavPanel;
    }

    private JPanel createBottomNavigationBar() {
        JPanel bottomNavPanel = new JPanel(new GridLayout(1, 4));

        JButton homeButton = new JButton("Home");
        JButton searchButton = new JButton("Search");
        JButton myPostsButton = new JButton("My Posts");
        JButton createPostButton = new JButton("New Post");

        homeButton.addActionListener(new NavigationButtonListener("Home"));
        searchButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Search functionality not implemented yet."));
        myPostsButton.addActionListener(new NavigationButtonListener("MyPosts"));
        createPostButton.addActionListener(new NavigationButtonListener("PostCreation"));

        bottomNavPanel.add(homeButton);
        bottomNavPanel.add(searchButton);
        bottomNavPanel.add(myPostsButton);
        bottomNavPanel.add(createPostButton);

        return bottomNavPanel;
    }

    private class NavigationButtonListener implements ActionListener {
        private String panelName;

        public NavigationButtonListener(String panelName) {
            this.panelName = panelName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (panelName.equals("Following") || panelName.equals("PostCreation")) {
                if (loggedInUser == null) {
                    JOptionPane.showMessageDialog(TwitterCloneApp.this, "Please log in first.");
                    cardLayout.show(mainPanel, "Login");
                    return;
                }
            }
            cardLayout.show(mainPanel, panelName);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TwitterCloneApp::new);
    }
}
