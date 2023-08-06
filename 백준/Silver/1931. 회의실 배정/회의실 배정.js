let [num,...input] = require('fs').readFileSync('/dev/stdin').toString().trim().split('\n');

arr = input.map(v => v.split(' ').map(i => Number(i)))
let time = arr.sort((a,b) => a[1] - b[1]||a[0]-b[0])
let end = 0;
let answer = 0;
time.forEach((e) => {
  if(end <=e[0]) {
    answer++;
    end= e[1];
  };
});
console.log(answer);