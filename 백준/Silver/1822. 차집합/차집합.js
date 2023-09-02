let fs = require("fs");

let cards = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let A = new Set([...cards[1].split(" ").map(Number)]);
let B = new Set([...cards[2].split(" ").map(Number)]);

const difference = (a, b) => {
  if(a.has(b)) a.delete(b);
}

B.forEach(el => {
  difference(A,el);
})

console.log(A.size)
console.log([...A].sort((a,b)=>a-b).join(" "))