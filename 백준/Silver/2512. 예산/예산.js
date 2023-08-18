let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let N = Number(line[0]);
let needs = line[1].split(" ").map(Number);
let budget = Number(line[2]);

let low = 0;
let high = needs.reduce((a,b) => Math.max(a,b));

let ans = 0;
while(low <= high){

  let mid = parseInt((high+low)/2);

  let sum = 0;
  needs.forEach(el => {
    if(mid < el){
      sum += mid;
    }else{
      sum += el;
    }
  })

  if(sum <= budget){
    low = mid+1;  
    ans = mid;
  }else{
    high = mid-1;
  }
  
}

let max = 0;
needs.forEach(el => {
  if(el > ans){
    max = Math.max(max, ans);
  }else{
    max = Math.max(max, el);
  }
})

console.log(max)