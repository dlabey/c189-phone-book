package com.phone_book;

/**
 * DL: Person
 * DL: Represents the person in the phone book
 */
class Person {
    // DL: Name
    String name;
    // DL: Name
    String email_address;
    // DL: Name
    String phone_number;

    /**
     * DL: Constructor
     * @param first_name First name
     * @param last_name Last name
     * @param email_address Email address
     * @param phone_number Phone number
     */
    Person(String first_name, String last_name, String email_address, String phone_number) {
        this.name = first_name + " " + last_name;
        this.email_address = email_address;
        this.phone_number = phone_number;
    }
}
