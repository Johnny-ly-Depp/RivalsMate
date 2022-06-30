package constant;

public class Constants {
	public static final String[] allCharactersOfficialOrder = {"random", "zetterburn", "orcane", "wrastor", "kragg", "forsburn", "maypul", "absa", "etalus", "ranno", "clairen", "sylvanos", "elliana", "oriandsein", "shovelknight", "mollo", "hodan", "pomme", "olympia"};
	public static final String[] characterSelectScreenOrder = {"mollo",  "clairen", "forsburn", "zetterburn",  "random", "wrastor", "absa", "elliana", "pomme", "olympia", "sylvanos", "maypul", "kragg", "oriandsein", "shovelknight", "orcane", "etalus", "ranno","hodan"};
	
	public static int convertCharacterNum(String characterName) {
		int i = 0;
		for(String character: allCharactersOfficialOrder) {
			if(character.equals(characterName)) {
				return i;
			}
			i++;
		}
		return i;
	}
}
