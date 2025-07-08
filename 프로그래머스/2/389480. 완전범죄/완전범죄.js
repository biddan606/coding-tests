// 2^40 = 1,099,511,627,776로 dfs 불가능
// dp로 풀어야 함
function solution(info, n, m) {
    const dp = Array.from({length: info.length}, () => Array(m).fill(Infinity));
    dp[0][0] = info[0][0];
    if (info[0][1] < m) {
        dp[0][info[0][1]] = 0;
    }
    
    for (let i = 1; i < info.length; i++) {
        const [costA, costB] = info[i];
        
        for (let b = 0; b < m; b++) {
            const prev = dp[i - 1][b];
            if (prev === Infinity) {
                continue;
            }
            
            dp[i][b] = Math.min(dp[i][b], prev + costA);
            
            if (b + costB < m) {
                dp[i][b + costB] = Math.min(dp[i][b + costB], prev);
            }
        }
    }
    
    let result = Infinity;
    
    dp.at(-1)
        .forEach((a) => result = Math.min(result, a));
    
    return result < n ? result : -1;
}
