let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let [N, M] = line[0].split(" ").map(Number);


let A = line.slice(1, 1+N).map(el=>el.split("").map(el => Number(el)));
let B = line.slice(1+N).map(el=>el.split("").map(el => Number(el)));;

let check = new Array(N);
for(let i=0; i<N; i++){
  check[i] = new Array();
}

for(let i=0; i<N; i++){
  for(let j=0; j<M; j++){
    if(A[i][j] !== B[i][j]) check[i].push(false);
    else  check[i].push(true);
  }
}


if(N < 3 || M < 3){
  // 3*3 보다 작은 행렬일 경우 
  for(let i=0; i<N; i++){
    for(let j=0; j<M; j++){
      if(!check[i][j]){
        // 다른 경우
        console.log(-1);
        return 0;
      }
    }
  }

  console.log(0)
}else{

  let cnt = 0;
  // 연산 가능한 경우
  for(let i=0; i<N; i++){
    for(let j=0; j<M; j++){
      if(!check[i][j]){
        // 다른 경우
        if( i <= N-3 && j <= M-3){
          // 범위 안에 존재 하는 경우
          for(let l=i; l<i+3; l++){
            for(let k=j; k<j+3; k++){
              check[l][k] = !check[l][k];
            }
          }
          cnt++;
        }else{
          // 범위 밖인 경우
          console.log(-1);
          return 0; 
        }
      }
    }
  }

  console.log(cnt)
}