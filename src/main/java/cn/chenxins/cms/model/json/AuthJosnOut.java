package cn.chenxins.cms.model.json;

public class AuthJosnOut {

    private String auth;

    private String module;

    public AuthJosnOut(String auth, String module) {
        this.auth = auth;
        this.module = module;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
