#!/bin/sh
# tomcat-path like /usr/lib/tomcat7/
TomcatID=$(ps -ef | grep tomcat-path | grep -v grep | awk '{print $2}')
#echo $TomcatID

# tomcat restart shell
StartTomcat=/usr/restart_tomcat.sh

# web
WebUrl=http://123.57.247.225/

# log
GetPageInfo=/dev/null
TomcatMonitorLog=/log/tomcatMonitor.log

Monitor()
{
  echo "[info]start moniter tomcat...[$(date +'%F %H:%M:%S')]"
  if [ $TomcatID ];then
    echo "[info]current tomcat PID is:$TomcatID,continue moniter page..."
    # success = 200
    TomcatServiceCode=$(curl -s -o $GetPageInfo -m 10 --connect-timeout 10 $WebUrl -w %{http_code})
    if [ $TomcatServiceCode -eq 200 ] || [ $TomcatServiceCode -eq 302 ];then
        echo "[info]return code is $TomcatServiceCode,tomcat start success,test success......"
    else
        echo "[error]tomcat error,warnning......status code is $TomcatServiceCode,log to $GetPageInfo"
        echo "[error]error access page,restart tomcat"
        #kill -9 $TomcatID  #
        #sleep 3
        #rm -rf $TomcatCache #
        $StartTomcat
    fi
  else
    echo "[error]tomcat PID none! tomcat auto restart..."
    echo "[info]$StartTomcat,please wait......"
    #rm -rf $TomcatCache
    $StartTomcat
  fi
  echo "------------------------------"
}
Monitor>>$TomcatMonitorLog