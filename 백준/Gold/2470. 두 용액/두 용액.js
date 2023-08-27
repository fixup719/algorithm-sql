let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let N = Number(line[0]);
let waters = line[1].split(" ").map(Number).sort((a,b)=>a-b);

let start = 0;
let end = N-1;
let ansStart = 0;
let ansEnd = N-1;
let mix = Math.abs(waters[start]+waters[end]);

while(start<end){
  let mixTemp = waters[start]+waters[end];

  if(Math.abs(mixTemp) < mix){
    mix = Math.abs(mixTemp);
    ansStart = start;
    ansEnd = end;

    if(mix === 0) break;
  }

  if(mixTemp < 0 ) start++;
  else end--;

}

console.log(waters[ansStart], waters[ansEnd])
