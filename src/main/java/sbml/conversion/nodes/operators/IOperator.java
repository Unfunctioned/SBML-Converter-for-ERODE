package sbml.conversion.nodes.operators;

public interface IOperator<T> {

    T not(T x);

    T and(T x, T y);

    T or(T x, T y);

    T xor(T x, T y);

    T implies(T x, T y);

    T equals(T x, T y);

    T notEquals(T x, T y);
}
