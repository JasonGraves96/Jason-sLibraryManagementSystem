// Jason Graves
// Software Development I
// Professor Walauskis
// 1/19/2024

/**
 * Represents a book in the library.
 * 
 * This class provides a template for creating book objects to be managed by the library system. Each book has attributes
 * such as title, author, and ID. Additionally, it keeps track of whether the book is checked in or out, any late fees
 * incurred, and who currently has possession of the book.
 */

public class Book {//this is the custom object Book class
    //its purpose is to provide the template for all these books this program is keeping track of
    //it consists of 3 attributes, a title an author and an id
    //each attribute is private with seperate getters and setters
    //there is a toString method which is called when both displaying to the console as well as when the books are being written to a file
    private String title;
    private String author;
    private int id;
    private boolean checkedIn;
    private double lateFees;
    private String whoHasMe;

    public String getWhoHasMe() {
        return whoHasMe;
    }

    public void setWhoHasMe(String whoHasMe) {
        this.whoHasMe = whoHasMe;
    }




    public Book(String x, String y, int i) {
        super();
        setTitle(x);
        setAuthor(y);
        setId(i);
        setCheckedIn(true);
        setLateFees(0.00);
        setWhoHasMe("library");
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

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }


    public double getLateFees() {
        return lateFees;
    }

    public void setLateFees(double lateFees) {
        this.lateFees = lateFees;
    }
}
