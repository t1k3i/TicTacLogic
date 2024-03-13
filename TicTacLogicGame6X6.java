import java.util.Set;
import java.util.HashSet;

public class TicTacLogicGame6X6 {

    private char[][] grid;

    TicTacLogicGame6X6(char[][] grid) {
        this.grid = grid;
    }

    TicTacLogicGame6X6() {
        this.generateGrid();
    }

    public void solve() {
        // TODO
    }

    public void print() {
        System.out.println("-------------");
        for (int i = 0; i < this.grid.length; i++) {
            System.out.print("|");
            for (int j = 0; j < this.grid[i].length; j++) {
                if (this.grid[i][j] == 0) System.out.print(" |");
                else System.out.print(this.grid[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("-------------");
    }

    private void generateGrid() {
        // TODO
    }

    // DONE??
    public boolean check() {
        Set<String> rowSet = new HashSet<>();
        for (int i = 0; i < this.grid.length; i++) {
            StringBuilder row = new StringBuilder("");
            int xR = 0; int oR = 0; int xR2 = 0; int oR2 = 0;
            for (int j = 0; j < this.grid[i].length; j++) {
                if (this.grid[i][j] == 'X') {xR++; xR2++; oR2 = 0; row.append('X');}
                else if (this.grid[i][j] == 'O') {oR++; oR2++; xR2 = 0; row.append('O');}
            }
            if (xR > 3 || oR > 3 || xR2 > 2 || oR2 > 2) return false;
            if (xR == 3 && oR == 3) {
                if (rowSet.contains(row.toString())) return false;
                else rowSet.add(row.toString());
            }
        }
        Set<String> collumSet = new HashSet<>();
        for (int j = 0; j < this.grid[0].length; j++) {
            StringBuilder collum = new StringBuilder("");
            int xC = 0; int oC = 0; int xC2 = 0; int oC2 = 0;
            for (int i = 0; i < this.grid.length; i++) {
                if (this.grid[i][j] == 'X') {xC++; xC2++; oC2 = 0; collum.append('X');}
                else if (this.grid[i][j] == 'O') {oC++; oC2++; xC2 = 0; collum.append('O');}
            }
            if (xC > 3 || oC > 3 || xC2 > 2 || oC2 > 2) return false;
            if (xC == 3 && oC == 3) {
                if (collumSet.contains(collum.toString())) return false;
                else collumSet.add(collum.toString());
            }
        }
        return true;
    }

}