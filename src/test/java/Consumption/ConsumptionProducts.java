package Consumption;

import java.util.List;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ConsumptionProducts 
{
	String BucketType = "x";
	String UsageType = "x";
	String BucketIndex = "0";
	String ProductIndex = "0";
	int ResponseSize = 0;
	int BucketSize = 0;
	//----------------------Used quota---------------------
	public float UsedFlexes = 123456;
	public float UsedInternet = 123456;
	public float UsedSuperPass = 123456;
	public float UsedFamilyInternet = 123456;
	public float UsedFamilyMinutes = 123456;
	public float UsedFamilySMS = 123456;
	public float UsedRoamingInternet = 123456;
	public float UsedRoamingOutgoing = 123456;
	public float UsedRoamingIncoming = 123456;
	public float UsedRoamingSMS = 123456;
	public float UsedMinutes = 123456;
	public float UsedSMS = 123456;
	//----------------------Remaining quota---------------------
	public float RemainingFlexes = 123456;
	public float RemainingInternet = 123456;
	public float RemainingSuperPass = 123456;
	public float RemainingFamilyInternet = 123456;
	public float RemainingFamilyMinutes = 123456;
	public float RemainingFamilySMS = 123456;
	public float RemainingRoamingInternet = 123456;
	public float RemainingRoamingOutgoing = 123456;
	public float RemainingRoamingIncoming = 123456;
	public float RemainingRoamingSMS = 123456;
	public float RemainingMinutes = 123456;
	public float RemainingSMS = 123456;
//=======================Constructor to extract values from response=================
	public ConsumptionProducts(Response response)
	{
		try {
			String jsonString = response.asString(); //Convert response to string
			List<String> ResponseList = response.jsonPath().getList("$"); //save response in list to get its size
			ResponseSize = ResponseList.size(); //Get size of response list
			//------------Start Loop on buckets and products to set its values---------------
			for (int BucketInterator = 0; BucketInterator < ResponseSize; BucketInterator++)
			{
				BucketIndex = Integer.toString(BucketInterator);
				List<String> BucketList = response.jsonPath().getList("bucket["+BucketIndex+"]");
				BucketSize = BucketList.size(); //Get size of bucket
				BucketType = JsonPath.from(jsonString).get("'@type'["+BucketIndex+"]");
				//----------------------------------Search for Flex products----------------------------------			
				if (BucketType.equals("FLEX"))
				{
					for (int ProductIterator = 0; ProductIterator < BucketSize; ProductIterator++)
					{
						ProductIndex = Integer.toString(ProductIterator);
						UsageType = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].usageType["+ProductIndex+"]"); //Get product type
						if (UsageType.equals("flex"))
						{
							UsedFlexes = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketCounter["+ProductIndex+"].value.amount[0]"); //Save used flexes
							RemainingFlexes = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketBalance["+ProductIndex+"].remainingValue.amount[0]"); //Save remaining flexes
						}
					}
				}
				//----------------------------------Search for Data products----------------------------------	
				else if (BucketType.equals("DATA"))
				{
					for (int ProductIterator = 0; ProductIterator < BucketSize; ProductIterator++)
					{
						ProductIndex = Integer.toString(ProductIterator);
						UsageType = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].usageType["+ProductIndex+"]"); //Get product type
						if (UsageType.equals("DATA"))
						{
							UsedInternet = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketCounter["+ProductIndex+"].value.amount[0]");
							RemainingInternet = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketBalance["+ProductIndex+"].remainingValue.amount[0]");
						}
						else if (UsageType.equals("SUPER_MEGA"))
						{
							UsedSuperPass = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketCounter["+ProductIndex+"].value.amount[0]");
							RemainingSuperPass = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketBalance["+ProductIndex+"].remainingValue.amount[0]");
						}
					}
				}
				//----------------------------------Search for Family products----------------------------------
				else if (BucketType.equals("Family"))
				{
					for (int ProductIterator = 0; ProductIterator < BucketSize; ProductIterator++)
					{
						ProductIndex = Integer.toString(ProductIterator);
						UsageType = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].usageType["+ProductIndex+"]"); //Get product type
						if (UsageType.equals("DATA"))
						{
							UsedFamilyInternet = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketCounter["+ProductIndex+"].value.amount[0]");
							RemainingFamilyInternet = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketBalance["+ProductIndex+"].remainingValue.amount[0]");
						}
						else if (UsageType.equals("SMS"))
						{
							UsedFamilySMS = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketCounter["+ProductIndex+"].value.amount[0]");
							RemainingFamilySMS = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketBalance["+ProductIndex+"].remainingValue.amount[0]");
						}
						else if (UsageType.equals("extra_voice"))
						{
							UsedFamilyMinutes = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketCounter["+ProductIndex+"].value.amount[0]");
							RemainingFamilyMinutes = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketBalance["+ProductIndex+"].remainingValue.amount[0]");
						}
					}
				}
				//----------------------------------Search for Roaming products----------------------------------
				else if (BucketType.equals("ROAMING"))
				{
					for (int ProductIterator = 0; ProductIterator < BucketSize; ProductIterator++)
					{
						ProductIndex = Integer.toString(ProductIterator);
						UsageType = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].usageType["+ProductIndex+"]"); //Get product type
						if (UsageType.equals("DATA"))
						{
							UsedRoamingInternet = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketCounter["+ProductIndex+"].value.amount[0]");
							RemainingRoamingInternet = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketBalance["+ProductIndex+"].remainingValue.amount[0]");
						}
						else if (UsageType.equals("SMS"))
						{
							UsedRoamingSMS = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketCounter["+ProductIndex+"].value.amount[0]");
							RemainingRoamingSMS = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketBalance["+ProductIndex+"].remainingValue.amount[0]");
						}
						else if (UsageType.equals("mo"))
						{
							UsedRoamingOutgoing = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketCounter["+ProductIndex+"].value.amount[0]");
							RemainingRoamingOutgoing = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketBalance["+ProductIndex+"].remainingValue.amount[0]");
						}
						else if (UsageType.equals("mt"))
						{
							UsedRoamingIncoming = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketCounter["+ProductIndex+"].value.amount[0]");
							RemainingRoamingIncoming = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketBalance["+ProductIndex+"].remainingValue.amount[0]");
						}
					}
				}
				//----------------------------------Search for Voice products----------------------------------
				else if (BucketType.equals("VOICE"))
				{
					for (int ProductIterator = 0; ProductIterator < BucketSize; ProductIterator++)
					{
						ProductIndex = Integer.toString(ProductIterator);
						UsageType = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].usageType["+ProductIndex+"]"); //Get product type
						if (UsageType.equals("VOICE"))
						{
							UsedMinutes = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketCounter["+ProductIndex+"].value.amount[0]");
							RemainingMinutes = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketBalance["+ProductIndex+"].remainingValue.amount[0]");
						}
					}
				}
				//----------------------------------Search for SMS products----------------------------------
				else if (BucketType.equals("SMS"))
				{
					for (int ProductIterator = 0; ProductIterator < BucketSize; ProductIterator++)
					{
						ProductIndex = Integer.toString(ProductIterator);
						UsageType = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].usageType["+ProductIndex+"]"); //Get product type
						if (UsageType.equals("SMS"))
						{
							UsedSMS = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketCounter["+ProductIndex+"].value.amount[0]");
							RemainingSMS = JsonPath.from(jsonString).get("bucket["+BucketIndex+"].bucketBalance["+ProductIndex+"].remainingValue.amount[0]");
						}
					}
				}
			} //End of bucket loop
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //End of constructor
//=================================Test==================================
	public static void main( String[] args )
    {
		Response response= ConsumptionEndPoints.consumptionRequest("1030693069", "Test@1234");
		ConsumptionProducts Obj = new ConsumptionProducts(response);
		
		System.out.println("-------Print Flex products------");
		System.out.println("UsedFlexes "+Obj.UsedFlexes
				+"\nRemainingFlexes "+Obj.RemainingFlexes);
		
		System.out.println("-------Print Data Products------");
		System.out.println("UsedInternet "+Obj.UsedInternet
				+"\nRemainingInternet "+Obj.RemainingInternet
				+"\nUsedSuperPass "+Obj.UsedSuperPass
				+"\nRemainingSuperPass "+Obj.RemainingSuperPass);
		
		System.out.println("-------Print Family products------");
		System.out.println("UsedFamilyInternet "+Obj.UsedFamilyInternet
				+"\nRemainingFamilyInternet "+Obj.RemainingFamilyInternet
				+"\nUsedFamilySMS "+Obj.UsedFamilySMS 
				+"\nRemainingFamilySMS "+Obj.RemainingFamilySMS
				+"\nUsedFamilyMinutes "+Obj.UsedFamilyMinutes
				+"\nRemainingFamilyMinutes "+Obj.RemainingFamilyMinutes);
		
		System.out.println("-------Print Roaming products------");
		System.out.println("UsedRoamingInternet "+Obj.UsedRoamingInternet
				+"\nRemainingRoamingInternet "+Obj.RemainingRoamingInternet
				+"\nUsedRoamingSMS "+Obj.UsedRoamingSMS 
				+"\nRemainingRoamingSMS "+Obj.RemainingRoamingSMS
				+"\nUsedRoamingOutgoing "+Obj.UsedRoamingOutgoing
				+"\nRemainingRoamingOutgoing "+Obj.RemainingRoamingOutgoing
				+"\nUsedRoamingIncoming "+Obj.UsedRoamingIncoming
				+"\nRemainingRoamingIncoming "+Obj.RemainingRoamingIncoming);
		
		System.out.println("-------Print Minutes products------");
		System.out.println("UsedMinutes "+Obj.UsedMinutes
				+"\nRemainingMinutes "+Obj.RemainingMinutes);
		
		System.out.println("-------Print SMS products------");
		System.out.println("UsedSMS "+Obj.UsedSMS
				+"\nRemainingSMS "+Obj.RemainingSMS);
    }
}
