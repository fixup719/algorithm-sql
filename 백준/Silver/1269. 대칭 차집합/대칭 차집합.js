let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let A = new Set([...line[1].split(" ").map(Number)]);
let B = new Set([...line[2].split(" ").map(Number)]);

const difference = (set1, set2) => {
  let cnt = 0;
  set2.forEach(el => {
    if(set1.has(el)) cnt++;
  })  
  return cnt;
}

console.log(A.size-difference(A,B) + B.size-difference(B,A)) ;

