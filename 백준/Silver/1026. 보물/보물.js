let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").splice(1);

let A = line[0].split(" ").map(Number);
let B = line[1].split(" ").map(Number);

A.sort((a,b) => {
  return a-b;
});
B.sort((a,b) => {
  return b-a;
})

let sum = 0;
for(let i=0; i<A.length; i++){
  sum += A[i]*B[i];
}

console.log(sum)