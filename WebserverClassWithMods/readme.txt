webserver.java class requires that build.graddle have the line:
implementation 'com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava'
added to the dependencies section

It also requires that android studio settings be overwritten to allow cleartext urls
add network_securyti_config.xml to the res/xml
add these two lines to the android manifest under application:
	android:networkSecurityConfig="@xml/network_security_config"
        android:usesCleartextTraffic="true"

With that the class should run normally.
