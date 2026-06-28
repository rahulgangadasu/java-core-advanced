package java_code;

public class Movie {
    private String title;
    private int likes;
    private Genre genre;
    
    public Movie(String title, int likes, Genre genre){
        this.title = title;
        this.likes = likes;
        this.genre = genre;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }
    public Genre getGenre() {
        return genre;
    }
}
