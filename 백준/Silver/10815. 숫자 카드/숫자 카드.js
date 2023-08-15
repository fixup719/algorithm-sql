let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let have = line[1].split(" ").map(Number).sort((a,b) => {
  return a-b;
});
let cards = line[3].split(" ").map(Number);

function binarySearch(start, end, arr, target){
  while(start <= end){
    let mid = parseInt((start+end)/2);
    
    if(arr[mid] === target) return 1;
    else if(arr[mid] > target) end = mid-1;
    else start = mid+1;
  }
  return 0;
}

let ans = [];
for(let i=0; i<cards.length; i++){
  ans.push(binarySearch(0, have.length-1, have, cards[i]));
}

console.log(ans.join(" "))