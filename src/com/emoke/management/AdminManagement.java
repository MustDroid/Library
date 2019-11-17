package com.emoke.management;

import com.emoke.*;

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
        final int MAX_CHOICE = 2;
        welcomeTextForAdmin();
        System.out.println("1.) List all books");
        System.out.println("2.) List all customers");
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
            }

            System.out.println("------------------");
        }
    }
}
