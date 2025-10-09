import java.util.*;

class Solution {
    public int solution(String s) {
        int minCompressedLength = s.length();
        
        // 가능한 모든 압축 길이를 압축하여, 최소 길이 갱신
        for (int unitSize = 1; unitSize <= s.length() / 2; unitSize++) {
            int compressedLength = calculateCompressedLength(s, unitSize);        
            minCompressedLength = Math.min(minCompressedLength, compressedLength);
        }
        
        // 최소 길이 반환
        return minCompressedLength;
    }
    
    private int calculateCompressedLength(String source, int unitSize) {
        List<String> chunks = new ArrayList<>();
        
        for (int startIndex = 0; startIndex < source.length(); startIndex += unitSize) {
            int endIndex = Math.min(startIndex + unitSize, source.length());
            chunks.add(source.substring(startIndex, endIndex));
        }
        
        if (chunks.isEmpty()) {
            return 0;
        }
        
        int totalLength = 0;
        CompressedChunk currentChunk = new CompressedChunk(chunks.get(0));

        for (int i = 1; i < chunks.size(); i++) {
            if (currentChunk.matches(chunks.get(i))) {
                currentChunk.incrementCount();
            } else {
                totalLength += currentChunk.getCompressedLength();
                currentChunk = new CompressedChunk(chunks.get(i));
            }
        }

        totalLength += currentChunk.getCompressedLength();
        return totalLength;
    }
    
    private static class CompressedChunk {
        private final String value;
        private int count;

        public CompressedChunk(String value) {
            this.value = value;
            this.count = 1;
        }

        public boolean matches(String other) {
            return this.value.equals(other);
        }

        public void incrementCount() {
            this.count++;
        }

        public int getCompressedLength() {
            int length = value.length();
            if (count > 1) {
                length += String.valueOf(count).length();
            }
            return length;
        }
    }
}
