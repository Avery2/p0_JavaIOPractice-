/*-
 * Author: Justin Chan
 * Email: jachan@wisc.edu
 * Lecture: LEC 1
 * A game about exploring rooms
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A basic input/output program
 * 
 * @author averychan
 *
 */
public class Main {
  private static Scanner sc;
  private static Scanner text;

  public enum direction {
    UP, DOWN, LEFT, RIGHT
  }

  /**
   * Initializes resources.
   */
  public static void initialize() {
    // initialize
    sc = new Scanner(System.in);
    try {
      text = new Scanner(new File("stuff.txt"));
    } catch (FileNotFoundException f) {
      System.out.println("File not found");
    }
    // System.out.println("Text file says: " + text.nextLine());
  }

  public static void pr() {
    System.out.println("Hi");
  }

  /**
   * Closes resources.
   */
  public static void terminate() {
    // close streams
    sc.close();
    if (text != null) {
      text.close();
    }
  }

  /**
   * Asks
   * 
   * @param firstAsk
   * @return An movement direction.
   */
  public static direction getMovement(Room rm, boolean firstAsk) {
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
          return getMovement(rm, false);
        }
      case 's':
        if (rm.hasDown()) {
          return direction.DOWN;
        } else {
          System.out.println("No room below.");
          return getMovement(rm, false);
        }
      case 'a':
        if (rm.hasLeft()) {
          return direction.LEFT;
        } else {
          System.out.println("No room to the left.");
          return getMovement(rm, false);
        }
      case 'd':
        if (rm.hasRight()) {
          return direction.RIGHT;
        } else {
          System.out.println("No room to the right.");
          return getMovement(rm, false);
        }
      default:
        System.out.println("Invalid input.");
        return getMovement(rm, false);
    }
  }

  public static void printRoom(Room rm) {
    String tag;
    if (rm.isGoal()) {
      tag = "X";
    } else {
      tag = "" + rm.getId();
    }
    System.out.print("\n  ┌─");
    // up?
    if (rm.hasUp()) {
      System.out.print("┴");
    } else {
      System.out.print("─");
    }
    System.out.print("─┐\n");
    System.out.print(" ");
    // left?
    if (rm.hasLeft()) {
      System.out.print("─┤");
    } else {
      System.out.print(" │");
    }
    System.out.print(" " + tag + " ");
    // right?
    if (rm.hasRight()) {
      System.out.print("├─");
    } else {
      System.out.print("│");
    }
    System.out.print("\n");
    System.out.print("  └─");
    // down?
    if (rm.hasDown()) {
      System.out.print("┬");
    } else {
      System.out.print("─");
    }
    System.out.print("─┘\n\n");
  }

  public static void main(String[] args) {
    initialize();
    Room center = new Room();
    center.generateRoom(direction.UP).generateRoom(direction.UP);
    center.generateRoom(direction.DOWN).generateRoom(direction.DOWN);
    center.generateRoom(direction.LEFT).generateRoom(direction.LEFT);
    center.generateRoom(direction.RIGHT).generateRoom(direction.RIGHT).setGoal(true);
    Room currRoom = center;

    // currRoom.getLeft().setGoal(true);
    direction move;
    // @formatter:off
    System.out.println("Welcome to room explorer.\n\n" + 
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

    while (true) {
      printRoom(currRoom);
      if (currRoom.isGoal()) {
        System.out.println("You win");
        break;
      }
      move = getMovement(currRoom, true);
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
    terminate();
  }
}
