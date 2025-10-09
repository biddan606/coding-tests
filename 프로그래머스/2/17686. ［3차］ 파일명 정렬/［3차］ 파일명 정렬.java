import java.util.*;
import java.util.regex.*;

class Solution {
    /*
    HEAD - NUMBER - TAIL로 이루어진 파일명을 정렬한다.
    1. HEAD는 사전 순, 대소문자 구분X
    2. NUMBER 숫자 오름차순
    3. TAIL은 신경쓰지 않음
    */
    public String[] solution(String[] files) {
        // 정렬 기준 정의
        Pattern pattern = Pattern.compile(
            "^(?<header>[a-zA-Z \\.\\-]+)(?<number>[0-9]+)(?<tail>.*)$"
        );
        
        // 정렬
        Arrays.sort(files, (a, b) -> {
            Matcher aMatcher = pattern.matcher(a);
            aMatcher.find();
            String aHeader = aMatcher.group("header").toLowerCase();
            String aNumber = aMatcher.group("number");

            Matcher bMatcher = pattern.matcher(b);
            bMatcher.find();
            String bHeader = bMatcher.group("header").toLowerCase();
            String bNumber = bMatcher.group("number");

            int headerComparison = aHeader.compareTo(bHeader);
            if (headerComparison != 0) {
                return headerComparison;
            }

            int numberComparison = Integer.parseInt(aNumber) - Integer.parseInt(bNumber);
            if (numberComparison != 0) {
                return numberComparison;
            }

            return 0; 
        });
        
        return files;
    }
}