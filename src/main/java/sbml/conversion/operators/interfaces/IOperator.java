package sbml.conversion.operators.interfaces;

public interface IOperator<T> {

    T Not(T x);

    T And(T x, T y);

    T Or(T x, T y);

    T Xor(T x, T y);

    T Implies(T x, T y);

    T Equals(T x, T y);

    T NotEquals(T x, T y);
}
