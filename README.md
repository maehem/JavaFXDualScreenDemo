# JavaFX Dual Screen Demo

JavaFX 17 demo to run on dual HDMI monitor on RaspberryPi 4 (64-bit).

Based upon:  Frank Delporte's version
https://github.com/FDelporte/JavaFxDualScreen




## Setup
Assuming a RaspberryPi 4 with early access 64-bit OS.
Installed Java and Gluon JavaFX as shown below.

```
sudo apt install default-jdk
 java -version

Download: https://gluonhq.com/download/javafx-17-ea-sdk-linux-aarch64/
sudo mv javafx-sdk-17.zip /opt
cd /opt
sudo unzip javafx-sdk-17.zip

cd ~
vi .bashrc
        add ==>   export ENABLE_GLUON_COMMERCIAL_EXTENSIONS=true

sudo apt install libegl-mesa0 libegl1 libgbm1 libgles2 libpango1.0-0 libpangoft1.0-0
```


### Compile
There is a convenience script ( compile-demo ) in the top directory.
```
export PATH_TO_FX=/opt/javafx-sdk-17/lib
javac --module-path $PATH_TO_FX -d dist --add-modules javafx.controls hellofx/HelloFXDual.java
```


### Run
There is a convenience script ( run-demo ) in the top directory.
```
sudo -E java -Dmonocle.platform=EGL -Dembedded=monocle -Dglass.platform=Monocle -Degl.displayid=/dev/dri/card1 -Dmonocle.egl.lib=/opt/javafx-sdk-17/lib/libgluon_drm-1.1.4.so -Dprism.verbose=true --module-path /opt/javafx-sdk-17/lib --add-modules javafx.controls -cp dist hellofx.HelloFXDual
```
