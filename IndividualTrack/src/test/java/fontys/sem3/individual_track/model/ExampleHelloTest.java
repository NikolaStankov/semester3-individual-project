package fontys.sem3.individual_track.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExampleHelloTest {

    ExampleHello exampleHello;
    @BeforeEach
    void setUp() {
        this.exampleHello = new ExampleHello();
    }

    @Test
    void sayHelloTestShouldReturnHelloNikola() {
        assertEquals("Hello Nikola", this.exampleHello.SayHello());
    }
}