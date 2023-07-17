let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n")
  .map(el => el.split(" ").map(Number));

let number = line[0][1];
let list = line[1];

list.sort((a,b) => {return b-a});

let ans = 0; 

list.map((el, key) => {
  if(key<number){
    ans = el;
    return;
  }
})
console.log(ans)



