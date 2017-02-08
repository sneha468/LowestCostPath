package com.sneha.lowestcostpath.Utils;

public class Util {

    public static int[][] convertFromStringToGridArray(String input) {
        if (input != null) {
            String[] rows = input.split("\n");
            String[] firstColumns = rows[0].split("\\s+");
            int[][] output = new int[rows.length][firstColumns.length];

            try {
                for (int row = 0; row < rows.length; row++) {
                    String[] columns = rows[row].split("\\s+");
                    for (int column = 0; column < columns.length; column++) {
                        if (column < output[0].length) {
                            output[row][column] = Integer.valueOf(columns[column]);
                        }
                    }
                }

                return output;
            } catch (NumberFormatException nfe) {
                return new int[0][0];
            }
        } else {
            return new int[0][0];
        }
    }

}
