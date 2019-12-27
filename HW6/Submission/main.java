// The following imports are used for file IO and other functionalities for the code

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class main
{
	public static void main(String[] args) throws URISyntaxException
	{
		String[] words = new String[100];
		words = main.readFile("dict.txt", 100);
		Probing probe = new Probing();
		Probing.returnValuesFromMethod returnedValues = null;
		Scanner scan = new Scanner(System.in);
		// I have provided a interactive code so that linear probing and quadratic probing can be used
		System.out.println("Select the method you want to use");
		System.out.println("1. Linear Probing");
		System.out.println("2. Quadratic Probing");
		int choice1 = scan.nextInt();
		switch (choice1)
		{
			case 1:
				System.out.println("We are using linear probing");
				System.out.println("Do you want to increase the number of words");
				// Here press yes if you want to increase the number of words ( I increased 20 words so that it affects the table size
				System.out.println("1. Yes");
				System.out.println("2. No");
				int choice2 = scan.nextInt();
				switch (choice2)
				{
					case 1:
						// You can change the number of words to be increased. I used 20 since increasing 10 words would not have increased the table size
						String[] newWords = main.readFile("more_words.txt", 20);
						// The following code is used to create the new array from old and new words combined
						String[] totalWords = Arrays.copyOf(words, words.length + newWords.length);
						System.arraycopy(newWords, 0, totalWords, words.length, newWords.length);
						returnedValues = probe.linearProbing(totalWords, 53);
						break;
					case 2:
						returnedValues = probe.linearProbing(words, 53);
						break;
				}
				break;
			case 2:
				System.out.println("We are using quadratic probing");
				System.out.println("Do you want to increase the number of words");
				System.out.println("1. Yes");
				System.out.println("2. No");
				choice2 = scan.nextInt();
				switch (choice2)
				{
					case 1:
						// I had to increase 20 words since I was taking the next prime number
						String[] newWords = main.readFile("more_words.txt", 20);
						String[] totalWords = Arrays.copyOf(words, words.length + newWords.length);
						System.arraycopy(newWords, 0, totalWords, words.length, newWords.length);
						returnedValues = probe.quadraticProbing(totalWords, 53);
						break;
					case 2:
						returnedValues = probe.quadraticProbing(words, 53);
						break;
				}
				break;
		}
		// The following is the hash table and table size after inserting the values
		String[] hashTable = returnedValues.hashTable;
		// The following code is used to chheck if a given word is in the dictionary
		int tableSize = returnedValues.tableSize;
		System.out.println("The final table size is " + tableSize);
		System.out.println("Please enter the words you want to check in the dictionary");
		while (scan.hasNext())
		{
			String newWord = scan.nextLine().toLowerCase();
			if (newWord.equals("close"))
			{
				return;
			}
			if (newWord.equals("\n") || newWord.equals(""))
			{
				continue;
			}
			int hashValue = hashFunction(newWord, tableSize);
			if (choice1 == 1)
			{
				while (true)
				{
					if (hashTable[hashValue].equals(newWord))
					{
						System.out.println("The word " + newWord + " is in the dictionary");
						break;
					}
					else if (hashTable[hashValue].equals(""))
					{
						System.out.println("The word " + newWord + " was not found in the dictionary, please enter another word.");
						break;
					}
					else
					{
						hashValue = (hashValue + 1) % tableSize;
					}
				}
			}
			else if (choice1 == 2)
			{
				int newHashValue = hashValue;
				int count = 1;
				while (true)
				{
					if (hashTable[newHashValue].equals(newWord))
					{
						System.out.println("The word " + newWord + " is in the dictionary");
						break;
					}
					else if (hashTable[newHashValue].equals(""))
					{
						System.out.println("The word " + newWord + " was not found in the dictionary, please enter another word.");
						break;
					}
					else
					{
						newHashValue = (hashValue + count * count) % tableSize;
						count++;
					}
				}
			}
			System.out.print("Please enter the words you want to check in the dictionary. ");
			System.out.println("Else write close to exit the program");
		}
	}

	/**
	 * This function is used to read the file
	 *
	 * @param fileName   The name of the file
	 * @param numOfWords The number of words to be read
	 * @return String array containing all those words
	 * @throws URISyntaxException
	 */
	public static String[] readFile(String fileName, int numOfWords) throws URISyntaxException
	{
		URL path = ClassLoader.getSystemResource(fileName);
		if (path == null)
		{
			//The file was not found, insert error handling here
		}
		File file = new File(path != null ? path.toURI() : null);
		String[] words = new String[numOfWords];
		Scanner sc = null;
		try
		{
			sc = new Scanner(file);
		} catch (FileNotFoundException e)
		{
			System.out.println("The file was not found, please check.");
		}
		int count = 0;
		while (sc != null && sc.hasNextLine() && count < numOfWords)
		{
			words[count++] = sc.nextLine();
		}
		return words;
	}

	/**
	 * This is the third hash function mentioned in the slides on page 	15
	 *
	 * @param key       This is the value of the key to be hashed
	 * @param tableSize This is the size of the table
	 * @return the hash value
	 */
	public static int hashFunction(String key, int tableSize)
	{

		int hashValue = 0;
		// The hash function is used to convert strings to hash values
		for (int count = 0; count < key.length(); count++)
		{
			hashValue = 37 * hashValue + (int) key.charAt(count);
		}
		hashValue = hashValue % tableSize;
		if (hashValue < 0)
		{
			hashValue = hashValue + tableSize;
		}
		return hashValue;
	}

	public static class Probing
	{

		/**
		 * This is the function which implements linear probing
		 *
		 * @param words     The string of words
		 * @param tableSize The size of the table
		 * @return The created hash table and new table size after probing
		 */
		public returnValuesFromMethod linearProbing(String[] words, int tableSize)
		{
			String[] hashTable = new String[tableSize];
			int number_of_collisions = 0;
			// I am putting -1 as a value if the hashtable index if not filled
			for (int count1 = 0; count1 < tableSize; count1++)
			{
				hashTable[count1] = "";
			}
			for (int count1 = 0; count1 < words.length; count1++)
			{
				if (count1 >= tableSize / 2)
				{
					BigInteger b = new BigInteger(String.valueOf(2 * tableSize));
					tableSize = Math.toIntExact(Long.parseLong(b.nextProbablePrime().toString()));
					System.out.println("Increasing table size to " + tableSize);
					System.out.println("The collisions for table size " + tableSize + " are");
					return linearProbing(words, tableSize);
				}
				int hashValue = hashFunction(words[count1], tableSize);
				int nextHashValue = hashValue;
				while (true)
				{
					if (hashTable[nextHashValue].equals(""))
					{
						hashTable[nextHashValue] = words[count1];
						break;
					}
					else
					{
						number_of_collisions++;
						nextHashValue = (nextHashValue + 1) % tableSize;
						System.out.println("There was a collision for word \'" + words[count1] + "\' with hashValue " + hashValue + ". Now checking on index " + nextHashValue);
					}
				}
			}
			System.out.println("The number of collisions are " + number_of_collisions);
			return new returnValuesFromMethod(hashTable, tableSize);
		}

		/**
		 * This is the function which implements quadratic probing
		 *
		 * @param words     The string of words
		 * @param tableSize The size of the table
		 * @return The created hash table and new table size after probing
		 */
		public returnValuesFromMethod quadraticProbing(String[] words, int tableSize)
		{
			String[] hashTable = new String[tableSize];
			int numberOfCollisions = 0;
			// I am putting -1 as a value if the hashtable index if not filled
			for (int count1 = 0; count1 < tableSize; count1++)
			{
				hashTable[count1] = "";
			}
			for (int count1 = 0; count1 < words.length; count1++)
			{
				if (count1 >= tableSize / 2)
				{
					BigInteger b = new BigInteger(String.valueOf(2 * tableSize));
					tableSize = Math.toIntExact(Long.parseLong(b.nextProbablePrime().toString()));
					System.out.println("Increasing table size to " + tableSize + " and rehashing all the values.");
					System.out.println("The collisions for table size " + tableSize + " are");
					return quadraticProbing(words, tableSize);
				}
				int hashValue = hashFunction(words[count1], tableSize);
				int newHashValue = hashValue;
				int count2 = 1;
				while (true)
				{
					if (hashTable[newHashValue].equals(""))
					{
						hashTable[newHashValue] = words[count1];
						break;
					}
					else
					{
						newHashValue = (hashValue + count2 * count2) % tableSize;
						numberOfCollisions++;
						System.out.println("There was a collision for word '" + words[count1] + "' with hashValue " + hashValue + ". Now checking on index " + newHashValue);
						count2 = count2 + 1;
					}
				}
			}
			System.out.println("The number of collisions are " + numberOfCollisions);
			return new returnValuesFromMethod(hashTable, tableSize);
		}

		/**
		 * This class is used to return the values from the function
		 */
		public class returnValuesFromMethod
		{
			public String[] hashTable;
			public int tableSize;

			public returnValuesFromMethod(String[] value, int tableSize)
			{
				this.hashTable = value;
				this.tableSize = tableSize;
			}
		}
	}
}
