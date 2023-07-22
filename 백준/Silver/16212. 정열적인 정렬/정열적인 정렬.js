let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").slice(1).map(el => el.split(" ").map(Number))[0];

line.sort((a,b) => {
  return a-b;
})

console.log(line.join(" "))