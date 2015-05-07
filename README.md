# WhiteBoard
Shared whiteboards are one form of collaboration of the Internet, we developed a distributed whiteboard for individuals to access and communicate visually from different hosts according to the socket scheme. First, the report introduces the developments of remote applications. Then in the second part, it illustrates 

## Synopsis
###### Motivation
The purpose of this project is to implement a shared Whiteboard application which allows numbers of clients to share the paint from different location. Some basic operations are provided, such as draw line, curve, oval and text, all these function is based on the MS paint. Each operation of every client is updated through the server in real time. Besides the server establisher is the administrator, he can behave kick out, reject and allowance function.For the existing related applications, there are two programs, which are the Microsoft Paint and the Coccinella. This single white board of this project is based on the Microsoft Paint, which is a standard whiteboard program, but it is not distributed. It mainly has the core features of a whiteboard, 

###### Orignal
This is a project I made in Melbourne Unviersity. appreciate with my teammate: *Xun HU* who did (Drawing type, DataList, Broadcast) &Xuechong HAN who did (Colorpanel, Textpanel, StrokePanel,
SocketClient).I did the The mainframe, MeanuBar, LoginFrame, SocketServer,client part. 
######  features
-  Drawing of basic shapes, such as: straight line, curve, rectangles, polygon, and freehand drawing.
-  Using an empty and filled mode for shapes.
-  Support for 48 colors. And users can define their own color.
-  Support for text editing which users can select the font.
-  Support the tools of erase, magnifier, cut, brush.
-  Turning over, rotating, stretching and distorting the picture.
-  And it saves the paint picture as image files, .bmp, .gif, .jpeg and etc.
-  	Support drawing different basic shape: line, circle, rectangle and oval. 
-  	Free draw and the eraser function
-  	Stroke the shape and eraser can be changed by a slider.
-  	26 kinds of color are provided and more color can be chosen.
-  	Input text everywhere inside the drawing area and provide different font, size, color and type to choose.
-  	New drawings are always placed on the top
-  	Provide basic file operations: new, open, save, saveAs and close.
-   A list of joined clients and administrator is displayed.
- 	Two different user interface and the corresponding roles: CreateWhiteboard-Admin and JoinWhiteboard-users. The administrator can create a instance and allow or reject other users to join, He also can kick out certain peer and teminate this application

## Implementation
###### Platfrom
Java 
###### Lanugae
Java 
###### API & Library
Java
###### FAQ
-	How to implement the text input anywhere in the canvas?

At first, we plan to implement this as what is done in the drawing board of windows that create a small new Jpanel and type the words. But it is difficult to implement in Java. So as last we using the drawstring method in Java.awt package and use a JOptionPane to type the words.
-	How to notify all the users when the information is updated?

It is difficult and long latency to inform every client the latest update especially when there is large scale of clients using TCP protocol. So here we adopt UPD protocol to broadcast information that the server received synchronal. 
-	How to implement the function of administrator?

The ideal way is to create a separate UI interface which is different from clients’, including the kick and reject function. But we across some difficulties when we design the canvas and the data exchange, so we design the function of administrator separately with a client list instead of combining it with the whiteboard.

###### run
1. After the server start, it is required a port to build the server
2. Server is starting with a list of client and two buttons, one for kick out the selected client, one for refresh.
3. Start the client and it need some feature to build up the connection with the server.
4. The sever will show the one who want to join in.
5. The main GUI of the client: we can draw here with the different tools and the set for the tools. 
6. The server will join the information of the joined client.
7. We can share what we draw now.
8. When the server is broken, the client will show the information box and quit
9. When the client is kicked out, it will give a message and quit.

## Installation
- Compile 
```
   javac WhiteBoard5.0\src\*.java
```
- start the server (support stanard input)
```
   java WhiteBoard5.0\bin\Server.java
```
-start the client in different mechine
```
   java WhiteBoard5.0\bin\PanelClient.java
```

## Tests

## Other Contributors
- Xun HU
- Xuechong HAN

## License
This is a project based on learning and studying. Not for any kind of commecial using. This is a project built within a research group in Melbourne University. Please **do not** copy and using it as both commercial and **academic** using directly.  
######Other API license
- [Java Binary Code License Agreement](http://www.oracle.com/technetwork/java/javase/terms/license/index.html)



