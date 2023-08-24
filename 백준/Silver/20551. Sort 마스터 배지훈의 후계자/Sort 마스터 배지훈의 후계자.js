let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let [N, M] = line[0].split(" ").map(Number);
let arr1 = line.slice(1, N + 1).map(Number).sort((a, b) => a - b);
let arr2 = line.slice(N + 1).map(Number);


function check(start, end, arr, target) {
  if (start > end) return -1;

  let mid = parseInt((start + end) / 2);

  if (arr[mid] === target) return 1;
  else if (arr[mid] > target) return check(start, mid - 1, arr, target);
  else return check(mid + 1, end, arr, target);
}

function lowerBound(start, end, arr, target) {
  while (start < end) {
    let mid = parseInt((start + end) / 2);
    if (arr[mid] >= target) end = mid;
    else start = mid + 1;
  }
  return end
}

let ans = [];
for (let i = 0; i < M; i++) {
  if (check(0, N, arr1, arr2[i]) === 1) {
    ans.push(lowerBound(0, N, arr1, arr2[i]))
  } else {
    ans.push(-1);
  }

}

console.log(ans.join("\n"))

