import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        reader.close();

        int numberLimit = Integer.parseInt(tokenizer.nextToken());
        int size = Integer.parseInt(tokenizer.nextToken());

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        boolean[] visited = new boolean[numberLimit];
        ArrayList<Integer> sequence = new ArrayList<>();

        dfs(visited, 0, size, sequence, writer);

        writer.flush();
        writer.close();
    }

    private static void dfs(boolean[] visited, int depth, int size, List<Integer> sequence, BufferedWriter writer) throws IOException {
        if (depth == size) {
            writer.write(sequence.stream().map(String::valueOf).collect(Collectors.joining(" ")));
            writer.newLine();
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;


            int n = i + 1;
            sequence.add(n);

            dfs(visited, depth + 1, size, sequence, writer);

            visited[i] = false;
            sequence.remove(sequence.size() - 1);
        }
    }
}
