package httpJavaClient;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;

import java.net.URL;

import org.json.JSONObject;

import utility.ExcelUtils;

public class HttpUrlConnectionDemo {

	public static void main(String[] args) throws IOException {

		String projDir = System.getProperty("user.dir");
		String excelPath = projDir+"/test data/HttpJavaClientTestData.xlsx";
		String endPoint = "https://api.nasa.gov/planetary/apod";

		ExcelUtils excel = new ExcelUtils(excelPath, "Sheet1");

		int rows = excel.getRowCount();

		for (int i = 1; i <= rows; i++) {

			URL url = new URL(endPoint + "?date=" + excel.getCellData(i, 0) + "&api_key=" + excel.getCellData(i, 1));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("GET");

			connection.setRequestProperty("Content-Type", "application/json");

			connection.setDoOutput(true);

			int respCode = connection.getResponseCode();

			if (respCode == 200) {

				System.out.println("Response is: " + connection.getResponseMessage());

			} else {

				System.out.println("Response code is not 200");

			}

			BufferedReader br = null;

			String responseBody = "";

			if (connection.getResponseCode() == 200) {

				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				String strCurrentLine;

				while ((strCurrentLine = br.readLine()) != null) {

					responseBody = responseBody + strCurrentLine;

				}

			} else {

				br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));

				String strCurrentLine;

				while ((strCurrentLine = br.readLine()) != null) {

					responseBody = responseBody + strCurrentLine;

				}

			}

			responseBody = responseBody.replace("\r", "");

			responseBody = responseBody.replace("\t", "");

			responseBody = responseBody.replace("\n", "");

			System.out.println("Response is: " + responseBody);

			JSONObject jsonReader = new JSONObject(responseBody);

			Object title = jsonReader.get("title");

			System.out.println("Title is : " + title);

			excel.writeDataToCell(i, 2, title.toString());

		}

	}

}
