let fs = require("fs");

let line = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let [hour, minute, second] = line[0].split(" ").map(Number);
let dur = Number(line[1]);

second += dur;

while(second >= 60) {
  minute++;
  second -= 60;
}

while(minute >= 60){
  hour++;
  minute -= 60;
}

while(hour >= 24){
  hour -= 24;
}

console.log(hour + " " + minute + " " + second)