package graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import graph.Graph2.Vertex2;

//https://www.geeksforgeeks.org/hamiltonian-cycle-backtracking-6/
class HamiltonsCycle {

    public <T> boolean getHamilton(Graph2<T> graph, List<Vertex2<T>> result) {

        Vertex2<T> startVertex = graph.getAllVertex().iterator().next();
        Set<Vertex2<T>> visited = new HashSet<>();
        return HamiltonsUtil(startVertex, startVertex, result, visited, graph.getAllVertex().size());
    }

    private <T> boolean HamiltonsUtil(Vertex2<T> startVertex, Vertex2<T> currentVertex, List<Vertex2<T>> result,
            Set<Vertex2<T>> visited, int totalVextex) {

        visited.add(currentVertex);
        result.add(currentVertex);
        for (Vertex2<T> child : currentVertex.getAllAdajecent()) {
            if (startVertex.equals(child) && totalVextex == result.size()) {
                result.add(startVertex);
                return true;
            }
            if (!visited.contains(child)) {
                if (HamiltonsUtil(startVertex, child, result, visited, totalVextex)) {
                    return true;
                }
            }
            result.remove(result.size() - 1);
            visited.remove(currentVertex);
        }
        return false;
    }
}

/*
#include <iostream>
#include <vector>

bool hamilton_cycle(const std::vector<std::vector<bool>> &routes)
{
    const size_t n = routes.size();
    const size_t height = 1 << n; // height of array is 2^n
    std::vector<std::vector<bool>> dp(height, std::vector<bool>(n, false));

    for (size_t i = 0; i < n; ++i)
    {
        dp[1 << i][i] = true;
    }
    for (size_t i = 1; i < height; i++)
    {
        std::vector<size_t> zeros, ones;
        for (size_t pos = 0; pos < n; ++pos)
        {
            if ((1 << pos) & i)
            {
                ones.push_back(pos);
            }
            else
            {
                zeros.push_back(pos);
            }
        }

        for (auto &o : ones)
        {
            if (!dp[i][o])
            {
                continue;
            }

            for (auto &z : zeros)
            {
                if (!routes[o][z])
                {
                    continue;
                }
                dp[i + (1 << z)][z] = true;
            }
        }
    }

    bool is_cycle = false;
    for (size_t i = 0; i < n; i++)
    {
        is_cycle |= dp[height - 1][i];
        if (is_cycle)
        {
            break;
        }
    }
    return is_cycle;
};
*/