package kn.severina.usermanagement;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    private User user;
	private Date dateOfBirthday;

    
    protected void setUp() throws Exception {
        super.setUp();
        user = new User();
        
       Calendar calendar = Calendar.getInstance();
       calendar.set(1996, Calendar.NOVEMBER, 6);
       dateOfBirthday = calendar.getTime();
        
    }

    
    public void testGetFullName() {
        user.setFirstName("John");
        user.setLastName("Doe");
        assertEquals("Doe, John", user.getFullName());
    }
    
    
    public void testGetAge() {
       
        user.setDateOfBirthday(dateOfBirthday);
        assertEquals(2016 - 1996, user.getAge());
        
    }

}
