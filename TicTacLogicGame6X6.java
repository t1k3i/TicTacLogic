import java.util.Set;
import java.util.HashSet;

public class TicTacLogicGame6X6 {

    private char[][] grid;
    private int size;

    TicTacLogicGame6X6(char[][] grid) {
        this.grid = grid;
        this.size = this.grid.length;
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
        int countEmpty = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.grid[i][j] == 0) countEmpty++;
            }
        }

        while (countEmpty > 0) {
            countEmpty--;
            this.print();
            System.out.println(this.check());
            Triple<?, ?, ?> triple = this.avoidTriples1and2();
            if (triple != null) {
                System.out.println(">>>>>>>>>>>>>> Rule 1");
                this.grid[(Integer) triple.fst()][(Integer) triple.snd()] = (Character) triple.thd();
                continue;
            }
            triple = this.findFullLine();
            if (triple != null) {
                System.out.println(">>>>>>>>>>>>>> Rule 2");
                this.grid[(Integer) triple.fst()][(Integer) triple.snd()] = (Character) triple.thd();
                continue;
            }
            triple = this.findDifferentLine();
            if (triple != null) {
                System.out.println(">>>>>>>>>>>>>> Rule 3");
                int i1 = (Integer) ((Triple<?, ?, ?>)triple.fst()).fst();
                int j1 = (Integer) ((Triple<?, ?, ?>)triple.fst()).snd();
                char c1 = (Character) ((Triple<?, ?, ?>)triple.fst()).thd();
                this.grid[i1][j1] = c1;
                int i2 = (Integer) ((Triple<?, ?, ?>)triple.snd()).fst();
                int j2 = (Integer) ((Triple<?, ?, ?>)triple.snd()).snd();
                char c2 = (Character) ((Triple<?, ?, ?>)triple.snd()).thd();
                this.grid[i2][j2] = c2;
                countEmpty--;
                continue;
            }
            triple = this.avoidTriples3();
            if (triple != null) {
                System.out.println(">>>>>>>>>>>>>> Rule 4");
                this.grid[(Integer) triple.fst()][(Integer) triple.snd()] = (Character) triple.thd();
                continue;
            }
            /*
            int[] advanced2 = this.advanced2();
            if (advanced2 != null) {
                System.out.println(">>>>>>>>>>>>>> Advanced 1");
                this.grid[advanced2[0]][advanced2[1]] = advanced2[2] == 1 ? 'X' : 'O';
                continue;
            }*/
            // TODO one more advanced rule
            return false;
        }

        return true;
    }

    /* 
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
 */

    private Triple<Integer, Integer, Character> avoidTriples1and2() {
        // Check rows
        for (int i = 0; i < this.size; i++) {
            int consecXCount = 0; int consecOCount = 0;
            for (int j = 0; j < this.size; j++) {
                if (this.grid[i][j] == 'X') {
                    consecXCount++; consecOCount = 0;
                    if (consecXCount == 2) {
                        if (j + 1 < this.grid[i].length && this.grid[i][j + 1] == 0)
                            return new Triple<Integer,Integer,Character>(i, j + 1, 'O');
                        else if (j - 2 >= 0 && this.grid[i][j - 2] == 0)
                            return new Triple<Integer,Integer,Character>(i, j - 2, 'O');
                    }
                } else if (this.grid[i][j] == 'O') {
                    consecOCount++; consecXCount = 0;
                    if (consecOCount == 2) {
                        if (j + 1 < this.grid[i].length && this.grid[i][j + 1] == 0)
                            return new Triple<Integer,Integer,Character>(i, j + 1, 'X');
                        else if (j - 2 >= 0 && this.grid[i][j - 2] == 0)
                            return new Triple<Integer,Integer,Character>(i, j - 2, 'X');
                    }
                } else if (this.grid[i][j] == 0 && j > 0 && j < this.size - 1) {
                    consecXCount = 0; consecOCount = 0;
                    if (this.grid[i][j - 1] == 'X' && this.grid[i][j + 1] == 'X')
                        return new Triple<Integer,Integer,Character>(i, j, 'O');
                    else if (this.grid[i][j - 1] == 'O' && this.grid[i][j + 1] == 'O')
                        return new Triple<Integer,Integer,Character>(i, j, 'X');
                } else {
                    consecXCount = 0; consecOCount = 0;
                }
            }
        }

        // Check collums
        for (int j = 0; j < this.size; j++) {
            int consecXCount = 0; int consecOCount = 0;
            for (int i = 0; i < this.size; i++) {
                if (this.grid[i][j] == 'X') {
                    consecXCount++; consecOCount = 0;
                    if (consecXCount == 2) {
                        if (i + 1 < this.size && this.grid[i + 1][j] == 0)
                            return new Triple<Integer,Integer,Character>(i + 1, j, 'O');
                        else if (i - 2 >= 0 && this.grid[i - 2][j] == 0)
                            return new Triple<Integer,Integer,Character>(i - 2, j, 'O');
                    }
                } else if (this.grid[i][j] == 'O') {
                    consecOCount++; consecXCount = 0;
                    if (consecOCount == 2) {
                        if (i + 1 < this.size && this.grid[i + 1][j] == 0)
                            return new Triple<Integer,Integer,Character>(i + 1, j, 'X');
                        else if (i - 2 >= 0 && this.grid[i - 2][j] == 0)
                            return new Triple<Integer,Integer,Character>(i - 2, j, 'X');
                    }
                } else if (this.grid[i][j] == 0 && i > 0 && i < this.size - 1) {
                    consecXCount = 0; consecOCount = 0;
                    if (this.grid[i - 1][j] == 'X' && this.grid[i + 1][j] == 'X')
                        return new Triple<Integer,Integer,Character>(i, j, 'O');
                    else if (this.grid[i - 1][j] == 'O' && this.grid[i + 1][j] == 'O')
                        return new Triple<Integer,Integer,Character>(i, j, 'X');
                } else {
                    consecXCount = 0; 
                    consecOCount = 0;
                }
            }
        }

        return null;
    }

    private Triple<Integer, Integer, Character> findFullLine() {
        int numOfXO = this.size / 2;

        // Check rows
        for (int i = 0; i < this.size; i++) {
            int numOfX = 0; int numOfO = 0;
            for (int j = 0; j < this.size; j++) {
                if (this.grid[i][j] == 'X') {
                    numOfX++;
                } else if (this.grid[i][j] == 'O') {
                    numOfO++;
                }
            }
            if (numOfX == numOfXO) {
                for (int j = 0; j < this.size; j++) {
                    if (this.grid[i][j] == 0)
                        return new Triple<Integer,Integer,Character>(i, j, 'O');
                }
            } else if (numOfO == numOfXO) {
                for (int j = 0; j < this.size; j++) {
                    if (this.grid[i][j] == 0)
                        return new Triple<Integer,Integer,Character>(i, j, 'X');
                }
            }
        }

        // Check collums
        for (int j = 0; j < this.size; j++) {
            int numOfX = 0; int numOfO = 0;
            for (int i = 0; i < this.size; i++) {
                if (this.grid[i][j] == 'X') {
                    numOfX++;
                } else if (this.grid[i][j] == 'O') {
                    numOfO++;
                }
            }
            if (numOfX == numOfXO) {
                for (int i = 0; i < this.size; i++) {
                    if (this.grid[i][j] == 0)
                        return new Triple<Integer,Integer,Character>(i, j, 'O');
                }
            } else if (numOfO == numOfXO) {
                for (int i = 0; i < this.size; i++) {
                    if (this.grid[i][j] == 0)
                        return new Triple<Integer,Integer,Character>(i, j, 'X');
                }
            }
        }

        return null;
    }

    private Triple<Integer, Integer, Character> avoidTriples3() {
        // Check rows
        for (int i = 0; i < this.size; i++) {
            int numOfX = 0, numOfO = 0;
            StringBuilder row = new StringBuilder("");
            for (int j = 0; j < this.size; j++) {
                if (this.grid[i][j] == 'X') {
                    numOfX++;
                    row.append('X');
                } else if (this.grid[i][j] == 'O') {
                    numOfO++;
                    row.append('O');
                } else {
                    row.append('.');
                }
            }
            if (numOfX > numOfO || numOfO > numOfX) {
                for (int j = 0; j < this.size; j++) {
                    if (row.charAt(j) == '.') {
                        row.replace(j, j + 1, "X");
                        if (!checkLine(new StringBuilder(row.toString())))
                            return new Triple<Integer,Integer,Character>(i, j, 'O');
                        row.replace(j, j + 1, "O");
                        if (!checkLine(new StringBuilder(row.toString())))
                            return new Triple<Integer,Integer,Character>(i, j, 'X');
                        row.replace(j, j + 1, ".");
                    }
                }
            }
        }

        // Check collums
        for (int j = 0; j < this.size; j++) {
            int numOfX = 0, numOfO = 0;
            StringBuilder collum = new StringBuilder("");
            for (int i = 0; i < this.size; i++) {
                if (this.grid[i][j] == 'X') {
                    numOfX++;
                    collum.append('X');
                } else if (this.grid[i][j] == 'O') {
                    numOfO++;
                    collum.append('O');
                } else {
                    collum.append('.');
                }
            }
            if (numOfX > numOfO || numOfO > numOfX) {
                for (int i = 0; i < this.size; i++) {
                    if (collum.charAt(i) == '.') {
                        collum.replace(i, i + 1, "X");
                        if (!checkLine(new StringBuilder(collum.toString())))
                            return new Triple<Integer,Integer,Character>(i, j, 'O');
                        collum.replace(i, i + 1, "O");
                        if (!checkLine(new StringBuilder(collum.toString())))
                            return new Triple<Integer,Integer,Character>(i, j, 'X');
                        collum.replace(i, i + 1, ".");
                    }
                }
            }
        }

        return null;
    }

    private static boolean checkLine(StringBuilder str) {
        int emptyCounter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') {
                emptyCounter++;
            }
        }
        if (emptyCounter == 0) return checkString(str.toString()); 
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

    private static boolean checkString(String str) {
        int numOfXO = str.length() / 2;
        int numOfX = 0, numOfO = 0, consecXCount = 0, consecOCount = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'X') {numOfX++; consecXCount++; consecOCount = 0;}
            else if (str.charAt(i) == 'O') {numOfO++; consecOCount++; consecXCount = 0;}
            if (numOfX > numOfXO || numOfO > numOfXO || consecXCount > 2 || consecOCount > 2) return false;
        }
        if (numOfX == numOfXO && numOfO == numOfXO) return true;
        return false;
    }

    private Triple<Triple<Integer, Integer, Character>, Triple<Integer, Integer, Character>, ?> findDifferentLine() {
        // Check rows
        Set<String> rowSet = new HashSet<>();
        for (int i = 0; i < this.size; i++) {
            StringBuilder row = new StringBuilder("");
            for (int j = 0; j < this.size; j++) {
                if (this.grid[i][j] == 'X') row.append('X');
                else if (this.grid[i][j] == 'O') row.append('O');
                else row.append('.');
            }
            rowSet.add(row.toString());
        }
        outher: for (int i = 0; i < this.size; i++) {
            int x = 0; int o = 0; int empty1 = -1; int empty2 = -1;
            StringBuilder row = new StringBuilder("");
            for (int j = 0; j < this.size; j++) {
                if (this.grid[i][j] == 'X') {
                    row.append('X');
                    x++;
                } else if (this.grid[i][j] == 'O') {
                    row.append('O');
                    o++;
                } else {
                    row.append('.');
                    if (empty1 == -1) empty1 = j;
                    else if (empty2 == -1) empty2 = j;
                    else continue outher;
                }
            }
            if (x + o == this.size - 2) {
                row.replace(empty1, empty1 + 1, "X");
                row.replace(empty2, empty2 + 1, "O");
                String row1 = row.toString();
                row.replace(empty1, empty1 + 1, "O");
                row.replace(empty2, empty2 + 1, "X");
                String row2 = row.toString();
                if (rowSet.contains(row1) || rowSet.contains(row2)) {
                    if (rowSet.contains(row1) && rowSet.contains(row2)) return null;
                    if (rowSet.contains(row1)) row = new StringBuilder(row2);
                    else row = new StringBuilder(row1);
                    Triple<Integer, Integer, Character> triple1 = new Triple<>(i, empty1, row.charAt(empty1));
                    Triple<Integer, Integer, Character> triple2 = new Triple<>(i, empty2, row.charAt(empty2));
                    return new Triple<Triple<Integer,Integer,Character>,
                        Triple<Integer,Integer,Character>,Object>(triple1, triple2, null);
                }
            }
        }

        // Check collums
        Set<String> collumSet = new HashSet<>();
        for (int j = 0; j < this.size; j++) {
            StringBuilder collum = new StringBuilder("");
            for (int i = 0; i < this.size; i++) {
                if (this.grid[i][j] == 'X') collum.append('X');
                else if (this.grid[i][j] == 'O') collum.append('O');
                else collum.append('.');
            }
            collumSet.add(collum.toString());
        }
        outher2: for (int j = 0; j < this.size; j++) {
            int x = 0; int o = 0; int empty1 = -1; int empty2 = -1;
            StringBuilder collum = new StringBuilder("");
            for (int i = 0; i < this.size; i++) {
                if (this.grid[i][j] == 'X') {
                    collum.append('X');
                    x++;
                } else if (this.grid[i][j] == 'O') {
                    collum.append('O');
                    o++;
                } else {
                    collum.append('.');
                    if (empty1 == -1) empty1 = i;
                    else if (empty2 == -1) empty2 = i;
                    else continue outher2;
                }
            }
            if (o + x == this.size - 2) {
                collum.replace(empty1, empty1 + 1, "X");
                collum.replace(empty2, empty2 + 1, "O");
                String collum1 = collum.toString();
                collum.replace(empty1, empty1 + 1, "O");
                collum.replace(empty2, empty2 + 1, "X");
                String collum2 = collum.toString();
                if (collumSet.contains(collum1) || collumSet.contains(collum2)) {
                    if (collumSet.contains(collum1) && collumSet.contains(collum2)) return null;
                    if (collumSet.contains(collum1)) collum = new StringBuilder(collum2);
                    else collum = new StringBuilder(collum1);
                    Triple<Integer, Integer, Character> triple1 = new Triple<>(empty1, j, collum.charAt(empty1));
                    Triple<Integer, Integer, Character> triple2 = new Triple<>(empty2, j, collum.charAt(empty2));
                    return new Triple<Triple<Integer,Integer,Character>,
                        Triple<Integer,Integer,Character>,Object>(triple1, triple2, null);
                }
            }
        }

        return null;
    }

    // DONE -------------------------------------------------------------------------
    public void print() {
        int numOfLine = this.size * 2 + 1;
        for (int i = 0; i < numOfLine; i++) System.out.print("-");
        System.out.println(); 
        for (int i = 0; i < this.size; i++) {
            System.out.print("|");
            for (int j = 0; j < this.size; j++) {
                if (this.grid[i][j] == 0) System.out.print(" |");
                else System.out.print(this.grid[i][j] + "|");
            }
            System.out.println();
        }
        for (int i = 0; i < numOfLine; i++) System.out.print("-");
        System.out.println(); 
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
                else {consecOCount = 0; consecXCount = 0;}
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
                else {consecOCount = 0; consecXCount = 0;}
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