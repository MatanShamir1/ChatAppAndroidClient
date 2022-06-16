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
![image](https://user-images.githubusercontent.com/84122241/174073143-70c8174d-4a77-4b94-a8a5-8f18aeb37c6a.png)

### Add contact

![image](https://user-images.githubusercontent.com/84122241/174075353-5b7cc4dc-fade-4867-8634-1c0678e799fa.png)

## How-to-open-the-app

1. If you dont have visual studio's newest version, please install it.
2. Choose an empty project, and please do:
  ![image](https://user-images.githubusercontent.com/74719554/170250838-2316fed6-fa43-44ef-8953-2a6fa751ba92.png)
   This will open a window:
   ![image](https://user-images.githubusercontent.com/74719554/170250972-b5c02025-8ade-478d-84e7-d477229f7059.png)
enter this repo's location from the ![image](https://user-images.githubusercontent.com/74719554/170251059-59ce3781-092e-4e43-8c58-cb6c3e9df193.png)
part, and walla! you cloned our repo.
3. in package manager console. enter: 
   ``` add-migration init ``` to initialize a new migration for entity framework's default database.
4. then enter:
   ``` update-database ``` in the same console.
5. check that the database has been created.
6. read how-to-use-the-app section for details about creating data, and read the client side readme parallely
7. run the server.

## How-to-use-the-app
please refer **ChatApp** repository for more information on how to activate the client.

## Developers:
**Matan Shamir 206960239** <br />
**Itamar Bachar 295847376**
