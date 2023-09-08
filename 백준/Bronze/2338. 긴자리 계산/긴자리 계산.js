let fs = require("fs");


let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").map(BigInt);

let A = line[0];
let B = line[1];
console.log((A+B).toString());
console.log((A-B).toString());
console.log((A*B).toString());