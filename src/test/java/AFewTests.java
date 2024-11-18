import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShapeClassifierTest {
    private ShapeClassifier classifier;

    @BeforeEach
    void setUp() {
        classifier = new ShapeClassifier();
    }

    @Test
    void testCorrectGuessEquilateralTriangle() {
        String input = "Equilateral,Large,Yes,100,100,100";
        assertEquals("Yes", classifier.evaluateGuess(input));
    }

    @Test
    void testCorrectGuessCircle() {
        String input = "Circle,Small,Yes,5,5";
        assertEquals("Yes", classifier.evaluateGuess(input));
    }

    @Test
    void testIncorrectShapeGuessRectangleAsSquare() {
        String input = "Square,Large,Yes,10,20,10,20";
        assertTrue(classifier.evaluateGuess(input).contains("Suggestion=Rectangle"));
    }

    @Test
    void testIncorrectSizeGuessLargeForSmall() {
        String input = "Circle,Large,Yes,3,3";
        assertTrue(classifier.evaluateGuess(input).contains("Wrong Size"));
    }

    @Test
    void testIncorrectEvenOddGuess() {
        String input = "Equilateral,Large,No,100,100,100";
        assertTrue(classifier.evaluateGuess(input).contains("Wrong Even/Odd"));
    }

    @Test
    void testAllGuessesIncorrect() {
        String input = "Scalene,Small,No,50,50,50";
        assertTrue(classifier.evaluateGuess(input).contains("No"));
    }

    @Test
    void testInvalidInputFormat() {
        String input = "Circle,Small";
        assertEquals("No", classifier.evaluateGuess(input));
    }

    @Test
    void testNegativeDimensions() {
        String input = "Circle,Small,Yes,-5,-5";
        assertTrue(classifier.evaluateGuess(input).contains("Suggestion=Circle"));
    }

    @Test
    void testBoundarySmallSize() {
        String input = "Circle,Small,Yes,5,5";
        assertEquals("Yes", classifier.evaluateGuess(input));
    }

    @Test
    void testBoundaryLargeSize() {
        String input = "Rectangle,Large,Yes,25,25,25,25";
        assertEquals("Yes", classifier.evaluateGuess(input));
    }
}
