let fs = require("fs");

let logs = fs.readFileSync("/dev/stdin").toString().trim().split("\n").splice(1).map(el => el.split(" "));

let map = new Map(logs.map(el => [el[0], el[1]]));
let result = []

for(let key of map.keys()){
  if(map.get(key) !== 'leave') result.push(key)
}

console.log(result.sort().reverse().join("\n"))





