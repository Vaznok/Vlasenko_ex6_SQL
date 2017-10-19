package tasks.anagramm;

import java.io.IOException;

public class Client {
	public static void main(String[] args) throws IOException {
		String inputString = Utils.readString();
		String anagram = AnagrammProcessor.makeAnagramm(inputString);
		System.out.println(anagram);
	}
}
