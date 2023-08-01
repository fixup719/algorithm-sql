let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split(" ");

let N = Number(line[0]);
let K = Number(line[1]);


let sum = 0;
for (let i = 1; i <= K; i++) {
  sum += i;
}

if (sum > N) {
  console.log(-1);
} else {
  N -= sum;
  if (N % K === 0) {
    console.log(K - 1);
  } else {
    console.log(K);
  }
}

