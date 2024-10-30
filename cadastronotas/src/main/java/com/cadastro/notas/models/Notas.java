package com.cadastro.notas.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Notas {

    private DoubleProperty nota1;
    private DoubleProperty nota2;
    private DoubleProperty nota3;
    private DoubleProperty nota4;

    public Notas(double nota1, double nota2, double nota3, double nota4) {

        this.nota1 = new SimpleDoubleProperty(nota1);
        this.nota2 = new SimpleDoubleProperty(nota2);
        this.nota3 = new SimpleDoubleProperty(nota3);
        this.nota4 = new SimpleDoubleProperty(nota4);

    }

    public double getNota1() {
        return nota1.doubleValue();
    }

    public void setNota1(double nota1) {
        this.nota1.set(nota1);
    }

    public DoubleProperty nota1Property() {
        return nota1;
    }

    public double getNota2() {
        return nota2.doubleValue();
    }

    public void setNota2(double nota2) {
        this.nota2.set(nota2);
    }

    public DoubleProperty nota2Property() {
        return nota2;
    }

    public double getNota3() {
        return nota3.doubleValue();
    }

    public void setNota3(double nota3) {
        this.nota3.set(nota3);
    }

    public DoubleProperty nota3roperty() {
        return nota3;
    }

    public double getNota4() {
        return nota4.getValue();
    }

    public void setNota4(double nota4) {
        this.nota4.set(nota4);
    }

    public DoubleProperty nota4roperty() {
        return nota4;
    }

}