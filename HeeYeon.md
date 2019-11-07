### 개인별 문제풀이 기록하기 - 황희연(github.com/gmldus)

---



### Remove duplicates from array

(주소)https://leetcode.com/problems/remove-duplicates-from-sorted-array/



#### 문제 요약:
정렬되어있는 입력배열 nums에서 중복되는 원소들을 제거하는 문제.

(in-place로 구현해야함. 추가 배열 사용X)

ex. 입력 nums = [0,0,1,1,1,2,2,3,3,4] 이면, nums는 [0,1,2,3,4]로 수정되어야하고 원소의 개수인 5를 리턴.



#### 풀이 해설:
중복되지 않는 숫자들을 swap을 통해 nums의 앞부분으로 모아서 뒷부분은 버린다.

```c++
class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        int n=nums.size();
        if(n==0 || n==1) return n;
        int a=nums[0];
        int p=1;
        for(int i=1;i<n;i++){
            if(nums[i]!=a){
                a=nums[i];
                swap(nums[i],nums[p]);
                p++;
            }
        }
        for(int i=p;i<n;i++){
            nums.pop_back();
        }
        return p;
    }
};
```
---

### Rotate Image

(주소)https://leetcode.com/problems/rotate-image/



#### 문제 요약:
주어진 2차원 배열을 시계방향으로 90도 돌리는 문제.

(in-place로 구현해야함. 다른 2차원 배열 사용 X)




#### 풀이 해설:
입력배열에서 행열번호가 같은 원소들을 축으로 대칭시키면 정답으로 나와야하는 2차원배열에서 좌우반전 시킨 결과와 동일.

```c++
class Solution {
public:
    void rotate(vector<vector<int>>& matrix) {
        int n=matrix.size();
        if (n == 1) return; 
        for (int i = 0; i < n; ++i) {
            for (int j = i+1; j < n; ++j) {
                swap(matrix[i][j], matrix[j][i]);
            }
        }
        for (int i = 0; i < n; ++i) {
            reverse(matrix[i].begin(), matrix[i].end());
        }
    }
};
```
---


### 78. Subsets

(주소)https://leetcode.com/problems/subsets/



#### 문제 요약:
가능한 부분집합들 구하기
(공집합도 포함)


#### 풀이 해설:
DFS 백트래킹 이용

입력 배열이 1,2,3 이라면,

1  ->  1,2  ->  1,2,3  ->  1,3  ->  2  ->  2,3  -> 3  순으로 answer에 push.

```c++
class Solution {
public:
    vector<int> ele;
    vector<vector<int>> answer;
    int n;
    void dfs(int x, vector<int>& nums){
        for(int j=x+1;j<n;j++){
            ele.push_back(nums[j]);
            answer.push_back(ele);
            dfs(j, nums);
            ele.pop_back();
        }
    }
    vector<vector<int>> subsets(vector<int>& nums) {
        n=nums.size();
        answer.push_back(ele);  //공집합
        
        for(int i=0;i<n;i++){
            ele.push_back(nums[i]);
            answer.push_back(ele);
            dfs(i, nums);
            ele.pop_back();
        }
        return answer;
    }
};
```
---


### 228. Summary Ranges

(주소)https://leetcode.com/problems/summary-ranges/



#### 문제 요약:
Input:  [0,1,2,4,5,7]

Output: ["0->2","4->5","7"]


Input:  [0,2,3,4,6,8,9]

Output: ["0","2->4","6","8->9"]

연속되는 구간이 존재하는 경우는 구간의 앞과 끝을 출력, 구간이 아닌 숫자는 해당 숫자만 출력

#### 풀이 해설:
연속되는 구간의 숫자들을 벡터 v에 push해나가고 

만약 그다음 숫자가 연속되는 숫자가 아닌경우는 벡터 v의 맨앞과 뒤의 원소를 벡터 res에 push 하고, v는 clear한 뒤 새로 시작되는 수(nums[i])를 push.

```c++
class Solution {
public:
    vector<string> summaryRanges(vector<int>& nums) {
        vector<string> res;
        vector<int> v;
        int n=nums.size();
        
        if(n==0) return res;  //원소가 없으면 그냥리턴
        else if(n==1){        //원소가 1개면 해당 숫자만 리턴
            res.push_back(to_string(nums[0]));
            return res;
        }
        else{
            v.push_back(nums[0]);
            for(int i=1;i<n;i++){
                if(nums[i]==nums[i-1]+1){
                    v.push_back(nums[i]);
                }
                else{
                    if(v.size()>1) 
                        res.push_back(to_string(v[0])+"->"+to_string(v[v.size()-1]));
                    else res.push_back(to_string(v[0]));
                    v.clear();
                    v.push_back(nums[i]);
                }
            }
            if(v.size()>0){ //벡터v에 원소가 남아있다면
                if(v.size()>1) 
                        res.push_back(to_string(v[0])+"->"+to_string(v[v.size()-1]));
                else res.push_back(to_string(v[0]));
            }
            return res;
        }
    }
};
```
---


### 39. Combination Sum

(주소)https://leetcode.com/problems/combination-sum/



#### 문제 요약:
target이 주어지고, 원소들을 합해서 target이 되는 모든 집합들 구하기. 중복 허용.

Input: candidates = [2,3,5],  target = 8,

A solution set is:

[ [2,2,2,2], [2,3,3],[3,5] ]



#### 풀이 해설:
sum에 값을 더해나가면서 target이 될 때 벡터에 push + 백트래킹

candidates를 정렬했을 때, 이미 이때까지 합한 값(sum)이 target보다 크다면 자신의 인덱스부터 그 후 인덱스들의 값들을 더해도 더 커지기만 하므로 sum이 target보다 작을 때만 dfs함수를 수행.

```c++
class Solution {
public:
    int sum=0;
    int n;
    vector<vector<int>> answer;
    vector<int> ele;
    
    void dfs(vector<int>& candidates, int x, int target){
        if (sum < target) { 
        //이미 sum이 target보다 큰 경우, candidates는 정렬되어있으므로 더 더해나가도 커지기만할뿐 -> 아예 수행 X
            for (int j = x; j < n; j++) {
                ele.push_back(candidates[j]);
                sum += candidates[j];
                
                if (sum == target) {
                    answer.push_back(ele);
                    sum -= candidates[j];
                    ele.pop_back();
                    return;
                }
                if (sum > target) {
                    sum -= candidates[j];
                    ele.pop_back();
                    return;
                }
                
                dfs(candidates, j, target);
                sum -= candidates[j];
                ele.pop_back();
            }
	    }
    }
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        
        sort(candidates.begin(),candidates.end()); //정렬
        n=candidates.size();
        sum=0; 
        
        for(int i=0;i<n;i++){
            ele.push_back(candidates[i]);
            sum += candidates[i];
            
            if (sum == target) {
                answer.push_back(ele);
                ele.pop_back();
                continue;
            }
            
            dfs(candidates, i, target);
            sum -= candidates[i];
            ele.pop_back();
        }
        return answer;
    }
};

```
---

### 5. Longest Palindromic Substring

(주소)https://leetcode.com/problems/longest-palindromic-substring/



#### 문제 요약:
주어진 문자열에서 가장 긴 palindrom인 문자열을 찾는다.



#### 풀이 해설:
	
	 if(ss[i]==ss[j] && dp[i+1][j-1]==true){
	 
	    dp[i][j]=true;
	    
	}

```c++
class Solution {
public:
    string longestPalindrome(string s) {
        string answer="";
        int max=0;
        int a,b;
        int n=s.size();
        bool dp[n+1][n+1];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                dp[i][j]=false;    //가능한 쌍들의 dp값 초기화
            }
        }
        
        if(n==0 || n==1) return s;
        else if(n==2){
            if(s[0]==s[1]) return s;
            else {
                answer.push_back(s[0]);
                return answer;
            }
        }
	
	//dp[i][j]==true는 i부터 j인덱스까지의 문자열이 palindrom이라는 뜻
	
        for(int i=0;i<n;i++){
            dp[i][i]=true;    //하나의 단어는 palindrom
            
            if(i<n-1 && s[i]==s[i+1]){   //2개짜리 쌍들에서도 palindrom탐색
                dp[i][i+1]=true; 
            }
        }
        
        for(int i=n-3;i>=0;i--){
            for(int j=i+2;j<n;j++){  //연속 2개짜리 쌍들은 위에서 이미 비교 -> j = i+1이 아닌 i+2 부터 시작
            
                if(j-i>=2 && dp[i+1][j-1]==true){
                    if(s[i]==s[j]){
                        dp[i][j]=true;
                    }
                }
            }
        }
        
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                if(dp[i][j]==true){ //palindrom인 문자열들 중에서 가장 긴 문자열 찾기
		                    
                    if(j-i+1>max) {
                        max=j-i+1;
                        a=i;
                        b=j;
                    }
                }
            }
        }
        for(int i=a;i<=b;i++){
            answer.push_back(s[i]);
        }
        return answer;
        
    }
};
```
---



### 1222. Queens That Can Attack the King

(주소)https://leetcode.com/problems/queens-that-can-attack-the-king/



#### 문제 요약:
King을 공격할 수 있는 Q들의 위치값들을 리턴.

공격은 같은 행, 열, 대각선상에 있는 Q들이 할 수 있다. 단 다른 Q가 K에 더 가까이 위치해있으면 can't attack.


#### 풀이 해설:

K의 위치 (a , b)로부터 총 8방향 ( 위,아래,왼쪽,오른쪽, 대각선 4방향)으로 위치를 이동해나가며 Q값을 가진 위치를 만나면 answer에 추가한다.

```c++
class Solution {
public:
    int dir[8][2]={{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
    bool isQueen[10][10];
    int a,b;
    vector<vector<int>> v;
    vector<int>vv;
    
    vector<vector<int>> queensAttacktheKing(vector<vector<int>>& queens, vector<int>& king) {
        int n=queens.size();
        for(int i=0;i<n;i++){
            isQueen[queens[i][0]][queens[i][1]]=true;
        }
        a=king[0];
        b=king[1];
        
        for(int i=0;i<8;i++){
            int dx=a,dy=b;   //'K'가 항상 출발점
            while(1){
                dx+=dir[i][0];
                dy+=dir[i][1];
                if(dx>=0 && dx<8 && dy>=0 && dy<8 && isQueen[dx][dy]==true){ //'Q'가 있는 자리 발견 즉시 push,break
                    vv.push_back(dx);
                    vv.push_back(dy);
                    v.push_back(vv);
                    vv.clear();
                    break;
                }
                else if(dx<0 || dx>=8 || dy<0 || dy>=8){   //8x8 chess board의 범위를 벗어나면 탐색진행 중단
                    break;
                }
            }
        }
        return v;
    }
};
```
---


### 946. Validate Stack Sequences

(주소)https://leetcode.com/problems/validate-stack-sequences/



#### 문제 요약:
pushed 배열의 원소들을 차례대로 push, pop 해서 popped 배열과 동일한 결과를 낼 수 있는지 판별.


#### 풀이 해설:

popped 배열의 원소는 현재 스택의 맨 위 원소와 같거나, 아직 스택에 추가되지 않은 pushed 배열의 원소와 같아야 (push 한뒤 pop하면 되므로) pop 될 수 있다.

```c++
class Solution {
public:
    bool validateStackSequences(vector<int>& pushed, vector<int>& popped) {
        int n1=pushed.size();
        int n2=popped.size();
        vector<int> t;
        stack<int>s;

        for(int i=0, j=0; i < n1 ;i++){
            
            s.push(pushed[i]);
        
            while(s.size()>0 && s.top()==popped[j]){
                s.pop();
                j++;
            }
        }
        if(s.size()==0) return true;
        else return false;
        
    }
};
```
---

---

### Priority Queue 



#### Priority Queue란?

- Queue는 들어간 순서대로 데이터를 뽑아낼 수 있는 자료구조

- Priority Queue는 들어간 순서와는 상관 없이, 우선순위라는 값을 도입하여 우선순위가 높은 데이터를 먼저 뽑아낼 수 있는 자료구조, max heap을 이용하여 구현하는 것이 일반적.




#### Priority Queue 구현 2가지 함수 : 

- insert(A[], x) :  배열 A에 새로운 원소를 넣되, max heap 특성을 유지. 

- extractMax(A[]) : 배열 A에서 최댓값을 반환하지만, 최댓값을 제거하고 max heap 특성을 유지하기 위하여 재배열. 




#### insert(A[], x) 



```c++
Function insert(a[], x){
	a.heapSize = a.heapSize + 1;
	a[a.heapSize − 1] = x;
	i = a.heapSize - 1;
	while( i>0 && a[(i-1)/2]< a[i]){
		Exchange a[(i-1)/2] with a[i];
		i=(i-1)/2;
	}
}
 ```
 
 #### extractMax(a[])
 
 
 ```c++
Function extractMax(a[]){
	max = a[0];
	a[0] = a[a.heapSize - 1];
	a.heapSize = a.heapSize - 1;
	maxHeapify(a,0);
	i = a.heapSize - 1;
	return max;
}

Function maxHeapify(a[],i){
	left=2i+1;   //왼쪽 자식 노드
	right=2i+2;  //오른쪽 자식 노드
	if(left<a.heapSize && a[i]<a[left])  largest=left;
	else largest=i;
	if(right<a.heapSize && a[largest]<a[right]) largest=right;
	if(largest != i){
		Exchange a[i] with a[largest];
		maxHeapify(a,largest);
	}
}
		

```
---
