#execute script: sudo sh install.sh

DEMARKER="*****************************"
INSTALLED=""
FAILED_INSTALLED=""
ALREADY_INSTALLED=""

createFileSystem(){
    echo "$DEMARKER"
    echo "Creating fileSystem"
    cd /
    sudo mkdir work
    sudo chown -R $USER /work
    sudo chmod -R 777 /work
    sudo mkdir /work/programs
    sudo mkdir /work/workspace
    echo "Created fileSystem"
}

installCommand(){
    echo
    echo "Installing $1"
    isInstalled=$(dpkg -l $1 | grep $1| awk '{print $1; exit;}')
    if [ "$isInstalled" = "ii" ]
    then
        echo "$1 is already installed"
        ALREADY_INSTALLED="$ALREADY_INSTALLED\n$1"
    else
        sudo apt-get -y install $1 > /dev/null 2>&1
        if [ "$?" -ne 0 ]
        then
            echo "Failed to install $1"
            FAILED_INSTALLED="$FAILED_INSTALLED\n$1"
        fi
        echo "Installed $1"
        INSTALLED="$INSTALLED\n$1"
    fi
}

installEssentialCommands(){
    echo "$DEMARKER"
    echo "Installing Essential Commands"
    
    installCommand "gawk"
    installCommand "curl"
}

installUtilities(){
    echo "$DEMARKER"
    echo "Installing Utilities/Programs"

    echo
    echo "Installing code"
    isInstalled=$(dpkg -l code | grep code| awk '{print $1; exit;}')
    if [ "$isInstalled" = "ii" ]
    then
        echo "code is already installed"
        ALREADY_INSTALLED="$ALREADY_INSTALLED\ncode"
    else
        curl https://packages.microsoft.com/keys/microsoft.asc | gpg --dearmor > microsoft.gpg
        sudo install -o root -g root -m 644 microsoft.gpg /etc/apt/trusted.gpg.d/
        sudo sh -c 'echo "deb [arch=amd64] https://packages.microsoft.com/repos/vscode stable main" > /etc/apt/sources.list.d/vscode.list'
        sudo apt-get -y install apt-transport-https
        sudo apt-get update
        sudo apt-get -y install code
        sudo rm -f microsoft.gpg
        echo "Installed code"
        INSTALLED="$INSTALLED\ncode"
    fi  
   


    echo
    echo "Installing google-chrome"
    isInstalled=$(dpkg -l | grep google-chrome| awk '{print $1; exit;}')
    if [ "$isInstalled" = "ii" ]
    then
        echo "google-chrome is already installed"
        ALREADY_INSTALLED="$ALREADY_INSTALLED\ngoogle-chrome"
    else

        sudo apt-get -y install libxss1 libappindicator1 libindicator7 
        sudo wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb 
        sudo dpkg -i google-chrome*.deb 
        sudo rm -f google-chrome*.deb

        echo "Installed google-chrome"
        INSTALLED="$INSTALLED\ngoogle-chrome"
    fi  



    echo
    echo "Installing java 8"
    isInstalled=$(dpkg -l | grep openjdk| awk '{print $1; exit;}')
    if [ "$isInstalled" = "ii" ]
    then
        echo "openjdk-8-jdk is already installed"
        ALREADY_INSTALLED="$ALREADY_INSTALLED\nopenjdk-8-jdk"
    else
        sudo apt-get -y install openjdk-8-jdk > /dev/null 2>&1
        echo "Installed openjdk-8-jdk"
        INSTALLED="$INSTALLED\nopenjdk-8-jdk"
    fi  


    echo
    echo "Installing maven"
    isInstalled=$(dpkg -l | grep maven| awk '{print $1; exit;}')
    if [ "$isInstalled" = "ii" ]
    then
        echo "maven is already installed"
        ALREADY_INSTALLED="$ALREADY_INSTALLED\nmaven"
    else
        sudo apt-get -y install maven > /dev/null 2>&1
        echo "Installed maven"
        INSTALLED="$INSTALLED\nmaven"
    fi


    echo
    echo "Installing gnome-open"
    isInstalled=$(dpkg -l | grep libgnome2-bin | awk '{print $1; exit;}')
    if [ "$isInstalled" = "ii" ]
    then
        echo "gnome-open is already installed"
        ALREADY_INSTALLED="$ALREADY_INSTALLED\ngnome-open"
    else
        sudo apt-get -y install libgnome2-bin > /dev/null 2>&1
        echo "Installed gnome-open"
        INSTALLED="$INSTALLED\ngnome-open"
    fi

    echo
    echo "Installing git"
    isInstalled=$(dpkg -l git | grep git | awk '{print $1; exit;}')
    if [ "$isInstalled" = "ii" ]
    then
        echo "git is already installed"
        ALREADY_INSTALLED="$ALREADY_INSTALLED\ngit"
    else
        sudo apt-get -y install git > /dev/null 2>&1
        echo "Installed git"
        INSTALLED="$INSTALLED\ngit"
    fi

    echo
    echo "Installing nmap"
    isInstalled=$(dpkg -l nmap | grep nmap | awk '{print $1; exit;}')
    if [ "$isInstalled" = "ii" ]
    then
        echo "nmap is already installed"
        ALREADY_INSTALLED="$ALREADY_INSTALLED\nnmap"
    else
        sudo apt-get -y install nmap > /dev/null 2>&1
        echo "Installed nmap"
        INSTALLED="$INSTALLED\nnmap"
    fi
}

installAlias(){

    echo "Installing alias eclipse"
    foundAlias=$(grep -i "alias eclipse" ~/.bashrc)
    if [ -z "$foundAlias" ]
    then
        echo 'alias eclipse="/work/programs/eclipse/eclipse > /dev/null 2>&1 &"' >> ~/.bashrc
        echo "Installed alias eclipse"
        INSTALLED="$INSTALLED\nalias eclipse"      
    else
        echo "alias eclipse already installed"
        ALREADY_INSTALLED="$ALREADY_INSTALLED\nalias eclipse"
    fi
}

createFileSystem
installEssentialCommands
installUtilities
installAlias
echo "$DEMARKER"
echo
echo "Installed"
echo -e $INSTALLED
echo
echo "Failed to Install"
echo -e $FAILED_INSTALLED
echo
echo "Already Installed"
echo -e $ALREADY_INSTALLED

