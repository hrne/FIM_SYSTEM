package com.modle.util;

import org.json.*;

public class JsonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
		test2();
		test3();
		test4();
	}

	public static void test1() {
		JSONObject j;
		try {
			String tmp = "{\"Data\":{\"Name\":\"MichaelChan\",\"Email\":\"XXXX@XXX.com\",\"Phone\":[1234567,0911123456]}}";

			j = new JSONObject(tmp);

			Object jsonOb = j.getJSONObject("Data");

			System.out.println(jsonOb);

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

	public static void test2() {
		JSONObject j;
		try {
			String tmp = "{\"Data\":{\"Name\":\"MichaelChan\",\"Email\":\"XXXX@XXX.com\",\"Phone\":[1234567,0911123456]}}";

			j = new JSONObject(tmp);

			Object jsonOb = j.getJSONObject("Data").get("Name");

			System.out.println(jsonOb);

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void test3() {
		JSONObject j;
		try {
			String tmp = "{\"Data\":{\"Name\":\"MichaelChan\",\"Email\":\"XXXX@XXX.com\",\"Phone\":[1234567,0911123456]}}";

			j = new JSONObject(tmp);

			Object jsonOb = j.getJSONObject("Data").getJSONArray("Phone");

			System.out.println(jsonOb);

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void test4() {
		JSONObject j;
		try {
			String tmp = "{\"Data\":{\"Name\":\"MichaelChan\",\"Email\":\"XXXX@XXX.com\",\"Phone\":[1234567,0911123456]}}";

			j = new JSONObject(tmp);

			Object jsonOb = j.getJSONObject("Data").getJSONArray("Phone").get(0);

			System.out.println(jsonOb);

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

}
