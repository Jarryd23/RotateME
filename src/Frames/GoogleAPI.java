 package Frames; // SAVED FOR FUTURE USE
//
//import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
//import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.util.store.FileDataStoreFactory;
//import com.google.api.client.util.DateTime;
//
//import com.google.api.services.calendar.CalendarScopes;
//import com.google.api.services.calendar.model.*;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.List;
//import java.util.TimeZone;
//
//public class GoogleAPI {
//    /** Application name. */
//    private static final String APPLICATION_NAME =
//        "Google Calendar API Java Quickstart";
//
//    /** Directory to store user credentials for this application. */
//    private static final java.io.File DATA_STORE_DIR = new java.io.File(
//        System.getProperty("user.home"), ".credentials/3/calendar-java-quickstart");
//
//    /** Global instance of the {@link FileDataStoreFactory}. */
//    private static FileDataStoreFactory DATA_STORE_FACTORY;
//
//    /** Global instance of the JSON factory. */
//    private static final JsonFactory JSON_FACTORY =
//        JacksonFactory.getDefaultInstance();
//
//    /** Global instance of the HTTP transport. */
//    private static HttpTransport HTTP_TRANSPORT;
//
//    /** Global instance of the scopes required by this quickstart.
//     *
//     * If modifying these scopes, delete your previously saved credentials
//     * at ~/.credentials/calendar-java-quickstart
//     */
//    private static final List<String> SCOPES =
//        Arrays.asList(CalendarScopes.CALENDAR);
//
//    static {
//        try {
//            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
//        } catch (Throwable t) {
//            t.printStackTrace();
//            System.exit(1);
//        }
//    }
//
//    /**
//     * Creates an authorized Credential object.
//     * @return an authorized Credential object.
//     * @throws IOException
//     */
//    public static Credential authorize() throws IOException {
//        // Load client secrets.
//        InputStream in =
//            GoogleAPI.class.getResourceAsStream("/client_secret.json");
//        GoogleClientSecrets clientSecrets =
//            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//
//        // Build flow and trigger user authorization request.
//        GoogleAuthorizationCodeFlow flow =
//                new GoogleAuthorizationCodeFlow.Builder(
//                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
//                .setDataStoreFactory(DATA_STORE_FACTORY)
//                .setAccessType("offline")
//                .build();
//        Credential credential = new AuthorizationCodeInstalledApp(
//            flow, new LocalServerReceiver()).authorize("user");
//        System.out.println(
//                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
//        return credential;
//    }
//    
//    
//
//    public void addEvent(String barcode, String prodName, String Batch, String date, com.google.api.services.calendar.Calendar service)
//    {
//
//        Event event = new Event()
//                .setSummary(prodName + " is Expiring.")
//                .setDescription(prodName+" ("+barcode+") Batch Number "+Batch +" is expiring today");
//        
//        DateTime startDate = new DateTime(date+"T05:00:00-00:00");
//        EventDateTime start = new EventDateTime()
//                .setDateTime(startDate).setTimeZone("Africa/Johannesburg");
//        event.setStart(start);
//        
//        DateTime endDateTime = new DateTime(date+"T09:00:00-00:00");
//        EventDateTime end = new EventDateTime()
//                .setDateTime(endDateTime)
//                .setTimeZone("Africa/Johannesburg");
//        event.setEnd(end);
//        
//        String calendarId = "primary";
//        
//        try{
//            service.events().insert(calendarId, event).execute();
//        }catch (IOException e)
//        {
//            System.out.println(e);
//        }
//
//    }
//    
//    public void deleteEvent(com.google.api.services.calendar.Calendar service, String summary, String description) throws IOException
//    {
//        System.out.println(summary +" "+ description);
//        String pageToken = null;
//        do {
//            Events events = service.events().list("primary").setPageToken(pageToken).execute();
//            List<Event> items = events.getItems();
//            for (Event event : items) {
//                if (event.getSummary().equalsIgnoreCase(summary) && event.getDescription().equalsIgnoreCase(description))
//                {
//                    service.events().delete("primary", event.getId()).execute();
//                }
//            }
//            pageToken = events.getNextPageToken();
//        } while (pageToken != null);
//
//    }
//    
//    public void deleteEventProd(com.google.api.services.calendar.Calendar service, String summary, String description) throws IOException
//    {
//        System.out.println(summary +" "+ description);
//        String pageToken = null;
//        do {
//            Events events = service.events().list("primary").setPageToken(pageToken).execute();
//            List<Event> items = events.getItems();
//            for (Event event : items) {
//                if (event.getDescription().contains(description))
//                {
//                    service.events().delete("primary", event.getId()).execute();
//                }
//            }
//            pageToken = events.getNextPageToken();
//        } while (pageToken != null);
//
//    }
//    
//    
//    public static com.google.api.services.calendar.Calendar
//        getCalendarService() throws IOException {
//        Credential credential = authorize();
//        return new com.google.api.services.calendar.Calendar.Builder(
//                HTTP_TRANSPORT, JSON_FACTORY, credential)
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//    }
//
//    public static void main(String[] args) throws IOException {
//        // Build a new authorized API client service.
//        // Note: Do not confuse this class with the
//        //   com.google.api.services.calendar.model.Calendar class.
//        com.google.api.services.calendar.Calendar service =
//            getCalendarService();
//        
//        
//        
//        // List the next 10 events from the primary calendar.
//        DateTime now = new DateTime(System.currentTimeMillis());
//        Events events = service.events().list("primary")
//            .setMaxResults(10)
//            .setTimeMin(now)
//            .setOrderBy("startTime")
//            .setSingleEvents(true)
//            .execute();
//        
//        List<Event> items = events.getItems();
//        if (items.size() == 0) {
//            System.out.println("No upcoming events found.");
//        } else {
//            System.out.println("Upcoming events");
//            for (Event event : items) {
//                DateTime start = event.getStart().getDateTime();
//                if (start == null) {
//                    start = event.getStart().getDate();
//                }
//                System.out.printf("%s (%s)\n", event.getSummary(), start);
//            }
//        }
//    }

//} 