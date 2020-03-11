import java.util.*;

public class Hopcroft extends global
{

    private final int NIL = 0;
    private final int INF = Integer.MAX_VALUE;
    private int[] PairU;
    private int[] PairV;
    private int[] Dist;
    ArrayList<Integer> adjList[];       //Adjacency Array of ArrayList for Edges
    ArrayList<Integer> match[];     //Index for matchList
    int i = 0;

    public void GraphV(int V)
    {
      this.V = V;                       //VERTICES
      adjList = new ArrayList[V + 1];
      for(int i = 0; i < adjList.length; i++)
        adjList[i] = new ArrayList<>();

    }

    public void GraphE(int E)
    {
      this.E = E;
      match = new ArrayList[E];
      for(int j = 0; j < match.length; j++)
        match[j] = new ArrayList<>();
    }


    /*------ Breadth First Search ------*/
    public boolean BFS()
    {
      Queue<Integer> queue = new LinkedList<Integer>();

      for(int u = 1; u <= L; u++)
      {

        if (PairU[u] == NIL)
        {
          Dist[u] = 0;
          queue.add(u);
        }

        else
          Dist[u] = INF;

      }

      Dist[NIL] = INF;

      while(!queue.isEmpty())
      {

        int u = queue.poll();


        if (Dist[u] < Dist[NIL])
        {

          for(int v : adjList[u])
          {

            if (Dist[PairV[v]] == INF)
            {
              Dist[PairV[v]] = Dist[u] + 1;
              queue.add(PairV[v]);
            }
          }
        }
      }
      return Dist[NIL] != INF;
    }//BFS END BRACKET

    /*------ Depth First Search ------*/
    public boolean DFS(int u)
    {
      if (u != NIL)
      {
        for (int v: adjList[u])
        {

          if(Dist[PairV[v]] == Dist[u] + 1)

          {
            if (DFS(PairV[v]))
            {
              PairV[v] = u;
              PairU[u] = v;
              match[i].add(PairV[v]);
              match[i].add(PairU[u]);
              matchList.add(match[i]);
              i++;
              return true;
            }
          }
        }

        Dist[u] = INF;
        return false;
      }

      return true;
    }//DFS END BRACKET


    /*------ Display Adjacency List ------*/
    void display()
    {
      for(int i = 0; i < V; i++)
      {
        if ( adjList[i].size() > 0 )
        {
          System.out.println("Vertex " +i+ " is connected to:- ");
          for(int j = 0; j < adjList[i].size(); j++)
            System.out.print(adjList[i].get(j) + " ");
        }
        System.out.println();
      }

    }//display END BRACKET

    /*------ Add Edges to Adjacency List ------*/
    void addEdge(int i, int j)
    {
      adjList[i+1].add(j+1);
    }//addEdge END BRACKET


    /*------ Hopcroft Karp ------*/
    public ArrayList HopcroftKarp(int L, int R) //Hopcroft Karp Algorithm
    {
      matchList.clear();
      System.out.println("No. of Customers: " + L);
      System.out.println("No. of Cleaners: " + R);

      PairU = new int[L + 1];

      PairV = new int[R + 1];

      Dist = new int[L + 1];

      for(int u = 0; u < L; u++)
        PairU[u] = NIL;
      for(int v = 0; v < R; v++)
        PairV[v] = NIL;

      int matching = 0;
      while (BFS())
      {

        for (int u = 1; u <= L; u++)
        {
          if (PairU[u] == NIL)
            if (DFS(u))
            {
              matching = matching + 1;
            }
        }
      }

      /*------ Remove previous matching if there exists a new matching
              of the same vertex of Customer ------*/
      for(int c = 0; c < matchList.size(); c++)
        {
        for(int z = 0; z < c; z++)
          {
            if ( matchList.get(c).get(0) == matchList.get(z).get(0) )
              {
                matchList.remove(matchList.get(z));
                c = c - 1;
              }
          }

        }


      Arrays.fill(adjList, null);
      Arrays.fill(match, null);
      i = 0;
      return matchList;
    }//END BRACKET HopcroftKarp



}
