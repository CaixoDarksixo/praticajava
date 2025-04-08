package praticajava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Save {
	public Save() {
		
	}
	
	
	public static void SaveNow() {
		for(int i = 0; i < Main.skills.size(); i++) {
			saveState(Main.skills);
		}
	}
	
	public static void applySave(String str) {
		if(str == "" || str == "nofile") {}else {
		
		System.out.println(str);
		String[] spl = str.split("sx/sx");
		
		
		for(int i = 0; i < Main.skills.size(); i++)
			Main.skills.remove(Main.skills.get(i));
	
		for(int i = 0; i < spl.length; i++) {
			String[] spl2 = spl[i].split("xhx");
			System.out.println(spl[i]+"????"+spl2);
			int[] in = new int[40];
			for(int j = 0; j < 40; j++) { in[j] = Integer.parseInt(spl2[5+j]);}
			Skill nskill = new Skill( spl2[0],Integer.parseInt(spl2[1]),Integer.parseInt(spl2[2]),Integer.parseInt(spl2[3]),Integer.parseInt(spl2[4]),in);
			Main.skills.add(nskill);
			}
		}
	}
	
	public static void saveState(List<Skill> skills) {
		BufferedWriter write = null;
		
		try {
			write = new BufferedWriter(new FileWriter("save.txt"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < skills.size(); i++) {
			String current = ""+skills.get(i).getSkill()+"xhx"+skills.get(i).getX()+"xhx"+skills.get(i).getTrueY()+"xhx"+skills.get(i).getLevel()+"xhx"+skills.get(i).getBgIndex();
			int[] ind = skills.get(i).getIndexes();
			for(int j = 0; j < ind.length; j++)
				current+= "xhx"+ind[j];
			try {
				write.write(current);
				if(i < skills.size() - 1)
					write.newLine();
				
			}catch(IOException e) {}
		}
		try {
			write.flush();
			write.close();
		}catch(IOException e) {}
		
	
	}
	
	public static String loadSave() {
		String line = "";
		File file = new File("save.txt");
		if(file.exists()) {
			try {
				String singleLine = null;
				BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
				try {
					while((singleLine = reader.readLine()) != null) {
						String[] trans = singleLine.split("xhx");
						//char[] val = trans[1].toCharArray();
                       /// trans[1] = "";
						//for(int i = 0; i < val.length; i++) {
							//val[i]-=encode;
							//trans[1]+=val[i];
							
							
						//}
						line+=trans[0]; 
						for(int i = 1; i < trans.length; i++) {
						line+="xhx";
						line+=trans[i];  
						}
						//line+=trans[1];
						line+="sx/sx";
						System.out.println("line:"+line);
					}
				}catch(IOException e) {	System.out.println("no line");}
				
			
				
			}catch(FileNotFoundException e) { System.out.println("Save n existe");return "nofile";}
		}
		return line;
		
	}
}
