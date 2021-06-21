package sbml.conversion;

import it.imt.erode.booleannetwork.updatefunctions.BooleanUpdateFunctionExpr;
import it.imt.erode.booleannetwork.updatefunctions.IUpdateFunction;
import it.imt.erode.booleannetwork.updatefunctions.ReferenceToNodeUpdateFunction;
import it.imt.erode.crn.symbolic.constraints.BooleanConnector;
import org.sbml.jsbml.ASTNode;

public class UpdateFunctionBuilder {

    /*
    Logical Operators
    * */
    public static IUpdateFunction And(IUpdateFunction x, IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x, y, BooleanConnector.AND);
    }

    public static IUpdateFunction Or(IUpdateFunction x, IUpdateFunction y) {
        return new BooleanUpdateFunctionExpr(x, y, BooleanConnector.OR);
    }

    public static IUpdateFunction Not(IUpdateFunction x) {
        //Not P is equivalent to (P -> false)
        return new BooleanUpdateFunctionExpr(x, new ReferenceToNodeUpdateFunction("false"), BooleanConnector.IMPLIES);
    }

    public static IUpdateFunction Xor(IUpdateFunction x, IUpdateFunction y) {
        //(x || y) && !(x && y)
        IUpdateFunction xOrY = new BooleanUpdateFunctionExpr(x, y, BooleanConnector.OR);
        IUpdateFunction xAndY = new BooleanUpdateFunctionExpr(x,y, BooleanConnector.AND);
        return new BooleanUpdateFunctionExpr(xOrY, xAndY, BooleanConnector.AND);
    }

    public static IUpdateFunction Equals(IUpdateFunction x, IUpdateFunction y) {
        //x <-> y = x->y && y->x
        IUpdateFunction xImpliesY = new BooleanUpdateFunctionExpr(x, y, BooleanConnector.IMPLIES);
        IUpdateFunction yImpliesX = new BooleanUpdateFunctionExpr(y, x, BooleanConnector.IMPLIES);
        return new BooleanUpdateFunctionExpr(xImpliesY, yImpliesX, BooleanConnector.AND);
    }

    public static IUpdateFunction NotEquals(IUpdateFunction x, IUpdateFunction y) {
        IUpdateFunction equals = Equals(x,y);
        return Not(equals);
    }

    public static IUpdateFunction Reference(ASTNode node) {
        return new ReferenceToNodeUpdateFunction(node.getName());
    }

    public static IUpdateFunction Constant(ASTNode node) {
        switch (node.getInteger()) {
            case 1:
                return new ReferenceToNodeUpdateFunction("true");
            case 0:
                return new ReferenceToNodeUpdateFunction("false");
            default:
                throw new IllegalArgumentException("Given network is not a boolean network");
        }
    }
}
