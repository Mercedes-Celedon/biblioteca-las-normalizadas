package com.normalizadas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.normalizadas.crud.AllBooks;

public class App {
    public static Scanner scanner= new Scanner(System.in);
    private Connection conn;//Inicializando atributo(property or class member) de la clase app
    //con un constructor se tiene organizada y separada la lógica de inicialización y ejecución. da claridad y organización del código.
    public App() {//implementando un constructor para la clase app  (inicialización de recurso que podría usar en esa clase) 
        this.conn = new dbConnection().getDbConnection();
    }

    public static void main( String[] args ) throws SQLException
    {
        App myApp = new App();//creando una nueva variable con la instancia de la clase app que se llama myApp
        

        /*
         * opening a loop (do while)
         * el do-while se cerrará solo cuando el usuario quiera salir y en cada vuelta
         * se pregunta si quiere salir
         * tiene que enseñar una lista de lo que debe hacer (swtich)
         */
        AllBooks allBooks = new AllBooks();
        int opc;

        do {
            System.out.println("\n¿Qué quieres hacer?");
            System.out.println("1. Ver catálogo entero.");
            System.out.println("2. Buscar un libro.");
            System.out.println("3. Añadir un libro.");
            System.out.println("4. Salir.\n");

            opc = scanner.nextInt();

            if (opc == 1) {
                allBooks.showAll();

            } else if (opc == 2) {
                myApp.searchBooks();

            } else if (opc == 3) {
                addBook();

            } else if (opc == 4) {
                //dbConnection.closeConnection(conn);
                break;
            }

        } while (opc != 4);
    }


    /* Function search books by filters - searchBooks */
    public void searchBooks() {
        /*
         * Add functions:
         * searchByTitle()
         * searchByAuthor()
         * searchByGenre()
         */
        // System.out.println("A buscar");
    }

    /* Function search a book by title - searchByTitle */
    public void searchByTitle() {

    }

    /* Function search a book by author - searchByAuthor */
    public void searchByAuthor() {

    }

    /* Function search a book by genre - searchByGenre */
    public void searchByGenre()throws SQLException{
        Statement stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM books ORDER BY id ASC");
            while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("title");
                    System.out.println(id + " " + nombre);
                }
            stmt.close();
            rs.close();
    }  

    /* Function add a book - addBook */
    public static void addBook() {
        /*
         * Add title
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
        System.out.println("Indica el autor o autores (en este caso separados por comas): ");
        String[] author = scanner.nextLine().split(",");
        System.out.println("Indica el género o géneros (en este caso separados por comas): ");
        String[] genre = scanner.nextLine().split(",");
        
        for(String authorName : author){
            // int id_author = authorClass.findOrCreateAuthor(author.trim());
            //bookClass.addBookAuthor()
        }
        
        // insertBook(title, description, isbn, stock, id_language);
    }

    // public static void insertBook(String title, String description, String isbn, int stock, int id_language) throws SQLException{

    //     PreparedStatement stmn = conn.prepareStatement("INSERT INTO books (title, description, isbn, stock, id_language) VALUES (?,?,?,?,?)");
        
    //     stmn.setString(1, title);
    //     stmn.setString(2, description);
    //     stmn.setString(3, isbn);
    //     stmn.setInt(4, stock);
    //     stmn.setInt(5, id_language);
    //     stmn.execute();
    // }
    /**
     * Function add an author - addAuthor
     * 
     * @param id (int)
     */
    public void addAuthor(int id) {

    }

    /**
     * Function add a genre - addGenre
     * 
     * @param id (int)
     */
    public void addGenre(int id) {

    }

    /**
     * Function edit a book - editBook
     * 
     * @param id (int)
     */
    public void editBook(int id) {

    }

    /**
     * Function delete a book - deleteBook
     * 
     * @param id
     */
    public void deleteBook(int id) {

    }
}

