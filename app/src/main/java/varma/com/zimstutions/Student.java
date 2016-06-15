package varma.com.zimstutions;

/**
 * Created by Chandu on 6/9/2016.
 */
public class Student
{
    //name and address string
    private String name;
    private String parent;
    private String phone;
    private String cls;
    private String school;
    private String unique_id;

    public Student() {
      /*Blank default constructor essential for Firebase*/
    }
    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getUnique_id()
    {
        return unique_id;
    }
    public void setUnique_id(String unique_id)
    {
        this.unique_id = unique_id;
    }

}
