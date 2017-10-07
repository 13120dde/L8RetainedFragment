package mahlabs.l8retainedfragment.expressions;

import java.io.*;

/**
 * Created by tsroax on 2014-09-30.
 */
public class Expression implements Serializable {
	private static final long serialVersionUID = 1L;
	private int nbr1;
    private int nbr2;
    private char operation;

    public Expression(int nbr1, char operation, int nbr2) {
        this.nbr1 = nbr1;
        this.operation = operation;
        this.nbr2 = nbr2;
    }

    public int getNbr1() {
        return nbr1;
    }

    public int getNbr2() {
        return nbr2;
    }

    public char getOperation() {
        return operation;
    }
}
