package com.phone_book;

/**
 * DL: Hash Table Node
 * DL: Generic implementation of a hash table node
 * @param <string> String (name)
 * @param <Person> Person
 */
class HashTableNode<string, Person> {
    // DL: The hash (identifier)
    final int hash;
    // DL: The name
    final string name;
    // DL: The person
    Person person;
    // DL: The next node (linked-list)
    HashTableNode<string, Person> next;

    HashTableNode(int hash, string name, Person person, HashTableNode<string, Person> next) {
        this.hash = hash;
        this.name = name;
        this.person = person;
        this.next = next;
    }
}
