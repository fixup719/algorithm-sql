let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let [N, M] = line[0].split(" ").map(Number);
let arr1 = line.slice(1, N + 1).map(Number).sort((a, b) => a - b);
let arr2 = line.slice(N + 1).map(Number);

function lowerBound(start, end, arr, target) {
  while (start < end) {
    let mid = parseInt((start + end) / 2);
    if (arr[mid] >= target) end = mid;
    else start = mid + 1;
  }
  return arr[end] === target ? end :  -1;
}

let ans = [];
for (let i = 0; i < M; i++) {
    ans.push(lowerBound(0, N, arr1, arr2[i]))
}

console.log(ans.join("\n"))

