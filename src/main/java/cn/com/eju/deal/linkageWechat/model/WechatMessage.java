package cn.com.eju.deal.linkageWechat.model;

import cn.com.eju.deal.core.model.BaseModel;

/**
 * 微信消息（服务端接收到的）
 * @author yujun
 */
public  class WechatMessage extends BaseModel{

    private static final long serialVersionUID = 1L;
    public static final String TEXT = "text";
	public static final String IMAGE = "image";
	public static final String LOCATION = "location";
	public static final String LINK = "link";
	public static final String EVENT = "event";
	public static final String MUSIC = "music";
	public static final String NEWS = "news";
	
	public static final String ERROR_CONTENT = "操作失败，请稍后再试。\n";
	
	/**
     * 业务介绍内容
     */
    public static final String WELCOME_CONTENT = "欢迎您关注易居房产交易服务中心！小易为您服务，即刻绑定手机，实时查询办理进度。\n\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfefea783d966e840&redirect_uri=http%3A%2F%2Fdemo.m.ehousetrade.com%2FmobileBind%2Findex&response_type=code&scope=snsapi_base&state=123#wechat_redirect\">【绑定手机】</a>\n";                                                                                                                                                                                                                 
   
    /**
	 * 案件详情提示【临时】
	 */
	public static final String ORDERDETAIL_CONTENT = "签约编号：11111111111111\n产权地址：上海市徐汇区南丹东路888号\n卖方：王老五/1399999999\n"
			+ "买方：杨白劳/1388888888\n经纪人：李小强/13777777777\n案件进度：案件已完成网签\n现阶段业务负责人：王晓明/13666666666\n下一阶段业务：办理贷款\n"
			+ "贷款专员：王大明/13777777777\n备件提醒：身份证、户口本、结婚证\n";
	/**
	 * 手机已绑定提示
	 */
	public static final String ISBIND_MOBILE_CONTENT = "您已经成功绑定过手机号码了，无需再次绑定。";
	//------------------------------------------微信公众号菜单 start----------------------------------------//
    /**
     * 菜单2 易居服务KEY【交易中心】
     */
    public static final String EXCHANGE_CENTER_002 = "EXCHANGE_CENTER_002";
    /**
     * 菜单2 易居服务KEY【钜派金融】
     */
    public static final String FINANCE_003 = "FINANCE_003";
    /**
     * 菜单3 我的KEY【绑定手机】
     */
    public static final String BINDPhone_001 = "BINDPhone_001";
    /**
     * 菜单3 我的KEY【小易帮您】
     */
    public static final String HELP_002 = "HELP_002";
	//------------------------------------------微信公众号菜单 end----------------------------------------//
    
  //------------------------------------------ 推送的消息内容 start -----------------------------------------//
    /**
    * 手机绑定URL
    */ 
    public static final String BINDPHONE_URL = "<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfefea783d966e840&redirect_uri=http%3A%2F%2Fdemo.m.ehousetrade.com%2FmobileBind%2Findex&response_type=code&scope=snsapi_base&state=123#wechat_redirect\">【绑定手机】</a>";

    /**
     * 【用户回复除1,2,3,4,5以外的任意字符】内容
     */
    public static final String OTHER_CONTENT = "Hi，我是小易，您的问题我会仔细查看，感谢您的支持！";
    //------------------------------------------ 推送的消息内容 end -----------------------------------------//
	
	private Integer id;
	// 开发者微信号  
    private String ToUserName;  
    // 发送方帐号（一个OpenID）  
    private String FromUserName;  
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    // 消息创建时间    
    private long CreateTime;  
    // 消息类型（text/image/location/link）  
    private String MsgType;  
    // 消息id，64位整型  
    private String MsgId;
    
    // 消息内容 (文本消息专有)
    private String Content;
    
    //图片链接 (图片消息专有)
    private String PicUrl;
    
    // 消息标题 (链接消息专有) 
    private String Title;  
    // 消息描述 (链接消息专有) 
    private String Description;  
    // 消息链接  (链接消息专有)
    private String Url;
    
    
    //地理位置纬度 Location_X(地理位置专有)
    private String Location_X;
    //地理位置经度 Location_Y(地理位置专有)
    private String Location_Y;
    // 地图缩放大小  (地理位置专有)
    private String Scale;  
    // 地理位置信息  (地理位置专有)
    private String Label;
    
    
    //事件类型，subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件) （事件推送专有）
    private String Event;
    //事件KEY值，与自定义菜单接口中KEY值对应（事件推送专有）
    private String EventKey;
    /**
     * 消息类型【R-回复S-发送】
     */
    private String Type;
    
    private String MediaId;
    
    private String Format;
    
    private String ThumbMediaId;
    
    private String MusicUrl;
    
    private String HqMusicUrl	;
    
    private String ArticleCount;
    
    private String Articles;
    
    private String ScanCodeInfo;
    
    private String ScanType;
    
    private String ScanResult;
    
    private String SendPicsInfo;
    
    private String Count;
    
    private String PicList;
    
    private String PicMd5Sum;
    
    private String SendLocationInfo;
    
    private String Poiname;
    
    private String Recognition;
    
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	/**
	 * @return the location_X
	 */
	public String getLocation_X() {
		return Location_X;
	}
	/**
	 * @param location_X the location_X to set
	 */
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	/**
	 * @return the location_Y
	 */
	public String getLocation_Y() {
		return Location_Y;
	}
	/**
	 * @param location_Y the location_Y to set
	 */
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return Type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		Type = type;
	}
	/**
	 * @return the mediaId
	 */
	public String getMediaId() {
		return MediaId;
	}
	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	/**
	 * @return the format
	 */
	public String getFormat() {
		return Format;
	}
	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		Format = format;
	}
	/**
	 * @return the thumbMediaId
	 */
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	/**
	 * @param thumbMediaId the thumbMediaId to set
	 */
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	/**
	 * @return the musicUrl
	 */
	public String getMusicUrl() {
		return MusicUrl;
	}
	/**
	 * @param musicUrl the musicUrl to set
	 */
	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}
	/**
	 * @return the hqMusicUrl
	 */
	public String getHqMusicUrl() {
		return HqMusicUrl;
	}
	/**
	 * @param hqMusicUrl the hqMusicUrl to set
	 */
	public void setHqMusicUrl(String hqMusicUrl) {
		HqMusicUrl = hqMusicUrl;
	}
	/**
	 * @return the articleCount
	 */
	public String getArticleCount() {
		return ArticleCount;
	}
	/**
	 * @param articleCount the articleCount to set
	 */
	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}
	/**
	 * @return the articles
	 */
	public String getArticles() {
		return Articles;
	}
	/**
	 * @param articles the articles to set
	 */
	public void setArticles(String articles) {
		Articles = articles;
	}
	/**
	 * @return the scanCodeInfo
	 */
	public String getScanCodeInfo() {
		return ScanCodeInfo;
	}
	/**
	 * @param scanCodeInfo the scanCodeInfo to set
	 */
	public void setScanCodeInfo(String scanCodeInfo) {
		ScanCodeInfo = scanCodeInfo;
	}
	/**
	 * @return the scanType
	 */
	public String getScanType() {
		return ScanType;
	}
	/**
	 * @param scanType the scanType to set
	 */
	public void setScanType(String scanType) {
		ScanType = scanType;
	}
	/**
	 * @return the scanResult
	 */
	public String getScanResult() {
		return ScanResult;
	}
	/**
	 * @param scanResult the scanResult to set
	 */
	public void setScanResult(String scanResult) {
		ScanResult = scanResult;
	}
	/**
	 * @return the sendPicsInfo
	 */
	public String getSendPicsInfo() {
		return SendPicsInfo;
	}
	/**
	 * @param sendPicsInfo the sendPicsInfo to set
	 */
	public void setSendPicsInfo(String sendPicsInfo) {
		SendPicsInfo = sendPicsInfo;
	}
	/**
	 * @return the count
	 */
	public String getCount() {
		return Count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(String count) {
		Count = count;
	}
	/**
	 * @return the picList
	 */
	public String getPicList() {
		return PicList;
	}
	/**
	 * @param picList the picList to set
	 */
	public void setPicList(String picList) {
		PicList = picList;
	}
	/**
	 * @return the picMd5Sum
	 */
	public String getPicMd5Sum() {
		return PicMd5Sum;
	}
	/**
	 * @param picMd5Sum the picMd5Sum to set
	 */
	public void setPicMd5Sum(String picMd5Sum) {
		PicMd5Sum = picMd5Sum;
	}
	/**
	 * @return the sendLocationInfo
	 */
	public String getSendLocationInfo() {
		return SendLocationInfo;
	}
	/**
	 * @param sendLocationInfo the sendLocationInfo to set
	 */
	public void setSendLocationInfo(String sendLocationInfo) {
		SendLocationInfo = sendLocationInfo;
	}
	/**
	 * @return the poiname
	 */
	public String getPoiname() {
		return Poiname;
	}
	/**
	 * @param poiname the poiname to set
	 */
	public void setPoiname(String poiname) {
		Poiname = poiname;
	}
	/**
	 * @return the recognition
	 */
	public String getRecognition() {
		return Recognition;
	}
	/**
	 * @param recognition the recognition to set
	 */
	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
    
}