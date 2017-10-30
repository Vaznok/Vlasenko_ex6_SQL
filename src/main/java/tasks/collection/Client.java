package tasks.collection;

import java.util.concurrent.ExecutionException;

import static tasks.collection.UniqueCharactersHandler.getUniqueCharactersCounts;

public class Client {
    public static void main(String[] args) throws ExecutionException {
        System.out.println(getUniqueCharactersCounts("Hello World!"));
        System.out.println(getUniqueCharactersCounts("Hello World!"));
        System.out.println(getUniqueCharactersCounts("Hello World!"));

        System.out.println(getUniqueCharactersCounts("Second Hello World!"));
        System.out.println(getUniqueCharactersCounts("Second Hello World!"));
        System.out.println(getUniqueCharactersCounts("Second Hello World!"));

        System.out.println(getUniqueCharactersCounts("Third Hello World!"));
        System.out.println(getUniqueCharactersCounts("Third Hello World!"));
        System.out.println(getUniqueCharactersCounts("Third Hello World!"));

        System.out.println(getUniqueCharactersCounts("mo13@#$5 - *-%#$#$3"));
        System.out.println(getUniqueCharactersCounts("mo13@#$5 - *-%#$#$3"));
        System.out.println(getUniqueCharactersCounts("mo13@#$5 - *-%#$#$3"));
    }
}
