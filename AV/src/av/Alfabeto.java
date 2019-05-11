/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package av;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 *
 * @author Francisco Rodrigues
 */
public class Alfabeto {
    
    public Alfabeto() {
    }
    
    /**
     * Função recebe uma String str
     * e retorna a String sem acentos nos
     * caracteres. Exemplos: 'á' -> 'a', 
     * müllér -> muller.
     * @param str
     * @return String
     */
    private static String retiraAcento (String str) {
        String normalizaString = Normalizer.normalize(str, Normalizer.Form.NFD); 
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalizaString).replaceAll("");
    }
    
    /**
     * Função recebe uma String str
     * e um inteiro mode, e retorna a str
     * tratada de acordo com o valor do mode
     * mode = 0 para string da Trie
     * mode = 1 para string da Consulta.
     * @param str
     * @param mode
     * @return String
     */
    public String limpaString (String str, int mode) {
        String aux = "";
        str = retiraAcento(str);
        for (int i = 0 ; i < str.length(); i++) {
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
                aux += (char) (str.charAt(i) + 32);
            else if (((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') 
                    || (str.charAt(i) >= '0' && str.charAt(i) <= '9') 
                    || (str.charAt(i) == ' ')) && mode == 0)
                aux += str.charAt(i);
            else if (((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') 
                    || (str.charAt(i) >= '0' && str.charAt(i) <= '9') 
                    || (str.charAt(i) == '.') || (str.charAt(i) == ' ')) && mode == 1)
                aux += str.charAt(i);
            else
                aux += '?';
        }
        return aux;
    }
    
    /**
     * Função recebe uma String str e um
     * inteiro mode retorna true se os caracteres 
     * da str pertence ao alfabeto false caso contrario
     * mode = 0 verifica para trie
     * mode = 1 verifica para Consulta.
     * @param str
     * @param mode
     * @return boolean
     */
    public boolean verificaString (String str, int mode) {
        for (int i = 0; i < str.length(); i++) {
            if (((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') 
                    || (str.charAt(i) >= '0' && str.charAt(i) <= '9') 
                    || (str.charAt(i) == '?')) && mode == 0) {
            }
            else if (((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') 
                    || (str.charAt(i) >= '0' && str.charAt(i) <= '9') || (str.charAt(i) == '?') 
                    || (str.charAt(i) == '.')) && mode == 1) {
            }
            else
                return false;
        }
        return true;
    }
}
