let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let N = Number(line[0]);
let M = Number(line[2]);
let numbers = line[1].split(" ").map(Number).sort((a,b) => a-b);
let targets = line[3].split(" ").map(Number);

function binarySearch(start, end, target, arr){
  if(start > end ) return 0;

  let mid = parseInt((start+end)/2);

  if(arr[mid] > target) return binarySearch(start, mid-1, target, arr);
  else if(arr[mid] < target) return binarySearch(mid+1, end, target, arr);
  else return 1;
}

let ans = [];
for(let i=0; i<M; i++){
  ans.push(binarySearch(0, N-1, targets[i], numbers));
}

console.log(ans.join("\n"))
