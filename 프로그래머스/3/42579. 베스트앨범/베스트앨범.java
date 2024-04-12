import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 장르별 분류한다.
        Map<String, Integer> playsByGenre = new HashMap<>();
        Map<String, List<Music>> musicsByGenre = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            Music music = new Music(i, plays[i]);
            
            playsByGenre.put(genre, playsByGenre.getOrDefault(genre, 0) + music.plays);
            musicsByGenre.computeIfAbsent(genre, k -> new ArrayList<>())
                .add(music);
        }
        
        // 장르별 순위를 구한다.
        PriorityQueue<Rank> rankQueue = new PriorityQueue<>((r1, r2) -> r2.plays - r1.plays);
        for (Map.Entry<String, Integer> entry : playsByGenre.entrySet()) {
            Rank rank = new Rank(entry.getKey(), entry.getValue());
            
            rankQueue.offer(rank);
        }
        
        // 장르 순위 순서로 곡을 선정한다.
        List<Integer> result = new ArrayList<>();
        while (!rankQueue.isEmpty()) {
            Rank current = rankQueue.poll();
            List<Music> musics = musicsByGenre.get(current.genre);
            
            Collections.sort(musics, (m1, m2) -> {
                if (m2.plays == m1.plays) {
                    return m1.id - m2.id;
                }
                return m2.plays - m1.plays;
            });
            
            result.add(musics.get(0).id);
            if (musics.size() > 1) {
                result.add(musics.get(1).id);
            }
        }
        
        return result.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
    
    private static class Music {
        final int id;
        final int plays;
        
        public Music(int id, int plays) {
            this.id = id;
            this.plays = plays;
        }
    }
    
    private static class Rank {
        final String genre;
        final int plays;
        
        public Rank(String genre, int plays) {
            this.genre = genre;
            this.plays = plays;
        }
    }
}