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

---

### 64. Minimum Path Sum

(주소)https://leetcode.com/problems/minimum-path-sum/



#### 문제 요약:

이차원 배열의 형태(m x n)인 grid 이차원 벡터가 주어지는데, 좌상단 지점에서 우하단 지점으로 이동하는데 거쳐가는 점들의 합을 최소로 하는 path의 합을 구하는 문제.

Note: You can only move either down or right at any point in time.

Input:

[

  [1,3,1],
  
  [1,5,1],
  
  [4,2,1]
  
]

Output: 7

Explanation: Because the path 1→3→1→1→1 minimizes the sum.

#### 풀이 해설:
grid 배열의 원소마다 dp값에 경로의 최소합을 누적시켜나간다. (순서는 왼쪽에서 오른쪽으로, 위쪽에서 아래쪽순으로)

이동은 오른쪽, 아래쪽으로만 가능하다하였으므로 dp[i][j]의 값에 영향을 주는 요소들은 [i][j]의 왼쪽, 위쪽인 [i][j-1], [i-1][j] .

단, 맨 위의 행이나 맨 왼쪽의 열 같은 경우는 i-1, j-1 값이 0보다 작아질 수 있으므로 따로 검사.

```c++
class Solution {
public:
    int minPathSum(vector<vector<int>>& grid) {
        int n1=grid.size();     //n1=m , 행의 개수
        int n2=grid[0].size();  //n2=n , 열의 개수
        int dp[n1+1][n2+1];
        
        dp[0][0]=grid[0][0];
        
        for(int i=0;i<n1;i++){
            for(int j=0;j<n2;j++){
                if(i==0 && j==0) continue;  
                if(i-1<0){  //자신의 바로 위쪽이 grid범위 밖이라면 왼쪽만 고려
                    dp[i][j]=dp[i][j-1]+grid[i][j];
                }
                else if(j-1<0){  //자신의 바로 왼쪽이 grid범위 밖이라면 위쪽만 고려
                    dp[i][j]=dp[i-1][j]+grid[i][j];
                }
                else{
                    dp[i][j]=min(dp[i-1][j],dp[i][j-1])+grid[i][j];   //자신의 왼쪽과 위쪽의 dp값 중 minimum을 택해서 더한다
                }
            }
        }
        return dp[n1-1][n2-1];
    }
};
```
---


### 318. Maximum Product of Word Lengths

(주소)https://leetcode.com/problems/maximum-product-of-word-lengths/



#### 문제 요약:
입력으로 주어지는 words 벡터안에서 두 단어의 길이의 곱의 최댓값 구하는 문제.  단, 두 단어는 서로 공통된 알파벳을 가지고 있지 않아야한다

Input: ["abcw","baz","foo","bar","xtfn","abcdef"]   =words vector

Output: 16 

Explanation: The two words can be "abcw", "xtfn".


#### 풀이 해설:
words벡터에서 가능한 두쌍의 경우들을 모두 구해 두 단어가 공통된 알파벳을 가지고 있는지 아닌지 판단 후, 가지고 있지 않으면 길이의 곱을 계산.



* 두 단어가 공통된 알파벳을 가지고 있는지 아닌지 판단하는 방법  :

 	비트연산자를 이용해서 단어들마다 그 단어가 포함하는 알파벳의 자리에 1을 넣는다. 여기서 최종적으로 구한 값은 벡터 v에 저장한다.
 
	만약 두 단어가 공통된 알파벳을 가지고 있지 않다면, 두 단어들의 벡터 v 값을 AND했을 때 0의 값을 가질 것이다. 

	0 이 아니면 조건을 만족하지 못하므로 무시

```c++
class Solution {
public:
    int maxProduct(vector<string>& words) {
        int n=words.size();  //n=단어들의 개수
        int mm=0;            //maximum value
        vector<int> v(n,0);  //Create a vector v of size n with all values as 10.
             
        for(int i=0;i<n;i++){   //words의 단어들마다 부여되는 벡터값
            for(int j=0;j<words[i].size();j++){   
                v[i] |= ( 1<< words[i][j]-'a');   //포함하는 알파벳 자리에 1이 들어감. (OR연산자)
            }
        }
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if((v[i] & v[j])==0){  //(AND연산자), 공통되는 알파벳이 없다면 AND의 결과는 0이다
                    int a=words[i].size()*words[j].size();
                    if(mm<a) mm=a;
                }
            }
        }
        return mm;
    }
};
```
---

