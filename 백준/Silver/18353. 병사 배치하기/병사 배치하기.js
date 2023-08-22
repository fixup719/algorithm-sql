let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let N = Number(line[0]);
let soldiers = line[1].split(" ").map(Number);
let selected = [0];


function lowerBound(start, end, target, arr){
  while(start < end){
    let mid = parseInt((start+end)/2);
    if(arr[mid] >= target) end = mid;
    else start = mid + 1;
  }
  return end;
}

for(let i=N-1; i>=0; i--){
  let target = soldiers[i];
  if(selected[selected.length-1] < target){
    selected.push(target);
  }else{
    // 여기서 크기 비교위한 lowerbound 사용
    let idx = lowerBound(0, selected.length, target, selected);
    selected[idx] = target;
  }
}

console.log(N-(selected.length-1))