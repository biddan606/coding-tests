class Solution {
    /*
    문제:
    타일의 길이만큼 문자가 쓰여져 있다
    타일을 이용해서 만들 수 있는 유니크한 문자열의 개수를 반환해야 한다
    
    접근:
    - tiles.length의 최대 길이가 7이므로 어떤 알고리즘을 사용하여도 된다
    - 유니크한 조합만을 탐색하는 방법을 찾아야 한다
    
    직관:
    - 문자를 선택하거나 선택하지 않는 모든 경우의 수를 살펴본다 O(2^{tiles.length})
    - 유니크해야 하므로, Set을 통해 유니크함을 보장한다
    */

    private Set<String> combinations;
    private String tiles;

    public int numTilePossibilities(String tiles) {
        combinations = new HashSet<>();
        this.tiles = tiles;
        dfs(0, new boolean[tiles.length()], new StringBuilder());

        return combinations.size() - 1;
    }

    private void dfs(int depth, boolean[] visited, StringBuilder combination) {
        if (depth == tiles.length()) {
            combinations.add(combination.toString());
            return;
        }

        int nextDepth = depth + 1;
        dfs(nextDepth, visited, combination);
        
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            dfs(nextDepth, visited, combination.append(tiles.charAt(i)));
            
            combination.deleteCharAt(combination.length() - 1);
            visited[i] = false;
        }
    }
}
