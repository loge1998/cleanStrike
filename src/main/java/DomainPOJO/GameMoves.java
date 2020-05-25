package DomainPOJO;

import org.apache.commons.lang3.StringUtils;

public enum GameMoves {

    STRIKE(1,false),
    MULTI_STRIKE(2,false),
    RED_STRIKE(3,false),
    STRIKER_STRIKE(-1,true),
    DEFLULTCOIN(0,true),
    MISS(0,false);

    private final int point;
    private boolean isFoulMove;

    private GameMoves(int point,boolean isFoulMove) {
        this.point = point;
        this.isFoulMove = isFoulMove;
    }

    public int getPoint() {
        return this.point;
    }

    public boolean getFoulMove() {
        return this.isFoulMove;
    }

    public static GameMoves getGameMove(String input) {

        if(input == null || input.trim().isEmpty()) {
            throw new UnsupportedOperationException("input - " + input + " is not a valid Game Move");
        }

        return GameMoves.valueOf(input.toUpperCase());
    }
}
