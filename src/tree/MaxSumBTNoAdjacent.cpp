#include <bits/stdc++.h>
using namespace std;

struct node{
    int data;
    struct node* left;
    struct node* right;
};

int sumOfGrandChildren(node* node,  map<struct node*, int>& mp){
    int sum = 0;
    if(node->left){
        sum += getMaxSumUtil(node->left->left, mp) + getMaxSumUtil(node->left->right, mp);
    }

    if(node->right){
        sum += getMaxSumUtil(node->right->left, mp) + getMaxSumUtil(node->right->right, mp);
    }
    return sum;
}

int getMaxSumUtil(node * node, map<struct node*, int>& mp){
    if(node == NULL)
        return 0;

    if(mp.find(node) != mp.end()){
        return mp[node];
    }

    int incl = node->data + sumOfGrandChildren(node, mp);
    int excl = getMaxSumUtil(node->left, mp) + getMaxSumUtil(node->right, mp);
    mp[node] = max(incl, excl);
    return mp[node];
}
