# Running the application locally

### Database
1. You can use h2 embedded memory for data and configure the application in this manner, but to have data persist it is better to create a database utilzing a client such as Dbeaver
2. Configure your application.yml with the database url, username, and password and set ddl to create-drop if you want to start fresh everytime or create then set it to update after to have data persist

### Maven
1. Install Maven, it is the dependency manager and build tool used for the application
2. The dependencies required are found within this pom.xml file then build the project using your IDE

### Run
1. Open up SocialMediaApplication.java file and run the file.


# Running the application on AWS

### Dockerfile
1. These are the build instructions to allow for an image to built to allow for a container to be created for your application to run

### Buildspec.yml
1. These are the instructions for AWS CodeBuild to build the project

### Pipeline
    Go to elastic beanstalk

    Choose docker for platform

    Codepipeline in searchbar

    Create a pipeline

    Github version 2 for provider

    Connect to github, name it whatever

    Then install new app

    Click rev org then install then connect then choose your repository name then branch main then next

    Build provider is aws code build

    Then create project and name

    Restrict conccurrent builds to one

    Change OS to ubunut

    Change image to 5

    Put group name and stream name then configure then next

    Deploy elastiv beanstalk

    Create pipeline
    
  ### Failed Builds
  1. Utilize the logs to see where the build failed
