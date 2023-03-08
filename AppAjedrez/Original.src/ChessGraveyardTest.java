import static org.junit.jupiter.api.Assertions.*;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.Test;

class ChessGraveyardTest {

    @Test
    void testAddPiece() {
        ChessGraveyard graveyard = new ChessGraveyard("Test Graveyard");
        ChessGamePiece piece = new Pawn(null, 0, 0, ChessGamePiece.WHITE);
        graveyard.addPiece(piece);
        assertEquals(1, graveyard.getComponentCount());
        
    }

    @Test
    void testClearGraveyard() {
        ChessGraveyard graveyard = new ChessGraveyard("Test Graveyard");
        ChessGamePiece piece = new Pawn(null, 0, 0, ChessGamePiece.WHITE);
        graveyard.addPiece(piece);
        graveyard.clearGraveyard();
        assertEquals(1, graveyard.getComponentCount());
     
    }

}
