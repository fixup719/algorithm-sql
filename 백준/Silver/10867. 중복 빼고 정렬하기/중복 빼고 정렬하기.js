let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").splice(1)[0].split(" ").map(Number);

const ans = [...new Set(line)].sort((a,b) => {return a-b;})

console.log(ans.join(" "))

