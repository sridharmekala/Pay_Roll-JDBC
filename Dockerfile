FROM tomcat:9.0

COPY pay_roll/pay_roll.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080