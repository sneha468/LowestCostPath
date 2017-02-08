package com.sneha.lowestcostpath.model;

import java.util.Comparator;

public class PathComparator implements Comparator<Path> {
    private static int SORT_LEFT_FIRST = -1;
    private static int SORT_RIGHT_FIRST = 1;
    private static int SORT_EQUAL = 0;

    public PathComparator() {
    }

    public int compare(Path leftPath, Path rightPath) {
        int comparedLength = this.compareLengths(leftPath, rightPath);
        return comparedLength != 0?comparedLength:this.compareCosts(leftPath, rightPath);
    }

    private int compareLengths(Path leftPath, Path rightPath) {
        int leftLength = this.getLengthFromPath(leftPath);
        int rightLength = this.getLengthFromPath(rightPath);
        return leftLength > rightLength?SORT_LEFT_FIRST:(leftLength == rightLength?SORT_EQUAL:SORT_RIGHT_FIRST);
    }

    private int compareCosts(Path leftPath, Path rightPath) {
        int leftCost = this.getCostFromPath(leftPath);
        int rightCost = this.getCostFromPath(rightPath);
        return leftCost < rightCost?SORT_LEFT_FIRST:(leftCost == rightCost?SORT_EQUAL:SORT_RIGHT_FIRST);
    }

    private int getLengthFromPath(Path path) {
        return path != null?path.getPathLength():-2147483648;
    }

    private int getCostFromPath(Path path) {
        return path != null?path.getTotalCost():2147483647;
    }
}
