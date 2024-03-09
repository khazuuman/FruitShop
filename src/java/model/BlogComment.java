package model;

import java.time.LocalDateTime;

public class BlogComment {

    private int cmtID;
    private Account account;
    private Blog blog;
    private int reCmt;
    private String content;
        private LocalDateTime time;

    public BlogComment() {
    }

    public BlogComment(int cmtID, Account account, Blog blog, int reCmt, String content, LocalDateTime time) {
        this.cmtID = cmtID;
        this.account = account;
        this.blog = blog;
        this.reCmt = reCmt;
        this.content = content;
        this.time = time;
    }

    public int getCmtID() {
        return cmtID;
    }

    public void setCmtID(int cmtID) {
        this.cmtID = cmtID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public int getReCmt() {
        return reCmt;
    }

    public void setReCmt(int reCmt) {
        this.reCmt = reCmt;
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

}
