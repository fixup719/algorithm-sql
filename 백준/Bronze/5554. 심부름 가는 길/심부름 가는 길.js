let fs = require("fs");
let input = fs.readFileSync("/dev/stdin")
	.toString()
	.split("\n")
	.map((el) => Number(el));
let sum = input.reduce((past, curr) => past + curr, 0);
let minutes = Math.floor(sum / 60);
let seconds = sum % 60;

console.log(minutes + '\n' + seconds);