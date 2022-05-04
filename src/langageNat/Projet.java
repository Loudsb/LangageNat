package langageNat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Projet {
	/*
	 * 1. Parcourir le fichier du premier mot et stocker les relations r_isa (id = 6) dans une liste
	 * 2. Parcourir le fichier du deuxième mot et regarder lesquels sont en commun
	 *
	 * 
	 */
	
	// Récupère le fichier csv des relations du mot indiqué dans l'ordinateur
	public static File getFileRelation(String mot) {
		String ficMotRel = mot + "Rel.csv";
		File CSVFileRel = new File("/Users/marie-lou/eclipse-workspace/langageNat/" + ficMotRel);
		return CSVFileRel;
	}
	
	
	// Récupère le fichier csv des mots du mot indiqué dans l'ordinateur
	public static File getFileMot(String mot) {
		String ficMot = mot + "Mot.csv";
		File CSVFileMot = new File("/Users/marie-lou/eclipse-workspace/langageNat/" + ficMot);
		return CSVFileMot;
	}
	
	
	// Met les infos du fichier csv rentré en paramètre dans un tableau
	public static String[][] putRelationsInArray(File f){
		Scanner sc = null;
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    sc.useDelimiter(";");
	   
        //Initialise un compteur de lignes
	    int compteurLigne = 0;
        
	    
        int nbLigne = 0;
        while(sc.hasNextLine()) {
        	nbLigne++;
        }
        
        final int nbColonnes = 6;
        
        
        // Initialise un tableau pour mettre les infos qui nous interressent
        String[][] infosRelationMot = new String[nbLigne][nbColonnes];
	    
        while(sc.hasNextLine()) {
	    	String csvLigne = sc.nextLine();
	    	
	    	if (!csvLigne.isEmpty()) {
	    		// Initialise un scanner pour interpréter la ligne.
                Scanner ligneScan = new Scanner(csvLigne);
                ligneScan.useDelimiter(";");
             
                
                // Lis chaque champ de la ligne
                String relation = ligneScan.next().trim();
                String rid = ligneScan.next().trim();
                String node1 = ligneScan.next().trim();
                String node2 = ligneScan.next().trim();
                String type = ligneScan.next().trim();
                String w = ligneScan.next().trim();
                
                // Rentre les infos dans un tableau
                infosRelationMot[compteurLigne][0] = relation;
                infosRelationMot[compteurLigne][1] = rid;
                infosRelationMot[compteurLigne][2] = node1;
                infosRelationMot[compteurLigne][3] = node2;
                infosRelationMot[compteurLigne][4] = type;
                infosRelationMot[compteurLigne][5] = w;
                
                
                compteurLigne++;
                
                
                ligneScan.close();
                          
	    	}
	    	
	    }
        
	    sc.close();
	    
	    return infosRelationMot;
	}
	
	// Met les infos du fichier csv rentré en paramètre dans un tableau
	public static String[][] putMotsInArray(File f){
		Scanner sc = null;
		try {
			sc = new Scanner(f);			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    sc.useDelimiter(";");
		   
	    //Initialise un compteur de lignes
	    int compteurLigne = 0;
	        	    
	    int nbLigne = 0;
	    while(sc.hasNextLine()) {
        	nbLigne++;
        }
	        
	    final int nbColonnes = 6;
	        
	        
        // Initialise un tableau pour mettre les infos qui nous interressent
	    String[][] infosMot = new String[nbLigne][nbColonnes];
		    
	    while(sc.hasNextLine()) {
	    	String csvLigne = sc.nextLine();
	    	
		   	if (!csvLigne.isEmpty()) {
		   		// Initialise un scanner pour interpréter la ligne.
		   		Scanner ligneScan = new Scanner(csvLigne);
		   		ligneScan.useDelimiter(";");
	             
	                
	            // Lis chaque champ de la ligne
	            String element = ligneScan.next().trim();
	            String eid = ligneScan.next().trim();
	            String name = ligneScan.next().trim();
	            String type = ligneScan.next().trim();
	            String w = ligneScan.next().trim();
	            String formatedname = ligneScan.next().trim();
	                
	            // Rentre les infos dans un tableau
	            infosMot[compteurLigne][0] = element;
	            infosMot[compteurLigne][1] = eid;
	            infosMot[compteurLigne][2] = name;
	            infosMot[compteurLigne][3] = type;
	            infosMot[compteurLigne][4] = w;
	            infosMot[compteurLigne][5] = formatedname;
	                
	                
	            compteurLigne++;
	                
	                
	            ligneScan.close();
	                	          
		    }
		   	
	    }
	        
	    sc.close();
		    
	    return infosMot;
	}
		
	// Prend un fichier et met l'id du mot et e la relation dans un tableau
	public static ArrayList<String> tri(File f) throws IOException{
		ArrayList<String> al = new ArrayList<String>();
		
		//Met l'id du mot et le numéro de la relation dans un tableau
	    BufferedReader CSVFile = new BufferedReader(new FileReader(f));
	    String dataRow = CSVFile.readLine();
	    while (dataRow != null)
	    {
	        String[] dataArray1 = dataRow.split(";");
	        
	        String node2 = dataArray1[3];
        	String type = dataArray1[4];
        	
        	al.add(node2);
        	al.add(type);
	        
        	/*for (String item1:dataArray1)
	        {
	        	if(!item1.equals("r")) {
	        		al1.add(item1);
	        	}
	        }*/

	        dataRow = CSVFile.readLine();
	    }

	    CSVFile.close();
		
		return al;
		
	}
	
	// Compare les 2 fichiers et donne la liste des id des mots et du type de relation
	public static ArrayList<String> compare(ArrayList<String> al1, ArrayList<String> al2) throws IOException {
		//Tableau des mots en communs
		ArrayList<String> communs=new ArrayList<String>();
	     
	     for(String element:al1) {
	    	 if(al2.contains(element) && !communs.contains(element) && (al1.indexOf(element)%2 == 0)) { //Si le deuxième fichier contient aussi cet élément et que c'est bien un node2
		    	 communs.add(element);
		     }
	     }
	     
	     System.out.println("Tableau des mots communs : " + communs);
	     
	     return communs;
	     
	    	 
	}
	
	public static ArrayList<String> significationMots(ArrayList<String> al, File f) throws IOException {
		//Trier le fichier pour avoir l'id des mots et leur sigification
		ArrayList<String> mots = new ArrayList<String>();
		
		BufferedReader CSVFile = new BufferedReader(new FileReader(f));
	    String dataRow = CSVFile.readLine();
	    while (dataRow != null)
	    {
	        String[] dataArray = dataRow.split(";");
	        
	        String eid = dataArray[1];
        	String name = dataArray[2];
        	
        	if(name == null || name.contains(">")) { //Ne pas prendre le premier mot (eid)
        		name = dataArray[5];
        	}
        	
        	mots.add(eid);
        	mots.add(name);
        	
	        dataRow = CSVFile.readLine();
	    }
	     CSVFile.close();

	     //System.out.println("Tableau des mots du premier fichier : " + mots);
	     
	     //Pour chaque mot de la liste, faire une autre liste avec leur traduction
	     ArrayList<String> alTrad = new ArrayList<String>();
	     
	     for(String el: al) {
	    	 if(al.indexOf(el) != 0) {
	    		 int place = mots.indexOf(el);
	    		 String trad = mots.get(place + 1);
	    		 alTrad.add(trad);
	    	 }
	     }
	     
	     //System.out.println("Traduction des mots communs : " + alTrad);
	     
	     return alTrad;
	}
	
	public static void main(String[] args) throws IOException {
		
		InputStreamReader isr = new InputStreamReader(System.in); //Pour pouvoir utiliser l'entrée clavier
		BufferedReader in = new BufferedReader(isr);
		
		
		/* 1. Récupérer les 2 fichiers csv du premier mot */
		System.out.print("Donnez le premier mot: ");
		String mot1 = in.readLine();
		
		File CSVFile1Rel = getFileRelation(mot1);
		File CSVFile1Mot = getFileMot(mot1);
		
		System.out.println("~~ Fichiers du premier mot récupérés ~~");
		
		/* 2. Connaitre la relation voulu entre les deux mots */
		
		System.out.print("Donnez la relation parmis r_isa | r_has_part | r_holo : ");
		String relation = in.readLine();
		System.out.println(relation);
		
		/* 2. Récupérer les 2 fichiers csv du deuxième mot */
 		System.out.print("Donnez le deuxième mot: ");
 		String mot2 = in.readLine();
 		
 		File CSVFile2Rel = getFileRelation(mot2);
 		//File CSVFile2Mot = getFileMot(mot2);
 		
		System.out.println("~~ Fichiers du deuxième mot récupérés~~");

 		
 		/* 3. Tableau qui contient id du mot + numéro de relation pour chaque mot */
		
		
		ArrayList<String> alMot1 = tri(CSVFile1Rel);
		
		System.out.println("Tableau du premier mot : " + alMot1);
		
	    ArrayList<String> alMot2 = tri(CSVFile2Rel);
		
	    System.out.println("Tableau du deuxième mot : " + alMot1);
	    
		/* 3. Trouver l'id des mots en communs aux deux fichiers */
		
		ArrayList<String> communs= compare(alMot1, alMot2);
		
		
		/* 4. Trouver leur nom en français */
		
		ArrayList<String> trad = significationMots(communs, CSVFile1Mot);
		
		/* 5. La liste des relations avec leur nom */
		ArrayList<String> nomRel = new ArrayList<String>();
		
		nomRel.add(0,"r_associated");
		nomRel.add(1,"r_raff_sem");
		nomRel.add(2, "r_raff_morpho");
		nomRel.add(3, "r_domain");
		nomRel.add(4, "r_pos");
		nomRel.add(5, "r_syn");
		nomRel.add(6, "r_isa");
		nomRel.add(7, "r_anto");
		nomRel.add(8, "r_hypo");
		nomRel.add(9, "r_has_part");
		nomRel.add(10, "r_holo");
		nomRel.add(11, "r_locution");
		nomRel.add(12, "r_flpot");
		nomRel.add(13, "r_agent");
		nomRel.add(14, "r_patient");
		nomRel.add(15, "r_lieu");
		nomRel.add(16, "r_instr");
		nomRel.add(17, "r_carac");
		nomRel.add(18, "r_data");
		nomRel.add(19, "r_lemma");
		nomRel.add(20, "r_has_magn");
		nomRel.add(21, "r_has_antimagn");
		nomRel.add(22, "r_family");

		int indexR = nomRel.indexOf(relation);
		String indexRel = Integer.toString(indexR);
		
		System.out.println("Le numéro de la relation est : " + indexRel);
		
		/* Dans le cas où la relation est r_isa */
		// pour avoir le type de relation on cherche l'index du mot dans le tableau de base et on fait +1)
		
		// Si le mot1 r_isa x et x r_isa mot2 alors mot1 r_isa mot2
		
		if(relation.equals("r_isa")) {
			System.out.println("Je suis dans le if !");
			for(String motCom:communs) {
				int index1 = alMot1.indexOf(motCom);
				int index2 = alMot2.indexOf(motCom);
				String indexRel1 = alMot1.get(index1+1);
				//System.out.println("Le type de la relation du mot 1 est : " + indexRel1);
				String indexRel2 = alMot2.get(index2+1);
				//System.out.println("Le type de la relation du mot 2 est : " + indexRel2);
				
				if(indexRel1.equals(indexRel) && indexRel2.equals(indexRel)) {
					int i = communs.indexOf(motCom);
					String motTrad = trad.get(i);
					System.out.println(mot1 + " " + relation + " " + mot2 + " car : " + mot1 + " " + relation + " " + motTrad + " " + relation + " " + mot2);
				}
			}
			
		}
     
	}
	 

}

// Si a est en relation avec b et b est en relation avec c alors a est en relation avec c 

//Faire les tableaux pour les 3 autres fichiers
//Comparer les fichier des relations pour voir quels mots en commun ont les deux fichiers (fichiers relations des deux mots)

