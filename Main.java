public class Main {

    public static void main(String[] args) {

        // Testing
        /*char[][] grid = new char[6][6];
        grid[0][0] = 'X';
        grid[1][0] = 'X';
        grid[2][3] = 'X';
        grid[2][5] = 'O';
        grid[3][0] = 'O';
        grid[3][2] = 'O';
        grid[4][5] = 'X';
        /*grid[3][4] = 'X';
        grid[4][2] = 'O';
        grid[4][3] = 'X';
        grid[4][5] = 'O';
        grid[5][0] = 'O';
        grid[5][4] = 'O';
        /*grid[3][2] = 'O';
        grid[3][0] = 'O';
        grid[3][5] = 'O';
        grid[4][0] = 'X';
        grid[4][1] = 'X';
        grid[4][5] = 'X';
        grid[5][0] = 'X';
        grid[5][4] = 'X';
        grid[5][5] = 'X';*/

        char[][] grid = {
            {0, 'X', 0, 0, 0, 0},
            {'O', 0, 0, 0, 0, 0},
            {0, 0, 'O', 0,'X', 0},
            {0, 'X', 0, 0, 'X', 'O'},
            {0, 0, 0, 0, 0, 'O'},
            {0, 0, 0, 'O', 0, 0}
        };

        TicTacLogicGame6X6 game2 = new TicTacLogicGame6X6(12);
        game2.print();
        System.out.println(game2.check());

        /*TicTacLogicGame6X6 game = new TicTacLogicGame6X6(grid, 6);
        game.print();
        System.out.println(game.check());
        System.out.println("---------------------------------------------------------------------");
        TicTacLogicGame6X6 game2 = new TicTacLogicGame6X6(8);
        game2.print();
        System.out.println(game2.check());
        System.out.println("---------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------");
        TicTacLogicGame6X6 game3 = new TicTacLogicGame6X6(10);
        game3.print();
        System.out.println(game3.check());
        System.out.println("---------------------------------------------------------------------");*/
        /*System.out.println("---------------------------------------------------------------------");
        game.solve();
        System.out.println("---------------------------------------------------------------------");
        game.print();
        System.out.println(game.check());

        StringBuilder str = new StringBuilder("XX....");
        System.out.println(str + ": " + TicTacLogicGame6X6.checkLine(str));*/
        

        /*for (int i = 0; i < 10; i++) {
            TicTacLogicGame6X6 game = new TicTacLogicGame6X6();
            game.print();
            System.out.println(game.check());
        }*/
    }

}
