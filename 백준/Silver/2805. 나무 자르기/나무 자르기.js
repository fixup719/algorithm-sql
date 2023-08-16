let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let [N, M] = line[0].split(" ").map(Number);
let trees = line[1].split(" ").map(Number);

let low = 0;
let high = trees.reduce((a, b) => Math.max(a, b));


let answer = 0;
while (low <= high) {
  let mid = parseInt((low + high) / 2);

  let sum = 0;
  trees.forEach(el => {
    if (el > mid) {
      sum += (el - mid);
    }
  })

  if (sum < M) {
    high = mid - 1;
  } else {
    answer = mid;
    low = mid + 1;
  }
}

console.log(answer)