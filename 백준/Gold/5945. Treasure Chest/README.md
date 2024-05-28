# [Gold III] Treasure Chest - 5945 

[문제 링크](https://www.acmicpc.net/problem/5945) 

### 성능 요약

메모리: 210832 KB, 시간: 400 ms

### 분류

다이나믹 프로그래밍, 게임 이론

### 제출 일자

2024년 5월 28일 16:14:31

### 문제 설명

<p>Bessie and Bonnie have found a treasure chest full of marvelous gold coins! Being cows, though, they can't just walk into a store and buy stuff, so instead they decide to have some fun with the coins.</p>

<p>The N (1 <= N <= 5,000) coins, each with some value C_i (1 <= C_i <= 5,000) are placed in a straight line. Bessie and Bonnie take turns, and for each cow's turn, she takes exactly one coin off of either the left end or the right end of the line. The game ends when there are no coins left.</p>

<p>Bessie and Bonnie are each trying to get as much wealth as possible for themselves. Bessie goes first. Help her figure out the maximum value she can win, assuming that both cows play optimally.</p>

<p>Consider a game in which four coins are lined up with these values:</p>

<pre>            30  25  10  35</pre>

<p>Consider this game sequence:</p>

<pre>                           Bessie    Bonnie       New Coin
Player   Side   CoinValue   Total     Total         Line
Bessie   Right     35        35         0       30  25  10
Bonnie   Left      30        35        30         25  10
Bessie   Left      25        60        30           10
Bonnie   Right     10        60        40           --</pre>

<p>This is the best game Bessie can play.</p>

### 입력 

 <ul>
	<li>Line 1: A single integer: N</li>
	<li>Lines 2..N+1: Line i+1 contains a single integer: C_i</li>
</ul>

<p> </p>

### 출력 

 <ul>
	<li>Line 1: A single integer, which is the greatest total value Bessie can win if both cows play optimally.</li>
</ul>

<p> </p>

