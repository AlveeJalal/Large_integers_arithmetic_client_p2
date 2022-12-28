# Large_integers_arithmetic_client_p2
*This is a small project I completed in my Android Studio Java programming class called IT114 - Advanced Programming for Information Technology at New Jersey Institute 
of Technoloy in Fall 2022.
*This demonstrates Client/Server interaction using sockets and server sockets implemented in Android Studio (Java) and XML. 
*The project is meant to perform a desired operation on large integers and display the result. 
*The connection client (MainActivity.java) takes the port number and host name of the server to connect to. (Run this on an Android device or emulator)
*The operation client(CalculateActivity.java) takes the desired operation (add, ssw-sub, rev-mult), and the two big integers and displays the result retrieved from the server.(Run this on an Android device or emulator) 
*The server receives the input from the client, performs the operation, and sends the result back to the client. (Run the server on a SSH client like MobaXTerm)
*add: Adds the integers
*ssw-sub: Splits the halves of the integers, swaps the halves, and subtracts the newly created integers (Removes leading zeros and keeps negatives when modifying).
*rev-mult: Reverses the numbers and multiplies the newly created integers (Removes leading zeros and keeps negatives when modifying).

