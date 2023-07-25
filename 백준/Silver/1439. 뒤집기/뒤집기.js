let fs = require("fs");

let S = fs.readFileSync("/dev/stdin").toString().trim().split("\n")[0];

// 가장 적은 애들을 뒤집는다. 
let cnt = 0;

let one = [];
let zero = [];
let sum = "";

for (let i = 0; i < S.length; i++) {
  if(S[i] === '0'){
    sum += S[i];
    if(S[i+1]==="1" || S[i+1]===undefined){
      zero.push(sum);
      sum="";
    }
  }else{
    sum += S[i];
    if(S[i+1]==="0" || S[i+1]===undefined){
      one.push(sum);
      sum="";
    }
  }
}


console.log(Math.min(one.length,zero.length))
