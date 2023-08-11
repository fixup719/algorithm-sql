let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").map(el => el.split(" ").map(Number));

let N = line[0][0];
let L = line[0][1];
let toFix = line[1].sort((a,b) => {
  return a-b;
});

let cnt = 1;
let range = toFix[0] + L - 1;
for(let i=0; i<toFix.length; i++){
  if(range < toFix[i]){
    cnt++;
    range = toFix[i] + L - 1;
  }
}

console.log(cnt)