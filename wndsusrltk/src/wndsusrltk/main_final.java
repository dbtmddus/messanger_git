package wndsusrltk;

import java.math.BigInteger;
import java.util.Scanner;

public class main_final {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		//System.out.println("���ڰ� ");
		//System.out.println("���� %: / 1�� �� / 1�� ���");
		BigInteger bi_���ڰ�_���� =in.nextBigInteger();
		BigInteger bi_���ڰ�_1���� = in.nextBigInteger();
		String ���ڰ�_1����� =in.next();
		BigInteger bi_���ڰ�_1����� = new BigInteger( Integer.toString
				(Integer.parseInt(���ڰ�_1�����.substring(0, ���ڰ�_1�����.length()-1))));
		for (int i=0; i< (���ڰ�_1�����.charAt(���ڰ�_1�����.length()-1)-96); i++)
			{bi_���ڰ�_1����� = bi_���ڰ�_1�����.multiply(BigInteger.valueOf(1000));}
		//System.out.println();
		//System.out.println();
////////////////////////////////////////////////////////////////////////////////////////////////////
		//System.out.println("������ ");
		//System.out.println("���� %: / 1�� �� / 1�� ���");
		BigInteger bi_������_���� =in.nextBigInteger();
		BigInteger bi_������_1���� = in.nextBigInteger();
		String ������_1����� =in.next();
		BigInteger bi_������_1����� = new BigInteger( Integer.toString(Integer.parseInt(������_1�����.substring(0, ������_1�����.length()-1))));
		for (int i=0; i< (������_1�����.charAt(������_1�����.length()-1)-96); i++)
			{bi_������_1����� = bi_������_1�����.multiply(BigInteger.valueOf(1000));}
		//System.out.println();
		//System.out.println();
////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//System.out.println("����Į ");
		//System.out.println("���� %: / 1�� �� / 1�� ���");
		BigInteger bi_����Į_���� =in.nextBigInteger();
		BigInteger bi_����Į_1���� = in.nextBigInteger();
		String ����Į_1����� =in.next();
		BigInteger bi_����Į_1����� = new BigInteger( Integer.toString(Integer.parseInt(����Į_1�����.substring(0, ����Į_1�����.length()-1))));
		for (int i=0; i< (����Į_1�����.charAt(����Į_1�����.length()-1)-96); i++)
			{bi_����Į_1����� = bi_����Į_1�����.multiply(BigInteger.valueOf(1000));}
		//System.out.println();
		//System.out.println();

//////////////////////////////////////////////////////////////////////////////////////////////

		BigInteger temp = new BigInteger("10000000000000");

		/*System.out.println("("+bi_���ڰ�_1����+"+"+bi_���ڰ�_����+")/"+bi_���ڰ�_���� +"/"+bi_���ڰ�_1�����);
		System.out.println("("+bi_������_1����+"+"+bi_������_����+")/"+bi_������_���� +"/"+bi_������_1�����);
		System.out.println("("+bi_����Į_1����+"+"+bi_����Į_����+")/"+bi_����Į_���� +"/"+bi_����Į_1�����);


		System.out.println((bi_���ڰ�_1����.add(bi_���ڰ�_����)).multiply(temp).divide(bi_���ڰ�_����));
		System.out.println((bi_������_1����.add(bi_������_����)).multiply(temp).divide(bi_������_����));
		System.out.println((bi_����Į_1����.add(bi_����Į_����)).multiply(temp).divide(bi_����Į_����));
		*/
		BigInteger ���ڰ�ȿ�� = (bi_���ڰ�_1����).multiply(temp).divide(bi_���ڰ�_����).divide(bi_���ڰ�_1�����);
		BigInteger ������ȿ�� = (bi_������_1����).multiply(temp).divide(bi_������_����).divide(bi_������_1�����);
		BigInteger ����Įȿ�� = (bi_����Į_1����).multiply(temp).divide(bi_����Į_����).divide(bi_����Į_1�����);
		
		
		System.out.printf("���ڰ� ȿ�� : %20d\n", ���ڰ�ȿ��);
		System.out.printf("������ ȿ�� : %20d\n", ������ȿ��);
		System.out.printf("����Į ȿ�� : %20d\n", ����Įȿ��);
				
	}//end main
}
