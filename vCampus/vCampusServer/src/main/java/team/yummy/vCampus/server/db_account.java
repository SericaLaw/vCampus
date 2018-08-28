package team.yummy.vCampus.server;
//import 
import java.sql.*;

//import sun.tools.jconsole.ConnectDialog;
public class db_account{
	static String db_url = "./src/vCampusDatabase/trial1.accdb";
	static String db_driver = "net.ucanaccess.jdbc.UcanaccessDriver";
	
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
       	Class.forName(db_driver);
        Connection conn = DriverManager.getConnection("jdbc:ucanaccess://"+db_url);
        System.out.println(conn);   
        Statement st = conn.createStatement();
        CheckAccount(st,"teacher1","teacher1");

    }
    
    static void Test(Statement st) throws SQLException {
        ResultSet res=  st.executeQuery(
        		"SELECT Username, Password, CampusCardID FROM Account WHERE Username='teacher3'"
        		);
        int cnt = 0;
        while(res.next()){
            System.out.println(res.getString(3));
            cnt += 1;
        }
    }
    
    static boolean CheckAccount(Statement st,String username, String password) {
        // 1. username's password = password
            // {
            //     "CampusID": "213160000",
            //     "FirstName": "张",
            //     "LastName": "三"
            // }
        // 2. username's password != password
            // {
            //     "Error":"WrongUsernameOrPassword"
            // }
        // 3. username doesn't exist
            // {
            //     "Error": "UserNotFound"
            // }

    	try {
            ResultSet res = st.executeQuery("SELECT * FROM Account WHERE Username='"+username+"'");    		
            int cnt = 0;
            while(res.next()){
                String CampusCardID = res.getString(1);
                String Username = res.getString(2);
                String Password = res.getString(3);
                String LastName = res.getString(4);
                String FirstName = res.getString(5);

                if(Password==password){
                    Account cur_usr = new Account(CampusCardID, Username, Password, LastName,FirstName);
                    
                }

                cnt += 1;
            }
            if(cnt==1) return true;
            return false;
    	}
    	catch (Exception SQLException) {
    		System.out.println(SQLException);
            return false;
    	}
    }
}

class Account{
	Account(String id, String username, String password, String lastname,String firstname){
		CampusCardID = id;
		Username = username;
		Password = password;
		LastName = lastname;
		FirstName = firstname;
	}
	String CampusCardID;
	String Username;
	String Password;
	String LastName;
	String FirstName;
}