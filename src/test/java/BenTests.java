import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class BenTests {

    private ShapeClassifier shapeClassifier;

    @BeforeEach
    public void setUp() {
        shapeClassifier = new ShapeClassifier();
    }

    @Test
    public void testEquilateralTriangleValidGuess() {
        String result = shapeClassifier.evaluateGuess("Equilateral,Large,Yes,100,100,100");
        assertEquals("Yes", result, "Should return 'Yes' for correct guesses.");
    }

    @Test
    public void testEquilateralTriangleWrongSize() {
        String result = shapeClassifier.evaluateGuess("Equilateral,Small,Yes,50,50,50");
        assertTrue(result.contains("Wrong Size"), "Should indicate wrong size guess.");
    }

    @Test
    public void testRectangleValidGuess() {
        String result = shapeClassifier.evaluateGuess("Rectangle,Large,No,40,20,40,20");
        assertEquals("Yes", result, "Should return 'Yes' for correct rectangle guess.");
    }

    @Test
    public void testRectangleSuggestion() {
        String result = shapeClassifier.evaluateGuess("Square,Large,No,40,20,40,20");
        assertTrue(result.contains("Suggestion=Rectangle"), "Should suggest 'Rectangle' for incorrect square guess.");
    }

    @Test
    public void testCircleValidGuess() {
        String result = shapeClassifier.evaluateGuess("Circle,Large,No,50,50");
        assertEquals("Yes", result, "Should return 'Yes' for correct circle guess.");
    }

    @Test
    public void testInvalidInputHandling() {
        String result = shapeClassifier.evaluateGuess("Rectangle,Small,No,0,0");
        assertEquals("No", result, "Should return 'No' for invalid input.");
    }

    @Test
    public void testExceedingBadGuessLimit() {
        shapeClassifier.evaluateGuess("Rectangle,Large,No,10,10,10,10"); // 1st wrong guess
        shapeClassifier.evaluateGuess("Rectangle,Large,Yes,10,20,10,20"); // 2nd wrong guess
        String result = shapeClassifier.evaluateGuess("Square,Small,No,10,10,10,10"); // 3rd wrong guess
        assertEquals("ERROR: Bad guess limit Exceeded", result, "Should exit after 3rd bad guess.");
    }

    @Test
    public void testScaleneTriangleValidGuess() {
        String result = shapeClassifier.evaluateGuess("Scalene,Large,No,50,60,70");
        assertEquals("Yes", result, "Should return 'Yes' for correct scalene triangle guess.");
    }

    @Test
    public void testWrongEvenOddGuess() {
        String result = shapeClassifier.evaluateGuess("Circle,Large,Yes,50,50");
        assertTrue(result.contains("Wrong Even/Odd"), "Should indicate wrong even/odd guess.");
    }

    @Test
    public void testEllipseSuggestion() {
        String result = shapeClassifier.evaluateGuess("Circle,Large,Yes,30,20");
        assertTrue(result.contains("Suggestion=Ellipse"), "Should suggest 'Ellipse' for incorrect circle guess.");
    }

    @Test
    public void testSingleLineValidGuess() {
        String result = shapeClassifier.evaluateGuess("Line,Small,No,5");
        assertEquals("Yes", result, "Should return 'Yes' for correct single line guess.");
    }

    @Test
    public void testSingleLineInvalidGuess() {
        String result = shapeClassifier.evaluateGuess("Circle,Small,No,5");
        assertTrue(result.contains("Suggestion=Line"), "Should suggest 'Line' for incorrect shape guess.");
    }

    @Test
    public void testNegativeSideLength() {
        String result = shapeClassifier.evaluateGuess("Scalene,Small,Yes,-10,-20,-30");
        assertEquals("No", result, "Should return 'No' for input with negative side lengths.");
    }

    @Test
    public void testZeroLengthInput() {
        String result = shapeClassifier.evaluateGuess("Line,Small,Yes,0");
        assertEquals("No", result, "Should return 'No' for input with zero length.");
    }

    @Test
    public void testMaxBoundaryLength() {
        String result = shapeClassifier.evaluateGuess("Square,Large,Yes,4095,4095,4095,4095");
        assertEquals("Yes", result, "Should handle input at the maximum boundary value.");
    }

    @Test
    public void testExtremeLargeValuesHandling() {
        String result = shapeClassifier.evaluateGuess("Rectangle,Large,Yes,10000,10000,10000,10000");
        assertEquals("No", result, "Should handle input values that exceed typical bounds.");
    }

    @Test
    public void testInvalidInputFormat() {
        String result = shapeClassifier.evaluateGuess("Circle,Large,Yes");
        assertEquals("No", result, "Should return 'No' for incorrectly formatted input.");
    }

    @Test
    public void testRectangleWithDifferentSides() {
        String result = shapeClassifier.evaluateGuess("Rectangle,Large,Yes,40,30,40,30");
        assertEquals("Yes", result, "Should return 'Yes' for a valid rectangle with different side pairs.");
    }

    @Test
    public void testInvalidShapeGuess() {
        String result = shapeClassifier.evaluateGuess("Hexagon,Large,Yes,100,100,100,100,100,100");
        assertEquals("No", result, "Should return 'No' for an unsupported shape guess.");
    }

    @Test
    public void testEvenOddGuessTrailingWhitespace() {
        String result = shapeClassifier.evaluateGuess("Circle,Large,Yes ,100,100");
        assertTrue(result.contains("Wrong Even/Odd"), "Should handle and indicate incorrect even/odd guess with trailing whitespace.");
    }

    @Test
    public void testScaleneTriangleSuggestion() {
        String result = shapeClassifier.evaluateGuess("Isosceles,Large,Yes,50,60,70");
        assertTrue(result.contains("Suggestion=Scalene"), "Should suggest 'Scalene' for incorrect triangle guess.");
    }

    @Test
    public void testMultipleBadGuesses() {
        shapeClassifier.evaluateGuess("Circle,Small,Yes,20,20");
        shapeClassifier.evaluateGuess("Rectangle,Large,No,50,50,50,50");
        String result = shapeClassifier.evaluateGuess("Line,Small,Yes,5");
        assertEquals("Yes", result, "Should not trigger bad guess exit if the third guess is correct.");
    }

    @Test
    public void testValidIsoscelesTriangleWithLargeSize() {
        String result = shapeClassifier.evaluateGuess("Isosceles,Large,No,100,100,50");
        assertEquals("Yes", result, "Should return 'Yes' for a correct isosceles triangle guess with a large size.");
    }

    @Test
    public void testIncorrectShapeAndSizeGuess() {
        String result = shapeClassifier.evaluateGuess("Square,Small,Yes,100,100,100,100");
        assertTrue(result.contains("No: Suggestion=Rectangle"), "Should suggest 'Rectangle' for incorrect square guess with a wrong size.");
    }

    @Test
    public void testAmbiguousShapeParameters() {
        // This test checks for a scenario where parameters could lead to multiple valid classifications
        String result = shapeClassifier.evaluateGuess("Scalene,Large,Yes,50,50,50");
        assertTrue(result.contains("Suggestion=Equilateral"), "Should suggest 'Equilateral' when the parameters fit an equilateral triangle.");
    }

    @Test
    public void testMultipleInvalidInputs() {
        shapeClassifier.evaluateGuess("Circle,Small,No,10,10"); // Incorrect
        shapeClassifier.evaluateGuess("Rectangle,Large,No,20,20,20,20"); // Incorrect
        String result = shapeClassifier.evaluateGuess("Isosceles,Small,Yes,10,15,15"); // Incorrect, should trigger bad guess limit
        assertEquals("ERROR: Bad guess limit Exceeded", result, "Should exit with an error after three consecutive bad guesses.");
    }

    @Test
    public void testComplexShapeClassificationWithSuggestion() {
        String result = shapeClassifier.evaluateGuess("Rectangle,Large,Yes,40,20,40,40");
        assertTrue(result.contains("Suggestion=Square"), "Should suggest 'Square' when a valid guess for 'Rectangle' is close to 'Square'.");
    }

    @Test
    public void testHighValueScaleneTriangle() {
        String result = shapeClassifier.evaluateGuess("Scalene,Large,No,3000,2000,1500");
        assertEquals("Yes", result, "Should correctly classify a large scalene triangle.");
    }

    @Test
    public void testExtremeInvalidParameterCount() {
        String result = shapeClassifier.evaluateGuess("Rectangle,Small,No,10,20");
        assertEquals("No", result, "Should handle invalid parameter counts gracefully and return 'No'.");
    }

    @Test
    public void testIncorrectEvenOddWithComplexPerimeterCalculation() {
        String result = shapeClassifier.evaluateGuess("Ellipse,Large,Yes,50,30");
        assertTrue(result.contains("Wrong Even/Odd"), "Should return 'Wrong Even/Odd' when even/odd guess is incorrect.");
    }

    @Test
    public void testEdgeCaseZeroPerimeterHandling() {
        String result = shapeClassifier.evaluateGuess("Line,Small,Yes,0");
        assertEquals("No", result, "Should return 'No' for zero perimeter as it is invalid.");
    }

    @Test
    public void testVeryLargeInputArray() {
        String result = shapeClassifier.evaluateGuess("Scalene,Large,No,1000,1000,1000,1000,1000,1000,1000");
        assertEquals("No", result, "Should handle inputs with more parameters than supported and return 'No'.");
    }

    /**
     * This is an example test 
     */
    @DisplayName("Example Test")
    @Test
    public void example() {
        ShapeClassifier s = new ShapeClassifier();
        String answer = s.evaluateGuess("Equilateral,Large,Yes,100,100,100");
        assertNotEquals("Yes", answer);
    }
}
