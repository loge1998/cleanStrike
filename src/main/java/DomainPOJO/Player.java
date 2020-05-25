package DomainPOJO;
import org.apache.commons.collections4.queue.CircularFifoQueue;

public class Player {
    private final String name;
    private int points;
    private int fouls;
    private CircularFifoQueue<GameMoves> moveHistory;


    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.fouls = 0;
        this.moveHistory = new CircularFifoQueue<GameMoves>(3); // doubt
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", fouls=" + fouls +
                ", shotHistory=" + moveHistory +
                '}';
    }

    public void updatePoints(int point) {
        this.points += point;
    }

    public void checkFoulAndUpdatePoint() {
        if(this.fouls >= 3)
            this.updatePoints(-1);  //doubt
    }

    public Boolean checkForThreeMiss() {
        int noOfMisses = 0;

        if(moveHistory.size()<3)
            return false;

        for(int i=0;i<3;i++) {
            GameMoves gameMove = moveHistory.get(i);
            if(gameMove.name() == "MISS")
                noOfMisses++;
        }
        return (noOfMisses == 3);
    }

    public void incrementFoul() {
        this.fouls++;
    }

    public boolean checkFoulMoves(GameMoves gameMove) {
        return gameMove.getFoulMove();
    }

    public void nextMove(GameMoves gameMove)
    {
        moveHistory.add(gameMove);
        this.updatePoints(gameMove.getPoint());
        if(this.checkFoulMoves(gameMove))
            this.incrementFoul();
        if(checkForThreeMiss()) {
            this.updatePoints(-1);
            this.incrementFoul();
        }
        checkFoulAndUpdatePoint();
    }

}