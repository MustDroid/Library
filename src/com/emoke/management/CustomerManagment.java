package com.emoke.management;

import com.emoke.*;

import java.sql.Date;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;


public class CustomerManagment {
    public ICustomerRepository customerRepository;
    public IBookRepository bookRepository;
    public IWordRepository wordRepository;

    public CustomerManagment() {
        customerRepository = new DatabaseCustomerRepository(MySQLDbConnection.getInstance().getConnection());
        bookRepository = new DatabaseBookRepository(MySQLDbConnection.getInstance().getConnection());
        wordRepository = new DatabaseWordRepository(MySQLDbConnection.getInstance().getConnection());
    }

    public  void welcomeTextForCustomer() {
        System.out.println("Welcome to Lukas's Libary");
        System.out.println("My name is BATGA, your personal assistent");
        System.out.println("..........................................");
        System.out.println("How can I help you?");
        System.out.println("..........................................");
    }

    public int printMenuAndGetChioceCustomer(Scanner in) {
        final int MIN_CHOICE = 0;
        final int MAX_CHOICE = 3;
        welcomeTextForCustomer();

        System.out.println("1.) Log in");
        System.out.println("2.) Rent a book");
        System.out.println("3.) Browse books");
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

    public void searchByNameLogin (Scanner in){
        final String SAY_YES = "Yes";
        final String SAY_NO = "No";
        System.out.println("Enter first name please");
        String firstName = in.nextLine();
        System.out.println("Enter surname please");
        String surname = in.nextLine();

        Customer customer = customerRepository.findCustomerByFirstNameSurname(firstName, surname);

        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer not found in database! Would you like to register?");
            String answer = in.nextLine();
            if (answer.equals(SAY_YES)) {
                System.out.println("Im looking forward to welcoming you! ");
                registerCustomer(in);
                System.out.println("You are now registered in Lukas's Libary:)");
            } else {
                System.out.println("Thank you for your visiting, maybe next time!");
            }
        }
    }


    public void registerCustomer (Scanner in){
        in = new Scanner(System.in);
        //Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        java.sql.Date sqlDate;
        System.out.println("Choose your personal ID number");
        int id = Integer.parseInt(in.nextLine());
        System.out.println("Choose your personal PIN");
        String pinCodeHash = in.nextLine();
        System.out.println("Enter your email address please");
        String email = in.nextLine();
        System.out.println("Enter first name please");
        String firstName = in.nextLine();
        System.out.println("Enter surname please");
        String surname = in.nextLine();
        System.out.println("Enter birthday please (yyyy-mm-dd)");
        Date birthday = Date.valueOf(in.nextLine());
        System.out.println("Enter address please");
        String address = in.nextLine();
        System.out.println("Enter credit card number please");
        String creditCardNumber = in.nextLine();
        System.out.println("Enter cvc please");
        String cvc = in.nextLine();
        System.out.println("Enter expiryDate please");
        Date expiryDate = Date.valueOf(in.nextLine());

        customerRepository.addCustomer(new Customer(id, pinCodeHash, email, firstName, surname, birthday, address, creditCardNumber, cvc, expiryDate));
    }



   public void rentABook(Scanner in) {
       LocalDate today = LocalDate.now();
       LocalDate in2weeks = today.plus(14, ChronoUnit.DAYS);
       System.out.println("Enter book id please");
       int  id = Integer.parseInt(in.nextLine());
       bookRepository.rentBook(id);
       System.out.println("You rented the book on " + today);
       System.out.println("You should bring it on " + in2weeks);
       System.out.println("If you want to rent other books,please press 2 or 0 to exit");

   }
   public void qoutesForCustomer(int idWordCategory) {
        if (idWordCategory == 1) {
            System.out.println("My quote for you is: ");
            System.out.println("The good you do today will be forgotten tomorrow.Do good anyway.");
            System.out.println(".................................................................");
        }else if (idWordCategory == 2) {
            System.out.println("My quote for you is: ");
            System.out.println("Everything that has a beginning has an ending. Make your peace with that and all will be well");
            System.out.println(".................................................................");
        } else if (idWordCategory == 3) {
            System.out.println("My quote for you is: ");
            System.out.println("The average dog is a nicer person than the average person");
            System.out.println(".................................................................");
        }else if (idWordCategory == 4) {
            System.out.println("My quote for you is: ");
            System.out.println("All you need is love. But a little chocolate now and then doesn’t hurt");
            System.out.println(".................................................................");
        }else  {
            System.out.println("My quote for you is: ");
            System.out.println("You are the source of my joy, the center of my world and the whole of my heart");
            System.out.println(".................................................................");
        }
   }


    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            int choice = printMenuAndGetChioceCustomer(in);
            if (choice == 0) {
                System.out.println("Thank you for your visit in Lukas's Library");
                break;
            } else if (choice == 1) {
                searchByNameLogin(in);
            } else if (choice == 2) {
                System.out.println("How do you feel today?");
                String word = in.nextLine();
                int idWordCategory = wordRepository.getWordCategoryFromWord(word);
                //System.out.println("CATEGORY IS " + idWordCategory);
                qoutesForCustomer(idWordCategory);
                System.out.println("Found following recommended books for you:");
                List<Book> recommendedBooks = bookRepository.getBooksByCategory(idWordCategory);
               // System.out.println("Found following recommended books for you:");
                for(Book book : recommendedBooks) {
                    System.out.println(book);
                }
                rentABook(in);

            }else if(choice == 3) {
                List<Book> browseBook = bookRepository.getAllBooks();
                for (Book book : browseBook) {
                    System.out.println(book);
                }
                rentABook(in);
            }
            System.out.println("------------------");
        }
    }

}
