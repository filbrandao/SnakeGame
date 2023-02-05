package academy.mindswap.field;

import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;

public final class Field {

    private static final String BORDER_STRING = "▒";
    private static final String SNAKE_BODY_STRING = "#";
    private static final String SNAKE_HEAD_STRING = "0";
    private static final String FRUIT_STRING = "@";
    private static final String SNAKE_TAIL_STRING = ">";
    private static final String SNAKE_SCORE = "SCORE: ";
    private static final String GAME_OVER_STRING = "▒";

    private static int width;
    private static int height;
    private static Screen screen;
    private static ScreenWriter screenWriter;
    private static int fruitCatched = 0;

    public static void init(int width, int height) {

        screen = TerminalFacade.createScreen();

        Field.width = width;
        Field.height = height;
        screen.getTerminal().getTerminalSize().setColumns(width);
        screen.getTerminal().getTerminalSize().setRows(height);

        screenWriter = new ScreenWriter(screen);
        screen.setCursorPosition(null);
        screen.startScreen();

        drawWalls();
        screen.refresh();
    }

    public static void drawSnake(Snake snake) {

        Terminal.Color snakeColor = Terminal.Color.GREEN;

        if (!snake.isAlive()){
            snakeColor = Terminal.Color.RED;
        }

        Position head = snake.getHead();
        Position tail = snake.getTail();
        for (Position p : snake.getFullSnake()) {
            if (p.equals(tail)) {
                screen.putString(p.getCol(), p.getRow(), SNAKE_TAIL_STRING, snakeColor, null);
            } else if (!p.equals(head)){
                screen.putString(p.getCol(), p.getRow(), SNAKE_BODY_STRING, snakeColor, null);
            } else {
                screen.putString(p.getCol(), p.getRow(), SNAKE_HEAD_STRING, snakeColor, null);
            }
        }
        screen.refresh();
    }

    public static void clearTail(Snake snake) {
        Position tail = snake.getTail();
        screen.putString(tail.getCol(), tail.getRow(), " ", null, null);
    }

    private static void drawWalls() {

        for(int i = 0; i < width; i++){
            screenWriter.drawString(i, height - 1, BORDER_STRING);
        }

        for (int i = 0; i < 35; i++) {
            screenWriter.drawString(i, 0, BORDER_STRING);
        }

        //WRITES SCORE
        int index = 0;
        for (int i = 36; i < 36+SNAKE_SCORE.length(); i++) {
            screenWriter.drawString(i, 0, String.valueOf(SNAKE_SCORE.charAt(index)));
            index++;
        }

        screenWriter.drawString(42, 0, String.valueOf(Field.fruitCatched));

        for (int i = 44; i < width; i++) {
            screenWriter.drawString(i, 0, BORDER_STRING);
        }

        for (int j = 0; j < height; j++) {
            screenWriter.drawString(0, j, BORDER_STRING);
            screenWriter.drawString(width - 1, j, BORDER_STRING);
        }

        /*for (int i = 0; i < width; i++) {
            screenWriter.drawString(i, 0, BORDER_STRING);
            screenWriter.drawString(i, height - 1, BORDER_STRING);
        }

        for (int j = 0; j < height; j++) {
            screenWriter.drawString(0, j, BORDER_STRING);
            screenWriter.drawString(width - 1, j, BORDER_STRING);
        }*/
    }

    public static Key readInput() {
        return screen.readInput();
    }

    public static void drawFruit(Fruit fruit) {
        screen.putString(fruit.getPosition().getCol(), fruit.getPosition().getRow(), FRUIT_STRING, Terminal.Color.YELLOW, null);
        System.out.println(fruit.getPosition().getCol() + " " + fruit.getPosition().getRow());
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static void gameOver() {


        /// G ///
        for(int i = 23; i < 29; i++){
            screen.putString(i, 8, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 22; i < 30; i++){
            screen.putString(i, 9, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 22; i < 24; i++){
            screen.putString(i, 10, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 28; i < 30; i++){
            screen.putString(i, 10, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 22; i < 24; i++){
            screen.putString(i, 11, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 22; i < 24; i++){
            screen.putString(i, 12, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 26; i < 30; i++){
            screen.putString(i, 12, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 22; i < 25; i++){
            screen.putString(i, 13, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 27; i < 30; i++){
            screen.putString(i, 13, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 23; i < 29; i++){
            screen.putString(i, 14, GAME_OVER_STRING, Terminal.Color.RED, null);
        }


        /// A ///
        for(int i = 33; i < 37; i++){
            screen.putString(i, 8, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 31; i < 38; i++){
            screen.putString(i, 9, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 31; i < 33; i++){
            screen.putString(i, 9, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 35; i < 39; i++){
            screen.putString(i, 9, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 31; i < 33; i++){
            screen.putString(i, 10, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 37; i < 39; i++){
            screen.putString(i, 10, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 31; i < 39; i++){
            screen.putString(i, 11, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 31; i < 33; i++){
            screen.putString(i, 12, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 37; i < 39; i++){
            screen.putString(i, 12, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 31; i < 33; i++){
            screen.putString(i, 13, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 37; i < 39; i++){
            screen.putString(i, 13, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 31; i < 33; i++){
            screen.putString(i, 14, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 37; i < 39; i++){
            screen.putString(i, 14, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        /// M ///
        for(int i = 40; i < 42; i++){
            screen.putString(i, 8, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 47; i < 49; i++){
            screen.putString(i, 8, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 40; i < 43; i++){
            screen.putString(i, 9, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 46; i < 49; i++){
            screen.putString(i, 9, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 40; i < 42; i++){
            screen.putString(i, 10, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        screen.putString(43, 10, GAME_OVER_STRING, Terminal.Color.RED, null);

        screen.putString(45, 10, GAME_OVER_STRING, Terminal.Color.RED, null);

        for(int i = 47; i < 49; i++){
            screen.putString(i, 10, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 40; i < 42; i++){
            screen.putString(i, 11, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        screen.putString(44, 11, GAME_OVER_STRING, Terminal.Color.RED, null);
        for(int i = 47; i < 49; i++){
            screen.putString(i, 11, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 40; i < 42; i++){
            screen.putString(i, 12, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 47; i < 49; i++){
            screen.putString(i, 12, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 40; i < 42; i++){
            screen.putString(i, 13, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 47; i < 49; i++){
            screen.putString(i, 13, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 40; i < 42; i++){
            screen.putString(i, 14, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 47; i < 49; i++){
            screen.putString(i, 14, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        /// E ///
        for(int i = 50; i < 57; i++){
            screen.putString(i, 8, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 50; i < 57; i++){
            screen.putString(i, 9, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 50; i < 52; i++){
            screen.putString(i, 10, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 50; i < 57; i++){
            screen.putString(i, 11, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 50; i < 52; i++){
            screen.putString(i, 12, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 50; i < 57; i++){
            screen.putString(i, 13, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 50; i < 57; i++){
            screen.putString(i, 14, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        /// O ///
        for(int i = 24; i < 30; i++){
            screen.putString(i, 16, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 23; i < 26; i++){
            screen.putString(i, 17, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 28; i < 31; i++){
            screen.putString(i, 17, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 23; i < 25; i++){
            screen.putString(i, 18, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 29; i < 31; i++){
            screen.putString(i, 18, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 23; i < 25; i++){
            screen.putString(i, 19, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 29; i < 31; i++){
            screen.putString(i, 19, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 23; i < 25; i++){
            screen.putString(i, 20, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 29; i < 31; i++){
            screen.putString(i, 20, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 23; i < 26; i++){
            screen.putString(i, 21, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 28; i < 31; i++){
            screen.putString(i, 21, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 24; i < 30; i++){
            screen.putString(i, 22, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        /// V ///
        for(int i = 31; i < 33; i++){
            screen.putString(i, 16, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 38; i < 40; i++){
            screen.putString(i, 16, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 32; i < 34; i++){
            screen.putString(i, 17, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 37; i < 39; i++){
            screen.putString(i, 17, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 32; i < 34; i++){
            screen.putString(i, 18, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 37; i < 39; i++){
            screen.putString(i, 18, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 33; i < 35; i++){
            screen.putString(i, 19, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 36; i < 38; i++){
            screen.putString(i, 19, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 33; i < 35; i++){
            screen.putString(i, 20, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 36; i < 38; i++){
            screen.putString(i, 20, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 34; i < 37; i++){
            screen.putString(i, 21, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        screen.putString(35, 22, GAME_OVER_STRING, Terminal.Color.RED, null);


        /// E ///
        for(int i = 41; i < 48; i++){
            screen.putString(i, 16, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 41; i < 48; i++){
            screen.putString(i, 17, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 41; i < 43; i++){
            screen.putString(i, 18, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 41; i < 48; i++){
            screen.putString(i, 19, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 41; i < 43; i++){
            screen.putString(i, 20, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 41; i < 48; i++){
            screen.putString(i, 21, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 41; i < 48; i++){
            screen.putString(i, 22, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        /// R ///
        for(int i = 49; i < 54; i++){
            screen.putString(i, 16, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 49; i < 51; i++){
            screen.putString(i, 17, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 53; i < 55; i++){
            screen.putString(i, 17, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 49; i < 51; i++){
            screen.putString(i, 18, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 54; i < 56; i++){
            screen.putString(i, 18, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 49; i < 54; i++){
            screen.putString(i, 19, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        /*for(int i = 49; i < 51; i++){
            screen.putString(i, 19, GAME_OVER_STRING, Terminal.Color.RED, null);
        }*/

        for(int i = 49; i < 51; i++){
            screen.putString(i, 20, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 52; i < 54; i++){
            screen.putString(i, 20, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 49; i < 51; i++){
            screen.putString(i, 21, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 53; i < 55; i++){
            screen.putString(i, 21, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        for(int i = 49; i < 51; i++){
            screen.putString(i, 22, GAME_OVER_STRING, Terminal.Color.RED, null);
        }
        for(int i = 54; i < 56; i++){
            screen.putString(i, 22, GAME_OVER_STRING, Terminal.Color.RED, null);
        }

        /*for (int i = 0; i < width; i++) {
            screen.putString(i, 13, FRUIT_STRING, Terminal.Color.RED, null);

        }

        for (int j = 0; j < height; j++) {
            screen.putString(50, j, FRUIT_STRING, Terminal.Color.RED, null);
        }*/
        screen.refresh();
    }

    public static void gameOverTest() {

        String gameOver =
                "██████╗  █████╗ ███╗   ███╗███████╗\n" +
                "██╔════╝ ██╔══██╗████╗ ████║██╔════╝\n" +
                "██║  ███╗███████║██╔████╔██║█████╗\n" +
                "██║   ██║██╔══██║██║╚██╔╝██║██╔══╝\n" +
                "╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗\n" +
                " ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝\n" +
                " ██████╗ ██╗   ██╗███████╗██████╗\n" +
                "██╔═══██╗██║   ██║██╔════╝██╔══██╗\n" +
                "██║   ██║██║   ██║█████╗  ██████╔╝\n" +
                "██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗\n" +
                "╚██████╔╝ ╚████╔╝ ███████╗██║  ██║\n" +
                " ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝";

        int index = 0;
        int gameOverY = 10;
        int numberOfLines = 12;
        for (int i = 22; i < 22 + gameOver.length(); i++) {

            for (int j = 10; j < numberOfLines; j++) {
                if (!String.valueOf(gameOver.charAt(index)).equals("\n")) {
                    screen.putString(j, gameOverY, String.valueOf(gameOver.charAt(index)), Terminal.Color.RED, null);
                }

                index++;
                screen.refresh();
            }
        }
    }

    public static void setFruitCatched(int fruitCatched) {

        Field.fruitCatched += fruitCatched;
        screenWriter.drawString(42, 0, String.valueOf(Field.fruitCatched));
    }
}
