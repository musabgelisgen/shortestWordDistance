import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
   public static List<String> readWordList(BufferedReader input) throws IOException {
      LinkedList<String> list = new LinkedList<>();
      while (true) {
         String s = input.readLine();
         if (s.equals("#"))
         break;
         list.add(s);
      }
      return list;
   }

   public static List<String> readCheckList(BufferedReader input) throws IOException {

      LinkedList<String> list = new LinkedList<>();
      while (true) {
         String s = input.readLine();
         if (s == null)
         break;
         list.add(s);
      }
      return list;
   }

   public static void main(String args[]) throws IOException {
      long t1 = System.currentTimeMillis();

      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
      List<String> wordList = readWordList(stdin);
      List<String> checkList = readCheckList(stdin);

      int matrixSize = 38;
      int[][] matrix = new int[matrixSize][matrixSize];
      for (int i = 0; i < matrixSize; i++){
         matrix[i][0] = i;
         matrix[0][i] = i;
      }
      ClosestWords.matrix = matrix;

      int wordListSize = wordList.size();
      int[] distances = new int[wordListSize];
      String[] wordArray = wordList.toArray(new String[wordListSize]);

      distances[0] = 0;
      for (int i = 0; i < wordListSize - 1; i++){
         distances[i+1] = getFirstMatchingCharCount(wordArray[i], wordArray[i+1]);
      }

      ClosestWords.distances = distances;

      StringBuilder sb = new StringBuilder();
      for (String word : checkList) {
         ClosestWords closestWords = new ClosestWords(word, wordList);
         sb.append(word);
         sb.append(" (");
         sb.append(closestWords.getMinDistance());
         sb.append(")");
         for (String w : closestWords.getClosestWords()){
            sb.append(" ");
            sb.append(w);
         }
         sb.append("\n");
      }
      System.out.print(sb);
      long tottime = (System.currentTimeMillis() - t1);
      //System.out.println("CPU time: " + tottime + " ms");
   }

   public static int getFirstMatchingCharCount(String w1, String w2){
      int sameCharCount = 0;
      int shortestWordLength = Math.min(w1.length(), w2.length());
      for(int i = 0; i < shortestWordLength; i++){
         if(w1.charAt(i) != w2.charAt(i)){
            break;
         }else{
            sameCharCount++;
         }
      }
      return sameCharCount;
   }
}
