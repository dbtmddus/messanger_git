import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class Solution {
	static String[] size_one = {"H", "B", "C", "N", "O", "F", "P", "S", "K", "V",  "Y", "I", "W","U" };
	static String[] size_two = {"He", "Li", "Be", "Ne", "Na", "Mg", "Al",
			"Si",  "Cl", "Ar", "Ca", "Sc", "Ti",  "Cr", "Mn", "Fe",
			"Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr",
			"Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb",
			"Te",  "Xe", "Cs", "Ba", "Hf", "Ta", "Re", "Os", "Ir", "Pt", "Au",
			"Hg", "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra", "Rf", "Db", "Sg",
			"Bh", "Hs", "Mt", "Ds", "Rg", "Cn", "Fl", "Lv", "La", "Ce", "Pr", "Nd",
			"Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "Ac",
			"Th", "Pa",  "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm", "Md",
			"No", "Lr"};

	static Vector<String> v_one = new Vector<String>(0);
	static Vector<String> v_two = new Vector<String>(0);

	static String str;
	static boolean b_is_available;

	public static void main(String[] args) throws NumberFormatException, Exception {

		insert_one();
		insert_two();
		Scanner in = new Scanner(System.in);
		int total_cycle = Integer.parseInt(in.nextLine());
		
		for (int cycle = 0 ; cycle<total_cycle ; cycle++){
			str = in.nextLine();
			b_is_available = false;
			inspect(0);

			String result;
			if (b_is_available){
				result = "YES";
			}else{
				result = "NO";
			}
			System.out.println("Case #"+(cycle+1));	
			System.out.println(result);	
		}
	}// end main

	public static void inspect(int _index){	//8ÃÊ, 92Á¡

		if (_index == str.length()){
			b_is_available =true;
		}else{
			String o = Character.toString(str.charAt(_index));
			if (v_one.contains(o)){
				inspect((_index+1));
			}

			if (_index<str.length()-1){
				String t = o+Character.toString(str.charAt(_index+1));
				if (v_two.contains(t)){
					inspect((_index+2));
				}
			}
		}
	}
	public static void insert_one(){
		for (int i=0 ; i<size_one.length; i++){
			v_one.addElement(size_one[i]);
			v_one.addElement(size_one[i].toLowerCase());
		}
	}
	static public void insert_two(){
		for (int i=0 ; i<size_two.length; i++){
			String str = size_two[i];
			v_two.addElement(str.toUpperCase());
			v_two.addElement(str.toLowerCase());

			String first = Character.toString(str.charAt(0));
			String second = Character.toString(str.charAt(1));
			v_two.addElement(first.toLowerCase() + second.toUpperCase() );
			v_two.addElement(first.toUpperCase() + second.toLowerCase() );
		}
	}

	
}//end
