import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String initialStr = reader.readLine();
        int tokenCount = Integer.parseInt(reader.readLine());

        Editor editor = new Editor(initialStr);
        for (int i = 0; i < tokenCount; i++) {
            String[] token = reader.readLine().split(" ");

            String command = token[0];
            if (command.equals("L")) {
                editor.moveLeft();
            } else if (command.equals("D")) {
                editor.moveRight();
            } else if (command.equals("B")) {
                editor.remove();
            } else {
                editor.add(token[1].toCharArray()[0]);
            }
        }

        editor.print();
    }

    private static class Editor {

        private Node head;
        private Node rear;
        private Node current;

        public Editor(String initialStr) {
            // 헤드는 prev = null, 리어는 next = null
            head = new Node(null, null, 'h');
            rear = new Node(head, null, 'r');
            head.next = rear;

            current = head;
            for (char ch : initialStr.toCharArray()) {
                Node newNode = new Node(current, current.next, ch);
                current.next.prev = newNode;
                current.next = newNode;

                current = newNode;
            }
        }


        public void moveLeft() {
            if (current == head) {
                return;
            }

            current = current.prev;
        }

        public void moveRight() {
            if (current.next == rear) {
                return;
            }

            current = current.next;
        }

        public void remove() {
            if (current == head) {
                return;
            }

            current.prev.next = current.next;
            current.next.prev = current.prev;

            current = current.prev;
        }

        public void add(char ch) {
            Node newNode = new Node(current, current.next, ch);
            current.next.prev = newNode;
            current.next = newNode;

            current = newNode;
        }

        public void print() {
            StringBuilder sb = new StringBuilder();

            for (Node node = head.next; node != rear; node = node.next) {
                sb.append(node.element);
            }

            System.out.println(sb);
        }
    }

    private static class Node {
        Node prev;
        Node next;
        final char element;

        public Node(Node prev, Node next, char element) {
            this.prev = prev;
            this.next = next;
            this.element = element;
        }
    }
}
