
using namespace std;

int hammingDistance(int n1, int n2)
{
    int x = n1 ^ n2;
    int distance = 0;

    while (x > 0)
    {
        distance += x & 1;
        x >>= 1;
    }
    return distance;
}