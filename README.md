# Hospital St Claire patient record.

The application allows the user to create specialties for the Hospital, uddate it, delete it (when the Speciality do not have patients asociated) and also get all the specialties. Also, alows to create new patients records in a specific speciality if the patient already exist in that specific speciality the user can add new appointments or create new patients.

> About the App: The front of the app was made using HTML5, CSS3, and Typescript. The backend was built using Java Spring Boot and MySQL as database.

#### Steps to execute the project:

1. Download the [backend](https://github.com/Katerinccc/Hospital-record-back) of the project from GitHub.
2. Open the folder Record with IntelliJ IDEA.
3. When the project loads correctly go to the file application.properties located in the folder resources, and update the setup to connect with MySQL. 

```sh
spring.datasource.url = jdbc:mysql://localhost/hospital_record?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrival=true    // (Update URL where the database is located if is different of localhost)
```
```sh
spring.datasource.username = root  // (Update name of the user if is different of root )
```
```sh
spring.datasource.password = ####  // (Update your password)
```
4. ▶️ Run the application on the file **HospitalRecordApplication.java**
5. Download the [frontend](https://github.com/Katerinccc/Hospital-record-front) of the application from GitHub.
6. Open the project with Visual Studio Code.
7. Go to the file index.html and select **Open with Live Server** (
if you don't have the extension you can download it [here](https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer) )
8. The Application will open in the browser on the URL: 
```sh
http://127.0.0.1:5500/index.html
```
9. Now you can test the APP.



 ✨Created by Katerin Calderón ✨
