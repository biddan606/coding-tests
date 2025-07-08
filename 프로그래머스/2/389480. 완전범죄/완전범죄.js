// 2^40 = 1,099,511,627,776로 dfs 불가능
// dp로 풀어야 함
function solution(info, n, m) {
    // memorization: 현재 A + memory[index][b]를 탐색한다
    //      - memory[index][b]: 현재 index에서 b일 때 만들 수 있는 A의 최소값
    const memory = Array.from({length: info.length}, () => Array(m).fill(-1));
    
    // 모든 경우의 수를 탐색한다
    steal = (index, currentB) => {
        if (currentB >= m) {
            return Infinity;
        }
        
        if (index === info.length) {
            return 0;
        }
        
        if (memory[index][currentB] !== -1) {
            return memory[index][currentB];
        }
        
        const [costA, costB] = info[index];
        
        const case1 = costA + steal(index + 1, currentB);
        const case2 = steal(index + 1, currentB + costB);
        
        memory[index][currentB] = Math.min(case1, case2);
        return memory[index][currentB];
    }
    
    const result = steal(0, 0);
    return result >= n ? -1 : result;
}
