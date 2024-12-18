export CATALINA_HOME=/usr/local/tomcat
export PATH=$CATALINA_HOME/bin:$PATH
mkdir -p "$CATALINA_HOME"
curl -o $CATALINA_HOME/tomcat.tar.gz https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.34/bin/apache-tomcat-10.1.34.tar.gz
cd $CATALINA_HOME
tar -xf tomcat.tar.gz --strip-components=1
rm bin/*.bat
rm tomcat.tar.gz*
mv webapps webapps.dist
mkdir webapps
chown --recursive vscode $CATALINA_HOME
