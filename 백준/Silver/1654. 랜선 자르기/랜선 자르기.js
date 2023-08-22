let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let [K, N] = line[0].split(" ").map(Number);
let wires = line.splice(1).map(Number).sort((a,b) => a-b);

let high = wires[K-1];
let low = 1;
let minWire = wires[0];

let ans = 0;
while(low <= high){
  let mid = parseInt((high+low)/2);
  let sum = 0;
  wires.forEach(el => {
    sum += parseInt(el/mid);
  })

  if(sum >= N){
    ans = mid;
    low = mid+1;
  }else{
    high = mid - 1;
  }
  
}
console.log(ans)