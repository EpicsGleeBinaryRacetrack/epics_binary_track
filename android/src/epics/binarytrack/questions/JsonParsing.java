import java.io.InputStream;
import net.sf.json.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.io.IOUtils;


//these are for the default categories. if they are true then they should be added to the question set
 

public class JsonParsing {
  
  public static boolean division = false;  
  public static boolean dec_to_bin = false;
  public static boolean addition = false;
  public static boolean subtraction = false;
  public static boolean multiplication = false;
  public static boolean bin_to_dec = false;
//this is the list of questions you can use they are of type question
  public static ArrayList<MultiChoiceQuestion> list = new ArrayList<MultiChoiceQuestion>();
  
    public static void main(String[] args) throws Exception
    {      
      //this reads in the file then parses it to get the questions
      InputStream is = JsonParsing.class.getResourceAsStream("questions.txt"); //change this file name!!!!
      String jsonTxt = IOUtils.toString( is );
      JSONObject json = (JSONObject) JSONSerializer.toJSON( jsonTxt ); 
      JSONObject user_cat = json.getJSONObject("user categories"); //get question categories
      JSONObject user_ques = json.getJSONObject("questions"); //get all the user questions
      
      JSONArray categories = user_cat.names();  //gets all categories as list
     
      for(int i = 0; i < categories.size();i++) //goes through user categories finding questions
      {
        String cat = categories.getString(i);//string for category from user categories 
              
        if( user_cat.getString(cat).equals("1") || user_cat.getString(cat).equals("true")) //make sure category is turned on
        {        
          JSONObject category = user_ques.getJSONObject(cat); //get object/question list from current category
          Iterator ques = category.keys();
          while(ques.hasNext())
          {
            String send = ques.next().toString();
           // getQuestions(category.getJSONArray(send),send);
            list.add(getQuestions(category.getJSONArray(send),send));   //adds new question to list
          }
        }
      }
      
      
      JSONObject default_cat = json.getJSONObject("default categories");
    
      //this sets the default categories to true or false
      if(default_cat.getString("division").equals("1") || default_cat.getString("division").equals("true")) {
        division = true;
      }
      if(default_cat.getString("decimal to binary").equals("1") || default_cat.getString("decimal to binary").equals("true")) {
        dec_to_bin = true;
      }
      if(default_cat.getString("addition").equals("1") || default_cat.getString("addition").equals("true")) {
        addition = true;
      }
      if(default_cat.getString("subtraction").equals("1") || default_cat.getString("subtraction").equals("true")) {
        subtraction = true;
      }
      if(default_cat.getString("multiplication").equals("1") || default_cat.getString("multiplication").equals("true")) {
        multiplication = true;
      }
      if(default_cat.getString("binary to decimal").equals("1") || default_cat.getString("binary to decimal").equals("true")) {
        bin_to_dec = true;
      }
          
      System.out.println("done");
    }
    
    
    //this method creates the questions  if there is an error it's most likley i did something wrong here!!
    public static MultiChoiceQuestion getQuestions(JSONArray answers, String question)
    {      
      MultiChoiceQuestion q = new MultiChoiceQuestion(answers.size());
      q.setQuestion(question);
  
      for(int i = 0; i < answers.size();i++)
      {
        q.addAnswers(answers.getString(i));
      }
      return q;
    }
    
    
    public static ArrayList getList()
     {
       return list;
     }
}
