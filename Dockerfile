FROM tomcat:10.1

COPY JDBC_PRJ/pay_roll/pay_roll.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]
