/*
 * Created by Itzik Braun on 12/3/2015.
 * Copyright (c) 2015 deluge. All rights reserved.
 *
 * Last Modification at: 3/12/15 4:34 PM
 */

package co.chatsdk.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import co.chatsdk.core.dao.DaoDefines;

import static co.chatsdk.core.types.Defines.ServerUrl;

public class FirebasePaths{

    public static final char Separator = '/';

    public static final String UsersPath = "users";
    public static final String MessagesPath = "messages";
    public static final String ThreadsPath = "threads";
    public static final String PublicThreadsPath = "public-threads";
    public static final String DetailsPath = "details";
    public static final String IndexPath = "searchIndex";
    public static final String OnlinePath = "online";
    public static final String MetaPath = "meta";
    public static final String FollowersPath = "followers";
    public static final String FollowingPath = "follows";
    public static final String Image = "imaeg";
    public static final String Thumbnail = "thumbnail";
    public static final String BTyping = "typing";

    private String url;
    private static StringBuilder builder = new StringBuilder();

    private FirebasePaths(String url) {
        this.url = url;
    }

    /* Not sure if this the wanted implementation but its give the same result as the objective-C code.*/
    /** @return The main databse ref.*/
    public static DatabaseReference firebaseRef(){
        if (StringUtils.isBlank(ServerUrl))
            throw new NullPointerException("Please set the server url in DaoDefines class");

        return fb(ServerUrl);
    }

    /** @return Firebase object for give url.*/
    private static DatabaseReference fb (String url){
        return FirebaseDatabase.getInstance().getReferenceFromUrl(url);
    }

    /* Users */
    /** @return The users main ref.*/
    public static DatabaseReference usersRef(){
        return firebaseRef().child(UsersPath);
    }

    /** @return The user ref for given id.*/
    public static DatabaseReference userRef(String firebaseId){
        return usersRef().child(firebaseId);
    }

    /** @return The user threads ref.*/
    public static DatabaseReference userThreadsRef(String firebaseId){
        return usersRef().child(firebaseId).child(ThreadsPath);
    }

    /** @return The user meta ref for given id.*/
    public static DatabaseReference userMetaRef(String firebaseId){
        return usersRef().child(firebaseId).child(MetaPath);
    }

    public static DatabaseReference userOnlineRef(String firebaseId){
        return userRef(firebaseId).child(OnlinePath);
    }

    public static DatabaseReference userFollowingRef(String firebaseId){
        return userRef(firebaseId).child(FollowingPath);
    }

    public static DatabaseReference userFollowersRef(String firebaseId){
        return userRef(firebaseId).child(FollowersPath);
    }

    /* Threads */
    /** @return The thread main ref.*/
    public static DatabaseReference threadRef(){
        return firebaseRef().child(ThreadsPath);
    }

    /** @return The thread ref for given id.*/
    public static DatabaseReference threadRef(String firebaseId){
        return threadRef().child(firebaseId);
    }

    public static DatabaseReference threadUsersRef(String firebaseId){
        return threadRef().child(firebaseId).child(UsersPath);
    }

    public static DatabaseReference threadDetailsRef(String firebaseId){
        return threadRef().child(firebaseId).child(DetailsPath);
    }

    public static DatabaseReference threadMessagesRef(String firebaseId){
        return threadRef(firebaseId).child(MessagesPath);
    }
    
    public static DatabaseReference publicThreadsRef(){
        return firebaseRef().child(PublicThreadsPath);
    }

    /* Index */
    public static DatabaseReference indexRef(){
        return firebaseRef().child(IndexPath);
    }

    @Deprecated
    public static Map<String, Object> getMap(String[] keys,  Object...values){
        Map<String, Object> map = new HashMap<String, Object>();

        for (int i = 0 ; i < keys.length; i++){

            // More values then keys entered.
            if (i == values.length)
                break;

            map.put(keys[i], values[i]);
        }

        return map;
    }

    public static PathBuilder userThreadsPath (String userID, String threadID) {
        return new PathBuilder(DaoDefines.Keys.Users)
                .a(userID)
                .a(DaoDefines.Keys.Threads)
                .a(threadID);

    }

    public static PathBuilder threadUsersPath (String threadID, String userID) {
        return new PathBuilder(DaoDefines.Keys.Threads)
                .a(threadID)
                .a(DaoDefines.Keys.Users)
                .a(userID);

    }

//    public static int providerToInt(String provider){
//        if (provider.equals(DaoDefines.ProviderString.Password))
//        {
//            return DaoDefines.ProviderInt.Password;
//        }
//        else if (provider.equals(DaoDefines.ProviderString.Facebook))
//        {
//            return DaoDefines.ProviderInt.Facebook;
//        }
//        else if (provider.equals(DaoDefines.ProviderString.Google))
//        {
//            return DaoDefines.ProviderInt.Google;
//        }
//        else if (provider.equals(DaoDefines.ProviderString.Twitter))
//        {
//            return DaoDefines.ProviderInt.Twitter;
//        }
//        else if (provider.equals(DaoDefines.ProviderString.Anonymous))
//        {
//            return DaoDefines.ProviderInt.Anonymous;
//        }
//        else if (provider.equals(DaoDefines.ProviderString.Custom))
//        {
//            return DaoDefines.ProviderInt.Custom;
//        }
//
//        throw new IllegalArgumentException("No provider was found matching requested. Provider: " + provider);
//    }

//    public static String providerToString(int provider){
//
//        switch (provider){
//            case DaoDefines.ProviderInt.Password:
//                return DaoDefines.ProviderString.Password;
//            case DaoDefines.ProviderInt.Facebook:
//                return DaoDefines.ProviderString.Facebook;
//            case DaoDefines.ProviderInt.Google:
//                return DaoDefines.ProviderString.Google;
//            case DaoDefines.ProviderInt.Twitter:
//                return DaoDefines.ProviderString.Twitter;
//            case DaoDefines.ProviderInt.Anonymous:
//                return DaoDefines.ProviderString.Anonymous;
//            case DaoDefines.ProviderInt.Custom:
//                return DaoDefines.ProviderString.Custom;
//
//            default:
//                /*return ProviderString.Password;*/
//                throw new IllegalArgumentException("Np provider was found matching requested.");
//        }
//    }
}
