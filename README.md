# ChatAppAndroidClient
## a chatting app by Matan Shamir and Itamar Bachar - server side
1. [About](#About)
2. [Dependencies](#dependencies)  
3. [Pages-flow-and-explanation](#Pages-flow-and-explanation)
4. [How-to-open-the-app](#How-to-open-the-app)
5. [How-to-use-the-app](#How-to-use-the-app)
6. [Developers](#Developers)


## About
this is the client android part of the ChapApp system. its the third milestone in advanced programming 2 course.

## Dependencies
* Windows / Linux / macOS
* Git
* Visual Studio's newest version
* Android studio

## Android Client
here, we will expand explanation about the android client part of this project.
### Pages-flow-and-explanation 
we have been asked to create client in android that communicate with the same server that we create in the ex2,
so we created several activities with a flow that communicates the server from part to. lets dive right into it!

### Login page
this is the main activity, here any user that's registered to our server can login.
the client needs to enter his phone number and his password.
<br />
![image](https://user-images.githubusercontent.com/84122241/174067442-b0f55067-d755-4e34-8414-f036133346b0.png)

### Register new client
by pressing "to register" you can create a new user, specifying his details.
<br />
![image](https://user-images.githubusercontent.com/84122241/174067709-f03ef097-58e9-4678-9291-48b887405d30.png)
<br />

you can go back to login and enter with an existing client. <br />
pay attention to the red errors in the left bottom of the page: it specifies the password needs, and the username's, and nickname's. <br />
you are free to pick a profile picture from existing gallery or directly from your camera, but you don't have to.
**make sure: password contains every kind of letter, number and special char, and username contains only digits- we assumed it is a phone number!!!**
after login you will see the contact page..

### Contact list
we decided to implement our contacts list as a recycle view as so you can see in the source code.
in the top you can search for specific user , you can upload image and change the server you are talking to, you can also add new contact. 
from this activity you can skip to this three activity that we mentioned let see the message view..
<br />
![image](https://user-images.githubusercontent.com/84122241/174068333-b501eba6-812b-479a-ae06-1f40800639fe.png)
<br />

### Message list 
In this activity you can chat we the person you want selected.
you can also return to contact list activity, we also add the abilty to see the message that we get in live with the firebase service.
<br />
![image](https://user-images.githubusercontent.com/84122241/174073143-70c8174d-4a77-4b94-a8a5-8f18aeb37c6a.png)
<br />
### Add contact
In this activity you can add contact to the user that is login right now.
**important!!! please write correctly the ip and the port of the user that you to add.
exmpale - 10.0.2.2:5243 
this is how we commuincate with our server in the android.**
<br />
![image](https://user-images.githubusercontent.com/84122241/174075353-5b7cc4dc-fade-4867-8634-1c0678e799fa.png)
<br />

### Change server or image
in the last activity we see two options that the app is given to the user , one is upload image and second is to change the server that the user want to get request.
<br />
![image](https://user-images.githubusercontent.com/84122241/174078251-439e07f7-67ce-45d0-ba0c-a98ffcd8aa53.png)
<br />
## How-to-open-the-app
1 First, you need to clone the server's project. open terminal (if on linux) or gitbash if on windows (you can also use powershell), and type:
<br />
``
  git clone https://github.com/MatanShamir1/ChatAppServer/tree/ex3
``
<br />
<br /> <br />
   also make sure that you are in your desired directory.
   you can also use visual studio's UI and clone the existing server's project from there.
 <br /> <br />
2. clone the android studio project, this repository. type in the cmd you chose in step 1: 
 <br />
 ``
  git clone https://github.com/MatanShamir1/ChatAppAndroidClient
 ``
 <br />
   you can also clone via android studio UI.
 <br /> <br />
 
3. in the server's project, open the console and write down:
4. <br />
``
  Install-Package FirebaseAdmin -Version 2.3.0
``
<br />
this installs the package needed to be able to write post requests to the firebase server. 
<br /> <br />
4. run the server on port 5243, by clicking the "play" button on visual studio.
<br /> <br />
5. run the client's emulator by clicking the "play" button on android studio.
<br /> <br />
6. you can also run the web client by cloning it and running it from the VScode environment, see the details in the readme of the 2nd milestone, ChatAppServer.

## How-to-use-the-app
* you can register new users to the server's database as explained in the login+register parts above. <br />
* you can open 2 or more clients from web\android and try checking live updates from one to another. <br />
* you can change the user's profile pics and server addresses in the settings page, as explained above. <br />
* you can also use the web client from ex1 (in the newest branch- which is ex2 branch) and activate the users from different client. <br />

## Developers:
**Matan Shamir 206960239** <br />
**Itamar Bachar 295847376**
