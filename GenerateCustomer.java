  class GenerateCustomer  {

  public void GenerateCustomer(int newGen)
  {
    global global = new global();

    for (int i = 0; i < newGen; i++)
    {
      Customer customerobj = new Customer();
      customerobj.setCustomerTask1(global.Task[(int) (Math.random()*global.Task.length)]);
      global.customerAl.add(customerobj);
    }

  }

}
