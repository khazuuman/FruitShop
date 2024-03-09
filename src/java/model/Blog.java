package model;

import java.time.LocalDateTime;

public class Blog {

    private int blogID;
    private Account account;
    private BlogCategory category;
    private boolean isActive;
    private String title;
    private String img;
    private String content;
    private LocalDateTime time;
    private BlogCmtCount bcount;

    public Blog() {
    }

    public Blog(int blogID, Account account, BlogCategory category, boolean isActive, String title, String img, String content, LocalDateTime time, BlogCmtCount bcount) {
        this.blogID = blogID;
        this.account = account;
        this.category = category;
        this.isActive = isActive;
        this.title = title;
        this.img = img;
        this.content = content;
        this.time = time;
        this.bcount = bcount;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BlogCategory getCategory() {
        return category;
    }

    public void setCategory(BlogCategory category) {
        this.category = category;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public BlogCmtCount getBcount() {
        return bcount;
    }

    public void setBcount(BlogCmtCount bcount) {
        this.bcount = bcount;
    }

    

    

}
