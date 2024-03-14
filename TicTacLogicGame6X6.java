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

    public boolean solve() {
        while (!this.full()) {
            int[] pair = this.findPair();
            if (pair != null) {
                this.grid[pair[0]][pair[1]] = pair[2] == 1 ? 'X' : 'O';
                continue;
            }
            int[] fullLine = this.findFullLine();
            if (fullLine != null) {
                this.grid[fullLine[0]][fullLine[1]] = fullLine[2] == 1 ? 'X' : 'O';
                continue;
            }
            int[] differentLine = this.findDifferentLine();
            if (differentLine == null) return false;
            this.grid[differentLine[0]][differentLine[1]] = differentLine[2] == 1 ? 'X' : 'O';
            this.grid[differentLine[3]][differentLine[4]] = differentLine[5] == 1 ? 'X' : 'O';
        }
        return true;
    }
    
    private int[] findDifferentLine() {
        int[] pair = new int[6];
        // Check rows
        Set<String> rowSet = new HashSet<>();
        for (int i = 0; i < this.grid.length; i++) {
            int xR = 0, oR = 0;
            StringBuilder row = new StringBuilder("");
            for (int j = 0; j < this.grid[i].length; j++) {
                if (this.grid[i][j] == 'X') {
                    row.append('X');
                    xR++;
                } else if (this.grid[i][j] == 'O') {
                    row.append('O');
                    oR++;
                } else {
                    row.append('.');
                }
            }
            boolean contains = false;
            for (int k = 0; k < row.length(); k++) {
                if (row.charAt(k) == '.') {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                rowSet.add(row.toString());
            }
            if (xR + oR == 4 && rowSet.size() >= 1) {
                int j1 = -1, j2 = -1;
                for (int j = 0; j < this.grid[i].length; j++) {
                    if (this.grid[i][j] == 0 && j1 == -1) {
                        j1 = j;
                    } else if (this.grid[i][j] == 0 && j2 == -1) {
                        j2 = j;
                    }
                }
                row.replace(j1, j1, "X");
                row.replace(j2, j2, "O");
                if (rowSet.contains(row.toString())) {
                    row.replace(j1, j1, "O");
                    row.replace(j2, j2, "X");
                    if (rowSet.contains(row.toString())) return null;
                }
                pair[0] = i;
                pair[1] = j1;
                pair[2] = row.charAt(j1) == 'X' ? 1 : 0;
                pair[3] = i;
                pair[4] = j2;
                pair[5] = row.charAt(j2) == 'X' ? 1 : 0;
                return pair;
            }
        }
        // Check collums
        Set<String> collumSet = new HashSet<>();
        for (int j = 0; j < this.grid[0].length; j++) {
            int xC = 0, oC = 0;
            StringBuilder collum = new StringBuilder("");
            for (int i = 0; i < this.grid.length; i++) {
                if (this.grid[i][j] == 'X') {
                    collum.append('X');
                    xC++;
                } else if (this.grid[i][j] == 'O') {
                    collum.append('O');
                    oC++;
                } else {
                    collum.append('.');
                }
            }
            boolean contains = false;
            for (int k = 0; k < collum.length(); k++) {
                if (collum.charAt(k) == '.') {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                collumSet.add(collum.toString());
            }
            if (xC + oC == 4 && collumSet.size() >= 1) {
                int i1 = -1, i2 = -1;
                for (int i = 0; i < this.grid.length; i++) {
                    if (this.grid[i][j] == 0 && i1 == -1) {
                        i1 = i;
                    } else if (this.grid[i][j] == 0 && i2 == -1) {
                        i2 = i;
                    }
                }
                collum.replace(i1, i1, "X");
                collum.replace(i2, i2, "O");
                if (collumSet.contains(collum.toString())) {
                    collum.replace(i1, i1, "O");
                    collum.replace(i2, i2, "X");
                    if (collumSet.contains(collum.toString())) return null;
                }
                pair[0] = i1;
                pair[1] = j;
                pair[2] = collum.charAt(i1) == 'X' ? 1 : 0;
                pair[3] = i2;
                pair[4] = j;
                pair[5] = collum.charAt(i2) == 'X' ? 1 : 0;
                return pair;
            }
        }
        return null;
    }

    private int[] findFullLine() {
        int[] pair = new int[3];
        // Check rows
        for (int i = 0; i < this.grid.length; i++) {
            int xR = 0; int oR = 0;
            for (int j = 0; j < this.grid[i].length; j++) {
                if (this.grid[i][j] == 'X') {
                    xR++;
                } else if (this.grid[i][j] == 'O') {
                   oR++;
                }
            }
            if (xR == 3) {
                for (int j = 0; j < this.grid[i].length; j++) {
                    if (this.grid[i][j] == 0) {
                        pair[0] = i;
                        pair[1] = j;
                        pair[2] = 0;
                        return pair;
                    }
                }
            } else if (oR == 3) {
                for (int j = 0; j < this.grid[i].length; j++) {
                    if (this.grid[i][j] == 0) {
                        pair[0] = i;
                        pair[1] = j;
                        pair[2] = 1;
                        return pair;
                    }
                }
            }
        }
        // Check collums
        for (int j = 0; j < this.grid[0].length; j++) {
            int xC = 0; int oC = 0;
            for (int i = 0; i < this.grid.length; i++) {
                if (this.grid[i][j] == 'X') {
                    xC++;
                } else if (this.grid[i][j] == 'O') {
                   oC++;
                }
            }
            if (xC == 3) {
                for (int i = 0; i < this.grid.length; i++) {
                    if (this.grid[i][j] == 0) {
                        pair[0] = i;
                        pair[1] = j;
                        pair[2] = 0;
                        return pair;
                    }
                }
            } else if (oC == 3) {
                for (int i = 0; i < this.grid.length; i++) {
                    if (this.grid[i][j] == 0) {
                        pair[0] = i;
                        pair[1] = j;
                        pair[2] = 1;
                        return pair;
                    }
                }
            }
        }
        return null;
    }

    private int[] findPair() {
        int[] pair = new int[3];
        // Check rows
        for (int i = 0; i < this.grid.length; i++) {
            int xR2 = 0; int oR2 = 0;
            for (int j = 0; j < this.grid[i].length; j++) {
                if (this.grid[i][j] == 'X') {
                    xR2++; oR2 = 0;
                    if (xR2 == 2) {
                        if (j + 1 < this.grid[i].length && this.grid[i][j + 1] == 0) {
                            pair[0] = i;
                            pair[1] = j + 1;
                            pair[2] = 0;
                            return pair;
                        } else if (j - 2 >= 0 && this.grid[i][j - 2] == 0) {
                            pair[0] = i;
                            pair[1] = j - 2;
                            pair[2] = 0;
                            return pair;
                        }
                    }
                }
                else if (this.grid[i][j] == 'O') {
                    oR2++; xR2 = 0;
                    if (oR2 == 2) {
                        if (j + 1 < this.grid[i].length && this.grid[i][j + 1] == 0) {
                            pair[0] = i;
                            pair[1] = j + 1;
                            pair[2] = 1;
                            return pair;
                        } else if (j - 2 >= 0 && this.grid[i][j - 2] == 0) {
                            pair[0] = i;
                            pair[1] = j - 2;
                            pair[2] = 1;
                            return pair;
                        }
                    }
                } else if (this.grid[i][j] == 0 && j > 0 && j < this.grid[i].length - 1) {
                    xR2 = 0; oR2 = 0;
                    if (this.grid[i][j - 1] == 'X' && this.grid[i][j + 1] == 'X') {
                        pair[0] = i;
                        pair[1] = j;
                        pair[2] = 0;
                        return pair;
                    } else if (this.grid[i][j - 1] == 'O' && this.grid[i][j + 1] == 'O') {
                        pair[0] = i;
                        pair[1] = j;
                        pair[2] = 1;
                        return pair;
                    }
                } else {xR2 = 0; oR2 = 0;}
            }
        }
        // Check collums
        for (int j = 0; j < this.grid[0].length; j++) {
            int xC2 = 0; int oC2 = 0;
            for (int i = 0; i < this.grid.length; i++) {
                if (this.grid[i][j] == 'X') {
                    xC2++; oC2 = 0;
                    if (xC2 == 2) {
                        if (i + 1 < this.grid.length && this.grid[i + 1][j] == 0) {
                            pair[0] = i + 1;
                            pair[1] = j;
                            pair[2] = 0;
                            return pair;
                        } else if (i - 2 >= 0 && this.grid[i - 2][j] == 0) {
                            pair[0] = i - 2;
                            pair[1] = j;
                            pair[2] = 0;
                            return pair;
                        }
                    }
                }
                else if (this.grid[i][j] == 'O') {
                    oC2++; xC2 = 0;
                    if (oC2 == 2) {
                        if (i + 1 < this.grid.length && this.grid[i + 1][j] == 0) {
                            pair[0] = i + 1;
                            pair[1] = j;
                            pair[2] = 1;
                            return pair;
                        } else if (i - 2 >= 0 && this.grid[i - 2][j] == 0) {
                            pair[0] = i - 2;
                            pair[1] = j;
                            pair[2] = 1;
                            return pair;
                        }
                    }
                } else if (this.grid[i][j] == 0 && i > 0 && i < this.grid.length - 1) {
                    xC2 = 0; oC2 = 0;
                    if (this.grid[i - 1][j] == 'X' && this.grid[i + 1][j] == 'X') {
                        pair[0] = i;
                        pair[1] = j;
                        pair[2] = 0;
                        return pair;
                    } else if (this.grid[i - 1][j] == 'O' && this.grid[i + 1][j] == 'O') {
                        pair[0] = i;
                        pair[1] = j;
                        pair[2] = 1;
                        return pair;
                    }
                } else {xC2 = 0; oC2 = 0;}
            }
        }
        return null;
    }

    private boolean full() {
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                if (this.grid[i][j] == 0) return false;
            }
        }
        return true;
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
        this.grid = new char[6][6];
        this.generateFullGrid(6, 0, 0, 0);
        this.makePuzzle();
    }

    private boolean generateFullGrid(int ranNumberOfSquares, int ix, int i, int j) {
        if (i >= this.grid.length) return true;
        if (j >= this.grid[i].length) return this.generateFullGrid(ranNumberOfSquares, ix, i + 1, 0); 
        if (ranNumberOfSquares > ix) {
            int rI = (int) (Math.random() * this.grid.length), rJ = (int) (Math.random() * this.grid[0].length);
            while (this.grid[rI][rJ] == 'X' || this.grid[rI][rJ] == 'O') {
                System.out.println("Sem tle");
                rI = (int) (Math.random() * this.grid.length); rJ = (int) (Math.random() * this.grid[0].length);
            }
            char random = Math.random() < 0.5 ? 'X' : 'O';
            this.grid[rI][rJ] = random;
            boolean valid = this.check();
            if (!valid || !this.generateFullGrid(ranNumberOfSquares, ix + 1, 0, 0)) {
                random = random == 'X' ? 'O' : 'X';
                this.grid[rI][rJ] = random;
                valid = this.check();
                if (!valid || !this.generateFullGrid(ranNumberOfSquares, ix + 1, 0, 0)) {
                    this.grid[rI][rJ] = 0;
                    return false;
                }
            }
        } else {
            if (this.grid[i][j] != 0) return this.generateFullGrid(ranNumberOfSquares, ix + 1, i, j + 1); 
            char random = Math.random() < 0.5 ? 'X' : 'O';
            this.grid[i][j] = random;
            boolean valid = this.check();
            if (!valid || !this.generateFullGrid(ranNumberOfSquares, ix + 1, i, j + 1)) {
                random = random == 'X' ? 'O' : 'X';
                this.grid[i][j] = random;
                valid = this.check();
                if (!valid || !this.generateFullGrid(ranNumberOfSquares, ix + 1, i, j + 1)) {
                    this.grid[i][j] = 0;
                    return false;
                }
            }
        }
        return true;
    }

    private void makePuzzle() {
        // TODO
    }

    public boolean check() {
        // Check rows
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
        // Check collums
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