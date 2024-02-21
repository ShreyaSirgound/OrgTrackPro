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

    /** 
     * Brief description of the organization
     */
    private String desc;

    /** 
     * The organization's address
     */
    private String address;

    public Org(String name, String email, String phone, String desc, String address){
        this.name = name;
        this.email = email;
        this.phoneNum = phone;
        this.desc = desc;
        this.address = address;
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

    public String getDesc(){
        return desc;    
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public static String toString(Org o){
        String org = o.getName() + "|"
                        + o.getEmail() + "|"
                        + o.getPhoneNum() + "|"
                        + o.getAddress() + "|"
                        + o.getDesc();
        return org;
    }

    public static boolean exists(Org o){
        boolean exists = true;
        for(Org org : getOrgs()){
            if(org.toString().equals(o.toString())){
                return false;
            } 
        }
        return exists;
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
