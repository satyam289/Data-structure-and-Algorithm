package Math_Bit;

public class ConvertFraction {

	public static void main(String[] args) {
		convertFraction(500.125);
	}

	public static void convertFraction(double decimal) {
		String str = String.valueOf(decimal);
		int decimalpoint = str.indexOf(".");
		int num = Integer.parseInt(str.substring(0, decimalpoint));
		int dem = Integer.parseInt(str.substring(decimalpoint + 1));
		// System.out.println(num+","+ dem);
		int num1, dem1;
		if (num >= dem) {
			num1 = num;
			dem1 = dem;
		} else {
			num1 = dem;
			dem1 = num;
		}
		int commonfactor = gcd(num1, dem1);
		System.out.println((num / commonfactor) + " / " + (dem / commonfactor));
	}

	public static int gcd(int num, int divisor) {
		if (divisor == 0)
			return num;
		return gcd(divisor, num % divisor);
	}

}
