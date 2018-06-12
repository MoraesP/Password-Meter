/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "senha")
public class Senha {

    private String password;
    private String complex;
    private boolean symb = false;
    private boolean numb = false;
    private boolean upper = false;
    private boolean lower = false;
    private final String sNumb = "0123456789";
    private final String sSymb = "!@#$%¨&*()";
    private final String sLett = "abcdefghijklmnopqrstuvwxyz";

    //CONTA O NÚMERO DE CARACTERES
    private int numberOfCharacters(String s) {
        return (s.length() * 4);
    }

    //QUANTIDADE DE LETRAS MAISCULAS
    private int uppercaseLetters(String s) {
        String str = s.replaceAll("[^A-Z]+", "");
        upper = str.length() > 0;
        return str.length() > 0 ? (s.length() - str.length()) * 2 : 0;
    }

    //QUANTIDADE DE LETRAS MINUSCULAS
    private int lowercaseLetters(String s) {
        String str = s.replaceAll("[^a-z]+", "");
        lower = str.length() > 0;
        return str.length() > 0 ? (s.length() - str.length()) * 2 : 0;
    }

    //QUANTIDADE DE NUMEROS
    private int numbers(String s) {
        String str = s.replaceAll("[^0-9]+", "");
        numb = str.length() > 0;
        return s.length() == str.length() ? 0 : (str.length() * 4);

    }

    //QUANTIDADE DE SIMBOLOS
    private int symbols(String s) {
        String str = s.replaceAll("[A-Za-z0-9 ]+", "");
        symb = str.length() > 0;
        return (str.length() * 6);
    }

    //QUANTIDADE DE NUMEROS E SIMBOLOES ENTRE CARACTERES
    private int middleNumbersSymbols(String s) {
        int count = 0;
        for (int l = 1; l < s.length(); l++) {
            if (s.substring(l, l + 1).matches("[^A-Za-z ]")) {
                if (l + 2 < s.length() + 1) {
                    count++;
                }
            }
        }
        return (count * 2);
    }

    //REQUERIMENTOS
    private int requirements(String s) {
        int count = 0;
        if (s.length() >= 8) {
            count++;
        }
        if (symb) {
            count++;
        }
        if (numb) {
            count++;
        }
        if (lower) {
            count++;
        }
        if (upper) {
            count++;
        }
        return count >= 4 ? count * 2 : 0;
    }

    //APENAS LETRAS
    private int lettersOnly(String s) {
        String str = s.replaceAll("[^a-zA-Z]+", "");
        return s.length() == str.length() ? -s.length() : 0;
    }

    //APENAS NUMEROS
    private int numbersOnly(String s) {
        String str = s.replaceAll("[^0-9]+", "");
        return s.length() == str.length() ? -s.length() : 0;
    }

    //CARACTERES REPETIDOS CONFORME NO EXEMPLO
    private int repeatCharacters(String s) {
        double repInc = 0;
        int repChar = 0, unique, result;
        boolean exist;
        for (int a = 0; a < s.length(); a++) {
            exist = false;
            for (int b = 0; b < s.length(); b++) {
                if (s.charAt(a) == s.charAt(b) && a != b) {
                    exist = true;
                    repInc += Math.abs(s.length() / (b - a));
                }
            }
            if (exist) {
                repChar++;
                unique = s.length() - repChar;
                repInc = ((unique > 0) ? Math.ceil(repInc / unique) : Math.ceil(repInc));
            }
        }
        result = (int) repInc * -1;
        return result;
    }

    //QUANTIDADE DE CARACTERES REPETIDOS
    private int repeatCharactersCount(String s) {
        int count = 0, saveA = -1;
        for (int a = 0; a < s.length(); a++) {
            for (int b = 0; b < s.length(); b++) {
                char strA = s.charAt(a);
                char strB = s.charAt(b);
                if (strA == strB && a != b) {
                    if (!(saveA == a)) {
                        count++;
                    }
                    saveA = a;
                }
            }
        }
        System.out.println(count);
        return 0;
    }

    //QUANTIDADE LETRAS MAISCULAS, MINUSCULAS E NUMEROS CONSECUTIVOS
    private int consecutiveUpperAndLowerLettersAndNumbers(String s) {
        int countN = 0, countUp = 0, countLow = 0;
        for (int l = 0; l < s.length(); l++) {
            if (s.substring(l, l + 1).matches("[0-9]")) {
                if (l + 2 < s.length() + 1) {
                    if (s.substring(l + 1, l + 2).matches("[0-9]")) {
                        countN++;
                    }
                }
            }
        }
        for (int l = 0; l < s.length(); l++) {
            if (s.substring(l, l + 1).matches("[A-Z]")) {
                if (l + 2 < s.length() + 1) {
                    if (s.substring(l + 1, l + 2).matches("[A-Z]")) {
                        countUp++;
                    }
                }
            }
        }
        for (int l = 0; l < s.length(); l++) {
            if (s.substring(l, l + 1).matches("[a-z]")) {
                if (l + 2 < s.length() + 1) {
                    if (s.substring(l + 1, l + 2).matches("[a-z]")) {
                        countLow++;
                    }
                }
            }
        }
        return -((countN * 2) + (countUp * 2) + (countLow * 2));
    }

    //LETRAS EM SEQUENCIAS CONFORME PADRONIZADO
    private int sequentialLetters(String s) {
        int count = 0;
        for (int x = 0; x < 23; x++) {
            String str = sLett.substring(x, (x + 3));
            StringBuilder rev = new StringBuilder(str);
            rev.reverse();
            String sRev = rev.toString();
            if (s.toLowerCase().contains(str)) {
                count++;
            }
            if (s.toLowerCase().contains(sRev)) {
                count++;
            }
        }
        return -(count * 3);
    }

    //NUMEROS EM SEQUENCIAS CONFORME PADRONIZADO
    private int sequentialNumbers(String s) {
        int count = 0;
        for (int x = 0; x < 8; x++) {
            String str = sNumb.substring(x, (x + 3));
            StringBuilder rev = new StringBuilder(str);
            rev.reverse();
            String sRev = rev.toString();
            if (s.toLowerCase().contains(str)) {
                count++;
            }
            if (s.toLowerCase().contains(sRev)) {
                count++;
            }
        }
        return -(count * 3);
    }

    //SIMBOLOS EM SEQUENCIAS CONFORME PADRONIZADO
    private int sequentialSymbols(String s) {
        int count = 0;
        for (int x = 0; x < 8; x++) {
            String str = sSymb.substring(x, (x + 3));
            StringBuilder rev = new StringBuilder(str);
            rev.reverse();
            String sRev = rev.toString();
            if (s.toLowerCase().contains(str)) {
                count++;
            }
            if (s.toLowerCase().contains(sRev)) {
                count++;
            }
        }
        return -(count * 3);
    }

    public int start() {
        Senha senha = new Senha();
        String[] complexity = {"Curto demais", "Muito Fraco", "Fraco", "Bom", "Forte", "Muito forte"};

        int result;
        String s = password;

        if (s == null) {
            result = 0;
        } else {
            int a = senha.numberOfCharacters(s);
            int b = senha.uppercaseLetters(s);
            int c = senha.lowercaseLetters(s);
            int d = senha.numbers(s);
            int e = senha.symbols(s);
            int f = senha.middleNumbersSymbols(s);
            int g = senha.requirements(s);
            int h = senha.lettersOnly(s);
            int i = senha.numbersOnly(s);
            int j = senha.consecutiveUpperAndLowerLettersAndNumbers(s);
            int k = senha.sequentialLetters(s);
            int l = senha.sequentialNumbers(s);
            int m = senha.sequentialSymbols(s);
            int x = senha.repeatCharacters(s);
            result = a + b + c + d + e + f + g + h + i + j + k + l + m + x;
        }

        if (s == null) {
            complex = complexity[0];
        } else if (result < 20) {
            complex = complexity[1];
        } else if (result >= 20 && result < 40) {
            complex = complexity[2];
        } else if (result >= 40 && result < 60) {
            complex = complexity[3];
        } else if (result >= 60 && result < 80) {
            complex = complexity[4];
        } else if (result >= 80) {
            complex = complexity[5];
        }

        if (result > 100) {
            result = 100;
        }else if(result < 0) result = 0;
        password = Integer.toString(result);
        return result;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComplex() {
        return complex;
    }

    public void setComplex(String complex) {
        this.complex = complex;
    }
}
