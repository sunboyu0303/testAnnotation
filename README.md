# testAnnotation

元注解：
    
    元注解的作用就是负责注解其他注解。
    
        1.@Target,
        2.@Retention,
        3.@Documented,
        4.@Inherited
    
    @Target：
    @Target说明了Annotation所修饰的对象范围：Annotation可被用于 packages、types（类、接口、枚举、Annotation类型）、
    类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）。
    在Annotation类型的声明中使用了target可更加明晰其修饰的目标。
    
    作用：用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
    
    取值(ElementType)有：
    
        1.CONSTRUCTOR:用于描述构造器
        2.FIELD:用于描述域
        3.LOCAL_VARIABLE:用于描述局部变量
        4.METHOD:用于描述方法
        5.PACKAGE:用于描述包
        6.PARAMETER:用于描述参数
        7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
    
    @Retention：
    @Retention定义了该Annotation被保留的时间长短：某些Annotation仅出现在源代码中，而被编译器丢弃；
    而另一些却被编译在class文件中；编译在class文件中的Annotation可能会被虚拟机忽略，而另一些在class被装载时将被读取
    （请注意并不影响class的执行，因为Annotation与class在使用上是被分离的）。
    使用这个meta-Annotation可以对 Annotation的“生命周期”限制。
    
    作用：表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）
    
    取值（RetentionPoicy）有：
        
        1.SOURCE:在源文件中有效（即源文件保留）
        2.CLASS:在class文件中有效（即class保留）
        3.RUNTIME:在运行时有效（即运行时保留）
       
    @Documented:
    @Documented用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。
    Documented是一个标记注解，没有成员。
    
    @Inherited：
    @Inherited 元注解是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。
    如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
    
    注意：@Inherited annotation类型是被标注过的class的子类所继承。类并不从它所实现的接口继承annotation，
    方法并不从它所重载的方法继承annotation。
    
    当@Inherited annotation类型标注的annotation的Retention是RetentionPolicy.RUNTIME，则反射API增强了这种继承性。
    如果我们使用java.lang.reflect去查询一个@Inherited annotation类型的annotation时，反射代码检查将展开工作：
    检查class和其父类，直到发现指定的annotation类型被发现，或者到达类继承结构的顶层。
    
    自定义注解：
    使用@interface自定义注解时，自动继承了java.lang.annotation.Annotation接口，由编译程序自动完成其他细节。
    在定义注解时，不能继承其他的注解或接口。@interface用来声明一个注解，其中的每一个方法实际上是声明了一个配置参数。
    方法的名称就是参数的名称，返回值类型就是参数的类型（返回值类型只能是基本类型、Class、String、enum）。
    可以通过default来声明参数的默认值。
    
    定义注解格式：
    　　public @interface 注解名 {定义体}
    
    注解参数的可支持数据类型：
        1.所有基本数据类型（int,float,boolean,byte,double,char,long,short)
        2.String类型
        3.Class类型
        4.enum类型
        5.Annotation类型
        6.以上所有类型的数组
        
    Annotation类型里面的参数该怎么设定: 
    　　第一,只能用public或默认(default)这两个访问权修饰.例如,String value();这里把方法设为defaul默认类型；　 　
    　　第二,参数成员只能用基本类型byte,short,char,int,long,float,double,boolean八种基本数据类型和 
             String,Enum,Class,annotations等数据类型,以及这一些类型的数组.例如,String value();这里的参数成员就为String;　　
    　　第三,如果只有一个参数成员,最好把参数名称设为"value",后加小括号.例:下面的例子FruitName注解就只有一个参数成员。
    
注解处理器类库(java.lang.reflect.AnnotatedElement)：
    
    Java使用Annotation接口来代表程序元素前面的注解，该接口是所有Annotation类型的父接口。
    除此之外，Java在java.lang.reflect 包下新增了AnnotatedElement接口，该接口代表程序中可以接受注解的程序元素，
    该接口主要有如下几个实现类：
    
        Class：类定义
        Constructor：构造器定义
        Field：累的成员变量定义
        Method：类的方法定义
        Package：类的包定义
        
    java.lang.reflect 包下主要包含一些实现反射功能的工具类，实际上，java.lang.reflect 包所有提供的反射API扩充了
    读取运行时Annotation信息的能力。当一个Annotation类型被定义为运行时的Annotation后，该注解才能是运行时可见，
    当class文件被装载时被保存在class文件中的Annotation才会被虚拟机读取。
    
    AnnotatedElement 接口是所有程序元素（Class、Method和Constructor）的父接口，所以程序通过反射获取了某个类的
    AnnotatedElement对象之后，程序就可以调用该对象的如下四个个方法来访问Annotation信息：
    
        方法1：<T extends Annotation> T getAnnotation(Class<T> annotationClass): 
                返回改程序元素上存在的、指定类型的注解，如果该类型注解不存在，则返回null。
    　　方法2：Annotation[] getAnnotations():返回该程序元素上存在的所有注解。
    　　方法3：boolean is AnnotationPresent(Class<?extends Annotation> annotationClass):
                判断该程序元素上是否包含指定类型的注解，存在则返回true，否则返回false.
    　　方法4：Annotation[] getDeclaredAnnotations()：返回直接存在于此元素上的所有注释。
                与此接口中的其他方法不同，该方法将忽略继承的注释。（如果没有注释直接存在于此元素上，
                则返回长度为零的一个数组。）该方法的调用者可以随意修改返回的数组；这不会对其他调用者返回的数组产生任何影响。