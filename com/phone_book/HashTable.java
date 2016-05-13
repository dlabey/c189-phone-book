package com.phone_book;

/**
 * DL: Hash Table
 * DL: Generic implementation of a hash table
 */
class HashTable {
    // DL: The hash table node dictionary array
    private HashTableNode<String, Person>[] nodes;

    /**
     * DL: Constructor
     * @param size Size of the hash table
     */
    @SuppressWarnings("unchecked")
    HashTable(int size) {
        nodes = new HashTableNode[size];
    }

    /**
     * DL: Get Index
     * DL: Gets the hash index for the hash table
     * @param name Name
     * @return Integer The hash index if found
     */
    private Integer getIndex(String name) {
        Integer hash = name.hashCode() % nodes.length;

        if (hash < 0) {
            hash += nodes.length;
        }

        return hash;
    }

    /**
     * DL: Namify
     * DL: Concatenates a first and last name into a full name
     * @param first_name First name
     * @param last_name Last name
     * @return String
     */
    private static String namify(String first_name, String last_name) {
        return first_name + " " + last_name;
    }

    /**
     * DL: Insert
     * DL: Inserts a new person into the hash table
     * @param first_name First name
     * @param last_name Last name
     * @param email_address Email address
     * @param phone_number Phone Number
     * @return Person The inserted or updated person
     */
    @SuppressWarnings("unchecked")
    Person insert(String first_name, String last_name, String email_address, String phone_number) {
        Person person = new Person(first_name, last_name, email_address, phone_number);
        int hash = getIndex(person.name);

        // DL: Determine if the person was already in the hash table, and if so update them
        for (HashTableNode<String, Person> node = nodes[hash]; node != null; node = node.next) {
            if (hash == node.hash && person.name.equals(node.name)) {
                Person previous = node.person;

                node.person = person;

                System.out.println(person.name + " was replaced in the hash table with the new version");

                return previous;
            }
        }

        // DL: Insert the person fresh into the hash table
        HashTableNode<String, Person> node = new HashTableNode(hash, person.name, person, nodes[hash]);
        nodes[hash] = node;

        System.out.println(person.name + " was inserted into the hash table");

        return person;
    }

    /**
     * DL: Lookup
     * DL: Searches for a person by name
     * @param first_name First name
     * @param last_name Last name
     * @return Person|Null A person if one was found
     */
    Person lookup(String first_name, String last_name) {
        String name = namify(first_name, last_name);
        int hash = getIndex(name);

        // DL: Search the hash table and its nodes for the person
        for (HashTableNode<String, Person> node = nodes[hash]; node != null; node = node.next) {
            if (name.equals(node.name)) {
                System.out.println(name + " was found in the hash table with the email address " + node.person.email_address + " and the phone number " + node.person.phone_number);

                return node.person;
            }
        }

        System.out.println(name + " was not found in the hash table");

        return null;
    }

    /**
     * DL: Delete
     * DL: Deletes a person by name
     * @param first_name First name
     * @param last_name Last name
     * @return Boolean True if the person was deleted or false if they weren't
     */
    Boolean delete(String first_name, String last_name) {
        String name = namify(first_name, last_name);
        int hash = getIndex(name);
        HashTableNode<String, Person> previous = null;

        // DL: Search the hash table for the person, and if it is found delete them
        for (HashTableNode<String, Person> node = nodes[hash]; node != null; node = node.next) {
            if (hash == node.hash && name.equals(node.name)) {
                if (previous != null) {
                    previous.next = node.next;
                } else {
                    nodes[hash] = node.next;
                }

                System.out.println(name + " was deleted from the hash table");

                return true;
            }

            previous = node;
        }

        System.out.println(name + " was not deleted from the hash table because they weren't in it");

        return false;
    }
}