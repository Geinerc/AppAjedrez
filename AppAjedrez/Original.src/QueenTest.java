import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.junit.jupiter.api.Test;

class QueenTest {

	@Test
	void testCalculatePossibleMoves() {
		// Crear el tablero y la reina
		ChessGameBoard board = new ChessGameBoard();
		Queen queen = new Queen(board, 4, 4, ChessGamePiece.WHITE);
		
		// Obtener los movimientos posibles de la reina
		ArrayList<String> moves = queen.calculatePossibleMoves(board);
		
		// Comprobar que la lista de movimientos no es nula ni está vacía
		assertNotNull(moves);
		assertTrue(moves.size() > 0);
		
		// Comprobar que la lista contiene algunos movimientos esperados
		assertTrue(moves.contains("d1"));
		assertTrue(moves.contains("h1"));
		assertTrue(moves.contains("a4"));
		assertTrue(moves.contains("e4"));
		assertTrue(moves.contains("a8"));
		assertTrue(moves.contains("e8"));
		assertTrue(moves.contains("h5"));
		assertTrue(moves.contains("d5"));
	}
	
	@Test
	void testCreateImageByPieceType() {
		// Crear la reina blanca y la reina negra
		Queen whiteQueen = new Queen(null, 0, 0, ChessGamePiece.WHITE);
		Queen blackQueen = new Queen(null, 0, 0, ChessGamePiece.BLACK);
		
		// Obtener las imágenes correspondientes a cada reina
		ImageIcon whiteImage = whiteQueen.createImageByPieceType();
		ImageIcon blackImage = blackQueen.createImageByPieceType();
		
		// Comprobar que las imágenes no son nulas
		assertNotNull(whiteImage);
		assertNotNull(blackImage);
		
		// Comprobar que las imágenes corresponden a las reinas blancas y negras
		assertEquals("WhiteQueen.gif", whiteImage.getDescription());
		assertEquals("BlackQueen.gif", blackImage.getDescription());
	}
	
}
