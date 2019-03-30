/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictive.parser;

import java.util.Scanner;

/**
 *Predictive Parser
 * @author Tushar M K
 */

public class PredictiveParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       Scanner sc = new Scanner(System.in);
        System.out.print("Enter the input String (in terms of 'i' like : i+i*i): ");
        String input = sc.next();
        //Appending $ at the end : -
        input += '$';
        String stack = "$E" ;
        String output = "" ;
        
       
        System.out.println("STACK\t\tINPUT\t\tOUTPUT");
        
        
       while(!(stack.equals("$" ) && input.equals("$")))
        {
                      
            System.out.println(stack + "\t\t"+ input + "\t\t" + output);
            
           
                  
            if(stack.charAt(stack.length()-1) == input.charAt(0))  
            {
                //popping from stack
                String temp = "";
                for(int i = 0 ; i < stack.length()-1   ; i++ )
                {
                    temp = temp + stack.charAt(i);
                }

                stack = temp;
                
                output = "MATCHES";
                
                //input will be -
                temp = "";
                for(int i = 1 ; i < input.length() ; i++ )
                {
                    temp = temp + input.charAt(i);
                }
                input = temp;
                
                
             
            }
            else
            {
                 String temp_stack = parseTable(stack.charAt(stack.length()-1),input.charAt(0));
                 if(temp_stack.equals("ERROR"))
                 {
                        System.out.println("String REJECTED");
                       break ;
                  }
               
                
              
                  output = stack.charAt(stack.length()-1) +" -> " + temp_stack ;
                

                String temp1 = "",temp2 = "";
                //temp1 for reversing the output
                //temp2 for poppping the stack
                for(int i = temp_stack.length()-1 ; i >= 0  ; i-- )
                {
                    temp1 = temp1 + temp_stack.charAt(i);
                }
                for(int i = 0 ; i < stack.length()-1   ; i++ )
                {
                    temp2 = temp2 + stack.charAt(i);
                }
                //Stack after the output will be
                stack = temp2 + temp1;
            }
        }
       
        System.out.println("$\t\t$\t\tSTRING ACCEPTED");
        
    }
    
    
    
    
    
    public static String parseTable(char stack , char input)
    {
        if(stack == 'E')
        {
            if(input == 'i')
                return "Te";
            else if(input == '(')
                return "Te";
            else
                return "ERROR";
            
        }
        
        if(stack == 'e')
        {
            if(input == '+')
                return "+Te";
            else if(input == ')')
                return "";
            else if(input == '$')
                return "";
            else
                return "ERROR";
            
        }
        
         
        if(stack == 'T')
        {
            if(input == 'i')
                return "Ft";
            else if(input == '(')
                return "Ft";
            else
                return "ERROR";
            
        }
        
         if(stack == 't')
        {
            if(input == '+')
                return "";
            else if(input == '*')
                return "*Ft";
            else if(input == ')')
                return "";
            else if(input == '$')
                return "";
            else
                return "ERROR";
            
        }
         
        if(stack == 'F')
        {
            if(input == 'i')
                return "i";
            else if(input == '(')
                return "(E)";
            else
                return "ERROR";
            
        }
        
        
        return "ERROR";
    }
    
}
