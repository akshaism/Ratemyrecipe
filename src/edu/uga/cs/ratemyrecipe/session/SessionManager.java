 package edu.uga.cs.ratemyrecipe.session;


import java.security.MessageDigest; 
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.uga.cs.ratemyrecipe.RARException;
import edu.uga.cs.ratemyrecipe.persistence.impl.DbUtils;




/**
 * Based on the modified code from Matthew Eavenson
 * 
 * @author Matthew Eavenson
 */

/** This class provides different operations for the Sessions such as 
 *  creating new sessions, removing, login and logout.
 */
public class SessionManager
{
    /** 
     * Map for the existing sessions
     */
    private static Map<String, Session> sessions;
    
    /** 
     * Map for the currently logged-in users
     */
    private static Map<String, Session> loggedIn;
    
    //    private static Logger log = Logger.getLogger( SessionManager.class );
    
    static{
        sessions = new HashMap<String, Session>();
        loggedIn = new HashMap<String, Session>();
    } 
    
    public static Session createSession() 
            throws RARException 
    {
        Connection conn = null;
        Session s = null;
        
        // open a connection to the database
        try {
            conn = DbUtils.connect();
        } catch (Exception seq) {
            throw new RARException( "SessionManager.login: Unable to get a database connection" );
        }
        
        // initialize a new Session object
        // this creates PersistenceLayer, ObjectLayer, and LogicLayer instances
        // The LogicLayer reference is stored with the Session for use in other use cases later.
        s = new Session( conn );
        
        return s; 
    }
    
    public static String storeSession( boolean isAdmin, Session session ) 
            throws RARException
    {
    		String ssid = secureHash( "CLUBS" + System.nanoTime() );
        return ssid;
    }
    
    /****************************************************
     * Logout of the current session (based on session)
     * @param  the session being used
     * @throws GVException
     */
    public static void logout(Session s) 
            throws RARException
    {
        s.setExpiration(new Date());
        s.interrupt();
        removeSession(s);
    }
    
    /****************************************************
     * Logout of the current session (based on session)
     * @param  ssid the session being used
     * @throws GVException
     */
    public static void logout(String ssid) 
            throws RARException
    {
        Session s = getSessionById(ssid);
        s.setExpiration(new Date());
        s.interrupt();
        removeSession(s);
    }
    
    /****************************************************
     * Remove the session
     * @param s the current session
     * @throws GVException
     */
    protected static void removeSession( Session s ) 
            throws RARException
    {
        try { 
            s.getConnection().close();
        } 
        catch( SQLException sqe ) { 
	    //            log.error( "SessionManager.removeSession: cannot close connection", sqe );
            throw new RARException( "SessionManager.removeSession: Cannot close connection" );
        } // try
        sessions.remove( s.getSessionId() );
    }
    
    /****************************************************
     * Get the session by session id
     * @param ssid the current session id
     */
    public static Session getSessionById( String ssid ){
        return sessions.get( ssid );
    }
    
    /*********************************************************************
     * Hashes the string input using the SHA1 algorithm.
     * @param   input   string to hash.
     * @return  SHA hash of the string.
     * @throws ClubsException 
     */
    public static String secureHash( String input ) 
            throws RARException
    {
        StringBuilder output = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance( "SHA" );
            md.update( input.getBytes() );
            byte[] digest = md.digest();
            for( int i = 0; i < digest.length; i++ ) {
                String hex = Integer.toHexString( digest[i] );
                if( hex.length() == 1 )
                    hex = "0" + hex;
                hex = hex.substring( hex.length() - 2 );
                output.append( hex );
            }
        }
        catch( Exception e ) {
            // log.error( "SessionManager.secureHash: Invalid Encryption Algorithm", e );
            throw new RARException(
                    "SessionManager.secureHash: Invalid Encryption Algorithm" );
        }
        return output.toString();
    }
    
    /**********************************************************************
     * Return the logger object. 
     * @return Logger object. 
     * @author Arsham Mesbah
     */
    /*
    public static Logger getLog()
    {
        return log; 
    }
    */
}
