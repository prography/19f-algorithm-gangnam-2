### 개인별 문제풀이 기록하기 - 추지철(github.com/JC-Choo)

---

#### 첫번째 문제 : Remove Element

(주소) https://leetcode.com/problems/remove-element/



#### 문제 요약:
> 1. "nums"라는 array와 "val"라는 value가 주어짐 
> 2. 주어진 val값이 nums에 포함되어 있다면, 삭제하고 아니면 패스
> 3. 삭제된 값을 제외한 nums의 length를 구하면서 남은 nums의 값을 print해보는 문제


#### 풀이 해설:

---
https://github.com/JC-Choo/prography_algorithm/blob/master/app/src/test/java/kr/dev/chu/algorithm/ExampleUnitTest.kt
첫번째 fun 참고


---

---

#### 두번째 문제 : Next Permutation

(주소) https://leetcode.com/problems/next-permutation/



#### 문제 요약:
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

> 1. "nums"라는 array가 주어짐 
> 2. 그 array에서 가장 작은 값부터 오름차순으로 구현
> 3. 주어진 값이 가장 작은 값이었다면, 그 다음 값을 나타내주면 된다.
예를 들어, [1, 2, 3]일 경우 가장 작은 값인 123 이니 그 다음 작은 값인 132를 나타내주면 됨
다른 예로, [3, 1, 2]일 경우 가장 작은 값인 123 을 나타내주면 됨


#### 풀이 해설:

---
https://github.com/JC-Choo/prography_algorithm/blob/master/app/src/test/java/kr/dev/chu/algorithm/ExampleUnitTest.kt
첫번째 fun 참고


---
---


#### 191024_ 문제 : 3sum

(주소) https://leetcode.com/problems/3sum/submissions/

Runtime: 376 ms, faster than 86.64% of Kotlin online submissions for 3Sum.
Memory Usage: 45.9 MB, less than 100.00% of Kotlin online submissions for 3Sum.

#### 문제 요약:
> 1. n개의 인트값들이 들어가있는 nums array가 주어진다.
> 2. 그 중 3개(a, b, c)를 골라 합한 값이 0이어야 한다.
> 3. 합이 0되는 어레이를 찾아라.


#### 풀이 해설:

---
https://github.com/JC-Choo/prography_algorithm/blob/master/app/src/test/java/kr/dev/chu/algorithm/ExampleUnitTest.kt
3sum class 코드 참고
추가 해설을 하자면, 우선 sort를 통해 순서대로 array를 정렬한 뒤, 첫번째 값과 두번째 값, 마지막 값을 더한 합이 "0" 이 되면 List<List<Int>>에 추가해주고, 아닐 경우 pass해주면 되는 문제.


---
---


#### 191024_ 문제 : First Missing Positive

(주소) https://leetcode.com/problems/first-missing-positive/submissions/

Runtime: 184 ms, faster than 30.00% of Kotlin online submissions for First Missing Positive.
Memory Usage: 34 MB, less than 100.00% of Kotlin online submissions for First Missing Positive.


#### 문제 요약:
Given an unsorted integer array, find the smallest missing positive integer.
> 1. n개의 인트값들이 들어가있는 nums array가 주어진다.
> 2. 어레이 중에 없는 가장 작은 정수(중에서 양수)를 선택하면 된다.


#### 풀이 해설:

---
https://github.com/JC-Choo/prography_algorithm/blob/master/app/src/test/java/kr/dev/chu/algorithm/ExampleUnitTest.kt
First Missing Positive 코드 참고
추가 해설을 하자면, 우선 sort를 통해 순서대로 array를 정렬한 뒤, 자연수 1부터 비교를 통해 조건에 맞지 않으면 return해주면 끝...


---

---
