let fs = require("fs");

let cents = fs.readFileSync("/dev/stdin").toString().trim().split("\n").slice(1).map(Number);

// 쿼터 0.25
// 다임 0.10
// 니켈 0.05
// 페니 0.01
// 동전의 개수를 구하시오
// 거스름돈은 항상 5이하
// 최소 동전 개수

let results = [];

for(let i=0; i<cents.length; i++){
  let cent = cents[i];
  let q = 0;
  let d = 0;
  let n = 0;
  let p = 0;
  let tmp = 0;
  
  while(cent>0){
    if(cent >= 25){
      tmp = parseInt(cent / 25);
      cent -= 25*tmp;
      q+=tmp;
    }else if(cent >= 10){
      tmp = parseInt(cent / 10);
      cent -= 10*tmp;
      d+=tmp;
    }else if(cent >= 5){
      tmp = parseInt(cent / 5);
      cent -= 5*tmp;
      n+=tmp;
    }else if(cent >= 1){
      tmp = parseInt(cent / 1);
      cent -= 1*tmp;
      p+=tmp;
    }
  }

  results.push(q + " " + d + " " + n + " " + p);
}

console.log(results.join("\n"))