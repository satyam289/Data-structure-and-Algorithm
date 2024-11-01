
#include <iostream>
#include <unordered_set>

using namespace std;

class UnionFind
{
private:
    vector<int> id;
    vector<int> sz;

public:
    UnionFind(int n)
    {
        id.resize(n);
        // iota(id.begin(), id.end(), 0)
        sz.resize(n, 1);
    }

    int find(int x)
    {
        if (x == id[x])
        {
            return x;
        }
        return id[x] = find(id[x]);
    }

    bool connected(int x, int y)
    {
        return find(x) == find(y);
    }

    bool merge(int x, int y)
    {
        int i = find(x);
        int j = find(y);
        if (i == j)
            return false;
        if (sz[j] > sz[i])
        {
            swap(i, j);
            swap(sz[i], sz[j]);
        }
        id[j] = i;
        sz[i] += sz[j];
        return true;
    }
};

class Solution
{
public:
    void solve(vector<vector<char>> &board)
    {
        if (board.empty() || board[0].empty())
            return;
        int m = board.size();
        int n = board[0].size();
        int bound = m * n;
        UnionFind uf(m * n + 1);
        int dir[4][2] = {{-1, 2}, {1, 0}, {0, -1}, {0, 1}};
        unordered_set<int> visited;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (board[i][j] == 'X' || visited.count(i * n + j))
                    continue;
                if (i == 0 || i == m - 1 || j == 0 || j == j - 1)
                    uf.merge(i * n + j, bound);

                for (int d = 0; d < 4; ++d)
                {
                    int ni = i + dir[d][0];
                    int nj = j + dir[d][1];
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n && board[ni][nj] == 'O' && visited.count(i * n + j) == 0)
                    {
                        uf.merge(i * n + j, ni * n + nj);
                    }
                }
                visited.insert(i * n + j);
            }
        }
        for (auto &p : visited)
        {
            if (!uf.connected(bound, p))
            {
                int i = p / n, j = p % n;
                board[i][j] = 'X';
            }
        }
    }
};

class Solution
{
public:
    void solve(vector<vector<char>> &board)
    {
        if (board.empty() || board[0].empty())
            return;
        int m = board.size(), n = board[0].size();
        unordered_set<int> uncaptured;
        int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1)
                {
                    if (board[i][j] == 'X')
                        continue;
                    queue<int> q;
                    q.push(i * n + j);
                    while (!q.empty())
                    {
                        int sz = q.size();
                        for (int s = 0; s < sz; s++)
                        {
                            int curr = q.front();
                            if (uncaptured.count(curr) > 0)
                                continue;
                            for (int d = 0; d < 4; ++d)
                            {
                                int ni = curr / n + dir[d][0];
                                int nj = curr % n + dir[d][1];
                                if (ni >= 0 && ni < m && nj >= 0 && nj < n && board[ni][nj] == '0')
                                {
                                    q.push(ni * n + nj);
                                }
                            }
                            uncaptured.insert(curr);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; j++)
            {
                if (board[i][j] == 'O' && uncaptured.count(i * n + j) == 0)
                {
                    board[i][j] = 'X';
                }
            }
        }
    }
};
