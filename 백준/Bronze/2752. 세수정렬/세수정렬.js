let fs = require("fs");

let line = fs.readFileSync('/dev/stdin').toString().split(" ").map(el => Number(el));

function compare(a, b) {
  return a - b; // 오름차순
}

line.sort(compare);

let ans = '';
line.map(el => ans+=el+' ');
console.log(ans.trim());