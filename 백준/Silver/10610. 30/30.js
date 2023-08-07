let fs = require("fs");

let input = fs.readFileSync("/dev/stdin").toString().trim();

let sum = 0;
let check = false; 
for(let i=0, size = input.length; i<size; i++){
   sum += Number(input[i]);
  if(input[i] === '0') check = true;
}

if(sum%3===0 && check){
  //30의 배수라면
  let arr = input.split("").map(Number);
  arr.sort((a,b) => {
    return b-a;
  });
  console.log(arr.join(""));
}else{
  console.log(-1)
}

