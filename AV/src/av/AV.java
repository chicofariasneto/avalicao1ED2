package av;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AV {
    
    /**
     * Retorna o maior prefixo de s que seja
     * uma chave da trie ou null caso nao 
     * haja resposta.
     * @param s
     * @param trie
     * @return String
     */
    public static String maiorPrefixo(String s, TrieST trie) {
        String prefix = null;
        int i = s.length();
        Iterable<String> it;
        while (i >= 1) {
            prefix = s.substring(0, i);
            it = trie.keysThatMatch(prefix);
            if (it.iterator().hasNext()) return prefix;
            --i;
        }
        return null;
    }
    
    /**
     * Funcao responsavel pela leitura de arquivos de
     * entrada e insercao na trie.
     * @param trie
     */
    public static void lerDados(TrieST trie) {
        FileInputStream in = null;
        Alfabeto alfabeto = new Alfabeto();
        try {
            in = new FileInputStream("movie.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("Erro ao ler arquivo movie.txt, por favor, certifique-se"
                    + "de que o arquivo esta no mesmo diretorio que a pasta src"
                    + "desse projeto.");
            System.exit(1);
        }
        Scanner scan = new Scanner(in);
        while (scan.hasNextLine()) {
            String str = scan.nextLine();
            str = alfabeto.limpaString(str, 0);
            trie.put(str, 0);
        }
        scan.close();
    }
    
    /**
     * Funcao principal para a resolucao do problema 7.9.
     * Essa funcao parte do pressuposto que o usurario
     * iniciara o programa com uma string de consulta
     * como argumento, que deve estar envolta em
     * aspas simples.
     * @author Francisco Rodrigues de Farias Neto - 201700017321 
     * e Lucas Tiago de Jesus Pereira - 201700100286 
     * @param args 
     */
    public static void main(String[] args) {
        TrieST trie = new TrieST();
        Alfabeto alfabeto = new Alfabeto();
        args[0] = alfabeto.limpaString(args[0], 1);
        lerDados(trie);
        Iterable<String> it = trie.keysWithPrefix(args[0]);
        // (1)
        if (it.iterator().hasNext()) {
            System.out.println("Filmes que tem títulos com " + args[0] +
                    " como prefixo:");
            for (String str : it) System.out.println(str);
        }
        else 
            System.out.println("Não existem filmes com prefixo " + args[0]);
        System.out.println("");
        //(2)
        String resp = maiorPrefixo(args[0], trie);
        if (resp != null)
            System.out.println("O maior prefixo de " + args[0] 
                    + " que é título de filme" + " é " + resp);
        else
            System.out.println("Não existem prefixos de " +args[0] 
            + " que sejam títulos de filme");
        System.out.println("");
        //(3)
        it = trie.keysThatMatch(args[0]);
        if (it.iterator().hasNext()) {
            System.out.println("Títulos de filme que casam com " + args[0] + " :");
            for (String str : it) System.out.println(str);
        }
        else System.out.println(args[0] + " não casa com nenhum dos títulos");
    }
}