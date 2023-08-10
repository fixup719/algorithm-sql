let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").map(el => el.split(" ").map(Number));

let N = line[0][0];
let M = line[0][1];

let org = line.slice(1, 1+N);
let copy = line.slice(1+N);

let result = [];

org.forEach((el, i) => {
  let arr = '';
  el.forEach((el, j) => {
    arr += (el + copy[i][j]) + " ";
  })

  result.push(arr);
})

console.log(result.join("\n"))