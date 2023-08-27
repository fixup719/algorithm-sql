let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let T = Number(line[0]);


let ans = [];

const binarySearch = (start, end, target, arr) => {

  if(start > end) return start; 
  
  let mid = parseInt((start+end)/2);

  // if(target===3){
  //   console.log(start, end, mid, arr[mid], target)
  // }

  if(arr[mid] < target){
    return binarySearch(mid+1, end, target, arr );
  }else if(arr[mid] >= target){
    return binarySearch(start, mid-1, target, arr);
  }
  
}


for(let i=1; i<=T*3; i+=3){
  let cnt = 0;
  let [N, M] = line[i].split(" ").map(Number);
  let arrA = line[i+1].split(" ").map(Number);
  let arrB = line[i+2].split(" ").map(Number).sort((a,b) => a-b);

  
  for(let j=0; j<N; j++){
    let target = arrA[j];
    cnt+= binarySearch(0, M-1, target, arrB);
    // console.log(count)
  }

  ans.push(cnt);
}

console.log(ans.join("\n"))


