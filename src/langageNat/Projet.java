package langageNat;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
//import java.util.Scanner;

public class Projet {
	/*
	 * 1. Parcourir le fichier du premier mot et stocker les relations r_isa (id = 6) dans une liste
	 * 2. Parcourir le fichier du deuxième mot et regarder lesquels sont en commun
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
	
	
		
	// Prend le fichier relation du mot 1 et met l'id du mot et de la relation dans un tableau
	public static ArrayList<String> tri1(File f, String id) throws IOException{
		ArrayList<String> al = new ArrayList<String>();
		
		//Met l'id du mot et le numéro de la relation dans un tableau
	    BufferedReader CSVFile = new BufferedReader(new FileReader(f));
	    String dataRow = CSVFile.readLine();
	    while (dataRow != null)
	    {
	        String[] dataArray1 = dataRow.split(";");
	        
	        //Si la 4ème colonne ne contient pas le mot 1
	        if(!dataArray1[3].equals(id)) {
	        	String node2 = dataArray1[3];
	        	String type = dataArray1[4];
	        	
	        	//Même si le tableau contient déjà le mot c'est pas grave parce qu'il peut avoir une relation différente
	        	
	        	//if(!al.contains(node2)) { 
	            	al.add(node2);
	            	al.add(type);
	        	//}
	        }
	        
	        
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
	
	// Prend le fichier relation du mot 2 et met l'id du mot et de la relation dans un tableau
		public static ArrayList<String> tri2(File f, String id) throws IOException{
			ArrayList<String> al = new ArrayList<String>();
			
			//Met l'id du mot et le numéro de la relation dans un tableau
		    BufferedReader CSVFile = new BufferedReader(new FileReader(f));
		    String dataRow = CSVFile.readLine();
		    while (dataRow != null)
		    {
		        String[] dataArray1 = dataRow.split(";");
		        
		        if(!dataArray1[2].equals(id)) {
		        	String node2 = dataArray1[2];
		        	String type = dataArray1[4];
	        	
		        	//if(!al.contains(node2)) {
		        		al.add(node2);
		        		al.add(type);
		        	//}
		        }
		        
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
	
	// Compare les 2 fichiers et donne la liste des id des mots et du type de relation en communs
	public static ArrayList<String> compare(ArrayList<String> al1, ArrayList<String> al2) throws IOException {
		//Tableau des mots en communs
		ArrayList<String> communs=new ArrayList<String>();
	     
	     for(String element:al1) { //Pour chaque mot 
	    	 if(al2.contains(element) && !communs.contains(element) && (al1.indexOf(element)%2 == 0)) { //Si le deuxième fichier contient aussi cet élément et que c'est bien un node2
		    	 communs.add(element);
		     }
	     }
	     
	     System.out.println("Tableau des mots communs : " + communs);
	     
	     return communs;
	     
	    	 
	}
	
	public static ArrayList<String> significationMots(ArrayList<String> alCommuns, File f) throws IOException {
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
	     
	     
	     //Pour chaque mot de la liste des mots communs, faire une autre liste avec leur traduction
	     ArrayList<String> alTrad = new ArrayList<String>();
	     
	     for(String el: alCommuns) {
	    	 //if(alCommuns.indexOf(el) == 0) {
	    		 int place = mots.indexOf(el);
	    		 String trad = mots.get(place + 1);
	    		 alTrad.add(trad);
	    	 //}
	     }
	     //System.out.println("Tableau des mots communs : " + alCommuns);
	     //System.out.println("Traduction des mots communs : " + alTrad);
	     //System.out.println("Taille de la liste de mots communs : " + alCommuns.size());
	     //System.out.println("Taille de leur traduction : " + alTrad.size());

	     
	     return alTrad;
	}
	
	
	//Fonction qui trouve l'id d'un mot
	public static String findID(File f) throws IOException {
		
		BufferedReader CSVFile = new BufferedReader(new FileReader(f));
	    String dataRow = CSVFile.readLine();
	    
	    //On passe à la deuxième ligne parce que la première c'est l'entête
	    dataRow = CSVFile.readLine();
	    
	    //On fait un tableau avec la première ligne
	    String[] dataArray1 = dataRow.split(";");
        
        String id = dataArray1[1];
	    
		
		CSVFile.close();
		return id;
		
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
		
		
		/* 3. Récupérer les 2 fichiers csv du deuxième mot */
 		System.out.print("Donnez le deuxième mot: ");
 		String mot2 = in.readLine();
 		
 		File CSVFile2Rel = getFileRelation(mot2);
 		File CSVFile2Mot = getFileMot(mot2);
 		
		System.out.println("~~ Fichiers du deuxième mot récupérés~~");
		
		
		/* 4. La liste des relations avec leur nom */
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
		String RelID = Integer.toString(indexR);
		
		System.out.println("Le numéro de la relation est : " + RelID);
		
		/* 5. Récupérer l'id des mots */
		
		String id1 = findID(CSVFile1Mot);

		System.out.println("Id du mot 1 : " + id1);
		
		String id2 = findID(CSVFile2Mot);

		System.out.println("Id du mot 2 : " + id2);
		
		
 		/* 6. Tableau qui contient id du mot + numéro de relation pour chaque mot */
		
		ArrayList<String> alMot1 = tri1(CSVFile1Rel, id1);
		
		System.out.println("Tableau du premier mot : " + alMot1);
		
	    ArrayList<String> alMot2 = tri2(CSVFile2Rel, id2);
		
	    System.out.println("Tableau du deuxième mot : " + alMot2);
	    
	    //Test pour voir si toutes les occurence de 55488 sont dans le tableau du mot 2
	    System.out.println("Le tableau contient le mot 55488: " + alMot2.contains("55488"));
	    while(alMot2.contains("55488")) {
	    	int index = alMot2.indexOf("55488");
		    String num = alMot2.get(index + 1);
		    System.out.println("num de la relation associé :" + num);
		    alMot2.remove(index);
	    }
	    
	    
	    
		/* 7. Trouver l'id des mots en communs aux deux fichiers */
		
		ArrayList<String> communs= compare(alMot1, alMot2);
		
		
		/* 8. Création du tableau qui contient le nom des mots d'une liste en français */
		
		ArrayList<String> trad = significationMots(communs, CSVFile1Mot);
		
		/* 9. Créer un compteur pour pouvoir afficher le nombre de réponses */
		
		int compteurReponses = 0;
		
		System.out.println(mot1 + " " + relation + " " + mot2 + " :");
		/* 10. Dans le cas où la relation est r_isa */
		// pour avoir le type de relation on cherche l'index du mot dans le tableau de base et on fait +1)
		
		// Si le mot1 r_isa x et x r_isa mot2 alors mot1 r_isa mot2
		
		if(relation.equals("r_isa")) {
			
			for(String motCom:communs) { //Pour chaque mot commun
				//System.out.println("L'id du mot en commun est : " + motCom);
				

				//Pour avoir la traduction du mot en commun
				int indexMotCom = communs.indexOf(motCom);
				String motTrad = trad.get(indexMotCom);
				//System.out.println("Le mot commun est : " + motTrad);
	
				
				
				int index1 = -1;
				int index2 = -1;
				
				ArrayList<String> alMot2copie = new ArrayList<String>();
				
				while(alMot1.contains(motCom)) { //Tant qu'il y a des occurences du mot en commun dans la liste des mots 1
				
					//On cherche l'index de ce mot commun dans le tableau du mot 1
					index1 = alMot1.indexOf(motCom);
					
					
					//On crée une copie de la liste du mot 2 pour pouvoir la réinitialiser à chaque tour
					alMot2copie = alMot2;
					
					while(alMot2copie.contains(motCom)) {
						//On cherche l'index de ce mot commun dans le tableau du mot 2
						index2 = alMot2.indexOf(motCom);
						//System.out.println("Index du mot en commun dans le tableau du mot 1 : " + index1);
						//System.out.println("Index du mot en commun dans le tableau du mot 2 : " + index2);
						
						
						//On regarde le type de relation que le mot en commun a avec les mots 1 et 2
						String RelID1 = alMot1.get(index1+1);
						String RelID2 = alMot2.get(index2+1);
						//System.out.println("id de la relation avec le mot 1 : " + RelID1);
						//System.out.println("id de la relation avec le mot 2 : " + RelID2);

						/* 1ère manière : A r_isa B r_isa C */
						if(RelID1.equals(RelID) && RelID2.equals(RelID)) {
							compteurReponses++;
							System.out.println(" -> " + mot1 + " " + relation + " " + motTrad + " " + relation + " " + mot2);
						}
						
						alMot2copie.set(index2, "x"); //On remplace le mot par un x pour qu'il ne le prenne plus en compte
					
					}
					
					//On supprime le mot de la liste pour voir s'il y en a d'autre d'identique
					alMot1.set(index1, "x");

				}
				
			}
			
		}
		
		//Afficher le nombre de réponses
		if (compteurReponses == 0) {
			System.out.println("Il n'y a aucune réponse pour votre requête.");
		} else if (compteurReponses == 1) {
			System.out.println("Il y a une réponse.");
		} else {
			System.out.println("Il y a " + compteurReponses + " réponses.");
		}
     
	}
	 

}

// Si a est en relation avec b et b est en relation avec c alors a est en relation avec c 

//Faire les tableaux pour les 3 autres fichiers
//Comparer les fichier des relations pour voir quels mots en commun ont les deux fichiers (fichiers relations des deux mots)

