import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    static List<Integer> dwarves = null;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] dwarfCandidates = new int[9];
        for (int i = 0; i < 9; i++) {
            dwarfCandidates[i] = Integer.parseInt(reader.readLine());
        }
        reader.close();

        dfs(dwarfCandidates, 0, new ArrayList<>());

        dwarves.sort(Comparator.comparingInt(a -> a));
        printDwarves();
    }

    private static void dfs(int[] dwarfCandidates, int index, List<Integer> current) {
        if (dwarves != null) {
            return;
        }

        if (dwarfCandidates.length == index) {
            if (current.size() == 7 && current.stream().mapToInt(Integer::intValue).sum() == 100) {
                dwarves = new ArrayList<>(current);
            }

            return;
        }

        current.add(dwarfCandidates[index]);
        dfs(dwarfCandidates, index + 1, current);
        current.remove(current.size() - 1);

        dfs(dwarfCandidates, index + 1, current);
    }

    private static void printDwarves() {
        for (Integer dwarf : dwarves) {
            System.out.println(dwarf);
        }
    }
}
