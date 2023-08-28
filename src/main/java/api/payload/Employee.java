package api.payload;

public class Employee {

    public static String empdata()
    {
        String data=" {\r\n"
                + "      \"empname\": \"aaysha\",\r\n"
                + "      \"desig\": \"manager\",\r\n"
                + "      \"hobbies\": [\r\n"
                + "        \"readingbooks\",\r\n"
                + "        \"travel\"\r\n"
                + "      ]\r\n"
                + "}";

        return data;
    }
    public static String empdata1(){
        String data="{\"name\":\"sonam\", \r\n"
                + "\"email\":\"sonamsid08@gmail.com\", \r\n"
                + "\"gender\":\"female\", \r\n"
                + "\"status\":\"active\"}";
        return data;
    }
}
