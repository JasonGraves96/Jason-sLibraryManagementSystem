// Jason Graves
// Software Development I
// Professor Walauskis
// 1/19/2024

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Atlas {//this class does most of the heavy lifting. Carries the program on its back.
    //the way this program is structured is the main class handles the menu,
    //and when the user selects an option from said menu the program calls a method from this class

    public static ArrayList<Book> textReader(ArrayList<Book> array) throws FileNotFoundException {//this method reads the text file
        String[] tempBookData;//stores each element of our new book into an Array of Strings
        File incomingBooks = new File("newbooks.txt");//it has to be called newbooks.txt, this is not flexible
        Scanner sc = new Scanner(incomingBooks);//new scanner for the text file

        while (sc.hasNextLine()){//while the scanned file still has lines
            Book tempBook = new Book("temp","temp",0);//create a new book with placeholder values
            String line = sc.nextLine();//turns each line into a string
            tempBookData = line.split(",");//seperates the line by comma and places them into the array of Strings
            tempBook.setId(Integer.parseInt(tempBookData[0]));//converts the first index to an Integer and sets it as the id of our new book
            tempBook.setTitle(tempBookData[1]);//reads the second index as the title and sets it as the title of our new book
            tempBook.setAuthor(tempBookData[2]);//reads the third index as the author and sets it as the author of our new book
            array.add(tempBook);//adds our new book to the array
        }
        sc.close();//closes the file scanner
        return array;//ends the method returning the ArrayList with our new books added
    }



    public static Book bookMaker(Scanner scnr){//method to create new books
        String title;
        String author;
        int id;//initializing our variables
        System.out.println("Please enter the title of the book");
        title = scnr.nextLine();//reads the first input as the title
        System.out.println("Please enter the author of the book");
        author = scnr.nextLine();//reads the second input as the author
        id = 0;//temporary ID before it is assigned one
        Book tempBook = new Book(title,author,id);//creates the new book object and sets the user's input as the values
        return tempBook;//returns the created book
    }

    public static ArrayList<Book> idSetter(ArrayList<Book> array){//this reassigns id numbers based on the ArrayList's index
        for(int i = 0; i < array.size(); i++){
            array.get(i).setId(i+1);//for each element in the ArrayList set the id to the index plus 1
        }
        return array;
    }


    public static int removeBook(Scanner scnr, int x){//this method just gets the selection from the user, the actual removing of the book is done in main
        Boolean readyToLeave = false;//this filters for bad inputs
        do {
            System.out.println("What is the ID number of the book you want to remove?");
            if(scnr.hasNextInt()) {
                int selection = scnr.nextInt();
                if (selection <= x && selection > 0)
                    return selection;//returns the number the user picked
                else//this displays if they put in a non valid number
                    System.out.println("Please use a real ID value");
            } else{//this displays if they didn't enter a number
                System.out.println("Please use a real ID value");
                scnr.nextLine();
            }
        }while(readyToLeave == false);//keeps looping until the user gives a valid id number
        return 0;
    }

    public static void saveMyLibrary(ArrayList<Book> array, Scanner scnr) {//this saves the library
        String filePath = "placeholder";//the filePath variable is ironically the file name and not the file path. It came to this after multiple iterations of trying and failing to write this method
        System.out.println("What do you want to name your file?");//asks user what the name of their file is
        filePath = scnr.nextLine();//gets the preferred file name from the user
        filePath = filePath + ".liby";//adds the extension to the created file
        try {

            File theFile = new File("out\\production\\Graves SDLC Assignment",filePath);//idk if this is needed, but I think it creates the file if it's not there already
            FileWriter writer = new FileWriter(theFile);//this makes the FileWriter object
            for (int i = 0; i < array.size(); i++) {//for every element in the array
                if(array.get(i) != null){//if the value isn't null. Don't think this is necessary, but is left over from when I was troubleshooting this, and can't hurt to keep
                    writer.write(array.get(i).toString()+"\n");//writes each element of the array to the file
                }
            }
            writer.close();//closes the writer, the file doesn't actually write unless you do this :/
        } catch (IOException e) {//intellij mandated that this is exception handled, autogenerated
            e.printStackTrace();
        }
    }//end saveMyLibrary

    public static void introPrinter(){//this generates the ascii art at the start of the program
        System.out.println("  ");
        try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}//adds a delay for effect
        System.out.println("             .--.           .---.        .-.");
        try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}//adds a delay for effect
        System.out.println("         .---|--|   .-.     | J |  .---. |~|    .--.");
        try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}//adds a delay for effect
        System.out.println("      .--|===|  |---|_|--.__| A |--|:::| |~|-==-|==|---.");
        try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}//adds a delay for effect
        System.out.println("      |%%|   |  |===| |~~|%%| S |--|   |_|~|    |  |___|-.");
        try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}//adds a delay for effect
        System.out.println("      |  |LMS|  |===| |==|  | O |  |:::|=| |    |  |---|=|");
        try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}//adds a delay for effect
        System.out.println("      |  |   |  |   |_|__|  | N |__|   | | |    |  |___| |");
        try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}//adds a delay for effect
        System.out.println("      |~~|===|--|===|~|~~|%%|~~~|--|:::|=|~|----|==|---|=|");
        try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}//adds a delay for effect
        System.out.println("      ^--^---'--^---^-^--^--^---'--^---^-^-^-==-^--^---^-'");
        try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}//adds a delay for effect
        System.out.println();
        try {Thread.sleep(250);} catch (InterruptedException e) {e.printStackTrace();}//adds a delay for effect
        System.out.println("      Welcome to Jason Graves' Library Management System!!!");


    }//end introPrinter

    public static ArrayList<Book> loadMyLibrary(ArrayList<Book> array, Scanner scnr){
        //this method loads a previously saved file to our collection of books
        //it's the most complicated one, the general idea is that it finds and displays a list of saved files
        //then the user is able to select which one they want to load
        try {
            String[] tempBookData;//a string array is used to store our temporary values before we build the book
            int selection = 0;//0 is a placeholder value, this varriable is used to define the user's selection
            String rootPath = new File(main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();//this gets where the project is saved on the user's computer
            File rootDirectory = new File(rootPath);//this makes a file object based on that directory
            File[] files = rootDirectory.listFiles((dir, name) -> name.endsWith(".liby"));//this makes an array of those files consisting only of the ones that end in .liby
            if (files != null) {//if saved libraries are found
                System.out.println("Please select which file you would like to load");
                for (int i=0; i <files.length; i++) {//this displays the list of .liby files
                    System.out.println(i+1 + ". "+files[i].getName());
                }
                selection = scnr.nextInt()-1;//minus one because the index is the selection minus 1
                File incomingBookFile = new File(files[selection].getPath());//this gets the url of the selected file using the index the user just specified
                try {
                    Scanner sc = new Scanner(incomingBookFile);//two scanners, one to read user input which is being imported from main, and the other to read the file. There's probably a more elegant way to do this with one scanner, but I don't want to deal with that confusion.
                    while (sc.hasNextLine()){//while our new scanner reading the file contains another line
                        Book tempBook = new Book("temp","temp",0);//creating our temporary book object
                        String line = sc.nextLine();//making the first line a string
                        tempBookData = line.split("\\.");//splitting the string by the first period
                        tempBook.setId(Integer.parseInt(tempBookData[0]));//setting the first index as the book's ID
                        tempBookData = line.split(" by ");//splitting the string again, this time by the word "by"
                        //the above code is mostly the same as reading a book from the .txt, with a slight difference being that the books aren't stored in the .liby in the exact same format, so seperating by comma doesn't work



                        //I realize the following is an incredibly janky way to solve this problem, but it gets the job done.
                        // The issue I was running into is that when these books were being loaded the "1. "
                        // were being added to the title of the book, so I'm using the .substring method
                        // to truncate the titles so only the titles themselves are added.
                        // The problem is that after the id number contains more than 1 digit
                        // I need to cut off more of the string.
                        // this solution will work until there are a million books in the database,
                        // at which point the titles will start to get messed up
                        if(tempBook.getId()<10)
                            tempBook.setTitle(tempBookData[0].substring(3));//this sets the title as the new first index in the array after the last split
                        else if(tempBook.getId()<100)//the following are the same only with the number of characters being cut off increased by 1
                            tempBook.setTitle(tempBookData[0].substring(4));
                        else if(tempBook.getId()<1000)
                            tempBook.setTitle(tempBookData[0].substring(5));
                        else if(tempBook.getId()<10000)
                            tempBook.setTitle(tempBookData[0].substring(6));
                        else if(tempBook.getId()<100000)
                            tempBook.setTitle(tempBookData[0].substring(7));
                        else if(tempBook.getId()<1000000)
                            tempBook.setTitle(tempBookData[0].substring(8));



                        tempBook.setAuthor(tempBookData[1]);//reads the next index as the author and sets it as the author of our new book

                        array.add(tempBook);//adds our temporary book to the array
                    }
                } catch (FileNotFoundException e) {//auto generated exception handle
                    e.printStackTrace();
                }
            } else {//if no saved libraries are found
                System.out.println("No files found in the project directory.");
            }
        } catch (URISyntaxException e) {//another auto generated exception handle
            e.printStackTrace();
        }
    return array;//returns the array back to main
    }//end loadMyLibrary method
}//end main class.