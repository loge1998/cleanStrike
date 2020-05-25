import DomainPOJO.GameMoves;
import DomainPOJO.Player;

public class CarromGame {

    Player playerOne, playerTwo;
    int numberOfRed;
    int numberOfBlack;

    public CarromGame(Player playerOne, Player playerTwo,int numberOfRed, int numberOfBlack) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.numberOfRed = numberOfRed;
        this.numberOfBlack = numberOfBlack;
    }

    @Override
    public String toString() {
        return "CarromGame{" +
                "playerOne=" + playerOne +
                ", playerTwo=" + playerTwo +
                ", numberOfRed=" + numberOfRed +
                ", numberOfBlack=" + numberOfBlack +
                '}';
    }

    public void updateBlackCoin(int change) {
        this.numberOfBlack += change;
    }

    public void updateRedCoin(int change) {
        this.numberOfRed += change;
    }

    public void move(Player player, String move) {
        GameMoves gameMove = GameMoves.getGameMove(move);
        player.nextMove(gameMove);
        updateCoinsBasedOnMove(gameMove);
    }

    public void updateCoinsBasedOnMove(GameMoves gameMove) {
        switch (gameMove.name())
        {
            case "STRIKE": {
                this.updateBlackCoin(-1);
            }
            case "RED_STRIKE": {
                this.updateRedCoin(-1);
            }
        }
    }


    public boolean checkForDraw() {
        return (numberOfBlack == 0 && numberOfRed == 0);
    }


    private Player checkForWinner() {
        if(playerOne.getPoints() > playerTwo.getPoints()) {
            if(playerOne.getPoints() > 5 && playerOne.getPoints() -playerTwo.getPoints() >=3)
                return  playerOne;
        }
        else {
            if(playerTwo.getPoints() > 5 && playerTwo.getPoints() -playerOne.getPoints() >= 3)
                return playerTwo;
        }
        return null;
    }

    public boolean isGameOver() {
        Player winner = checkForWinner();
        if (winner != null) {
            System.out.println(winner);
            return true;
        }
        else {
            if (checkForDraw()) {
                System.out.println("Draw");
                return true;
            }
        }
        return false;
    }

}
