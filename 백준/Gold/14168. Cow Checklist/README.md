# [Gold I] Cow Checklist - 14168 

[문제 링크](https://www.acmicpc.net/problem/14168) 

### 성능 요약

메모리: 49264 KB, 시간: 212 ms

### 분류

다이나믹 프로그래밍

### 제출 일자

2024년 6월 9일 22:56:53

### 문제 설명

<p>Every day, Farmer John walks through his pasture to check on the well-being of each of his cows. On his farm he has two breeds of cows, Holsteins and Guernseys. His H Holsteins are conveniently numbered 1…H, and his G Guernseys are conveniently numbered 1…G (1 ≤ H ≤ 1000,1 ≤ G ≤ 1000). Each cow is located at a point in the 2D plane (not necessarily distinct).</p>

<p>Farmer John starts his tour at Holstein 1, and ends at Holstein H. He wants to visit each cow along the way, and for convenience in maintaining his checklist of cows visited so far, he wants to visit the Holsteins and Guernseys in the order in which they are numbered. In the sequence of all H+G cows he visits, the Holsteins numbered 1…H should appear as a (not necessarily contiguous) subsequence, and likewise for the Guernseys. Otherwise stated, the sequence of all H+G cows should be formed by interleaving the list of Holsteins numbered 1…H with the list of Guernseys numbered 1…G.</p>

<p>When FJ moves from one cow to another cow traveling a distance of D, he expends D<sup>2</sup> energy. Please help him determine the minimum amount of energy required to visit all his cows according to a tour as described above.</p>

### 입력 

 <p>The first line of input contains H and G, separated by a space.</p>

<p>The next H lines contain the x and y coordinates of the H Holsteins, and the next G lines after that contain coordinates of the Guernseys. Each coordinate is an integer in the range 0…1000.</p>

### 출력 

 <p>Write a single line of output, giving the minimum energy required for FJ's tour of all the cows.</p>

