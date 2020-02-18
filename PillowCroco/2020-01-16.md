### Isomorphic Strings
[https://leetcode.com/problems/isomorphic-strings/](https://leetcode.com/problems/isomorphic-strings/)
string 두개가 '동형사상'인지 판별하는 알고리즘이다. 다음 과정을 통하여 구현한다.
- 처음 들어온 string을 s, 뒤에 들어온 걸 t라고 할 때,
- __matching__: s의 각 character를 t의 character로 mapping
- __rmatching__: t의 각 character를 s의 character로 역 mapping
- s와 t의 모든 character에 대해 mapping이 일대일인지 확인하는 과정

동형사상이려면 f: X->Y인 f(x)가 일대일대응이 되어야 하므로, 다음 두 조건을 만족시켜야 한다.
- 함수의 정의를 만족시켜야 한다. 즉, 모든 x에 대해 f(x)는 유일하다.
- 일대일대응을 만족시켜야 한다. 즉, x~1~=x~2~인 x~1~, x~2~에 대해 f(x~1~)!=f(x~2~)이다.

첫번째 `for`문을 통해 대응시킬 때, i!=j인 i, j에 대해 만일 s[i]에 대응되는 것이 t[i], t[j] 두가지가 있다면 matching[ s[i] ] = t[j]가 최종적으로 기록된다. 이런 경우, 두번째 `for`문을 통해 대응이 제대로 되었는지 확인할 때 False가 나온다. __matching__ 에 대한 검사가 함수의 정의에 관한 검증이고, __rmatching__ 에 대한 검사가 일대일대응에 대한 검증이다.
```c++
class Solution {
public:
    bool isIsomorphic(string s, string t) {
    
	map<char, char> matching, rmatching;
	for (int i = 0; i < s.length(); i++) {
		matching[s[i]] = t[i];
        rmatching[t[i]] = s[i];
	}

	for (int i = 0; i < s.length(); i++) {
		if (matching[s[i]] != t[i]) return false;
        if (rmatching[t[i]] != s[i]) return false;
	}

	return true;    
    }
};
```

---

### Word Pattern

[https://leetcode.com/problems/word-pattern/](https://leetcode.com/problems/word-pattern/)

string과 word들의 묶음이 동형사상인지 판별하는 알고리즘이다. 다음 과정을 통하여 구현한다.
 - 두번째 인자로 들어오는 긴 string을 word 단위로 쪼개 list화 한다.
 - 이것은 `sstream`을 이용하여 구현한다. 
 - 다른 방법도 있으나, 공백으로 구분된 경우 이 방법이 가장 간단하고 직관적이다.
 - 그 이후의 과정은 위의 isomorphic strings의 과정과 완전히 동일하다. 
```c++
#include <map>
#include <string>
#include <sstream>
#include <vector>

class Solution {
public:
    bool wordPattern(string pattern, string str) {
        map<char,string> hash;
        map<string,char> r_hash;
        
        string buffer;
        vector<string> tokens;
        stringstream ss(str);
        while(ss>>buffer)
            tokens.push_back(buffer);
        
        if(pattern.length()!=tokens.size()) return false;
        
        for(int i=0;i<pattern.length();i++){
            hash[pattern[i]]=tokens[i];
            r_hash[tokens[i]]=pattern[i];
        }
        
        for(int i=0;i<pattern.length();i++){
            if(hash[pattern[i]]!=tokens[i]) return false;
            if(r_hash[tokens[i]]!=pattern[i]) return false;
        }
        
      return true;
    }
};
```

---
### Brick Wall

[https://leetcode.com/problems/brick-wall/](https://leetcode.com/problems/brick-wall/)

```c++
// column의 total length다 돌면서 cnt의 min을 뱉어용
// tokenize하고 그 간격마다는 0을 넣고, (int값 -1) 만큼 인덱스엔 1을 넣기
// 이게 map이랑 무슨 상관이지? 일단 돌려보고 시간복잡도를 비교해보자.

// map[acc+pres]++ 이런식으로 하면 되겠구나. map<int,int>이고, key는 n번째 틈이고, 
// value는 n번째 틈으로 흘러갈수있는 라인의 개수. 출력값은 value의 최댓값.. 으로하면
// 벽돌최대개수네. 그러니까 예제에선 4가 나오네.
// 아 최댓값을 구하고, wall의 n_row에서 빼주면 되겠다! 

class Solution {
public:
    int leastBricks(vector<vector<int>>& wall) {
        map<int, int> hash;
        for (int i = 0; i < wall.size(); i++) {
		    int acc = 0;
	    	for (int j = 0; j < wall[i].size() - 1;j++) {
		    	acc += wall[i][j];
                hash[acc]++;
			    //cout<<hash[acc]<<" ";
            }
	    	//cout << endl;
	    }

    	int max = 0;
	    for (pair<int, int> atom : hash) {
		    if (atom.second > max) max = atom.second;
	    }
    	return wall.size() - max;
    }
};

```
