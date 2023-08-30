let fs = require("fs");

let N = Number(fs.readFileSync("/dev/stdin").toString().trim());

function recursive(N, fac){
  // 5! = 5*4*3*2*1

  if(N===0) {
    return 1;
  }

  return N*recursive(N-1);
}

// 5*recursive(4)
// 5*4*recursive(3)
// 5*4*3*recursive(2)
// 5*4*3*2*recursive(1)
// 5*4*3*2*1*recursive(0)
// 5*4*3*2*1*1

console.log(recursive(N));


