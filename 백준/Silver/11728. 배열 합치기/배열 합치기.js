let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").slice(1).map(el => el.split(" ").map(el => Number(el)));

const arr = [...line[0], ...line[1]];
arr.sort((a,b) => {return a-b});


console.log(arr.join(" "));
