package self.sunng.miscellaneous;

import java.io.Serializable;

/**
 * Created by fengzheng on 2016/6/28.
 */
public class BaseDTO implements Serializable {

    private static final long serialVersionUID = -7867366024502131069L;

    /** 应用ID */
    private String appId;
    /** 平台类型 1：Android   2：ios   3：wechat  */
    private String platform;
    /** 渠道 */
    private String channel;
    /** 版本 */
    private String version;
    /**设备唯一编码*/
    private String uuid;
    /** token */
    private String token;
    /** 交易流水号; UUID */
    private String transSn;
    /** 交易时间;yyyy-MM-dd HH:mm:ss.sss*/
    private String transTime;

    private int statusCode;
    private String errorInfo;

    private String versionCode;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTransSn() {
        return transSn;
    }

    public void setTransSn(String transSn) {
        this.transSn = transSn;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }
}