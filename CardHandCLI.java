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
    int funds = 100; //set to a value of your choice
    int bet = -1;
    //int slot;

    //Card card;                       // playing card
    CardDeck deck = new CardDeck();  // deck of playing cards
    
    ListInterface<Card> hand = new ABList<Card>(HANDSIZE); // user's hand
    
    deck.shuffle();
    hand.add(deck.drawCard());  // deals 1st card and places into hand
    hand.add(deck.drawCard());
    
    printCards(hand);
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
}