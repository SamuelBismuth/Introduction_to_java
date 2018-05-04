import java.io.*;
import java.util.Scanner;

public class PictureProcessing {
	
	// We define all the variables we need to use.
	private String fileName;
	private int rows;
	private int columns;
	private int i;
	private int j;
	private short[][] pict;

	//constructor.
	public PictureProcessing(String fileName){
		this.fileName = fileName;
		this.readFil(fileName);
		this.writeFile(fileName, pict);
	}
	
	/** This function allows us to read a document according to his address. In our case, it will be a picture. 
	 Since the document has been read, with the help of the method "scanner", 
	 we are able to read the header of the picture and so, to know the size of the rows and of the columns.
	 Then, we fill a new matrix with all the pixels of our picture.
	 If any problem come to use in the lecture of the address of the document, thanks to "try" and"catch", the program
	 is stopping and an error message is displayed.
	 **/
	public void readFil(String fileName){
		try {
			FileReader fr = new FileReader(fileName); // We use the methods "FileReader" and "BufferedReader"
			BufferedReader br = new BufferedReader(fr);
			String str;
			str = br.readLine(); // "str" is the string which contain the first line of our document (the header).
			Scanner sc = new Scanner(str); // We create a scanner method.
			sc.next(); // We ask to the scanner to pass the first word.
			this.columns = sc.nextInt(); // This is the number of column.
			this.rows = sc.nextInt(); // This is the number of rows.
			this.pict = new short[rows][columns]; // We create a new matrix with the good size (according to the header).
			for(int i=0; i<rows; i++){
				int j = 0;
				str = br.readLine(); // "str" read the next line.
				sc = new Scanner(str); // "sc" cut of the pixels in "str"
				while(j<columns){
					if(str != null){
						this.pict[i][j] = sc.nextShort(); // We fill our matrix.
					}
					j++;

				}	
			}
			sc.close(); // We close all the methods.
			br.close();
			fr.close();
		}
		catch(IOException ex) { // If there is an error.
			System.out.print("Error reading file\n" + ex);
			System.exit(2);
		}
	}

	/** This function allows us to write a document in an address. In our case, it will be a picture. 
	 If any problem come to use in the lecture of the address of the document, thanks to "try" and"catch", the program
	 is stopping and an error message is displayed.
	 **/
	public void writeFile(String fileName, short pict[][]){
		try {
			FileWriter fw = new FileWriter(fileName); // We use the methods "FileWrinter" and "PrintWriter"
			PrintWriter outs = new PrintWriter(fw);
			outs.println("Header: " + pict[0].length + " " + pict.length); // We write the header.
			for (int i = 0; i < pict.length; i++){
				for (int j = 0; j < pict[i].length; j++){
					outs.print(pict[i][j] + " "); // Thanks two "for", we write all the pixels. 
				}
				outs.println(); // We come back to the line.
			}
			outs.close(); // We close all the methods.
			fw.close();
		}
		catch(IOException ex) { // If there is an error.
			System.out.print("Error writing file\n" + ex);
		}
	}

	/** This function makes a rotation of all the elements of a matrix (in our case, all the pixels).
	 Once it's finish, we use the function "WriteFile" to write our new picture in the address we input.
	 **/
	public void rotate90Pict(String fileName){
		short [][] pict2 = new short [columns][rows]; // We create a new matrix, by inverting the number of columns and rows.
		int a = 0;
		int b = rows - 1;
		for(int i = 0; i < rows; i++){ // We use two for to fill our new matrix.
			for(int j = 0; j < columns; j++){
				pict2[a][b] = pict[i][j];
				a++;
			}
			b--;
			a = 0;
		}
		writeFile(fileName, pict2); // We print our new picture.
	}
	
	/** This function allows use to change all the elements of our matrix, by the average of this element 
	 with all this neighborhoods. For this, we need to be careful of the number of neighborhoods that the element have.
	 For the 4 corners, only 3 neighborhoods.
	 For the side, 6 neighborhoods.
	 Finally, for the middle 8 neighborhoods.
	 Until the number known, we input the element and them neighborhoods into an array, then, we just need to make the average.
	 **/
	public void ave3_3(String fileName){
		short [][] pict2 = new short [rows][columns]; // We create a new matrix full of 0, with the same size of our picture.
		short sum = 0;
		for (i = 0; i < rows; i++){ // We use two "for" to generate all the element of our matrix.
			for (j = 0; j < columns; j++){

				if(i != 0 && j != 0 && i != rows - 1 && j != columns - 1){ // if(we are in the middle).
					short arr [] = neighbour9(i, j); // We call the function "neighbour9".
					for(int a = 0; a < 9; a++){
						sum = (short) (arr[a] + sum); // We calculate the average.
					}
					pict2[i][j] = (short) (sum / 9);
					sum = 0;
				}

				else if((i == 0 || i == rows - 1) && (j == 0 || j == columns - 1)){ // else if( we are on the corner).
					short arr [] = neighbour4(i, j); // We call the function "neighbour4".
					for(int a = 0; a < 4; a++){
						sum = (short) (arr[a] + sum); // We calculate the average.
					}
					pict2[i][j] = (short) (sum / 4);
					sum = 0;
				}
				else{ // else(if we don't are on the corner and not on the middle).
					short arr [] = neighbour6(i, j); // We call the function "neighbour6".
					for(int a = 0; a < 6; a++){
						sum = (short) (arr[a] + sum); // We calculate the average.
					}
					pict2[i][j] = (short) (sum / 6);
					sum = 0;
				}
			}
		}
		writeFile(fileName, pict2); // We print our new picture.
	}
	
	/** This function allows use to change all the elements of our matrix, by the median of this element 
	 with all this neighborhoods. For this, we need to be careful of the number of neighborhoods that the element have.
	 For the 4 corners, only 3 neighborhoods.
	 For the side, 6 neighborhoods.
	 Finally, for the middle 8 neighborhoods.
	 Until the number known, we input the element and them neighborhoods into an array, then, we just need to make the median.
	 **/
	public void mode3_3(String fileName){
		short [][] pict3 = new short [rows][columns]; // We create a new matrix full of 0, with the same size of our picture.
		for (i = 0; i < rows; i++){ // We use two "for" to generate all the element of our matrix.
			for (j = 0; j < columns; j++){

				if(i != 0 && j != 0 && i != rows - 1 && j != columns - 1){ // if(we are in the middle).
					short arr [] = neighbour9(i, j); // We call the function "neighbour9".
					bubbleSort(arr); // We sort our array.
					pict3[i][j] = arr[5]; // If the size of the array is odd, median = number of the element at the middle place (here 5).
				}

				else if((i == 0 || i == rows - 1) && (j == 0 || j == columns - 1)){ // else if( we are on the corner).
					short arr [] = neighbour4(i, j); // We call the function "neighbour4".
					bubbleSort(arr); // We sort our array.
					pict3[i][j] = (short) ((arr[1] + arr[2])/ 2); // If the size of the array is even, median = all the number between the two element at the middle place (here 1 and 2) (We take the average by default).
				}
				else{ // else(if we don't are on the corner and not on the middle).
					short arr [] = neighbour6(i, j); // We call the function "neighbour6".
					bubbleSort(arr); // We sort our array.
					pict3[i][j] = (short) ((arr[2] + arr[3])/ 2); // If the size of the array is even, median = all the number between the two element at the middle place (here 2 and 3) (We take the average by default).
				}
			}
		}

		writeFile(fileName, pict3); // We print our new picture.
	}

	/** This function allows use to input into an array, all the neighborhood of an element which is on the middle.
	 */
	private short [] neighbour9(int x, int y){
		int a = 0;
		x = i - 1;
		y = j - 1;
		short [] arr = new short [9]; // We create a new array with the size 9 (the number wee need).
		for(int b = 0; b < 3; b++){ // Two "for" to fill our array.
			for(int c = 0; c < 3; c++){
				arr[a] = pict[x][y];
				y++;
				a++;
			}
			y = y - 3;
			x++;
		}
		return arr;
	}

	/** This function allows use to input into an array, all the neighborhood of an element which is on the corner.
	 */
	private short [] neighbour4(int x, int y){
		int a = 0;
		short [] arr = new short [4]; // We create a new array with the size 4 (the number wee need).
		if(x == 0 && y == 0){ //if(we are on the corner on the top, to the side left).
			for(int b = 0; b < 2; b++){ // Two "for" to fill our array.
				for(int c = 0; c < 2; c++){
					arr[a] = pict[x][y];
					y++;
					a++;
				}
				y = y - 2;
				x++;
			}
			x = 1;
			y = 1;
			a = 0;
		}
		if(x == 0 && y == columns - 1){ //if(we are on the corner on the top, to the side right).
			for(int d = 0; d < 2; d++){ // Two "for" to fill our array.
				for(int e = 0; e < 2; e++){
					arr[a] = pict[x][y];
					y--;
					a++;
				}
				y = y + 2;
				x++;
			}
			a = 0;
			x = 1;
			y = 1;
		}
		if(x == rows - 1 && y == 0){ //if(we are on the corner on the down, to the side left).
			for(int f = 0; f < 2; f++){ // Two "for" to fill our array.
				for(int g = 0; g < 2; g++){
					arr[a] = pict[x][y];
					y++;
					a++;
				}
				y = 0;
				x--;
			}
			a = 0;
		}
		if(x == rows - 1 && y == columns - 1){ //if(we are on the corner on the down, to the side left).
			for(int h = 0; h < 2; h++){ // Two "for" to fill our array.
				for(int i = 0; i < 2; i++){
					arr[a] = pict[x][y];
					y--;
					a++;
				}
				y = y + 2;
				x--;
			}
			a = 0;
		}
		return arr;
	}

	/** This function allows use to input into an array, all the neighborhood of an element which is on the side and not on the corner.
	 */
	private short [] neighbour6(int x, int y){
		int a = 0;
		short [] arr = new short [6]; // We create a new array with the size 4 (the number wee need).

		if(x == 0){ //if(we are on the side up).
			y--;
			for(int b = 0; b < 2; b++){ // Two "for" to fill our array.
				for(int c = 0; c < 3; c++){
					arr[a] = pict[x][y];
					y++;
					a++;
				}
				y = y - 3;
				x++;
			}
			a = 0;
			x =0;
			y = 1;

		}

		if(x == rows - 1){ //if(we are on the side down).
			y--;
			for(int d = 0; d < 2; d++){ // Two "for" to fill our array.
				for(int e = 0; e < 3; e++){
					arr[a] = pict[x][y];
					y++;
					a++;
				}
				y = y - 3;
				x--;
			}
			a = 0;
			x = rows - 1;
			y = 1;

		}

		if(y == 0){ //if(we are on the side left).
			x--;
			for(int f = 0; f < 3; f++){ // Two "for" to fill our array.
				for(int g = 0; g < 2; g++){
					arr[a] = pict[x][y];
					y++;
					a++;
				}
				y = 0;
				x++;
			}
			a = 0;
			y = 0;
			x = 1;

		}

		if(y == columns - 1){ //if(we are on the side right).
			x--;
			for(int h = 0; h < 3; h++){ // Two "for" to fill our array.
				for(int i = 0; i < 2; i++){
					arr[a] = pict[x][y];
					y--;
					a++;
				}
				y = columns - 1;
				x++;
			}
			a = 0;
			y = columns - 1;
			x = 1;	
		}
		return arr;
	}

	/** This function allows use to sort our array, because we need to calculate the median.
	 */
	private short [] bubbleSort(short arr []){
		short temp = 0;
		for(int a = 0; a < arr.length; a++){
			for(int b = a; b < arr.length; b ++){
				if(arr[a] > arr[b]){ // If the element is bigger than the second.
					temp = arr[a]; // We use "temp" to switch the two elements.
					arr[a] = arr[b];
					arr[b] = temp;
				}
			}
		}
		return arr;
	}	
	
	/** This function allows use to print only the part we want of the picture.
	 */
	public void sub_picture (String fileName,int x1,int y1,int x2,int y2){
		rows = y2 - y1 + 1; // We define or new size of rows, according to "y1" and "y2".
		columns = x2 - x1 + 1; // We define or new size of rows, according to "x1" and "x2".
		int m = 0;
		int n = 0;
		short [][] pict4 = new short [rows][columns]; // We create a new matrix full of 0, with the size we defined.
		for(int a = y1 - 1; a < y2; a++){ // Two "for" to fill our new matrix.
			for(int b = x1 - 1; b < x2; b++){
				pict4[m][n] = pict[a][b];
				n++;
			}
			n = 0;
			m++;
		}
		writeFile(fileName, pict4); // We print our new picture.
	}
}
