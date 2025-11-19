import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = reader.readLine();

        // 크로아티아 알파벳 패턴 목록
        String[] croatianAlphabets = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        // 패턴과 일치하는 문자열을 길이 1짜리 문자("*")로 치환
        for (String pattern : croatianAlphabets) {
            str = str.replace(pattern, "*");
        }

        writer.write(String.valueOf(str.length()));
        
        reader.close();
        writer.flush();
        writer.close();
    }
}