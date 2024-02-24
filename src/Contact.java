import java.util.ArrayList;
import java.util.List;

public class Contact {
    /** 
     * The contact's name
     */
    private String name;

    /** 
     * The contact's email
     */
    private String email;

    /** 
     * The contact's contact number
     */
    private String phoneNum;

    /** 
     * Brief description of the contact
     */
    private String desc;

    /** 
     * The organization the contact is affiliated with
     */
    private String org;

    public Contact(String name, String email, String phone, String desc, String org){
        this.name = name;
        this.email = email;
        this.phoneNum = phone;
        this.desc = desc;
        this.org = org;
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

    public String getOrg(){
        return org;
    }

    public void setOrg(String org){
        this.org = org;
    }

    public static String toString(Contact c){
        String contact = c.getName() + "|"
                        + c.getEmail() + "|"
                        + c.getPhoneNum() + "|"
                        + c.getOrg() + "|"
                        + c.getDesc();
        return contact;
    }

    public static boolean exists(Contact contact){
        boolean exists = true;
        for(Contact c : getContacts()){
            if(c.toString().equals(contact.toString())){
                return false;
            } 
        }
        return exists;
    }

    //creates a list of all the contacts
    protected static List<Contact> contactsList = new ArrayList<Contact>();

    //add contact to the contacts list
    public static void addContact(Contact c) {
        contactsList.add(c);
    }

    /**
     * Unregisters an existing contact from the application. Does nothing if the account does not exist.
     *
     * @param c The contacts account to remove
     */
    public static void removeContact(Contact c) {
        contactsList.remove(c);
    }
    
    /**
     * Gets a list of all active contact accounts.
     *
     * @return A list of contacts
     */
    public static List<Contact> getContacts() {
        return contactsList;
    }
}
