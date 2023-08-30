let fs = require("fs");

let cases = fs.readFileSync("/dev/stdin").toString().trim().split("\n").slice(1);

function recur(str, s, e, cnt){
  
  if(s>=e) {
    return [1, cnt];
  }
  else if(str.charAt(s) !== str.charAt(e)) {
    return [0, cnt];
  }
  else return recur(str, s+1, e-1, ++cnt);
}

function isPalindrome(str){
  return recur(str, 0, str.length-1, 1);
}

let ans = [];
for(let i=0; i<cases.length; i++){
  ans.push(isPalindrome(cases[i]).join(" "));
}
console.log(ans.join("\n"));