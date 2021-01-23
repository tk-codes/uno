package domain.game;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGameBuilder {

    @Test
    public void WhenCreated_ShouldXXX() {
        var game = new GameBuilder().build();

        var random = new Random();
        for(var i = 0; i < 10; i++) {
            System.out.println(random.nextInt(5));
        }
        assertEquals("test", "test2");
    }
}
