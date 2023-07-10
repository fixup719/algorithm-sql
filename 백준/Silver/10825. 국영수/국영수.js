let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n").slice(1);

const arr = line.map(el=> el.split(" "))

// 1. 국어점수 내림차순
// 2. 같으면 영어점수 오름차순
// 3. 같으면 수학점수 내림차순
// 4. 모두 같으면 사전순

function compareTo(a,b){
  let a_name = a[0];
  let a_kor = Number(a[1]);
  let a_math = Number(a[3]);
  let a_eng = Number(a[2]);
  
  let b_name = b[0];
  let b_kor = Number(b[1]);
  let b_math = Number(b[3]);
  let b_eng = Number(b[2]);
  
  if(a_kor === b_kor){
    if(a_eng === b_eng){
      if(a_math === b_math){
        if(a_name > b_name){
          return 1;
        }else{
          return -1;
        }
      }else{
        return b_math - a_math;
      }
    }else{
      return a_eng - b_eng;
    }
  }else{
    return b_kor - a_kor;
  }
}

arr.sort(compareTo);

const name =[];
arr.map(el => name.push(el[0]));

console.log(name.join("\n"))