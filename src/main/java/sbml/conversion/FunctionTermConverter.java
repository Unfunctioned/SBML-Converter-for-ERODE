package sbml.conversion;

import it.imt.erode.booleannetwork.updatefunctions.*;
import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.ext.qual.FunctionTerm;
import sbml.configurations.SBMLConfiguration;
import sbml.conversion.nodes.NodeConverter;

public class FunctionTermConverter {
    private static final SBMLConfiguration CONFIG = SBMLConfiguration.getConfiguration();

    public IUpdateFunction toErode(FunctionTerm functionTerm) {
        ASTNode node = functionTerm.getMath();
        NodeConverter converter = NodeConverter.create(node);
        return converter.getUpdateFunction();
    }

    public ListOf<FunctionTerm> toSBML(IUpdateFunction updateFunction, int maxLevel) {
        ListOf<FunctionTerm> functionTerms = new ListOf<>(CONFIG.getLevel(),CONFIG.getVersion());
        for(int i = maxLevel; i > 0; i--) {
            FunctionTerm f = new FunctionTerm(CONFIG.getLevel(), CONFIG.getVersion());
            f.setResultLevel(i);
            f.setMath(ConvertMath(updateFunction));
            functionTerms.add(f);
        }
        FunctionTerm defaultTerm = new FunctionTerm();
        defaultTerm.setDefaultTerm(true);
        functionTerms.add(defaultTerm);
        return functionTerms;
    }

    private ASTNode ConvertMath(IUpdateFunction updateFunction) {
        if(updateFunction.getClass().equals(ReferenceToNodeUpdateFunction.class)) {
            ReferenceToNodeUpdateFunction refExpr = (ReferenceToNodeUpdateFunction) updateFunction;
            return new ASTNode(refExpr.toString());
        }
        else if(updateFunction.getClass().equals(BooleanUpdateFunctionExpr.class)) {
            BooleanUpdateFunctionExpr expr = (BooleanUpdateFunctionExpr) updateFunction;
            ASTNode leftChild = ConvertMath(expr.getFirst());
            ASTNode rightChild = ConvertMath(expr.getSecond());
            return CreatBinaryNode(leftChild,rightChild,expr);

        }
        else if(updateFunction.getClass().equals(NotBooleanUpdateFunction.class)) {
            NotBooleanUpdateFunction notExpr = (NotBooleanUpdateFunction) updateFunction;
            ASTNode child = ConvertMath(notExpr.getInnerUpdateFunction());
            ASTNode booleanNot = new ASTNode(child);
            booleanNot.setType(ASTNode.Type.LOGICAL_NOT);
            return booleanNot;

        }
        else if(updateFunction.getClass().equals(TrueUpdateFunction.class)){
            return new ASTNode(1);
        }
        else {
            return new ASTNode(0);
        }
    }

    private ASTNode CreatBinaryNode(ASTNode leftChild, ASTNode rightChild, BooleanUpdateFunctionExpr expr) {
        ASTNode astNode = new ASTNode();
        switch (expr.getOperator()) {
            case AND:
                astNode.setType(ASTNode.Type.LOGICAL_AND);
                break;
            case OR:
                astNode.setType(ASTNode.Type.LOGICAL_OR);
                break;
            case IMPLIES:
                astNode.setType(ASTNode.Type.LOGICAL_IMPLIES);
                break;
            default:
                throw new IllegalArgumentException("Invalid type");
        }
        astNode.addChild(leftChild);
        astNode.addChild(rightChild);
        return astNode;
    }
}
