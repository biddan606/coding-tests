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
        String currentChunk = chunks.get(0);
        int repeatCount = 1;
        
        for (int i = 1; i < chunks.size(); i++) {
            if (currentChunk.equals(chunks.get(i))) {
                repeatCount++;
            } else {
                totalLength += getCompressedChunkLength(currentChunk, repeatCount);
                currentChunk = chunks.get(i);
                repeatCount = 1;
            }
        }
        
        totalLength += getCompressedChunkLength(currentChunk, repeatCount);
        return totalLength;
    }
    
    private int getCompressedChunkLength(String chunk, int repeatCount) {
        int length = chunk.length();
        if (repeatCount > 1) {
            length += String.valueOf(repeatCount).length();
        }
        return length;
    }
}
