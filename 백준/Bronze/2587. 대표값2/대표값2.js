let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").map(Number).sort((a,b) => {return a-b;});

let sum = 0;
line.map(el=> sum+=el);

let ans1 = sum/line.length;
let ans2 = line[parseInt(line.length/2)];
console.log(ans1);
console.log(ans2);