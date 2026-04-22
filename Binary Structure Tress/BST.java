import javax.swing.JOptionPane;

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

class BST {
    Node root;

    BST() {
        root = null;
    }

    // Insert
    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);
        // Duplicates not allowed (silently ignored)
        return root;
    }

    // Search
    boolean search(int key) {
        return searchRec(root, key);
    }

    boolean searchRec(Node root, int key) {
        if (root == null)
            return false;
        if (root.key == key)
            return true;
        if (key < root.key)
            return searchRec(root.left, key);
        return searchRec(root.right, key);
    }

    // Inorder traversal (returns a string)
    String inorder() {
        StringBuilder sb = new StringBuilder();
        inorderRec(root, sb);
        return sb.toString().trim();
    }

    void inorderRec(Node root, StringBuilder sb) {
        if (root != null) {
            inorderRec(root.left, sb);
            sb.append(root.key).append(" ");
            inorderRec(root.right, sb);
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();

        // Optional: Preload the dataset from Part A
        int[] initialData = {50, 30, 70, 20, 40, 60, 80};
        for (int val : initialData) {
            tree.insert(val);
        }

        while (true) {
            String menu = "Binary Search Tree Operations\n\n" +
                    "1. Insert a number\n" +
                    "2. Search for a number\n" +
                    "3. Show Inorder Traversal (sorted order)\n" +
                    "4. Exit\n\n" +
                    "Enter your choice:";
            String choiceStr = JOptionPane.showInputDialog(menu);
            if (choiceStr == null) break; // Cancel or close

            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter 1-4.");
                continue;
            }

            switch (choice) {
                case 1:
                    String insVal = JOptionPane.showInputDialog("Enter number to insert:");
                    if (insVal == null) break;
                    try {
                        int num = Integer.parseInt(insVal);
                        tree.insert(num);
                        JOptionPane.showMessageDialog(null, num + " inserted successfully.");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                    }
                    break;

                case 2:
                    String searchVal = JOptionPane.showInputDialog("Enter number to search:");
                    if (searchVal == null) break;
                    try {
                        int num = Integer.parseInt(searchVal);
                        boolean found = tree.search(num);
                        if (found)
                            JOptionPane.showMessageDialog(null, num + " is FOUND in the tree.");
                        else
                            JOptionPane.showMessageDialog(null, num + " is NOT in the tree.");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                    }
                    break;

                case 3:
                    String sorted = tree.inorder();
                    JOptionPane.showMessageDialog(null, "Inorder Traversal (sorted):\n" + sorted);
                    break;

                case 4:
                    JOptionPane.showMessageDialog(null, "Exiting program.");
                    System.exit(0);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please enter 1-4.");
            }
        }
    }
}