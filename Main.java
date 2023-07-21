import java.util.*;
import java.io.*;

/*abstract interface Check
{
  public abstract boolean authenticate(); 
}*/

class Admin //implements Check
{
  private final String Username="Admin123";
  private final String Password="Password123";
  //private int admin;
  public int TerrainCount;
  public HashMap<String, ArrayList> MealSchedule = new HashMap<String, ArrayList>();
  Scanner sc = new Scanner(System.in);
  String timing[] ={"9:00","9:40","10:20","11:00","11:40","1:30","2:10","2:50","3:30","4:10","4:50","5:30","6:10"};
  public int priority[] = new int[13]; 
  public int shows[] = new int[13]; 
  String names[] = {"Lion","Cobra","Elephant","Peacock","Deer","Panda","Flamingo","Tortoise","Crocodile","Macau","Dolphin","Blue Whale","FLying squirrels"};//Names
  //String names[] = {"Lion","Cobra","Elephant","Peacock"};
  int n =13;
  public void set()
  {
    for(int i=0;i<n;i++)
    {
      priority[i]=1; //Medium 
    }
    for(int i=0;i<n;i++)
    {
      shows[i] = priority[i]*13 / Arrays.stream(priority).sum();  
    }
  }

  public boolean authenticate(String Username, String Password)
  {
    if (this.Username.equals(Username)&& this.Password.equals(Password))
    {
      return true;
    }
    else
    {
     	return false;
    }
  }
  
  void Display_show()
  { set();
    String timearr[] = {"9:00 to 9:40", "9:40 to 10:20","10:20 to 11:00","11:00 to 11:40","11:40 to 12:20", "1:30 to 2:10", "2:10 to 2:50","2:50 to 3:30","3:30 to 4:10","4:10 to 4:50","4:50 to 5:30","5:30 to 6:10","6:10 to 6:50"};
    for(int i=0;i<13;i++)
    {
      System.out.println("  Show number "+(i+1)+"  Show name  " +names[i] + "  Number of shows: "+shows[i] + "Timing : "+timearr[i]);
    }
  }

  public void priority_update()
  {String timearr[] = {"9:00 to 9:40", "9:40 to 10:20","10:20 to 11:00","11:00 to 11:40","11:40 to 12:20", "1:30 to 2:10", "2:10 to 2:50","2:50 to 3:30","3:30 to 4:10","4:10 to 4:50","4:50 to 5:30","5:30 to 6:10","6:10 to 6:50"};
    for(int i=0;i<n;i++)
    {
    System.out.println("Enter the percent of tickets sold for "+names[i]);
    int p=sc.nextInt();
    if(p<70 && p>40) priority[i]=1;    //Medium
    if(p<40) priority[i]=0;            //Low
    if(p>70) priority[i]=2;}           //High
    int sum=0;
    //Update
    for(int i=n-1;i>-1;i--)
    { int temp=priority[i] *13 / Arrays.stream(priority).sum() ;
      shows[i] =   temp;
      sum +=temp;
    }
    int remainder = 13-sum;
    while(remainder>0)
    {
      int pr=2;
      while(pr>=0)
      {
      for (int i=n-1;i>-1;i--)
      {
        if(priority[i]==pr && remainder>0)
        {
          shows[i]++;
          remainder--;
        }
      }
      pr--;
      }
    }
    for(int i=0;i<13;i++)
    {
      System.out.println("Show number "+(i+1)+"  Show name  " +names[i] + "  Number of shows: "+shows[i] + " Timings  " +timearr[i]);
    }
  }
}

class Customer //implements Check
{
  private ArrayList<String> Firstname = new ArrayList<String>();
  private ArrayList<Integer> Age =new ArrayList<Integer>();
  private ArrayList<String> Email= new ArrayList<String>();
  private ArrayList<String> Password = new ArrayList<String>();
  private ArrayList<Integer> Donations = new ArrayList<Integer>();
  public ArrayList<String> Favourite_Animal = new ArrayList<String>();
  ArrayList<Integer> visits = new ArrayList<Integer>();
  public int i;
  public HashMap<String,ArrayList<Object>> customerHash=new HashMap<String,ArrayList<Object>>();

	public void setFirstname(String Firstname)
  {
    	this.Firstname.add(Firstname);
   }
 public void setAge(int Age)
 {
   	this.Age.add(Age);
 }
 public void setEmail(String Email)
 {
   	this.Email.add(Email);
 }
 public void setPassword(String Password)
 {
   	this.Password.add(Password);
 }
 public void setDonations(int Donations)
 {
   	this.Donations.add(Donations);
 }
 public String getFirstname(int i)
 {
   	return Firstname.get(i);
 }
 public String getEmail(int i)
 {
   	return Email.get(i);
 }
 public String getPassword(int i )
 {
   	return Password.get(i);
 }
 public int getAge(int i)
 {
   	return Age.get(i);
 }
 public int getDonations(int i)
 {
   	return Donations.get(i);
 }

Customer()
  {
    
    //setEmail(Email);
    //setPassword(Password);
    
  }
  /*
    this.Favourite_Animal=Favourite_Animal;*/
  
  public void write()
  { //int i=0;
    // ArrayList temp=new ArrayList();
    // while(true)
    // {
    // try{
    // temp.add(getFirstname(i));
    // temp.add(getAge(i));
    // temp.add(getPassword(i));
    // temp.add(getDonations(i));
    // temp.add(Favourite_Animal.get(i));
    // temp.add(visits.get(i));
    // this.customerHash.put(getEmail(i),temp);
    // i++;
    // }
    // catch (Exception e)
    // {
    //     break;
    // }
    // }
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Customers.csv"))) 
    {
      for (Map.Entry<String, ArrayList<Object>> entry : customerHash.entrySet()) 
      {
        StringBuilder sb = new StringBuilder();
        sb.append(entry.getKey());
        sb.append(',');
        ArrayList<Object> values = entry.getValue();
        sb.append(values.get(0));
        sb.append(',');
        sb.append(values.get(1));
        sb.append(',');
        sb.append(values.get(2));
        sb.append(',');
        sb.append(values.get(3));
        sb.append(',');
        sb.append(values.get(4));
        sb.append(',');
        sb.append(values.get(5));
        sb.append('\n');
        bw.write(sb.toString());
      }
    } catch (IOException e) 
    {
      e.printStackTrace();
    }
  }

  public void readFile()
  {
    try (BufferedReader br = new BufferedReader(new FileReader("Customers.csv"))) 
    { System.out.println("READING FILE");
      String line;
      while ((line = br.readLine()) != null) 
      {
        String[] values = line.split(",");
        String key = values[0];
        ArrayList<Object> data = new ArrayList<>();
        data.add(values[1]);
        data.add(Integer.parseInt(values[2]));
        data.add(values[3]);
        data.add(Integer.parseInt(values[4]));
        data.add(values[5]);
        data.add(Integer.parseInt(values[6]));
        setFirstname(values[1]); 
        setAge(Integer.parseInt(values[2])); 
        setDonations(Integer.parseInt(values[4]));
        setEmail(values[0]);
        setPassword(values[3]);
        Favourite_Animal.add(values[5]);
        visits.add(Integer.parseInt(values[6]));
        customerHash.put(key, data);
      }
    } 
    catch (IOException e) 
    {
      e.printStackTrace();
    }
    for(String i:customerHash.keySet())
    {
        System.out.println(customerHash.get(i));
    }
  }

  boolean authenticate(String Username, String Password)
  {
    int flag=0;
    try (BufferedReader br = new BufferedReader(new FileReader("Customers.csv"))) 
    {
      String line;
      while ((line = br.readLine()) != null) 
      {
        String[] values = line.split(",");        
        if (values[0].equals(Username) && values[3].equals(Password))
        {
          flag=1;
          break;
        }
      }
    }
    catch (IOException e) 
    {
      e.printStackTrace();
    }
    if (flag==1)
    {
      return true;
    }
    else
    {
     	return false;
    }
  }

}

class Animal extends Admin
{
  public int count=0;
  public String Species;
  public ArrayList<String> Subspecies=new ArrayList<String>();
  public ArrayList<Integer> Age=new ArrayList<Integer>();
  public ArrayList<Boolean> HealthStatus=new ArrayList<Boolean>();
  public String PrefferedFood;
  public HashMap<String, ArrayList<Object>> AnimalHash = new HashMap<String, ArrayList<Object>>();
  public String Type;
  public ArrayList<String> Terrain=new ArrayList<String>();

  public Animal(String Species, String Type)
  {
      this.Species=Species;
      this.Type=Type;
  }

//Terrains- Savannah Terrain, Fresh Grass Terrain, Marshland Terrain, Aquatic, Rocky 
  public String AssignTerrain()
  {
    String terrain="";
    if (Type.equals("Mammal(H)"))
    {
        terrain="Fress Grass Terrain";
    }
    else if (Type.equals("Mammal(C)"))
    {
        terrain="Savannah Terrain";
    }
    else if (Type.equals("Mammal(O)"))
    {
        terrain="Rocky Terrain";
    }
    else if (Type.equals("Amphibian"))
    {
        terrain="Marshland";
    }
    else if (Type.equals("Reptile"))
    {
        terrain="Rocky Terrain";
    }
    else if (Type.equals("Bird"))
    {
        terrain="Fresh Grass Terrain";
    }
    else if (Type.equals("Aquatic"))
    {
      terrain="Aquarium Terrain";
    }
    return terrain;
  }

  public boolean isHealthy(String AnimalID)
  {
    int index = Integer.parseInt(AnimalID.substring((this.Species.length())));
    System.out.println(index);
    boolean x=HealthStatus.get(index); 
    return x;
  }

  public void writeAll(Animal[] Objarr)
  {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Animals123.csv"))) 
    { System.out.println(AnimalHash);
      for(Animal i:Objarr)
      {for (Map.Entry<String, ArrayList<Object>> entry : i.AnimalHash.entrySet()) 
      {
        StringBuilder sb = new StringBuilder();
        sb.append(entry.getKey());
        sb.append(',');
        ArrayList<Object> values = entry.getValue();
        sb.append(values.get(0));
        sb.append(',');
        sb.append(values.get(1));
        sb.append(',');
        sb.append(values.get(2));
        sb.append(',');
        sb.append(values.get(3));
        sb.append('\n');
        bw.write(sb.toString());
      }
    }} catch (IOException e) 
    {
      e.printStackTrace();
    } 
  }
  public void write(String Subspecies, int age, boolean health)
  {
    String AnimalID=this.Species+Integer.toString(AnimalHash.size()+1);
    System.out.println("Animal id"+(AnimalID+1));
    ArrayList temp=new ArrayList();
    
    temp.add(age);
    temp.add(Subspecies);
    temp.add(health);
    temp.add(this.AssignTerrain());

    this.AnimalHash.put(AnimalID,temp);
    System.out.println(AnimalHash);

    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Animals123.csv"))) 
    {
      for (Map.Entry<String, ArrayList<Object>> entry : AnimalHash.entrySet()) 
      {
        StringBuilder sb = new StringBuilder();
        sb.append(entry.getKey());
        sb.append(',');
        ArrayList<Object> values = entry.getValue();
        sb.append(values.get(0));
        sb.append(',');
        sb.append(values.get(1));
        sb.append(',');
        sb.append(values.get(2));
        sb.append(',');
        sb.append(values.get(3));
        sb.append('\n');
        bw.write(sb.toString());
      }
    } catch (IOException e) 
    {
      e.printStackTrace();
    }
  }

  public void readFile()
  {
    try (BufferedReader br = new BufferedReader(new FileReader("Animals.csv"))) 
    {
      String line;
      while ((line = br.readLine()) != null) 
      {
        String[] values = line.split(",");
        String key = values[0];
        if (key.contains(this.Species))
        {
          ArrayList<Object> data = new ArrayList<>();
          data.add(Integer.parseInt(values[1]));
          Age.add(Integer.parseInt(values[1]));
          data.add(values[2]);
          data.add(Boolean.parseBoolean(values[3]));
          data.add((values[4]));
          Terrain.add(values[4]);
          HealthStatus.add(Boolean.parseBoolean(values[3]));
          Subspecies.add(values[2]);
          AnimalHash.put(key, data);
          
          count++;
        }
      }
      System.out.println("READ"+AnimalHash);
    } 
    catch (IOException e) 
    {
      e.printStackTrace();
    }
  }
}

class Revenue extends Admin
{
  private double EmpSalary;
  private double FoodCost;
  private double MaintenanceService;
  private double Donations;
  private double TicketCost;
	private double GovtFunds;
  private double MerchandiseMaking;
  private double MerchandiseSelling;
  private double ProfitMade;  
  
  public void setEmpSalary(double EmpSalary)
  {
    this.EmpSalary=EmpSalary;
  }
  public void setFoodCost(double FoodCost)
  {
    this.FoodCost=FoodCost;
  }
  public void setMaintenanceService(double MaintenanceService)
  {
    this.MaintenanceService=MaintenanceService;
  }
  public void setDonations(double Donations)
  {
    this.Donations=Donations;
  }
  public void setTicketCost(double TicketCost)
  {
    this.TicketCost=TicketCost;
  }
  public void setGovtFunds(double GovtFunds)
  {
    this.GovtFunds=GovtFunds;
  }
  public void setMerchandiseMaking(double MerchandiseMaking)
  {
    this.MerchandiseMaking=MerchandiseMaking;
  }
  public void setMerchandiseSelling(double MerchandiseSelling)
  {
    this.MerchandiseSelling=MerchandiseSelling;
  }

  public double getEmpSalary()
  {
    return EmpSalary;
  }
  public double getFoodCost()
  {
    return FoodCost;
  }
  public double getMaintenanceService()
  {
    return MaintenanceService;
  }
  public double getDonations()
  {
    return Donations;
  }
  public double getTicketCost()
  {
    return TicketCost;
  }
  public double getGovtFunds()
  {
    return GovtFunds;
  }
  public double getMerchandiseMaking()
  {
    return MerchandiseMaking;
  }
  public double getMerchandiseSelling()
  {
    return MerchandiseSelling;
  }
	public double CalculateProfit()
  {
    ProfitMade=(Donations + TicketCost + GovtFunds + MerchandiseSelling) - (MerchandiseMaking+EmpSalary+FoodCost+MaintenanceService);
    return ProfitMade;
  }
}

class Employee extends Admin //implements Check
{
  String name;
  int id;
  private ArrayList<Integer> EmployeeID=new ArrayList<Integer>();
  private ArrayList<String> EmployeeName=new ArrayList<String>();
  private ArrayList<String> EmployeeUsername=new ArrayList<String>();
  private ArrayList<String> EmployeePassword=new ArrayList<String>();
  public ArrayList<String> Role=new ArrayList<String>();
  public ArrayList<Integer> Experience=new ArrayList<Integer>();
  private ArrayList<Double> Salary=new ArrayList<Double>();
  public HashMap<Integer,ArrayList<Object>> EmployeeHash=new HashMap<Integer,ArrayList<Object>>();
  Employee(String name,int id)
    {
        this.name=name;
        this.id=id;
    }

  public void calcSal()
  {	
    double base=0;
    for (int i=0;i<30;i++)
    {
      if (Role.get(i).equals("Feeder"))
    {
      base= 15000;
    }
    else if (Role.get(i).equals("Caregiver"))
    {
      base= 20000;
    }
    Salary.add(base+ Experience.get(i)* 1.07) ;

    }
    
  }

  public void readFile()
  {
    try (BufferedReader br = new BufferedReader(new FileReader("Employees.csv"))) 
    {
      String line;
      int count=0;
      while ((line = br.readLine()) != null) 
      {
        String[] values = line.split(",");
        int key = Integer.parseInt(values[0]);
        ArrayList<Object> data = new ArrayList<>();
        EmployeeID.add(Integer.parseInt(values[0]));

        data.add(values[1]);
        EmployeeName.add(values[1]);   
        data.add(values[2]);
        EmployeeUsername.add(values[2]);
        data.add(values[3]);
        EmployeePassword.add(values[3]);
        data.add(values[4]);
        Role.add(values[4]);
        data.add(Integer.parseInt(values[5]));
        Experience.add(Integer.parseInt(values[5]));
        data.add(Double.parseDouble(values[6]));
        Salary.add(Double.parseDouble(values[6]));

        EmployeeHash.put(key,data);
      }
    }
  
    catch (IOException e) 
    {
      e.printStackTrace();
    }
  }

  public boolean authenticate(int ID)
  {

    int flag=0;
    try (BufferedReader br = new BufferedReader(new FileReader("Employees.csv"))) 
    {
      String line;
      while ((line = br.readLine()) != null) 
      {
        String[] values = line.split(",");
        if(ID==Integer.parseInt(values[0]))
        {
            flag=1;
        }
      }
    }

    catch (IOException e) 
    {
      e.printStackTrace();
    }
    if (flag==1)
    {
      return true;
    }
    else
    {
     	return false;
    }
  }

  public String toString()
    {
        return "Name: "+name+" emp_id: "+id;
    }
}

class Time {
    int hours;
    int mins;
  
    Time(int h, int m) {
        hours = h;
        mins = m;
    }
  
    public String toString() {
        return hours + ":" + mins;
    }
  }
  
  class Caregiving 
  {
    String animal;
    Employee emp;
    Time t_start;
    Time t_end;
  
    Caregiving(String a, Employee e, Time t_s, Time t_e) {
        animal = a;
        emp = e;
        t_start = t_s;
        t_end = t_e;
    }
  
    public String toString() {
        return emp + " " + animal + " " + t_start + " - " + t_end;
    }
  }
  
  class Meals 
  {
    String animal;
    Employee emp;
    Time t_start;
    Time t_end;
  
    Meals(String a, Employee e, Time t_s, Time t_e) {
        animal = a;
        emp = e;
        t_start = t_s;
        t_end = t_e;
    }
  
    public String toString() {
        return emp + " " + animal + " " + t_start + " - " + t_end;
    }
  }

class InvalidLoginException extends Exception
{
    InvalidLoginException()
    {
        super("Invalid Login!");
    }
}

class InsufficientEnclosureException extends Exception
{
  InsufficientEnclosureException()
    {
        super("Insufficient Enclosures");
    }
}

class Main
{
    public static void main(String[] args) 
    {
        Scanner sc= new Scanner(System.in);
        char mainop;

        do
        {
            System.out.println("MAIN MENU\n\ta)Admin\n\tb)Customer\n\tc)Employee\n\td)EXIT");
            mainop=sc.next().charAt(0);

            Animal ObjArr[]=new Animal[15];
                                        
                        ObjArr[14] =new Animal("Macaus","Bird") ;
                        ObjArr[12] =new Animal("Parakeets","Bird") ;
                        ObjArr[11] =new Animal("Sea-Gull","Bird") ;
                        ObjArr[13] =new Animal("Pelicans","Bird") ;
                        ObjArr[10] =new Animal("Flamingo","Bird") ;
                        ObjArr[9] =new Animal("Tortoise","Amphibian") ;
                        ObjArr[8] =new Animal("Snake","Reptile") ;
                        ObjArr[7] =new Animal("Crocodile","Reptile") ;
                        ObjArr[5] =new Animal("Dolphin","Aquatic") ;
                        ObjArr[3] =new Animal("Giraffe","Mammal(H)") ;
                        ObjArr[4] =new Animal("Panda","Mammal(H)") ;
                        ObjArr[6] =new Animal("Fish","Aquatic") ;
                        ObjArr[0] =new Animal("Tiger","Mammal(C)") ;
                        ObjArr[1] =new Animal("Elephant","Mammal(H)") ;
                        ObjArr[2] =new Animal("Deer","Mammal(H)") ;

                        HashMap<String, Caregiving> map_c = new HashMap<String, Caregiving>();
                        HashMap<String,Meals> map_m = new HashMap<String,Meals>();
                        String[] caretakers = { "Eric", "Jonathan", "Larry", "Brandon", "Gregory","Tara","Alex", "Anant","Jay","Ranbir","Dennis","Taksh","Chaitanya","Nathan","Henry" };
                        String[] feeders = { "Shyam", "Marcus", "Bairav", "Luna", "Luke", "Robert", "Micheal", "Ram", "Rahul", "Sunitha", "Rajesh", "Suresh", "Vijay", "Vinod", "Usha" };
                        String[] ani_caretakers = { "Macaus", "Parakeets", "Sea-Gull", "Pelicans", "Flamingo","Tortoise","Snake","Crocodile","Dolphin","Giraffe","Panda","Fish","Tiger","Elephant","Deer"};
                        String[] ani_feeders = { "Tiger", "Elephant", "Deer", "Giraffe", "Panda", "Dolphin", "Fish", "Crocodile", "Snake","Tortoise", "Flamingo", "Sea-Gull", "Parakeets", "Pelicans", "Macaus" };
                        Time[] t = new Time[30];
                        int k = 0;
                        for(int i = 0;i < 30;i++){
                            int p = i%4;
                            t[i] = new Time(10+k,15*p);
                            if(p == 3 && i != 0)
                                k++;
                        }
                        Employee[] arr;
                        arr = new Employee[30];
                        for (int i = 0; i < 15; i++)
                            arr[i] = new Employee(feeders[i], i + 1);
                        for (int i = 15; i < 30; i++)
                            arr[i] = new Employee(caretakers[i-15],i + 1);
                        Caregiving[] c;
                        c = new Caregiving[15];
                        for (int i = 15; i < 30; i++)
                            c[i-15] = new Caregiving(ani_caretakers[i-15], arr[i], t[i-15], t[i-15 + 1]);
                        for (int i = 0; i < 15; i++)
                            map_c.put(ani_caretakers[i], c[i]);
                        Meals[] m;
                        m = new Meals[15];
                        for(int i = 0;i < 15;i++)
                            m[i] = new Meals(ani_feeders[i],arr[i],t[i],t[i+1]);
                        //System.out.println("Caregivers: ");
                        for(int i = 0;i < 15;i++){
                            map_m.put(ani_feeders[i],m[i]);
                            //System.out.println(c[i]);
                        }
            switch(mainop)
            {
                case ('a'):
                {
                    Admin AdminObj=new Admin();
                    String User,Pwd;
                    System.out.println("Enter the ADMIN username: ");
                    sc.nextLine();
                    User=sc.nextLine();
                    System.out.println("Enter the ADMIN password: ");
                    Pwd=sc.nextLine();
                    if (AdminObj.authenticate(User,Pwd)==false)
                        {
                           try{ throw new InvalidLoginException();}
                            //break;
                            catch (InvalidLoginException e1)
                    {
                        System.out.println(e1);
                    }
                        }
                    else
                    {
                        
                        Revenue RevenueObj=new Revenue();
                       
                            int adop;
                            do
                            {
                                System.out.println("ADMIN MENU\n\t1)Display shows\n\t2)Update priority\n\t3)Add Animal\n\t4)Check animal health\n\t5)Calculate revenue\n\t6)View Caregiving Schedule\n\t7)View Meal Schedule\n\t8)EXIT");
                                adop=sc.nextInt();

                                switch(adop)
                                {
                                    case(1):
                                    {
                                        AdminObj.Display_show();
                                        break;
                                    }

                                    case(2):
                                    {
                                        AdminObj.priority_update();
                                        break;
                                    }

                                    case(3):
                                    {
                                        String sp, type;
                                        System.out.println("Enter the species name: ");
                                        sc.nextLine();
                                        sp=sc.nextLine();
                                        System.out.println("Enter the type: ");
                                        type=sc.nextLine();
                                        for(Animal i : ObjArr)
                                        {
                                            i.readFile();
                                            
                                        }
                                        // for(Animal i : ObjArr)
                                        // {
                                        //   i.writeAll();
                                        // }
                                        
                                        Animal temp=new Animal(sp,type);
                                      
                                        sc.nextLine();
                                        sc.nextLine();

                                        /*temp.readFile();
                                        temp.writeAll();*/
                                        for (Animal i: ObjArr)
                                        {
                                            if(i.Species.equals(sp))
                                            {
                                                temp=i;
                                            }
                                            // else
                                            // {i.readFile();
                                            // System.out.println(i.AnimalHash);
                                            // i.writeAll();}
                                        }
                                        temp.readFile();
                                        String sub;
                                        int age;
                                        boolean hl;

                                        System.out.println("Enter the subspecies: ");

                                        sub=sc.nextLine();

                                        System.out.println("Enter the age: ");
                                        age=sc.nextInt();

                                        System.out.println("Enter the health status: ");
                                        sc.nextLine();
                                        hl=Boolean.parseBoolean(sc.nextLine());

                                        temp.write(sub,age,hl);
                                        temp.readFile();
                                        temp.writeAll(ObjArr);
                                        break;
                                    }

                                    case(4):
                                    {
                                        String search;
                                        System.out.println("Enter animal id of the animal you want to check: ");
                                        sc.nextLine();
                                        search=sc.nextLine();
                                        
                                        String sp, type;
                                        System.out.println("Enter the species name: ");
                            
                                        sp=sc.nextLine();
                                        System.out.println("Enter the type: ");

                                        type=sc.nextLine();

                                        Animal temp=new Animal(sp,type);
                                        for (Animal i: ObjArr)
                                        {
                                            if(i.Species.equals(sp))
                                            {
                                                temp=i;
                                            }
                                        }
                                        temp.readFile();
                                        boolean x=temp.isHealthy(search);
                                        if (x)
                                        {
                                            System.out.println("The animal is healthy");
                                        }
                                        else
                                        {
                                            System.out.println("The animal is unhealthy");
                                        }
                                        break;
                                    }

                                    case(5):
                                    {
                                        Revenue rev=new Revenue();                                        
                                        double empsal,fdcost,ms,tc,gf,mmake,msell,pro;

                                        System.out.println("Enter Total Employee Salary: ");
                                        empsal=sc.nextDouble();
                                        rev.setEmpSalary(empsal);

                                        System.out.println("Enter Food Cost: ");
                                        fdcost=sc.nextDouble();
                                        rev.setFoodCost(fdcost);

                                        System.out.println("Enter Total Maintenence Service Cost: ");
                                        ms=sc.nextDouble();
                                        rev.setMaintenanceService(ms);

                                        System.out.println("Enter Total Donations: ");
                                        ms=sc.nextDouble();
                                        rev.setMaintenanceService(ms);

                                        System.out.println("Enter Total TicketCost: ");
                                        tc=sc.nextDouble();
                                        rev.setTicketCost(tc);

                                        System.out.println("Enter Total Govt Funds: ");
                                        gf=sc.nextDouble();
                                        rev.setGovtFunds(gf);

                                        System.out.println("Enter Total Merchandise Making cost: ");
                                        mmake=sc.nextDouble();
                                        rev.setMerchandiseMaking(mmake);

                                        System.out.println("Enter Total Merchandise Selling: ");
                                        msell=sc.nextDouble();
                                        rev.setMerchandiseSelling(msell);

                                        pro=rev.CalculateProfit();
                                        System.out.println("The profit made is: "+pro);
                                        break;
                                    }

                                    case(6):
                                    {
                                        System.out.println("Caregiving Schedule: ");
                                        for(int i = 0;i < 15;i++)
                                            { 
                                              if (i<=7)
                                              {
                                                System.out.print(c[i+7].emp+"  "+c[i+7].animal+"\t");
                                              }  
                                              else
                                              {
                                                System.out.print(c[i-8].emp+"  "+c[i-8].animal+"\t");
                                              }
                                              System.out.println(c[i]);
                                              }
                                        break;
                                    }

                                    case(7):
                                    {
                                        System.out.println("Feeding Schedule: ");
                                        for(int i = 0;i < 15;i++)
                                        {   System.out.print(c[14-i].emp+"  "+c[14-i].animal+"\t");
                                            System.out.println(m[i]);}
                                        break;
                                    }

                                }
                            } while (adop!=8);
                        
                    
                    } break;
                }

                case('b'):
                {
                    // String Email,Pwd;
                    // System.out.println("Enter the CUSTOMER Email: ");
                    // sc.nextLine();
                    // Email=sc.nextLine();
                    // System.out.println("Enter the CUSTOMER password: ");
                    // Pwd=sc.nextLine();
                
                    Customer CustomerObj=new Customer();
                    CustomerObj.readFile();
                    String Email = new String();
                    String Pwd = new String();
                          int custop;
                            do
                            {
                                System.out.println("CUSTOMER MENU\n\t1)Login\n\t2)Sign Up\n\t3)Book Tickets\n\t4)View Favourite Animal Schedule\n\t5)EXIT");
                                custop=sc.nextInt();
                                switch(custop)
                                {
                                    case(1):
                                    {   
                                        System.out.println("Enter the CUSTOMER Email: ");
                                        sc.nextLine();
                                        Email=sc.nextLine();
                                        System.out.println("Enter the CUSTOMER password: ");
                                        Pwd=sc.nextLine();
                                        if (CustomerObj.authenticate(Email,Pwd)==false)
                                        {
                                            try{
                                                throw new InvalidLoginException();
                                            }
                                            catch(InvalidLoginException e2){
                                                System.out.println(e2);
                                                break;
                                            }
                                        }
                                        else{
                                            continue;
                                        }
                                        
                                    }

                                    case(2):
                                    {   //String Email,Pwd;
                                        System.out.println("Enter the CUSTOMER Email: ");
                                        sc.nextLine();
                                        Email=sc.nextLine();
                                        System.out.println("Enter the CUSTOMER password: ");
                                        Pwd=sc.nextLine();
                                        String Firstname,  Favourite_Animal;
                                        int Age, Donations;

                                        System.out.println("Enter Firstname: ");
                                        Firstname=sc.nextLine();
                                        System.out.println("Enter Age: ");
                                        Age=sc.nextInt();
                                        sc.nextLine();
                                        System.out.println("Enter Donations amount: ");
                                        Donations=sc.nextInt();
                                        sc.nextLine();
                                        System.out.println("Enter Favourite Animal: ");
                                        Favourite_Animal=sc.nextLine();
                                        ArrayList<Object> temp = new ArrayList<Object>();
                                        temp.add(Firstname);
                                        temp.add(Age);
                                        temp.add(Pwd);
                                        temp.add(Donations);
                                        temp.add(Favourite_Animal);
                                        temp.add(0);
                                        CustomerObj.customerHash.put(Email,temp);
                                        for(String i : CustomerObj.customerHash.keySet())
                                        {
                                            System.out.println(i+CustomerObj.customerHash.get(i));
                                        }
                                        CustomerObj.write();
                                        System.out.println("Details Stored successfully!!");
                                        break;

                                    }
                                    case(3):
                                    {
                                        System.out.println("Choose type of ticket to buy \n1.Individual regular \n2.Individual Premium \n 3.Group(Institution)\n4.Group(Family)\n 5.Safari\n");
                                        int ch;
                                        ch=sc.nextInt();
                                        switch(ch)
                                        {
                                            case(1):
                                            {
                                                System.out.println("Cost per ticket is 150");
                                                break;
                                            }
                                            case(2):
                                            {   System.out.println("Cost per ticket is 600");
                                                break;
                                            }
                                            case(3):
                                            {   System.out.println("Cost per ticket is 100(Minimum 20)");
                                                break;
                                            }
                                            case(4):
                                            {
                                                System.out.println("Cost per ticket is 120(Minimum 5)");
                                                break;
                                            }
                                            case(5):
                                            {
                                                System.out.println("Cost per ticket is 1000");   
                                                break;
                                            }       
                                        }
                                            for(String i:CustomerObj.customerHash.keySet())
                                            {   
                                                if(i.equals(Email))
                                                {   
                                                    ArrayList<Object> Arr = (CustomerObj.customerHash.get(i));
                                                    int pre_vist= (Integer)(Arr.get(5));
                                                    if(pre_vist>2 && pre_vist<5)
                                                    {
                                                        System.out.println("You have more than 2 vists, hence 10% discount");
                                                    }
                                                    else if(pre_vist>5)
                                                    {
                                                        System.out.println("You have more than 5 visits, hence 20% discount");
                                                    }
                                                    CustomerObj.customerHash.get(i).set(5,pre_vist+1);
                                                    CustomerObj.write();
                                                    System.out.println("Updated your number of visits");
                                                }
                                            }
                                            
                                            break;
                                        }

                                        case (4):
                                        {
                                          String Favourite_Animal;
                                          sc.nextLine();
                                          int count=0, flag=0;
                                          System.out.println("Enter you favourite animal: ");
                                          Favourite_Animal=sc.nextLine();
                                          for (String search: ani_caretakers)
                                          {
                                            if (search.equals(Favourite_Animal))
                                            {
                                              //count++;
                                              flag=1;
                                              System.out.println(t[count]+":"+t[count+1]+"    "+caretakers[count]);
                                              break;
                                            }
                                            count++;
                                          }
                                          if (flag==0)
                                          {
                                            System.out.println("Sorry! We do not have this animal");
                                          }
                                          break;
                                        }
                                        }
                                    
                                    }
                                
                            while(custop!=5);
                    
                    break;
            }

                case('c'):
                {   String empname;
                    System.out.println("Enter employee name");
                    sc.nextLine();
                    empname=sc.nextLine();
                    int EmpID;
                    System.out.println("Enter the Employee ID: ");
                    EmpID= sc.nextInt();
                    sc.nextLine();
                    // User=sc.nextLine();
                    // System.out.println("Enter the Employee password: ");
                    // Pwd=sc.nextLine();
                    Employee EmployeeObj=new Employee(empname,EmpID);
                    ArrayList CurrentEmp ; 
                    if (EmployeeObj.authenticate(EmpID)==false)
                        {
                           try{ throw new InvalidLoginException();}
                            //break;
                            catch (InvalidLoginException e1)
                            {
                                System.out.println(e1);
                            }
                        }
                    else{
                        EmployeeObj.readFile();
                        for(Integer i : EmployeeObj.EmployeeHash.keySet())
                        {
                            if(i.equals(EmpID))
                            {
                                CurrentEmp = EmployeeObj.EmployeeHash.get(i);
                                System.out.println("The details of the employee are: ");
                                System.out.println("ID Name  Email  Password  Role  Visits Salary");
                                System.out.print(EmpID+".");
                                for(Object j : CurrentEmp)
                                {System.out.print(j+" ");}
                                System.out.println();
                                break;
                            }
                            
                        }
                                          
                    }
                }
            }
        }while(mainop!='d');
    } 
}
