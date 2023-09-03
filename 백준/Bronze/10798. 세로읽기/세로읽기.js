let fs = require("fs");


let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let length = 0;
line.forEach(el => {
  length = Math.max(el.length, length);
})

let str = '';
for (let i = 0; i < length; i++) {
  for (let j = 0; j < line.length; j++) {
    if(line[j].length > i) str += line[j][i];
    else str += '';
  }
}

console.log(str)