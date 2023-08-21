let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let T = Number(line[0]);

function binarySearch(arr, start, end, target) {
  if (start > end) return 0;

  let mid = parseInt((start + end) / 2);
  if (arr[mid] === target) return 1;
  else if (arr[mid] < target) return binarySearch(arr, mid + 1, end, target);
  else return binarySearch(arr, start, mid - 1, target);
}

let ans = [];
for (let i = 1, j = 1; i <= T; i++, j += 4) {
  let N = Number(line[j]);
  let M = Number(line[j+2]);
  let nArr = line[j+1].split(" ").map(Number).sort((a, b) => a - b);
  let mArr = line[j+3].split(" ").map(Number);

  for (let i = 0; i < M; i++) {
    let target = mArr[i];
    ans.push(binarySearch(nArr, 0, N - 1, target));
  }

}

console.log(ans.join("\n"))