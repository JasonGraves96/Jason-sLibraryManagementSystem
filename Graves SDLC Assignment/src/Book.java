// Jason Graves
// Software Development I
// Professor Walauskis
// 1/19/2024

public class Book {//this is the custom object Book class
    //its purpose is to provide the template for all these books this program is keeping track of
    //it consists of 3 attributes, a title an author and an id
    //each attribute is private with seperate getters and setters
    //there is a toString method which is called when both displaying to the console as well as when the books are being written to a file
    private String title;
    private String author;
    private int id;

    public Book(String x,String y, int i) {
        super();
        setTitle(x);
        setAuthor(y);
        setId(i);

    }

    @Override
    public String toString() {
        return id + ". " + title + " by " + author + " ";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}