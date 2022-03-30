package com.oyinkansola;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class PhoneNumberSummaryService {
	private static final Pattern MTN = Pattern.compile("^0(70(3|6|25|26|4)|8(0[36]|1[0346])|9(0[36]|1[36]))\\d+");
	private static int numberOfMTN;
	private static final Pattern AIRTEL = Pattern.compile("^0(70([18])|8(02|08|12)|9(0[1247]|12))\\d+");
	private static int numberOfAirtel;
	private static final Pattern GLOBACOM = Pattern.compile("^0(705|8(0[57]|1[15])|9[01]5)\\d+");
	private static int numberOfGlobacom;
	private static final Pattern NINE_MOBILE = Pattern.compile("^0(8(09|17|18)|90[98])\\d+");
	private static int numberOf9Mobile;
	private static final Pattern MTEL = Pattern.compile("^0804\\d+");
	private static int numberOfMTEL;

	private static void updateWithPhoneNumber(String phoneNumber) {
		if (MTN.matcher(phoneNumber).matches()) {
			numberOfMTN++;
		} else if (AIRTEL.matcher(phoneNumber).matches()) {
			numberOfAirtel++;
		} else if (GLOBACOM.matcher(phoneNumber).matches()) {
			numberOfGlobacom++;
		} else if (NINE_MOBILE.matcher(phoneNumber).matches()) {
			numberOf9Mobile++;
		} else {
			numberOfMTEL++;
		}
	}

	private static String generateSummary() {
		return "MTN: " + numberOfMTN +
			"\nAIRTEL: " + numberOfAirtel +
			"\nGLOBACOM: " + numberOfGlobacom +
			"\n9MOBILE: " + numberOf9Mobile +
			"\nMTEL: " + numberOfMTEL;
	}

	public static void main(String[] args) {
		try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/PhoneNumbers.txt"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/Summary.txt"))) {
			String phoneNumber;
			while ((phoneNumber = reader.readLine()) != null) {
				updateWithPhoneNumber(phoneNumber);
			}
			String summary = generateSummary();
			writer.write(summary);
			System.out.println("Summary can be found in the resources folder.\n" + summary);
		} catch (IOException e) {
			System.out.println("IOexception: " + e.getMessage());
		}
	}
}
