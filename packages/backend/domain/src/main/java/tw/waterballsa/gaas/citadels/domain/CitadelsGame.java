package tw.waterballsa.gaas.citadels.domain;

import java.util.List;

public class CitadelsGame {
    private List<Player> players;
    public CitadelsGame(List<Player> list) {
        this.players = list;
    }

    public boolean removePlayer(Player player) {
        return this.players.remove(player);
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public Player getPlayerByName(String s) {
        for (Player player : this.players) {
            if (player.getName().equals(s)) {
                return player;
            }
        }
        return null;
    }
}
