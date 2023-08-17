let fs = require("fs");

let arr = fs.readFileSync("/dev/stdin").toString().trim().split("\n").map(Number);

let sum = 0;
arr.forEach(el => {
  if(el < 40){
    el = 40;
  }
  sum+=el
});

console.log(sum/5)