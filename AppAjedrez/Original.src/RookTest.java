import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class RookTest {
    ChessGameBoard board;
    Rook rook;
    @BeforeEach
    void setUp() {
        board = new ChessGameBoard();
        rook = new Rook(board, 3, 3, ChessGamePiece.WHITE);
    }
    @Test
    void testCalculatePossibleMoves() {
        ArrayList<String> expectedMoves = new ArrayList<String>();
        expectedMoves.add("d1");
        expectedMoves.add("d2");
        expectedMoves.add("d4");
        expectedMoves.add("d5");
        expectedMoves.add("a3");
        expectedMoves.add("b3");
        expectedMoves.add("c3");
        expectedMoves.add("e3");
        expectedMoves.add("f3");
        expectedMoves.add("g3");
        expectedMoves.add("h3");
        assertEquals(expectedMoves, rook.calculatePossibleMoves(board));
    }
    @Test
    void testCreateImageByPieceType() {
        assertNotNull(rook.createImageByPieceType());
    }
}
