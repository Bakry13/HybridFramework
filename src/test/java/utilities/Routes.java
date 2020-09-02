package utilities;

public class Routes 
{
	//==================================BaseURL======================================
	public static final String productionWebURL = "https://web.vodafone.com.eg";
	public static final String productionURL = "https://mobile.vodafone.com.eg";
	public static final String stagingURL = "http://test1.vodafone.com.eg";
	public static final String testingURL = "http://qa1.vodafone.com.eg";
	public static final String baseURL = productionURL;
	public static final String webBaseURL = productionWebURL;
	//==================================Routes========================================
	public static String token = "/services/security/oauth/oauth/token";
	public static String loyalty= "/services/dxl/loyaltymng/loyaltyProgramMember";
	public static String consumption= "/services/dxl/usage/usageConsumptionReport";
	public static String management= "/services/dxl/usagemng/usage";
	public static String productInventory = "/services/dxl/pim/product";
	public static String configuration = "/services/dxl/cc/channelConfiguration";
	public static String accountList = "/services/account/listAccounts";
	public static String adslAccount = "services/dxl-api/serviceAccountManagement/v1/serviceAccount";
	public static String webToken ="/auth/realms/vf-realm/protocol/openid-connect/token";
}
