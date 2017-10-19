package tasks.anagramm;

import java.util.StringTokenizer;

public class AnagrammProcessor {	
	public static String makeAnagramm (String str) {
		if (str != null && !str.isEmpty()) {
			StringBuilder strBuilder = new StringBuilder();
			StringTokenizer st = new StringTokenizer(str);
			while(st.hasMoreTokens()) {
				strBuilder = strBuilder.append(reverseLettersIn(st.nextToken())).append(" ");				
			}
			return strBuilder.toString().trim();
		} else {
			throw new IllegalArgumentException("Given argument mustn't been 'null' or empty");
		}
	}
	
	private static String reverseLettersIn (String word) {
		char[] chars = word.toCharArray();
		int indexBegin = 0;
		int indexEnd = chars.length - 1;
		
		while(indexEnd > indexBegin) {
            if(!Character.isLetter(chars[indexBegin])) {
                indexBegin += 1;                
                continue;
            }
            if(!Character.isLetter(chars[indexEnd])) {
                indexEnd -= 1;               
                continue;
            }
            char tmp = chars[indexBegin];
            chars[indexBegin] = chars[indexEnd];
            chars[indexEnd] = tmp;

            indexBegin += 1;
            indexEnd -= 1;            
        }
		new StringBuilder().reverse();
        return new String(chars);
	}
	
}
