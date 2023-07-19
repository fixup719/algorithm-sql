let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").splice(1).map(el => el.split(" ")).map(el=> el.map((el, key) => {
  if(key>0){
   return Number(el)
  }else{
   return el
  }
}));


line.sort((a,b) => {
  if(a[3]===b[3]){
    if(a[2]===b[2]){
      return b[1]-a[1];
    }else{
      return b[2]-a[2];
    }
  }else{
    return b[3]-a[3];
  }
});

let ans = line[0][0] + "\n" + line[line.length-1][0];
console.log(ans);

