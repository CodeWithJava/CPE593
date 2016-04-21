package syy;

import java.util.Scanner; 
import java.io.FileInputStream; 
import java.io.IOException; 

public class TrieDictionary
{
        public static void main(String [] args) throws IOException
        {
                Trie x = new Trie();
                Scanner scanner = new Scanner(System.in); 
                System.out.println("Please input path of the dictionary data file"); 
                String path = scanner.next(); 
                Scanner data = new Scanner(new FileInputStream(path)); 
                while(data.hasNext())
                        x.insert(data.next());
                data.close();
                
                System.out.println("Please input path of the test data file"); 
                String path2 = scanner.next(); 
                Scanner data2 = new Scanner(new FileInputStream(path2)); 
                while(data2.hasNext())
                        System.out.println(x.search(data2.next()));
                scanner.close();
                data2.close();
        }
}