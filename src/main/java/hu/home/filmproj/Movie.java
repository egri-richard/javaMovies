package hu.home.filmproj;

public class Movie {
    private int id;
    private String title;
    private String category;
    private int length;
    private int rating;

    public Movie(int id, String title, String category, int length, int rating) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.length = length;
        this.rating = rating;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getLength() {
        return length;
    }

    public int getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
