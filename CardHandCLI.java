//---------------------------------------------------------------------
// CardHandCLI.java        by Dale/Joyce/Weems                Chapter 6
//
// Allows user to organize a hand of playing cards.
// Uses a command line interface.
//----------------------------------------------------------------------
//RUN THIS FilE!!!!!!!!!!!!!!!
import java.util.Scanner;   

public class CardHandCLI
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
    final int HANDSIZE = 2;
    double funds = 100; //set to a value of your choice
    double bet = -1;
    int minValue = 999;
    int maxValue = -999;
    //int slot;

    //Card card;                       // playing card
    CardDeck deck = new CardDeck();  // deck of playing cards
    Card card3 = deck.nextCard();
    
    ABList<Card> hand = new ABList<Card>(HANDSIZE); // user's hand
    
    System.out.println("Ey, kid. I see ya' got some "+funds+" bucks there.");
    wait(1000);
    System.out.println("I'm gonn' be takin' those, but I ain't no criminal, heh.");
    wait(1000);
    System.out.println("Let's play sum' games of in-between, shall we?");
    wait(1000);
    System.out.println("And, for all intensive purposes, an ace is 'leven. No ifs, ands, or but's about it!");
    wait(1000);

    while(funds>0)
    {
      bet = -1;
      bet = bet(funds, scan);
      if(bet<1)
      {
        System.out.println("Somethin' went wrong with yer bettin'.");
        wait(1000);
        break;
      }
      funds-=bet;
      if(deck.getDeck().size()<10)
      {
        System.out.println("Seems we're runnin' low on cards. Don't worry, I know howta' shuffle!");
        wait(1000);
        deck.reset();
      }
      deck.shuffle();
      hand.add(deck.drawCard());  // deals 1st card and places into hand
      hand.add(deck.drawCard());
      
      System.out.println("Here's yer cards.");
      wait(1000);
      printCards(hand);
      //System.out.print(hand.get(0).getValue());
      String choice = "gingle";
      minValue = Math.min(hand.get(0).getValue(), hand.get(1).getValue());
      maxValue = Math.max(hand.get(0).getValue(), hand.get(1).getValue());
      wait(1000);
      if(minValue==maxValue)
      {
        System.out.println("Dang nabbit, they're both the same value! I'll win next round, you'll see!");
        funds+=bet*2;
        wait(1000);
      }
      else if(minValue==maxValue-1)
      {
        System.out.println("Oh. Not so great for you, but it sure is good for me, heh!");
        wait(1000);
      }
      else
      {
        System.out.println("Go on, big shot. Make your choice!");
        System.out.print("Options: Inside, Outside");
        if(bet<=funds)
        {
          System.out.println(", or Double Down!");
        }
        else
        {
          System.out.println(".");
        }

        while(choice.equals("gingle"))
        {
          choice = scan.nextLine();
          if(choice.equalsIgnoreCase("Inside"))
          {
            card3 = deck.drawCard();
            System.out.println("The dealer drew a "+card3);
            wait(1000);
            if(card3.getValue()>minValue&&card3.getValue()<maxValue)
            {
              System.out.println("Lucky guess.");
              funds+=bet*2;
            }
            else if(card3.getValue()==minValue||card3.getValue()==maxValue)
            {
              System.out.println("Yikes, bump!");
            }
            else
            {
              System.out.println("No hard feelings, but ya' guessed wrong.");
            }
          }
          else if(choice.equalsIgnoreCase("Outside"))
          {
            card3 = deck.drawCard();
            System.out.println("The dealer drew a "+card3);
            wait(1000);
            if(card3.getValue()<minValue||card3.getValue()>maxValue)
            {
              System.out.println("Lucky guess.");
              funds+=bet*2;
            }
            else if(card3.getValue()==minValue||card3.getValue()==maxValue)
            {
              System.out.println("Yikes, bump!");
            }
            else
            {
              System.out.println("No hard feelings, but ya' guessed wrong.");
            }
          }
          else if(choice.equalsIgnoreCase("Double Down"))
          {
            
          }
          else
          {
            System.out.println("Not an option. Don't make me choose for ya!");
            choice = "gingle";
          }
        }
      }
      System.out.println();
      System.out.println("-------------------------------------------------------------");
      System.out.println();
      System.out.println("Okay, next round!");
      wait(1000);
      hand.clear();
    }

    System.out.println("Welp, seems I've pulled all the dolla's I could outta' you.");
    wait(1000);
    System.out.println("Ye' played great, kid. But I gotta' go.");
    /*
    for (int i = 1; i < HANDSIZE; i++)
    {
      System.out.println("\nYour hand so far:");
      slot = 0;
      for (Card c: hand)
      {
        System.out.println(slot + "\n  " + c);
        slot++;
      }
      System.out.println(slot);
      
      card = deck.nextCard();      
      System.out.print("Slot between 0 and " + i + " to put " + card + " > ");
      slot = scan.nextInt();
      hand.add(slot, card);
    }

    System.out.println("\nYour final hand is:");
    for (Card c: hand)
       System.out.println("  " + c);
  */
  }

  public static void printCards(ListInterface<Card> array)
  {
    System.out.print("Your hand: ");
    for (int o = 0; o < array.size()-1; o++)
    {
      System.out.print(array.get(0)+", ");
    }
    System.out.println(array.get(array.size()-1));
  }

  public static boolean inRange(int num, int bound1, int bound2)
  {
    if(num>bound1&&num<bound2||num<bound1&&num>bound2)
      return true;
    return false;
  }

  public static void wait(int miliseconds)
  {
    try
    {
      Thread.sleep(miliseconds);
    }
    catch(Exception e)
    {
      System.out.println("Kid? You gonna get up? Oh, phew.");
    }
  }

  public static double bet(double funds, Scanner scanner)
  {
    double bet = -1;
    System.out.println("'kay. How much ya' wanna bet?");
    System.out.println("Remember, ye got "+funds+" dollas' to spend still.");
    while(bet<=0||bet>funds)
    {
      try{
        System.out.print("I'd like to bet: ");
        bet = Double.parseDouble(scanner.nextLine());
      }
      catch(Exception e){
        System.out.println("Kid. That ain't a number.");
      }
      if(bet == -1)
      {

      }
      else if(bet < 0)
      {
        System.out.println("Anybody ever tell ya' that negatives ain't real? No? Weird.");
        System.out.println("...well, we ain't playin' with those. Choose somethin' else.");
      }
      else if(bet == 0)
      {
        System.out.println("Zero? We ain't playing for fun, kid. Bet somethin' bigger.");
      }
      else if(bet <= funds*.1)
      {
        System.out.println("Good 'nough for me!");
      }
      else if(bet == funds)
      {
        System.out.println("Ooh! All in!");
      }
      else if(bet >= funds)
      {
        System.out.println("You and I both know ya' don't got that much moolah.");
      }
      else
      {
        System.out.println("'kay. Let's get a move on, shall we?");
      }
    }
    wait(1000);
    return bet;
  }
}