package main;

import java.sql.Timestamp;

public class Post {
    private int id;
    private int userId;        // Relación con la tabla `users`
    private int categoryId;    // Relación con la tabla `categories`
    private String title;
    private String content;
    private Timestamp createdAt;
    private String categoryName;
    private String username;

    // Constructores
    public Post() {}

    public Post(int id, int userId, int categoryId, String title, String content, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
     // Nombre de la categoría

 // Getter y Setter
 public String getCategoryName() {
     return categoryName;
 }

 public void setCategoryName(String categoryName) {
     this.categoryName = categoryName;
 }

public void setUsername(String string) {
	// TODO Auto-generated method stub
	this.username = string;
}
public String getUsername() {
	return this.username;
}

}

