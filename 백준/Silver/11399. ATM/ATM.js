let fs = require("fs");

let times = fs.readFileSync("/dev/stdin").toString().trim().split("\n").splice(1)[0].split(" ").map(Number).sort((a,b) => {return a-b;});


let ans = 0;
let acm = 0;
times.map(el => {
  ans += (acm+el);
  acm += el;
})

console.log(ans)

