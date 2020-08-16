package Utilities;

public class Routes 
{
	//==================================BaseURL======================================
	public static final String ProductionWebURL = "https://web.vodafone.com.eg";
	public static final String ProductionURL = "https://mobile.vodafone.com.eg";
	public static final String StagingURL = "http://test1.vodafone.com.eg";
	public static final String TestingURL = "http://qa1.vodafone.com.eg";
	public static final String BaseURL = ProductionURL;
	public static final String WebBaseURL = ProductionWebURL;
	//==================================Routes========================================
	public static String Token = "/services/security/oauth/oauth/token";
	public static String Loyalty= "/services/dxl/loyaltymng/loyaltyProgramMember";
	public static String Consumption= "/services/dxl/usage/usageConsumptionReport";
	public static String Management= "/services/dxl/usagemng/usage";
	public static String ProductInventory = "/services/dxl/pim/product";
	public static String Configuration = "/services/dxl/cc/channelConfiguration";
	public static String AccountList = "/services/account/listAccounts";
	public static String ADSLAccount = "services/dxl-api/serviceAccountManagement/v1/serviceAccount";
	public static String WebToken ="/auth/realms/vf-realm/protocol/openid-connect/token";

}
