import java.util.ArrayList;
import java.util.List;

class Solution {

    public int solution(String source) {
        int minCompressedLength = source.length();

        for (int compressionLength = 1; compressionLength <= source.length(); compressionLength++) {
            List<String> tokens = split(source, compressionLength);
            int compressedLength = getCompressedLength(tokens);

            minCompressedLength = Math.min(compressedLength, minCompressedLength);
        }

        return minCompressedLength;
    }

    private int getCompressedLength(List<String> tokens) {

        StringBuilder builder = new StringBuilder();
        String lastToken = "";
        int count = 1;

        for (String token : tokens) {
            if (lastToken.equals(token)) {
                count++;
                continue;
            }

            if (count != 1) {
                builder.append(count);
            }
            builder.append(lastToken);

            lastToken = token;
            count = 1;
        }

        if (count != 1) {
            builder.append(count);
        }
        builder.append(lastToken);

        return builder.length();
    }

    private List<String> split(String str, int compressionLength) {
        List<String> tokens = new ArrayList<>();

        for (int startIndex = 0; startIndex < str.length(); startIndex += compressionLength) {
            int endIndex = Math.min(str.length(), startIndex + compressionLength);

            String token = str.substring(startIndex, endIndex);

            tokens.add(token);
        }

        return tokens;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int result = solution.solution("xababcdcdababcdcd");

        System.out.println(result);
    }
}
