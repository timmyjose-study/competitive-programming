#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

struct ListNode {
  int val;
  ListNode *next;

  ListNode(int val) : val(val), next(nullptr) {}
};

// O(n) / O(1)
bool has_cycle(ListNode *head) {
  ListNode *slow = head, *fast = head;

  while (fast != nullptr && fast->next != nullptr) {
    slow = slow->next;
    fast = fast->next->next;

    if (slow == fast) {
      return true;
    }
  }

  return false;
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int tt, n, k;
  cin >> tt;

  while (tt--) {
    cin >> n >> k;

    ListNode *head = nullptr, *tail = nullptr;
    for (int i = 1; i <= n; i++) {
      if (head == nullptr) {
        head = tail = new ListNode(i);
      } else {
        tail->next = new ListNode(i);
        tail = tail->next;
      }
    }

    if (k != -1) {
      ListNode *curr = head;
      for (int i = 0; i < k - 1; i++) {
        curr = curr->next;
      }

      tail->next = curr;
    }

    cout << (has_cycle(head) ? "true" : "false") << "\n";
  }

  return 0;
}