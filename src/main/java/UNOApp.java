import domain.game.GameBuilder;
import ui.AppFrame;

import javax.swing.*;
import java.util.stream.Collectors;

public class UNOApp {
    public static void main(String[] args) {
        var game = new GameBuilder()
            .withPlayer("Player 1")
            .withPlayer("Player 2")
            .withPlayer("Player 3")
            .build();

        game.getPlayers().forEach(p -> System.out.println("Player " + p.getName() + " - " + p.getTotalCards()));

        SwingUtilities.invokeLater(() -> {
            var frame = new AppFrame(game.getPlayers().collect(Collectors.toList()));
        });
    }
}
