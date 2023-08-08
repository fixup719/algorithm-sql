let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").slice(0,-1).map(el => el.split(" ").map(Number));

let answer = [];
for(let i=0; i<line.length; i++){
  let L = line[i][0]; // 캠핑 가능한 일수
  let P = line[i][1]; // 연속된 P일
  let V = line[i][2]; // 휴가 일수

  let block = parseInt(V/P);
  let remain = V%P;

  if( remain > L){
    answer.push(`Case ${i+1}: ${block*L+L}`);
  }else{
    answer.push(`Case ${i+1}: ${block*L+remain}`);
  }
  
}

console.log(answer.join("\n"))

