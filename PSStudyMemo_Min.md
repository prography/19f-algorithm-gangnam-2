# LeetCode 문제풀이 기록하기
## 민경태(github.com/applebuddy)

<br>
<br> 

## Easy Problem 
### Two Sum_easy
- (주소)  https://leetcode.com/problems/two-sum/

<br>

- 문제 요약:
  - 배열 내 다른 두 개의 값을 더해 target 값이 되면 정답으로 반환하는 문제 

<br>

- 풀이 해설:

  - 1) 이중 for문을 사용한 brute force 방법
    - 효율성 18.24%
~~~ C++
/// MARK: - 이중 for문 사용 통과답안, 18.24%
vector<int> twoSum(vector<int>& nums, int target) {
  vector<int> Ans;
  for(int i=0; i<nums.size()-1; i++) {
      // 비교할 첫번째 값 인덱스
      for(int j=i+1; j<nums.size(); j++) {
          // 비교할 두번째 값 인덱스
          if(nums[i] + nums[j] == target) {
              // 순회 중 비교한 두개의 값 합이 타겟과 일치할 경우 답 제출 후 종료
              Ans = {i,j};
              break;
          }
      }
  }
  return Ans;
}
~~~
  
  <br>
  
  - 2) map 테이블을 활용한 풀이방법 
    - 효율성 64.24% ~ 92.65%
~~~ C++
/// MARK: - unordered_map 사용 통과답안, 64.24% ~ 92.65%
//  * map 사용 시 48%
vector<int> twoSum2(vector<int>& nums, int target) {
  unordered_map<int,int> MP;
  // map에 배열 키(배열값), 값(배열값 인덱스)를 저장
  for(int i=0; i<nums.size(); i++) MP[nums[i]]=i;
  for(int i=0; i<nums.size(); i++) {
      // 동일한 인덱스 값이 아닌 짝 값(target - 배열값)이 존재하는지 map을 검색, 있다면 두개의 인덱스를 반환
      if(MP[target-nums[i]] > 0 && MP[target-nums[i]] != i) {
          return {i, MP[target-nums[i]]};
      }
  }
  return {0,0};
}
~~~

<br>

### Remove duplicates from array_easy
- (주소)  https://leetcode.com/problems/remove-duplicates-from-sorted-array/

<br>

- 문제 요약:
  - 오름차순 배열의 연속값을 제거하여 단일값만 반환하는 문제 

<br>

- 풀이 해설:

  - 벡터 erase() 사용 답안 
    - 효율성 10%
~~~ C++
/// MARK: erase 함수 사용 통과답안, 10%
int removeDuplicates(vector<int>& nums) {
    for(int i=1; i<nums.size(); i++) {
        if(nums[i-1]==nums[i]) {
            nums.erase(nums.begin()+i);
            i--;
        }
        
    }
    return (int)nums.size();
}
~~~

<br>

  - set 사용 답안 
    - 효율성 25%
~~~ C++
/// MARK: - set 사용 통과답안, 25%
int removeDuplicates(vector<int>& nums) {
    set<int> ST;
    for(auto v: nums) ST.insert(v);
    nums.clear();
    for(auto s: ST) nums.push_back(s);
    return nums.size();
}
~~~

<br>
  
  - 서브 벡터 사용 통과답안
    - 효율성 93.43%
~~~ C++
int removeDuplicatesSemiMaster(vector<int>& nums) {
    vector<int> Ans;
    if(nums.size()==0) return 0;
    Ans.push_back(nums.front());
    for(unsigned int i=1; i<nums.size(); i++) {
        if(nums[i-1]!=nums[i]) Ans.push_back(nums[i]);
    }
    nums = Ans;
    return Ans.size();
}
~~~

<br>

### Remove Element_easy

- (주소)  https://leetcode.com/problems/remove-element/

<br>

- 문제 요약:
  - 특정 값, val을 제거한 배열을 반환하는 문제 

<br>

- 풀이 해설:

~~~ C++

/// MARK: find 함수 사용 통과답안 75%
int removeElement(vector<int>& nums, int val) {
    auto pos = nums.begin();
    while(1) {
        auto cur = find(pos, nums.end(), val);
        if(cur != nums.end()) {
            auto dir = cur - nums.begin();
            nums.erase(pos+dir);
        } else break;
    }
    return (int)nums.size();
}

/// MARK: 단순 반복문 사용 통과답안 70%
int removeElement2(vector<int>& nums, int val) {
    for(int i=0; i<nums.size();) {
        if(nums[i]==val) nums.erase(nums.begin()+i);
        else i++;
    }
    return (int)nums.size();
}

/// MARK: - 단순 반복분, 서브 벡터 활용 답안, 100%
int removeElementMaster(vector<int>& nums, int val) {
        vector<int> Ans;
        for(int i=0; i<nums.size(); i++) if(nums[i]!=val) Ans.push_back(nums[i]);
        nums = Ans;
        return (int)nums.size();
    }
    
~~~

<br>

### Search Insert Position_easy
- (주소)  https://leetcode.com/problems/search-insert-position

<br>

- 문제 요약:
  - target 인덱스를 반환, target 없을 시 target 값 이하의 최댓값 다음 인덱스 반환 

<br>

- 풀이 해설:

~~~ C++

#include <vector>
using namespace std;

class SearchInsertPosition {
public:
    
    /// MARK: - 통과 답안, 98.22%
    int searchInsertMaster(vector<int>& nums, int target) {
        int Ans=0;
        for(int i=0; i<nums.size(); i++) {
            if(nums[i]==target) return i;
            else {
                if(nums[i] < target) Ans=i+1;
                else break;
            }
        }
        return Ans;
    }
};
    
~~~

<br>

### Search Insert Position_easy
- (주소)  https://leetcode.com/problems/search-insert-position

<br>

- 문제 요약:
  - target 인덱스를 반환, target 없을 시 target 값 이하의 최댓값 다음 인덱스 반환 

<br>

- 풀이 해설:

~~~ C++

#include <vector>
using namespace std;

class SearchInsertPosition {
public:
    
    /// MARK: - 통과 답안, 98.22%
    int searchInsertMaster(vector<int>& nums, int target) {
        int Ans=0;
        for(int i=0; i<nums.size(); i++) {
            if(nums[i]==target) return i;
            else {
                if(nums[i] < target) Ans=i+1;
                else break;
            }
        }
        return Ans;
    }
};
    
~~~

<br>
<br>

## Medium Problem 

### Rotate Image
- (주소)  https://leetcode.com/problems/rotate-image/

<br>

- 문제 요약:
  - 2차원 배열을 회전한 결과를 반환하는 문제 

<br>

- 풀이 해설:

~~~ C++
/// MARK: - RotateImage
#if 0
#include <iostream>
#include <vector>
using namespace std;

/// MARK: 이중 vector 사용 통과답안, 81.89% / 97.56%

void rotate(vector<vector<int>>& matrix) {
    vector<vector<int>> V(matrix.size(),vector<int>(matrix[0].size(),0));
    for(int i=0; i<matrix.size(); i++) {
        for(int j=0; j<matrix[0].size(); j++) {
            V[i][j] = matrix[matrix.size()-j-1][i];
        }
    }
    matrix = V;
}

int main() {
    int N,M; cin>>N>>M;
    vector<vector<int>> V(N,vector<int>(M,0));
    for(int i=0; i<N; i++)
        for(int j=0; j<M; j++) cin>>V[i][j];
    rotate(V);
    for(int i=0; i<N; i++) {
        for(int j=0; j<M; j++) printf("%d ", V[i][j]);
        printf("\n");
    }
    return 0;
}
#endif
~~~

<br>

### Next Permutation
- (주소)  https://leetcode.com/problems/next-permutation/

<br>

- 문제 요약:
  - 현재 배열 값의 다음 순열 값을 반환하는 문제 

<br>

- 풀이 해설:
~~~ C++
/// MARK: - Next Permutation : Mathematics Algorithm Problem

#include <vector>
#include <algorithm>
using namespace std;

/// MARK: next_permutation() 사용 통과 답안, 98.75% / 81.72%
class NextPermutation {
public:
    void nextPermutation(vector<int>& nums) {
        next_permutation(nums.begin(), nums.end());
    }
};
~~~

<br>

### Container With Most Water
- (주소)  https://leetcode.com/problems/container-with-most-water/

<br>

- 문제 요약:
  - 수조의 최대 수용가능 면접을 구하는 문제 

<br>

- 풀이 해설:
~~~ C++
/// MARK: - Container With Most Water : Range Calculation Algorithm Problem
#include <vector>
#include <iostream>
using namespace std;

/// MARK: - 좌우 비교 알고리즘 활용 통과답안, 65.27% / 71.13%
class ContainerWithMostWater {
public:
    int maxArea(vector<int>& height) {
        int Ans = 0;
        int left = 0;
        int right = (int)height.size()-1;
        while(left<right) {
            int dim = (right-left) * (height[left] > height[right] ? height[right] : height[left]);
            Ans = Ans < dim ? dim : Ans;
            if(height[left] < height[right]) left++;
            else right--;
        }
        return Ans;
    }
};
~~~

<br>
<br>

## Hard Problem 

### First missing positive
- (주소)  https://leetcode.com/problems/first-missing-positive/

<br>

- 문제 요약:
  - 배열 내 없는 양수 최솟값을 반환하는 문제 

<br>

- 풀이 해설:

~~~ C++
/// MARK: - First Missing Postiive : Hard Level Array algorithm Problem
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class FirstMissingPositive {
public:
    /// MARK: vector, sort() 사용 통과답안, 63.96% / 76%
    int firstMissingPositive(vector<int>& nums) {
        // 비교 전 오름차순 정렬
        sort(nums.begin(), nums.end());
        // 배열요소가 없거나, 가장 큰 수가 음수라면 가장 작은 Positive Number인 1을 출력한다.
        if(nums.size()==0 || nums.back()<0) return 1;
        // 양수값 비교에 사용하는 cur 변수
        int cur=0;
        for(int i=0; i<(int)nums.size(); i++) {
            // 음수인 값을 스킵하고 양수값부터 순회한다.
            if(nums[i]<0) continue;
            
            // 현재까지 발견한 정수값+1 보다 현재 순회하는 값이 크면, 그 사이의 공백이 생겼으므로, 공백 값 중 최솟값인 cur+1을 답으로 반환하고 종료
            if(cur+1 < nums[i]) return cur+1;
            else cur = nums[i];
        }
        
        // 만약 전부 순회했는데 답이 안나온 경우 배열 사이 공백값이 없는 것이므로 최댓값+1을 정답으로 제출
        return nums.back()+1;
    }
};
~~~

<br>
<br>
