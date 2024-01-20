// Jason Graves
// Software Development I
// Professor Walauskis
// 1/19/2024

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;


public class main {//main mostly handles the menu, but there's a couple other things
    //it's where the ArrayList of books is saved while the user is running the program
    public static void main(String[] args) {

        int selection = 0;//this is for the user's menu selection
        Scanner scnr = new Scanner(System.in);//this is for reading the user's input
        ArrayList<Book> tempLibrary = new ArrayList<Book>();//this ArrayList holds the Book objects the user is adding
        Atlas.introPrinter();//prints the intro ascii art

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//adds a delay after the ascii art and before the main menu appears

        do {//start of the menu loop
            System.out.println("\nPlease pick an option below to get started!"
                    + "\n1.Add books from text file.\n2.Enter book manually.\n3.Remove book.\n4.Save library."
                    + "\n5.Load library.\n6.List all books.\nAny other input will quit the program\nDON'T FORGET TO SAVE BEFORE LEAVING!!!");
            try{
            selection = scnr.nextInt();

            if(selection < 1 || selection > 6)//ends the program if a non-valid number is entered
                System.exit(0);

            scnr.nextLine();//flushes the scanner so there's no leftover data

            switch (selection) {//switch statement branches program to what number the user selected
                case 1://read book from .txt file
                    try {//the try... catch is in case of the file not existing
                        tempLibrary = Atlas.textReader(tempLibrary);//runs the textReader method to add the books from the text file to the unsaved library
                    } catch (FileNotFoundException fnfe) {
                        fnfe.printStackTrace();//this will output the error but keep the program running if this errors out
                    }
                    tempLibrary = Atlas.idSetter(tempLibrary);
                    System.out.println("Books added successfully");
                    try {Thread.sleep(1500);} catch (InterruptedException e) {e.printStackTrace();}//adds a delay
                    break;

                case 2://manually add book from within the program
                    tempLibrary.add(Atlas.bookMaker(scnr));   //adds new book
                    tempLibrary = Atlas.idSetter(tempLibrary);//resets id numbers
                    break;

                case 3://remove book from ID
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

                    break;

                case 4://Save Book Library
                    Atlas.saveMyLibrary(tempLibrary, scnr);
                    break;

                case 5://Load Book Library
                    tempLibrary = Atlas.loadMyLibrary(tempLibrary, scnr);
                    tempLibrary = Atlas.idSetter(tempLibrary);//reset's id numbers
                    break;

                case 6://List all Books in the Library
                    if(tempLibrary.size() > 0)
                    for (int i = 0; i < tempLibrary.size(); i++){
                        System.out.println(tempLibrary.get(i).toString());//prints out the list of books
                        try {Thread.sleep(150);} catch (InterruptedException e) {e.printStackTrace();}//adds a delay for effect
                    }

                    else
                        System.out.println("You have no books right now!");
                    try {Thread.sleep(1500);} catch (InterruptedException e) {e.printStackTrace();}//adds a delay for effect
                    break;

                default:
                    break;
            }
            }catch(InputMismatchException ime){
                System.exit(0);//ends the program if a non number is entered as a menu selection
            }
        } while (selection < 7);//keeps looping while the selection is still valid
    }
}