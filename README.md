# cocktail-project
## Start-up project
To start up a project use Eclipse ide for Enterprise:
<ol>
  <li>Make sure to add Spring Tools 4 from Eclipse marketplace:
    <ol>
      <li>Go to help on main menu </li>
      <li>Then click eclipse marketplace </li> 
      <li>there search for Spring Tools 4</li> 
      <li>Use default setting and click next till finish, as well as finish</li>
    </ol> 
  </li>
  <li> Add Maven; see https://toolsqa.com/maven/how-to-install-maven-eclipse-ide for instructions. </li>
  <li> When downloading code to the computer, in Eclipse go to File > Open project from file system > find a directory(root folder of the project)> click         finish</li>
  <li>Import launch configurations for application and test, named "cocktails - CocktailsApplication.launch" and "CocktailApiE2ETest.launch", respectively:
    <ol>
      <li>Go to File > Import... > Run/Debug > Launch Configurations </li>
      <li>In the dialog select all or what you want</li>  
      <li>there search for Spring Tools 4</li> 
      <li>Choose project root directory, then launch fails will appear</li>
      <li>Select them, and click finish</li>
    </ol>
  </li>
<li>On the right click on the run button and choose to run the application</li> 
<li>Or to run it without launch go to the starting files CocktailApplication.java for application(right-click> run as> spring boot app),
  and for CocktailApiE2ETest.jave(right-click > run as > JUnit Test)</li>
</ol>
## What I left out

- Data from third-party service only had the date modified, not the creation date and
the update date, so I put it into my resource only.
- Every layer of the application was simple enough, so I did not add any unit tests.
- Project has swagger documentation added, once the application is started, go to 'http://localhost:8080/swagger-ui/index.html' to visit docs.
