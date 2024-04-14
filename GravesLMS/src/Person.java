// Jason Graves
// Software Development I
// Professor Walauskis
//3/10/2024

/**
 * Represents a person who can check out books from the library.
 * 
 * This class manages information about library patrons, including their name, ID, books checked out, and total late fees.
 * Each person has a list of books they currently have checked out, along with their accumulated late fees.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Person {
    private String[] booksCheckedOut;
    private double lateFeesTotal;
    private String name;
    private ArrayList<Book> booksIHave;
    private int id;

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


	public void setId(int nextInt) {
		this.id = nextInt;
		// TODO Auto-generated method stub
	}


	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
}
