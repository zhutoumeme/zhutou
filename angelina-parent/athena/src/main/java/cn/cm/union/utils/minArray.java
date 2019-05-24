package cn.cm.union.utils;

import java.util.Arrays;

public class minArray {

  public static void getMinArray() {
    int[][] arrays = {{237, 26, 74, 175}, {154, 45, 3, 765}, {893, 21, 4, 1}};
    int[] minArray = new int[arrays[0].length];
    for (int i = 0, j = minArray.length; i < j; i++) {
      int minNum = arrays[0][i];
      for (int k = 1, m = arrays.length; k < m; k++) {
        if (minNum > arrays[k][i]) {
          minNum = arrays[k][i];
        }
      }
      minArray[i] = minNum;
    }
    System.out.println(Arrays.toString(minArray));
  }

  public static void main(String[] args) {
    getMinArray();
  }

}
