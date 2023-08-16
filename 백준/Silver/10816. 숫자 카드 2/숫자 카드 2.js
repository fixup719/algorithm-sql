let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let N = Number(line[0]);
let M = Number(line[2]);
let cards = line[1].split(" ").map(Number).sort((a,b) => a-b);
let targets = line[3].split(" ").map(Number);

function lowerBound(start, end, target, arr){
  while(start < end){
    let mid = parseInt((start+end)/2);

    if(arr[mid] >= target) end = mid;
    else start = mid + 1;
  }
  return end;
}

function upperBound(start, end, target, arr){
  while(start < end){
     let mid = parseInt((start+end)/2);

     if(arr[mid] > target) end = mid;
     else start = mid + 1;
  }
  return end;
}

function countRange(startValue, endValue, arr){
  let startIdx = lowerBound(0 ,arr.length , startValue, arr);
  let endIdx = upperBound(0 ,arr.length , endValue, arr);
  return endIdx-startIdx;
}

let ans = []
for(let i=0; i<M; i++){
  let target = targets[i];
  ans.push(countRange(target, target, cards));
}

console.log(ans.join(" "))