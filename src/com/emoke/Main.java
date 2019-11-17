package com.emoke;

import com.emoke.management.AdminManagement;
import com.emoke.management.CustomerManagment;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static ICustomerRepository customerRepository;
    public static IBookRepository bookRepository;
    public static IWordRepository wordRepository;



    public static void main(String[] args) {
        customerRepository = new DatabaseCustomerRepository(MySQLDbConnection.getInstance().getConnection());
        bookRepository = new DatabaseBookRepository(MySQLDbConnection.getInstance().getConnection());
        wordRepository = new DatabaseWordRepository(MySQLDbConnection.getInstance().getConnection());

        Scanner in = new Scanner(System.in);
        System.out.println("BATGA is loading");
        System.out.println("Please enter your name!");
        String name = in.nextLine();

        if (name.equalsIgnoreCase("lUKAs") || name.equalsIgnoreCase("Luki")) {
            AdminManagement admin = new AdminManagement();
            admin.run();
        }else {
            CustomerManagment customer = new CustomerManagment();
            customer.run();
        }


        //comedy
        Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        bookRepository.addBook(new Book(1,"Cartman und die Analsonde","Matt Stone",12345,567,"Comedy Central",now,20,"german",3,30));
        bookRepository.addBook(new Book(2,"Mr. Hankey, der Weihnachtskot","Matt Stone",12345,456,"Comedy Central",now,20,"german",3,45));
        bookRepository.addBook(new Book(3,"Creme Fraiche","Matt Stone",12345,456,"Comedy Central",now,30,"german",3,90));
        bookRepository.addBook(new Book(4,"Der letzte Meheekaner","Matt Stone",234566,678,"Comedy Central",now,34,"german",3,78));

        //Cookingbook
        bookRepository.addBook(new Book(5,"Jamie’s Dinners","Jamie Oliver",12335,456,"Penguin Group",now,44,"english",4,67));
        bookRepository.addBook(new Book(6,"COOK with Jamie","Jamie Oliver",45669,890,"Penguin Group",now,33,"english",4,8));
        bookRepository.addBook(new Book(7,"Jamie at Home","Jamie Oliver",45669,890,"Penguin Group",now,33,"english",4,8));
        bookRepository.addBook(new Book(8,"Jamie's 15-Minute Meals","Jamie Oliver",45669,890,"Penguin Group",now,33,"english",4,8));



        //Books about positivity
        bookRepository.addBook(new Book(9,"Fragen und Antworten","Jiddu Krishnamurti",675847648,8978,"Penguin Group",now,90,"german",2,78));
        bookRepository.addBook(new Book(10,"Leben ohne Illusionen","Jiddu Krishnamurti",675847648,8978,"Penguin Group",now,90,"german",2,78));
        bookRepository.addBook(new Book(11,"Einbruch in die Freiheit","Jiddu Krishnamurti",675847648,8978,"Penguin Group",now,90,"german",2,78));
        bookRepository.addBook(new Book(12,"Vom Werden zum Sein","Jiddu Krishnamurti",675847648,8978,"Penguin Group",now,90,"german",2,78));


        //Books Relationships
        bookRepository.addBook(new Book(13,"Vom Nehmen und Genommen werden","Doris Christinger",785978495,7869578,"Penguin Group",now,45,"german",5,67));
        bookRepository.addBook(new Book(14,"Nackt zur Wahrheit","David Deida",785978495,7869578,"Penguin Group",now,45,"german",5,67));
        bookRepository.addBook(new Book(15,"Darüber spricht man nicht","Yael Adler",785978495,7869578,"Penguin Group",now,45,"german",5,67));
        bookRepository.addBook(new Book(16,"Die fünf Sprachen der Liebe","Gary Chapman",785978495,7869578,"Penguin Group",now,45,"german",5,67));

        //Books Happiness
        bookRepository.addBook(new Book(17,"Enjoy this Life - In 30 Tagen zu dir selbst","Pascal Voggenhuber",846758,7878,"Penguin Group",now,67,"german",1,11));
        bookRepository.addBook(new Book(17,"Das Leben ist zu kurz fur spater"," Alexandra Reinwarth",846758,7878,"Penguin Group",now,67,"german",1,11));
        bookRepository.addBook(new Book(17,"Why not?","Lars Amend",846758,7878,"Penguin Group",now,67,"german",1,11));
        bookRepository.addBook(new Book(17,"Das Powerprinzip. Grenzenlose Energie"," Anthony Robbins",846758,7878,"Penguin Group",now,67,"german",1,11));





    }

}