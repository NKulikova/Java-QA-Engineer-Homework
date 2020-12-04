package TestData;

import org.openqa.selenium.By;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class PersonalInfo {
    Map<String, String> profileInfo = new Map<String, String>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public String get(Object key) {
            return null;
        }

        @Override
        public String put(String key, String value) {
            return null;
        }

        @Override
        public String remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<? extends String, ? extends String> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<String> keySet() {
            return null;
        }

        @Override
        public Collection<String> values() {
            return null;
        }

        @Override
        public Set<Entry<String, String>> entrySet() {
            return null;
        }
    };
    private Map<String, String> contactInfo;

    public PersonalInfo(String name) {
        switch (name) {
            case "Afanaseva": {
                this.profileInfo.put("firstName", "Анна");
                this.profileInfo.put("lastName", "Афанасьева");
                this.profileInfo.put("firstNameLatin", "Anna");
                this.profileInfo.put("lastnameLatin", "Afanaseva");
                this.profileInfo.put("nickName", "Anuta193");
                this.profileInfo.put("dayOfBirth", "10.12.1993");
            }
            case "Kulikova":
            default: {
                this.profileInfo.put("firstName", "Наталья");
                this.profileInfo.put("lastName", "Куликова");
                this.profileInfo.put("firstNameLatin", "");
                this.profileInfo.put("lastnameLatin", "");
                this.profileInfo.put("nickName", "Наталья");
                this.profileInfo.put("dayOfBirth", "26.12.1986");
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
