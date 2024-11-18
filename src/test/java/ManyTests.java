import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * BRUTE FORCE GEN-AI ABUSE STRATEGY
 * 
 * Write an extensive set of java test cases for the following program. Make
 * sure you read the comments and adhere to the spec.
 * 
 * These are not creative or robust enough
 * 
 * Generate a class with 100 test cases. They can be anything you deem
 * appropriate
 */
public class ManyTests {

    private ShapeClassifier classifier = new ShapeClassifier();

    // --- Valid Input Tests ---
    @Test
    void testValidEquilateralTriangle() {
        assertEquals("Yes", classifier.evaluateGuess("Equilateral,Small,Yes,10,10,10"));
    }

    @Test
    void testValidIsoscelesTriangle() {
        assertEquals("Yes", classifier.evaluateGuess("Isosceles,Small,Yes,10,10,15"));
    }

    @Test
    void testValidScaleneTriangle() {
        assertEquals("Yes", classifier.evaluateGuess("Scalene,Large,No,8,6,5"));
    }

    @Test
    void testValidRectangle() {
        assertEquals("Yes", classifier.evaluateGuess("Rectangle,Large,Yes,12,8,12,8"));
    }

    @Test
    void testValidSquare() {
        assertEquals("Yes", classifier.evaluateGuess("Square,Small,Yes,5,5,5,5"));
    }

    @Test
    void testValidCircle() {
        assertEquals("Yes", classifier.evaluateGuess("Circle,Small,Yes,7"));
    }

    @Test
    void testValidEllipse() {
        assertEquals("Yes", classifier.evaluateGuess("Ellipse,Large,No,30,15"));
    }

    @Test
    void testValidRightTriangle() {
        assertEquals("Yes", classifier.evaluateGuess("Right Triangle,Small,Yes,3,4,5"));
    }

    @Test
    void testValidLineSegment() {
        assertEquals("Yes", classifier.evaluateGuess("Line,Small,Yes,0"));
    }

    @Test
    void testValidPolygon() {
        assertEquals("Yes", classifier.evaluateGuess("Polygon,Large,No,10,12,15,18,20"));
    }

    // --- Invalid Guesses ---
    @Test
    void testInvalidShapeName() {
        assertEquals("No: Suggestion=Rectangle", classifier.evaluateGuess("Quadrilateral,Large,Yes,10,10,10,10"));
    }

    @Test
    void testIncorrectSizeClassification() {
        assertEquals("Wrong Size", classifier.evaluateGuess("Square,Large,Yes,5,5,5,5"));
    }

    @Test
    void testIncorrectEvenOddGuess() {
        assertEquals("Wrong Even/Odd", classifier.evaluateGuess("Circle,Small,No,7"));
    }

    @Test
    void testMultipleIncorrectGuesses() {
        assertEquals("Wrong Size,Wrong Even/Odd", classifier.evaluateGuess("Triangle,Large,Yes,3,4,5"));
    }

    // --- Edge Cases ---
    @Test
    void testNegativeSideLengths() {
        assertEquals("No", classifier.evaluateGuess("Triangle,Small,Yes,-3,-4,-5"));
    }

    @Test
    void testZeroSideLengths() {
        assertEquals("No", classifier.evaluateGuess("Triangle,Small,Yes,0,0,0"));
    }

    @Test
    void testBoundarySmallSize() {
        assertEquals("Yes", classifier.evaluateGuess("Square,Small,Yes,10,10,10,10"));
    }

    @Test
    void testBoundaryLargeSize() {
        assertEquals("Yes", classifier.evaluateGuess("Rectangle,Large,No,20,15,20,15"));
    }

    @Test
    void testExactCircleBoundary() {
        assertEquals("Yes", classifier.evaluateGuess("Circle,Large,Yes,16"));
    }

    // --- Invalid Inputs ---
    @Test
    void testTooFewParameters() {
        assertEquals("No", classifier.evaluateGuess("Rectangle,Small,Yes,10"));
    }

    @Test
    void testTooManyParameters() {
        assertEquals("No", classifier.evaluateGuess("Circle,Small,Yes,10,5"));
    }

    @Test
    void testInvalidParameterType() {
        assertEquals("No", classifier.evaluateGuess("Triangle,Small,Yes,10,abc,5"));
    }

    @Test
    void testNullInput() {
        assertThrows(NullPointerException.class, () -> classifier.evaluateGuess(null));
    }

    @Test
    void testEmptyInput() {
        assertEquals("No", classifier.evaluateGuess(""));
    }

    // --- Specific Shape Characteristics ---
    @Test
    void testInvalidTriangleSum() {
        assertEquals("No", classifier.evaluateGuess("Scalene,Small,Yes,1,2,10"));
    }

    @Test
    void testValidTriangleSum() {
        assertEquals("Yes", classifier.evaluateGuess("Scalene,Small,Yes,7,8,10"));
    }

    @Test
    void testInvalidRectangleSides() {
        assertEquals("No", classifier.evaluateGuess("Rectangle,Large,Yes,10,15,20,25"));
    }

    @Test
    void testValidEllipseAxis() {
        assertEquals("Yes", classifier.evaluateGuess("Ellipse,Small,Yes,5,3"));
    }

    @Test
    void testInvalidEllipseAxis() {
        assertEquals("No", classifier.evaluateGuess("Ellipse,Small,Yes,5,0"));
    }

    // --- Random Edge Case Fillers ---
    @Test
    void testLargeSquare() {
        assertEquals("Yes", classifier.evaluateGuess("Square,Large,Yes,100,100,100,100"));
    }

    @Test
    void testSmallCircleRadiusBoundary() {
        assertEquals("Yes", classifier.evaluateGuess("Circle,Small,Yes,5"));
    }

    @Test
    void testIncorrectClassification() {
        assertEquals("No: Suggestion=Circle", classifier.evaluateGuess("Ellipse,Small,Yes,10"));
    }

    @Test
    void testMultipleErrorsShapeGuess() {
        assertEquals("No: Suggestion=Square", classifier.evaluateGuess("Triangle,Small,No,3,3,3"));
    }

    @Test
    void testMultipleErrorsSizeGuess() {
        assertEquals("Wrong Size,Wrong Even/Odd", classifier.evaluateGuess("Square,Small,No,20,20,20,20"));
    }

    @Test
    void testPerfectIsoscelesTriangle() {
        assertEquals("Yes", classifier.evaluateGuess("Isosceles,Small,Yes,5,5,8"));
    }

    @Test
    void testValidRightIsoscelesTriangle() {
        assertEquals("Yes", classifier.evaluateGuess("Right Triangle,Small,Yes,6,6,8.49"));
    }

    @Test
    void testValidEquilateralBoundary() {
        assertEquals("Yes", classifier.evaluateGuess("Equilateral,Small,Yes,10,10,10"));
    }

    @Test
    void testValidIsoscelesBoundary() {
        assertEquals("Yes", classifier.evaluateGuess("Isosceles,Large,No,50,50,70"));
    }

    @Test
    void testValidLargeCircle() {
        assertEquals("Yes", classifier.evaluateGuess("Circle,Large,No,20"));
    }

    @Test
    void testValidEllipseWithLargeAxes() {
        assertEquals("Yes", classifier.evaluateGuess("Ellipse,Large,No,40,20"));
    }

    @Test
    void testValidParallelogram() {
        assertEquals("Yes", classifier.evaluateGuess("Parallelogram,Small,Yes,15,25,15,25"));
    }

    @Test
    void testValidRegularPentagon() {
        assertEquals("Yes", classifier.evaluateGuess("Pentagon,Large,Yes,12,12,12,12,12"));
    }

    @Test
    void testValidRegularHexagon() {
        assertEquals("Yes", classifier.evaluateGuess("Hexagon,Small,No,8,8,8,8,8,8"));
    }

    @Test
    void testValidIrregularPolygon() {
        assertEquals("Yes", classifier.evaluateGuess("Polygon,Large,No,10,12,15,18,20"));
    }

    @Test
    void testValidLineWithZeroLength() {
        assertEquals("Yes", classifier.evaluateGuess("Line,Small,Yes,0"));
    }

    // --- Invalid Input Tests ---
    @Test
    void testInvalidNegativeSideLength() {
        assertEquals("No", classifier.evaluateGuess("Triangle,Small,Yes,-5,8,10"));
    }

    @Test
    void testInvalidExtraParameters() {
        assertEquals("No", classifier.evaluateGuess("Circle,Small,Yes,7,3,4"));
    }

    @Test
    void testInvalidMissingParameters() {
        assertEquals("No", classifier.evaluateGuess("Rectangle,Large,Yes,10"));
    }

    @Test
    void testInvalidParameterOrder() {
        assertEquals("No", classifier.evaluateGuess("Rectangle,Small,Yes,10,10,10"));
    }

    @Test
    void testInvalidRectangleOppositeSidesMismatch() {
        assertEquals("No", classifier.evaluateGuess("Rectangle,Large,No,10,15,20,25"));
    }

    @Test
    void testInvalidTriangleInequality() {
        assertEquals("No", classifier.evaluateGuess("Triangle,Small,Yes,5,1,10"));
    }

    @Test
    void testInvalidCircleRadiusZero() {
        assertEquals("No", classifier.evaluateGuess("Circle,Small,Yes,0"));
    }

    @Test
    void testInvalidZeroSidesForPolygon() {
        assertEquals("No", classifier.evaluateGuess("Polygon,Small,Yes,0,0,0,0,0"));
    }

    @Test
    void testInvalidRightTriangleWithWrongHypotenuse() {
        assertEquals("No", classifier.evaluateGuess("Right Triangle,Small,Yes,3,4,6"));
    }

    // --- Edge Cases ---
    @Test
    void testEdgeCaseSquareBoundary() {
        assertEquals("Yes", classifier.evaluateGuess("Square,Small,Yes,10,10,10,10"));
    }

    @Test
    void testEdgeCaseRectangleBoundary() {
        assertEquals("Yes", classifier.evaluateGuess("Rectangle,Large,No,15,25,15,25"));
    }

    @Test
    void testEdgeCaseCircleBoundary() {
        assertEquals("Yes", classifier.evaluateGuess("Circle,Large,Yes,20"));
    }

    @Test
    void testEdgeCaseLargePolygon() {
        assertEquals("Yes", classifier.evaluateGuess("Polygon,Large,No,50,60,70,80,90"));
    }

    @Test
    void testEdgeCaseEquilateralTriangleBoundary() {
        assertEquals("Yes", classifier.evaluateGuess("Equilateral,Small,Yes,10,10,10"));
    }

    @Test
    void testEdgeCaseEllipseBoundary() {
        assertEquals("Yes", classifier.evaluateGuess("Ellipse,Large,No,40,20"));
    }

    @Test
    void testEdgeCaseZeroWidthRectangle() {
        assertEquals("No", classifier.evaluateGuess("Rectangle,Small,Yes,10,0,10,0"));
    }

    @Test
    void testEdgeCaseSinglePoint() {
        assertEquals("No", classifier.evaluateGuess("Line,Small,Yes,0"));
    }

    @Test
    void testEdgeCaseRightTriangleBoundary() {
        assertEquals("Yes", classifier.evaluateGuess("Right Triangle,Small,Yes,6,8,10"));
    }

    @Test
    void testEdgeCaseNegativeDimensions() {
        assertEquals("No", classifier.evaluateGuess("Ellipse,Large,No,-20,30"));
    }

    // --- Consistency Tests ---
    @Test
    void testConsistencyMultipleValidInputs() {
        assertEquals("Yes", classifier.evaluateGuess("Square,Small,Yes,10,10,10,10"));
        assertEquals("Yes", classifier.evaluateGuess("Circle,Small,Yes,7"));
        assertEquals("Yes", classifier.evaluateGuess("Rectangle,Large,Yes,15,20,15,20"));
    }

    @Test
    void testConsistencyMultipleInvalidInputs() {
        assertEquals("No", classifier.evaluateGuess("Triangle,Small,Yes,3,3,10"));
        assertEquals("No", classifier.evaluateGuess("Circle,Small,Yes,0"));
        assertEquals("No", classifier.evaluateGuess("Polygon,Large,No,0,0,0"));
    }

    @Test
    void testConsistencySameShapeMultipleTimes() {
        for (int i = 0; i < 10; i++) {
            assertEquals("Yes", classifier.evaluateGuess("Square,Small,Yes,5,5,5,5"));
        }
    }

    @Test
    void testConsistencyRandomValidInputs() {
        assertEquals("Yes", classifier.evaluateGuess("Right Triangle,Small,Yes,6,8,10"));
        assertEquals("Yes", classifier.evaluateGuess("Circle,Small,Yes,5"));
        assertEquals("Yes", classifier.evaluateGuess("Rectangle,Large,No,20,15,20,15"));
    }

    @Test
    void testConsistencyRandomInvalidInputs() {
        assertEquals("No", classifier.evaluateGuess("Square,Small,Yes,10,10,10,0"));
        assertEquals("No", classifier.evaluateGuess("Line,Large,No,10"));
        assertEquals("No", classifier.evaluateGuess("Triangle,Small,No,3,3,100"));
    }

    // --- Fuzz Tests ---
    @Test
    void testFuzzRandomLargeInput() {
        assertEquals("No", classifier.evaluateGuess("Polygon,Large,No,1000,2000,3000,4000,5000,6000,7000,8000"));
    }

    @Test
    void testFuzzRandomSmallInput() {
        assertEquals("No", classifier.evaluateGuess("Circle,Small,Yes,-5"));
    }

    @Test
    void testFuzzExtremeBoundaryConditions() {
        assertEquals("No", classifier.evaluateGuess("Square,Small,Yes,0,0,0,0"));
    }

    @Test
    void testFuzzInvalidShapeName() {
        assertEquals("No", classifier.evaluateGuess("Squircle,Small,Yes,10,10"));
    }

    @Test
    void testFuzzEmptyInputString() {
        assertEquals("No", classifier.evaluateGuess(""));
    }
}
