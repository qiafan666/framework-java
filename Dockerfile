FROM 121.40.49.5/imagebase/jdk8:2019
#FROM registry-scu.cloudtogo.cn/ubuntu:jdk
COPY ./target/test-web-0.0.1-SNAPSHOT.jar /root/startups/test-web-service.jar
WORKDIR /root/startups/
RUN yum -y install kde-l10n-Chinese && yum -y reinstall glibc-common
RUN localedef -c -f UTF-8 -i zh_CN zh_CN.utf8
ENV LC_ALL zh_CN.utf8
ENV LANG zh_CN.UTF-8
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
RUN mkdir -p /data/test/share
CMD ["java", "-Xms1024m", "-Xmx1500m", "-jar", "test-web-service.jar", "spring.profiles.active=test"]
