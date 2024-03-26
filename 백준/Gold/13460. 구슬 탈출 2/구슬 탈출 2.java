import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;

class Main {

    private static final int[][] DIRECTIONS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    } ;

    /*
    1. 맵을 만든다.
    2. 최소 이동횟수를 센다.
    - 같은 상태는 중복되면 안된다. 4중 배열(빨간 구슬, 파란 구슬)로 방문 상태를 확인한다.
    - 4방향을 움직이면 다음 상태를 만든다.(한 방향으로 갈 수 있는 최대로 간다.)
    - 구슬끼리는 겹칠 수 없다.
     */
    public static void main(String[] args) {
        char[][] map = createMap();
        Beads beads = extractBeads(map);
        Hole hole = extractHole(map);

        int result = countMinTurns(map, beads, hole);

        System.out.println(result);
    }

    private static char[][] createMap() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int[] tokens = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int rows = tokens[0];

            char[][] map = new char[rows][];
            for (int row = 0; row < map.length; row++) {
                map[row] = reader.readLine().toCharArray();
            }

            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Beads extractBeads(char[][] map) {
        Bead red = extractBead(map, 'R');
        Bead blue = extractBead(map, 'B');
        return new Beads(red, blue);
    }

    private static Hole extractHole(char[][] map) {
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (map[r][c] == 'O') {
                    map[r][c] = '.';
                    return new Hole(new int[]{r, c});
                }
            }
        }
        return null;
    }

    private static Bead extractBead(char[][] map, char ch) {
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (map[r][c] == ch) {
                    map[r][c] = '.';
                    return new Bead(new int[]{r, c});
                }
            }
        }
        return null;
    }

    private static int countMinTurns(char[][] map, Beads startBeads, Hole hole) {
        boolean[][][][] visited = new boolean[map.length][map[0].length][map.length][map[0].length];

        Queue<QueueElement> queue = new ArrayDeque<>();
        queue.offer(new QueueElement(startBeads, 0));

        /*
        1. 유효한 위치인지 검사한다.
        2. 방문한 적 있다면 진행하지 않는다.
        3. 빨간 구슬만 구멍에 들어갔다면 turns, 파란 구슬이 구멍에 들어갔다면 -1 반환한다.
        4. 구멍에 들어가지 않았다면 다음 진행값(4방향)을 큐에 넣는다.
         */
        while (!queue.isEmpty()) {
            QueueElement element = queue.poll();
            Beads beads = element.beads;
            Bead red = beads.red;
            Bead blue = beads.blue;

            if (element.turns > 10) {
                break;
            }
            if (visited[red.coordinate[0]][red.coordinate[1]][blue.coordinate[0]][blue.coordinate[1]]) {
                continue;
            }
            visited[red.coordinate[0]][red.coordinate[1]][blue.coordinate[0]][blue.coordinate[1]] = true;

            if (isInHole(blue, hole)) {
                continue;
            } else if (isInHole(red, hole)) {
                return element.turns;
            }

            int nextTurns = element.turns + 1;
            for (int[] direction : DIRECTIONS) {
                Beads nextBead = nextBead(map, hole, beads, direction);

                queue.offer(new QueueElement(nextBead, nextTurns));
            }
        }

        return -1;
    }

    private static boolean isWithinRange(char[][] map, Bead bead) {
        int[] coordinate = bead.coordinate;
        if (coordinate[0] <= 0 || map.length - 1 <= coordinate[0]) {
            return false;
        }
        return 0 < coordinate[1] && coordinate[1] < map[coordinate[0]].length - 1;
    }

    private static boolean isInHole(Bead bead, Hole hole) {
        return Arrays.equals(bead.coordinate, hole.coordinate);
    }

    private static Beads nextBead(char[][] map, Hole hole, Beads beads, int[] direction) {
        Bead prevRed = beads.red;
        Bead nextRed = new Bead(new int[]{prevRed.coordinate[0] + direction[0], prevRed.coordinate[1] + direction[1]});

        while (isWithinRange(map, nextRed) && !isWall(map, nextRed) && !isInHole(prevRed, hole)) {
            if (Arrays.equals(nextRed.coordinate, beads.blue.coordinate) && !isInHole(nextRed, hole)) {
                break;
            }

            prevRed = nextRed;
            nextRed = new Bead(new int[]{prevRed.coordinate[0] + direction[0], prevRed.coordinate[1] + direction[1]});
        }

        Bead prevBlue = beads.blue;
        Bead nextBlue = new Bead(new int[]{prevBlue.coordinate[0] + direction[0], prevBlue.coordinate[1] + direction[1]});

        while (isWithinRange(map, nextBlue) && !isWall(map, nextBlue) && !isInHole(prevBlue, hole)) {
            if (Arrays.equals(nextBlue.coordinate, prevRed.coordinate) && !isInHole(nextBlue, hole)) {
                break;
            }

            prevBlue = nextBlue;
            nextBlue = new Bead(new int[]{prevBlue.coordinate[0] + direction[0], prevBlue.coordinate[1] + direction[1]});
        }

        while (isWithinRange(map, nextRed) && !isWall(map, nextRed) && !isInHole(prevRed, hole)) {
            if (Arrays.equals(nextRed.coordinate, prevBlue.coordinate) && !isInHole(nextRed, hole)) {
                break;
            }

            prevRed = nextRed;
            nextRed = new Bead(new int[]{prevRed.coordinate[0] + direction[0], prevRed.coordinate[1] + direction[1]});
        }

        return new Beads(prevRed, prevBlue);
    }

    private static boolean isWall(char[][] map, Bead bead) {
        int[] coordinate = bead.coordinate;
        return map[coordinate[0]][coordinate[1]] == '#';
    }

    private static class Bead {
        final int[] coordinate;

        public Bead(int[] coordinate) {
            this.coordinate = coordinate;
        }
    }

    private static class Hole {
        final int[] coordinate;

        public Hole(int[] coordinate) {
            this.coordinate = coordinate;
        }
    }

    private static class Beads {
        final Bead red;
        final Bead blue;

        public Beads(Bead red, Bead blue) {
            this.red = red;
            this.blue = blue;
        }
    }

    private static class QueueElement {
        final Beads beads;
        final int turns;

        public QueueElement(Beads beads, int turns) {
            this.beads = beads;
            this.turns = turns;
        }
    }
}
