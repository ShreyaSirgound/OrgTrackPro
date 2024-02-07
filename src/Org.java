import java.util.ArrayList;
import java.util.List;

public class Org {
    /** 
     * The organization's name
     */
    private String name;

    /** 
     * The organization's email
     */
    private String email;

    /** 
     * The organization's contact number
     */
    private String phoneNum;

    public Org(String name, String email, String phone){
        this.name = name;
        this.email = email;
        this.phoneNum = phone;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhoneNum(){
        return phoneNum;
    }

    public void setPhoneNumber(String phone){
        this.phoneNum = phone;
    }

    //creates a list of all the organizations in affialtaion with the school
    protected static List<Org> orgsList = new ArrayList<Org>();

    //add an organization to the organizations list
    public static void addOrg(Org o) {
        orgsList.add(o);
    }

    /**
     * Unregisters an existing organization from the application. Does nothing if the account does not exist.
     *
     * @param o The organization account to remove
     */
    public static void removeOrg(Org o) {
        orgsList.remove(o);
    }
    
    /**
     * Gets a list of all active organization accounts.
     *
     * @return A list of organizations
     */
    public static List<Org> getOrgs() {
        return orgsList;
    }
}
