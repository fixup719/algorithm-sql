let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let [N,M] = line[0].split(" ").map(Number);
let times = line[1].split(" ").map(Number);

let low = -1;
let high = 0
for(let i=0; i<N; i++){
  high += times[i];
  low = Math.max(low, times[i]);
}


while(low<=high){
  let mid = parseInt((low+high)/2);

  let cnt = 1;
  let sum = 0;
  for(let i=0; i<N; i++){
    sum += times[i];
    if(sum > mid){
      cnt++;
      sum=times[i];
    }
  }

  if(cnt > M) low = mid+1;
  else high = mid-1;
}

console.log(low)