let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let N = Number(line[0]);
let dists = line[1].split(" ").map(Number);
let prices = line[2].split(" ").map(Number);
let answer = 0;

let minPrice = prices[0];
for(let i=0; i<N; i++){
  minPrice = Math.min(minPrice, prices[i]);
  prices[i] = minPrice;
}

for(let i=0; i<N-1; i++){
  answer += dists[i]*prices[i];
}


console.log(answer)
