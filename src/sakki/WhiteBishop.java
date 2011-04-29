/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sakki;

/**
 * White bishop.
 *
 * @author Tuomas Starck
 */
class WhiteBishop extends Piece {
    public WhiteBishop(String birthplace) {
        super(Type.b, birthplace);
    }

    @Override
    public void update(Type[][] status) {
        markDiagonal(status);
    }
}