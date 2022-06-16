# ChatAppAndroidClient
## a chatting app by Matan Shamir and Itamar Bachar - server side
1. [About](#About)
2. [Dependencies](#dependencies)  
3. [Pages-flow-and-explanation](#Pages-flow-and-explanation)
4. [How-to-open-the-app](#How-to-open-the-app)
5. [How-to-use-the-app](#How-to-use-the-app)
6. [Developers](#Developers)


## About
this is the client android part of the third milestone in advanced programming 2 course.

## Dependencies
* Windows / Linux / macOS
* Git
* Visual Studio's newest version
## Android Client
here, we will expand explanation about the android client part of this project.
### Pages-flow-and-explanation 
we have been asked to create client in android that communicate with the same server that we create in the ex2,
so we create several activities as we asked let dive to it!

### Login page
this is the main activity, here any user that register to our server can login.
the client enter is phone number and his password.
<br />
![image](https://user-images.githubusercontent.com/84122241/174067442-b0f55067-d755-4e34-8414-f036133346b0.png)

### Register new client
by pressing "to register" you can create a new client, specifying his details.
<br />
![image](https://user-images.githubusercontent.com/84122241/174067709-f03ef097-58e9-4678-9291-48b887405d30.png)
<br />

now you can also back to login and enter with exist client , if you trying to connect wrong password it wouldnt allow it.
after login you will see the contact page..

### Contact list
we are implement this list as recycle view as so you can in the source code..
in the top you can search for specific user , you can upload image and change the server you are taking to, you can also add new contact. 
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
important!!! please write correct the ip and the port of the user that you to add.
exmpale - 10.0.2.2:5243 
this is how we commuincate with our server in the android.
<br />
![image](https://user-images.githubusercontent.com/84122241/174075353-5b7cc4dc-fade-4867-8634-1c0678e799fa.png)
<br />

### Change server or image
in the last activity we see two option that the app is given to the user , one is upload image and second is to change the server that the user want to get request.
<br />
![image](https://user-images.githubusercontent.com/84122241/174078251-439e07f7-67ce-45d0-ba0c-a98ffcd8aa53.png)
<br />
## How-to-open-the-app
1 First, you need to clone the server's project. open terminal (if on linux) or gitbash if on windows (you can also use powershell), and type:
``
  git clone https://github.com/MatanShamir1/ChatAppServer/tree/ex3
``
<br />
   also make sure that you are in your desired directory.
   you can also use visual studio's UI and clone the existing server's project from there.
2. clone the android studio project, this repository. type in the cmd you chose in step 1:
 ``
  git clone https://github.com/MatanShamir1/ChatAppAndroidClient
 ``
   you can also clone via android studio UI.
 ``
3. in the server's project, open the console and write down:
``
  Install-Package FirebaseAdmin -Version 2.3.0
``
   this installs the package needed to be able to write post requests to the firebase server.  
`` 
4. run the server on port 5243, by clicking the "play" button on visual studio.
<br />
5. run the client's emulator by clicking the "play" button on android studio.
<br />
6. you can also run the web client by cloning it and running it from the VScode environment, see the details in the readme of the 2nd milestone, ChatAppServer.

## How-to-use-the-app
* you can register new users to the server's database as explained in the login+register parts above.
* you can open 2 or more clients from web\android and try checking live updates from one to another.
* you can change the user's profile pics and server addresses in the settings page, as explained above.

## Developers:
**Matan Shamir 206960239** <br />
**Itamar Bachar 295847376**
