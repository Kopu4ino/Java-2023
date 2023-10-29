package edu.hw2;

public class Task1 {
    public sealed interface Expr {
        double evaluate();

        record Constant(double val) implements Expr {
            @Override
            public double evaluate() {
                return val;
            }
        }

        record Negate(Expr expr) implements Expr {
            @Override
            public double evaluate() {
                return -expr.evaluate();
            }
        }

        record Exponent(Expr expr, int pow) implements Expr {
            @Override
            public double evaluate() {
                return Math.pow(expr.evaluate(), pow);
            }
        }

        record Addition(Expr expr1, Expr expr2) implements Expr {
            @Override
            public double evaluate() {
                return expr1.evaluate() + expr2.evaluate();
            }
        }

        record Multiplication(Expr expr1, Expr expr2) implements Expr {
            @Override
            public double evaluate() {
                return expr1.evaluate() * expr2.evaluate();
            }
        }
    }
}
