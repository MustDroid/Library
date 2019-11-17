package com.emoke;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCustomerRepository implements ICustomerRepository {
    private final Connection connection;

    public DatabaseCustomerRepository(Connection connection) {
        this.connection = connection;
        createTableIfNotExists();
    }

    public void createTableIfNotExists() {
        Statement st = null;
        try {
            st = connection.createStatement();
            st.execute("CREATE TABLE IF NOT EXISTS `customers` (\n" +
                    "  `id` int(11) NOT NULL,\n" +
                    "  `pinCodeHash` varchar(50) NOT NULL,\n" +
                    "  `email` varchar(100) NOT NULL,\n" +
                    "  `firstName` varchar(50) NOT NULL,\n" +
                    "  `surname` varchar(50) NOT NULL,\n" +
                    "  `birthday` date NOT NULL,\n" +
                    "  `address` varchar(200) NOT NULL,\n" +
                    "  `creditCardNumber` varchar(20) NOT NULL,\n" +
                    "  `cvc` varchar(10) NOT NULL,\n" +
                    "  `expiryDate` date NOT NULL\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM customers");
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                customers.add(getCustomerFromResultSet(rs));
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return customers;
    }

    @Override
    public Customer getCustomerById(int id) {
        Customer customer = null;

        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM customers WHERE id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if(rs.next()) {
                customer = getCustomerFromResultSet(rs);
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    @Override
    public void addCustomer(Customer customer) {

        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO customers (pinCodeHash, email, firstName, surname, birthday, address, creditCardNumber, cvc, expiryDate)" +
                    " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, customer.getPinCodeHash());
            st.setString(2, customer.getEmail());
            st.setString(3, customer.getFirstName());
            st.setString(4, customer.getSurname());
            st.setDate(5, customer.getBirthday());
            st.setString(6, customer.getAddress());
            st.setString(7, customer.getCreditCardNumber());
            st.setString(8, customer.getCvc());
            st.setDate(9, customer.getExpiryDate());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer findCustomerByFirstNameSurname(String firstName, String surname) {
        Customer customer = null;
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT * FROM customers WHERE firstName=? AND surname=?");
            pr.setString(1, firstName);
            pr.setString(2, surname);
            ResultSet rs = pr.executeQuery();
            if(rs.next()) {
                customer = getCustomerFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    private Customer getCustomerFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String pinCodeHash = rs.getString("pinCodeHash");
        String email = rs.getString("email");
        String firstName = rs.getString("firstName");
        String surname = rs.getString("surname");
        Date birthday = rs.getDate("birthday");
        String address = rs.getString("address");
        String creditCardNumber = rs.getString("creditCardNumber");
        String cvc = rs.getString("cvc");
        Date expiryDate = rs.getDate("expiryDate");

        return new Customer(id, pinCodeHash, email, firstName, surname, birthday, address, creditCardNumber, cvc, expiryDate);
    }
}
