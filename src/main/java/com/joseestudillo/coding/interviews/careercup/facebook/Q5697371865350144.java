package com.joseestudillo.coding.interviews.careercup.facebook;

import java.util.Arrays;

/**
 * Created on 07/09/2017.
 *
 * @author Jose Estudillo
 */

/*

If you are given 2 infinitely large integers in the form of strings, given the length of the string, find the product of the two integers.

 */
public class Q5697371865350144 {

	//Precondition: String are properly formatted numbers
	private static class Res {
		private final char n;
		private final char c;

		public Res(char c, char n) {
			this.n = n;
			this.c = c;
		}
	}

	private static final char NULL = 'n';

	private static char[] multiply(char[] cs0, char[] cs1) {
		char[] res = new char[cs0.length + cs1.length];
		Arrays.fill(res, '0');
		int shift = 0;
		for (int i = cs0.length - 1; i >= 0; i--) {
			char[] mul = multiply(cs0[i], cs1);
			add(res, mul, shift);
			shift++;
		}
		return res;
	}

	private static void add(char[] res, char[] toAdd, int shift) {
		int sizeDiff = res.length - toAdd.length;
		char carried = '0';
		int i = toAdd.length - 1;
		for (; i >= 0; i--) {
			int resPos = i + sizeDiff - shift;
			Res sum = add(res[resPos], toAdd[i], carried);
			res[resPos] = sum.n;
			carried = sum.c;
		}

		//TODO carry the carried
		while(carried != '0') {
			int resPos = i + sizeDiff - shift;
			Res sum = add(res[resPos], toAdd[i], carried);
			res[resPos] = sum.n;
			carried = sum.c;
			i--;
		}
	}

	private static char[] multiply(char c, char[] cs) {
		char[] res = new char[1 + cs.length];
		char carried = '0';
		for (int i = cs.length - 1; i >= 0; i--) {
			Res mul = multiply(c, cs[i]);
			Res sum = add(carried, mul.n);
			res[i + 1] = sum.n;
			carried = add(mul.c, sum.c).n;
		}
		res[0] = carried;
		return res;
	}


	private static Res add(char c0, char c1) {
		int i0 = (c0 == NULL) ? 0 : Integer.parseInt(Character.toString(c0));
		int i1 = (c1 == NULL) ? 0 : Integer.parseInt(Character.toString(c1));
		int res = i0 + i1;
		return new Res(
				Integer.toString(res / 10).charAt(0),
				Integer.toString(res % 10).charAt(0));
	}

	private static Res add(char c0, char c1, char c2) {
		int i0 = (c0 == NULL) ? 0 : Integer.parseInt(Character.toString(c0));
		int i1 = (c1 == NULL) ? 0 : Integer.parseInt(Character.toString(c1));
		int i2 = (c2 == NULL) ? 0 : Integer.parseInt(Character.toString(c2));
		int res = i0 + i1 + i2;
		return new Res(
				Integer.toString(res / 10).charAt(0),
				Integer.toString(res % 10).charAt(0));
	}

	private static Res multiply(char c0, char c1) {
		int i0 = (c0 == NULL) ? 1 : Integer.parseInt(Character.toString(c0));
		int i1 = (c1 == NULL) ? 1 : Integer.parseInt(Character.toString(c1));
		int res = i0 * i1;
		return new Res(
				Integer.toString(res / 10).charAt(0),
				Integer.toString(res % 10).charAt(0));
	}

	private static void printM(char[] cs0, char[] cs1) {
		System.out.println(
				String.format("%s x %s = %s",
						Arrays.toString(cs0),
						Arrays.toString(cs1),
						Arrays.toString(multiply(cs0, cs1))));
		System.out.println(
				String.format("%s x %s = %s",
						Arrays.toString(cs1),
						Arrays.toString(cs0),
						Arrays.toString(multiply(cs1, cs0))));
	}

	public static void main(String[] args) {
		char[] nNull = new char[]{};
		char[] n0 = new char[]{'0'};
		char[] n9 = new char[]{'9'};
		char[] n99 = new char[]{'9', '9'};

		printM(nNull, nNull);
		printM(nNull, n0);
		printM(nNull, n9);
		printM(nNull, n99);

		printM(n0, n0);
		printM(n0, n9);
		printM(n0, n99);

		printM(n9, n9);
		printM(n9, n99);

		printM(n99, n99);


	}

/*
	323
	456
	---

     1|1+6|1+2|8
 1|1+5|1+0|  5|0

    99
     9
 8|8+1|1

 49
  7
 --
26
083
---
343
*/
}
