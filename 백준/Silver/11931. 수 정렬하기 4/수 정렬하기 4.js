let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").splice(1).map(Number);

line.sort((a,b)=>{return b-a});

console.log(line.join("\n"))
