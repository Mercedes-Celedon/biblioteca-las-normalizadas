package com.normalizadas;



import java.sql.SQLException;
import java.util.Scanner;

public class App 
{
    public static Scanner scanner= new Scanner(System.in);
    public static void main( String[] args )
    {
        dbConnection.initConnection();
       /*
        * opening a loop (do while)
        * el do-while se cerrará solo cuando el usuario quiera salir y en cada vuelta se pregunta si quiere salir
        * tiene que enseñar una lista de lo que debe hacer (swtich)
        */
        try {
            addBook();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /* Function show all books - showAll */
    public void showAll(){

    }

    /* Function search books by filters  - searchBooks */
    public void searchBooks(){
        /* Add functions:
         * searchByTitle()
         * searchByAuthor()
         * searchByGenre()
         */
    }


    /* Function search a book by title - searchByTitle */
    public void searchByTitle(){
        
    }

    
    /* Function search a book by author - searchByAuthor */
    public void searchByAuthor(){
        
    }

    /* Function search a book by genre - searchByGenre */
    public void searchByGenre(){
        
    }

    /*Function add a book - addBook */
    public static void addBook() throws SQLException{
        /*Add title
         * addAuthor(bookId)
         * addGenre(bookId)
         */
        System.out.println("Indica el título:");
        String title = scanner.nextLine();
        System.out.println("Añade una descripción (de menos de 200 caracteres):");
        String description = scanner.nextLine();
        System.out.println("Indica el ISBN:");
        String isbn = scanner.nextLine();
        System.out.println("Indica el stock:");
        int stock = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Indica el idioma (escribe solo el número): \n\t1. Español \n\t2. Inglés \n\t3. Francés \n\t4. Catalán.");
        int id_language = scanner.nextInt();
        scanner.nextLine();
        // System.out.println("Indica el autor: ");
        // String author = scanner.nextLine();
        
        // if(author){}
        
        // String genre = scanner.nextLine();
        
        // if(genre){}
        dbConnection.insertBook(title, description, isbn, stock, id_language);
    }

    /**
     * Function add an author - addAuthor
     * @param id (int)
     */
    public void addAuthor(int id){
        
    }

    /**
     * Function add a genre - addGenre
     * @param id (int)
     */
    public void addGenre(int id){
        
    }

    /**
     * Function edit a book - editBook
     * @param id (int)
     */
    public void editBook(int id){

    }

    /**
     * Function delete a book - deleteBook
     * @param id
     */
    public void deleteBook(int id){

    }
}
