import java.util.*;

public class Main {
    public static void main(String[] args) {
        Queue<String> filaInfixa = passo1();
        System.out.println("Infixa: " + String.join(" ", filaInfixa));

        Queue<String> filaPosFixa = passo2(filaInfixa);
        System.out.println("Pós Fixa: " + String.join(" ", filaPosFixa));

        String resultado = passo3(filaPosFixa);
        System.out.println("Resultado: " + resultado);
    }

    public static Queue<String> passo1(){
        Queue<String> filaInfixa = new LinkedList<>();

        Scanner scan = new Scanner(System.in);
        String exp = scan.nextLine();
        String[] simbolos = exp.split(" ");

        for(String simbolo : simbolos){
            filaInfixa.offer(simbolo);
        }

        return filaInfixa;
    }

    public static Queue<String> passo2(Queue<String> filaInfixa){
        Queue<String> filaPosFixa = new LinkedList<>();
        Stack<String> pilhaConv = new Stack<>();


        while (!filaInfixa.isEmpty()){
            Simbolo simbFila = new Simbolo(filaInfixa.poll());

            if(simbFila.isOperando()){
                filaPosFixa.offer(simbFila.caractere);
                continue;
            }

            if(simbFila.isAbreParenteses()){
                pilhaConv.push(simbFila.caractere);
                continue;
            }

            if(simbFila.isOperador()){
                while(!pilhaConv.isEmpty()){
                    Simbolo simbPilha = new Simbolo(pilhaConv.peek());
                    if(simbFila.getPrioridade() > simbPilha.getPrioridade()){
                        break;
                    }
                    pilhaConv.pop();
                    filaPosFixa.offer(simbPilha.caractere);
                }
                pilhaConv.push(simbFila.caractere);
            }

            if(simbFila.isFechaParenteses()){
                while(!pilhaConv.isEmpty()){
                    Simbolo simbPilha = new Simbolo(pilhaConv.peek());
                    if(simbPilha.isAbreParenteses()){
                        break;
                    }
                    pilhaConv.pop();
                    filaPosFixa.offer(simbPilha.caractere);
                }
                pilhaConv.pop();
            }
        }

        while(!pilhaConv.isEmpty()){
            String simbPilha = pilhaConv.pop();
            filaPosFixa.offer(simbPilha);
        }

        return filaPosFixa;
    }

    public static String passo3(Queue<String> filaEntrada){
        Stack<String> pilhaCalc = new Stack<>();
        while(!filaEntrada.isEmpty()){
            Simbolo simbFila = new Simbolo(filaEntrada.poll());

            if(simbFila.isOperando()){
                pilhaCalc.push(simbFila.caractere);
                continue;
            }
            if(simbFila.isOperador()){
                String operandoA = pilhaCalc.pop();
                String operandoB = pilhaCalc.pop();
                String resultado = realizaOperacao(operandoA, operandoB, simbFila.caractere);
                pilhaCalc.push(resultado);
            }
        }
        return pilhaCalc.peek();
    }

    public static String realizaOperacao(String operandoA, String operandoB, String operador){
        Double valorA = Double.parseDouble(operandoA);
        Double valorB = Double.parseDouble(operandoB);

        if(operador.equals("+")){
            return String.valueOf(valorA + valorB);
        }
        if(operador.equals("-")){
            return String.valueOf(valorB - valorA);
        }
        if(operador.equals("*")){
            return String.valueOf(valorA * valorB);
        }
        if(operador.equals("/")){
            return String.valueOf(valorB / valorA);
        }

        return null;
    }
}