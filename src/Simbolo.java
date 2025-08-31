public class Simbolo {
    public String caractere;

    public Simbolo(String caracter) {
        this.caractere = caracter;
    }

    public Boolean isOperando(){
        return Character.isDigit(caractere.charAt(0));
    }

    public boolean isOperador(){
        return "+-*/".contains(caractere);
    }

    public boolean isAbreParenteses(){
        return caractere.equals("(");
    }

    public boolean isFechaParenteses(){
        return caractere.equals(")");
    }

    public int getPrioridade(){
        if(isAbreParenteses() || isFechaParenteses()){
            return 0;
        }
        if("+-".contains(caractere)){
            return 1;
        }
        if("*/".contains(caractere)){
            return 2;
        }
        return -1;
    }
}
