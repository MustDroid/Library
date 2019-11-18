package com.emoke;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> getAllCustomers();
    Customer getCustomerById(int id);
    void addCustomer(Customer customer);
    Customer findCustomerByFirstNameSurname(String firstName, String surname);
    void deleteCustomer(Customer customer);

    // coding guidelines
    // https://github.com/isocpp/CppCoreGuidelines/blob/master/CppCoreGuidelines.md
}
