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

