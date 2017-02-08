package com.sneha.lowestcostpath.model;

import java.util.ArrayList;
import java.util.Collections;

public class GridSearch {

    private Grid grid;
    private PathComparator pathComparator;

    public GridSearch(Grid grid) {
        if(grid == null) {
            throw new IllegalArgumentException("A grid is required");
        } else {
            this.grid = grid;
            this.pathComparator = new PathComparator();
        }
    }

    public Path getBestPathForGrid() {
        ArrayList allPaths = new ArrayList();

        for(int row = 1; row <= this.grid.getRowCount(); ++row) {
            RowSearch rowSearch = new RowSearch(row, this.grid, new PathCollector());
            allPaths.add(rowSearch.getBestPathForRow());
        }

        Collections.sort(allPaths, this.pathComparator);
        return (Path)allPaths.get(0);
    }
}
