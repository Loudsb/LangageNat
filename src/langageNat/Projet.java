package langageNat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
		
		
	
	public static void main(String[] args) throws IOException {
		
		InputStreamReader isr = new InputStreamReader(System.in); //Pour pouvoir utiliser l'entrée clavier
		BufferedReader in = new BufferedReader(isr);
		
		
		/* 1. Récupérer les 2 fichiers csv du premier mot */
		System.out.println("Donnez le premier mot:");
		String mot1 = in.readLine();
		
		File CSVFile1Rel = getFileRelation(mot1);
		File CSVFile1Mot = getFileMot(mot1);
		
		System.out.println("Fichiers du premier mot récupérés");
		
		//String ficMot1Rel = mot + "Rel.csv";
		//String ficMot1Mot = mot + "Mot.csv";
		
		//File CSVFile1Rel = new File("/Users/marie-lou/eclipse-workspace/langageNat/" + ficMot1Rel);
		//File CSVFile1Mot = new File("/Users/marie-lou/eclipse-workspace/langageNat/" + ficMot1Mot);
		
		/* 2. Récupérer les 2 fichiers csv du deuxième mot */
 		System.out.println("Donnez le deuxième mot:");
 		String mot2 = in.readLine();
 		
 		File CSVFile2Rel = getFileRelation(mot2);
 		File CSVFile2Mot = getFileMot(mot2);
 		
		System.out.println("Fichiers du deuxième mot récupérés");

 		
 		
		/* 3. mettre l'id des relations et l'id du mot dans un tableau pour le premier mot */
		
 		String[][] infosRelationMot1 = putRelationsInArray(CSVFile1Rel);
 		String[][] infosRelationMot2 = putRelationsInArray(CSVFile2Rel);
 		
		System.out.println("Tableaux de relations créés");

 		
 		String[][] infosMot1 = putRelationsInArray(CSVFile1Mot);
 		String[][] infosMot2 = putRelationsInArray(CSVFile2Mot);
 		
 		System.out.println("Tableaux de mots créés");
 		
 		
        System.out.print("id du mot : " + infosMot1[10][1] + " | mot : " + infosMot1[10][2]);
        System.out.print("id de la relation : " + infosRelationMot1[10][1] + " | id du mot en relation : " + infosRelationMot1[10][3]);

        System.out.print("id du mot : " + infosMot2[10][1] + " | mot : " + infosMot2[10][2]);
        System.out.print("id de la relation : " + infosRelationMot2[10][1] + " | id du mot en relation : " + infosRelationMot2[10][3]);

     
	}
	 

}

// Si a est en relation avec b et b est en relation avec c alors a est en relation avec c 

//Faire les tableaux pour les 3 autres fichiers
//Comparer les fichier des relations pour voir quels mots en commun ont les deux fichiers (fichiers relations des deux mots)

