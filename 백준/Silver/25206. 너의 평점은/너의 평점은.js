let fs = require("fs");


let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").map(el => el.split(" "));

let sum = 0;
let grade = 0;
line.forEach(el => {
  grade += Number(el[1]);
  if(el[2] === 'A+'){
    sum += 4.5 * Number(el[1]);
  } else if(el[2] === 'A0'){
     sum += 4.0 * Number(el[1]);
  }else if(el[2] === 'B+'){
     sum += 3.5 * Number(el[1]);
  }else if(el[2] === 'B0'){
     sum += 3.0 * Number(el[1]);
  }else if(el[2] === 'C+'){
     sum += 2.5 * Number(el[1]);
  }else if(el[2] === 'C0'){
     sum += 2.0 * Number(el[1]);
  }else if(el[2] === 'D+'){
     sum += 1.5 * Number(el[1]);
  }else if(el[2] === 'D0'){
     sum += 1.0 * Number(el[1]);
  }else if(el[2] === 'F'){
     sum += 0.0 * Number(el[1]);
  }else{
    grade -= Number(el[1]);
  }
})

console.log(sum/grade);