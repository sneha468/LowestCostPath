package com.sneha.lowestcostpath.model;


import java.util.Iterator;
import java.util.List;


public class RowSearch {
    private int row;
    private Grid grid;
    private PathCollector pathCollector;

    public RowSearch(int startRow, Grid grid, PathCollector collector) {
        if(grid == null) {
            throw new IllegalArgumentException("A grid is required");
        } else if(collector == null) {
            throw new IllegalArgumentException("A visitor requires a collector");
        } else if(startRow > 0 && startRow <= grid.getRowCount()) {
            this.row = startRow;
            this.grid = grid;
            this.pathCollector = collector;
        } else {
            throw new IllegalArgumentException("Cannot visit a row outside of grid boundaries");
        }
    }

    public Path getBestPathForRow() {
        Path initialPath = new Path(this.grid.getColumnCount());
        this.visitPathsForRow(this.row, initialPath);
        return this.pathCollector.getBestPath();
    }

    private void visitPathsForRow(int row, Path path) {
        if(this.canVisitRowOnPath(row, path)) {
            this.visitRowOnPath(row, path);
        }

        List adjacentRows = this.grid.getRowsAdjacentTo(row);
        boolean currentPathAdded = false;
        Iterator var5 = adjacentRows.iterator();

        while(var5.hasNext()) {
            int adjacentRow = ((Integer)var5.next()).intValue();
            if(this.canVisitRowOnPath(adjacentRow, path)) {
                Path pathCopy = new Path(path);
                this.visitPathsForRow(adjacentRow, pathCopy);
            } else if(!currentPathAdded) {
                this.pathCollector.addPath(path);
                currentPathAdded = true;
            }
        }

    }

    private boolean canVisitRowOnPath(int row, Path path) {
        return !path.isComplete() && !this.nextVisitOnPathWouldExceedMaximumCost(row, path);
    }

    private void visitRowOnPath(int row, Path path) {
        int columnToVisit = path.getPathLength() + 1;
        path.addRowWithCost(row, this.grid.getValueForRowAndColumn(row, columnToVisit));
    }

    private boolean nextVisitOnPathWouldExceedMaximumCost(int row, Path path) {
        int nextColumn = path.getPathLength() + 1;
        return path.getTotalCost() + this.grid.getValueForRowAndColumn(row, nextColumn) > Path.MAXIMUM_COST;
    }
}
