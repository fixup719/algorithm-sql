let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split(" ").map(BigInt);

let N = line[0];
let M = line[1];

console.log(String(N / M));
console.log(String(N % M));