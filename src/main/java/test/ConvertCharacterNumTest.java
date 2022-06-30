package test;

import constant.Constants;

public class ConvertCharacterNumTest {

	public static void main(String[] args) {
		String character = Constants.characterSelectScreenOrder[0];
		int characterNumber = Constants.convertCharacterNum(character);
		
		System.out.println(character + characterNumber);
	}

}
