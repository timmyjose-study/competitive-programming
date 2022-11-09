#include <algorithm>
#include <cmath>
#include <iostream>
#include <limits>
#include <list>
#include <queue>
#include <random>
#include <string>
#include <tuple>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;

int next_random(int low, int high) {
  random_device rd;
  mt19937 engine(rd());
  uniform_int_distribution<int> dist(low, high);

  return dist(engine);
}

// O(n2) / O(n)
vector<tuple<int, int, int>> triplets_with_zero_sum(vector<int> &a) {
  sort(a.begin(), a.end());

  vector<tuple<int, int, int>> res;
  for (int i = 0; i < a.size() - 1; i++) {
    int left = i + 1, right = a.size() - 1;
    while (left < right) {
      int sum = a[i] + a[left] + a[right];

      if (sum == 0) {
        res.push_back(make_tuple<>(a[i], a[left], a[right]));
        left++;
        right--;
      } else if (sum > 0) {
        right--;
      } else {
        left++;
      }
    }
  }

  return res;
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  int tt, n;
  cin >> tt;

  while (tt--) {
    cin >> n;

    vector<int> a(n);
    for (int i = 0; i < n; i++) {
      cin >> a[i];
    }

    auto res = triplets_with_zero_sum(a);
    for (auto r : res) {
      cout << get<0>(r) << " " << get<1>(r) << " " << get<2>(r) << "\n";
    }
  }

  return 0;
}