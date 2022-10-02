import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame implements Strategy{

    JPanel mainPanel;


    JPanel topPanel;
    JButton rockButton;
    JButton paperButton;
    JButton scissorsButton;

    JButton quitButton;

    Image rockIcon;
    Image paperIcon;
    Image scissorsIcon;

    Icon scaledRockIcon;
    Icon scaledPaperIcon;
    Icon scaledScissorsIcon;

    JPanel middlePanel;
    JLabel playerWinsLabel;
    JLabel computerWinsLabel;
    JLabel tiesLabel;
    JTextField playerWinsTF;
    JTextField computerWinsTF;
    JTextField tiesTF;

    JPanel bottomPanel;
    JTextArea results;
    JScrollPane resultsScroller;



    String[] computerArray;
    Random rand;
    String userChoice;
    String computerChoice;
    int computerWins;
    int userWins;
    int ties;


    // Extra Credit
    int amountRockUsed;
    int amountPaperUsed;
    int amountScissorsUsed;

    String lastUsed;
    String mostUsed;
    String leastUsed;
    Random algorithmDecider;
    int percent;
    public RockPaperScissorsFrame() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        ties = 0;
        userWins = 0;
        computerWins = 0;

        createTopPanel();
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        mainPanel.add(topPanel, BorderLayout.NORTH);

        createMiddlePanel();
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        createBottomPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setTitle("Rock Paper Scissors Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(mainPanel);

    }

    private void createTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 4));



        rockIcon = new ImageIcon("src/rock.jpg").getImage().getScaledInstance(10, 20, java.awt.Image.SCALE_SMOOTH);
        paperIcon = new ImageIcon("src/paper.jpg").getImage().getScaledInstance(10, 20, java.awt.Image.SCALE_SMOOTH);
        scissorsIcon = new ImageIcon("src/scissors.jpg").getImage().getScaledInstance(10, 20, java.awt.Image.SCALE_SMOOTH);

        scaledRockIcon = new ImageIcon(rockIcon);
        scaledPaperIcon = new ImageIcon(paperIcon);
        scaledScissorsIcon = new ImageIcon(scissorsIcon);


        rockButton = new JButton(scaledRockIcon);
        paperButton = new JButton(scaledPaperIcon);
        scissorsButton = new JButton(scaledScissorsIcon);
        quitButton = new JButton("Quit");

        rockButton.addActionListener((ActionEvent ae) -> determineMove("Rock"));

        paperButton.addActionListener((ActionEvent ae) -> determineMove("Paper"));
        scissorsButton.addActionListener((ActionEvent ae) -> determineMove("Scissors"));

        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));

        topPanel.add(rockButton);
        topPanel.add(paperButton);
        topPanel.add(scissorsButton);
        topPanel.add(quitButton);

    }

    private void createMiddlePanel() {
        middlePanel = new JPanel();
        playerWinsLabel = new JLabel("Players Wins: ");
        computerWinsLabel = new JLabel("Computers Wins: ");
        tiesLabel = new JLabel("Ties: ");

        playerWinsTF = new JTextField("0");
        playerWinsTF.setColumns(10);
        playerWinsTF.setEditable(false);

        computerWinsTF = new JTextField("0");
        computerWinsTF.setColumns(10);
        computerWinsTF.setEditable(false);

        tiesTF = new JTextField("0");
        tiesTF.setColumns(10);
        tiesTF.setEditable(false);



        middlePanel.add(playerWinsLabel);
        middlePanel.add(playerWinsTF);

        middlePanel.add(computerWinsLabel);
        middlePanel.add(computerWinsTF);

        middlePanel.add(tiesLabel);
        middlePanel.add(tiesTF);
    }

    private void createBottomPanel() {
        bottomPanel = new JPanel();
        results = new JTextArea(30, 50);
        resultsScroller = new JScrollPane(results);

        bottomPanel.add(resultsScroller);
    }


    public void rockPaperScissors(String userDecision) {
        computerArray = new String[3];
        computerArray[0] = "Rock";
        computerArray[1] = "Paper";
        computerArray[2] = "Scissors";
        rand = new Random();
        userChoice = userDecision;
        computerChoice = computerArray[rand.nextInt(3)];

        if (userChoice == computerChoice) {
            ties += 1;
            tiesTF.setText(String.valueOf(ties));
            results.append("Tie - both gave " + userChoice + "\n");

        }
        if (userChoice == "Rock") {
            if (computerChoice == "Scissors") {
                userWins += 1;
                playerWinsTF.setText(String.valueOf(userWins));
                results.append("User's rock crushes computers scissors (Regular)\n");
            }
            if (computerChoice == "Paper") {
                computerWins += 1;
                computerWinsTF.setText(String.valueOf(computerWins));
                results.append("Computer's paper covers user's rock (Regular)\n");
            }
        }
        if (userChoice == "Paper") {
            if (computerChoice == "Scissors") {
                computerWins += 1;
                computerWinsTF.setText(String.valueOf(computerWins));
                results.append("Computer's scissors cut user's paper (Regular)\n");
            }
            if (computerChoice == "Rock") {
                userWins += 1;
                playerWinsTF.setText(String.valueOf(userWins));
                results.append("User's paper covers computer's rock (Regular)\n");
            }
        }
        if (userChoice == "Scissors") {
            if (computerChoice == "Paper") {
                userWins += 1;
                playerWinsTF.setText(String.valueOf(userWins));
                results.append("User's scissors cut computers paper (Regular)\n");
            }
            if (computerChoice == "Rock") {
                computerWins += 1;
                computerWinsTF.setText(String.valueOf(computerWins));
                results.append("Computer's rock crushes user's scissors (Regular)\n");
            }
        }

    }

    public void determineMove(String userChoice) {
        lastUsed = userChoice;

        algorithmDecider = new Random();
        percent = algorithmDecider.nextInt(100);
        if (percent <= 100 && percent > 77) {
            // Least Used
            System.out.println("Least Used");
            leastUsed(userChoice);
        }
        if (percent <= 77 && percent > 55) {
            // Most Used
            System.out.println("Most Used");
            mostUsed(userChoice);
        }
        if (percent <= 55 && percent > 32) {
            // Last Used
            System.out.println("Last Used");
            lastUsed(userChoice);
        }
        if (percent <= 32 && percent > 10) {
            // Random
            System.out.println("Random");
            rockPaperScissors(userChoice);
        }
        if (percent <= 10 && percent > 0) {
            // Cheat
            System.out.println("Cheat");
            cheat(userChoice);
        }


    }

    public void leastUsed(String userChoice) {
        if (amountPaperUsed < amountRockUsed && amountPaperUsed < amountScissorsUsed) {
            computerChoice = "Scissors";
            if (userChoice == computerChoice) {
                ties += 1;
                tiesTF.setText(String.valueOf(ties));
                results.append("Tie - both gave " + userChoice + " (Least Used)\n");

            }
            if (userChoice == "Rock") {
                if (computerChoice == "Scissors") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's rock crushes computers scissors (Least Used)\n");
                }
                if (computerChoice == "Paper") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's paper covers user's rock (Least Used)\n");
                }
            }
            if (userChoice == "Paper") {
                if (computerChoice == "Scissors") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's scissors cut user's paper (Least Used)\n");
                }
                if (computerChoice == "Rock") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's paper covers computer's rock (Least Used)\n");
                }
            }
            if (userChoice == "Scissors") {
                if (computerChoice == "Paper") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's scissors cut computers paper (Least Used)\n");
                }
                if (computerChoice == "Rock") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's rock crushes user's scissors (Least Used)\n");
                }
            }
        }
        if (amountScissorsUsed < amountRockUsed && amountScissorsUsed < amountPaperUsed) {
            computerChoice = "Rock";
            if (userChoice == computerChoice) {
                ties += 1;
                tiesTF.setText(String.valueOf(ties));
                results.append("Tie - both gave " + userChoice + " (Least Used)\n");

            }
            if (userChoice == "Rock") {
                if (computerChoice == "Scissors") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's rock crushes computers scissors (Least Used)\n");
                }
                if (computerChoice == "Paper") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's paper covers user's rock (Least Used)\n");
                }
            }
            if (userChoice == "Paper") {
                if (computerChoice == "Scissors") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's scissors cut user's paper (Least Used)\n");
                }
                if (computerChoice == "Rock") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's paper covers computer's rock (Least Used)\n");
                }
            }
            if (userChoice == "Scissors") {
                if (computerChoice == "Paper") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's scissors cut computers paper (Least Used)\n");
                }
                if (computerChoice == "Rock") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's rock crushes user's scissors (Least Used)\n");
                }
            }
        }

        if (amountRockUsed < amountScissorsUsed && amountRockUsed < amountPaperUsed) {
            computerChoice = "Paper";
            if (userChoice == computerChoice) {
                ties += 1;
                tiesTF.setText(String.valueOf(ties));
                results.append("Tie - both gave " + userChoice + " (Least Used)\n");

            }
            if (userChoice == "Rock") {
                if (computerChoice == "Scissors") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's rock crushes computers scissors (Least Used)\n");
                }
                if (computerChoice == "Paper") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's paper covers user's rock (Least Used)\n");
                }
            }
            if (userChoice == "Paper") {
                if (computerChoice == "Scissors") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's scissors cut user's paper (Least Used)\n");
                }
                if (computerChoice == "Rock") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's paper covers computer's rock (Least Used)\n");
                }
            }
            if (userChoice == "Scissors") {
                if (computerChoice == "Paper") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's scissors cut computers paper (Least Used)\n");
                }
                if (computerChoice == "Rock") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's rock crushes user's scissors (Least Used)\n");
                }
            }
        } else {
            computerChoice = "Paper";
            if (userChoice == computerChoice) {
                ties += 1;
                tiesTF.setText(String.valueOf(ties));
                results.append("Tie - both gave " + userChoice + " (Least Used)\n");

            }
            if (userChoice == "Rock") {
                if (computerChoice == "Scissors") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's rock crushes computers scissors (Least Used)\n");
                }
                if (computerChoice == "Paper") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's paper covers user's rock (Least Used)\n");
                }
            }
            if (userChoice == "Paper") {
                if (computerChoice == "Scissors") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's scissors cut user's paper (Least Used)\n");
                }
                if (computerChoice == "Rock") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's paper covers computer's rock (Least Used)\n");
                }
            }
            if (userChoice == "Scissors") {
                if (computerChoice == "Paper") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's scissors cut computers paper (Least Used)\n");
                }
                if (computerChoice == "Rock") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's rock crushes user's scissors (Least Used)\n");
                }
            }
        }
    }

    public void mostUsed(String userChoice) {
        if (amountPaperUsed > amountRockUsed && amountPaperUsed > amountScissorsUsed) {
            computerChoice = "Scissors";
            if (userChoice == computerChoice) {
                ties += 1;
                tiesTF.setText(String.valueOf(ties));
                results.append("Tie - both gave " + userChoice + " (Most Used)\n");

            }
            if (userChoice == "Rock") {
                if (computerChoice == "Scissors") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's rock crushes computers scissors (Most Used)\n");
                }
                if (computerChoice == "Paper") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's paper covers user's rock (Most Used)\n");
                }
            }
            if (userChoice == "Paper") {
                if (computerChoice == "Scissors") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's scissors cut user's paper (Most Used)\n");
                }
                if (computerChoice == "Rock") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's paper covers computer's rock (Most Used)\n");
                }
            }
            if (userChoice == "Scissors") {
                if (computerChoice == "Paper") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's scissors cut computers paper (Most Used)\n");
                }
                if (computerChoice == "Rock") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's rock crushes user's scissors (Most Used)\n");
                }
            }
        }
        if (amountScissorsUsed > amountRockUsed && amountScissorsUsed > amountPaperUsed) {
            computerChoice = "Rock";
            if (userChoice == computerChoice) {
                ties += 1;
                tiesTF.setText(String.valueOf(ties));
                results.append("Tie - both gave " + userChoice + " (Most Used)\n");

            }
            if (userChoice == "Rock") {
                if (computerChoice == "Scissors") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's rock crushes computers scissors (Most Used)\n");
                }
                if (computerChoice == "Paper") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's paper covers user's rock (Most Used)\n");
                }
            }
            if (userChoice == "Paper") {
                if (computerChoice == "Scissors") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's scissors cut user's paper (Most Used)\n");
                }
                if (computerChoice == "Rock") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's paper covers computer's rock (Most Used)\n");
                }
            }
            if (userChoice == "Scissors") {
                if (computerChoice == "Paper") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's scissors cut computers paper (Most Used)\n");
                }
                if (computerChoice == "Rock") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's rock crushes user's scissors (Most Used)\n");
                }
            }
        }
        if (amountRockUsed > amountScissorsUsed && amountRockUsed > amountPaperUsed) {
            computerChoice = "Paper";
            if (userChoice == computerChoice) {
                ties += 1;
                tiesTF.setText(String.valueOf(ties));
                results.append("Tie - both gave " + userChoice + " (Most Used)\n");

            }
            if (userChoice == "Rock") {
                if (computerChoice == "Scissors") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's rock crushes computers scissors (Most Used)\n");
                }
                if (computerChoice == "Paper") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's paper covers user's rock (Most Used)\n");
                }
            }
            if (userChoice == "Paper") {
                if (computerChoice == "Scissors") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's scissors cut user's paper (Most Used)\n");
                }
                if (computerChoice == "Rock") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's paper covers computer's rock (Most Used)\n");
                }
            }
            if (userChoice == "Scissors") {
                if (computerChoice == "Paper") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's scissors cut computers paper (Most Used)\n");
                }
                if (computerChoice == "Rock") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's rock crushes user's scissors (Most Used)\n");
                }
            }
        } else {
            computerChoice = "Paper";
            if (userChoice == computerChoice) {
                ties += 1;
                tiesTF.setText(String.valueOf(ties));
                results.append("Tie - both gave " + userChoice + " (Most Used)\n");

            }
            if (userChoice == "Rock") {
                if (computerChoice == "Scissors") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's rock crushes computers scissors (Most Used)\n");
                }
                if (computerChoice == "Paper") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's paper covers user's rock (Most Used)\n");
                }
            }
            if (userChoice == "Paper") {
                if (computerChoice == "Scissors") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's scissors cut user's paper (Most Used)\n");
                }
                if (computerChoice == "Rock") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's paper covers computer's rock (Most Used)\n");
                }
            }
            if (userChoice == "Scissors") {
                if (computerChoice == "Paper") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's scissors cut computers paper (Most Used)\n");
                }
                if (computerChoice == "Rock") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's rock crushes user's scissors (Most Used)\n");
                }
            }
        }
    }

    public void lastUsed(String userChoice) {
        if (lastUsed == "Rock") {
            computerChoice = "Paper";
            if (userChoice == computerChoice) {
                ties += 1;
                tiesTF.setText(String.valueOf(ties));
                results.append("Tie - both gave " + userChoice + "(Last Used)\n");

            }
            if (userChoice == "Rock") {
                if (computerChoice == "Scissors") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's rock crushes computers scissors (Last Used)\n");
                }
                if (computerChoice == "Paper") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's paper covers user's rock (Last Used)\n");
                }
            }
            if (userChoice == "Paper") {
                if (computerChoice == "Scissors") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's scissors cut user's paper (Last Used)\n");
                }
                if (computerChoice == "Rock") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's paper covers computer's rock (Last Used)\n");
                }
            }
            if (userChoice == "Scissors") {
                if (computerChoice == "Paper") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's scissors cut computers paper (Last Used)\n");
                }
                if (computerChoice == "Rock") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's rock crushes user's scissors (Last Used)\n");
                }
            }

        }
        if (lastUsed == "Paper") {
            computerChoice = "Scissors";
            if (userChoice == computerChoice) {
                ties += 1;
                tiesTF.setText(String.valueOf(ties));
                results.append("Tie - both gave " + userChoice + " (Last Used)\n");

            }
            if (userChoice == "Rock") {
                if (computerChoice == "Scissors") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's rock crushes computers scissors (Last Used)\n");
                }
                if (computerChoice == "Paper") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's paper covers user's rock (Last Used)\n");
                }
            }
            if (userChoice == "Paper") {
                if (computerChoice == "Scissors") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's scissors cut user's paper (Last Used)\n");
                }
                if (computerChoice == "Rock") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's paper covers computer's rock (Last Used)\n");
                }
            }
            if (userChoice == "Scissors") {
                if (computerChoice == "Paper") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's scissors cut computers paper (Last Used)\n");
                }
                if (computerChoice == "Rock") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's rock crushes user's scissors (Last Used)\n");
                }
            }
        }
        if (lastUsed == "Scissors") {
            computerChoice = "Rock";
            if (userChoice == computerChoice) {
                ties += 1;
                tiesTF.setText(String.valueOf(ties));
                results.append("Tie - both gave " + userChoice + " (Last Used)\n");

            }
            if (userChoice == "Rock") {
                if (computerChoice == "Scissors") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's rock crushes computers scissors (Last Used)\n");
                }
                if (computerChoice == "Paper") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's paper covers user's rock (Last Used)\n");
                }
            }
            if (userChoice == "Paper") {
                if (computerChoice == "Scissors") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's scissors cut user's paper (Last Used)\n");
                }
                if (computerChoice == "Rock") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's paper covers computer's rock (Last Used)\n");
                }
            }
            if (userChoice == "Scissors") {
                if (computerChoice == "Paper") {
                    userWins += 1;
                    playerWinsTF.setText(String.valueOf(userWins));
                    results.append("User's scissors cut computers paper (Last Used)\n");
                }
                if (computerChoice == "Rock") {
                    computerWins += 1;
                    computerWinsTF.setText(String.valueOf(computerWins));
                    results.append("Computer's rock crushes user's scissors (Last Used)\n");
                }
            }
        }
    }

    public void cheat(String userChoice) {
        if (userChoice == "Rock") {
            computerWins += 1;
            computerWinsTF.setText(String.valueOf(computerWins));
            results.append("Computer's paper covers user's rock by (Cheat)\n");
        }
        if (userChoice == "Scissors") {
            computerWins += 1;
            computerWinsTF.setText(String.valueOf(computerWins));
            results.append("Computer's rock crushes user's scissors by cheating (Cheat)\n");
        }
        if (userChoice == "Paper") {
            computerWins += 1;
            computerWinsTF.setText(String.valueOf(computerWins));
            results.append("Computer's scissors cut user's paper by cheating (Cheat)\n");
        }
    }

}
