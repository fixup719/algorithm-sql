let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").slice(1)[0].split(" ").map(Number);

// 1. 딸기 우유 한 팩(0)
// 2. 초코 우유 한 팩(1)
// 3. 바나나 우류 한 팩(2)
// 4. 딸기 우유 한 팩
// 마실 수 있는 최대 우유 개수??

let check = [0, 0, 0];
let cnt = 0;
line.map((el, idx) => {
  if(cnt % 3 === el) cnt++;
})

console.log(cnt)