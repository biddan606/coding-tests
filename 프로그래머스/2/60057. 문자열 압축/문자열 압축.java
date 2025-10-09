import java.util.*;

class Solution {
    public int solution(String s) {
        int minCompressedLength = s.length();
        
        // 가능한 모든 압축 길이를 압축하여, 최소 길이 갱신
        for (int compressUnit = 1; compressUnit <= s.length() / 2; compressUnit++) {
            int compressedLength = compress(s, compressUnit);
            
            minCompressedLength = Math.min(minCompressedLength, compressedLength);
        }
        
        // 최소 길이 반환
        return minCompressedLength;
    }
    
    private int compress(String str, int compressUnit) {
        int compressedLength = 0;
        
        // string을 unit 길이로 나눈다.
        List<String> splited = new ArrayList<>();
        
        for (int startLengthInclusive = 0; startLengthInclusive < str.length(); startLengthInclusive += compressUnit) {
            int endLengthExclusive = Math.min(startLengthInclusive + compressUnit, str.length());
            
            String substr = str.substring(startLengthInclusive, endLengthExclusive);
            splited.add(substr);
        }
        
        // 나눠진 string들을 순차적으로 통합한다.
        // 현재 문자열과 다음 문자열이 같다면, 통합할 수 있다.
        
        // 통합되지 못하면, 현재 string과 count를 기록한다.
        // 통합하면, count++
        String currentStr = "";
        int count = 0;
        
        for (String nextStr : splited) {
            if (currentStr.equals(nextStr)) {
                count++;
            } else {
                if (count != 0) {
                    compressedLength += currentStr.length(); 
                    if (count > 1) {
                        compressedLength += String.valueOf(count).length();
                    }
                }
                
                currentStr = nextStr;
                count = 1;
            }
        }
        
        if (count != 0) {
            compressedLength += currentStr.length(); 
            if (count > 1) {
                compressedLength += String.valueOf(count).length();
            }
        }
        
        return compressedLength;
    }
}
