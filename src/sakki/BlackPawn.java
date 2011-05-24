/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sakki;

/**
 * Black pawn.
 *
 * @author Tuomas Starck
 */
class BlackPawn extends Piece {
    private final int INITIAL_RANK = 1;
    private final int PROMOTION_RANK = 7;

    public BlackPawn(Coord birthplace) {
        super(Type.p, birthplace);
    }

    @Override
    public void update(Type[][] status, Coord enpassant) {
        reset();

        markIfMoveable(loc.south(1), status);

        if (loc.rank == INITIAL_RANK) {
            markIfMoveable(loc.south(2), status);
        }

        Coord se = loc.southeast(1);
        Coord sw = loc.southwest(1);

        markIfCapturable(se, status);
        markIfCapturable(sw, status);

        if (enpassant == null) return;

        if (enpassant.equals(se)) {
            view[se.rank][se.file] = Type.capturable;
        }
        if (enpassant.equals(sw)) {
            view[sw.rank][sw.file] = Type.capturable;
        }
    }

    @Override
    public Rebound move(Move move) {
        Coord target = move.to();
        Rebound rebound = new Rebound();

        if (target.rank == PROMOTION_RANK) {
            rebound.promotionAvailable();
        }

        if (loc.rank == INITIAL_RANK && target.rank == (INITIAL_RANK+2)) {
            rebound.setEnpassant(loc.south(1));
        }

        loc = target;

        return rebound;
    }
}