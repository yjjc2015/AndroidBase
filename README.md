# AndroidBase
安卓基础知识，从《第一行代码》开始
整理了Android应用部分基础知识的实践（demo）

1.IntentService案例：
相比于传统的Service，它不必考虑如何创建一个线程去执行耗时/网络操作（ps，Service默认也是执行在主UI线程上），也不用考虑何时手动关闭这个Service（ps，IntentService执行完后会自动关闭）。
实现它要重写 父类的super(String name)构造方法
             onHandleIntent方法，该方法执行在子线程上，执行完自动关闭服务
