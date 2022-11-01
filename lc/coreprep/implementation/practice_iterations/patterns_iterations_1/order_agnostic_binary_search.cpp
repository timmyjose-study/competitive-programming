#include <algorithm>
#include <iostream>
#include <list>
#include <queue>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

using namespace std;

// O(logn) / O(1)
int binary_search(const vector<int> &a, int low, int high, int elem) {
  if (low == high) {
    return a[low] == elem ? low : -1;
  }

  bool is_inc = a[low] < a[high];

  while (low <= high) {
    int mid = low + (high - low) / 2;

    if (a[mid] == elem) {
      return mid;
    } else if (a[mid] < elem) {
      if (is_inc) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    } else {
      if (is_inc) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
  }

  return -1;
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int tt, n, k;
  cin >> tt;

  while (tt--) {
    cin >> n >> k;

    vector<int> a(n);
    for (int i = 0; i < n; i++) {
      cin >> a[i];
    }

    cout << binary_search(a, 0, n - 1, k) << "\n";
  }

  return 0;
}