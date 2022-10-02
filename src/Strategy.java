public interface Strategy {

    void determineMove(String userChoice);
    void leastUsed(String userChoice);
    void lastUsed(String userChoice);
    void mostUsed(String userChoice);
    void rockPaperScissors(String userChoice);
    void cheat(String userChoice);
}
