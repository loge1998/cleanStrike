import DomainPOJO.Player;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import  java.io.*;
import java.util.HashMap;
import java.util.Scanner;



public class Main {

    private HashMap<String,Player> playerHashMap;
    private CarromGame carromGame;
    private static Logger logger = LogManager.getLogger(Main.class.getName());

    public Main(String initializationFile) {
        playerHashMap = new HashMap<>();
        initialCarromBoardFromFile(initializationFile);
        logger.debug("Main Class Initialized");
    }

    public void initialCarromBoardFromFile(String fileName) {
        try {
            logger.debug("Reading Initialization File");
            File initializationFile = new File(fileName);
            Scanner scanner = new Scanner(initializationFile);
            String inputLine = scanner.nextLine();
            String[] inputs = inputLine.split(" ");
            Player playerOne = new Player(inputs[0]);
            Player playerTwo = new Player(inputs[1]);
            playerHashMap.put(inputs[0],playerOne);
            playerHashMap.put(inputs[1],playerTwo);
            int noOfBlack = Integer.parseInt(inputs[2]);
            int noOfRed = Integer.parseInt(inputs[3]);
            this.carromGame = new CarromGame(playerOne, playerTwo, noOfRed, noOfBlack);
            logger.debug("Player Created" + playerOne);
            logger.debug("Player Created" + playerTwo);
            logger.debug("CarromBoard object Created"+ carromGame);
        }catch (FileNotFoundException exception) {
            logger.error("Error - Initialization file(" + fileName+ ") not found" + exception);
        }
    }

    public void processInput(String fileName)
    {
        try {
            logger.debug("Reading Input File");
            File inputFile = new File(fileName);
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                String[] inputs = inputLine.split(" ");
                String playerName = inputs[0];
                String move = inputs[1];
                logger.info("Player " + playerName + " : " + move);
                Player player = playerHashMap.get(playerName);
                carromGame.move(player, move);
                if(carromGame.isGameOver())
                    break;
            }
        }catch (FileNotFoundException e) {
            logger.error("Error - Initialization file(" + fileName+ ") not found" + e);
        }
    }


    public static void main(String[] args) {
        String inputFile = "/Users/logeshb/IdeaProjects/cleanStrike-1/src/main/java/input.txt";
        String initializationFile = "/Users/logeshb/IdeaProjects/cleanStrike-1/src/main/java/initial.txt";
        Main main = new Main(initializationFile);
        main.processInput(inputFile);
    }
}
