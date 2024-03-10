package dkw.demo.res;

public class AccessToken_resData
{
    private String refresh_token;

    private int expires_in;

    private String session_key;

    private String access_token;

    private String scope;

    private String session_secret;

    public void setRefresh_token(String refresh_token){
        this.refresh_token = refresh_token;
    }
    public String getRefresh_token(){
        return this.refresh_token;
    }
    public void setExpires_in(int expires_in){
        this.expires_in = expires_in;
    }
    public int getExpires_in(){
        return this.expires_in;
    }
    public void setSession_key(String session_key){
        this.session_key = session_key;
    }
    public String getSession_key(){
        return this.session_key;
    }
    public void setAccess_token(String access_token){
        this.access_token = access_token;
    }
    public String getAccess_token(){
        return this.access_token;
    }
    public void setScope(String scope){
        this.scope = scope;
    }
    public String getScope(){
        return this.scope;
    }
    public void setSession_secret(String session_secret){
        this.session_secret = session_secret;
    }
    public String getSession_secret(){
        return this.session_secret;
    }
}
