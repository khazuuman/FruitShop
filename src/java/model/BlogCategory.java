package model;

public class BlogCategory {

    private int blogCateID;
    private String blogCateName;

    public BlogCategory() {
    }

    public BlogCategory(int blogCateID, String blogCateName) {
        this.blogCateID = blogCateID;
        this.blogCateName = blogCateName;
    }

    public int getBlogCateID() {
        return blogCateID;
    }

    public void setBlogCateID(int blogCateID) {
        this.blogCateID = blogCateID;
    }

    public String getBlogCateName() {
        return blogCateName;
    }

    public void setBlogCateName(String blogCateName) {
        this.blogCateName = blogCateName;
    }

}
