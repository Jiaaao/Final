import java.util.*;




public class bpM  {


  /* ------------------ BYTE TO MEGABYTE CONVERSION ---------------- */
  private static final long MEGABYTE = 1024L * 1024L;
  public static long bytesToMegabytes(long bytes) {
      return bytes / MEGABYTE;
  }

  /* ------------------ MAIN CLASS ---------------- */
  public static void main(String[] args){



    Scanner input = new Scanner(System.in);
    char choice;

    GenerateCleaner GenerateCleaner = new GenerateCleaner();
    GenerateCustomer GenerateCustomer = new GenerateCustomer();
    Hopcroft hck = new Hopcroft();
    global global = new global();


    int batchCounter = 0;
    long elapsedTotal = 0;

    /*------ ONLINE ------*/
    do
    {
        long startTime = System.currentTimeMillis();
        batchCounter++;

        /* ------ Initial Generation ------ */
        if ( batchCounter == 1 )
          {
            global.L = (int) (Math.random()*global.control + 1);
            global.R = (int) (Math.random()*global.control + 1);
            GenerateCustomer.GenerateCustomer(global.L);
            GenerateCleaner.GenerateCleaner(global.R);
          }

        global.V = global.L + global.R;
        hck.GraphV(global.V);
        global.E = 0;

        /* ------ Display Customers ------ */
        System.out.println("\n\n------ CUSTOMERS ------");
        for(int displayCtr = 0; displayCtr < global.L; displayCtr++)
        {
          System.out.println("\nCustomer " + ( displayCtr + 1 ) + " Task : " + global.customerAl.get(displayCtr).getCustomerTask1()  );
        }
        System.out.println("------------------------");

        /* ------ Display Cleaners ------ */
        System.out.println("\n\n------ CLEANERS ------");
        for(int displayCtr = 0; displayCtr < global.R; displayCtr++)
        {
          System.out.println("\nCleaner " + ( displayCtr + 1 ) + " Task 1: " + global.cleanerAl.get(displayCtr).getCleanerTask1() + " Task 2 : " + global.cleanerAl.get(displayCtr).getCleanerTask2() );
        }
        System.out.println("------------------------");

        //While there is L/Customer
        for( int n = 0; n < global.L; n++ )
        {
              //While there is a R/Cleaner
              for(int m = 0; m < global.R; m++)
              {
                //Finding a match for Customer with a cleaner
                if ( global.customerAl.get(n).getCustomerTask1() == global.cleanerAl.get(m).getCleanerTask1() || global.customerAl.get(n).getCustomerTask1() == global.cleanerAl.get(m).getCleanerTask2()  )
                {
                  hck.addEdge(n, m);
                  global.E = global.E + 1;
                }
              }
        }
        System.out.println("\n\n------------ Batch " + batchCounter + " -----------------------------");
        hck.GraphE(global.E);
        hck.display();  //DISPLAY Adjacency List

        /* ------------------- Hopcroft Karp Algorithm ------------------- */
        ArrayList<ArrayList<Integer>> Matched = hck.HopcroftKarp(global.L, global.R);
        System.out.println("Matches: " + Matched);    //prints matched vertices



        /* ------ Finding the matched vertices in the vertices of Customers and Cleaners ------ */
        int removeCustomerctr = 0;
        int removeCleanerctr = 0;

        if ( Matched.size() > 0 )
          for (int u = 0; u < Matched.size() ;u++ )
          {
            for(int l = 0; l < global.L; l++)
              if ( global.customerAl.get(Matched.get(u).get(0) - 1).equals(global.customerAl.get(l)) )
                {
                  global.removeCustomer.add(global.customerAl.get(l));
                  removeCustomerctr++;
                }
            for(int k = 0; k < global.R; k++)
              if ( global.cleanerAl.get(Matched.get(u).get(1) - 1).equals(global.cleanerAl.get(k)) )
                {
                  global.removeCleaner.add(global.cleanerAl.get(k));
                  removeCleanerctr++;
                }
          }
          /*------------------------------------------------------------------------------------*/




        /* ------ Remove Matched Customers and Cleaners ------ */
        global.customerAl.removeAll((global.removeCustomer));
        global.cleanerAl.removeAll((global.removeCleaner));
        global.L = global.L - removeCustomerctr;
        global.R = global.R - removeCleanerctr;


        /* ------ There is a matching which matches all vertices of the graph ------ */
        if(global.L == 0 && global.R == 0)
          System.out.println("Perfect Match: Yes");
        else
          System.out.println("Perfect Match: No");

        /* ------ Displays the number of removed Customers and Cleaners if matched ------ */
        System.out.println("\n\nNo. of Removed Customers: " +removeCleanerctr);
        System.out.println("No. of Removed Cleaners: " +removeCleanerctr);


        /* ------ Generate random number of customers and cleaners for new batch ------ */
        int genRandCust = (int) (Math.random()*global.control + 1);
        int genRandCle = (int) (Math.random()*global.control + 1);
        global.L = global.L + genRandCust;
        global.R = global.R + genRandCle;
        System.out.println("\n\nRandom Generated Customers: " + genRandCust);
        System.out.println("Random Generated Cleaners: " + genRandCle);
        GenerateCustomer.GenerateCustomer(genRandCust);
        GenerateCleaner.GenerateCleaner(genRandCle);



        /* ------ Displays the new current number of customers and cleaners ------ */
        System.out.println("\n\nNew Number of Customers: " + global.L);
        System.out.println("New Number of Cleaners: " + global.R);

        /* ------ Execution Time  ------ */
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        elapsedTotal = timeElapsed + elapsedTotal;
        System.out.println("\nExecution Time in milliseconds: " + timeElapsed);
        System.out.println("--------------------------------------------------");

        Matched.clear();
        /* ------ No. of Batches per 'Continue' ------ */
        if( batchCounter % global.batches == 0)
          {
            System.out.println("\n\nContinue? Y or N");
            choice = input.next().charAt(0);
            if ( (choice == 'y') || (choice == 'Y') )
              continue;
            else if ( (choice == 'n') || (choice == 'N') )
              break;
            else
              {
                System.out.println("\nWrong input.\nEND");
                break;
              }
          }
    }while( 1 > 0 ); //END OF WHILE LOOP


    /* ------ Memory Consumption ------ */
    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    long memory = runtime.totalMemory() - runtime.freeMemory();
    System.out.println("\nUsed memory: " + memory + " bytes");
    System.out.println("Used memory: "+ bytesToMegabytes(memory) + " megabytes");

    /* ------ Average Execution Time ------ */
    long average = elapsedTotal / batchCounter;
    System.out.println("\nAverage Execution Time in milliseconds: " + average);
    System.out.println("--------------------------------------------------");


  }
}
