import java.util.*;
import java.util.regex.*;

class Solution {
    private static final Pattern P = Pattern.compile("^(\\D+?)(\\d{1,5})(.*)$");
    
    public String[] solution(String[] files) {
        File[] parsed = Arrays.stream(files)
            .map(File::new)
            .toArray(File[]::new);
        
        Arrays.sort(parsed, (a, b) -> {
            int h = a.header.compareToIgnoreCase(b.header);
            return h != 0 ? h : Integer.compare(a.number, b.number);
        });
        
        return Arrays.stream(parsed)
            .map(f -> f.original)
            .toArray(String[]::new);
    }
    
    class File {
        String original, header;
        int number;
        
        File(String name) {
            original = name;
            Matcher m = P.matcher(name);
            m.find();
            header = m.group(1);
            number = Integer.parseInt(m.group(2));
        }
    }
}
