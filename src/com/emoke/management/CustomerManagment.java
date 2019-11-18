package com.emoke.management;

import com.emoke.*;

import java.sql.Date;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        final int MAX_CHOICE = 2;
        welcomeTextForCustomer();

        System.out.println("1.) Log in");
        System.out.println("2.) Rent a book");
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

   /* public void addNewBookByAdmin(Scanner in) {
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
        System.out.println("Enter appearance please");
        Date appearance = Date.valueOf(in.nextLine());
        System.out.println("Enter amountPages please");
        int amountPages = Integer.parseInt(in.nextLine());
        System.out.println("Enter language please");
        String language = in.nextLine();
        System.out.println("Enter idWordCategory please");
        int idWordCategory = Integer.parseInt(in.nextLine());
        System.out.println("Enter bookInStock please");
        String bookInStock = in.nextLine();
    }*/

   public void rentABook(Scanner in) {
       System.out.println("Enter book id please");
       int  id = Integer.parseInt(in.nextLine());
       bookRepository.rentBook(id);
       System.out.println("You rented the book");

   }



    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            int choice = printMenuAndGetChioceCustomer(in);
            if (choice == 0) {
                break;
            } else if (choice == 1) {
                searchByNameLogin(in);
            } else if (choice == 2) {
                System.out.println("How do you feel today?");
                String word = in.nextLine();
                int idWordCategory = wordRepository.getWordCategoryFromWord(word);
                System.out.println("CATEGORY IS " + idWordCategory);
                System.out.println("Found following recommended books for you:");
                List<Book> recommendedBooks = bookRepository.getBooksByCategory(idWordCategory);
               // System.out.println("Found following recommended books for you:");
                for(Book book : recommendedBooks) {
                    System.out.println(book);

                }
                rentABook(in);
            }

            System.out.println("------------------");
        }
    }

}
