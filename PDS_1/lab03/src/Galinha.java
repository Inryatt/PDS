public class Galinha implements JGaloInterface {
    private Boolean turn;
    private Boolean grid[][];   //This is the Game Table
                                //Null->Empty Spot
                                //0->'O' in it
                                //1->'X' in it

    //Class Constructor
    public Galinha(Boolean b) {
        this.grid = new Boolean[3][3];
        this.turn = b;  //True  -> 'X'
                        //False -> 'O'
    }

    //Returns the player who will play next
    @Override
    public char getActualPlayer() {
        // Alternates between players
        if (this.turn == true) {
            this.turn = false;
            return 'X';
        } else {
            this.turn = true;
            return 'O';
        }
    }

    //Marks in the game table which player occupies the spot
    @Override
    public boolean setJogada(int lin, int col) {
        this.grid[lin-1][col-1] = this.turn;
        return false;
    }

    //Checks if the game is over
    @Override
    public boolean isFinished() {
        if(checkResult() != 0 ) return true; //checkResult returns 0 if the game is over
        else {
            int count = 0;
            for(Boolean[] col : grid) {
                for(Boolean b: col) {
                    if (b != null) count++;
                }
            }
            return count == 9; //if all spots are filled, game ends
        }
    }

    //Checks if the game has been won, and in that case, returns who won the game ('O' or 'X')
    //If the game is a tie, returns  ' '
    //if the game hasn't finished, returns 0
    @Override
    public char checkResult() {
        //check possible vertical wins
        for (int i = 0; i < 3; i++) { // para cada linha
            if(this.grid[i][0]!=null&&this.grid[i][1]!=null && this.grid[i][2]!=null) {// Checks if the values aren't null (Spots are occupied)
                if (this.grid[i][0] == this.grid[i][1] && this.grid[i][1] == this.grid[i][2]) { // And they all have the same value
                                                                                                // (Same player on all of them)
                    if (this.grid[i][0]) { // A spot occupied by 'O' will return true
                        return 'O';
                    } else return 'X';
                }
            }
        }

        //check possible horizontal wins
        for (int i = 0; i < 3; i++) {
            if(this.grid[0][i]!=null&&this.grid[1][i]!=null && this.grid[2][i]!=null) {
                if (this.grid[0][i] == this.grid[1][i] && this.grid[1][i]== this.grid[2][i]) {
                    if (this.grid[0][i]) {
                        return 'O';
                    } else return 'X';
                }
            }
        }

        //Check possible diagonal wins
        if(this.grid[0][0]!=null&&this.grid[1][1]!=null && this.grid[2][2]!=null)
            if (this.grid[0][0] == this.grid[1][1] && this.grid[1][1]== this.grid[2][2]) {
                if (this.grid[0][0]) {
                    return 'O';
                } else return 'X';
            }

        //check possible anti-diagonal (top right to bottom left) wins
        if(this.grid[0][2]!=null&&this.grid[1][1]!=null && this.grid[2][0]!=null)
            if (this.grid[0][2] == this.grid[1][1]&& this.grid[1][1] == this.grid[2][0]) {
                if (this.grid[0][2]) {
                    return 'O';
                } else return 'X';
            }

        //If no player has gotten a win until here, it's a tie if the board is full
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.grid[i][j] != null) {
                    counter++;
                }
            }
        }
        //It's a Tie!
        if (counter == 9)
            return ' ';

        //Game isn't over yet
        return 0;
    }
}
