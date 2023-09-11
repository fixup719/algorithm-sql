let fs = require("fs");


let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").map(Number);

let sum = 0;
for(let i=0; i<line.length; i++){
  sum += line[i];
}

console.log(sum)