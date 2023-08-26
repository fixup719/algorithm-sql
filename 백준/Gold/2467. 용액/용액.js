let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let N = Number(line[0]);
let waters = line[1].split(" ").map(Number);

let start = 0;
let end = N-1;
let ansStart = 0;
let ansEnd = N-1;
let ansMixValue = Math.abs(waters[start] + waters[end]);

while(start < end){
  let mixValue = waters[start] + waters[end];

  
  if(ansMixValue > Math.abs(mixValue)){
    ansMixValue = Math.abs(mixValue);
    ansStart = start;
    ansEnd = end;

    if(mixValue === 0){
      break;
    }
  }
  
  if(mixValue < 0) start++;
  else end--;
}


console.log(waters[ansStart], waters[ansEnd]);

