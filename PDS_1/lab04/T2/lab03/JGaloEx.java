
public class JGaloEx implements JGaloInterface {
    public char [][] board = new char [3][3];
    
    int numJogadas = 0;
    static char winner;
    private char player;

    public JGaloEx(char player){
        this.player=player; 
        
    }
    public char getActualPlayer() { 
        return this.player;

    }
    public char setNextPlayer(char player){
        return this.player=player;
    }

    public boolean setJogada(int lin, int col) {
        char c = getActualPlayer();
        if (board[lin-1][col-1] == 'X' || board[lin-1][col-1] == 'O'){  //see if the move can be made in that square.
            return false;
        }

        else {
            numJogadas++; //if the move can be made the number of plays increase and the player character is add to the board on the place he wants
            board[lin-1][col-1] = c;
            return true;

        }
    }
    
    public boolean isFinished() {
        
        // check if the game is finised : boolean true if so
        //to see is finished we have to check 8 possibilities (vertical, horizontal, diagonal)
        char c = getActualPlayer();
        winner=c;
    
        //Horizontal

        if (board[0][0] == c && board[0][1] == c && board[0][2] == c) {
            return true;
        }
        else if (board[1][0] == c && board[1][1] == c && board[1][2] == c) {
            return true;
        }
        else if (board[2][0] == c && board[2][1] == c && board[2][2] == c) {
            return true;
        }

        //Vertical

        else if (board[0][0] == c && board[1][0] == c && board[2][0] == c) {
            return true;
        }
        
        else if (board[0][1] == c && board[1][1] == c && board[2][1] == c) {
            return true;
        }
        else if (board[0][2] == c && board[1][2] == c && board[2][2] == c) {
            return true;
        }

        //Diagonal
        else if (board[0][0] == c && board[1][1] == c && board[2][2] == c) {
            return true;
        }
        else if (board[0][2] == c && board[1][1] == c && board[2][0] == c) {
            return true;
        }
        else if (numJogadas == 9){ //if 9 moves were made, the board is full, no one wins, it's a tie
            winner=' ';
            return true;
        }
        else { //choose who is the next player 
            if (getActualPlayer() == 'X'){ 
                setNextPlayer('O'); 
            }
            else{
                setNextPlayer('X');
            }
            return false;
        }
       
    }
    public char checkResult() {
        return winner;
    }
}
