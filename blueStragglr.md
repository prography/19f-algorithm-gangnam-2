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



#### 문제 이름

(주소)



#### 문제 요약:



#### 풀이 해설:

---

