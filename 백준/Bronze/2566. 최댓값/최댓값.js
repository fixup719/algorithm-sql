let fs = require("fs");

let arr = fs.readFileSync("/dev/stdin").toString().trim().split("\n").map(el => el.split(" ").map(Number));

// https://www.acmicpc.net/problem/2566
let max = {
  row : 0,
  col : 0,
  value : 0,
}

for(let i=0; i<9; i++){
  for(let j=0; j<9; j++){
    if(max.value<=arr[i][j]){
      max.value = arr[i][j];
      max.row = i+1;
      max.col = j+1;
    }
  }
}

console.log(max.value);
console.log(max.row + " " + max.col);