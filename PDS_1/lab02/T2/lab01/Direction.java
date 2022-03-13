public enum Direction {
    UP, DOWN, LEFT, RIGHT, UPLEFT, DOWNLEFT, UPRIGHT, DOWNRIGHT;  

    public static Direction getEnum(int i) {
        switch(i){
            case(1):
            return UP;
            case(2):
            return DOWN;
            case(3):
            return LEFT;
            case(4):
            return RIGHT;
            case(5):
            return UPLEFT;
            case(6):
            return DOWNLEFT;
            case(7):
            return UPRIGHT;
            default:
            return DOWNRIGHT;
        }
    }
}