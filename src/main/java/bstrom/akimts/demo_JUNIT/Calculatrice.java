package bstrom.akimts.demo_JUNIT;

import bstrom.akimts.demo_JUNIT.exception.DivideByZeroException;

public interface Calculatrice {

    double plus(double a, double b);
    double minus(double a, double b);
    double divide(double a, double b) throws DivideByZeroException;
    double multiply(double a, double b);

}
