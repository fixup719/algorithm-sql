let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").map(el=> el.split(" ").map(Number));

line.splice(line.length-1)

let str = '';
for(let i=0; i<line.length; i++){
  if(line[i][0] > line[i][1]) str += 'Yes\n';
  else str+= 'No\n'
}

console.log(str.trim());