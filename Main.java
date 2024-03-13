
public class Main {

    public static void main(String[] args) {

        // Testing
        char[][] grid = new char[6][6];
        grid[0][2] = 'X';
        grid[0][0] = 'O';
        grid[0][5] = 'O';
        grid[1][2] = 'X';
        grid[1][0] = 'O';
        grid[1][5] = 'O';
        grid[2][0] = 'X';
        grid[2][5] = 'X';
        grid[3][2] = 'O';
        grid[3][0] = 'O';
        grid[3][5] = 'O';
        grid[4][0] = 'X';
        grid[4][1] = 'X';
        grid[4][5] = 'X';
        grid[5][0] = 'X';
        grid[5][4] = 'X';
        grid[5][5] = 'X';
        TicTacLogicGame6X6 game = new TicTacLogicGame6X6(grid);
        game.print();
        System.out.println(game.check());

    }

}
