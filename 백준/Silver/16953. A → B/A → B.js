let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split(" ");

let A = Number(line[0]);
let B = Number(line[1]);

let cnt = 0;
while(true){
  if(B<A){
    cnt = -1;
    break;
  }else if(B===A){
    cnt++;
    break;
  }

  if(B%2===0){
    B /= 2;
  }else if(B%10===1){
    B = Math.floor(B/10);
  }else{
    cnt = -1;
    break;
  }
  
  cnt++;
}

console.log(cnt)