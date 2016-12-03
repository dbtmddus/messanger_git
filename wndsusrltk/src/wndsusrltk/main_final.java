package wndsusrltk;

import java.math.BigInteger;
import java.util.Scanner;

public class main_final {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		//System.out.println("십자가 ");
		//System.out.println("현재 %: / 1업 폭 / 1업 비용");
		BigInteger bi_십자가_현재 =in.nextBigInteger();
		BigInteger bi_십자가_1업폭 = in.nextBigInteger();
		String 십자가_1업비용 =in.next();
		BigInteger bi_십자가_1업비용 = new BigInteger( Integer.toString
				(Integer.parseInt(십자가_1업비용.substring(0, 십자가_1업비용.length()-1))));
		for (int i=0; i< (십자가_1업비용.charAt(십자가_1업비용.length()-1)-96); i++)
			{bi_십자가_1업비용 = bi_십자가_1업비용.multiply(BigInteger.valueOf(1000));}
		//System.out.println();
		//System.out.println();
////////////////////////////////////////////////////////////////////////////////////////////////////
		//System.out.println("진힘반 ");
		//System.out.println("현재 %: / 1업 폭 / 1업 비용");
		BigInteger bi_진힘반_현재 =in.nextBigInteger();
		BigInteger bi_진힘반_1업폭 = in.nextBigInteger();
		String 진힘반_1업비용 =in.next();
		BigInteger bi_진힘반_1업비용 = new BigInteger( Integer.toString(Integer.parseInt(진힘반_1업비용.substring(0, 진힘반_1업비용.length()-1))));
		for (int i=0; i< (진힘반_1업비용.charAt(진힘반_1업비용.length()-1)-96); i++)
			{bi_진힘반_1업비용 = bi_진힘반_1업비용.multiply(BigInteger.valueOf(1000));}
		//System.out.println();
		//System.out.println();
////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//System.out.println("진폭칼 ");
		//System.out.println("현재 %: / 1업 폭 / 1업 비용");
		BigInteger bi_진폭칼_현재 =in.nextBigInteger();
		BigInteger bi_진폭칼_1업폭 = in.nextBigInteger();
		String 진폭칼_1업비용 =in.next();
		BigInteger bi_진폭칼_1업비용 = new BigInteger( Integer.toString(Integer.parseInt(진폭칼_1업비용.substring(0, 진폭칼_1업비용.length()-1))));
		for (int i=0; i< (진폭칼_1업비용.charAt(진폭칼_1업비용.length()-1)-96); i++)
			{bi_진폭칼_1업비용 = bi_진폭칼_1업비용.multiply(BigInteger.valueOf(1000));}
		//System.out.println();
		//System.out.println();

//////////////////////////////////////////////////////////////////////////////////////////////

		BigInteger temp = new BigInteger("10000000000000");

		/*System.out.println("("+bi_십자가_1업폭+"+"+bi_십자가_현재+")/"+bi_십자가_현재 +"/"+bi_십자가_1업비용);
		System.out.println("("+bi_진힘반_1업폭+"+"+bi_진힘반_현재+")/"+bi_진힘반_현재 +"/"+bi_진힘반_1업비용);
		System.out.println("("+bi_진폭칼_1업폭+"+"+bi_진폭칼_현재+")/"+bi_진폭칼_현재 +"/"+bi_진폭칼_1업비용);


		System.out.println((bi_십자가_1업폭.add(bi_십자가_현재)).multiply(temp).divide(bi_십자가_현재));
		System.out.println((bi_진힘반_1업폭.add(bi_진힘반_현재)).multiply(temp).divide(bi_진힘반_현재));
		System.out.println((bi_진폭칼_1업폭.add(bi_진폭칼_현재)).multiply(temp).divide(bi_진폭칼_현재));
		*/
		BigInteger 십자가효율 = (bi_십자가_1업폭).multiply(temp).divide(bi_십자가_현재).divide(bi_십자가_1업비용);
		BigInteger 진힘반효율 = (bi_진힘반_1업폭).multiply(temp).divide(bi_진힘반_현재).divide(bi_진힘반_1업비용);
		BigInteger 진폭칼효율 = (bi_진폭칼_1업폭).multiply(temp).divide(bi_진폭칼_현재).divide(bi_진폭칼_1업비용);
		
		
		System.out.printf("십자가 효율 : %20d\n", 십자가효율);
		System.out.printf("진힘반 효율 : %20d\n", 진힘반효율);
		System.out.printf("진폭칼 효율 : %20d\n", 진폭칼효율);
				
	}//end main
}
