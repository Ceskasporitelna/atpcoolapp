FROM java:8-jre
FROM tomcat:9

ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH

RUN mkdir -p "$CATALINA_HOME"
WORKDIR $CATALINA_HOME

ADD ./target/*.war $CATALINA_HOME/webapps/
ADD ./tomcat/context.xml $CATALINA_HOME/conf/
ADD ./tomcat/tomcat-users.xml $CATALINA_HOME/conf/
ADD ./tomcat/*.jar $CATALINA_HOME/lib/

EXPOSE 8080
CMD ["catalina.sh", "run"]
