
package com.mycompany.practica1.automata;

public enum TipoToken {
        Identificador(""),
        Numero(""),
        Decimal(""),
        Puntuacion(""),
        Operador(""),
        Agrupacion("");
        
        private String Lexema;
        
        private TipoToken(String Lexema){
            this.Lexema = Lexema;
        }

    
    public String getLexema() {
        return Lexema;
    }

    public void setLexema(String Lexema) {
        this.Lexema = Lexema;
    } 
        
}
