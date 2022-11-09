#include <algorithm>
#include <iostream>
#include <queue>
#include <sstream>
#include <string>
#include <vector>

using namespace std;

struct TreeNode {
  int val;
  TreeNode *left;
  TreeNode *right;

  TreeNode(int val) : val(val), left(nullptr), right(nullptr) {}
};

TreeNode *build_tree(int curr_idx, const vector<string> &nodes) {
  if (curr_idx >= nodes.size() || nodes[curr_idx] == "null") {
    return nullptr;
  }

  TreeNode *node = new TreeNode(stoi(nodes[curr_idx]));
  node->left = build_tree(2 * curr_idx + 1, nodes);
  node->right = build_tree(2 * curr_idx + 2, nodes);

  return node;
}

TreeNode *build_tree(const vector<string> &nodes) {
  return build_tree(0, nodes);
}

// O(n) / O(n)
vector<deque<int>> zigzag_traversal(TreeNode *root) {
  vector<deque<int>> res;

  queue<TreeNode *> q;
  q.push(root);

  bool l2r = true;
  while (!q.empty()) {
    int sz = q.size();

    deque<int> d;
    for (int i = 0; i < sz; i++) {
      TreeNode *node = q.front();
      q.pop();

      if (l2r) {
        d.push_back(node->val);
      } else {
        d.push_front(node->val);
      }

      if (node->left) {
        q.push(node->left);
      }

      if (node->right) {
        q.push(node->right);
      }
    }
    res.push_back(d);

    if (l2r) {
      l2r = false;
    } else {
      l2r = true;
    }
  }

  return res;
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  int tt;
  string s;

  cin >> tt;
  cin.ignore(1, '\n');

  while (tt--) {
    getline(cin, s);

    stringstream ss(s);
    string tmp;
    vector<string> nodes;

    while (getline(ss, tmp, ' ')) {
      nodes.push_back(tmp);
    }

    TreeNode *root = build_tree(nodes);
    auto res = zigzag_traversal(root);
    for (auto r : res) {
      for (int e : r) {
        cout << e << " ";
      }
      cout << "\n";
    }
  }

  return 0;
}