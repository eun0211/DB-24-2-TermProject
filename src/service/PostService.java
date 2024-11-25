package service;

import model.Post;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostService {

    public static List<Post> getMyPosts(String userId) {
        List<Post> posts = new ArrayList<>();
        String query = "SELECT * FROM posts WHERE writer_id = ? ORDER BY post_id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                posts.add(new Post(
                        rs.getInt("post_id"),
                        rs.getString("writer_id"),
                        rs.getString("content"),
                        rs.getInt("num_of_likes")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static List<Post> getPostsForYou(String userId) {
        List<Post> posts = new ArrayList<>();
        String query = "SELECT * FROM posts WHERE writer_id != ? ORDER BY post_id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                posts.add(new Post(
                        rs.getInt("post_id"),
                        rs.getString("writer_id"),
                        rs.getString("content"),
                        rs.getInt("num_of_likes")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static List<Post> getFollowingPosts(String userId) {
        List<Post> posts = new ArrayList<>();
        String query = "SELECT * FROM posts WHERE writer_id IN (SELECT follower_id FROM followers WHERE user_id = ?) ORDER BY post_id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                posts.add(new Post(
                        rs.getInt("post_id"),
                        rs.getString("writer_id"),
                        rs.getString("content"),
                        rs.getInt("num_of_likes")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static void addPost(String userId, String content) {
        String query = "INSERT INTO posts (writer_id, content, num_of_likes) VALUES (?, ?, 0)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userId);
            pstmt.setString(2, content);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
