package com.phone_book;

/**
 * Binary Tree Node
 * Generic implementation of a binary tree node
 */
class BinaryTreeNode {
    // DL: Right
    private BinaryTreeNode right;
    // DL: Left
    BinaryTreeNode left;
    // DL: Person
    Person person;

    /**
     * DL: Constructor
     * DL: Constructor for an empty node
     */
    BinaryTreeNode() {
        person = null;
        left = null;
        right = null;
    }

    /**
     * DL: Constructor
     * DL: Constructor for a node with a person
     * @param person Person
     */
    BinaryTreeNode(Person person) {
        this.person = person;
        left = null;
        right = null;
    }

    /**
     * DL: Min
     * DL: Get the min node based on which one is populated
     * @return Person|Null Person or null when there is no person
     */
    private Person min() {
        if (left == null) {
            return person;
        } else {
            return left.min();
        }
    }

    /**
     * DL: Insert
     * DL: Insert a new person into the binary tree
     * @param first_name First name
     * @param last_name Last name
     * @param email_address Email address
     * @param phone_number Phone Number
     * @return Person The inserted or updated person
     */
    Person insert(String first_name, String last_name, String email_address, String phone_number) {
        Person person = new Person(first_name, last_name, email_address, phone_number);

        // DL: If the new person is this node's person update this node's person
        if (person.name.compareTo(this.person.name) == 0) {
            this.person = person;

            System.out.println(person.name + " was replaced in the binary tree with the new version");

            return this.person;
        } else if (person.name.compareTo(this.person.name) < 0) {
            // DL: If the new person comes before this node's person insert it as a left
            // DL: If the left is null set it as the left
            if (left == null) {
                left = new BinaryTreeNode(person);

                System.out.println(person.name + " was inserted into the binary tree");

                return left.person;
            } else {
                return left.insert(first_name, last_name, email_address, phone_number);
            }
        } else {
            // DL: If the new person comes after this node's person insert it as a right
            // DL: If the right is null set it as the right
            if (right == null) {
                right = new BinaryTreeNode(person);

                System.out.println(person.name + " was inserted into the binary tree");

                return right.person;
            } else {
                return right.insert(first_name, last_name, email_address, phone_number);
            }
        }
    }

    /**
     * DL: Lookup
     * DL: Searches for a person by name
     * @param name Name
     * @return Person|Null A person if one was found
     */
    Person lookup(String name) {
        // DL: If this node is the person return it
        if (name.compareTo(person.name) == 0) {
            System.out.println(name + " was found in the binary tree with the email address " + person.email_address + " and the phone number " + person.phone_number);

            return person;
        } else if (name.compareTo(person.name) < 0) {
            // DL: If the person comes before this node's person search the left
            // DL: If there is no left there is no match
            if (left == null) {
                System.out.println(name + " was not found in the binary tree");

                return null;
            } else {
                return left.lookup(name);
            }
        } else {
            // DL: If the person comes after this node's person search the right
            // DL: If there is no right there is no match
            if (right == null) {
                System.out.println(name + " was not found in the binary tree");

                return null;
            } else {
                return right.lookup(name);
            }
        }
    }

    /**
     * DL: Delete
     * DL: Deletes a person by their name and their parent
     * @param name Name
     * @param parent Parent
     * @return Boolean Whether or not the person was deleted
     */
    Boolean delete(String name, BinaryTreeNode parent) {
        // DL: If this node is the person delete this node and maintain the binary tree
        if (name.compareTo(person.name) == 0) {
            // DL: If there is a left and a right traverse the right first to find a match to delete
            if (left != null && right != null) {
                return right.delete(name, this);
            } else if (parent.left == this) {
                // DL: If the parent's left is this node's match set it to null to delete it
                parent.left = left != null ? left: right;

                System.out.println(name + " was deleted from the binary tree");

                return true;
            } else if (parent.right == this) {
                // DL: If the parent's right is this node's match set it to null to delete it
                parent.right = left != null ? left : right;

                System.out.println(name + " was deleted from the binary tree");

                return true;
            }
        } else if (name.compareTo(person.name) < 0) {
            // DL: If the person searched for is less than this node's person delete from the left
            // DL: If there is a left to delete from continue the search for deletion
            // DL: Otherwise there is no left to delete from there is no match to delete
            if (left != null) {
                return left.delete(name, this);
            } else {
                System.out.println(name + " was not deleted from the binary because they weren't in it");

                return false;
            }
        } else {
            // DL: If the person searched for is greater than this node's person delete from the right
            // DL: If there is a right to delete from continue the search for deletion
            // DL: Otherwise there is no right to delete from there is no match to delete
            if (right != null) {
                return right.delete(name, this);
            } else {
                System.out.println(name + " was not deleted from the binary because they weren't in it");

                return false;
            }
        }

        System.out.println(name + " was not deleted from the binary because they weren't in it");

        return false;
    }
}
