import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class ChessGameBoard extends JPanel{
    private BoardSquare[][] chessCells;
    private transient BoardListener   listener;
 
    public BoardSquare[][] getCells(){
        return chessCells;
    }

    private boolean validateCoordinates( int row, int col ){
        return chessCells.length > 0 && chessCells[0].length > 0 &&
            row < chessCells.length && col < chessCells[0].length
            && row >= 0 && col >= 0;
    }

    public BoardSquare getCell( int row, int col ){
        if ( validateCoordinates( row, col ) ){
            return chessCells[row][col];
        }
        return null;
    }

    public void clearCell(int row, int col){
        if ( validateCoordinates( row, col ) ){
            chessCells[row][col].clearSquare();
        }
        else
        {
            throw new IllegalStateException( "Row " + row + " and column" +
            		" " + col + " are invalid, or the board has not been" +
            				"initialized. This square cannot be cleared." );
        }
    }
    // ----------------------------------------------------------

    public ArrayList<ChessGamePiece> getAllWhitePieces(){
        ArrayList<ChessGamePiece> whitePieces = new ArrayList<ChessGamePiece>();
        for ( int i = 0; i < 8; i++ ){
            for ( int j = 0; j < 8; j++ ){
                if ( chessCells[i][j].getPieceOnSquare() != null
                    && chessCells[i][j].getPieceOnSquare().getColorOfPiece() ==
                        ChessGamePiece.WHITE ){
                    whitePieces.add( chessCells[i][j].getPieceOnSquare() );
                }
            }
        }
        return whitePieces;
    }
    // ----------------------------------------------------------

    public ArrayList<ChessGamePiece> getAllBlackPieces(){
        ArrayList<ChessGamePiece> blackPieces = new ArrayList<ChessGamePiece>();
        for ( int i = 0; i < 8; i++ ){
            for ( int j = 0; j < 8; j++ ){
                if ( chessCells[i][j].getPieceOnSquare() != null
                    && chessCells[i][j].getPieceOnSquare().getColorOfPiece() ==
                        ChessGamePiece.BLACK ){
                    blackPieces.add( chessCells[i][j].getPieceOnSquare() );
                }
            }
        }
        return blackPieces;
    }
    // ----------------------------------------------------------
 
    public ChessGameBoard(){
        this.setLayout( new GridLayout( 8, 8, 1, 1 ) );
        listener = new BoardListener();
        chessCells = new BoardSquare[8][8];
        initializeBoard();
    }
    // ----------------------------------------------------------

    public void resetBoard ( boolean addAfterReset ){
        chessCells = new BoardSquare[8][8];
        this.removeAll();
        if ( getParent() instanceof ChessPanel ){
            ( (ChessPanel)getParent() ).getGraveyard( 1 ).clearGraveyard();
            ( (ChessPanel)getParent() ).getGraveyard( 2 ).clearGraveyard();
            ( (ChessPanel)getParent() ).getGameLog().clearLog();
        }
        for ( int i = 0; i < chessCells.length; i++ ){
            for ( int j = 0; j < chessCells[0].length; j++ ){
                chessCells[i][j] = new BoardSquare( i, j, null );
                if ( ( i + j ) % 2 == 0 ){
                    chessCells[i][j].setBackground( Color.WHITE );
                }
                else
                {
                    chessCells[i][j].setBackground( Color.BLACK );
                }
                if ( addAfterReset ){
                    chessCells[i][j].addMouseListener( listener );
                    this.add( chessCells[i][j] );
                }
            }
        }
        repaint();

    }

    public void initializeBoard() {
        resetBoard(false);
        initializePiecesInFirstAndLastRows();
        initializePawnsInSecondAndSeventhRows();
    }

    private void initializePiecesInFirstAndLastRows() {
        initializePiece(0, 0, new Rook(this, 0, 0, ChessGamePiece.WHITE));
        initializePiece(0, 1, new Knight(this, 0, 1, ChessGamePiece.WHITE));
        initializePiece(0, 2, new Bishop(this, 0, 2, ChessGamePiece.WHITE));
        initializePiece(0, 3, new Queen(this, 0, 3, ChessGamePiece.WHITE));
        initializePiece(0, 4, new King(this, 0, 4, ChessGamePiece.WHITE));
        initializePiece(0, 5, new Bishop(this, 0, 5, ChessGamePiece.WHITE));
        initializePiece(0, 6, new Knight(this, 0, 6, ChessGamePiece.WHITE));
        initializePiece(0, 7, new Rook(this, 0, 7, ChessGamePiece.WHITE));

        initializePiece(7, 0, new Rook(this, 7, 0, ChessGamePiece.BLACK));
        initializePiece(7, 1, new Knight(this, 7, 1, ChessGamePiece.BLACK));
        initializePiece(7, 2, new Bishop(this, 7, 2, ChessGamePiece.BLACK));
        initializePiece(7, 3, new Queen(this, 7, 3, ChessGamePiece.BLACK));
        initializePiece(7, 4, new King(this, 7, 4, ChessGamePiece.BLACK));
        initializePiece(7, 5, new Bishop(this, 7, 5, ChessGamePiece.BLACK));
        initializePiece(7, 6, new Knight(this, 7, 6, ChessGamePiece.BLACK));
        initializePiece(7, 7, new Rook(this, 7, 7, ChessGamePiece.BLACK));
    }

    private void initializePawnsInSecondAndSeventhRows() {
        for (int i = 0; i < chessCells.length; i++) {
            initializePiece(1, i, new Pawn(this, 1, i, ChessGamePiece.WHITE));
            initializePiece(6, i, new Pawn(this, 6, i, ChessGamePiece.BLACK));
        }
    }

    private void initializePiece(int row, int col, ChessGamePiece piece) {
        chessCells[row][col] = new BoardSquare(row, col, piece);
        chessCells[row][col].setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
        chessCells[row][col].addMouseListener(listener);
        add(chessCells[row][col]);
    }
    // ----------------------------------------------------------

    public void clearColorsOnBoard(){
        for ( int i = 0; i < chessCells.length; i++ ){
            for ( int j = 0; j < chessCells[0].length; j++ ){
                if ( ( i + j ) % 2 == 0 ){
                    chessCells[i][j].setBackground( Color.WHITE );
                }
                else
                {
                    chessCells[i][j].setBackground( Color.BLACK );
                }
            }
        }
    }

    private class BoardListener
        implements MouseListener
    {

        public void mouseClicked( MouseEvent e ){
            if ( e.getButton() == MouseEvent.BUTTON1 &&
                getParent() instanceof ChessPanel ){
                ( (ChessPanel)getParent() ).getGameEngine()
                    .determineActionFromSquareClick( e );
            }
        }

        public void mouseEntered( MouseEvent e ){ /* not used */
        }

        public void mouseExited( MouseEvent e ){ /* not used */
        }

        public void mousePressed( MouseEvent e ){ /* not used */
        }

        public void mouseReleased( MouseEvent e ){ /* not used */
        }
    }
}
