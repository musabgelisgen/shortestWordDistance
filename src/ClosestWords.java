import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
   LinkedList<String> closestWords = null;

   int closestDistance = -1;

   int firstMatchingCharCount = 0;
   public static int[][] matrix;
   public static int[] distances;

   int partDist(String w1, String w2, int w1len, int w2len){

      for (int j = firstMatchingCharCount + 1; j <= w2len; j++){
         for (int i = 1; i <= w1len; i++){
            int res = matrix[i - 1][j - 1] + (w1.charAt(i - 1) == w2.charAt(j - 1) ? 0 : 1);
            int addLetter = matrix[i - 1][j] + 1;
            int deleteLetter = matrix[i][j - 1] + 1;
            matrix[i][j] = Math.min(Math.min(res, addLetter), deleteLetter);
         }
      }
      return matrix[w1len][w2len];
   }

   int Distance(String w1, String w2) {
      return partDist(w1, w2, w1.length(), w2.length());
   }

   public ClosestWords(String w, List<String> wordList) {
      int i = 0;
      for (String word : wordList) {
         int wordLength = word.length();
         // int lengthDiff = Math.abs(wordLength - w.length());
         // if((lengthDiff <= closestDistance) || closestDistance == -1){
         firstMatchingCharCount = distances[i];
         int dist = Distance(w, word);
         if (dist < closestDistance || closestDistance == -1) {
            closestDistance = dist;
            closestWords = new LinkedList<String>();
            closestWords.add(word);
         }
         else if (dist == closestDistance){
            closestWords.add(word);
         }
         i++;
         // }

      }
   }

   int getMinDistance() {
      return closestDistance;
   }

   List<String> getClosestWords() {
      return closestWords;
   }
}
