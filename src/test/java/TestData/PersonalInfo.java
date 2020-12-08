package TestData;

import org.openqa.selenium.By;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PersonalInfo {
    private HashMap<String, String> profileInfo = new HashMap<>();
//    private HashMap<String, String> contactInfo = new HashMap<>();

    public PersonalInfo(String name) {
        switch (name) {
            case "Afanaseva": {
                this.profileInfo.put("firstName", "Анна");
                this.profileInfo.put("lastName", "Афанасьева");
                this.profileInfo.put("firstNameLatin", "Anna");
                this.profileInfo.put("lastnameLatin", "Afanaseva");
                this.profileInfo.put("nickName", "Anuta93");
                this.profileInfo.put("dayOfBirth", "10.12.1993");
                break;
            }
            case "Kulikova":
            default: {
                this.profileInfo.put("firstName", "Наталья");
                this.profileInfo.put("lastName", "Куликова");
                this.profileInfo.put("firstNameLatin", "");
                this.profileInfo.put("lastnameLatin", "");
                this.profileInfo.put("nickName", "Наталья");
                this.profileInfo.put("dayOfBirth", "26.12.1986");
                break;
            }
        }
    }

    public PersonalInfo(String firstName, String lastName, String firstNameLatin, String lastnameLatin, String nickName, String dayOfBirth) {
        this.profileInfo.put("firstName", firstName);
        this.profileInfo.put("lastName", lastName);
        this.profileInfo.put("firstNameLatin", firstNameLatin);
        this.profileInfo.put("lastnameLatin", lastnameLatin);
        this.profileInfo.put("nickName", nickName);
        this.profileInfo.put("dayOfBirth", dayOfBirth);
    }

    public PersonalInfo getPersonalInfo() {return this;}

    public String getProfileInfo(String key) {
        return profileInfo.get(key);
    }
}
