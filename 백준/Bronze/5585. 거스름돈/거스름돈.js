let fs = require("fs");

let N = fs.readFileSync("/dev/stdin").toString().trim().split("\n").map(Number)[0];

let remain = 1000 - N;
let cnt = 0;

// 500 100 50 10 5 1
while(remain>0){
  if(remain>=500){
    cnt += parseInt(remain/500);
    remain -= parseInt(remain/500)*500;
  }else if(remain >=100){
     cnt += parseInt(remain/100);
    remain -= parseInt(remain/100)*100;
  }else if(remain >=50){
     cnt += parseInt(remain/50);
    remain -= parseInt(remain/50)*50;
  }else if(remain >=10){
     cnt += parseInt(remain/10);
    remain -= parseInt(remain/10)*10;
  }else if(remain >=5){
     cnt += parseInt(remain/5);
    remain -= parseInt(remain/5)*5;
  }else{
    cnt += parseInt(remain/1);
    remain -= parseInt(remain/1)*1;
  }
}

console.log(cnt)
