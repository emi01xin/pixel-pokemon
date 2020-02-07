import java.util.*;
import java.io.*;

class Pokemon{
  private String name, type, resist, weak;
  private int HP, maxHP, energy, numAtks;
  private String[][] atks;  
  private boolean KO, stunEffect, disableEffect;
  
  public Pokemon(String s){
    
    // STATS
    
    String [] stats = s.split(",");     // splits pokemon stats
    
    name = stats[0];
    HP = Integer.parseInt(stats[1]);
    maxHP = HP;     // max HP pokemon can have
    energy = 50;      // sets energy to 50
    type = stats[2];
    resist = stats[3];
    weak = stats[4];
    numAtks = Integer.parseInt(stats[5]);
    
    String[][] atkStats = new String[numAtks][4];     // list of pokemon attacks [ <attack name>, <energy cost>, <damage>, <special> ]
    
    for (int i = 0; i < atkStats.length; i ++){     // splits attack stats     
      for (int j = 0; j < 4; j ++){
        atkStats[i][j] = stats[6 + 4*i + j];
      }
    }
    
    atks = atkStats;
  
  }
  
  // ATTACK
  
  public void attack(Pokemon poke, int t){      // takes in pokemon being attacked and attack as parameters
  
    Scanner kb = new Scanner(System.in);
    int a = 0;
    
    //  PLAYER ATTACK
    
    if (t == 0){
      System.out.println("\nChoose attack:");
      displayAtks();
      
      a = kb.nextInt();     // chosen attack
      
      while (Integer.parseInt(atks[a][1]) > energy){      // checks if pokemon has enough energy to perform attack 
        System.out.println("Not enough energy.");
        a = kb.nextInt();  
      } 
      
      System.out.println("\n" + name.toUpperCase() + " attacks !");
    }
    
    // OPPONENT ATTACK
    
    if (t == 1){
      a = (int)(Math.random()*(numAtks));      // opponent choose random attack
      
      while (Integer.parseInt(atks[a][1]) > energy){      // checks if pokemon has enough energy to perform attack 
        a = (int)(Math.random()*(numAtks));     // chosen attack
      } 
      
      System.out.println(name.toUpperCase() + " attacks !");
    }
    
    // ATTACK STATS    
    
    String atkName = atks[a][0];      // splits attack stats
    int energyCost = Integer.parseInt(atks[a][1]);
    int dmg = Integer.parseInt(atks[a][2]);
    String specName = atks[a][3];
    
    energy = Math.max(0, energy - energyCost);      // subtracts attack energyCost from pokemon's energy
                                                    // cannot be below 0
    if (disableEffect == true){     //apply disable effect
      dmg = Math.min(0, dmg - 10);
    }
    
    // RESISTANCE & WEAKNESS
    
    if((poke.resist).equals(type)){     // checks resistance         [ if attacking pokemon type equals resistance, attack is cut in half ]
      dmg /= 2;
    }
    
    if((poke.weak).equals(type)){     // checks weakness         [ if attacking pokemon type equals weakness, attack is doubled ]
      dmg *= 2;
    }
    
    // SPECIAL ATTACKS
    
    int s = (int)(Math.random()*2);     // 50% chance
                                        // 0 - performs special attack
    
    if (specName.equals("stun")){     // stun
      if (s == 0){
        poke.stun();
        System.out.println((poke.name).toUpperCase() + " has been stunned!");
      }
    }
    
    else if (specName.equals("wild card")){     // wild card
      if (s == 1){
        dmg = 0;
      }
    }
    
    else if (specName.equals("wild storm")){      // wild storm
      while (s == 0){
        dmg = Integer.parseInt(atks[a][2]);
        poke.HP = Math.max(0, poke.HP - dmg);
        s = (int)(Math.random()*2); 
      }
      
      dmg = 0;
    }
    
    else if (specName.equals("disable")){       // disable
      if (poke.disableEffect == false){     //checks if pokemon has already been disabled
        poke.disable();
        System.out.println((poke.name).toUpperCase() + " has been disabled!");
      }
    }
    
    else if (specName.equals("recharge")){      // recharge
      energy = Math.max(50, energy + 20);
    }
    
    poke.HP = Math.max(0, poke.HP - dmg);
    
    System.out.println(name.toUpperCase() + " uses " + atkName.toUpperCase() + " !");
    System.out.println((poke.name).toUpperCase() + " loses " + dmg + " HP !");
  }
  
  public void stun(){
    stunEffect = true;  
  }
  
  public void disable(){
    disableEffect = true;
  }
  
  public void unstun(){
    stunEffect = false;  
  }
  
  public void undisable(){
    disableEffect = false;
  }
  
  public boolean stunned(){
    return stunEffect;
  }
  
  public void heal(){     // heals pokemon's HP
    HP = Math.min(maxHP, HP + 20);      // cannot be above max HP
  }
  
  public void charge(){     // charges pokemon's energy
    energy = Math.min(50, energy + 10);     // cannot be above 50
  }
  
  public boolean KO(){      //checks if pokemon HP is 0
    if (HP <= 0){
      return true;
    }
    
    else{
      return false;
    }
  }
  
  // DISPLAY
  
  public String displayMenu(){
    return String.format("%-12s%3d%10s%10s%10s", name.toUpperCase(), HP, type.toUpperCase(), resist.toUpperCase(), weak.toUpperCase());
  }
    
  public void displayStats(){
    if (resist.equals(" ")){
      resist = "N / A";
    }
    
    if (weak.equals(" ")){
      weak = "N / A";
    }
    
    System.out.printf("\n%s\n%-15s %d / %d\n%-15s %d / %d\n%-15s %s\n%-15s %s\n%-15s %s", name.toUpperCase(), " HP:", HP, maxHP, " ENERGY:", energy, 50, " TYPE:", type.toUpperCase(), " RESISTANCE:", resist.toUpperCase(), " WEAKNESS:", weak.toUpperCase());
  }
  
  public void displayAtks(){
    System.out.println("\n ATTACKS:");
    
    for (int i = 0; i < numAtks; i ++){
      if (atks[i][3].equals(" ")){
        atks[i][3] = "N / A";
      }
      
      System.out.printf("   %d. %s\n%-15s %d\n%-15s %d\n%-15s %s\n", i, (atks[i][0]).toUpperCase(), "        ENERGY:", Integer.parseInt(atks[i][1]), "        DMG:", Integer.parseInt(atks[i][2]), "        SPECIAL:", (atks[i][3]).toUpperCase());
    }
  }
  
  public void getStats(Pokemon poke){
    System.out.printf("\n   %-5s\n   %-5s   %6d / %d\n   %-5s%6d / %d\n", name.toUpperCase(), " HP:", HP, maxHP, " ENERGY:", energy, 50);
    System.out.printf("\n   %-5s\n   %-5s   %6d / %d\n   %-5s%6d / %d\n", (poke.name).toUpperCase(), " HP:", poke.HP, poke.maxHP, " ENERGY:", poke.energy, 50);
  }
  
  public String getName(){
    return name;
  }
  
  public boolean checkEnergy(){     // checks if pokemon has enough energy to perform attack
    boolean e = true;
    for (int i = 0; i < numAtks; i ++){
      if (Integer.parseInt(atks[i][1]) > energy){
        e = false;
      }
    }
    
    return e;
  }

}
  