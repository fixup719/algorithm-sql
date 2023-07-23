let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let K = Number(line[0].split(" ")[1]);
let prices = line.slice(1).map(Number);
let size = prices.length-1;

let cnt = 0;
let remain = K;
let temp = 0;
for(let i=size; i>=0; i--){
  if(prices[i] <= K){
      temp = parseInt(K/prices[i]);
      cnt += temp;
      K -= temp*prices[i];
  }
}

console.log(cnt)

