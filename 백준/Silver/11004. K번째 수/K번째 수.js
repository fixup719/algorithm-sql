let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").map(el => el.split(" ").map(Number));

const K = line[0][1];

line[1].sort((a,b)=>{return a-b;});

line[1].map((el,idx) => {
  if(idx === K-1){
    console.log(el)
  }
})