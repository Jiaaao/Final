public class GenerateCleaner extends bpM{

  int generateCtr = 0;
  public Cleaner GenerateCleaner(int removectr, int newGen){

    String[] Task = { "Washing", "Furniture", "Pool", "Car" };

    generateCtr = generateCtr - removectr;
    newGen = newGen + generateCtr;

    while( generateCtr < newGen )
    {
      Cleaner cleanerArray = new Cleaner();

      cleanerArray.setCleanerTask(Task[(int) (Math.random()*4)]);                    //Set Random Task between 1 - 4 from the Aray
      cleanerAl.add(cleanerArray);


      Cleaner cleanerGetArray = new Cleaner();
      cleanerGetArray = cleanerAl.get(generateCtr);

      //Display
      System.out.println("\nCleaner " + ( generateCtr + 1 ) + " Task 1: " + cleanerGetArray.getCleanerTask());


      generateCtr++;
    }
    return cleanerGetArray;
  }
}
