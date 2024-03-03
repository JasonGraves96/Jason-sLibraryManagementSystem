// Jason Graves
// Software Development I
// Professor Walauskis
// 1/19/2024

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;



public class main {//main mostly handles the menu, but there's a couple other things
    //it's where the ArrayList of books is saved while the user is running the program
    //this is for the user's menu selection
    static Scanner scnr = new Scanner(System.in);//this is for reading the user's input
    static ArrayList<Book> tempLibrary = new ArrayList<Book>();//this ArrayList holds the Book objects the user is adding
    static ArrayList<Person> tempPeople = new ArrayList<>();
    static StringBuilder toTextBox = new StringBuilder("");
    //static mainMenu theMenu = new mainMenu();
    public static void main(String[] args) {
        int selection = 0;
        //setContentPane(mainMenu);
        //mainMenu theMenu = new mainMenu();
        //int selection = 0;//this is for the user's menu selection

        ArrayList<Book> tempLibrary = new ArrayList<Book>();//this ArrayList holds the Book objects the user is adding
        Atlas.introPrinter();//prints the intro ascii art


        //adds a delay after the ascii art and before the main menu appears
        do {//start of the menu loop
            System.out.println("\nPlease pick an option below to get started!"
                  + "\n1.Add books from text file.\n2.Enter book manually.\n3.Remove book.\n4.Save library."
                   + "\n5.Load library.\n6.List all books.\n7.Check out book\n8.Check in book\n9.View Late Fees(NON functional)\n10.Add new customer\nAny other input will quit the program\nDON'T FORGET TO SAVE BEFORE LEAVING!!!");

            selection = scnr.nextInt();

            try{
                //selection = theMenu.getSelection();
                //System.out.println(selection);
            if(selection < 1 || selection > 10)//ends the program if a non-valid number is entered
                System.exit(0);
            scnr.nextLine();//flushes the scanner so there's no leftover data

            switch (selection) {//switch statement branches program to what number the user selected
                case 1://read book from .txt file
                    Selection1();
                    break;

                case 2://manually add book from within the program
                    Selection2();
                    break;

                case 3://remove book from ID
                    Selection3();

                    break;

                case 4://Save Book Library
                    Selection4();

                    break;

                case 5://Load Book Library
                    Selection5();

                    break;

                case 6://List all Books in the Library
                    Selection6();
                    break;
                case 7:
                    Selection7();
                    break;
                case 8:
                    Selection8();
                    break;
                case 9:
                    Selection9();
                    break;

                case 10:
                    Selection10();
                    break;

                default:
                    break;
            }
            }catch(InputMismatchException ime){
               // System.exit(0);//ends the program if a non number is entered as a menu selection
            }
        } while (selection > 0 && selection < 11);//keeps looping while the selection is still valid
    }//ALL THE ABOVE CODE IS REDUNDANT AFTER ADDING THE UI, will be deleted in the final product, but I like to keep it around in case I need to re-use for some reason


//this is my solution to retrofit what I already made with the UI
//instead of a switch statement, the UI action listeners call these methods, which points to the same places the switch statement used to
    public static void Selection1(){
        try {//the try... catch is in case of the file not existing
            tempLibrary = Atlas.textReader(tempLibrary);//runs the textReader method to add the books from the text file to the unsaved library
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();//this will output the error but keep the program running if this errors out
        }
        tempLibrary = Atlas.idSetter(tempLibrary);
        System.out.println("Books added successfully");

    }

    public static void Selection2(){
        tempLibrary.add(Atlas.bookMaker(scnr));   //adds new book
        tempLibrary = Atlas.idSetter(tempLibrary);

    }

    public static void Selection3(){
        if(tempLibrary.size() > 0){
            for (int i = 0; i < tempLibrary.size(); i++)
                System.out.println(tempLibrary.get(i).toString());//this prints out the books before letting the user select one

            System.out.println("\nFrom the list above please pick which book you would like to remove.");
            tempLibrary.remove(Atlas.removeBook(scnr, tempLibrary.size()) - 1);//removes the book the user specified. size -1 because the index is one smaller than the id
            tempLibrary = Atlas.idSetter(tempLibrary);//reset's id numbers

        }else{
            System.out.println("But you have no books!!");
            try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}//adds a delay
        }

    }

    public static void Selection4(){
        Atlas.saveMyLibrary(tempLibrary, scnr,tempPeople);

    }

    public static void Selection5(){
        tempLibrary = Atlas.loadMyLibrary(tempLibrary, scnr, tempPeople);

        int selection = 0;
        tempPeople = Atlas.loadMyPeople(tempLibrary, scnr, tempPeople, selection);
        tempLibrary = Atlas.idSetter(tempLibrary);//reset's id numbers
    }

    public static void Selection6(){
        if(tempLibrary.size() > 0) {//the JtextArea stuff isn't ready yet

            for (int i = 0; i < tempLibrary.size(); i++) {

                System.out.println(tempLibrary.get(i).toString());//prints out the list of books
                //toTextBox.append(tempLibrary.get(i).toString() + "\n");
                //adds a delay for effect
            }
            //JTextArea tempJT = new JTextArea(toTextBox.toString());
            //theMenu.setTopTextBox(tempJT);
        }
        else{
            //JTextArea tempJT = new JTextArea("You have no books right now!");
            System.out.println("You have no books right now!");
        }
    }

    public static void Selection7(){//checking out books
        if(Atlas.areBooksCheckedOut(tempLibrary).size() > 0){
            int selection = Atlas.checkOutBooks(tempLibrary, scnr);
            tempLibrary.get(selection-1).setCheckedIn(false);
                if(tempPeople.size() < 1){
                    System.out.println("There are 0 customers in our database, so you'll have to add one");
                    tempPeople.add(Atlas.personMaker());
                    System.out.println("New Customer Added: "+tempPeople.get(0).getName());
                    tempPeople = Atlas.giveBookToPerson(tempPeople, scnr, tempLibrary.get(selection-1));
                }else{
                    tempPeople = Atlas.giveBookToPerson(tempPeople, scnr, tempLibrary.get(selection-1));

                }
        }else{
            System.out.println("no books are checked in");
        }
        tempLibrary = Atlas.idSetter(tempLibrary);//reset's id numbers
    }

    public static void Selection8(){//checking in books
        if(Atlas.areBooksCheckedIn(tempLibrary).size() > 0){
            int selection = Atlas.checkInBooks(tempLibrary, scnr);
            tempLibrary.get(selection-1).setCheckedIn(true);
        }else{
            System.out.println("no books are checked out");
        }
        //ADD METHODS THAT ACTUALLY CHECK THE BOOK BACK IN
        tempLibrary = Atlas.idSetter(tempLibrary);//reset's id numbers
    }

    public static void Selection9(){//idk


    }
    public static void Selection10(){//new person
        tempPeople.add(Atlas.personMaker());
    }
}