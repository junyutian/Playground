# Log Collection
This demo is to provide on-demand monitoring of various unix-based
servers without having to log into each individual machine and opening up the log files found in
/var/log

The customer could issue a REST request to a machine in order to retrieve logs from /var/log on the machine receiving the REST request. 

---
## Features 
Here is the list of support features through API /log-collection

- Get latest n lines from the given log
- Support big log file monitoring > 1G
- Support number of lines to return ( no pagination support )
- Support filtering 

Supported parameters
- **filename**: The log file name
- **entries**: The number of lates lines in the log
- **keyword**: The text/keyword for filtering

---

## Deployment & Run
The web application is packed as a WAR file. To generate the WAR file
- Use IDE like Intellij
![RunDebug Configurations 2022-09-14 at 1 50 44 PM](https://user-images.githubusercontent.com/40179591/190226835-346abd68-3bd6-48cc-96ed-7597f5a2ee99.jpg)
- Use maven command: mvn package
![-zsh 2022-09-15 at 7 51 27 AM](https://user-images.githubusercontent.com/40179591/190396962-1baa09fc-4a9a-4811-8899-2db0a7da6a20.jpg)


- Copy the WAR file into $CATALINA_HOME\webapps directory.
- Restart the Tomcat server. 
- If run in local, go to http://localhost:8080/demo1-1.0-SNAPSHOT/, will get the following simple UI to accept request parameters (by index.jsp).

![Get Latest Log Lines 2022-09-14 at 1 13 46 PM](https://user-images.githubusercontent.com/40179591/190219560-64e355f6-c696-441c-a1cd-dd71a14ef42c.jpg)

NOTE: Later if you want to update changes for the application, you must both replace the WAR file and delete the application’s unpacked directory, and then restart Tomcat.

---

## Test
It passed both unit test and local test
### Unit test
Cover the following four classes
- LogRequest
- LogResponse
- LogFile
- LogServlet

Run unit test in Intellij or in commend **mvn test**
![-zsh 2022-09-15 at 7 57 52 AM](https://user-images.githubusercontent.com/40179591/190397900-b994e3d5-d77a-4ad6-83fb-a97ae15cc692.jpg)

**Note**: test the big file locally, and the test file is too big to upload to github.

https://user-images.githubusercontent.com/40179591/190240176-fc68b984-9762-4b67-b425-0bb3f3956b99.mp4


### Test cases
#### Missing input filename
http://localhost:8080/log-collection?filename=&entries=&keyword=
#### Filename not exist
http://localhost:8080/log-collection?filename=aa&entries=&keyword=
#### Invalid entries
http://localhost:8080/log-collection?filename=system.log&entries=-1&keyword=
#### Valid case
http://localhost:8080/log-collection?filename=system.log&entries=10&keyword=SIG

![localhost8081log-collectionfilename=system log entries=10 keyword=SIG 2022-09-14 at 2 11 40 PM](https://user-images.githubusercontent.com/40179591/190230718-5146d62d-27df-4301-82bf-6bc0168eaa57.jpg)
