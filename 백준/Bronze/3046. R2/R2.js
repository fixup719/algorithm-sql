let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");


let [R1, S] = line[0].split(" ").map(Number);
console.log(2*S-R1);