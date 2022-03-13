import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class WSSolver {
	private static char[][] table;
	private static int width;
	private static List<String> words = new ArrayList<>();
	private static List<String> words_tofind = new ArrayList<>();
	private static final String[] directions = {"UpLeft", "Up", "UpRight", "Right", "DownRight", "Down", "DownLeft", "Left"};
	private static List<Integer[]> tmp = new ArrayList<>();
	private static Map<String, List<Integer[]>> results = new HashMap<>(); // word,length,row,col,direction : [[x1,y1], [x2,y2], ...]

	public static void main(String[] args) throws IOException {
		readFile(args[0]);

		words_tofind.addAll(words);
		words_tofind.sort(Comparator.comparing(String::length).reversed());
		for (String word : words_tofind)
			searchWord(word);


		buildAndSaveOutput(args[0].substring(0, args[0].length()-4) + "_out.txt");
	}

	/**
	 * Outputs the table and the draw to the fname file:
	 * Table: word  length  row,column  direction
	 * Table draw with the words
	 */
	private static void buildAndSaveOutput(String fname) throws IOException {
		PrintWriter pw = new PrintWriter(fname);

		char[][] table_toprint = new char[width][width];

		for (String word : words){
			for (Map.Entry<String, List<Integer[]>> entry : results.entrySet()){
				//Table
				String[] key = entry.getKey().split(",");
				if (key[0].equals(word))
					pw.printf("%-20s %12s %6d,%-6d %-12s\n", key[0], key[1], Integer.parseInt(key[2])+1, Integer.parseInt(key[3])+1, key[4]);

				//Table draw
				for (Integer[] p : entry.getValue())
					table_toprint[p[0]][p[1]] = Character.toUpperCase(table[p[0]][p[1]]);
			}
		}
		pw.println();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++)
				pw.printf("%c", (int)table_toprint[i][j] == 0 ? '.' :table_toprint[i][j]);
			pw.println();
		}


		pw.close();
	}

	/**
	 * Read file function
	 * @param fname -> file name
	 * @throws IOException
	 */
	private static void readFile(String fname) throws IOException{
		Scanner scf = new Scanner(new File(fname));
		String[] partition = scf.nextLine().split("");
		width = partition.length;
		table = new char[width][width];
		int current_row = 0;

		for (int i=0; i<width; i++) {
			table[current_row][i] = Character.toLowerCase(partition[i].charAt(0));
		}

		while (scf.hasNextLine()){
			String line = scf.nextLine();

			if (!line.contains(";") && !line.contains(",") && !line.contains(" ")) {
				partition = line.split("");
				current_row++;
				for (int i=0; i<width; i++){
					table[current_row][i] = Character.toLowerCase(partition[i].charAt(0));
				}

			}else{  // palavras a procurar
				partition = line.split("[\\s|;|,]+");
				for (String s : partition) {
					words.add(s);
				}

			}
		}

		scf.close();
	}

	/**
	 * Search a word and save it into the results map
	 * @param word -> word to find
	 */
	private static void searchWord(String word){
		for(int row = 0; row < width; row++){
			for (int col = 0; col < width; col++){

				if (table[row][col] == word.charAt(0)){
					for (String direction : directions){
						if(getPossibleWord(row, col, direction, word.length()).equals(word)) {
							if (isValid(word, direction)) {
								String key = String.format("%s,%s,%s,%s,%s", word, word.length(), row, col, direction);
								List<Integer[]> values = new ArrayList<>();
								values.addAll(tmp);
								results.put(key, values);
							}
						}
					}
				}

			}
		}
	}

	/**
	 * Check if a word is a substring of another word from the results map -> this function check is the possible positions are valid
	 * @param word -> word to check
	 * @return true or false
	 */
	private static boolean isValid(String word, String direction){
		for (Map.Entry<String, List<Integer[]>> entry : results.entrySet()){
			if (entry.getKey().split(",")[0].contains(word))
				for (Integer[] p_results : entry.getValue())
					for (Integer[] p_word : tmp)
						if (p_results[0] == p_word[0] && p_results[1] == p_word[1])
							if (entry.getKey().split(",")[4].equals(direction))
								return false;
		}

		return true;
	}

	/**
	 * If the position and the word length allow the creation of the possible word, this function will return the possible word
	 * Note: x+1 or x-1 or y+1 or y-1 -> Taking care of the case at the borders.
	 * @param x -> row
	 * @param y -> column
	 * @param direction -> possible direction to check
	 * @param len -> word length - 1
	 * @return if it's possible to form a word return the possible word, otherwise return the first char of the string
	 */
	private static String getPossibleWord(int x, int y, String direction, int len){
		tmp.clear();

		String pw = String.valueOf(table[x][y]);
		tmp.add(new Integer[]{x,y});

		switch (direction){
			case "UpLeft":
				if(x+1 - len >= 0 && y+1 - len >= 0){
					for (int i = 1; i < len; i++) {
						pw += String.valueOf(table[x - i][y - i]);
						tmp.add(new Integer[]{x - i, y - i});
					}
				}
				break;
			case "Up":
				if(x+1 - len >= 0){
					for (int i = 1; i < len; i++) {
						pw += String.valueOf(table[x - i][y]);
						tmp.add(new Integer[]{x - i, y});
					}
				}
				break;
			case "UpRight":
				if(x+1 - len >= 0 && y + len <= width){
					for(int i = 1; i < len; i++) {
						pw += String.valueOf(table[x - i][y + i]);
						tmp.add(new Integer[]{x - i, y + i});
					}
				}
				break;
			case "Right":
				if (y + len <= width){
					for (int i = 1; i < len; i++) {
						pw += String.valueOf(table[x][y + i]);
						tmp.add(new Integer[]{x, y + i});
					}
				}
				break;
			case "DownRight":
				if (x + len <= width && y + len <= width){
					for (int i = 1; i < len; i++) {
						pw += String.valueOf(table[x + i][y + i]);
						tmp.add(new Integer[]{x + i, y + i});
					}
				}
				break;
			case "Down":
				if (x + len <= width){
					for (int i = 1; i < len ; i++) {
						pw += String.valueOf(table[x + i][y]);
						tmp.add(new Integer[]{x + i, y});
					}
				}
				break;
			case "DownLeft":
				if(x + len <= width && y+1 - len >= 0){
					for (int i = 1; i < len ; i++) {
						pw += String.valueOf(table[x + i][y - i]);
						tmp.add(new Integer[]{x + i, y - i});
					}
				}
				break;
			case "Left":
				if (y+1 - len >= 0){
					for (int i = 1; i < len; i++) {
						pw += String.valueOf(table[x][y - i]);
						tmp.add(new Integer[]{x, y - i});
					}
				}
				break;
		}

		return pw;
	}
}
