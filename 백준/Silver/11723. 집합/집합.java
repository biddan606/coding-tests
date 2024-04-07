import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int commandCount = Integer.parseInt(reader.readLine());
        MyList myList = new MyList(writer);

        for (int i = 0; i < commandCount; i++) {
            String[] tokens = reader.readLine().split(" ");
            String command = tokens[0];

            switch (command) {
                case "add": {
                    int number = parseNumber(tokens[1]);
                    myList.add(number);
                    break;
                }
                case "remove": {
                    int number = parseNumber(tokens[1]);
                    myList.remove(number);
                    break;
                }
                case "check": {
                    int number = parseNumber(tokens[1]);
                    myList.check(number);
                    break;
                }
                case "toggle": {
                    int number = parseNumber(tokens[1]);
                    myList.toggle(number);
                    break;
                }
                case "all":
                    myList.all();
                    break;
                case "empty":
                    myList.empty();
                    break;
                default:
                    throw new IllegalArgumentException("잘못된 명령어입니다.");
            }
        }

        myList.print();

        reader.close();
        myList.destroy();
    }

    private static int parseNumber(String str) {
        int result = 0;

        for (char c : str.toCharArray()) {
            result *= 10;
            result += c - '0';
        }

        return result;
    }

    private static class MyList {
        private int mask;
        private final BufferedWriter writer;

        public MyList(BufferedWriter writer) {
            this.writer = writer;
        }

        public void add(int number) {
            mask |= 1 << number;
        }

        public void remove(int number) {
            mask &= ~(1 << number);
        }

        public void check(int number) throws IOException {
            int result = 1;
            if ((mask & (1 << number)) == 0) {
                result = 0;
            }

            writer.write('0' + result);
            writer.write('\n');
        }

        public void toggle(int number) {
            mask ^= 1 << number;
        }

        public void all() {
            mask = 0b1111_1111_1111_1111_1111_0;
        }

        public void empty() {
            mask = 0;
        }

        public void print() throws IOException {
            writer.flush();
        }

        public void destroy() throws IOException {
            writer.close();
        }
    }
}
