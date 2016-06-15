package varma.com.zimstutions;

/**
 * Created by Chandu on 6/11/2016.
 */
public class Tutor {
    //name and address string
    private String name;
    private String qualification;
    private String phone;
    private String subject;
    private String cls;
    private String experience;
    private String uniique_id;


    public Tutor() {
      /*Blank default constructor essential for Firebase*/
    }
    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getUniique_id()
    {
        return uniique_id;
    }
    public void setUniique_id(String uniique_id){
        this.uniique_id = uniique_id;
    }



}
