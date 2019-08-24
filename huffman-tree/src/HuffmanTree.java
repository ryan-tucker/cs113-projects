package edu.miracosta.cs113;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanTree extends BinaryTree implements HuffmanInterface {
    public int[] characterFrequencies;
    public String[] characterEncodings;

    public HuffmanTree(String text) {
        super();
        characterFrequencies = new int[128];
        characterEncodings = new String[128];
        generateCharacterFrequencies(new BufferedReader(new StringReader(text)));
        root = constructHuffmanTree();
        generateCharacterEncodings();
    }

    public HuffmanTree(File textFile){
        super();
        characterFrequencies = new int[128];
        characterEncodings = new String[128];
        try{
            generateCharacterFrequencies(new BufferedReader(new FileReader(textFile)));
        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        }
        root = constructHuffmanTree();
        generateCharacterEncodings();

    }

    private void generateCharacterFrequencies(BufferedReader reader){
        int current;
        try{
            current = reader.read();
            while (current != -1) {
                characterFrequencies[current] += 1;
                current = reader.read();
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private Node<HuffmanData> constructHuffmanTree() {
        PriorityQueue<Node<HuffmanData>> queue = generateHuffmanQueue();
        Node<HuffmanData> left, right, parent;
        int frequency;
        while (queue.size() > 1) {
            left = queue.remove();
            right = queue.remove();
            frequency = left.data.frequency + right.data.frequency;
            parent = new Node<>(new HuffmanData(frequency));
            parent.left = left;
            parent.right = right;
            queue.add(parent);
        }
        return queue.remove();
    }

    private void generateCharacterEncodings(){
        generateCharacterEncodings(root,"");
    }

    private void generateCharacterEncodings(Node<HuffmanData> node, String code) {
        if (node.data.data != Character.MAX_VALUE) {
             characterEncodings[node.data.data] = code;
        }
        else {
            generateCharacterEncodings(node.left, code + "0");
            generateCharacterEncodings(node.right, code + "1");
        }
    }

    @Override
    public String decode(String codedMessage) {
        StringBuilder sb = new StringBuilder();
        StringReader reader = new StringReader(codedMessage);
        Node<HuffmanData> currentNode = root;
        int charValue;
        try{
            charValue = reader.read();
            while (charValue != -1) {
                if (charValue == '0') {
                    currentNode = currentNode.left;
                }else if (charValue == '1') {
                    currentNode = currentNode.right;
                }
                if (currentNode.left == null && currentNode.right == null) {
                    sb.append(currentNode.data.data);
                    currentNode = root;
                }
                charValue = reader.read();
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return sb.toString();
    }

    @Override
    public String encode(String message) {
        StringReader reader = new StringReader(message);
        StringBuilder sb = new StringBuilder();
        int current;
        try {
            current = reader.read();
            while (current != -1) {
                sb.append(characterEncodings[current]);
                current = reader.read();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return sb.toString();
    }

    private PriorityQueue<Node<HuffmanData>> generateHuffmanQueue() {
        PriorityQueue<Node<HuffmanData>> queue = new PriorityQueue<>(128, new Comparator<Node<HuffmanData>>() {
            @Override
            public int compare(Node<HuffmanData> o1, Node<HuffmanData> o2) {
               return o1.data.compareTo(o2.data);
            }
        });

        populatePriorityQueue(queue);
        return queue;
    }

    private void populatePriorityQueue(PriorityQueue<Node<HuffmanData>> huffmanQueue) {
        Node<HuffmanData> current;
        for (int i = 0; i < characterFrequencies.length; i ++){
            if (characterFrequencies[i] != 0){
                huffmanQueue.add(new Node(new HuffmanData((char)i,characterFrequencies[i])));
            }
        }

    }

    private static class HuffmanData implements Comparable<HuffmanData> {
        private char data;
        private int frequency;

        private HuffmanData() {
            this.data = Character.MAX_VALUE;
            this.frequency = 0;
        }

        private HuffmanData(char data) {
            this.data = data;
            this.frequency = 0;
        }

        private HuffmanData(char data, int frequency) {
            this.data = data;
            this.frequency = frequency;
        }

        private HuffmanData(int frequency) {
            this.data = Character.MAX_VALUE;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(HuffmanData o) {
            return this.frequency - o.frequency;
        }

        @Override
        public String toString() {
            return data + ": " + frequency;
        }
    }
}
