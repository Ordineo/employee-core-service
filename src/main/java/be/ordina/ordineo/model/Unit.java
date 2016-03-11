package be.ordina.ordineo.model;

public enum Unit {

    ORAJ("ORA/J"), MS, CW, EU, FIN, ICC, Q, SAP, SCO, SOUTH, TES, VW, NONE;

    private String name;
    Unit(String name){
        this.name = name;
    }

    Unit(){
        this.name = name();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
