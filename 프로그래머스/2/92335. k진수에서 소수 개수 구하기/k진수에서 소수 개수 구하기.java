import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int n, int k) {
        String converted = convert(n, k);
        
        List<Long> splited = split(converted);
        
        int primeCount = 0;
        for (long number : splited) {
            if (isPrime(number)) {
                primeCount++;
            }
        }
        
        return primeCount;
    }
    
    private String convert(int original, int radix) {
        int current = original;
        StringBuilder converted = new StringBuilder();
        
        while (current != 0) {
            converted.append(current % radix);
            current /= radix;
        }
        
        return converted.reverse().toString();
    }
    
    private List<Long> split(String str) {
        return Arrays.stream(str.split("0+"))
            .filter(s -> !s.isEmpty()) // isBlank()로 대체 가능, 공백 문자열이 들어오지 않아 isEmpty() 사용함
            .map(Long::parseLong)
            .collect(Collectors.toList());
    }
    
    private boolean isPrime(long number) {
        if (number <= 1) {
            return false;
        }
        
        for (long l = 2; l * l <= number; l++) {
            if (number % l == 0) {
                return false;
            }
        }
        
        return true;
    }
}