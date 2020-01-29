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
  private static Scanner keyboard;
  private static Scanner text;
  // ANSI escape sequences to color text
  private static final String BLACK = "\u001B[30m";
  private static final String RED = "\u001B[31m";
  private static final String YELLOW = "\u001B[33m";
  private static final String BLUE = "\u001B[34m";
  private static final String PURPLE = "\u001B[35m";
  private static final String CYAN = "\u001B[36m";
  private static final String[] COLORS = {BLACK, RED, YELLOW, BLUE, PURPLE, CYAN};
  private static final String[] COLORNAMES = {"black", "red", "yellow", "blue", "purple", "cyan"};
  private static final String[] OSNAMES = {"MacOS", "Windows", "Linux"};
  private static final String RESET = "\u001B[0m"; // reset to default text color

  private static String userName, customOS = "";
  private static int OS, favColor;
  private static boolean usesLinux;

  /**
   * Initializes resources.
   */
  public static void initialize() {
    // initialize
    keyboard = new Scanner(System.in);
    try {
      text = new Scanner(new File("stuff.txt"));
    } catch (FileNotFoundException f) {
      System.out.println("File not found");
    }
    // System.out.println("Text file says: " + text.nextLine());
  }

  /**
   * Closes resources.
   */
  public static void terminate() {
    // close streams
    keyboard.close();
    text.close();
  }

  /**
   * Prompt user and records their responses.
   */
  public static void prompt() {
    // prompt user
    System.out.println("Tell me about yourself...");

    System.out.println("What's your name?");
    System.out.print("> ");
    userName = keyboard.nextLine();

    System.out.println("What operating system do you use?\n" + "[1] MacOS\n" + "[2] Windows\n"
        + "[3] Linux\n" + "[4] Other");
    System.out.print("> ");
    OS = keyboard.nextInt() - 1;
    keyboard.nextLine();
    if (OS == 3) {
      System.out.println("What OS do you use?");
      System.out.print("> ");
      customOS = keyboard.nextLine();
    }
    System.out.println("Are you using a linux console?\n" + "[1] Yes\n" + "[2] No");
    System.out.print("> ");
    usesLinux = keyboard.nextInt() == 1;

    System.out.println("What's your favorite color?\n" + "[1] Black\n" + "[2] Red\n"
        + "[3] Yellow\n" + "[4] Blue\n" + "[5] Purple\n" + "[6] Cyan");
    System.out.print("> ");
    favColor = keyboard.nextInt() - 1;
    keyboard.nextLine();
  }

  /**
   * Responds to user by outputting information about them.
   */
  public static void respond() {
    // respond to user based on input
    System.out.println("\nHere are some facts about you...");
    if (OS != 3) {
      System.out.println("You are using " + OSNAMES[OS] + ".");
    } else {
      System.out.println("You are using " + customOS + ".");
    }
    if (usesLinux) { // ANSI escape sequences only reliably works on linux consoles
      System.out.print("Your favorite color is ");
      System.out.println(COLORS[favColor] + COLORNAMES[favColor] + RESET + ".");
    } else {
      System.out.println(COLORNAMES[favColor] + ".");
    }
    System.out.println("That's it, bye " + userName + "!");
  }

  public static void main(String[] args) {
    System.out.println("┐");
    System.out.println((int) '┐');
    // for (int i = 0; i < 300; i++) {
    // System.out.println(i + ": " + (char) i);
    // }
    // initialize();
    // prompt();
    // respond();
    // terminate();
  }
}
