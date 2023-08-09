let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").map(el => el.split(" ").map(Number));

let N = line[0][0];
let M = line[0][1];

let pack = [];
let unit = [];
let arr = line.slice(1);
for(let i=0; i<arr.length; i++){
  pack.push(arr[i][0]);
  unit.push(arr[i][1]);
}

pack.sort((a,b) => {
  return a-b;
})

unit.sort((a,b) => {
  return a-b;
})

let price =Math.min(pack[0]*(parseInt(N/6)+1) ,Math.min(unit[0]*N,pack[0]*(parseInt(N/6))+unit[0]*(N%6)));


console.log(price);