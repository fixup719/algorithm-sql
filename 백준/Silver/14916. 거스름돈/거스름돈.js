let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim();

let N = Number(line);

// 2원
// 5원
// 동전 개수 최소가 될 것
// 거스름돈 n

// 5 10 15 20 
// 2 4 6 8 10 

let cnt = 0;
let flag = false;
while(N >= 0){

  if(N === 0 || N%5 === 0){
    cnt += parseInt(N/5);
    flag = true;
    break; 
  }
  
  
  N -= 2;
  cnt++;
}

if(flag) console.log(cnt);
else console.log(-1);