package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

class Comp implements Comparator<String> { // comparator to sort values in map
    Map<String, Integer> m;

    public Comp(HashMap<String, Integer> m) {
        this.m = m;
    }

    public int compare(String first, String second) {
        if (m.get(first) < m.get(second)) {
            return 1;
        } else {
            return -1;
        }
    }
}

public class Main {
    static void printTop(HashMap<String, Integer> m, int k) throws IOException {
        try(PrintWriter writer = new PrintWriter("answer.txt", StandardCharsets.UTF_8)) {
            Comp comp = new Comp(m);
            TreeMap<String, Integer> sorted = new TreeMap<>(comp);
            sorted.putAll(m);
            for (int i = 0; i < k; i++) {
                writer.println(sorted.firstKey() + ' ' + m.get(sorted.firstKey()));
                sorted.pollFirstEntry();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        final int k = 10;

        try (BufferedReader br =
                     new BufferedReader(new FileReader("input.txt"))) {
            HashMap<String, Integer> m = new HashMap<>();
            while (true) {
                String cur = br.readLine();
                if (cur == null) {
                    break;
                }
                String thread = cur.split("\\s")[5]; // split strings by spaces
                if (m.containsKey(thread)) {
                    m.put(thread, m.get(thread) + 1);
                } else {
                    m.put(thread, 1);
                }
            }
            printTop(m, k);
        }
    }
}
