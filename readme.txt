这个工程旨在比较各种xml的交互技术，提供参考

这里主要考察的xml的交互技术主要是：
xml文件的读和写，对不同字符集合的兼容性，xml文件扩展后的兼容性，xml模式修改以后的代码改动程度

这里选用了一个xmlbeans的例子 http://xmlbeans.apache.org/documentation/tutorial_getstarted.html
首先要读一个文件
<purchase-order>
    <customer>
        <name>Jasen Bourne</name>
        <address>Secret Place</address>
    </customer>
    <date>2011-08-01 09:16:00</date>
    <line-item>
        <description>The Bourne Identity</description>
        <price>21.79</price>
        <quantity>2</quantity>
    </line-item>
    <line-item>
        <description>The Bourne Supremacy</description>
        <price>19.89</price>
        <quantity>2</quantity>
    </line-item>
</purchase-order>

然后增加一个line-item
    <line-item>
        <description>The Bourne Ultimatum</description>
        <price>20.89</price>
        <quantity>2</quantity>
    </line-item>
并将日期改为<date>2011-08-09 15:20:00</date>写出到一个文件中

把description分别改成谍影重重1和谍影重重2，查看代码的修改情况


当xml的模式发生扩展之后，其中line-item增加了<per-unit-ounces>5</per-unit-ounces>一项，
customer增加一个attribute email，并在底部增加一个shipper
<purchase-order>
    <customer email="secret@no-ip-host.org">
        <name>Jasen Bourne</name>
        <address>Secret Place</address>
    </customer>
    <date>2011-08-01 09:16:00</date>
    <line-item>
        <description>The Bourne Identity</description>
        <per-unit-ounces>5</per-unit-ounces>
        <price>21.79</price>
        <quantity>2</quantity>
    </line-item>
    <line-item>
        <description>The Bourne Supremacy</description>
        <per-unit-ounces>5</per-unit-ounces>
        <price>19.89</price>
        <quantity>2</quantity>
    </line-item>
    <shipper>
        <name>ZipShip</name>
        <per-ounce-rate>0.74</per-ounce-rate>
    </shipper>
</purchase-order>
再测试其原有读写过程代码的兼容性，和新代码需要的改变

最后将customer的address改为city，line-item改为lineItem,per-unit-ounces改为ouncesPerUnit，per-ounce-rate改为ratePerOunce
再测试其原有读写过程代码的兼容性，和新代码需要的改变

