package com.joseestudillo.coding.interviews.ctci;

/**
 * 1.7
 * Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
 * bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
 */

public class IQ_1_7 {

    //we assume rotate right

    /* x,y

    [0, 1][1, 1]     [0, 0][0, 1]
    [0, 0][1, 0] ==> [1, 0][1, 1]

    [0, 2][1, 2][2, 2]     [0, 0][0, 1][0, 2]
    [0, 1][1, 1][2, 1]     [1, 0][1, 1][1, 2]
    [0, 0][1, 0][2, 0] ==> [2, 0][2, 1][2, 2]

     */


    /**
     * prints from top to bottom and from left to right, so it makes sense visually.
     *
     * @param matrix
     */
    public static void print(char[][] matrix) {
        for (int y = matrix.length - 1; y >= 0; y--) {
            for (int x = 0; x < matrix.length; x++) {
                System.out.print(String.format("[%s, %s] %s ", x, y, matrix[x][y]));
            }
            System.out.println();
        }
    }


    /**
     * Solution with extra NxN array and iterating by row
     *
     * @param matrix
     * @return
     */
    public static char[][] rotateByRow(char[][] matrix) {
        //validate nxn
        char[][] newMatrix = new char[matrix.length][matrix.length];
        for (int x = matrix.length - 1; x >= 0; x--) {
            char[] col = matrix[x];
            for (int y = 0; y < col.length; y++) {
                newMatrix[y][matrix.length - 1 - x] = col[y];
            }
        }
        return newMatrix;
    }

    /**
     * Solution with extra NxN array and assigning element by element.
     *
     * @param matrix
     * @return
     */
    public static char[][] rotateByElement(char[][] matrix) {
        //validate nxn
        char[][] newMatrix = new char[matrix.length][matrix.length];
        for (int x = 0; x < matrix.length; x++) {
            int yIdx = matrix.length - 1 - x;
            for (int y = 0; y < matrix.length; y++) {
                newMatrix[y][yIdx] = matrix[x][y];
            }
        }
        return newMatrix;
    }

    /**
     * Rotates a layer in a matrix.
     *
     * @param matrix
     * @param layerSize
     * @param x
     * @param y
     */
    private static void rotateLayer(char[][] matrix, int layerSize, int x, int y) {
        int delta = layerSize - 1;
        for (int i = 0; i < delta; i++) { //we always need to do layerSize - 1 rotations
            char tmp = matrix[x + i][y];
            matrix[x + i][y] = matrix[x + delta][y + i];
            matrix[x + delta][y + i] = matrix[x + delta - i][y + delta];
            matrix[x + delta - i][y + delta] = matrix[x][y + delta - i];
            matrix[x][y + delta - i] = tmp;

        }
    }

    public static char[][] rotateWithoutNewMatrix(char[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length) return matrix;
        int n = matrix.length;
        for (int layerSize = n, i = 0; layerSize > 1; layerSize -= 2, i++) {
            rotateLayer(matrix, layerSize, i, i);
        }
        return matrix;
    }


    static char[][] rotateSolutionFromCTCI(char[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length) return matrix;
        int n = matrix.length;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                char top = matrix[first][i];
                matrix[first][i] = matrix[last - offset][first];
                matrix[last - offset][first] = matrix[last][last - offset];
                matrix[last][last - offset] = matrix[i][last];
                matrix[i][last] = top;

            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        char[][] charMatrix1 = {
                {'a'}
        };

        char[][] charMatrix2 = {
                {'a', 'b'},
                {'c', 'd'}
        };
        char[][] charMatrix3 = {
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'}
        };
        char[][] charMatrix4 = {
                {'a', 'b', 'c', 'd'},
                {'e', 'f', 'g', 'h'},
                {'i', 'j', 'k', 'l'},
                {'m', 'n', 'o', 'p'}
        };

        print(charMatrix1);
        System.out.println();
        print(rotateWithoutNewMatrix(charMatrix1));
        System.out.println();
        print(charMatrix2);
        System.out.println();
        print(rotateWithoutNewMatrix(charMatrix2));
        System.out.println();
        print(charMatrix3);
        System.out.println();
        print(rotateWithoutNewMatrix(charMatrix3));
        System.out.println();
        print(charMatrix4);
        System.out.println();
        print(rotateWithoutNewMatrix(charMatrix4));
        System.out.println();
    }


}

