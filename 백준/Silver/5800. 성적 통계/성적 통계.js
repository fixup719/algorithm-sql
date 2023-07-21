let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").slice(1).map(el => el.split(" ")).map(el=> el.slice(1)).map(el => el.map(Number)).map(el => el.sort((a,b) => {
  return a-b;
}));

let ans = ""

for(let i=0; i<line.length; i++ ){
  let arr = line[i];

  let max = 0;
  for(let j=0; j<arr.length-1; j++){
    let gap = arr[j+1] - arr[j];
    if(max < gap) max = gap;
  }
  
    ans += `Class ${i+1}\nMax ${line[i][line[i].length-1]}, Min ${line[i][0]}, Largest gap ${max}\n`;
}

console.log(ans)