package bstrom.akimts.demo_JUNIT;

import bstrom.akimts.demo_JUNIT.exception.DivideByZeroException;

public class CalculatriceImpl implements Calculatrice {
    @Override
    public double plus(double a, double b) {
        return a + b;
    }

    @Override
    public double minus(double a, double b) {
        return a - b;
    }

    @Override
    public double divide(double a, double b) throws DivideByZeroException {
        if( b == 0 )
            throw new DivideByZeroException();

        return a / b;
    }

    @Override
    public double multiply(double a, double b) {
        return a * b;
    }

}
