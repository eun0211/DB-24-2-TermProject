package model;

public class Post {
    private int postId;
    private String writerId;
    private String content;
    private int numOfLikes;

    public Post(int postId, String writerId, String content, int numOfLikes) {
        this.postId = postId;
        this.writerId = writerId;
        this.content = content;
        this.numOfLikes = numOfLikes;
    }

    public int getPostId() {
        return postId;
    }

    public String getWriterId() {
        return writerId;
    }

    public String getContent() {
        return content;
    }

    public int getNumOfLikes() {
        return numOfLikes;
    }
}
