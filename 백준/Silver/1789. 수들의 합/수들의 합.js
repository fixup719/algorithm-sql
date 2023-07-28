let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n")[0];

let N = Number(line);

let i=1;
let sum=0;
let cnt = 0;
while(true){
  sum += i++;
  cnt++;
  if(sum > N){
    cnt--;
    break;
  }
}

console.log(cnt)