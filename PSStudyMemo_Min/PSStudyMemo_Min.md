# LeetCode 문제풀이 기록하기
## 민경태(github.com/applebuddy)

<br>
<br> 

## Easy Level Problem 
### Two Sum_easy
- (주소) https://leetcode.com/problems/two-sum/

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
<br>

## Medium Level Problem 

<br>

### 4 Sum

<br>

- 문제 요약:
  - 특정 배열 요소 4개를 합한 값이 타겟값일 경우의 해당 4개 배열요소를 출력하는 문제 

<br>

- 풀이 해설: 1) 4중 for문 사용하여 풀이 가능 (brute Force)
~~~ C++
/// MARK: - 4SUM
#include <vector>
#include <set>
using namespace std;

/// MARK: - 4중포문 통과답안, 5.02%, 더 좋은 방법을 찾아봐야 함
class theFourSum {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        if(nums.size()<4) return {};
        set<vector<int>> ST;
        
        sort(nums.begin(), nums.end());
        vector<vector<int>> Ans;
        // 4중 for문을 사용하여 전체 경우의 수에 따른 타겟 합을 구할 수 있다. 
        for(int i=0; i<nums.size()-3; i++) {
            for(int j=i+1; j<nums.size()-2; j++) {
                for(int k=j+1; k<nums.size()-1; k++) {
                    for(int l=k+1; l<nums.size(); l++) {
                        if(nums[i]+nums[j]+nums[k]+nums[l]==target) {
                            vector<int> temp;
                            temp = {nums[i], nums[j], nums[k], nums[l]};
                            ST.insert(temp);
                        }
                    }
                }
            }
        }
        for(auto s: ST) Ans.push_back(s);
        return Ans;
    }
};
~~~

<br>

~~~ swift
/// MARK: - 4중포문 + 예외처리 일부 추가
class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        if(nums.size()<4) return {};
        set<vector<int>> ST;
        
        sort(nums.begin(), nums.end());
        vector<vector<int>> Ans;
        // 4중 for문을 사용하여 전체 경우의 수에 따른 타겟 합을 구할 수 있다. 
        for(int i=0; i<nums.size()-3; i++) {
            if(target>=0 && nums[i]>target) break;
            if(target<0 && nums[i]>=0) break;
            for(int j=i+1; j<nums.size()-2; j++) {
                int twoSum = nums[i] + nums[j];
                if(target>=0 && twoSum>target) break;
                if(target<0 && twoSum>=0) break;
                for(int k=j+1; k<nums.size()-1; k++) {
                    int threeSum = nums[i]+nums[j]+nums[k];
                    if(target>=0 && threeSum>target) break;
                    if(target<0 && threeSum>=0) break;
                    for(int l=k+1; l<nums.size(); l++) {
                        int sum = nums[i]+nums[j]+nums[k]+nums[l];
                        if(sum==target) {
                            vector<int> temp;
                            temp = {nums[i], nums[j], nums[k], nums[l]};
                            ST.insert(temp);
                        } else if((sum>target && target>=0)
                                 || (sum>=0 && target<0)) break;
                    }
                }
            }
        }
        for(auto s: ST) Ans.push_back(s);
        return Ans;
    }
};
~~~

~~~ Swift 
/// MARK: - 다른 사람 모범답안 (예외처리 보완)
class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        int n = nums.size();
        vector<vector<int>>res;
        if(n < 4)   return res;
        sort(nums.begin(), nums.end());
        for(int i = 0; i < n-3; i++){
            if(i > 0 && nums[i] == nums[i-1])   continue;
            if(nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target)    break;
            if(nums[i] + nums[n-1] + nums[n-2] + nums[n-3] < target)    continue;
            for(int j = i + 1; j < n-2; j++){
                if(j > i+1 && nums[j] == nums[j-1])   continue;
                if(nums[i] + nums[j] + nums[j+1] + nums[j+2] > target)    break;
                if(nums[i] + nums[j] + nums[n-1] + nums[n-2] < target)    continue;
                int left = j+1, right = n-1;
                while(left < right){
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum < target)
                        left++;
                    else if(sum > target)
                        right--;
                    else{
                        res.push_back({nums[i], nums[j], nums[left], nums[right]});
                        left++, right--;
                        while(left < right && res.back()[2] == nums[left]) left++;
                        while(left < right && res.back()[3] == nums[right]) right--;
                    }
                }
            }
        }
        return res;
    }
};
~~~

<br>

### Sub Sets

<br>

- 문제 요약:
  - 특정 배열 요소의 전체 부분집합을 출력하는 문제 

<br>

- 풀이 해설: 재귀함수를 통해 풀이할 수 있다. + 비트마스크 방법 존재 
~~~ C++
/// MARK: - SubSets
//  - 재귀적 용법 활용 부분집합 출력 문제 통과답안
#include <vector>
#include <algorithm>
using namespace std;

class SubSets {
public:
    void go(int count, vector<int> C, vector<int> nums, vector<vector<int>> &ANS) {
        // 부분집합 가능 요소를 모드 훑었을 때마다 부분집합의 경우의 수를 출력하고 해당 재귀함수를 순차적으로 종료한다.
        if(count==C.size()) {
            vector<int> Ans;
            for(int i=0; i<C.size(); i++) {
                if(C[i]==1) Ans.push_back(nums[i]);
            }
            ANS.push_back(Ans);
            return;
        }
        
        C[count]=0;
        // 부분집합에서 해당 인덱스 요소를 사용 안할 경우,
        go(count+1,C,nums,ANS);
        C[count]=1;
        // 부분집합에서 해당 인덱스 요소를 사용 할 경우를 분리하여 경우의 수를 출력한다.
        go(count+1,C,nums,ANS);
    }
    
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> Ans;
        vector<int> C(nums.size(),0);
        go(0,C,nums,Ans);
        return Ans;
    }
};
~~~

<br>


### Longest Palindromic Substring
- 가장 긴 펠린드롬 부분문자열 문제 
- (주소)  https://leetcode.com/problems/longest-palindromic-substring/submissions/

<br>

- 문제 요약:
  - 가장 긴 펠린드롬 문자열(앞뒤 대칭 문자열)을 구해서 출력하는 문제 

<br>

- 풀이 해설: Manacher's Algorithm을 사용해서 풀이할 수 있다. 
- Manacher's Algorithm(마나커 알고리즘)은 A[i]가 존재할때, i-A[i] ~ A[i]+i 범위의 팰린드롬문자열이 존재하면, i-A[i]-1 ~ A[i]+i+1 의 팰린드롬 문자열은 없음을 이용하는 O(N) 복잡도의 특수 알고리즘 기법이다.
~~~ C++
/// MARK: - 가장 긴 펠린드롬 부분 문자열 문제 : Manacher's Algorithm Problem

#include <iostream>
#include <vector>
using namespace std;

vector<int> A(1000000,0);
class LongestPalindromicSubstring {
public:

    /// MARK: manacherAlgorithm
    string manacherAlgorithm(string S) {
        // r : 가장 큰 팰린드롬 문자열의 우측 범위를 저장 -> 해당 값이 줄어드는 경우는 없으므로 S 문자열을 순회하며 O(N)의 복잡도를 가진 후 종료
        // p : 가장 큰 팰린드롬 문자열의 중심 축을 저장
        int r=0,p=0;
        int sum=0,mxIdx=0;
        string Ans = "";
        for(int i=0; i<S.length(); i++) {
            // 현재 식별된 펠린드롬 문자열 범위 안에 있으면, 펠린드롬 범위 내 대칭점의 값과 '최근 펠린드롬 우측범위(r)-i' 값의 최솟값으로 초기화
            // 현재 식별된 펠린드롬 문자열 범위 밖이라면, 0으로 초기화
        for(int i=0; i<S.length(); i++) {
            if(i <= r) A[i] = min(A[2*p-i],r-i);
            else A[i] = 0;
            
            /// 펠린드롬 문자열 여지가 있는 인덱스를 기준으로 펠린드롬 가능 범위를 지정한다. 
            //  0 이상으로 초기화 된 경우에도 그 이상의 바깥 문자열을 비교하며, 더욱 긴 펠린드롬 문자열이 식별 시 A[i]를 증가시킨다. 
            while(i-A[i]-1 >= 0 && i+A[i]+1 <S.length() && S[i-A[i]-1]==S[i+A[i]+1]) A[i]++;
            
            /// 더 큰 팰린드롬 문자열 우측 범위가 식별되면 갱신 후, 그 중심점(P) 또한 갱신한다.
            if(r < i+A[i]) {
                r = i+A[i];
                p = i;
            }
            
            // 가장 긴 팰린드롬 문자열 길이 밎 그 중심점 인덱스를 저장한다. 
            if(sum < A[i]) {
                sum = A[i];
                mxIdx = i;
            }
        }

        for(int i=max(mxIdx-A[mxIdx],0); i<min(mxIdx+A[mxIdx],(int)S.length()-1); i++) {
            Ans += S[i];
        }

        return Ans;
    }
    
    string longestPalindrome(string s) {
        
        // "bb" 같은 짝수 개의 펠린드롬 부분문자열까지 식별하기 위해 문자열 사이에 '@' 문자를 임의로 끼워서 manacher's Algorithm을 수행한다.
        string S = "@";
        for(int i=0; i<s.length(); i++) {
            S+=s[i];
            S+='@';
        }

        string ANS = manacherAlgorithm(S);
        string Ans = "";
        
        /// '@' 문자를 제외한 가장 긴 펠린드롬 부분문자열을 반환한다.
        for(auto s: ANS) {
            if(s!='@') Ans+=s;
        }
        return Ans;
    }
};
~~~

<br>

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

## Hard Level Problem 

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


