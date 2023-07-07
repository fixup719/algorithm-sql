let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").slice(1);

let num_arr = line.map(el => el.split(" ").map(num_el => Number(num_el)));

function compare(a, b){
  if(a[0] === b[0]) {
    return a[1]-b[1];
  }else{
    return a[0]-b[0];
  }
}

num_arr.sort(compare);

let ans = '';
num_arr.map(el => {
  ans += `${el[0]} ${el[1]}\n`;
})

console.log(ans);