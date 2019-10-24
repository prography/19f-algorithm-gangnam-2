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


### Subsets

(주소)https://leetcode.com/problems/subsets/



#### 문제 요약:
가능한 부분집합들 구하기
(공집합도 포함)


#### 풀이 해설:
DFS 백트래킹 이용

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


