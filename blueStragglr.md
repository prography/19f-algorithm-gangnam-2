### 개인별 문제풀이 기록하기 - 신성환(github.com/blueStragglr)

---



#### [2020카카오공채] 문자열 압축

(https://programmers.co.kr/learn/courses/30/lessons/60057)



#### 문제 요약:

해당 문제는 임의의 string을 임의의 수의 substing으로 분해하여, 반복되는 substring을 압축함으로써 문자열을 짧게 압축하는 최적의 방법을 찾는 문제입니다. 압축은 아래와 같은 방법으로 수행합니다. 

> ababcdcdababcdcd의 경우 문자를 1개 단위로 자르면 전혀 압축되지 않지만, 2개 단위로 잘라서 압축한다면 2ab2cd2ab2cd로 표현할 수 있습니다. 다른 방법으로 8개 단위로 잘라서 압축한다면 2ababcdcd로 표현할 수 있으며, 이때가 가장 짧게 압축하여 표현할 수 있는 방법입니다.



#### 풀이 해설:

```
import math

def solution(s):

    ##### 가장 짧은 단어를 저장할 변수와, 특정 길이의 substring으로 압축한 문자열을 저장할 변수 초기화
    shortest = s
    shortenWord = s
    

    ##### 1부터 string 길이 절반(홀수 길이일 경우 소수점 이하 올림)의 length를 갖는 subsrting으로
    ##### 압축한 결과를 비교하며 최소값을 저장하는 루프
    for i in range(1, math.ceil((len(s)+1)/2)):
    

	    	##### getShortenWord(s,i)는 문자열 s를 길이 i의 substring으로 분해한 뒤 압축한 결과를 반환
        shortenWord = getShortenWord(s,i)           
        

        ##### 압축 결과가 shortest에 저장된 string보다 짧은 경우 shortest를 갱신
        if(len(shortenWord) < len(shortest)):
            shortest=shortenWord
            
    return len(shortest) 
```

​     

    def getShortenWord(originalStr: str, partSize: int) -> str:
    		##### 분해된 조각들을 담을 array 초기화
        partialArr = []
        
        ##### 결과를 저장할 string 초기화
        shortenWord = ''
        
        ##### string을 정해진 크기로 잘라서 저장하는 루프
        ##### 전체 string 길이를 조각의 크기로 나눈 뒤 소숫점 이하 올림
        for i in range(math.ceil(len(originalStr)/partSize)):
            ##### 읽기 편하게 줄을 바꾼 것으로, 실제로는 오작동할 수 있음. (원문은 띄어쓰기 x)
            ##### python의 parsing method를 이용하여 분해하여 저장
            partialArr.append(
            	originalStr[i*partSize:min((i+1)*partSize, len(originalStr))]
            )
    
        ##### buffer에는 직전에 확인한 substring의 정보를 담음
        buffer = ''
        
        ##### count에는 현재 substring이 반복된 횟수를 담음
        count = 1
        
        ##### 분해된 모든 조각에 대해서 압축 프로세스 실행
        for subStr in partialArr:
        
        		##### 이전에 나왔던 substring이 반복된 경우 count만 업데이트 하고 넘어감
            if(buffer == subStr):
                count += 1 
                
            ##### 이전에 나온 substring이 반복되지 않은 경우, 이전까지 반복된 정보를 shortenWord에 저장하고
            ##### 현재 substring에 대한 정보(buffer, count)를 업데이트 
            else:
                if(not(buffer=='')):
                    if(count == 1):
                        count = ''
                    shortenWord += str(count) + buffer
                count = 1
                buffer = subStr   
                
        ##### 마지막 substring은 위 루프에서 저장되지 않으므로 append를 종료하는 과정 수행
        ##### 마지막 substring이 반복된 것이 아닌 경우 substring(buffer)만 추가
        if(count==1):
            shortenWord += buffer
        ##### 반복된 substring인 경우 count와 함께 추가 
        else:
            shortenWord += str(count) + buffer  
            
        return shortenWord

---



#### 문제 이름: Search Insert Position

(https://leetcode.com/problems/search-insert-position/submissions/)



#### 문제 요약:

Sorting 된 int Array와 target(int)이 주어진다. target이 포함되어있다면 target의 위치를 반환하고, 포함되어 있지 않다면 추가 후 sorting했을 때의 위치를 반환하라.



#### 풀이 해설:

Sol 1. Brute Force Algorithm ~ O(n)

```
class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
    		##### index를 저장할 변수 선언
        index = 0
        for i in nums:
        		##### target보다 작은 element의 갯수를 count
            if(i < target):
                index += 1 
        ##### 갯수를 반환
        return index
```

 Sol 2. Binary Search Algorithm ~ O(log n)



---

#### 문제 이름: Max Water Container

(https://leetcode.com/problems/container-with-most-water/)



#### 문제 요약:

양의 정수가 담긴 Array가 주어진다. 해당 Array를 comb모양의 그래프로 그렸을 때, 가장 많은 물을 담을 수 있는 두 개의 값 쌍을 찾아 그 넓이를 반환하라. 

![image-20191017171013947](/Users/blueStragglr/Library/Application Support/typora-user-images/image-20191017171013947.png)





#### 풀이 해설:

Sol 1. Brute Force Algorithm ~ O(n^2)

***TIME LIMIT EXCEEDED!!***

```
class Solution:
    def maxArea(self, height: List[int]) -> int:
    		##### Area의 최대값을 저장할 변수 선언
        maxAreaVal = 0;
        
        ##### Array 두 개로 matrix를 만들어 각각의 값 중 최대값을 탐색
        for i in range(len(height)):
            for j in range(i+1, len(height)):
                maxAreaVal = max(maxAreaVal, (min(height[i], height[j])*(j-i)))
        return maxAreaVal;
```

결과값은 올바르게 계산되었지만 펑하고 터졌습니다. 

![image-20191017170903623](/Users/blueStragglr/Library/Application Support/typora-user-images/image-20191017170903623.png)





 Sol 2. Two Pointer Search(Greedy Algorithm) ~ O(n)

```
class Solution:
    def maxArea(self, height: List[int]) -> int:
    		##### 맨 앞과 맨 뒤에 pointer로 사용할 변수 선언
        frontIndex = 0
        backIndex = len(height) - 1
        
        ##### 최댓값을 저장할 변수와 임시 저장 공간 변수 초기화
        maxVal = 0;
        maxBuffer = 0;
        
        for i in range(len(height)):
        		##### 값을 계산해 봄 
            maxBuffer = min(height[frontIndex], height[backIndex]) 
												*(backIndex - frontIndex)
            ##### 둘 중 더 큰값을 저장
            maxVal = max(maxVal, maxBuffer)
            ##### 앞쪽과 뒷쪽 값 중 크기가 작은 쪽의 index를 변화시킴(앞쪽은 +1, 뒷쪽은 -1)
            ##### *해당 알고리즘의 핵심. 뒤에서 따로 증명.*
            if (height[frontIndex] < height[backIndex]):
                frontIndex += 1
            else: 
                backIndex -= 1
                
        ##### Loop에서 계산된 값들 중 최솟값을 반환
        return maxVal;
                
```



#### CLAIM ~ _앞쪽과 뒷쪽 값 중 크기가 작은 쪽의 index를 변화시키며 탐색_ 하면 올바르게 최댓값을 찾을 수 있는 것이 맞는가?

그림을 이용해 표현 해 보겠습니다. 길이 n의 array가 주어져 있을 때, `frontIndex = i, backIndex = j` 인 상황에서의 탐색은 다음과 같이 나타낼 수 있습니다.

![image-20191017174620378](/Users/blueStragglr/Library/Application Support/typora-user-images/image-20191017174620378.png)

이 경우, i를 변화시키며 탐색하게 됩니다. 이 방법을 통해 올바른 값을 찾을 수 있다는 것을 증명하기 위해서는 ***i를 이동하며 탐색했을 때 최대값 쌍을 누락하지 않는다*** 라는 것을 증명하면 됩니다. 해당 명제는 ***i를 이동하지 않고 탐색할 수 있는 모든 쌍 중에 최대값 쌍이 존재하지 않는다*** 라는 대우명제를 증명함으로써 증명할 수 있습니다. 

위 상황에서 i를 이동하지 않고 탐색하는 쌍은 `(i,i+1), (i,i+2), ... ,(i,j-1)` 입니다. 이 중 임의의 요소 `(i,k)~ where i+1<k<j-1`의 넓이는 `min(h[i], h[k]) * (k-i)` 로 계산할 수 있습니다. 

이 때, `min(h[i], h[k]) < h[i]` 이므로, 임의의 요소에 대해 넓이 최대값은 `h[i](k-i)` 이 되며, 탐색할 쌍의 정의로부터 `k-i<j-i`이므로 ***i를 이동하지 않고 탐색할 수 있는 모든 쌍 중에는 `(i,j)` 쌍의 값보다 큰 값이 존재하지 않는다***는 것을 알 수 있습니다. 

즉, i를 이동하지 않고 탐색할 수 있는 모든 쌍에 의한 값은  `(i,j)` 보다 작으므로, 최대값을 존재하지 않는다는 결론을 얻을 수 있습니다.



두 알고리즘을 행렬을 이용해 비교 해 보면 조금 더 직관적인 이해를 할 수 있습니다. 

![자산 1](/Users/blueStragglr/Screenshots/2x/자산 1.png)

 



---

