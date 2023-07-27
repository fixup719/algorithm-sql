let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").map(Number);

let N = line[0];
let lopes = line.splice(1).sort((a,b) => {
  return a-b;
});

let kgs = [];
for(let i=0, j=N; i<lopes.length; i++, j--){
 kgs.push(lopes[i]*j);
}

kgs.sort((a,b)=>{
  return b-a;
})


console.log(kgs[0]);

