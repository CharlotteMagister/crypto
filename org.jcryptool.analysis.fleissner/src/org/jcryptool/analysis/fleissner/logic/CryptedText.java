package org.jcryptool.analysis.fleissner.logic;
import java.util.ArrayList;

import org.jcryptool.analysis.fleissner.Activator;
//import java.util.logging.Logger;
import org.jcryptool.core.logging.utils.LogUtil;;


public class CryptedText {
	
	private ArrayList<char[][]> text = new ArrayList<char[][]>();
	private String line;
	
	/**
	 * loads given text in needed format for encryption and decryption
	 * 
	 * @param textInLine
	 * @param isPlaintext information if text has to be encrypted before saving in two-dimensional array and if random letters
	 *                    have to be inserted 
	 * @param templateLength
	 * @param coordinates
	 * @param fg the object fleissnerGrille that manages operations in the grille
	 */
	public void load(String textInLine, boolean isPlaintext, int templateLength, int[] coordinates, FleissnerGrille fg)
	{
		try {
			
			line=textInLine;
			int textLength = line.length();

			char[][] textPart;
			int k=0;
			
			if (isPlaintext) {
				
				int letterFields;
				double randomLetters;
				
//				  calculate number of random letters to be inserted
				if (templateLength%2==0) {
					letterFields = (int) (Math.pow(templateLength, 2));
				}
				else {
					letterFields = (int) (Math.pow(templateLength, 2)-1);
				}

				randomLetters = letterFields-(textLength%letterFields);
				
				if (randomLetters!=letterFields) {
					LogUtil.logInfo(Activator.PLUGIN_ID, randomLetters+" random letters inserted");
					for (int i=textLength;i<textLength+randomLetters;i++) {
						
						line += (char) ('A' + 26*Math.random());
					}
					LogUtil.logInfo(Activator.PLUGIN_ID, "Modified plaintext: "+line);
				}
				else {
					LogUtil.logInfo(Activator.PLUGIN_ID, "no random letters inserted");
				}
//				encrypt input text if plaintext (method is 'encrypt')
				line = fg.encryptText(line, coordinates);
				LogUtil.logInfo(Activator.PLUGIN_ID, "plaintext successfully encrypted");
			}
			
			while (k <line.length()) {
//				write text into grille
				textPart = new char[templateLength][templateLength];

				for (int y=0; y< templateLength; y++) {
					for (int x = 0; x < templateLength; x++) {
						if (k!=line.length()) {
							
							textPart[x][y] = line.charAt(k);
							k++;
						}
						else {
							break;
						}
					}
				}
				text.add(textPart);
			}
				
		} catch (Exception e) {
			LogUtil.logError(Activator.PLUGIN_ID, "Text konnte nicht geladen werden", e, true);
		}
	}
	
	public ArrayList<char[][]> getText()
	{
		return this.text;
	}
	
	@Override
	public String toString() {
		int size = this.getText().get(0).length;
		String s="\n\nCrypted Text:\n";
		for(char[][]textPart:text) {
			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					s+=textPart[x][y];
				}
				s+="\n";
			}
		}	
		return s;
	}
}
