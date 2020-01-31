/*-
 * Author: Justin Chan
 * Email: jachan@wisc.edu
 * Lecture: LEC 1
 * A game about exploring rooms
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A basic input/output program
 * 
 * @author averychan
 *
 */
public class Main {

  private static final String title = "Justin Chan jachan@wisc.edu LEC 1";
  private static final Scanner sc = new Scanner(System.in); // TODO change to final
  private static Scanner text;
  private static PrintWriter pw;

  protected enum direction {
    UP, DOWN, LEFT, RIGHT
  }

  /**
   * Initializes resources.
   */
  private static void initialize() {
    try {
      text = new Scanner(new File(getFileName(true)));
    } catch (FileNotFoundException e) {
      System.out.println("No file of that name was found.");
      // TODO hadnling invalid more than this?
    }
    try {
      pw = new PrintWriter(new File("output.txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    // System.out.println("Text file says: " + text.nextLine());
  }

  /**
   * Closes resources.
   */
  private static void terminate() {
    // close streams
    sc.close();
    if (text != null) {
      text.close();
    }
  }

  private static String getFileName(boolean firstAsk) {
    if (firstAsk) {
      System.out.println("If you played before, what is the name of your input file?");
    }
    System.out.print("> ");
    String response = sc.nextLine();
    if (response.length() < 4) {
      System.out.println("Must be a text file (*.txt)");
      return getFileName(false);
    } else if (response.substring(response.length() - 4, response.length()).equals(".txt")) {
      return response;
    } else {
      System.out.println("Must be a text file (*.txt)");
      return getFileName(false);
    }
  }

  private static void printOpenMenu() {
    boolean invalidInput = true;
    String name;
    /*
     * while (invalid input) ask test use
     */
    System.out.println(title);
 // @formatter:off
    System.out.println("\nWelcome to room explorer.\n\n" + 
        "This is an example room with a hallway to the right called room 0:\n\n" + 
        " ┌───┐\n" + 
        " │ 0 ├─\n" + 
        " └───┘\n\n" + 
        "You can navigate to the right by typing w, a, s, d when prompted.\n\n" + 
        "Your goal is to reach the final room marked with an X:\n\n" + 
        " ┌───┐\n" + 
        " │ X │\n" + 
        " └───┘\n\n" + 
        "GO!\n\n" + 
        "===================");
    // @formatter:on
  }

  /**
   * Asks
   * 
   * @param firstAsk
   * @return An movement direction.
   */
  private static direction nextMovement(Room rm, boolean firstAsk) {
    if (firstAsk) {
      System.out.println("Which direction?");
      if (rm.hasUp()) {
        System.out.println("  [w] up");
      } else {
        System.out.println("  [ ]");
      }
      if (rm.hasDown()) {
        System.out.println("  [s] down");
      } else {
        System.out.println("  [ ]");
      }
      if (rm.hasLeft()) {
        System.out.println("  [a] left");
      } else {
        System.out.println("  [ ]");
      }
      if (rm.hasRight()) {
        System.out.println("  [d] right");
      } else {
        System.out.println("  [ ]");
      }
    }
    System.out.print("> ");
    char response = sc.nextLine().charAt(0);
    switch (response) {
      case 'w':
        if (rm.hasUp()) {
          return direction.UP;
        } else {
          System.out.println("No room above.");
          return nextMovement(rm, false);
        }
      case 's':
        if (rm.hasDown()) {
          return direction.DOWN;
        } else {
          System.out.println("No room below.");
          return nextMovement(rm, false);
        }
      case 'a':
        if (rm.hasLeft()) {
          return direction.LEFT;
        } else {
          System.out.println("No room to the left.");
          return nextMovement(rm, false);
        }
      case 'd':
        if (rm.hasRight()) {
          return direction.RIGHT;
        } else {
          System.out.println("No room to the right.");
          return nextMovement(rm, false);
        }
      default:
        System.out.println("Invalid input.");
        return nextMovement(rm, false);
    }
  }


  public static void main(String[] args) {
    initialize();
    printOpenMenu();

    Room root = new Room(); // TODO random generation
    root.generateRoom(direction.UP).generateRoom(direction.UP);
    root.generateRoom(direction.DOWN).generateRoom(direction.DOWN);
    root.generateRoom(direction.LEFT).generateRoom(direction.LEFT);
    root.generateRoom(direction.RIGHT).generateRoom(direction.RIGHT).setGoal(true);

    Room currRoom = root;
    direction move;

    while (true) {
      currRoom.printRoom();
      if (currRoom.isGoal()) {
        System.out.println("You win!");
        break;
      }
      move = nextMovement(currRoom, true);
      switch (move) {
        case UP:
          currRoom = currRoom.getUp();
          break;
        case DOWN:
          currRoom = currRoom.getDown();
          break;
        case LEFT:
          currRoom = currRoom.getLeft();
          break;
        case RIGHT:
          currRoom = currRoom.getRight();
          break;
      }
      System.out.println("===============");
    }
    
    // temporary
    while (text.hasNext()) {
      System.out.println(text.nextLine());
    }
    pw.println("Some words.");
    pw.flush();
    
    terminate();
  }
}
