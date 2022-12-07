package srb;

/*
 * BEFORE RUNNING:
 * ---------------
 * 1. If not already done, enable the Google Sheets API
 *    and check the quota for your project at
 *    https://console.developers.google.com/apis/api/sheets
 * 2. Install the Java client library on Maven or Gradle. Check installation
 *    instructions at https://github.com/google/google-api-java-client.
 *    On other build systems, you can add the jar files to your project from
 *    https://developers.google.com/resources/api-libraries/download/sheets/v4/java
 */
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class SheetsExample {
  public static void main(String args[]) throws IOException, GeneralSecurityException {
    // The spreadsheet to request.
    String spreadsheetId = "1aD7IasUGozCAgQsZd0PnmHNcbTC_opX9SPZgdn74qVE"; // TODO: Update placeholder value.

    // The ranges to retrieve from the spreadsheet.
    List<String> ranges = new ArrayList<>(); // TODO: Update placeholder value.

    // True if grid data should be returned.
    // This parameter is ignored if a field mask was set in the request.
    boolean includeGridData = false; // TODO: Update placeholder value.

    Sheets sheetsService = createSheetsService();
    Sheets.Spreadsheets.Get request = sheetsService.spreadsheets().get(spreadsheetId);
    request.setRanges(ranges);
    request.setIncludeGridData(includeGridData);

    Spreadsheet response = request.execute();

    // TODO: Change code below to process the `response` object:
    System.out.println(response);
  }

  public static Sheets createSheetsService() throws IOException, GeneralSecurityException {
    HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

    // TODO: Change placeholder below to generate authentication credentials. See
    // https://developers.google.com/sheets/quickstart/java#step_3_set_up_the_sample
    //
    // Authorize using one of the following scopes:
    //   "https://www.googleapis.com/auth/drive"
    //   "https://www.googleapis.com/auth/drive.file"
    //   "https://www.googleapis.com/auth/drive.readonly"
    //   "https://www.googleapis.com/auth/spreadsheets"
    //   "https://www.googleapis.com/auth/spreadsheets.readonly"
    GoogleCredential credential = null;

    return new Sheets.Builder(httpTransport, jsonFactory, credential)
        .setApplicationName("Google-SheetsSample/0.1")
        .build();
  }
}