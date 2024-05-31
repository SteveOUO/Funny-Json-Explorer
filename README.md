# Funny JSON Explorer 软件工作作业

## 实验要求

1. **开发一个名为Funny JSON Explorer（FJE）的命令行界面小工具**，该工具用于可视化JSON文件。

2. **需求分析**：
   
   - 能够快速切换不同的可视化风格，包括树形（tree）和矩形（rectangle）。

     ```bash
     ├─♢oranges
     │ └─♢mandarin
     │ ├─♤clementine
     │ └─♤tangerine: cheap & juicy!
     └─♢apples
     └─♤gala
     poker-face-icon-family: 中间节点icon：♢ 叶节点icon：♤
     
     ┌─ oranges ───────────────────────────────┐
     │ ├─ mandarin  ───────────────────────────┤
     │ │ ├─ clementine   ──────────────────────┤
     │ │ ├─ tangerine:   cheap & juicy! ───────┤
     ├─ apples ────────────────────────────────┤
     └──┴─✩gala ───────────────────────────────┘
     矩形（rectangle）
     ```
   
   - 允许用户通过命令行参数指定JSON文件、风格和图标族。
   
3. **命令行使用示例**：
   
- `fje -f <json file> -s <style> -i <icon family>`
  
4. **领域模型**

   ![image-20240530205711243](README.assets/image-20240530205711243.png)

5. **设计模式要求**：
   - 使用工厂方法（Factory）、抽象工厂（Abstract Factory）、建造者（Builder）模式、组合模式（Composition）来设计和实现软件。
   - 确保程序易于扩展和维护。

6. **具体任务**：

   - （必做）在不改变现有代码的情况下添加新的抽象工厂添加新的风格。
   - （选做）通过配置文件添加新的图标族。

## 实验流程

### 使用四种模式实现任务要求

在本次作业中，我们需要使用工厂方法（Factory）、抽象工厂（Abstract Factory）、建造者（Builder）模式、组合模式（Composition）来设计和实现软件，在这里我展示在四种模式下设计得到的类图，具体的代码请参考github上的代码

#### 工厂方法

![FactoryMathod-Page-1.drawio](README.assets/FactoryMathod-Page-1.drawio.png)

在工厂方法模式中，每个产品（IconFamily和Style）都有与之对应的工厂，工厂方法模式定义了一个创建对象的接口，在工厂类中封装了产品的生产过程。在核心类`JsonExplorer`中只需调用工厂的`create`方法即可获得产品

#### 抽象工厂

![FactoryMathod-第 2 页.drawio](README.assets/FactoryMathod-%E7%AC%AC%202%20%E9%A1%B5.drawio.png)

与工厂方法不同，在抽象工厂模式中提供一个接口来创建产品族，所有的产品都在这个接口中定义。产品的具体实现由具体工厂类决定。具体到这次实现的代码中，抽象工厂模式中的一个工厂（如TreeDefaultContainerFactory）并不只生产一种产品（Style或IconFamily），而是生产一个产品族（Container），这样可以将具体产品的创建过程延迟到子类中，实现了产品创建的解耦。

#### 建造者模式

![FactoryMathod-第 3 页.drawio](README.assets/FactoryMathod-%E7%AC%AC%203%20%E9%A1%B5.drawio.png)

建造者模式下的类图跟抽象工厂有些相似之处，但建造者模式和抽象工厂模式的不同之处在于：建造者模式用于创建一个复杂对象，它允许通过分步骤构造来创建对象。建造者模式将对象的构造过程与其表示分离，在`Builder`类中封装了生产产品各个部分的方法，而在`Director`类中封装了各个生产方法的执行顺序，这使得相同的构造过程可以创建不同的表示。由于本次实现的任务比较简单，每个对象只有只有两个部分（Style和IconFamily），建造者模式的用处不大；但在复杂对象的创建时，这种模式会比抽象工厂更具优势。

#### 组合模式

![FactoryMathod-第 4 页.drawio](README.assets/FactoryMathod-%E7%AC%AC%204%20%E9%A1%B5.drawio.png)

组合模式（Composite Pattern）是一种结构型设计模式，它将对象组织成树形结构，以表示“部分-整体”的层次结构。组合模式使得客户端对单个对象和组合对象的使用具有一致性。在我的实现中，通过定义统一的`Component`接口，使得`Container`可以包含并管理多种类型的组件（如`Style`和`IconFamily`）。这种模式使得客户端可以一致地处理单个对象和组合对象，简化了客户端的操作。`Container`作为组合节点，通过组合和调用子组件，实现复杂的对象结构和行为。

### 在不改变现有代码的情况下添加新的抽象工厂添加新的风格

由于设计时确保了程序的可扩展性，在四种设计模式中都可以实现任务要求，下面我以抽象工厂模式为例，展示怎么在不改变现有代码的情况下添加新的抽象工厂以添加新的风格

假设现在我们有一个新的风格`newStyle`类

```Java
public class newStyle extends Style {
    public void draw(Map<String, Object> json, IconFamily iconFamily) {
        // 新风格的draw逻辑实现
    }          
}
```

添加新的抽象工厂来添加新的风格(以Default图标类为例)

```java
public class newDefaultContainerFactory implements ContainerFactory{
    public Container createContainer(){
        Style style = new newStyle();
        IconFamily icon = new DefaultIconFamily();
        return new Container(style, icon);
    }
}
```

然后只要在核心类`JsonExplorer`中创建这种新的抽象工厂就可以直接添加新的风格了

```java
public JsonExplorer(ContainerFactory containerFactory) {
    ContainerFactory containerFactory = new newDefaultContainerFactory();
	this.container = containerFactory.createContainer();
}
```

### 通过配置文件添加新的图标族

要实现通过修改配置文件生成新的图标族，一种简单的解决方法是：创建一个配置图标类：这个类通过读取配置文件中的图标，将图标应用到使用的风格中

```java
package FunnyJsonExplorer.AbstractFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ConfigIconFamily extends IconFamily {
    private static String leafIcon;
    private static String middleIcon;

    // Static block to read from YAML file once when the class is loaded
    static {
        Map<String, String> config = new HashMap<>();
        try {
            // Read file content as a string
            String content = new String(Files.readAllBytes(Paths.get("config.yaml")), StandardCharsets.UTF_8);
            String[] lines = content.split("\\n");

            leafIcon = lines[3].replaceAll("\\s", "")+" ";
            middleIcon = lines[1].replaceAll("\\s", "")+" ";
            System.out.println("LeafIcon:"+leafIcon);
            System.out.println("MiddleIcon:"+middleIcon);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load icons from config.yaml", e);
        }
    }

    @Override
    public String getLeafIcon() {
        return leafIcon;
    }

    @Override
    public String getMiddleIcon() {
        return middleIcon;
    }
}
```

以下面`config.yaml`文件为例：

```yaml
MiddleIcon:
  ♔ 
LeafIcon:
  ♕ 
```

运行程序结果如下：

![image-20240530235612766](README.assets/image-20240530235612766.png)

如果我们想添加新的图标族，我们只需要修改`config.yaml`文件，如下所示：

```yaml
MiddleIcon:
  ♡ 
LeafIcon:
  ❤ 
```

再次运行程序，我们可以看到新的图标族已经被应用了：

![image-20240530235828471](README.assets/image-20240530235828471.png)

## 运行截图

### Default图标族+树形风格

![image-20240530225214030](README.assets/image-20240530225214030.png)

### Poker图标族+树形风格

![image-20240530225031195](README.assets/image-20240530225031195.png)

### Default图标族+矩阵风格

![image-20240530224932460](README.assets/image-20240530224932460.png)

### Poker图标族+矩阵风格

![image-20240530225144722](README.assets/image-20240530225144722.png)