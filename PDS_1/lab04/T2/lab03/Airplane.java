

public class Airplane {
    int linesTourist;
    int rowsTourist;

    int linesExec;
    int rowsExec;

    int nSeatsExec;
    int nSeatsTourist;

    int totalSeats;
    
    
    public Airplane(int linesExec, int rowsExec, int linesTourist, int rowsTourist){ //in case the airplane has executive class
        this.linesExec=linesExec;
        this.rowsExec=rowsExec;
        this.linesTourist=linesTourist;
        this.rowsTourist=rowsTourist;
    }

    public Airplane(int linesTourist, int rowsTourist) { //in case the airplane hasn't executive class
        this.linesTourist = linesTourist;
        this.rowsTourist=rowsTourist;
        this.linesExec = 0;
        this.rowsExec = 0;
    }

    public int getLinesTourist() {
        return this.linesTourist;
    }

    public void setLinesTourist(int linesTourist) {
        this.linesTourist = linesTourist;
    }

    public int getRowsTourist() {
        return this.rowsTourist;
    }

    public void setRowsTourist(int rowsTourist) {
        this.rowsTourist = rowsTourist;
    }

    public int getLinesExec() {
        return this.linesExec;
    }

    public void setLinesExec(int linesExec) {
        this.linesExec = linesExec;
    }

    public int getRowsExec() {
        return this.rowsExec;
    }

    public void setRowsExec(int rowsExec) {
        this.rowsExec = rowsExec;
    }

    public int getNSeatsExec() {
        return this.nSeatsExec;
    }

    public void setNSeatsExec(int nSeatsExec) {
        this.nSeatsExec = nSeatsExec;
    }

    public int getNSeatsTourist() {
        return this.nSeatsTourist;
    }

    public void setNSeatsTourist(int nSeatsTourist) {
        this.nSeatsTourist = nSeatsTourist;
    }

    public int getTotalSeats() {
        return (linesExec*rowsExec+linesTourist*rowsTourist); //calculate the number of seats
        
    }
   

    public int getnSeatsExec() {
        return nSeatsExec;
    }
    public int getnSeatsTourist(){
        return nSeatsTourist;
    }

    

    public void setTotalSeats(int nSeats) {
        this.totalSeats = nSeats;
    }
    
}
