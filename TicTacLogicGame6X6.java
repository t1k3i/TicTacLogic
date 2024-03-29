import java.util.Set;
import java.util.HashSet;

public class TicTacLogicGame6X6 {

    private char[][] grid;
    private int size;

    TicTacLogicGame6X6(char[][] grid) {
        this.grid = grid;
        this.size = grid.length;
    }

    TicTacLogicGame6X6(int size) {
        this.size = size;
        this.generateGrid();
    }

    /*private boolean checkIfAbleToSolve() {
        char[][] tempGrid = new char[this.grid.length][this.grid[0].length];
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                tempGrid[i][j] = this.grid[i][j];
            }
        }
        TicTacLogicGame6X6	temp = new TicTacLogicGame6X6(tempGrid);
        if (temp.solve()) return true;
        return false;
    }*/

    public boolean solve() {
        while (!this.full()) {
            this.print();
            System.out.println(this.check());
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
            if (differentLine != null) {
                this.grid[differentLine[0]][differentLine[1]] = differentLine[2] == 1 ? 'X' : 'O';
                this.grid[differentLine[3]][differentLine[4]] = differentLine[5] == 1 ? 'X' : 'O';
                continue;
            }
            int[] avoidTriples = this.avoidTriples();
            if (avoidTriples != null) {
                this.grid[avoidTriples[0]][avoidTriples[1]] = avoidTriples[2] == 1 ? 'X' : 'O';
                continue;
            }
            int[] advanced2 = this.advanced2();
            if (advanced2 != null) {
                this.grid[advanced2[0]][advanced2[1]] = advanced2[2] == 1 ? 'X' : 'O';
                continue;
            }
            // TODO one more advanced rule
            return false;
        }
        return true;
    }

    private int[] advanced2() {
        int[] pair = new int[3];
        // Check rows ------------------------------------------------------------------------------------------------------------
        Set<String> rowSet = new HashSet<>();
        outher: for (int i = 0; i < this.grid.length; i++) {
            StringBuilder row = new StringBuilder("");
            for (int j = 0; j < this.grid[i].length; j++) {
                if (this.grid[i][j] == 'X') {
                    row.append('X');
                } else if (this.grid[i][j] == 'O') {
                    row.append('O');
                } else {
                    continue outher;
                }
            }
            rowSet.add(row.toString());
        }
        System.out.println(rowSet);
        for (int i = 0; i < this.grid.length; i++) {
            int x = 0, o = 0;
            StringBuilder row = new StringBuilder("");
            for (int j = 0; j < this.grid[i].length; j++) {
                if (this.grid[i][j] == 'X') {
                    row.append('X');
                    x++;
                } else if (this.grid[i][j] == 'O') {
                    row.append('O');
                    o++;
                } else {
                    row.append('.');
                }
            }
            System.out.println(row + " ----- " + x + " " + o);
            if (o == (this.grid.length / 2 - 1)) {
                for (int j = 0; j < row.length(); j++) {
                    if (row.charAt(j) == '.') {
                        row.replace(j, j + 1, "O");
                        StringBuilder row2 = new StringBuilder(row.toString());
                        for (int k = 0; k < row.length(); k++) {
                            if (row2.charAt(k) == '.') row2.replace(k, k + 1, "X");
                        }
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + row2);
                        if (rowSet.contains(row2.toString())) {
                            pair[0] = i;
                            pair[1] = j;
                            pair[2] = 1;
                            return pair;
                        }
                        row.replace(j, j + 1, ".");
                    }
                }
            } else if (x == (this.grid.length / 2 - 1)) {
                for (int j = 0; j < row.length(); j++) {
                    if (row.charAt(j) == '.') {
                        row.replace(j, j + 1, "X");
                        StringBuilder row2 = new StringBuilder(row.toString());
                        for (int k = 0; k < row.length(); k++) {
                            if (row2.charAt(k) == '.') row2.replace(k, k + 1, "O");
                        }
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + row2);
                        if (rowSet.contains(row2.toString())) {
                            pair[0] = i;
                            pair[1] = j;
                            pair[2] = 0;
                            return pair;
                        }
                        row.replace(j, j + 1, ".");
                    }
                }
            }
        }
        // Check collums ------------------------------------------------------------------------------------------------------------
        Set<String> collumSet = new HashSet<>();
        outher2: for (int j = 0; j < this.grid[0].length; j++) {
            StringBuilder collum = new StringBuilder("");
            for (int i = 0; i < this.grid.length; i++) {
                if (this.grid[i][j] == 'X') {
                    collum.append('X');
                } else if (this.grid[i][j] == 'O') {
                    collum.append('O');
                } else {
                    continue outher2;
                }
            }
            collumSet.add(collum.toString());
        }
        for (int j = 0; j < this.grid[0].length; j++) {
            int x = 0, o = 0;
            StringBuilder collum = new StringBuilder("");
            for (int i = 0; i < this.grid.length; i++) {
                if (this.grid[i][j] == 'X') {
                    collum.append('X');
                    x++;
                } else if (this.grid[i][j] == 'O') {
                    collum.append('O');
                    o++;
                } else {
                    collum.append('.');
                }
            }
            if (o == (this.grid.length / 2 - 1)) {
                for (int i = 0; i < collum.length(); i++) {
                    if (collum.charAt(i) == '.') {
                        collum.replace(i, i + 1, "O");
                        StringBuilder collum2 = new StringBuilder(collum.toString());
                        for (int k = 0; k < collum.length(); k++) {
                            if (collum2.charAt(k) == '.') collum2.replace(k, k + 1, "X");
                        }
                        if (collumSet.contains(collum2.toString())) {
                            pair[0] = i;
                            pair[1] = j;
                            pair[2] = 1;
                            return pair;
                        }
                        collum.replace(i, i + 1, ".");
                    }
                }
            } else if (x == (this.grid.length / 2 - 1)) {
                for (int i = 0; i < collum.length(); i++) {
                    if (collum.charAt(i) == '.') {
                        collum.replace(i, i + 1, "X");
                        StringBuilder collum2 = new StringBuilder(collum.toString());
                        for (int k = 0; k < collum.length(); k++) {
                            if (collum2.charAt(k) == '.') collum2.replace(k, k + 1, "O");
                        }
                        if (collumSet.contains(collum2.toString())) {
                            pair[0] = i;
                            pair[1] = j;
                            pair[2] = 0;
                            return pair;
                        }
                        collum.replace(i, i + 1, ".");
                    }
                }
            }
        }
        return null;
    }

    private int[] avoidTriples() {
        int[] pair = new int[3];
        // Check rows -----------------------------------------------------------------------
        for (int i = 0; i < this.grid.length; i++) {
            int x = 0, o = 0;
            StringBuilder row = new StringBuilder("");
            for (int j = 0; j < this.grid[i].length; j++) {
                if (this.grid[i][j] == 'X') {
                    x++;
                    row.append('X');
                }
                else if (this.grid[i][j] == 'O') {
                    o++;
                    row.append('O');
                } else {
                    row.append('.');
                }
            }
            if (x > o || o > x) {
                for (int j = 0; j < this.grid[i].length; j++) {
                    if (row.charAt(j) == '.') {
                        row.replace(j, j + 1, "X");
                        if (!checkLine(new StringBuilder(row.toString()))) {
                            pair[0] = i;
                            pair[1] = j;
                            pair[2] = 0;
                            return pair;
                        }
                        row.replace(j, j + 1, "O");
                        if (!checkLine(new StringBuilder(row.toString()))) {
                            pair[0] = i;
                            pair[1] = j;
                            pair[2] = 1;
                            return pair;
                        }
                        row.replace(j, j + 1, ".");
                    }
                }
            }
        }
        // Check collums ------------------------------------------------------------------------------------
        for (int j = 0; j < this.grid[0].length; j++) {
            int x = 0, o = 0;
            StringBuilder collum = new StringBuilder("");
            for (int i = 0; i < this.grid.length; i++) {
                if (this.grid[i][j] == 'X') {
                    x++;
                    collum.append('X');
                }
                else if (this.grid[i][j] == 'O') {
                    o++;
                    collum.append('O');
                } else {
                    collum.append('.');
                }
            }
            if (x > o || o > x) {
                for (int i = 0; i < this.grid.length; i++) {
                    if (collum.charAt(i) == '.') {
                        collum.replace(i, i + 1, "X");
                        if (!checkLine(new StringBuilder(collum.toString()))) {
                            pair[0] = i;
                            pair[1] = j;
                            pair[2] = 0;
                            return pair;
                        }
                        collum.replace(i, i + 1, "O");
                        if (!checkLine(new StringBuilder(collum.toString()))) {
                            pair[0] = i;
                            pair[1] = j;
                            pair[2] = 1;
                            return pair;
                        }
                        collum.replace(i, i + 1, ".");
                    }
                }
            }
        }
        return null;
    }

    public static boolean checkLine(StringBuilder str) {
        int emtCounter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') {
                emtCounter++;
            }
        }
        if (emtCounter == 0) {
            return checkString(str.toString()); 
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') {
                str.replace(i, i + 1, "X");
                if (checkLine(str)) return true;
                str.replace(i, i + 1, "O");
                if (checkLine(str)) return true;
                str.replace(i, i + 1, ".");
            }
        }
        return false;
    }

    public static boolean checkString(String str) {
        int xR = 0, oR = 0, xR2 = 0, oR2 = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'X') {xR++; xR2++; oR2 = 0;}
            else if (str.charAt(i) == 'O') {oR++; oR2++; xR2 = 0;}
            if (xR > 3 || oR > 3 || xR2 > 2 || oR2 > 2) return false;
        }
        if (xR == 3 && oR == 3) return true;
        return false;
    }
    
    private int[] findDifferentLine() {
        int[] pair = new int[6];
        // Check rows -------------------------------------------------------------------------------------
        Set<String> rowSet = new HashSet<>();
        for (int i = 0; i < this.grid.length; i++) {
            StringBuilder row = new StringBuilder("");
            for (int j = 0; j < this.grid[i].length; j++) {
                if (this.grid[i][j] == 'X') {
                    row.append('X');
                } else if (this.grid[i][j] == 'O') {
                    row.append('O');
                } else {
                    row.append('.');
                }
            }
            rowSet.add(row.toString());
        }
        outher: for (int i = 0; i < this.grid.length; i++) {
            int x = 0; int o = 0; int j1 = -1; int j2 = -1;
            StringBuilder row = new StringBuilder("");
            for (int j = 0; j < this.grid[i].length; j++) {
                if (this.grid[i][j] == 'X') {
                    row.append('X');
                    x++;
                } else if (this.grid[i][j] == 'O') {
                    row.append('O');
                    o++;
                } else {
                    row.append('.');
                    if (j1 == -1) {
                        j1 = j;
                    } else if (j2 == -1) {
                        j2 = j;
                    } else {
                        continue outher;
                    }
                }
            }
            if (x + o == 4) {
                row.replace(j1, j1 + 1, "X");
                row.replace(j2, j2 + 1, "O");
                String row1 = row.toString();
                row.replace(j1, j1 + 1, "O");
                row.replace(j2, j2 + 1, "X");
                String row2 = row.toString();
                if (rowSet.contains(row1) || rowSet.contains(row2)) {
                    if (rowSet.contains(row1) && rowSet.contains(row2)) return null;
                    if (rowSet.contains(row1)) {
                        row = new StringBuilder(row2);
                    } else {
                        row = new StringBuilder(row1);
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
        }
        // Check collums ---------------------------------------------------------------------------------------------------
        Set<String> collumSet = new HashSet<>();
        for (int j = 0; j < this.grid[0].length; j++) {
            StringBuilder collum = new StringBuilder("");
            for (int i = 0; i < this.grid.length; i++) {
                if (this.grid[i][j] == 'X') {
                    collum.append('X');
                } else if (this.grid[i][j] == 'O') {
                    collum.append('O');
                } else {
                    collum.append('.');
                }
            }
            collumSet.add(collum.toString());
        }
        outher2: for (int j = 0; j < this.grid[0].length; j++) {
            int x2 = 0; int o2 = 0; int i1 = -1; int i2 = -1;
            StringBuilder collum = new StringBuilder("");
            for (int i = 0; i < this.grid.length; i++) {
                if (this.grid[i][j] == 'X') {
                    collum.append('X');
                    x2++;
                } else if (this.grid[i][j] == 'O') {
                    collum.append('O');
                    o2++;
                } else {
                    collum.append('.');
                    if (i1 == -1) {
                        i1 = i;
                    } else if (i2 == -1) {
                        i2 = i;
                    } else {
                        continue outher2;
                    }
                }
            }
            if (o2 + x2 == 4) {
                collum.replace(i1, i1 + 1, "X");
                collum.replace(i2, i2 + 1, "O");
                String collum1 = collum.toString();
                collum.replace(i1, i1 + 1, "O");
                collum.replace(i2, i2 + 1, "X");
                String collum2 = collum.toString();
                if (collumSet.contains(collum1) || collumSet.contains(collum2)) {
                    if (collumSet.contains(collum1) && collumSet.contains(collum2)) return null;
                    if (collumSet.contains(collum1)) {
                        collum = new StringBuilder(collum2);
                    } else {
                        collum = new StringBuilder(collum1);
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

    // DONE -------------------------------------------------------------------------
    private boolean full() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.grid[i][j] == 0) return false;
            }
        }
        return true;
    }
    // DONE -------------------------------------------------------------------------

    // DONE -------------------------------------------------------------------------
    public void print() {
        System.out.println("-------------");
        for (int i = 0; i < this.size; i++) {
            System.out.print("|");
            for (int j = 0; j < this.size; j++) {
                if (this.grid[i][j] == 0) System.out.print(" |");
                else System.out.print(this.grid[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("-------------");
    }
    // DONE -------------------------------------------------------------------------

    private void generateGrid() {
        this.grid = new char[this.size][this.size];
        this.generateFullGrid(0, 0);
        //this.makePuzzle();
    }

    // DONE -------------------------------------------------------------------------
    private boolean generateFullGrid(int i, int j) {
        if (i >= this.size) return true;
        if (j >= this.size) return this.generateFullGrid(i + 1, 0); 
        char random = Math.random() < 0.5 ? 'X' : 'O';
        this.grid[i][j] = random;
        boolean valid = this.check();
        if (!valid || !this.generateFullGrid(i, j + 1)) {
            random = random == 'X' ? 'O' : 'X';
            this.grid[i][j] = random;
            valid = this.check();
            if (!valid || !this.generateFullGrid(i, j + 1)) {
                this.grid[i][j] = 0;
                return false;
            }
        }
        return true;
    }
    // DONE -------------------------------------------------------------------------

    /*private void makePuzzle() {
        while(true) {
            int rI = (int) (Math.random() * this.grid.length), rJ = (int) (Math.random() * this.grid[0].length);
            System.out.println(rI + " " + rJ);
            this.print();
            while (this.grid[rI][rJ] == 0) {
                rI = (int) (Math.random() * this.grid.length); rJ = (int) (Math.random() * this.grid[0].length);
                //System.out.println(rI + " " + rJ);
            }
            char temp = this.grid[rI][rJ];
            this.grid[rI][rJ] = 0;
            if (!this.checkIfAbleToSolve()) {
                this.grid[rI][rJ] = temp;
                break;
            }
        }
    }*/

    // DONE -------------------------------------------------------------------------
    public boolean check() {

        int numOfXO = this.size / 2;

        // Check rows
        Set<String> rowSet = new HashSet<>();
        for (int i = 0; i < this.size; i++) {
            StringBuilder row = new StringBuilder("");
            int numX = 0; int numO = 0; int consecXCount = 0; int consecOCount = 0;
            for (int j = 0; j < this.size; j++) {
                if (this.grid[i][j] == 'X') {numX++; consecXCount++; consecOCount = 0; row.append('X');}
                else if (this.grid[i][j] == 'O') {numO++; consecOCount++; consecXCount = 0; row.append('O');}
                if (numX > numOfXO || numO > numOfXO || consecXCount > 2 || consecOCount > 2) return false;
            }
            if (numX == numOfXO && numO == numOfXO) {
                if (rowSet.contains(row.toString())) return false;
                else rowSet.add(row.toString());
            }
        }

        // Check collums
        Set<String> collumSet = new HashSet<>();
        for (int j = 0; j < this.size; j++) {
            StringBuilder collum = new StringBuilder("");
            int numX = 0; int numO = 0; int consecXCount = 0; int consecOCount = 0;
            for (int i = 0; i < this.size; i++) {
                if (this.grid[i][j] == 'X') {numX++; consecXCount++; consecOCount = 0; collum.append('X');}
                else if (this.grid[i][j] == 'O') {numO++; consecOCount++; consecXCount = 0; collum.append('O');}
                if (numX > numOfXO || numO > numOfXO || consecXCount > 2 || consecOCount > 2) return false;
            }
            if (numX == numOfXO && numO == numOfXO) {
                if (collumSet.contains(collum.toString())) return false;
                else collumSet.add(collum.toString());
            }
        }

        return true;

    }
    // DONE -------------------------------------------------------------------------

}