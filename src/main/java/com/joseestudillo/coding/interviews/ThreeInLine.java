package com.joseestudillo.coding.interviews;

public class ThreeInLine {

    public static void main(String[] args) {
        char[][] table = { { 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' }, };

        char[][] table2 = { { 'a', 'a', 'a' }, { 'a', 'a', 'a' }, { 'a', 'a', 'a' }, };
        System.out.println(table[0][1]);
        System.out.println(table[1][1]);
        checkTable(table2);
    }

    private static final int N = 3;
    private static final char X = 'x';
    private static final char O = 'o';

    public static boolean checkHorizontalLine(char[][] table, int y) {
        char tmp = table[0][y];
        for(int x = 0; x < N; x++) {
            if(table[x][y] != tmp) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkVerticalLine(char[][] table, int x) {
        char tmp = table[x][0];
        for(int y = 0; y < N; y++) {
            if(table[x][y] != tmp) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkUpDiagLine(char[][] table, int x, int y) {
        char tmp = table[x][y];
        for(; x < N; x++, y++) {
            if(table[x][y] != tmp) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDownDiagLine(char[][] table, int x, int y) {
        char tmp = table[x][y];
        for(; x < N; x++, y--) {
            if(table[x][y] != tmp) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkTable(char[][] table) {
        for(int j = 0; j < N; j++) {
            System.out.print(checkHorizontalLine(table, j));
            System.out.print(checkVerticalLine(table, j));
        }
        System.out.print(checkUpDiagLine(table, 0, 0));
        System.out.print(checkDownDiagLine(table, 0, 2));

        return true;
    }
}
