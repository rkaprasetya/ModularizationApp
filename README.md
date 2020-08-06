# ModularizationApp
Application to practice dynamic modulaziration

important points:
1. - Library Modules = These are the modules whose code is accessible by the main App Module and it is implemented as any other 3rd party library.
   - Dynamic Feature Modules = These modules are more like code on demand. These get downloaded only when it is required by the user.
    It delivers the code via dynamic delivery. 
    These feature modules depend on the app but the app doesn't depend on the feature modules.
    
2. implementation 'com.google.android.play:core:version' in app gradle
   The play core library is responsible for the dyamic modules download on demand.
   
3. When we use "api" the libraries can be used by the other modules as well as the dynamic modules implements the app module.
   If we use "implementation" then the library is only used by module it is implemented in.
   for example :
    api 'androidx.constraintlayout:constraintlayout:1.1.3'
    
4. SplitInstallManager = responsible for downloading the module. The app has to be in Foreground to download the dynamic module.
   SplitInstallRequest = contain the request information that will be used to request our dynamic feature module from Google Play.  

5. when to use dynamic module and library module
  - If the module is very less used feature in the app but is an important flow of the app we keep that in dynamic-module.
    For example, Paid Features, OnBoarding Flow etc.
  - If the module is very light in size we can keep as a library module.
6. benefits modularization:
  - Easily Managable By Multiple Devs
  - Easier to maintain
  - Reduced APK Size
  - Testing New Features 
