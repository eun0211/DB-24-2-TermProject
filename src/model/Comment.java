package model;

public class Comment {
    private int id;
    private int postId;
    private String userId;
    private String content;

    public Comment(int id, int postId, String userId, String content) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public int getPostId() {
        return postId;
    }

    public String getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }
}
