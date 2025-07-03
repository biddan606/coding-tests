function solution(my_string) {
    return my_string.split("")
        .filter(s => /^\d$/.test(s))
        .map(Number)
        .sort();
}