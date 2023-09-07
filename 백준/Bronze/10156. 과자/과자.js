let fs = require("fs");


let line = fs.readFileSync("/dev/stdin").toString().trim().split(" ").map(Number);

let result = line[0]*line[1] - line[2];

if(result < 0) console.log(0);
else console.log(result);
