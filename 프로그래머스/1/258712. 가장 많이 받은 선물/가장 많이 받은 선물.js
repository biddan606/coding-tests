// 두 사람 사이에서 선물을 많이 준 사람이 다음 달에 선물 1개를 받음
// 선물 준 횟수가 같을 경우 선물 지수가 높은 사람이 1개를 받음
// 선물 지수 = 이번 달까지 친구들에게 준 선물 수 - 받은 선물 수
// 가장 많이 선물을 받을 친구의 선물의 수 반환
function solution(friends, gifts) {
    const friendCount = friends.length;

    const nameIndexes = friends.reduce((acc, value, index) => {
        acc[value] = index;
        return acc;
    }, {});
    
    const giftTable = Array.from({ length: friendCount }, () => 
      Array(friendCount).fill(0)
    );
    
    const giftScores = Array(friendCount).fill(0);
    
    gifts.forEach(gift => {
        const [from, to] = gift.split(' ');
        const fromIndex = nameIndexes[from];
        const toIndex = nameIndexes[to];
        
        giftTable[fromIndex][toIndex]++;
        giftScores[fromIndex]++;
        giftScores[toIndex]--;
    });
    
    const nextMonthGifts = Array(friendCount).fill(0);
    
    for (let i = 0; i < friendCount; i++) {
        for (let j = i + 1; j < friendCount; j++) {
            const iToJ = giftTable[i][j];
            const jToI = giftTable[j][i];
            
            if (iToJ > jToI) {
                nextMonthGifts[i]++;
            } else if (iToJ < jToI) {
                nextMonthGifts[j]++;
            } else {
                const iScore = giftScores[i];
                const jScore = giftScores[j];
                
                if (iScore > jScore) {
                    nextMonthGifts[i]++;
                } else if (jScore > iScore) {
                    nextMonthGifts[j]++;
                }
            }
        }
    }
    
    return Math.max(...nextMonthGifts);
} 
