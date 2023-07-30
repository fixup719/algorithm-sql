let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let N = Number(line[0]);
let dists = line[1].split(" ").map(Number);
let prices = line[2].split(" ").map(Number);
let minCity = {
  city: undefined,
  price: 100001,
}
prices.map((el, idx) => {
  if(idx !== prices.length-1){
    if(minCity.price > el){
      minCity.city = idx;
      minCity.price = el;
    }
  }
})



let answer = 0;
for(let i=0; i<dists.length; i++){
  if(i===minCity.city){
    // 가장 적은 기름값의 도시 => 여기서 남은 거리만큼 충전
    let remain = 0;
    for(let j=i; j<dists.length; j++){
        remain += dists[j];
    }
    answer += remain*minCity.price;
    break;
  }else{
    // 가장 적은 기름값 도시가 아니라면 => 다음 도시 거리까지만 충전
    answer += dists[i]*prices[i]
  }
}

console.log(answer)
