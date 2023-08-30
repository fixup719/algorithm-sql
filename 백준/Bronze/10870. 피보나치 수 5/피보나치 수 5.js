let fs = require("fs");

let N = Number(fs.readFileSync("/dev/stdin").toString().trim());

function recurs(N){
  // N = 5
  // 0 1 1 2 3 5 
  if(N===0 || N===1){
    return N;
  }

  // N = 5
  // 0 1 1(0+1) 2(1+1) 3(1+2) 5(2+3)

  // N=2
  
  
  return recurs(N-1) + recurs(N-2)
}

console.log(recurs(N));