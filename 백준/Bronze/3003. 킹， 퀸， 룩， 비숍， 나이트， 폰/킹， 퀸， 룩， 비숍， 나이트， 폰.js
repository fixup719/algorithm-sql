let fs = require("fs");

let pieces = fs.readFileSync("/dev/stdin").toString().trim().split(" ").map(Number);

let sets = [1,1,2,2,2,8];
let results = [];
for(let i=0; i<pieces.length; i++){
  results.push(sets[i]-pieces[i]);
}

console.log(results.join(" "))
