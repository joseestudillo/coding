package com.joseestudillo.puzzles.interview;

public class InLineN {

    private static final int N = 10;
    private static final int LINE_SIZE = 4;

    /*
     * [(9,0), (9,1), (9,2), (9,3), (9,4), (9,5), (9,6), (9,7), (9,8), (9,9)]
     * [(8,0), (8,1), (8,2), (8,3), (8,4), (8,5), (8,6), (8,7), (8,8), (8,9)]
     * [(7,0), (7,1), (7,2), (7,3), (7,4), (7,5), (7,6), (7,7), (7,8), (7,9)]
     * [(6,0), (6,1), (6,2), (6,3), (6,4), (6,5), (6,6), (6,7), (6,8), (6,9)]
     * [(5,0), (5,1), (5,2), (5,3), (5,4), (5,5), (5,6), (5,7), (5,8), (5,9)]
     * [(4,0), (4,1), (4,2), (4,3), (4,4), (4,5), (4,6), (4,7), (4,8), (4,9)]
     * [(3,0), (3,1), (3,2), (3,3), (3,4), (3,5), (3,6), (3,7), (3,8), (3,9)]
     * [(2,0), (2,1), (2,2), (2,3), (2,4), (2,5), (2,6), (2,7), (2,8), (2,9)]
     * [(1,0), (1,1), (1,2), (1,3), (1,4), (1,5), (1,6), (1,7), (1,8), (1,9)]
     * [(0,0), (0,1), (0,2), (0,3), (0,4), (0,5), (0,6), (0,7), (0,8), (0,9)]
     */

    public static void main(String[] args) {
        int[][] table = new int[N][N];

    }

    private int currentValue;
    private int count;

    private void eval(int currentValue) {
        if(this.count == 0) {
            this.currentValue = currentValue;
            this.count++;
        } else {
            if(this.currentValue == currentValue) {
                this.count++;
            } else {
                this.currentValue = currentValue;
                this.count = 1;
            }

        }
    }

    private boolean isLine() {
        return this.count == N;
    }

    public void checkTableIter(int[][] table) {
        // horizontal
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                this.eval(table[x][y]);
                if(this.isLine()) {
                    return;
                }
            }
        }

        // vertical
        for(int x = 0; x < N; x++) {
            for(int y = 0; y < N; y++) {
                this.eval(table[x][y]);
                if(this.isLine()) {
                    return;
                }
            }
        }

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                // diagonal up
                for(int i = x, j = y; i < N && j < N; i++, j++) {
                    this.eval(table[i][j]);
                    if(this.isLine()) {
                        return;
                    }
                }
                // diagonal down
                for(int i = x, j = y; i < N && j > 0; i++, j--) {
                    this.eval(table[i][j]);
                    if(this.isLine()) {
                        return;
                    }
                }
            }
        }
    }

    public void checkTableRecursive(int[][] table, int x, int y) {

    }
}
