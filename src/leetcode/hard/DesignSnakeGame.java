package leetcode.hard;

import java.util.*;

public class DesignSnakeGame {

    public static void main(String[] args) {
        int[][] food = { { 1, 2 }, { 0, 1 } };
        DesignSnakeGame snake = new DesignSnakeGame(3, 4, food);
        System.out.println(snake.move("R")); // 0
        System.out.println(snake.move("D")); // 0
        System.out.println(snake.move("R")); // 1 (first food)
        System.out.println(snake.move("U")); // 1
        System.out.println(snake.move("L")); // 2 (second food)
        System.out.println(snake.move("U")); // -1 (Over, collide with border)
    }

    Set<Integer> set;
    Deque<Integer> body;
    int score;
    int[][] food;
    int foodIndex;
    int width;
    int height;

    public DesignSnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        set = new HashSet<>();
        set.add(0);
        body = new LinkedList<>();
        body.offerLast(0);
    }

    public int move(String direction) {
        // game already over
        if (score == -1) {
            return -1;
        }
        int rowHead = body.peekFirst() / width;
        int colHead = body.peekFirst() % width;
        switch (direction) {
            case "U":
                rowHead--;
                break;
            case "D":
                rowHead++;
                break;
            case "L":
                colHead--;
                break;
            default:
                colHead++;
        }
        int head = rowHead * width + colHead;

        // out of boundary or eating body
        set.remove(body.peekLast());
        if (rowHead < 0 || rowHead == height || colHead < 0 || colHead == width || set.contains(head)) {
            return score = -1;
        }
        // add head
        set.add(head);
        body.offerFirst(head);

        // eating food, keep tail, add head
        if (foodIndex < food.length && rowHead == food[foodIndex][0] && colHead == food[foodIndex][1]) {
            set.add(body.peekLast()); // old tail doesn't change
            foodIndex++;
            return ++score;
        }
        // Normal Case: remove tail, add head
        body.pollLast();
        return score;
    }
}
