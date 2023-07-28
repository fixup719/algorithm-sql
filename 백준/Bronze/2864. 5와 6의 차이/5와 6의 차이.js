let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split(" ");

let A = line[0];
let B = line[1];

let minA = "";
let maxA = "";
let minB = "";
let maxB = "";
for(let i=0; i<A.length; i++){
  if(A[i]=='6' || A[i]=='5') {
    minA += '5';
    maxA += '6';
  }else{
    minA += A[i];
    maxA += A[i];
  }
}

for(let i=0; i<B.length; i++){
  if(B[i]=='6' || B[i]=='5') {
    minB += '5';
    maxB += '6';
  }else{
    minB += B[i];
    maxB += B[i];
  }
}

let min = Number(minA) + Number(minB);
let max = Number(maxA) + Number(maxB);

// let min = Number(A.replace('6', '5')) + Number(B.replace('6', '5')); 
// let max = Number(A.replace('5', '6')) + Number(B.replace('5', '6'));

console.log(min + " " +  max)