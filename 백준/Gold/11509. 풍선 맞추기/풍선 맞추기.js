let fs = require("fs");

let arr = fs.readFileSync("/dev/stdin").toString().trim().split("\n").slice(1)[0].split(" ").map(Number);

let cnt = 0;

let arrow = new Array(1000001).fill(0); // 각 높이에 화살이 몇 개?
for(let x of arr){
  if(arrow[x] > 0){
    // 해당 높이에 화살이 있다면
    arrow[x] -= 1;
    arrow[x-1] += 1;
  }else {
    // 해당 높이에 화살이 없다면
    arrow[x-1] += 1;
    // 화살을 쏜다. 
    cnt++;
  }
}

console.log(cnt)

