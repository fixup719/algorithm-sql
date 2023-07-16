let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").splice(1);

// 합 구하기
function sum(str){
  let sum = 0;
  for(let i=0; i<str.length; i++){
    if(str[i] - '0' <= 9 && str[i] - '0' >= 0){
      sum += str[i] - '0';
    }
  }
   return sum;
}

line.sort((a,b) => {
  if(a.length === b.length){
    let sum1 = sum(a);
    let sum2 = sum(b);
    if(sum1 == sum2) return a.localeCompare(b);
    else return sum1 - sum2;
  }else{
    return a.length - b.length;
  }
  
  // if(a.length > b.length){
  //   return false;
  // }else if(a.length < b.length){
  //   return true;
  // }else{
  //   if(sum(a)>sum(b)){
  //     return false;
  //   }else if(sum(a)<sum(b)){
  //     return true;
  //   }else{
  //     if(a<b) return true;
  //     else return false;
  //   }
  // }
});

console.log(line.join("\n"));