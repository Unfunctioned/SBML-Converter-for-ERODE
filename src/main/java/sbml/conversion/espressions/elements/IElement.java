package sbml.conversion.espressions.elements;

public interface IElement<T,T2> {

    T2 Reference(T node);

    T2 Constant(T node);

    T2 BooleanValue(T node);
}
