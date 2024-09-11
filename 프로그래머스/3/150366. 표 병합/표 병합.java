import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

class Solution {

    public String[] solution(String[] commands) {
        List<String> result = new ArrayList<>();
        Table table = new Table();

        for (String command : commands) {
            String[] tokens = command.split(" ");
            switch (tokens[0]) {
                case "UPDATE":
                    if (tokens.length == 4) {
                        table.update(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3]);
                    } else {
                        table.update(tokens[1], tokens[2]);
                    }
                    break;
                case "MERGE":
                    table.merge(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]),
                            Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
                    break;
                case "UNMERGE":
                    table.unmerge(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                    break;
                case "PRINT":
                    result.add(table.print(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
                    break;
            }
        }

        return result.toArray(new String[0]);
    }

    private static class Table {

        private final Map<Cell, String> values = new HashMap<>();
        private final Map<Cell, Set<Cell>> mergedGroups = new HashMap<>();

        public void update(int row, int col, String newValue) {
            Queue<Cell> queue = new ArrayDeque<>();
            queue.offer(new Cell(row, col));
            Set<Cell> visited = new HashSet<>();

            while (!queue.isEmpty()) {
                Cell current = queue.poll();
                if (visited.contains(current)) {
                    continue;
                }

                visited.add(current);
                values.put(current, newValue);

                for (Cell next : mergedGroups.getOrDefault(current, Collections.emptySet())) {
                    queue.offer(next);
                }
            }
        }

        public void update(String oldValue, String newValue) {
            for (Map.Entry<Cell, String> entry : values.entrySet()) {
                if (oldValue.equals(entry.getValue())) {
                    entry.setValue(newValue);
                }
            }
        }

        public void merge(int row1, int col1, int row2, int col2) {
            Cell cell1 = new Cell(row1, col1);
            Cell cell2 = new Cell(row2, col2);
            if (cell1.equals(cell2)) {
                return;
            }

            if (values.containsKey(cell1)) {
                update(row2, col2, values.get(cell1));
            } else if (values.containsKey(cell2)) {
                update(row1, col1, values.get(cell2));
            }

            mergedGroups.computeIfAbsent(cell1, k -> new HashSet<>())
                    .add(cell2);
            mergedGroups.computeIfAbsent(cell2, k -> new HashSet<>())
                    .add(cell1);
        }

        public void unmerge(int row, int col) {
            Cell startCell = new Cell(row, col);
            String value = values.get(startCell);
            Queue<Cell> queue = new ArrayDeque<>();
            queue.offer(startCell);
            Set<Cell> visited = new HashSet<>();

            while (!queue.isEmpty()) {
                Cell current = queue.poll();
                if (visited.contains(current)) {
                    continue;
                }

                visited.add(current);
                values.remove(current);

                for (Cell next : mergedGroups.getOrDefault(current, Collections.emptySet())) {
                    queue.offer(next);
                }

                mergedGroups.remove(current);
            }

            if (value != null) {
                values.put(startCell, value);
            }
        }

        public String print(int row, int col) {
            return values.getOrDefault(new Cell(row, col), "EMPTY");
        }
    }

    private static class Cell {

        private final int row;
        private final int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Cell cell = (Cell) o;
            return row == cell.row && col == cell.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}
