package com.sneha.lowestcostpath.model;


public class PathCollector {
    private Path bestPath;
    private PathComparator comparator = new PathComparator();

    public PathCollector() {
    }

    public Path getBestPath() {
        return this.bestPath;
    }

    public void addPath(Path newPath) {
        if(this.comparator.compare(newPath, this.bestPath) < 0) {
            this.bestPath = newPath;
        }

    }
}
