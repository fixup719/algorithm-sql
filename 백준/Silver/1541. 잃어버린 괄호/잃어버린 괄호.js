let fs = require("fs");

let formula = fs.readFileSync("/dev/stdin").toString().trim().split("\n")[0];

let groups = formula.split("-");
let ans = 0;

// reduce 
// array.reduce((누적값, 현재값, 인덱스, 요소) => {return 결과}, 초기값)

for(let i=0; i<groups.length; i++){
  let cur = groups[i].split("+").map(Number).reduce((a,c) => a+c);
  if(i == 0) ans += cur;
  else ans -= cur;
}

console.log(ans);