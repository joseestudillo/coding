package com.joseestudillo.puzzles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Jose Estudillo
 */

/*
 * Assumptions:
 * - a fragment has length at least 2
 */
public class TextFragments {

    private static final String SEPARATOR = ";";
    private static final String CHARSET = "UTF-8";
    /** index of the position value in the overlap array */
    private static final int POS = 0;
    /** index of the size value in the overlap array */
    private static final int SIZE = 1;

    public static void main(String[] args) throws IOException {
        if(args.length < 1) {
            System.err.println("Input file path required");
            System.exit(-1);
        }
        String filePath = args[0];
        File inputFile = new File(filePath);
        if(!inputFile.exists() || !inputFile.canRead()) {
            System.err.println("Input file path required");
            System.exit(-1);
        }

        List<String> fragments = collectFragments(inputFile, CHARSET, SEPARATOR);

        int nReductions = reassemble2(fragments);

        if(fragments.size() == 1) {
            System.out.println(fragments.get(0));
        } else {
            System.err.println(String.format("Error: Could not reassemble the given fragments: %s. %d merges performed.", fragments, nReductions));
        }

    }

    /**
     * Parses a given file line by line, splitting each line using <code>tokenSeparator</code> and adding the tokens to a list
     * 
     * @param fragmentsFile input file
     * @param charset file encoding
     * @param tokenSeparator separator to split the lines
     * @return a list with all the tokens found
     * @throws IOException
     */
    private static List<String> collectFragments(File fragmentsFile, String charset, String tokenSeparator) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fragmentsFile), charset));
        String line;
        String[] lineFragments;
        List<String> fragments = new ArrayList<String>(); // LinkedList may perform better as we will need to add and remove elements when we reassemble
        while((line = br.readLine()) != null) {
            lineFragments = line.split(tokenSeparator);
            for(String fragment : lineFragments) {
                fragments.add(fragment);
            }
        }
        br.close();
        return fragments;
    }

    /**
     * Reassemble the strings in a given list
     * 
     * @param fragments Input/output list containing the text fragments
     * @return number of reductions performed
     */
    private static int reassemble0(List<String> fragments) {
        int nReductions = 0;
        while(reduce0(fragments)) {// we prevent the case where there is no solution
            nReductions++;
        }
        return nReductions;
    }

    /**
     * Reduces the number of fragments merging the two with the biggest overlapping size.
     * 
     * @param list list of fragments.
     * @return true if it could make a reduction, false otherwise.
     */
    private static boolean reduce0(List<String> list) {
        if(list.size() > 1) {
            int[] pos = new int[] { 0, 0 };
            int[] overlap0, overlap1;
            int[] maxOverlap = new int[] { 0, 0 };
            String str0, str1;
            for(int i = 0; i < list.size(); i++) {
                for(int j = i + 1; j < list.size(); j++) {
                    str0 = list.get(i);
                    str1 = list.get(j);
                    overlap0 = getOverlapInfo(str0, str1);
                    overlap1 = getOverlapInfo(str1, str0);
                    if(overlap0[SIZE] >= overlap1[SIZE] && overlap0[SIZE] > maxOverlap[SIZE]) {
                        pos[0] = i;
                        pos[1] = j;
                        maxOverlap = overlap0;
                    } else if(overlap1[SIZE] > maxOverlap[SIZE]) {
                        pos[0] = j;
                        pos[1] = i;
                        maxOverlap = overlap1;
                    }
                }
            }
            if(maxOverlap[SIZE] > 0) {
                if(pos[0] > pos[1]) {
                    str0 = list.remove(pos[0]);
                    str1 = list.remove(pos[1]);
                } else {
                    str1 = list.remove(pos[1]);
                    str0 = list.remove(pos[0]);
                }
                String merged = merge(str0, str1, maxOverlap[POS]);
                list.add(merged);
                return true;
            }
        }
        return false;
    }

    /**
     * Check if two given strings overlaps generating the overlapping information. Note that overlapping is not a commutative operation.
     * 
     * @param str0
     * @param str1
     * @return an array a, that contains a[0] = position where the overlapping starts, a[1]= Size of the overlapped area
     */
    private static int[] getOverlapInfo(String str0, String str1) {
        int index = str0.indexOf(str1);
        if(index != -1) {
            return new int[] { index, str1.length() };
        } else {
            String tmp;
            for(int i = 0; i < str0.length() - 1; i++) {
                tmp = str0.substring(i);
                if(str1.startsWith(tmp)) {
                    return new int[] { i, tmp.length() };
                }
            }
        }
        return new int[] { 0, 0 };
    }

    /**
     * Merges two strings where the first String <code>str1</code> is partially or completely overlapped by <code>str2</code> and <code>mergeIndex</code> is the
     * index of the first overlapped character.
     * 
     * @param str0 overlapped string
     * @param str1
     * @param mergeIndex index of the first overlapped character
     * @return
     */
    private static String merge(String str0, String str1, int mergeIndex) {
        if(str0.length() > mergeIndex + str1.length()) {
            return str0;
        } else {
            return str0.substring(0, mergeIndex) + str1;
        }
    }

    /*
     * Alternative version using objects instead of arrays
     * 
     * I left both in place to show the way of thinking. In terms of CLI app the behaviour is the same.
     * 
     * In this new version the code is cleaner and we can have a global concept of null for overlaps. With arrays this is not possible because there is not
     * unmodifiable arrays in java.
     */
    private static class Overlap {

        public static final Overlap NULL = new Overlap(0, 0);
        private final int overlapingIndex;
        private final int size;

        public static Overlap newInstance(int overlapingIndex, int size) {
            if(overlapingIndex == 0 && size == 0) {
                return NULL;
            } else {
                return new Overlap(overlapingIndex, size);
            }
        }

        private Overlap(int overlapingIndex, int size) {
            this.overlapingIndex = overlapingIndex;
            this.size = size;
        }

        public int getOverlapingIndex() {
            return this.overlapingIndex;
        }

        public int getSize() {
            return this.size;
        }

        public static int compareSize(Overlap o0, Overlap o1) {
            return Integer.compare(o0.size, o1.size);
        }

        public String merge(String str0, String str1) {
            return merge(str0, str1, this);
        }

        /**
         * Check if two given strings overlaps generating the overlapping information. Note that overlapping is not a commutative operation.
         * 
         * @param str0
         * @param str1
         * @return an {@link Overlap} object with the overlapping information.
         */
        public static Overlap generateOverlap(String str0, String str1) {
            int index = str0.indexOf(str1);
            if(index != -1) {
                return newInstance(index, str1.length());
            } else {
                String tmp;
                for(int i = 0; i < str0.length() - 1; i++) {
                    tmp = str0.substring(i);
                    if(str1.startsWith(tmp)) {
                        return newInstance(i, tmp.length());
                    }
                }
            }
            return NULL;
        }

        public static Overlap[] generateOverlaps(String str0, String str1) {
            return new Overlap[] { generateOverlap(str0, str1), generateOverlap(str1, str0) };
        }

        /**
         * Merges two strings that where the first one is overlapped by the second one.
         * 
         * @param str0 string partially or completely overlapped by <code>str1</code>
         * @param str1 string that overlaps <code>str0</code>
         * @param overlapInfo information about how the strings overlap
         * @return
         */
        private static String merge(String str0, String str1, Overlap overlapInfo) {
            if(str0.length() > overlapInfo.getOverlapingIndex() + str1.length()) {
                return str0;
            } else {
                return str0.substring(0, overlapInfo.getOverlapingIndex()) + str1;
            }
        }
    }

    private static boolean reduce1(List<String> list) {
        if(list.size() > 1) {
            int pos0 = 0;
            int pos1 = 0;
            Overlap maxOverlap = Overlap.NULL;
            Overlap[] overlaps;
            for(int i = 0; i < list.size(); i++) {
                for(int j = i + 1; j < list.size(); j++) {
                    overlaps = Overlap.generateOverlaps(list.get(i), list.get(j));
                    if(Overlap.compareSize(overlaps[0], overlaps[1]) >= 0 && Overlap.compareSize(overlaps[0], maxOverlap) > 0) {
                        maxOverlap = overlaps[0];
                        pos0 = i;
                        pos1 = j;
                    } else if(Overlap.compareSize(overlaps[1], maxOverlap) > 0) {
                        maxOverlap = overlaps[1];
                        pos0 = j;
                        pos1 = i;
                    }
                }
            }
            if(maxOverlap != Overlap.NULL) {
                String str0;
                String str1;
                if(pos0 > pos1) {
                    str0 = list.remove(pos0);
                    str1 = list.remove(pos1);
                } else {
                    str1 = list.remove(pos1);
                    str0 = list.remove(pos0);
                }
                list.add(Overlap.merge(str0, str1, maxOverlap));
                return true;
            }
        }
        return false;
    }

    private static int reassemble1(List<String> fragments) {
        int nReductions = 0;
        while(reduce1(fragments)) { // we prevent the case where there is no solution
            nReductions++;
        }
        return nReductions;
    }

    /* Another implementation of overlap easier to use and with better performance */

    public static class OverlapInfo {

        public static final OverlapInfo NULL = new OverlapInfo(0, 0);
        private final int index;
        private final int size;

        public static OverlapInfo newInstance(int index, int size) {
            if(size == 0) {
                return NULL;
            } else {
                return new OverlapInfo(index, size);
            }
        }

        private OverlapInfo(int index, int size) {
            this.index = index;
            this.size = size;
        }

        public int getIndex() {
            return this.index;
        }

        public int getSize() {
            return this.size;
        }

        @Override
        public String toString() {
            return "OverlapInfo [index=" + this.index + ", size=" + this.size + "]";
        }

    }

    public static OverlapInfo overlap(String s0, String s1) {
        char[] chars0 = s0.toCharArray();
        char[] chars1 = s1.toCharArray();
        for(int i = 0; i < chars0.length; i++) {
            boolean ok = true;
            int j, k;
            for(j = i, k = 0; j < chars0.length && k < chars1.length && ok; j++, k++) {
                ok &= chars0[j] == chars1[k];
            }
            if(ok) {
                return OverlapInfo.newInstance(j - k, k);
            }
        }
        return OverlapInfo.NULL;
    }

    public static String merge(String s0, String s1, OverlapInfo info) {

        if(s0.length() >= info.getIndex() + s1.length()) {
            return s0; // s1 is contained in s0
        } else {
            return s0.substring(0, info.getIndex()) + s1;
        }
    }

    public static class OverlapResult {

        private final static OverlapResult NULL = new OverlapResult("", 0);

        private final String str;
        private final int size;

        public static OverlapResult newInstance(String str, int size) {
            if(size == 0) {
                return NULL;
            } else {
                return new OverlapResult(str, size);
            }
        }

        private OverlapResult(String str, int size) {
            this.str = str;
            this.size = size;
        }

        public String getStr() {
            return this.str;
        }

        public int getSize() {
            return this.size;
        }

        @Override
        public String toString() {
            return "OverlapResult [str=" + this.str + ", size=" + this.size + "]";
        }
    }

    public static OverlapResult overlapResult(String s0, String s1) {
        OverlapInfo o0 = overlap(s0, s1);
        OverlapInfo o1 = overlap(s1, s0);
        if(o0.getSize() > o1.getSize()) {
            return OverlapResult.newInstance(merge(s0, s1, o0), o0.getIndex());
        } else if(o1.getSize() > 0) {
            return OverlapResult.newInstance(merge(s1, s0, o1), o1.getIndex());
        } else {
            return OverlapResult.NULL;
        }
    }

    private static boolean reduce2(List<String> list) {
        if(list.size() > 1) {
            int pos0 = 0;
            int pos1 = 0;
            OverlapResult maxOverlap = OverlapResult.NULL;
            for(int i = 0; i < list.size(); i++) {
                for(int j = i + 1; j < list.size(); j++) {
                    OverlapResult tmpOverlap = overlapResult(list.get(i), list.get(j));
                    if(tmpOverlap.getSize() > maxOverlap.getSize()) {
                        pos0 = i;
                        pos0 = j;
                    }
                }
            }
            if(maxOverlap != OverlapResult.NULL) {
                if(pos0 > pos1) {
                    list.remove(pos0);
                    list.remove(pos1);
                } else {
                    list.remove(pos1);
                    list.remove(pos0);
                }
                list.add(maxOverlap.getStr());
                return true;
            }
        }
        return false;
    }

    private static int reassemble2(List<String> fragments) {
        int nReductions = 0;
        while(reduce2(fragments)) { // we prevent the case where there is no solution
            nReductions++;
        }
        return nReductions;
    }
}
