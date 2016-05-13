package com.phone_book;

/**
 * DL: Binary Tree
 * DL: Generic implementation of a binary tree
 */
class BinaryTree {
    // DL: The tree root
    private BinaryTreeNode root;

    /**
     * DL: Constructor
     */
    BinaryTree() {
        root = null;
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
     * DL: Inserts a new entry into the binary tree
     * @param first_name First name
     * @param last_name Last name
     * @param email_address Email address
     * @param phone_number Phone Number
     * @return Person Person that was inserted
     */
    Person insert(String first_name, String last_name, String email_address, String phone_number) {
        Person person = new Person(first_name, last_name, email_address, phone_number);

        // DL: If we don't have a root set the entry to be the root
        if (root == null) {
            root = new BinaryTreeNode(person);

            System.out.println(person.name + " was inserted into the binary tree");

            return root.person;
        } else {
            // DL: Otherwise insert an entry via the root
            return root.insert(first_name, last_name, email_address, phone_number);
        }
    }

    /**
     * DL: Lookup
     * DL: Searches for a person by name
     * @param first_name First name
     * @param last_name Last name
     * @return Person|Null A person if the name was found
     */
    Person lookup(String first_name, String last_name) {
        String name = namify(first_name, last_name);

        // DL: If root is null we won't have any entries O(1)
        if (root == null) {
            System.out.println(name + " was not found in the binary tree");

            return null;
        } else {
            // DL: Lookup the name view the root and itself
            return root.lookup(name);
        }
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

        // DL: If root is null we won't have any entries O(1)
        if (root == null) {
            System.out.println(name + " was not deleted from the binary because they weren't in it");

            return false;
        } else {
            // DL: Check if root is the person to delete
            if (root.person.name.compareTo(name) == 0) {
                // DL: If root is the person to delete, we need a temporary root for transition
                BinaryTreeNode tmpRoot = new BinaryTreeNode();

                // DL: Set the temporary root left to the root, delete the root, then set the root back to the temporary empty root
                tmpRoot.left = root;
                root.delete(name, tmpRoot);
                root = tmpRoot.left;

                System.out.println(name + " was deleted from the binary tree");

                return true;
            } else {
                return root.delete(name, null);
            }
        }
    }
}
