/*
 * Joe Bagofdonuts 1.0
 * 
 * War -- a card game using a deck of cards, where the user chooses
 *        a card and if it's greater than the computer's choice, user wins,
 *        in a tie, both choose a second card, and so on, until one wins
 */


import javax.swing.JOptionPane;
import java.util.ArrayList;

public class GameOfWar
{
    int [] nums; 
    int[] cardValues;
    char [] cardSuits;
    String [] cardNames;
    char suit;
    JOptionPane jop;
    int playAgain;
    ArrayList<Integer> user;
    ArrayList<Integer> comp;
    int userWins, compWins;
    
    public GameOfWar()
    {
      nums = new int[52];
      cardValues = new int[52];
      cardNames = new String[52];
      cardSuits = new char[52];
      jop = new JOptionPane();
      user = new ArrayList<Integer>();
      comp = new ArrayList<Integer>();
      playAgain = 0;
      userWins = 0;
      compWins = 0;
    }
    
    public void makeDeck()
    {
    int counter = 0, cardVal;
    for(int i = 1; i<5; i++)
    {
      cardVal = 2;
      for(int j = 2; j<15; j++)
      {
        
        // if i = 1, suit = clubs, 2->hearts, 3->spades, 4-> diamonds
        if(i==1) suit = 'C';
        else if (i==2) suit = 'H';
        else if (i==3) suit = 'S';
        else suit = 'D';
        
        cardSuits[counter]=suit;
        cardValues[counter]=cardVal;
        if(j<11)
          cardNames[counter]=cardVal+"";
        else if (j==11)
          cardNames[counter]="J";
        else if (j==12)
          cardNames[counter]="Q";
        else if (j==13)
          cardNames[counter]="K";
        else
          cardNames[counter]="A";
        //System.out.print(j+suit+", ");
        nums[counter]=counter;
        //System.out.print(nums[i*(j-2)]+", ");
        counter++;
        cardVal++;
      }
    }// end generate deck for loops

  }// end makeDeck method
  
  public ArrayList<Integer> dealDeck()
  {
    // local variable, rand, for the random integer "card"
    int rand;
    ArrayList <Integer> newArray = new ArrayList<Integer>();
    
    for(int count = 0; count < 26; count++)
    {
      // generate a random # for comp
      do
      {
        rand = (int)(52*Math.random());
      }
      while(nums[rand] == 99); // keep looking for a non-chosen value
      
      newArray.add(rand);
      nums[rand] = 99; // set any chosen index in num to the value 99
      
    }
    return newArray;
  } // end makeDeck method
  
  public void play()
  {
    makeDeck();
    user = dealDeck(); // user's hand
    comp = dealDeck(); // computer's hand
    int card = 0;
    while(card < user.size() && card<comp.size())
    {
      compareCards(card);
        
      
      card = (card+1)%(Math.min(user.size(), comp.size()));
      jop.showMessageDialog(null, "Score: User: "+user.size()+", Comp: "+comp.size());
    }
  }
  
  // this checks to see who won
  public boolean compareCards(int index)
  {
    System.out.println("User's card: "+cardNames[user.get(index)]
                         +cardSuits[user.get(index)]+
                       ", comp's card: "+cardNames[comp.get(index)]
                         +cardSuits[comp.get(index)]);
      if(cardValues[user.get(index)]>cardValues[comp.get(index)])
      {
        System.out.println("User won");
        userWins++;
        user.add(comp.remove(index));
        return true;
      }
        if(cardValues[user.get(index)]<cardValues[comp.get(index)])
      {
          System.out.println("Computer won!");
          compWins++;
          comp.add(user.remove(index));
          return false;
      }
      else
      {
        boolean winner;
        System.out.println("Tied");
        if(user.size()>index+1 && comp.size()>index+1)
        {
          if(compareCards(index+2))
          {
            user.add(comp.remove(index+1));
            return true;
          }
          else
          {
            comp.add(user.remove(index+1));
            return false;
          }        
        } 
        
      }
  }
  
  public static void main(String[] args)
  {
    GameOfWar g = new GameOfWar();
    g.play();
    System.out.println("Score: User: "+g.userWins+", Comp: "+g.compWins);
  } 
    
    
    
    
    
    
    
    
    
    
    
    
    
  
} // end class
