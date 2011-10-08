import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

	public class DarkCraft extends Plugin{
		String name = "DarkCraft";
		String version = "1.0";
		String author = "Darkdiplomat";
		static Logger log = Logger.getLogger("Minecraft");
		Server server = etc.getServer();
		ArrayList<String> recipes;

	  public void initialize(){
		  createRecipeFile();
		  Recipes();
	  }

	  public void enable(){
		  log.info(name + " version " + version + " by " + author + " is enabled!" );
	  }

	  public void disable(){
		  log.info(name + " version " + version + " is disabled!");
	  }
	  
	  public void createRecipeFile(){
			File Dir = new File("plugins/config/DarkCraft");
			//create Recipe directory if it does not exits
			if(!Dir.exists()){
				Dir.mkdir();
			}
			//create Recipe File if it doesn't exist
			File DCRFile = new File(Dir + File.separator + "DCrecipes.txt");
			if(!DCRFile.exists()){
				try{
					DCRFile.createNewFile();
					BufferedWriter writer = new BufferedWriter(new FileWriter(DCRFile));
					writer.write("# 1 SnowBlock next to 1 Water Bucket = 1 IceBlock"); writer.newLine();
					writer.write("79,1;WS;W,326,1,0;S,80,1,0"); writer.newLine();
					writer.write("# 1 Lighter = 20 Fire Blocks"); writer.newLine();
					writer.write("51,20;259,1,0"); writer.newLine();
					writer.write("#1 Log (damage 0) over 1 Fire Block = 1 CharCoal"); writer.newLine();
					writer.write("263,1,1;L,F;L,17,1,0;F,51,1,0"); writer.newLine();
					writer.write("#1 Pumpkin = 3 Pumpkin Seeds"); writer.newLine();
					writer.write("361,3;86,1,0;"); writer.newLine();
					writer.close();
				} catch (IOException e) {
					log.log(Level.SEVERE, "[DarkCraft] Failed to create \"DCrecipes.txt\"");
			}
		}
	}
	  
	  public void Recipes(){
		  //Get all recipes from the file
		  recipes = new ArrayList<String>();
		  try{
			  BufferedReader in = new BufferedReader(new FileReader("plugins/config/DarkCraft/DCrecipes.txt"));
			  String str;
			  while ((str = in.readLine()) != null) {
				  if (!str.startsWith("#")){
				 	recipes.add(str);
				  }
			  }
			  in.close();
		  }catch (IOException ioe){
			  log.info(ioe.toString());
		  }
		  //Process the recipes
		  for (int i = 0; i < recipes.size(); i++){
			  int reItem, reAmount, reDamage = 0;
			  boolean damagedresult = false;
			  Item item;
			  int[] stacks = new int[30];
			  String start = recipes.get(i);
			  String[] splitup = start.split(";");
			  String[] splititem = splitup[0].split(",");
			  Character A = null, B = null, C= null, D = null, E = null, F = null, G = null, H = null, I = null;
			  reItem = Integer.parseInt(splititem[0]);
			  reAmount = Integer.parseInt(splititem[1]);
			  if (splititem.length > 2){
				  reDamage = Integer.parseInt(splititem[2]);
				  damagedresult = true;
			  }
			  String[] splitrecipeP1 = splitup[1].split(",");
			  String Row1 = splitrecipeP1[0];
			  String Row2 = null;
			  String Row3 = null;
			  if (splitrecipeP1.length == 2){
				  Row2 = splitrecipeP1[1];
				  
			  }else if (splitrecipeP1.length == 3){
				  Row2 = splitrecipeP1[1];
				  Row3 = splitrecipeP1[2];
			  }
			  
			  
			  if(damagedresult){
				  item = new Item(reItem, reAmount, 0, reDamage);
			  }else{
				  item = new Item(reItem, reAmount);
			  }
			  int n = 0;
			  if (splitup.length == 2){
				  for (int j = 1; j < splitup.length; j++){
					  String[] getstacks = splitup[j].split(",");
					  stacks[n] = Integer.parseInt(getstacks[0]);
					  stacks[n+1] = Integer.parseInt(getstacks[1]);
				  }
			  }else{
				  for (int j = 2; j < splitup.length; j++){
					  String[] getstacks = splitup[j].split(",");
					  stacks[n] = Integer.parseInt(getstacks[1]);
					  stacks[n+1] = Integer.parseInt(getstacks[2]);
					  stacks[n+2] = Integer.parseInt(getstacks[3]);
					  n += 3;
				  }
			  }
			  String[] getChar;
			  if (splitup.length > 2){
				  getChar = splitup[2].split(",");
				  A = getChar[0].charAt(0);
			  }
			  if (splitup.length > 3){
				  getChar = splitup[3].split(",");
				  B = getChar[0].charAt(0);
			  }
			  if (splitup.length > 4){
				  getChar = splitup[4].split(",");
				  C = getChar[0].charAt(0);
			  }
			  if (splitup.length > 5){
				  getChar = splitup[5].split(",");
				  D = getChar[0].charAt(0);
			  }
			  if (splitup.length > 6){
				  getChar = splitup[6].split(",");
				  E = getChar[0].charAt(0);
			  }
			  if (splitup.length > 7){
				  getChar = splitup[7].split(",");
				  F = getChar[0].charAt(0);
			  }
			  if (splitup.length > 8){
				  getChar = splitup[8].split(",");
				  G = getChar[0].charAt(0);
			  }
			  if (splitup.length > 9){
				  getChar = splitup[9].split(",");
				  H = getChar[0].charAt(0);
			  }
			  if (splitup.length > 10){
				  getChar = splitup[10].split(",");
				  I = getChar[0].charAt(0);
			  }
			  if (splitup.length == 3){
				  if (splitrecipeP1.length == 1){
					  server.addRecipe(item, new Object[] { Row1, 
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2])
					  });
				  }else if (splitrecipeP1.length == 2){
					  server.addRecipe(item, new Object[] { Row1, Row2, 
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2])
					  });
				  }else if (splitrecipeP1.length == 3){
					  server.addRecipe(item, new Object[] { Row1, Row2, Row3, 
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2])
					  });
				  }
			  }else if (splitup.length == 4){
				  if (splitrecipeP1.length == 1){
					  server.addRecipe(item, new Object[] { Row1, 
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2]), 
							  Character.valueOf(B), new OItemStack(stacks[3],stacks[4], stacks[5])
					  });
				  }else if (splitrecipeP1.length == 2){
					  server.addRecipe(item, new Object[] { Row1, Row2,
							  A, new OItemStack(stacks[0],stacks[1], stacks[2]), 
							  B, new OItemStack(stacks[3],stacks[4], stacks[5])
					  });
				  }else if (splitrecipeP1.length == 3){
					  server.addRecipe(item, new Object[] { Row1, Row2, Row3, 
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2]), 
							  Character.valueOf(B), new OItemStack(stacks[3],stacks[4], stacks[5])
					  });
				  }
			  }else if (splitup.length == 5){
				  if (splitrecipeP1.length == 1){
					  server.addRecipe(item, new Object[] { Row1, 
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2]), 
							  Character.valueOf(B), new OItemStack(stacks[3],stacks[4], stacks[5]),
							  Character.valueOf(C), new OItemStack(stacks[6],stacks[7], stacks[8])
					  });
				  }else if (splitrecipeP1.length == 2){
					  server.addRecipe(item, new Object[] { Row1, Row2, 
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2]), 
							  Character.valueOf(B), new OItemStack(stacks[3],stacks[4], stacks[5]),
							  Character.valueOf(C), new OItemStack(stacks[6],stacks[7], stacks[8])
					  });
				  }else if (splitrecipeP1.length == 3){
					  server.addRecipe(item, new Object[] { Row1, Row2, Row3, 
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2]), 
							  Character.valueOf(B), new OItemStack(stacks[3],stacks[4], stacks[5]),
							  Character.valueOf(C), new OItemStack(stacks[6],stacks[7], stacks[8])
					  });
				  }
			  }else if (splitup.length == 6){
				  if (splitrecipeP1.length == 2){
					  server.addRecipe(item, new Object[] { Row1, Row2, 
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2]), 
							  Character.valueOf(B), new OItemStack(stacks[3],stacks[4], stacks[5]),
							  Character.valueOf(C), new OItemStack(stacks[6],stacks[7], stacks[8]),
							  Character.valueOf(D), new OItemStack(stacks[9],stacks[10], stacks[11])
					  });
				  }else if (splitrecipeP1.length == 3){
					  server.addRecipe(item, new Object[] { Row1, Row2, Row3, 
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2]), 
							  Character.valueOf(B), new OItemStack(stacks[3],stacks[4], stacks[5]),
							  Character.valueOf(C), new OItemStack(stacks[6],stacks[7], stacks[8]),
							  Character.valueOf(D), new OItemStack(stacks[9],stacks[10], stacks[11])
					  });
				  }
			  }else if (splitup.length == 7){
				  if (splitrecipeP1.length == 2){
					  server.addRecipe(item, new Object[] { Row1, Row2, 
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2]), 
							  Character.valueOf(B), new OItemStack(stacks[3],stacks[4], stacks[5]),
							  Character.valueOf(C), new OItemStack(stacks[6],stacks[7], stacks[8]),
							  Character.valueOf(D), new OItemStack(stacks[9],stacks[10], stacks[11]),
							  Character.valueOf(E), new OItemStack(stacks[12],stacks[13], stacks[14])
					  });
				  }else if (splitrecipeP1.length == 3){
					  server.addRecipe(item, new Object[] { Row1, Row2, Row3, 
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2]), 
							  Character.valueOf(B), new OItemStack(stacks[3],stacks[4], stacks[5]),
							  Character.valueOf(C), new OItemStack(stacks[6],stacks[7], stacks[8]),
							  Character.valueOf(D), new OItemStack(stacks[9],stacks[10], stacks[11]),
							  Character.valueOf(E), new OItemStack(stacks[12],stacks[13], stacks[14])
					  });
				  }
			  }else if (splitup.length == 8){
				  if (splitrecipeP1.length == 2){
					  server.addRecipe(item, new Object[] { Row1, Row2, 
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2]), 
							  Character.valueOf(B), new OItemStack(stacks[3],stacks[4], stacks[5]),
							  Character.valueOf(C), new OItemStack(stacks[6],stacks[7], stacks[8]),
							  Character.valueOf(D), new OItemStack(stacks[9],stacks[10], stacks[11]),
							  Character.valueOf(E), new OItemStack(stacks[12],stacks[13], stacks[14]),
							  Character.valueOf(F), new OItemStack(stacks[15],stacks[16], stacks[17])
					  });
				  }else if (splitrecipeP1.length == 3){
					  server.addRecipe(item, new Object[] { Row1, Row2, Row3, 
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2]), 
							  Character.valueOf(B), new OItemStack(stacks[3],stacks[4], stacks[5]),
							  Character.valueOf(C), new OItemStack(stacks[6],stacks[7], stacks[8]),
							  Character.valueOf(D), new OItemStack(stacks[9],stacks[10], stacks[11]),
							  Character.valueOf(E), new OItemStack(stacks[12],stacks[13], stacks[14]),
							  Character.valueOf(F), new OItemStack(stacks[15],stacks[16], stacks[17])
					  });
				  }
			  }else if (splitup.length == 9){
				  if (splitrecipeP1.length == 2){
					  server.addRecipe(item, new Object[] { Row1, Row2, 
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2]), 
							  Character.valueOf(B), new OItemStack(stacks[3],stacks[4], stacks[5]),
							  Character.valueOf(C), new OItemStack(stacks[6],stacks[7], stacks[8]),
							  Character.valueOf(D), new OItemStack(stacks[9],stacks[10], stacks[11]),
							  Character.valueOf(E), new OItemStack(stacks[12],stacks[13], stacks[14]),
							  Character.valueOf(F), new OItemStack(stacks[15],stacks[16], stacks[17]),
							  Character.valueOf(G), new OItemStack(stacks[18],stacks[19], stacks[20])
					  });
				  }else if (splitrecipeP1.length == 3){
					  server.addRecipe(item, new Object[] { Row1, Row2, Row3, 
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2]), 
							  Character.valueOf(B), new OItemStack(stacks[3],stacks[4], stacks[5]),
							  Character.valueOf(C), new OItemStack(stacks[6],stacks[7], stacks[8]),
							  Character.valueOf(D), new OItemStack(stacks[9],stacks[10], stacks[11]),
							  Character.valueOf(E), new OItemStack(stacks[12],stacks[13], stacks[14]),
							  Character.valueOf(F), new OItemStack(stacks[15],stacks[16], stacks[17]),
							  Character.valueOf(G), new OItemStack(stacks[18],stacks[19], stacks[20])
					  });
				  }
			  }else if (splitup.length == 10){
				  if (splitrecipeP1.length == 3){
					  server.addRecipe(item, new Object[] { Row1, Row2, Row3,
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2]), 
							  Character.valueOf(B), new OItemStack(stacks[3],stacks[4], stacks[5]),
							  Character.valueOf(C), new OItemStack(stacks[6],stacks[7], stacks[8]),
							  Character.valueOf(D), new OItemStack(stacks[9],stacks[10], stacks[11]),
							  Character.valueOf(E), new OItemStack(stacks[12],stacks[13], stacks[14]),
							  Character.valueOf(F), new OItemStack(stacks[15],stacks[16], stacks[17]),
							  Character.valueOf(G), new OItemStack(stacks[18],stacks[19], stacks[20]),
							  Character.valueOf(H), new OItemStack(stacks[21],stacks[22], stacks[23])
					  });
				  }
			  }else if (splitup.length == 11){
				  if (splitrecipeP1.length == 3){
					  server.addRecipe(item, new Object[] { Row1, Row2, Row3,
							  Character.valueOf(A), new OItemStack(stacks[0],stacks[1], stacks[2]),
							  Character.valueOf(B), new OItemStack(stacks[3],stacks[4], stacks[5]),
							  Character.valueOf(C), new OItemStack(stacks[6],stacks[7], stacks[8]),
							  Character.valueOf(D), new OItemStack(stacks[9],stacks[10], stacks[11]),
							  Character.valueOf(E), new OItemStack(stacks[12],stacks[13], stacks[14]),
							  Character.valueOf(F), new OItemStack(stacks[15],stacks[16], stacks[17]),
							  Character.valueOf(G), new OItemStack(stacks[18],stacks[19], stacks[20]),
							  Character.valueOf(H), new OItemStack(stacks[21],stacks[22], stacks[23]),
							  Character.valueOf(I), new OItemStack(stacks[24],stacks[25], stacks[26])
							  
					  });
				  }
			  }else{
				  server.addShapelessRecipe(item, new Object[] {new OItemStack(stacks[0], stacks[1], stacks[2])});
			  }
		  }
	  }
}
