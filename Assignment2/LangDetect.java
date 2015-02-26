import java.util.*;
import java.io.*;
import java.util.Scanner.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;

public class LangDetect {
	                
                        static String[] classvariables=new String[300];
                        static String[] acc_mod=new String[400];
			static String[] methods=new String[400]; 
			static String[] variables =new String[400];
			static String next;
			static String[] classnames=new String[400];
			static int i=0,j=0,k=0,classcount=0,defcount=0,endcount=0,l=0,open_br=0,close_br=0;
                        static Scanner sc = null;
public static void main(String[] args)
{
	try
	{
                        System.out.println("Enter filename");
                        Scanner consoleinput=new Scanner(System.in);
                        FileInputStream fileInputname=null;
			String nameOfFile=consoleinput.next();
                        sc = new Scanner(new FileReader(nameOfFile));
                        fileInputname = new FileInputStream(nameOfFile);
                       
                       
                 //to count def,end and class
               
                      while(sc.hasNext())
                     {
                       next=sc.next();
                       if((next.compareTo("def")==0)||(next.compareTo("class")==0)||(next.compareTo("end")==0))
                        {
                          if((next.compareTo("class")==0))
                           {
                               classcount++;
			       classnames[++j]=new String();
                               classnames[j]=sc.next();
                           }
                          else if((next.compareTo("end")==0))
                           {
				endcount++;
                           }
			 else if((next.compareTo("def")==0))
		           {
				defcount++;
                                methods[++l]=new String();
                                methods[l]=sc.next();
                           }

                       }  
                    }
           
		 
                 sc=new Scanner(new FileReader(nameOfFile)); 		          
	         fileInputname = new FileInputStream(nameOfFile);
	        
               //language detection
                   
	        while(sc.hasNext())
	 	{
                     
		    String firstword = sc.next();
	             
	   	    if(firstword.equals("<?php"))
	            {
	                 System.out.println("given language is php");
		         sc = new Scanner(new FileReader(nameOfFile));
                         phpOperations(sc);
                    } 
            //detection of ruby and java
                    else 
                    {
                	if(defcount==(endcount-classcount))
                  	{
                      	     System.out.println("this is ruby:");
                             sc = new Scanner(new FileReader(nameOfFile));
                             rubyOperations(sc);
                        }
                      
                        
                        else
		        {
                   	     System.out.println("this is java:");
		             sc= new Scanner(new FileReader(nameOfFile));
                             javaOperations(sc);
			}
                    }
		}
 		   //operation on java
		  

// indentation of file
        int t;
          while ((t = fileInputname.read()) != -1)
	  {
             char c = (char) t;
             if(t=='{')
               open_br++;
             else if(t=='}')
               close_br++;
          }

          if(open_br==close_br)
             System.out.println("*********file identation is correct*******");
          else
             System.out.println("********file identation is not correct***********");
     }
	catch(Exception e)
	{
		e.printStackTrace();
	} 

}             
	           

public  static void phpOperations(Scanner sc)
{
                 	while(sc.hasNext())
	                {
	                 	next=sc.next(); 
                                if(next.charAt(0)=='$')
	                   	{ 
                                 //next=sc.next();
                              		int q=next.indexOf("->");
                              		if(q>0)
                              		{
                              			variables[++i]=new String();
                                                variables[i]=next.substring(0,q);
			      		}
					else	
					{
	                	   		variables[++i]=new String();
                                   		variables[i]=next;
					}
                                                
                              			
	                   	}
	                 	if(next.equals("//"))
						{
						   sc.nextLine();
						   continue;
						}
						else if(next.equals("/**"))
						{
						  while(!(sc.next().equals("*/")))
						  {
						     continue;
						  }
						  continue;
						}
	                 //character
                            
                              
                         //class
                           	else if((next.compareTo("class")==0))
	                   	{
				   //j++;
	                	   	classnames[++j]=new String();
                                   	next=sc.next();
	                	   	classnames[j]=next;
				}
	                 //methods
                         	if(((next.compareTo("public")==0)||(next.compareTo("private")==0)||(next.compareTo("protected")==0)||(next.compareTo("static")==0)) && (sc.next().compareTo("function")==0)||(next.compareTo("function")==0))
	                 	{ 
					if((next.compareTo("public")==0||next.compareTo("private")==0||next.compareTo("protected")==0||next.compareTo("static")==0))
                                	{
                                		 acc_mod[++k]=new String();
						 acc_mod[k]=next;
						 next=sc.next();				 
						if(next.compareTo("function")==0)
						{       
                                        		k--;
         						next=sc.next();
                              				int q=next.indexOf('(');
                              				if(q>0)
                              				{
                              					methods[++k]=new String();
                              					methods[k]=next.substring(0,q);
			      				}
                              				else
                             				{     	      
                                methods[++k]=new String();                                                 
	                        methods[k]=next;
	                     				}
						}
					}
                               		else if(next.compareTo("function")==0)
					{
				    		next=sc.next();
                              			int q=next.indexOf('(');
                              			if(q>0)
                              			{
                              				methods[++k]=new String();
                              				methods[k]=next.substring(0,q);
			      			}
                              			else
                             			{     	      
                               		     	methods[++k]=new String();                                
	                			methods[k]=next;
	                     			}
					}
				
	                	}
                        	else
					continue;
                  	}
	                System.out.println("*********classes of php*************");
                        while(j!=0)
			{
                            	System.out.println("class:"+classnames[j]);
			    	j--;
		        }
	                System.out.println("*********variables of php********");
			while(i!=0)
			{
                            	if(variables[i]==variables[i+1])
                              		i--;
		            	System.out.println(variables[i]);
			     		i--;		    	
			}
			System.out.println("**********methods with access modifiar of php*************");
			while(k!=0 )
			{
                        	if(acc_mod[k]==null)
			    		System.out.println(methods[k]);
                        	else
                            		System.out.println(acc_mod[k]+methods[k]);
                        
				k--;
			}
}
 
public static void rubyOperations(Scanner sc)
{
		        while(sc.hasNext())
			{
                        	next=sc.next();
                           	if((next.compareTo("//")==0))
			   	{
                               		sc.nextLine();
                               		continue;
			   	}
			   	else if(next.compareTo("/**")==0)
	                   	{
	                	 	sc.nextLine();
	                	 	while(!(sc.next().compareTo("*/")==0))
	                	 	{
	                	   	sc.nextLine();
	                	   	continue;
	                	 	}
	     
	                   	}
                           	else if(next.charAt(0)=='@' && next.charAt(1)=='@')
			   	{
   					classvariables[++k]=new String();
 					classvariables[k]=next;
			  	}
                           	else if(next.charAt(0)=='@' && next.charAt(1)!='@')
			   	{
   					variables[++i]=new String();
 					variables[i]=next;
			   	}
                          /* else if((next.compareTo("class")==0))
                           {
                                classnames[++j]=new String();
                                classnames[j]=sc.next();
                           } */
			}

                         System.out.println("******************classes in ruby:*********************");
                         while(j!=0)
			 {
		         	System.out.println("class:"+classnames[j]);
				j--;
			 }
                         System.out.println("******************variables in ruby:*********************");
                         while(k!=0)
			 {
		             	System.out.println("class variables:"+classvariables[k]);
			     	k--;
			 }
			 while(i!=0)
			 {
		             	System.out.println("instance variables:"+variables[i]);
			     	i--;
			 }
                         System.out.println("******************methods in ruby:*********************");
                         while(l!=0)
			 {
		             	System.out.println("methods:"+methods[l]);
			     	l--;
			 }
}

                
public static void javaOperations(Scanner sc) 
{
                   	while(sc.hasNext())	
		   	{
		       	        next=sc.next();
                       		if((next.compareTo("class")==0))
				{
                           		next=sc.next();
                           		int q=next.indexOf('{');
                           		if(q>0)
			   		{
                           			classnames[++j]=new String();
                           			classnames[j]=next.substring(0,q);
			   		}
			   		else
		           		{
			        		classnames[++j]=new String();
                           			classnames[j]=next;

				   	}
				}
                       		else if((next.compareTo("public")==0)||(next.compareTo("private")==0)||(next.compareTo("protected")==0))
 		       		{   
			    		acc_mod[++i]=new String();
			    		acc_mod[i]=next;
                            		next=sc.next();
                            		if((next.compareTo("void")==0)||(next.compareTo("String")==0)||(next.compareTo("Long")==0)||(next.compareTo("Double")==0)||(next.compareTo("int")==0))
			    		{
                             			i--;
                              			next=sc.next();
                              			int q=next.indexOf('(');
                              			if(q>0)
                                		{
                              				methods[++i]=new String();
                              				methods[i]=next.substring(0,q);
                                		}
                                		else
                                		{
                              				methods[++i]=new String();
                              				methods[i]=next;
                                		}
                                         
			    		}
				}
                       }   
                       System.out.println("**********classes of java*********");
                       while(j!=0)
                       {
                   		System.out.println("class:"+classnames[j]);
                   		j--;
                       }	
                       System.out.println("********methods with access modifer of java**********");
                       while(i!=0)
		       {
                   	        if(methods[i]!=null)
                        		System.out.println(acc_mod[i]+methods[i]); 
		        	i--;
		       }
}

}        
	
