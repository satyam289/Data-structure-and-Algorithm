#include <iostream>

using namespace std;

class Trie
{
private:
    struct Node
    {
        bool isWord;
        vector<Node*> children;
        Node()
        {
            isWord = false;
            children.resize(26, nullptr);
        }
        ~Node()
        {
            for (auto &c : children)
            {
                delete c;
            }
        }
    };
    Node *root;

    Node *find(const string &word)
    {
        Node* cur = root;
        for (auto &c : word)
        {
            cur = cur->children[c - 'a'];
            if (!cur)
                break;
        }
        return cur;
    }

public:
    Trie()
    {
        root = new Node();
    }

    void insert(string word)
    {
        Node* cur = root;
        for (auto &c : word)
        {
            if (!cur->children[c - 'a'])
            {
                cur->children[c - 'a'] = new Node();
            }
            cur = cur->children[c - 'a'];
        }
        cur->isWord = true;
    }

    /**
     * @return if word is in the trie
     */
    bool search(string word)
    {
        Node *cur = find(word);
        return cur && cur->isWord;
    }

    /**
     * @return if there is any word that starts with prefix
     */
    bool startsWith(string prefix)
    {
        Node* cur = find(prefix);
        return cur;
    }
};

struct Node
{
    char character;
    bool isWord;
    vector<Node *> children;
    Node(char c, bool i) : character(c), isWord(i) {}
    ~Node()
    {
        for (auto &c : children)
            delete c;
    }
};

class Trie
{
private:
    Node* root;

public:
    Trie()
    {
        root = new Node('r', false);
    }

    void insert(string word)
    {
        int n = word.size();
        Node* cur = root;
        for (int i = 0; i < n; ++i)
        {
            bool foundCharacter = false;
            for (auto&c : cur->children)
            {
                if (word[i] == c->character)
                {
                    cur = c;
                    foundCharacter = true;
                    break;
                }
            }

            if (!foundCharacter)
            {
                Node* c = new Node(word[i], false);
                cur->children.push_back(c);
                cur = c;
            }
        }
        cur->isWord = true;
    }

    bool search(string word)
    {
        Node* cur = root;
        int n = word.size();
        for (int i = 0; i < n; ++i)
        {
            bool foundCharacter = false;
            for (auto& c : cur->children)
            {
                if (c->character == word[i])
                {
                    foundCharacter = true;
                    cur = c;
                    break;
                }
            }
            if (!foundCharacter)
                return false;
        }
        return cur->isWord;
    }

    bool startsWith(string prefix)
    {
        Node* cur = root;
        int n = prefix.size();
        for (int i = 0; i < n; i++)
        {
            bool foundCharacter = false;
            for (auto &c : cur->children)
            {
                if (c->character == prefix[i])
                {
                    foundCharacter = true;
                    cur = c;
                    break;
                }
            }
            if (!foundCharacter)
                return false;
        }
        return true;
    }
};