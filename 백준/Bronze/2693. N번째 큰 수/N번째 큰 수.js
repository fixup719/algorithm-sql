let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const N = Number(line[0]);

let ans = [];
for(let i=1; i<=N ; i++){
  const arr = line[i].split(" ").map(Number).sort((a,b) => b-a);

  ans.push(arr[2]);
}

console.log(ans.join("\n"));

