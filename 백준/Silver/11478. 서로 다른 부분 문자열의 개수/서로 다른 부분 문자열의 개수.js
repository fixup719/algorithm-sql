let fs = require("fs");

let str = fs.readFileSync("/dev/stdin").toString().trim();

let set = new Set();

for(let i=0; i<str.length; i++){
  for(let j=0; j<str.length+1; j++){
    set.add(str.slice(i,j));
  }
}
console.log(set.size-1);
