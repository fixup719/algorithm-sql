let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let tc = Number(line[0]);
let arrs = line.slice(1);
let N = 0;

function compareTo(a,b){
  // 기준 1로 오름차순
  return a[0]-b[0];
}

for (let i = 0, j = 0; i < tc; i++, j += (N + 1)) {
  N = Number(arrs[j]);
  let mans = arrs.slice(j+1,j+N+1).map(el => el.split(" ").map(Number));

  mans.sort(compareTo);

  let cnt = 0;
  let min = 100001;
  for(let [x,y] of mans){
    if(y < min){
      min = y;
      cnt++;
    }
  }

  console.log(cnt);
}

