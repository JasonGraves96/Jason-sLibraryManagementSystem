import java.util.ArrayList;
import java.util.Arrays;

public class Person {
    private String[] booksCheckedOut;
    private double lateFeesTotal;
    private String name;
    private ArrayList<Book> booksIHave;

    public Person(String name) {
        this.name = name;
        this.booksIHave = new ArrayList<>();
    }


    public ArrayList<Book> getBooksIHave() {return booksIHave;}

    public void setBooksIHave(ArrayList<Book> booksIHave) {this.booksIHave = booksIHave;}

    public void addBooksIHave(Book newBook){
        this.booksIHave.add(newBook);
    }
    public double getLateFeesTotal() {return lateFeesTotal;}
    public void setLateFeesTotal(double lateFeesTotal) {this.lateFeesTotal = lateFeesTotal;}

    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }
    public String[] getBooksCheckedOut() {
        return booksCheckedOut;
    }
    public void setBooksCheckedOut(String[] booksCheckedOut) {
        this.booksCheckedOut = booksCheckedOut;
    }

    @Override
    public String toString() {
        return "Person{" +
                "booksCheckedOut=" + Arrays.toString(booksCheckedOut) +
                ", lateFeesTotal=" + lateFeesTotal +
                ", name=" + name + '\'' +
                ", booksIHave=" + booksIHave +
                '}';
    }
}
