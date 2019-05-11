import av.Alfabeto;
import av.TrieST;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class teste1 {
    
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
            in = new FileInputStream("consultas.txt");
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
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int command = scan.nextInt();
        String s, resp;
        TrieST trie = new TrieST();
        Alfabeto alfabeto = new Alfabeto();
        lerDados(trie);
        scan.nextLine();
        Iterable<String> it;
        while (command != 0) {
            s = scan.nextLine();
            // System.out.println(s);
            s = alfabeto.limpaString(s, 1);
            // System.out.println(s);
            if (command == 1) {
                it = trie.keysWithPrefix(s);
                for (String str : it) System.out.println(str);
            }
            else if (command == 2) {
                resp = maiorPrefixo(s, trie);
                if (resp != null) System.out.println(resp);
            }
            else {
                it = trie.keysThatMatch(s);
                for (String str : it) System.out.println(str);
            }
            command = scan.nextInt();
            scan.nextLine();
        }
        scan.close();
    }
}
