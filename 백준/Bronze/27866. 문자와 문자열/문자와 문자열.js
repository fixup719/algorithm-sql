let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let N = Number(line[1]);
let string = line[0];

console.log(string[N-1])