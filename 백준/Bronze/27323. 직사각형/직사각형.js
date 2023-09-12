let fs = require("fs");


let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").map(Number);

console.log(line[0]*line[1])