package consumption;

import java.util.List;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ConsumptionProducts 
{
	String bucketType = "x";
	String usageType = "x";
	String bucketIndex = "0";
	String productIndex = "0";
	int responseSize = 0;
	int bucketSize = 0;
	//----------------------used quota---------------------
	public float usedFlexes = 123456;
	public float usedInternet = 123456;
	public float usedSuperPass = 123456;
	public float usedFamilyInternet = 123456;
	public float usedFamilyMinutes = 123456;
	public float usedFamilySMS = 123456;
	public float usedRoamingInternet = 123456;
	public float usedRoamingOutgoing = 123456;
	public float usedRoamingIncoming = 123456;
	public float usedRoamingSMS = 123456;
	public float usedMinutes = 123456;
	public float usedSMS = 123456;
	//----------------------remaining quota---------------------
	public float remainingFlexes = 123456;
	public float remainingInternet = 123456;
	public float remainingSuperPass = 123456;
	public float remainingFamilyInternet = 123456;
	public float remainingFamilyMinutes = 123456;
	public float remainingFamilySMS = 123456;
	public float remainingRoamingInternet = 123456;
	public float remainingRoamingOutgoing = 123456;
	public float remainingRoamingIncoming = 123456;
	public float remainingRoamingSMS = 123456;
	public float remainingMinutes = 123456;
	public float remainingSMS = 123456;
//=======================Constructor to extract values from response=================
	public ConsumptionProducts(Response response)
	{
		try {
			String jsonString = response.asString(); //Convert response to string
			List<String> ResponseList = response.jsonPath().getList("$"); //save response in list to get its size
			responseSize = ResponseList.size(); //Get size of response list
			//------------Start Loop on buckets and products to set its values---------------
			for (int bucketInterator = 0; bucketInterator < responseSize; bucketInterator++)
			{
				bucketIndex = Integer.toString(bucketInterator);
				List<String> bucketList = response.jsonPath().getList("bucket["+bucketIndex+"]");
				bucketSize = bucketList.size(); //Get size of bucket
				bucketType = JsonPath.from(jsonString).get("'@type'["+bucketIndex+"]");
				//----------------------------------Search for Flex products----------------------------------			
				if (bucketType.equals("FLEX"))
				{
					for (int productIterator = 0; productIterator < bucketSize; productIterator++)
					{
						productIndex = Integer.toString(productIterator);
						usageType = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].usageType["+productIndex+"]"); //Get product type
						if (usageType.equals("flex"))
						{
							usedFlexes = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketCounter["+productIndex+"].value.amount[0]"); //Save used flexes
							remainingFlexes = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketBalance["+productIndex+"].remainingValue.amount[0]"); //Save remaining flexes
						}
					}
				}
				//----------------------------------Search for Data products----------------------------------	
				else if (bucketType.equals("DATA"))
				{
					for (int productIterator = 0; productIterator < bucketSize; productIterator++)
					{
						productIndex = Integer.toString(productIterator);
						usageType = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].usageType["+productIndex+"]"); //Get product type
						if (usageType.equals("DATA"))
						{
							usedInternet = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketCounter["+productIndex+"].value.amount[0]");
							remainingInternet = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketBalance["+productIndex+"].remainingValue.amount[0]");
						}
						else if (usageType.equals("SUPER_MEGA"))
						{
							usedSuperPass = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketCounter["+productIndex+"].value.amount[0]");
							remainingSuperPass = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketBalance["+productIndex+"].remainingValue.amount[0]");
						}
					}
				}
				//----------------------------------Search for Family products----------------------------------
				else if (bucketType.equals("Family"))
				{
					for (int productIterator = 0; productIterator < bucketSize; productIterator++)
					{
						productIndex = Integer.toString(productIterator);
						usageType = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].usageType["+productIndex+"]"); //Get product type
						if (usageType.equals("DATA"))
						{
							usedFamilyInternet = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketCounter["+productIndex+"].value.amount[0]");
							remainingFamilyInternet = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketBalance["+productIndex+"].remainingValue.amount[0]");
						}
						else if (usageType.equals("SMS"))
						{
							usedFamilySMS = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketCounter["+productIndex+"].value.amount[0]");
							remainingFamilySMS = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketBalance["+productIndex+"].remainingValue.amount[0]");
						}
						else if (usageType.equals("extra_voice"))
						{
							usedFamilyMinutes = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketCounter["+productIndex+"].value.amount[0]");
							remainingFamilyMinutes = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketBalance["+productIndex+"].remainingValue.amount[0]");
						}
					}
				}
				//----------------------------------Search for Roaming products----------------------------------
				else if (bucketType.equals("ROAMING"))
				{
					for (int productIterator = 0; productIterator < bucketSize; productIterator++)
					{
						productIndex = Integer.toString(productIterator);
						usageType = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].usageType["+productIndex+"]"); //Get product type
						if (usageType.equals("DATA"))
						{
							usedRoamingInternet = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketCounter["+productIndex+"].value.amount[0]");
							remainingRoamingInternet = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketBalance["+productIndex+"].remainingValue.amount[0]");
						}
						else if (usageType.equals("SMS"))
						{
							usedRoamingSMS = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketCounter["+productIndex+"].value.amount[0]");
							remainingRoamingSMS = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketBalance["+productIndex+"].remainingValue.amount[0]");
						}
						else if (usageType.equals("mo"))
						{
							usedRoamingOutgoing = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketCounter["+productIndex+"].value.amount[0]");
							remainingRoamingOutgoing = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketBalance["+productIndex+"].remainingValue.amount[0]");
						}
						else if (usageType.equals("mt"))
						{
							usedRoamingIncoming = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketCounter["+productIndex+"].value.amount[0]");
							remainingRoamingIncoming = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketBalance["+productIndex+"].remainingValue.amount[0]");
						}
					}
				}
				//----------------------------------Search for Voice products----------------------------------
				else if (bucketType.equals("VOICE"))
				{
					for (int productIterator = 0; productIterator < bucketSize; productIterator++)
					{
						productIndex = Integer.toString(productIterator);
						usageType = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].usageType["+productIndex+"]"); //Get product type
						if (usageType.equals("VOICE"))
						{
							usedMinutes = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketCounter["+productIndex+"].value.amount[0]");
							remainingMinutes = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketBalance["+productIndex+"].remainingValue.amount[0]");
						}
					}
				}
				//----------------------------------Search for SMS products----------------------------------
				else if (bucketType.equals("SMS"))
				{
					for (int productIterator = 0; productIterator < bucketSize; productIterator++)
					{
						productIndex = Integer.toString(productIterator);
						usageType = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].usageType["+productIndex+"]"); //Get product type
						if (usageType.equals("SMS"))
						{
							usedSMS = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketCounter["+productIndex+"].value.amount[0]");
							remainingSMS = JsonPath.from(jsonString).get("bucket["+bucketIndex+"].bucketBalance["+productIndex+"].remainingValue.amount[0]");
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
		System.out.println("usedFlexes "+Obj.usedFlexes
				+"\nremainingFlexes "+Obj.remainingFlexes);
		
		System.out.println("-------Print Data Products------");
		System.out.println("usedInternet "+Obj.usedInternet
				+"\nremainingInternet "+Obj.remainingInternet
				+"\nusedSuperPass "+Obj.usedSuperPass
				+"\nremainingSuperPass "+Obj.remainingSuperPass);
		
		System.out.println("-------Print Family products------");
		System.out.println("usedFamilyInternet "+Obj.usedFamilyInternet
				+"\nremainingFamilyInternet "+Obj.remainingFamilyInternet
				+"\nusedFamilySMS "+Obj.usedFamilySMS 
				+"\nremainingFamilySMS "+Obj.remainingFamilySMS
				+"\nusedFamilyMinutes "+Obj.usedFamilyMinutes
				+"\nremainingFamilyMinutes "+Obj.remainingFamilyMinutes);
		
		System.out.println("-------Print Roaming products------");
		System.out.println("usedRoamingInternet "+Obj.usedRoamingInternet
				+"\nremainingRoamingInternet "+Obj.remainingRoamingInternet
				+"\nusedRoamingSMS "+Obj.usedRoamingSMS 
				+"\nremainingRoamingSMS "+Obj.remainingRoamingSMS
				+"\nusedRoamingOutgoing "+Obj.usedRoamingOutgoing
				+"\nremainingRoamingOutgoing "+Obj.remainingRoamingOutgoing
				+"\nusedRoamingIncoming "+Obj.usedRoamingIncoming
				+"\nremainingRoamingIncoming "+Obj.remainingRoamingIncoming);
		
		System.out.println("-------Print Minutes products------");
		System.out.println("usedMinutes "+Obj.usedMinutes
				+"\nremainingMinutes "+Obj.remainingMinutes);
		
		System.out.println("-------Print SMS products------");
		System.out.println("usedSMS "+Obj.usedSMS
				+"\nremainingSMS "+Obj.remainingSMS);
    }
}
