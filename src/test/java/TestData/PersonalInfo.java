package TestData;

import java.util.*;

public class PersonalInfo {
    private HashMap<String, String> profileInfo = new HashMap<>();
    private HashMap<String, String> contactInfo = new HashMap<>();

    public PersonalInfo(String name) {
        switch (name) {
            case "Afanaseva": {
                this.profileInfo.put("firstName", "Анна");
                this.profileInfo.put("lastName", "Афанасьева");
                this.profileInfo.put("firstNameLatin", "Anna");
                this.profileInfo.put("lastnameLatin", "Afanaseva");
                this.profileInfo.put("nickName", "Anuta93");
                this.profileInfo.put("dayOfBirth", "10.12.1993");
                this.contactInfo.put("Skype", "my_skype_number");
                this.contactInfo.put("Viber", "my_viber_number");
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

//    public PersonalInfo(String firstName, String lastName, String firstNameLatin, String lastnameLatin, String nickName, String dayOfBirth) {
//        this.profileInfo.put("firstName", firstName);
//        this.profileInfo.put("lastName", lastName);
//        this.profileInfo.put("firstNameLatin", firstNameLatin);
//        this.profileInfo.put("lastnameLatin", lastnameLatin);
//        this.profileInfo.put("nickName", nickName);
//        this.profileInfo.put("dayOfBirth", dayOfBirth);
//    }

    public PersonalInfo(HashMap profileInfo, HashMap contactInfo) {
        this.profileInfo = profileInfo;
        this.contactInfo = contactInfo;
    }

    public PersonalInfo getPersonalInfo() {return this;}

    public String getProfileInfoByName(String key) {
        return profileInfo.get(key);
    }

    public HashMap getContactInfo() { return this.contactInfo; }
}
