let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").slice(1);

const org_coords = line[0].split(" ").map(Number);


const coords = [...new Set(line[0].split(' ').map(el => Number(el)).sort((a,b)=> a-b))];

const object = {};

coords.map((el,idx) => object[el] = idx);

let ans = [];
for(let i=0; i<org_coords.length; i++){
  ans.push(object[org_coords[i]]);
}

console.log(ans.join(' '));

// index of 는 시간 초과
// let ans = '';
// org_coords.map(el => ans += coords.indexOf(el) + " ");
// console.log(ans);