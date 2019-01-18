# dmit2015-winter2019term-A01
In class demos for section A01 


Adding Existings Projects to your private GIT repository
-------------------------------------------------------
If you have an existing Eclipse project you like to add to your new GitHub repository:

1.  Open Eclipse and in the Project Explorer view, right-mouse click the project and delete the project but do NOT delete the contents on disk
2.  Open a Terminal and clone your remote GIT repository to your local machine:

    cd ~/git/
    git clone https://github.com/DMIT-2015/dmit2015-winter2019term-exercises-yourUserName.git
    mv -v /home/dmit2015/eclipse-workspace/DynamicWebProject ~/git/dmit2015-winter2019term-exercises-yourName

3. Open Eclipse and in the Project Explorer view, right-mouse click on the project and select "Team | Add To Index"
4. In Project Explorere view, right-mouse click on the project and select "Team | Commit..."
5. In the GIT Staging view, type in a Commit Message then click "Commit and Push..."

Merging Existings Projects to your private GIT repository
-------------------------------------------------------
If you have an existing Eclipse project that is already using a GitHub repository:

1. "Team | Share Project..."
2. Click Create button. Change directory to "/home/dmit2015/git/exercises-repo". Click Finish. Click Finish.
3. Open a Terminal and type:

    git add --all <br/>
    git commit -m "initial commit"  <br/>
    git remote add origin https://github.com/DMIT-2015/dmit2015-winter2019term-exercises-yourUserName.git  <br/>
    git pull origin master --allow-unrelated-histories  <br/>
    git push --set-upstream origin master  <br/>


