let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().split("\n");

console.log(line.join("\n"))