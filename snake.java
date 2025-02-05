
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class snake{
    private static final Map<Integer, Integer> snakes = new HashMap<>();
    private static final Map<Integer, Integer> ladders = new HashMap<>();
    private static final Random random = new Random();

    static {
        snakes.put(16, 6);
        snakes.put(47, 26);
        snakes.put(49, 11);
        snakes.put(56, 53);
        snakes.put(62, 19);
        snakes.put(64, 60);
        snakes.put(87, 24);
        snakes.put(93, 73);
        snakes.put(95, 75);
        snakes.put(98, 78);

        ladders.put(1, 38);
        ladders.put(4, 14);
        ladders.put(9, 31);
        ladders.put(21, 42);
        ladders.put(28, 84);
        ladders.put(36, 44);
        ladders.put(51, 67);
        ladders.put(71, 91);
        ladders.put(80, 100);
    }

    private static int rollDice() {
        return random.nextInt(6) + 1;
    }

    private static int movePlayer(int position, int diceValue) {
        int newPosition = position + diceValue;
        if (snakes.containsKey(newPosition)) {
            System.out.println("Oops! Snake at " + newPosition + " bites you! Moving down to " + snakes.get(newPosition));
            return snakes.get(newPosition);
        } else if (ladders.containsKey(newPosition)) {
            System.out.println("Yay! Ladder at " + newPosition + " takes you up to " + ladders.get(newPosition));
            return ladders.get(newPosition);
        } else if (newPosition > 100) {
            System.out.println("Roll exceeded 100. Try again next turn!");
            return position;
        }
        return newPosition;
    }

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> playerPositions = new HashMap<>();
        playerPositions.put("Player 1", 0);
        playerPositions.put("Player 2", 0);
        String turn = "Player 1";

        while (true) {
            System.out.println(turn + ", press Enter to roll the dice...");
            scanner.nextLine();
            int diceValue = rollDice();
            System.out.println(turn + " rolled a " + diceValue);

            playerPositions.put(turn, movePlayer(playerPositions.get(turn), diceValue));
            System.out.println(turn + " is now at position " + playerPositions.get(turn));

            if (playerPositions.get(turn) == 100) {
                System.out.println(turn + " wins the game! 🎉");
                break;
            }
            turn = turn.equals("Player 1") ? "Player 2" : "Player 1";
        }
        scanner.close();
    }

    public static void main(String[] args) {
        playGame();
    }
}

    

