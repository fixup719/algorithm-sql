let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim();

let T = Number(line)

// A : 5분
// B : 1분 
// C : 10초

// 시간의 합이 T초가 되게 하려고 한다. 
// ABC 버튼을 누른 횟수의 합은 항상 최소가 되게 하기

let A = 300;
let B = 60;
let C = 10;


let cntA = 0;
let cntB = 0;
let cntC = 0;
let flag = true;
while(T>0){
  if(T>=A){
    cntA += parseInt(T/A);
    T = T%A;
  }else if(T>=B){
     cntB += parseInt(T/B);
    T = T%B;
  }else if(T>=C){
     cntC += parseInt(T/C);
    T = T%C;
  }else{
    flag = false;
    break;
  }
}

if(flag){
  console.log(cntA + " " + cntB + " " + cntC);
}else{
  console.log(-1);
}

