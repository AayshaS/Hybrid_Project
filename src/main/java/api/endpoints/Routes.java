package api.endpoints;

public class Routes {

    public static String baseurl="https://gorest.co.in/";
    public static String posturl=baseurl+"public/v2/users";
    public static String geturl=baseurl+"public/v2/users/{userid}";
    public static String updateurl=baseurl+"public/v2/users/{userid}";
    public static String deleteurl=baseurl+"public/v2/users/{userid}";


    //	WeatherAPI.com rapid
    public static String baseUri="https://weatherapi-com.p.rapidapi.com/";
    public static String realtime=baseUri+"current.json";
    public static String forecast=baseUri+"forecast.json";
    public static String timeZone=baseUri+"timezone.json";

    //Moke API'S
   public static String employeeBaseUrl="http://localhost:3000/Employee/";
   public static String mokeGetUrl=employeeBaseUrl+"{userid}";
    public static String mokeUpdateUrl=employeeBaseUrl+"{userid}";
    public static String mokeDeleteUrl=employeeBaseUrl+"{userid}";
}
