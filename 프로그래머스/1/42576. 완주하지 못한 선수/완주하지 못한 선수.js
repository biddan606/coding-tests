function solution(participant, completion) {
    const completionMap = new Map();
    
    for (const c of completion) {
        completionMap.set(c, (completionMap.get(c) ?? 0) + 1);
    }
    
    for (const p of participant) {
        if (!completionMap.has(p)) {
            return p;
        }
        
        const count = completionMap.get(p);
        if (count <= 1) {
            completionMap.delete(p);
        } else {
            completionMap.set(p, count - 1);
        }
    }
    
    return null;
}
