package edu.miracosta.cs113;

import java.io.Serializable;
import java.util.Scanner;

/**
 * BinaryTree.java : A generic Binary Tree implementation. Currently only able to do pre-order traversal.
 *
 * @author Ryan Tucker
 * @version 1.0
 */
public class BinaryTree<E> implements Serializable {
    protected Node<E> root;

    /**
     * Default Constructor
     */
    public BinaryTree(){
        root = null;
    }

    /**
     * Constructor with initial root.
     *
     * @param root Generic Node containing the new root of this tree.
     */
    protected BinaryTree(Node<E> root){
        this.root = root;
    }

    /**
     * Constructor which merges two existing trees with a new root
     *
     * @param data Generic data to be stored in the new root of this tree
     * @param leftTree Binary Tree to be stored in the left node of this root
     * @param rightTree Binary Tree to be stored in the right node of this root
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree){
        this.root = new Node<>(data);
        if (leftTree != null){
            root.left = leftTree.root;
        }else{
            root.left = null;
        }

        if (rightTree != null){
            root.right = rightTree.root;
        }else{
            root.right = null;
        }
    }

    /**
     * Constructs a new BinaryTree<String> based on data read in by Scanner.
     *
     * Data must be in pre-order traversal, including null values for leaves.
     *
     * @param scan Scanner connected to data source containing pre-order traversal data
     * @return New BinaryTree<String>
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan){
        String data = scan.next();
        data = data.trim();
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<>(data, leftTree, rightTree);
        }
    }

    /**
     * Returns the left subtree
     *
     * @return Left BinaryTree
     */
    public BinaryTree<E> getLeftSubtree(){
        if (root != null && root.left != null){
            return new BinaryTree<E>(root.left);
        }else{
            return null;
        }
    }

    /**
     * Returns the right subtree
     *
     * @return Right BinaryTree
     */
    public BinaryTree<E> getRightSubtree(){
        if (root != null && root.right != null){
            return new BinaryTree<E>(root.right);
        }else{
            return null;
        }
    }

    /**
     * Accessor for this root Node's data
     *
     * @return Generic data type stored in this root Node.
     */
    public E getData(){
        return root.data;
    }

    /**
     * Checks if root Node of this tree is a leaf, or doesn't have any children.
     *
     * @return boolean True if no children, False if one or more children
     */
    public boolean isLeaf(){
        return root.left == null && root.right == null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        preorderTraverse(root,1, sb);
        return sb.toString();
    }

    /**
     * Recursive method for performing pre-order traversal which appends and indents
     * data based on its depth.
     *
     * @param node current root Node
     * @param depth depth of current Node
     * @param sb StringBuilder which will contain pre-ordered data from this tree
     */
    private void preorderTraverse(Node<E> node, int depth, StringBuilder sb){
        for (int i = 1; i < depth; i ++) {
            sb.append(" ");
        }

        if (node == null){
            sb.append("null\n");
        }else{
            sb.append(node.toString() + "\n");
            preorderTraverse(node.left, depth + 1, sb);
            preorderTraverse(node.right, depth + 1, sb);
        }
    }

    /**
     * Node: inner class representing a Node inside this tree
     * @param <E> Generic data type to be stored inside the Node
     */
    protected static class Node<E> implements Serializable {
        protected E data;
        protected Node left;
        protected Node right;

        /**
         * Constructor with Generic data type
         *
         * @param data Generic data to be stored in this Node
         */
        public Node(E data){
            this.data = data;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString(){
            return data.toString();
        }
    }
}
