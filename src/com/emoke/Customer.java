package com.emoke;

import java.sql.Date;

public class Customer {
    private int id;
    private String pinCodeHash;
    private String email;
    private String firstName;
    private String surname;
    private Date birthday;
    private String address;
    private String creditCardNumber;
    private String cvc;
    private Date expiryDate;

    public Customer(int id, String pinCodeHash, String email, String firstName, String surname, Date birthday, String address, String creditCardNumber, String cvc, Date expiryDate) {
        this.id = id;
        this.pinCodeHash = pinCodeHash;
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;
        this.birthday = birthday;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.cvc = cvc;
        this.expiryDate = expiryDate;
    }

    public int getId() {
        return id;
    }

    public String getPinCodeHash() {
        return pinCodeHash;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCvc() {
        return cvc;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id: " + id + "|" +
                "PIN: " + pinCodeHash + "|" + '\'' +
                "Emailaddress: " + email + "|" + '\'' +
                "Firstname: " + firstName + "|" + '\'' +
                "Surname: " + surname + "|" + '\'' +
                "Birthday: " + birthday +  "|" + '\'' +
                "Address" + address + '\'' +
                "Creditcardnumber: " + creditCardNumber + '\'' +
                "CVC: " + cvc + '\'' +
                "Expirydate: " + expiryDate +
                '}';
    }
}
