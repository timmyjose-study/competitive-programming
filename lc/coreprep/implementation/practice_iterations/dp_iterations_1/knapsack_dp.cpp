#include <algorithm>
#include <cmath>
#include <iostream>
#include <limits>
#include <list>
#include <queue>
#include <random>
#include <string>
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

// o(n) / O(n)
void print_selected_items(const vector<int> &profits,
                          const vector<int> &weights, int capacity,
                          const vector<vector<int>> &dp) {
  vector<int> items;

  int profit = dp[weights.size() - 1][capacity];
  for (int i = weights.size() - 1; i > 0; i--) {
    if (dp[i][capacity] != dp[i - 1][capacity]) {
      items.push_back(weights[i]);
      profit -= profits[i];
      capacity -= weights[i];
    }
  }

  if (profit != 0) {
    items.push_back(weights[0]);
  }

  for (int item : items) {
    cout << item << " ";
  }
  cout << "\n";
}

// o(nc) / o(nc)
void knapsack(const vector<int> &profits, const vector<int> &weights,
              int capacity) {
  if (profits.empty() || capacity == 0) {
    cout << 0 << "\n";
    return;
  }

  vector<vector<int>> dp(weights.size(), vector<int>(capacity + 1, 0));

  for (int i = 0; i < weights.size(); i++) {
    dp[i][0] = 0;
  }

  for (int c = 0; c <= capacity; c++) {
    if (weights[0] <= c) {
      dp[0][c] = profits[0];
    }
  }

  for (int i = 1; i < weights.size(); i++) {
    for (int c = 1; c <= capacity; c++) {
      dp[i][c] = dp[i - 1][c];

      if (weights[i] <= c) {
        dp[i][c] = max(dp[i][c], profits[i] + dp[i - 1][c - weights[i]]);
      }
    }
  }

  cout << dp[weights.size() - 1][capacity] << "\n";
  print_selected_items(profits, weights, capacity, dp);
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  int tt, n, c;
  cin >> tt;

  while (tt--) {
    cin >> n >> c;

    vector<int> profits(n);
    for (int i = 0; i < n; i++) {
      cin >> profits[i];
    }

    vector<int> weights(n);
    for (int i = 0; i < n; i++) {
      cin >> weights[i];
    }

    knapsack(profits, weights, c);
  }

  return 0;
}