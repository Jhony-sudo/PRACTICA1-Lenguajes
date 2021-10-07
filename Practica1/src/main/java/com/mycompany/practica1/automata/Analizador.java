
package com.mycompany.practica1.automata;




public class Analizador {
    private static int Estados = 9;
    private static int Alfabeto = 7;
    int [][] Transicion = new int [Estados][Alfabeto];
    int [] EstadosAceptados = {1,2,4,5,6,7,8};
    private String Texto;
    private int Posicion = 0;
    private int EstadoActual;
    private int EstadoAnterior;
    private String Lexema = "";
    private int Linea = 1;
    private int Columna = 1;
    public static boolean HayError = false;
    private ManejadorToken n = new ManejadorToken();
    public static String Pasos = "";
    //Transiciones y Estados
    {
        
      for(int i =0;i<Estados;i++){
          for(int x=0;x<Alfabeto;x++){
              Transicion[i][x]=-1;
              
          }
      }
      Transicion[0][0] = 5;
      Transicion[0][1] = 6;
      Transicion[0][2] = 8;
      Transicion[0][3] = 2;
      Transicion[0][4] = 1;
      Transicion[1][3] = 7;
      Transicion[1][4] = 1;
      Transicion[2][3] = 2;
      Transicion[2][5] = 3;
      Transicion[3][3] = 4;
      Transicion[4][3] = 4;
      Transicion[7][3] = 7;
      Transicion[7][4] = 1;
      
      Transicion[8][6] = 0;
      Transicion[7][6] = 0;
      Transicion[6][6] = 0;
      Transicion[5][6] = 0;
      Transicion[4][6] = 0;
      Transicion[3][6] = 0;
      Transicion[2][6] = 0;
      Transicion[1][6] = 0;
      Transicion[0][6] = 0;
      
      
      
    }
    
   
    public Analizador(String Texto){
        this.Texto = Texto;
    }
    
    public void Iniciar(){
       int Longitud = Texto.length();
       
       EstadoActual = 0;
       int Caracter = 0;
       while(Posicion < Longitud){
           char CaracterA = Texto.charAt(Posicion);
           EstadoAnterior =  EstadoActual;
           
           System.out.println(EstadoActual);
           System.out.println(getCaracter(CaracterA));
           if(getCaracter(CaracterA) != -1){
           EstadoActual = Transicion[EstadoActual][getCaracter(CaracterA)];
           EstadoActual = (Character.isSpaceChar(CaracterA))?0:EstadoActual;
            
                if(!Reiniciar(EstadoActual,EstadoAnterior,CaracterA)){
                    System.out.println("Entre");
                Pasos = Pasos + "Me movi del estado" + EstadoAnterior + " al estado" + EstadoActual + " CON UNA " + CaracterA + "\n";
                Lexema = Lexema + CaracterA;
                }
           
           
           
           }else{ 
                 Pasos = Pasos + ("Error "  + CaracterA + " No se encuentra en el alfabeto") + "\n"; 
                 Lexema = Lexema + CaracterA;
                 n.ReportarError(Lexema, Columna, Linea);
                 Lexema = "";
                 EstadoActual = 0;
                 }
           Posicion ++;
           Linea ++;
           
           if(Posicion == Longitud){
               Pasos = Pasos+"Fin de la cadena" + "\n";
               AsignarToken(EstadoActual);
           }
           
           
       }
    }
    
    public boolean Reiniciar(int Estado, int Anterior, char CaracterA){
        boolean tmp = false;
        System.out.println("Lexema actual " + Lexema);
        if(Estado == 6 | Estado == 5 | Estado == 8 ){
            
            Pasos = Pasos+"Me movi del estado" + EstadoAnterior + " al estado" + EstadoActual + " CON UNA " + CaracterA + "\n";
            Lexema = CaracterA + "";
            tmp = true;
            
        }
        if(Character.isSpaceChar(CaracterA)){
            EstadoActual = 0;
            Pasos = Pasos+"Se encontro un espacio, reiniciando automata" + "\n";
            
            AsignarToken(Anterior);
            
           Lexema = "";
            tmp = true;
        }
        if(CaracterA == '\n'){
            EstadoActual = 0;
            Pasos = Pasos + " Salto de Linea" + "\n";
            AsignarToken(Anterior);
            Linea = 0;
            Columna++;
            
            Lexema = "";
            tmp = true;
        }
        if(Estado == -1){
            
            EstadoActual = 0;
            Pasos = Pasos+"Error con " + CaracterA + " en " + Lexema + ("en Columna " + Columna + " Linea: " + Linea )+"\n";
            Pasos = Pasos+"Reiniciando Automata" + "\n";
            Lexema = Lexema + CaracterA;
            n.ReportarError(Lexema, Columna, Linea);
            HayError =  true;
            
            Lexema = "";
            tmp = true;
        }
        return tmp;
    }
    
    public void AsignarToken(int Estado){
        
        switch(Estado){
            case 1:
                  n.Llenar(TipoToken.Identificador,Lexema,Linea,Columna);
                  Pasos = Pasos + "Asignando Token: Identificador Lexema:" + Lexema + "\n";
                break;
            case 2:
                  n.Llenar(TipoToken.Numero,Lexema,Linea,Columna);
                  Pasos = Pasos + "Asignando Token: Numero Lexema: " + Lexema+"\n";
                
                break;
            case 4:
                  n.Llenar(TipoToken.Decimal,Lexema,Linea,Columna);
                  Pasos = Pasos + "Asignando Token: Numero Decimal " + Lexema+"\n";
                
                break;
            case 5:
                  n.Llenar(TipoToken.Operador,Lexema,Linea,Columna);
                  Pasos = Pasos + "Asignando Token: Operador Lexema:" + Lexema+"\n";
                
                break;
            case 6:
                  n.Llenar(TipoToken.Agrupacion,Lexema,Linea,Columna);
                  Pasos = Pasos + "Asignando Token: Agrupacion Lexema:" + Lexema+"\n";
                
                break;
            case 7:
                  n.Llenar(TipoToken.Identificador,Lexema,Linea,Columna);
                  Pasos = Pasos + "Asignando Token: Identificador Lexema:" + Lexema+"\n";
                
                break;
            case 8:
                  n.Llenar(TipoToken.Puntuacion,Lexema,Linea,Columna);
                  Pasos = Pasos + "Asignando Token: Puntuacion Lexema:" + Lexema+"\n";
                ;
                break;
                
            default:
                System.out.println("ERROR EN ASIGNACION");
                break; 
                
        }
        
        
    }
    
    public int getCaracter(char Caracter){
        int tmp = -1;
        if(Character.isDigit(Caracter)){
            tmp = 3;
        }
        if(Character.isLetter(Caracter) && Caracter != 'ñ'){
            tmp = 4;
        }
        if(esAgrupacion(Caracter)){
            tmp = 1;
        }
        if(esOperador(Caracter)){
            tmp = 0;
        }
        if(Character.isSpaceChar(Caracter)){
            tmp= 6;
        }
        if(Caracter == '.' && EstadoAnterior == 2){
            tmp = 5;
        }
        if(Caracter == '\n'){
            System.out.println("Salto de Linea");
            tmp = 6;
        }
        if(esPuntuacion(Caracter)){
            if(EstadoAnterior == 2 && Caracter == '.'){
            tmp = 5;}
            else tmp = 2;
        }
        
       
        return tmp;
    }
    
    public boolean esAgrupacion(char Caracter){
        boolean tmp = false;
        if(Caracter == '{' | Caracter == '}' | Caracter == '(' | Caracter == ')' 
        | Caracter == '[' | Caracter == ']' ){
            tmp = true;
        }
        return tmp;
    }
    
    
    
    
    
    public boolean esOperador(char Caracter){
        boolean tmp = false;
        if(Caracter == '+' | Caracter == '-' | Caracter == '*' | Caracter == '/' 
        | Caracter == '%'){
            tmp = true;
        }
        return tmp;
    }
    
    public boolean esPuntuacion(char Caracter){
        boolean tmp = false;
        if(Caracter == '.' | Caracter == ';' | Caracter == ',' | Caracter == ':' ){
            tmp = true;
        }
        return tmp;
    }
    
    public boolean EstadoAceptacion(int Estado){
        boolean tmp = false;
        for(int i =0;i<EstadosAceptados.length;i++){
            if(EstadosAceptados [i] == Estado){
                tmp = true;
            }
        }
        return tmp;
    }
}
