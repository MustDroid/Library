package com.emoke.management;

import com.emoke.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class AdminManagement {
    public ICustomerRepository customerRepository;
    public IBookRepository bookRepository;

    public AdminManagement() {
        customerRepository = new DatabaseCustomerRepository(MySQLDbConnection.getInstance().getConnection());
        bookRepository = new DatabaseBookRepository(MySQLDbConnection.getInstance().getConnection());
    }

    public void welcomeTextForAdmin() {
        System.out.println("Hallo Lukas, Im happy to see you again");
        System.out.println("what would you like to do?");
        System.out.println("..........................................");
    }

    public int printMenuAndGetChoiceAdmin(Scanner in)
    {
        final int MIN_CHOICE = 0;
        final int MAX_CHOICE = 3;
        welcomeTextForAdmin();
        System.out.println("1.) List all books");
        System.out.println("2.) List all customers");
        System.out.println("3.) Add books to inventory");
        System.out.println("0.) Exit");

        try
        {
            int choice = Integer.parseInt(in.nextLine());
            if(choice < MIN_CHOICE || choice > MAX_CHOICE) {
                throw new Exception("Please enter a valid menu number between " + MIN_CHOICE + " and " + MAX_CHOICE + "!");
            }
            return choice;
        }
        catch(NumberFormatException ex) {
            System.out.println("Invalid input! " + ex.getMessage());
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }

        return -1;
    }

     public void addNewBookByAdmin(Scanner in) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        java.sql.Date sqlDate;
        System.out.println("Choose book ID");
        int id = Integer.parseInt(in.nextLine());
        System.out.println("Choose title");
        String title = in.nextLine();
        System.out.println("Enter autor name");
        String autor = in.nextLine();
        System.out.println("Enter isbn");
        int isbn =  Integer.parseInt(in.nextLine());
        System.out.println("Enter fsk please");
        int fsk =  Integer.parseInt(in.nextLine());
        System.out.println("Enter publishingCompany");
        String publishingCompany = in.nextLine();
        System.out.println("Enter appearance please(yyyy-MM-dd)");
        Date appearance = Date.valueOf(in.nextLine());
        System.out.println("Enter amountPages please");
        int amountPages = Integer.parseInt(in.nextLine());
        System.out.println("Enter language please");
        String language = in.nextLine();
        System.out.println("Enter idWordCategory please");
        int idWordCategory = Integer.parseInt(in.nextLine());
        System.out.println("Enter bookInStock please");
        //String bookInStock = in.nextLine();
         int bookInStock = Integer.parseInt(in.nextLine());

        bookRepository.addBook(new Book(id,title,autor,isbn,fsk,publishingCompany,appearance,amountPages,language,idWordCategory,bookInStock));
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            int choice = printMenuAndGetChoiceAdmin(in);
            if (choice == 0) {
                break;
            } else if (choice == 1) {
                List<Book> books = bookRepository.getAllBooks();
                for (Book book : books) {
                    System.out.println(book);
                }
            } else if (choice == 2) {
                List<Customer> customers = customerRepository.getAllCustomers();
                for (Customer customer : customers) {
                    System.out.println(customer);
                }
            } else if(choice == 3) {
                addNewBookByAdmin(in);
                System.out.println("Congratulation Lukas, you added a new book to your library!");
                System.out.println("If you want to add other books,please press 3");
            }

            System.out.println("------------------");
        }
    }
}
