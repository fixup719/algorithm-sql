let fs = require("fs");

let arr = [];
let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").map((el, idx) => {
  let obj = {
    key: ++idx,
    score: Number(el)
  }
  arr.push(obj)
});

let sum = 0;
let idxArr = [];
arr.sort((a,b) => {
  return a.score - b.score;
}).slice(3).map(el => {
  sum+=el.score;
  idxArr.push(el.key);  
});

console.log(sum);
console.log(idxArr.sort((a,b)=> {return a-b;}).join(" "));


