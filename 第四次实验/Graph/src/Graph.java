import java.util.*;

class Edge {
    int v1, v2; // 起点和终点
    int weight; // 权重

    public Edge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }
}

public class Graph {
    private Map<Integer, List<Edge>> adjList; // 邻接表
    private int numVertices; // 顶点数量
    private int numEdges; // 边的数量
    private int[] marks; // 顶点的标记
    private Map<String, Integer> wordToVertex; // 单词到顶点编号映射
    private List<String> vertexToWord; // 顶点编号到单词的映射

    // 构造函数
    public Graph() {
        this.adjList = new HashMap<>();
        this.numVertices = 0;
        this.numEdges = 0;
        this.marks = new int[1000]; // 假设最多1000个顶点
        this.wordToVertex = new HashMap<>();
        this.vertexToWord = new ArrayList<>();
    }

    // 返回顶点数量
    public int n() {
        return numVertices;
    }

    // 返回边的数量
    public int e() {
        return numEdges;
    }

    // 添加单词到图中作为顶点
    public int addWord(String word) {
        if (!wordToVertex.containsKey(word)) {
            int vertexId = numVertices++;
            wordToVertex.put(word, vertexId);
            vertexToWord.add(word);
            adjList.put(vertexId, new ArrayList<>()); // 初始化邻接表的列表
        }
        return wordToVertex.get(word);
    }

    // 获取单词对应的顶点编号
    public int getVertex(String word) {
        return wordToVertex.getOrDefault(word, -1);
    }

    // 获取顶点对应的单词
    public String getWord(int vertex) {
        return vertex >= 0 && vertex < vertexToWord.size() ? vertexToWord.get(vertex) : null;
    }

    // 返回顶点v的第一条边
    public Edge first(int v) {
        List<Edge> edges = adjList.get(v);
        return (edges != null && !edges.isEmpty()) ? edges.get(0) : null;
    }

    // 返回顶点v的下一条边
    public Edge next(int v, Edge edge) {
        List<Edge> edges = adjList.get(v);
        if (edges != null) {
            int index = edges.indexOf(edge);
            if (index != -1 && index + 1 < edges.size()) {
                return edges.get(index + 1);
            }
        }
        return null;
    }

    // 判断是否有边
    public boolean isEdge(int i, int j) {
        List<Edge> edges = adjList.get(i);
        if (edges != null) {
            for (Edge edge : edges) {
                if (edge.v2 == j) {
                    return true;
                }
            }
        }
        return false;
    }

    // 添加边
    public void setEdge(int i, int j, int weight) {
        if (!isEdge(i, j)) {
            adjList.get(i).add(new Edge(i, j, weight));
            adjList.get(j).add(new Edge(j, i, weight)); // 无向图
            numEdges++;
        }
    }

    // 删除边
    public void delEdge(int i, int j) {
        List<Edge> edgesI = adjList.get(i);
        List<Edge> edgesJ = adjList.get(j);
        if (edgesI != null) {
            edgesI.removeIf(edge -> edge.v2 == j);
        }
        if (edgesJ != null) {
            edgesJ.removeIf(edge -> edge.v2 == i);
        }
        numEdges--;
    }

    // 获取边的权重
    public int weight(int i, int j) {
        List<Edge> edges = adjList.get(i);
        if (edges != null) {
            for (Edge edge : edges) {
                if (edge.v2 == j) {
                    return edge.weight;
                }
            }
        }
        return 0;
    }

    // 设置顶点标记
    public void setMark(int v, int val) {
        marks[v] = val;
    }

    // 获取顶点标记
    public int getMark(int v) {
        return marks[v];
    }

    // 添加单词之间的边（如果它们仅相差一个字母）
    public void addWordEdge(String word1, String word2) {
        int v1 = addWord(word1);
        int v2 = addWord(word2);
        if (differByOne(word1, word2)) {
            setEdge(v1, v2, 1);
        }
    }

    // 检查两个单词是否仅相差一个字母
    private boolean differByOne(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
                if (diffCount > 1) return false;
            }
        }
        return diffCount == 1;
    }

    // 打印图的邻接表
    public void printGraph() {
        System.out.println("Adjacency List:");
        for (Map.Entry<Integer, List<Edge>> entry : adjList.entrySet()) {
            System.out.print(getWord(entry.getKey()) + ": ");
            for (Edge edge : entry.getValue()) {
                System.out.print(getWord(edge.v2) + "(" + edge.weight + ") ");
            }
            System.out.println();
        }
    }

    // 打印顶点映射
    public void printVertices() {
        System.out.println("Vertices:");
        for (Map.Entry<String, Integer> entry : wordToVertex.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
