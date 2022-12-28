//        Alvee Jalal                                 //
//        IT114, Section 001                          //
//        Dr. Halper                                  //
//        App Project #2                              //
//        Dec. 6, 2022                                //
//                                                    //
//        This file is designed to perform as a       //
//        server to a respected client(app). This     //
//        server will retrieve connection requests &  //
//      data from the client, perform the desired     //
//      operations and send the requested & calculated//
//     data back to the client. This is a sequential //
//     server so it will keep listening for requests //
//     until it is manually stopped in the terminal  //
///////////////////////////////////////////////////////

//necessary imports for server
import java.util.*;
import java.net.*;
import java.io.*;
import java.math.*;

public class large_Server
{
    //main method
    public static void main(String[] args)
    {
        //necessary variables for the server
        ServerSocket serverSocket = null;
        Socket socket = null;
        int port;
        //condition determining if server is waiting for requests
        boolean listening = true;
        String operation;
        String int1;
        String int2;
        BigInteger bigInt1;
        BigInteger bigInt2;
        String sum;


        //get the port # from command line

        port = Integer.parseInt(args[0]);

        //attempt to create server socket

        try
        {
            serverSocket = new ServerSocket(port);
        }

        //if there's an error, stop server stops listening
        catch(IOException e)
        {
            System.out.println(e);
            listening = false;
        }

        //else, perform operations
        if(listening)
        {
            //continues to listen for requests until server is manually stopped in the terminal
            while(true)
            {
                try
                {
                    //getting a connection request from client
                    socket = serverSocket.accept();

                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    Scanner in = new Scanner(new InputStreamReader(socket.getInputStream()));
                    //get data from client


                    operation = in.nextLine();
                    int1 = in.nextLine();
                    int2 = in.nextLine();



                    //test to see if operation & numbers as input are valid before doing operation and converting to BigInts
                    if(validOperation(operation).equals("valid"))
                    {
                        if(validNumbers(int1,int2).equals("valid"))
                        {
                            bigInt1 = new BigInteger(int1);
                            bigInt2 = new BigInteger(int2);
                            //add the BigInts
                            if (operation.equals("add"))
                            {
                                sum =  (bigInt1.add(bigInt2)).toString();

                                //display sum
                                out.println("The sum is: "+sum);


                            }

                            //split the 1st and 2nd halves of the BigInts, swap the halves, and subtract the new BigInts by calling ssw_sub() method
                            else if(operation.equals("ssw-sub"))

                            {
                                //display difference
                                out.println("The difference is: "+ssw_sub(int1,int2));
                            }
                            //reverse and multiply the BigInts by calling rev_mult() method
                            else if(operation.equals("rev-mult"))
                            {
                                out.println("The product is: "+rev_mult(int1,int2));
                            }

                        }
                        //display error message if the numbers aren't valid
                        else if(!validNumbers(int1,int2).equals("valid"))
                        {
                            out.println(validNumbers(int1,int2));
                        }
                    }

                    //display error message if operation isn't valid
                    else if(!validOperation(operation).equals("valid"))
                    {
                        out.println(validOperation(operation));
                    }






                    //close connection to client
                    out.close();
                    in.close();
                    socket.close();

                }
                //display error message for general IO exceptions with Socket
                catch(IOException e)
                {
                    System.out.println(e);
                }

            }

        }
    }



    //split the 1st and 2nd halves of the BigInts, swap the halves, and subtract the new BigInts
    public static String ssw_sub(String num1, String num2)
    {
        //necessary variables for operation
        String beginningHalf1;
        String beginningHalf2;
        String endHalf1;
        String endHalf2;
        String midNum1;
        String midNum2;
        String newNum1;
        String newNum2;
        BigInteger posBigInt1 = new BigInteger(num1);
        BigInteger posBigInt2 = new BigInteger(num2);
        BigInteger zero = new BigInteger("0");
        BigInteger negative = new BigInteger("-1");
        BigInteger bigInt1;
        BigInteger bigInt2;
        String difference;

        //take out negative if number is negative.
        if(posBigInt1.compareTo(zero)==-1)
        {
            posBigInt1 = (posBigInt1.multiply(negative));
            num1 = posBigInt1.toString();
            posBigInt1 = (posBigInt1.multiply(negative));
        }
        //take out negative if number is negative.
        if(posBigInt2.compareTo(zero)==-1)
        {
            posBigInt2 = (posBigInt2.multiply(negative));
            num2 = posBigInt2.toString();
            posBigInt2 = (posBigInt2.multiply(negative));
        }

        //index for the half points
        int halfPoint1 = num1.length()/2;
        int halfPoint2 = num2.length()/2;

        //Perform these actions if the first number length is even (has no middle digit)
        if(num1.length()%2==0)
        {

            //get the beginning and end halves
            beginningHalf1 = num1.substring(0,halfPoint1);
            endHalf1 = num1.substring(halfPoint1);
            //combine end and beginning halves to make new split swapped number
            newNum1 = endHalf1+beginningHalf1;



            for(int x = 0;x<newNum1.length();x++)
            {
                //remove leading zeroes in new split swapped number
                if(newNum1.charAt(x)=='0')
                {
                    x++;
                    newNum1 = newNum1.substring(x);
                }

                //once no more leading zeroes left, stop the searching
                else
                {
                    newNum1 = newNum1.substring(x);
                    break;
                }
                bigInt1 = new BigInteger(newNum1);
            }
            //add the negative sign back in if the number was a negative
            if(posBigInt1.compareTo(zero)==-1)
            {
                newNum1 = "-"+newNum1;

            }
            bigInt1 = new BigInteger(newNum1);

        }

        //if the number length is odd (has a middle digit) do these actions instead
        else
        {
            //get the beginning and end halves and middle digit
            beginningHalf1 = num1.substring(0,halfPoint1);
            endHalf1 = num1.substring(halfPoint1+1);
            midNum1 = num1.substring(halfPoint1,halfPoint1+1);
            //combine beginning halve, middle number, and end halve to make new split swapped number
            newNum1 = endHalf1+midNum1+beginningHalf1;

            for(int x = 0;x<newNum1.length();x++)
            {
                //remove leading zeroes in new split swapped number
                if(newNum1.charAt(x)=='0')
                {
                    x++;
                    newNum1 = newNum1.substring(x);
                }

                //once no more leading zeroes left, stop the searching
                else
                {
                    newNum1 = newNum1.substring(x);
                    break;
                }
                bigInt1 = new BigInteger(newNum1);
            }

            //add the negative sign back in if the number was a negative
            if(posBigInt1.compareTo(zero)==-1)
            {
                newNum1 = "-"+newNum1;

            }
            bigInt1 = new BigInteger(newNum1);

        }

        //Perform these actions if the second number length is even (has no middle digit)
        if(num2.length()%2==0)
        {
            //get the beginning and end halves
            beginningHalf2 = num2.substring(0,halfPoint2);
            endHalf2 = num2.substring(halfPoint2);
            //combine end and beginning halves to make new split swapped number
            newNum2 = endHalf2+beginningHalf2;

            //remove leading zeroes in new split swapped number
            for(int x = 0;x<newNum2.length();x++)
            {
                if(newNum2.charAt(x)=='0')
                {
                    x++;
                    newNum2 = newNum2.substring(x);
                }
                //once no more leading zeroes left, stop the searching
                else
                {
                    newNum2 = newNum2.substring(x);
                    break;
                }
                bigInt2 = new BigInteger(newNum2);
            }
            //add the negative sign back in if the number was a negative
            if(posBigInt2.compareTo(zero)==-1)
            {
                newNum2 = "-"+newNum2;


            }
            bigInt2 = new BigInteger(newNum2);

        }

        //if the number length is odd (has a middle digit) do these actions instead
        else
        {
            //get the beginning and end halves and middle digit
            beginningHalf2 = num2.substring(0,halfPoint2);
            endHalf2 = num2.substring(halfPoint2+1);
            midNum2 = num2.substring(halfPoint2,halfPoint2+1);
            //combine beginning halve, middle number, and end halve to make new split swapped number
            newNum2 = endHalf2+midNum2+beginningHalf2;


            for(int x = 0;x<newNum2.length();x++)
            {
                //remove leading zeroes in new split swapped number
                if(newNum2.charAt(x)=='0')
                {
                    x++;
                    newNum2 = newNum2.substring(x);
                }

                //once no more leading zeroes left, stop the searching
                else
                {
                    newNum2 = newNum2.substring(x);
                    break;
                }
            }

            //add the negative sign back in if the number was a negative
            if(posBigInt2.compareTo(zero)==-1)
            {
                newNum2 = "-"+newNum2;

            }

            bigInt2 = new BigInteger(newNum2);
        }


        //subtract the Split Swapped BigInts and display the difference
        difference = bigInt1.subtract(bigInt2).toString();
        return difference;
    }



    public static String rev_mult(String num1, String num2)
    {
        //necessary variables for operation
        Stack <Character> num1Stack = new Stack<Character>();
        Stack <Character> num2Stack = new Stack<Character>();
        String reversedNum1="";
        String reversedNum2="";
        BigInteger posBigInt1 = new BigInteger(num1);
        BigInteger posBigInt2 = new BigInteger(num2);
        BigInteger zero = new BigInteger("0");
        BigInteger negative = new BigInteger("-1");


        //take out negative if first number is negative.
        if(posBigInt1.compareTo(zero)==-1)
        {
            posBigInt1 = (posBigInt1.multiply(negative));
            num1 = posBigInt1.toString();
            posBigInt1 = (posBigInt1.multiply(negative));
        }
        //take out negative if second number is negative.
        if(posBigInt2.compareTo(zero)==-1)
        {
            posBigInt2 = (posBigInt2.multiply(negative));
            num2 = posBigInt2.toString();
            posBigInt2 = (posBigInt2.multiply(negative));
        }

        //push each digit to the stack.
        for(int x=0;x<num1.length();x++)
        {
            num1Stack.push(num1.charAt(x));
        }


        //pop each digit out of the stack. Digits popped come out in reversed order compared to original number. This reverses the number. Stop if stack is empty
        while(!num1Stack.isEmpty())
        {
            reversedNum1+=num1Stack.pop().toString();
        }

        //push each digit to the stack.
        for(int x=0;x<num2.length();x++)
        {
            num2Stack.push(num2.charAt(x));
        }


        //pop each digit out of the stack. Digits popped come out in reversed order compared to original number. This reverses the number. Stop if stack is empty
        while(!num2Stack.isEmpty())
        {
            reversedNum2+=num2Stack.pop().toString();
        }



        for(int x = 0;x<reversedNum1.length();x++)
        {
            //remove leading zeroes in new split swapped number
            if(reversedNum1.charAt(x)=='0')
            {
                x++;
                reversedNum1= reversedNum1.substring(x);
            }
            //once no more leading zeroes left, stop the searching
            else
            {
                reversedNum1 = reversedNum1.substring(x);
                break;
            }
        }
        //add the negative sign back in if the number was a negative
        if(posBigInt1.compareTo(zero)==-1)
        {
            reversedNum1 = "-"+reversedNum1;
        }

        //remove leading zeroes in new split swapped number
        for(int x = 0;x<reversedNum2.length();x++)
        {
            if(reversedNum2.charAt(x)=='0')
            {
                x++;
                reversedNum2= reversedNum2.substring(x);
            }
            //once no more leading zeroes left, stop the searching
            else
            {
                reversedNum2 = reversedNum2.substring(x);
                break;
            }
        }
        //add the negative sign back in if the number was a negative
        if(posBigInt2.compareTo(zero)==-1)
        {
            reversedNum2 = "-"+reversedNum2;

        }

        //create the reversed numbers as BigIntegers
        BigInteger bigInt1 = new BigInteger(reversedNum1);
        BigInteger bigInt2 = new BigInteger(reversedNum2);


        //perform multiplication and return product
        return bigInt1.multiply(bigInt2).toString();
    }


    //determine if the input are actually numbers
    public static String validNumbers(String num1, String num2)
    {
        //necessary variables for method
        BigInteger b1;
        BigInteger b2;
        String message = "valid";
        int counter1=0;
        int counter2=0;

        //attempt to convert first input value to a BigInteger
        try
        {
            b1 = new BigInteger(num1);
        }

        //if not possible, means its not a number since formatting is incorrect. Create error message. Counter incrementation signifies this
        catch (NumberFormatException e)
        {
            message = "First value: not a number";
            counter1++;
        }
        //attempt to convert second input value to a BigInteger
        try
        {
            b2 = new BigInteger(num2);
        }
        //if not possible, means its not a number since formatting is incorrect. Create error message. Counter incrementation signifies this
        catch (NumberFormatException e)
        {
            message = "Second value: not a number";
            counter2++;
        }

        //both counters incremented to the same value means both inputs aren't numbers. Signify this in an error message.
        if (counter2==1 && counter1==1)
        {
            message = "Both values: not numbers";
        }



        //send appropriate error message
        return message;
    }

    //determine if input for operation is valid (add,ssw-sub,rev-mult)
    public static String validOperation(String op)
    {
        String condition = "valid";
        //if operation input is either one of the valid operations, it's valid
        if(op.equals("add")||op.equals("ssw-sub")||op.equals("rev-mult"))
        {
            condition="valid";
        }
        //if not, signify that it's unknown
        else
        {
            condition="Unknown Operation";
        }

        //send condition
        return condition;
    }

}