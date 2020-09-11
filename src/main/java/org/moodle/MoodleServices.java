package org.moodle;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

/**
* The MoodleServices program implements an interface to interact with Moodle
*
* @author  Jose Wuiler Pozzi
* @version 0.2.5
* @since   2020-08-01
*/
public class MoodleServices {

    private String url;
    private String token;
    private String serviceType; 
    private String function;

    public MoodleServices() {
    }

    public MoodleServices(String url, String token, String serviceType, String function) {
        this.url = url;
        this.token = token;
        this.serviceType = serviceType;
        this.function = function;
    }    

    public MoodleServices(String url, String token, Map<String,String> paramsApi) {        
        this.url = url;
        this.token = token;

        paramsApi.forEach(new BiConsumer<String, String> () {
            @Override
            public void accept(String k, String v) {
                System.out.println("Key: " + k + " Value: " + v);
                if (k.equals("wsfunction")) {
                    setFunction(v);
                }
                if (k.equals("moodlewsrestformat")) {
                    setServiceType(v);
                }
            }
        });

    }    

    private String produce(Map<String,Object> paramsApi, Map<String,Object> params) {
        //vamos a verificar para agregar controles de null;
        return Unirest.get(this.getUrl())
        .queryString("wstoken", this.getToken())
        .queryString(paramsApi)
        .queryString(params)
        .asJson().getBody().toString();

    }

    private JSONObject produceJSON(Map<String,Object> paramsApi, Map<String,Object> params) {

        return Unirest.get(this.getUrl())
        .queryString("wstoken", this.getToken())
        .queryString(paramsApi)
        .queryString(params)
        .asJson().getBody().getObject();

    }
        
    private String produce(String function, String format, Map<String,Object> params) {

        Map<String,Object> paramsApi = new HashMap<String,Object>();
        paramsApi.put("wsfunction", function);
        paramsApi.put("moodlewsrestformat", format);

        return produce(paramsApi, params);

    }

    private String produce(String function, Map<String,Object> params) {

        return produce(function, "json", params);

    }

   /**
   * Create and User passing User model of Moodle
   * @param User the User model of Moodle
   * @return String Is a string in json format.
   */
    public String createUser(User user) {
        
        String userNameParam = "users[0][username]";
        String userNameValue = user.getUserName();

        String firstNameParam = "users[0][firstname]";
        String firstNameValue = user.getFirstName();

        String lastNameParam = "users[0][lastname]";
        String lastNameValue = user.getLastName();

        String emailNameParam = "users[0][email]";
        String emailNameValue = user.getEmail();

        String passNameParam = "users[0][password]";
        String passNameValue = user.getPassword();

        //controlar si esta el create no deberia esta pass

        String createPassNameParam = "users[0][createpassword]";
        int createPassNameValue = user.getCreatePassword();

        Map<String,Object> params = new HashMap<String,Object>();
        params.put(userNameParam, userNameValue);
        params.put(firstNameParam, firstNameValue);
        params.put(lastNameParam, lastNameValue);
        params.put(emailNameParam, emailNameValue);
        params.put(passNameParam, passNameValue);
        params.put(createPassNameParam, createPassNameValue);

        return produce("core_user_create_users", params);

    }

    public String enrolUser(int roleId, int userId, int courseId) {

        String roleIdNameParam = "enrolments[0][roleid]";
        int roleIdNameValue = roleId;

        String userIdNameParam = "enrolments[0][userid]";
        int userIdNameValue = userId;

        String courseIdNameParam = "enrolments[0][courseid]";
        int courseIdNameValue = courseId;

        Map<String,Object> params = new HashMap<String,Object>();
        params.put(roleIdNameParam, roleIdNameValue);
        params.put(userIdNameParam, userIdNameValue);
        params.put(courseIdNameParam, courseIdNameValue);
        
        String res = null;
        
        res = produce("enrol_manual_enrol_users", params);                

        return res;
    }

    public String getCourseBy(String fieldName, String courseExternalId) {

        String idNameParam = fieldName;
        String idNameValue = courseExternalId;

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("field", idNameParam);
        params.put("value", idNameValue);
        
        //return consumeObject("core_course_get_courses_by_field", "json", params); 
        return produce("core_course_get_courses_by_field", "json", params);

    }

    public String getUserByEmail(String mail) {

        String emailCriteriaKey = "criteria[0][key]";
        String emailCriteriaKeyValue = "email";

        String emailCriteriaValue = "criteria[0][value]";
        String emailCriteriaValueValue = mail;

        Map<String,Object> params = new HashMap<String,Object>();
        params.put(emailCriteriaKey, emailCriteriaKeyValue);
        params.put(emailCriteriaValue, emailCriteriaValueValue);
        
        return produce("core_user_get_users", "json", params);

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;

    }

    @Override
    public String toString() {
        return "MoodleServices [function=" + function + ", serviceType=" + serviceType + ", url=" + url + "]";
    }

}

