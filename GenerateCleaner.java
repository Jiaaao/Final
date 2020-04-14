class GenerateCleaner  {


  public void GenerateCleaner(int newGen){

    global global = new global();

    for (int i = 0; i < newGen; i++)
    {
      Cleaner cleanerArray = new Cleaner();

      cleanerArray.setCleanerTask1(global.Task[(int) (Math.random()*global.Task.length)]);                    //Set Random Task between 1 - 4 from the Task String Array
      cleanerArray.setCleanerTask2(global.Task[(int) (Math.random()*global.Task.length)]);
      global.cleanerAl.add(cleanerArray);
    }

  }

}
