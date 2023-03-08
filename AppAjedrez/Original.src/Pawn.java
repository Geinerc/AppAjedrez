import javax.swing.ImageIcon;
import java.util.ArrayList;
// -------------------------------------------------------------------------
/**
 * Represents a Pawn game piece. Unique in that it can move two locations on its
 * first turn and therefore requires a new 'notMoved' variable to keep track of
 * its turns.
 *
 * @author Ben Katz (bakatz)
 * @author Myles David II (davidmm2)
 * @author Danielle Bushrow (dbushrow)
 * @version 2010.11.17
 */
public class Pawn
    extends ChessGamePiece{
    private boolean notMoved;
    // ----------------------------------------------------------
    /**
     * Create a new Pawn object.
     *
     * @param board
     *            the board to create the pawn on
     * @param row
     *            row of the pawn
     * @param col
     *            column of the pawn
     * @param color
     *            either GamePiece.WHITE, BLACK, or UNASSIGNED
     */
    public Pawn( ChessGameBoard board, int row, int col, int color ){
        super( board, row, col, color, true );
        notMoved = true;
        possibleMoves = calculatePossibleMoves( board );
    }
    /**
     * Moves this pawn to a row and col
     *
     * @param board
     *            the board to move on
     * @param row
     *            the row to move to
     * @param col
     *            the col to move to
     * @return boolean true if the move was successful, false otherwise
     */
    @Override
    public boolean move( ChessGameBoard board, int row, int col ){
        if ( super.move( board, row, col ) ){
            notMoved = false;
            possibleMoves = calculatePossibleMoves( board );
            if ( ( getColorOfPiece() == ChessGamePiece.BLACK && row == 7 )
                || ( getColorOfPiece() == ChessGamePiece.WHITE && row == 0 ) ){ // pawn has reached the end of the board, promote it to queen
                board.getCell( row, col ).setPieceOnSquare( new Queen(
                    board,
                    row,
                    col,
                    getColorOfPiece() ) );
            }
            return true;
        }
        return false;
    }
    /**
     * Calculates the possible moves for this piece. These are ALL the possible
     * moves, including illegal (but at the same time valid) moves.
     *
     * @param board
     *            the game board to calculate moves on
     * @return ArrayList<String> the moves
     */
    @Override
    protected ArrayList<String> calculatePossibleMoves( ChessGameBoard board ){
        ArrayList<String> moves = new ArrayList<String>();
        if ( isPieceOnScreen() ){
            int maxIter = notMoved ? 2 : 1;
            int currRow = getStartingRow();
            int count = 1;
            while ( count <= maxIter && isOnScreen( currRow, pieceColumn ) ){
                if ( isEmptySquare(board, currRow, pieceColumn) ){
                    moves.add( currRow + "," + pieceColumn );
                } else {
                    break;
                }
                currRow = getNextRow(currRow);
                count++;
            }
            addEnemyCaptureMoves(board, moves);
        }
        return moves;
    }

    private int getStartingRow() {
        return getColorOfPiece() == ChessGamePiece.WHITE ? ( pieceRow - 1 ) : ( pieceRow + 1 );
    }

    private int getNextRow(int currRow) {
        return ( getColorOfPiece() == ChessGamePiece.WHITE ) ? ( currRow - 1 ) : ( currRow + 1 );
    }

    private boolean isEmptySquare(ChessGameBoard board, int row, int col) {
        return board.getCell(row, col).getPieceOnSquare() == null;
    }

    private void addEnemyCaptureMoves(ChessGameBoard board, ArrayList<String> moves) {
        int leftRow = getColorOfPiece() == ChessGamePiece.WHITE ? ( pieceRow - 1 ) : ( pieceRow + 1 );
        int rightRow = getColorOfPiece() == ChessGamePiece.WHITE ? ( pieceRow - 1 ) : ( pieceRow + 1 );
        int leftCol = pieceColumn - 1;
        int rightCol = pieceColumn + 1;
        if ( isOnScreen( leftRow, leftCol ) && isEnemy( board, leftRow, leftCol ) ){
            moves.add( leftRow + "," + leftCol );
        }
        if ( isOnScreen( rightRow, rightCol ) && isEnemy( board, rightRow, rightCol ) ){
            moves.add( rightRow + "," + rightCol );
        }
    }
    /**
     * Creates an icon for this piece depending on the piece's color.
     *
     * @return ImageIcon the ImageIcon representation of this piece.
     */
    @Override
    public ImageIcon createImageByPieceType(){
        if ( getColorOfPiece() == ChessGamePiece.WHITE ){
            return new ImageIcon(
                getClass().getResource("chessImages/WhitePawn.gif")
            );            
        }
        else if ( getColorOfPiece() == ChessGamePiece.BLACK ){
            return new ImageIcon(
                getClass().getResource("chessImages/BlackPawn.gif")
            );            
        }
        else
        {
            return new ImageIcon(
                getClass().getResource("chessImages/default-Unassigned.gif")
            );           
        }
    }
}
