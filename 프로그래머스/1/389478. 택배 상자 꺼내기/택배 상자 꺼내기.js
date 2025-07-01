/*
# 문제 이해

## 상자 놓기

1. w개의 상자를 한 줄에 놓는다.
2. 왼->오, 오->왼 순으로 번갈아가며 놓는다.
3. n개의 상자를 모두 놓으면 끝난다.

## 상자 꺼내기

중간 상자를 꺼내려면 상위에 있는 상자를 꺼낸다음, 중간 상자를 꺼내야 한다.
이 때 총 꺼내야 하는 상자 개수를 반환한다.
꺼내려는 상자 = 8이라면, 20번 17번을 먼저 꺼내야 함
반환값 = 3

# 정리

한 방향이 아닌 두 방향이므로 수식으로 계산하기 애매해보인다

최대값은 n=100, w=10이므로 단순 구현으로 풀이가 가능하다

1. 순서대로 상자를 놓은 후, 
2. 꺼내려는 상자 index를 찾아
3. 꺼내야 하는 총 상자 수를 구하자

# 구현

## 상자 쌓기
1. 상자를 담은 배열을 선언한다
    배열은 w개의 길이를 가지고, 각 원소도 배열을 가진다
2. level이 짝수일 때는 왼->오, level이 홀수일 때는 오->왼으로 상자를 쌓는다
---

## 상자 꺼내기
1. 꺼낼 상자의 index를 찾는다. row col
    row = 꺼낼 번지, col = level
2. row의 총 길이 - col을 반환한다(꺼내야 하는 상자의 개수)

*/
function solution(n, w, num) {
    let boxes = [];
    for (let i = 0; i < w; i++) {
        boxes.push([]);
    }
    
    for (let b = 1; b <= n; b++) {
        let row = (b - 1) % w;
        if (((b - 1) / w) & 1 == 1) {
            row = (w - 1) - row;
        }
        
        boxes[row].push(b);
    }
    
    let numRow = -1;
    let numCol = -1;
    for (let row = 0; row < w; row++) {
        numCol = boxes[row].findIndex(n => n == num);
        if (numCol !== -1) {
            numRow = row;
            break;
        }
    }
    
    return boxes[numRow].length - numCol;
}
