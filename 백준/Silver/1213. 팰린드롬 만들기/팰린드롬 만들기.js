let fs = require("fs");

let str = fs.readFileSync("/dev/stdin").toString().trim();

let count = [];
for(let i=0; i<str.length; i++){
  const j = str.charCodeAt(i)-65;
  if(count[j]) count[j]++
  else count[j] = 1;
}

let odd = count.filter(el => el%2!==0);

if(odd.length > 1){
  console.log("I'm Sorry Hansoo");
}else{
  let start = '';
  let end = '';
  let middle = '';
  
  count.forEach((el, idx) => {
    if(el % 2 !== 0){
      middle += String.fromCharCode(idx + 65);
      el--;
    }
    
    for(let i=0; i<el/2; i++){
      start = start + String.fromCharCode(idx + 65);
      end = String.fromCharCode(idx + 65)+end;
    }
    
   
  })
  
  console.log(start+middle+end);
}

