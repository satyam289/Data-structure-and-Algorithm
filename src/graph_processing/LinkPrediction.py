import snap
import math
import time
from multiprocessing import Pool, cpu_count

graph = snap.LoadEdgeList(snap.TNGraph, "contact-high-school-proj-graph.txt", 0, 1)
ver = graph.GetNodes()

def getKScroreInSeqencial(k):
    def getNoPathLength(k, u, v):
        NodeNum, NodeVec = graph.GetNodesAtHop(u, k-1, True)
        count = 0
        for item in NodeVec:
            if graph.IsEdge(item, v):
                count = count + 1
        return count

    nonexistanceEdge = []
    for i in range(1, ver+1):
        for j in range(i, ver+1):
            if i != j and not graph.IsEdge(i, j):
                nonexistanceEdge.append((i, j))

    CNScores = []
    AAScores = []
    KScores = []
    for edge in nonexistanceEdge:
        u = edge[0]
        v = edge[1]
    
        #CNS Calculation
        numNbrs, Nbrs = graph.GetCmnNbrs(u, v, True)
        CNScores.append(numNbrs)
    
        #AAS Calulation
        sum = 0
        for nId in Nbrs:
            deg = graph.CntDegNodes(nId)
            if deg > 1:
                sum = sum + 1/math.log(deg)
        AAScores.append(round(sum, 3))
        
        #KSccore Calculation
        sum1 = 0
        beta = 0.1
        for i in range(2, 7):
            path = getNoPathLength(i, u, v)
            sum1 = sum1 + ((beta**i) * path)
        KScores.append(round(sum1, 3))

    CNScores = sorted(CNScores, reverse=True)
    AAScores = sorted(AAScores, reverse=True)
    KScores = sorted(KScores, reverse=True)
    if(len(CNScores) < k):
        print("Length of k request is more than available: ",  len(CNScores))
    
    print("-- CN Score -- ", CNScores[:k])
    print("-- AA Score -- ", AAScores[:k])
    print("-- KS Score -- ", KScores[:k])

def getNonExistanceEdge(parent):
    res = []
    for child in range(parent, ver+1):
        if parent != child and not graph.IsEdge(parent, child):
             res.append((parent, child))
    return res       

def processPerEdge(edge):   
    u = int(edge[0])
    v = int(edge[1])
    #can parallel with 'multiprocessing.Process' but breaking due daemonic process cannot have children
    CNScore = getCNNScore(u, v)
    AAScore = getCHHScore(u, v)
    KScore = getAsSorce(u, v)
    return CNScore, AAScore, KScore

def getCNNScore(u, v):
    numNbrs = graph.GetCmnNbrs(u, v)
    return numNbrs

def getCHHScore(u, v):
    #AAS Calulation
    numNbrs, Nbrs = graph.GetCmnNbrs(u, v, True)
    sum = 0
    for nId in Nbrs:
        deg = graph.CntDegNodes(nId)
        if deg > 1:
            sum = sum + 1/math.log(deg)
    return round(sum, 3)

def getNoPathLength(k, u, v):
    NodeNum, NodeVec = graph.GetNodesAtHop(u, k-1, True)
    count = 0
    for item in NodeVec:
        if graph.IsEdge(item, v):
            count = count + 1
    return count

def getAsSorce(u, v):
    #KSccore Calculation
    sum1 = 0
    beta = 0.1
    for i in range(2, 7):
        path = getNoPathLength(i, u, v)
        sum1 = sum1 + ((beta**i) * path)
    return round(sum1, 3)

def getSortedScorce(arr):
    return sorted(arr, reverse=True)

def getKScroreInParellel(k):
    pool = Pool(processes=cpu_count())
    #parellel fetch Non Existance Edge
    edges = pool.map(getNonExistanceEdge, range(1, ver+1))
    nonexist = []
    for edge in edges:
        if edge:
            nonexist.extend(edge)
    #parallel scoring each Edge
    processedScores = pool.map(processPerEdge, nonexist)
    CNScores, AAScores, KScores = zip(*processedScores)
    #parallel sorting of each metrics
    scores = [CNScores, AAScores, KScores]
    allscore = pool.map(getSortedScorce, scores)
    if(len(CNScores) < k):
        print("Length of k request is more than available: ",  len(CNScores))
    print("-- CN Score -- ", allscore[0][:k])
    print("-- AA Score -- ", allscore[1][:k])
    print("-- KS Score -- ", allscore[2][:k])
    pool.close()

if __name__=="__main__":
    k = int(input("Please Enter k value : "))
    start_time1 = time.time()
    print("Sequential started ...")
    getKScroreInSeqencial(k)
    diff1 = time.time() - start_time1
    print(" Time taken [%0.4f] sec " % (diff1))
    
    start_time2 = time.time()
    print("parallel started ...")
    getKScroreInParellel(k)
    diff2 = time.time() - start_time2
    print(" Time taken [%0.4f] sec " % (diff2))

    print("Execution reduced by [%0.4f] sec " % (diff1 - diff2))
    