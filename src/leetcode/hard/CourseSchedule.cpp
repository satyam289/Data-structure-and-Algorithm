#include <iostream>

using namespace std;

/**
 * There are a total of numCourses courses you have to take, labeled from
 * 0 to numCourses-1
 * Some courses may have prerequisites, for example to courses 0, you have to
 * first take courses which is expressed as pair : [0, 1]
 * 
 * Given the total number of courses and list of prerequisite pairs,
 * is it possible for you to finish all courses ?
 * 
 * Input : numberCourses = 2, prerequisites = [[1, 0]]
 * Output = true
 * Explaination :  To take course 1, you should have finised course 0. possible
 * 
 * Input: numCourses = 2, prerequisites = [[1, 0], [0, 1]]
 * Output: false
 * Explaination: To take course 1, you should course 0;
 *               To take course 0, you should course 1;, impossible
 */

class Solution
{
public:
    bool canFinish(int numCourses, vector<vector<int>> &prerequesites)
    {
        vector<vector<int>> adj(numCourses);
        for (auto &p : prerequesites)
        {
            adj[p[1]].push_back(p[0]);
        }
        vector<bool> path(numCourses);
        for (int i = 0; i < numCourses; ++i)
        {
            if (isCycle(i, adj, path))
            {
                return false;
            }
            return true;
        }
    }

    bool isCycle(int i, vector<vector<int>> &adj, vector<bool> &path)
    {
        if (path[i])
            return true;
        if (adj[i].empty())
            return false;
        path[i] = true;
        bool ret = false;
        for (auto &j : adj[i])
        {
            ret = isCycle(j, adj, path);
            if (ret)
            {
                break;
            }
        }
        path[i] = false;
        return ret;
    }
};