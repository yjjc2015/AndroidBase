# AndroidBase
安卓基础知识，从《第一行代码》开始
整理了Android应用部分基础知识的实践（demo）

1.IntentService案例：
相比于传统的Service，它不必考虑如何创建一个线程去执行耗时/网络操作（ps，Service默认也是执行在主UI线程上），也不用考虑何时手动关闭这个Service（ps，IntentService执行完后会自动关闭）。
实现它要重写 父类的super(String name)构造方法
             onHandleIntent方法，该方法执行在子线程上，执行完自动关闭服务

			 
2.WebView中JavaScript调用本地Android
（1）WebView加载的html中的JavaScript可以通过addJavaScriptInteface(Object obj, String name)来调用Android本地的方法；
其中参数obj代表的是Android为JavaScript提供方法的类的对象，name是任意声明的调用名（相当于一种约定，Android和Js都用同一个name）；
（2）注意点：要将配置文件中目标API设为<=16，就是说android:targetSDKVersion<=16（详情可以参考addJavaScriptInteface源码），
			只有设备的API>=17的情况下才能使用。