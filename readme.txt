�������ּ�ڱȽϸ���xml�Ľ����������ṩ�ο�

������Ҫ�����xml�Ľ���������Ҫ�ǣ�
xml�ļ��Ķ���д���Բ�ͬ�ַ����ϵļ����ԣ�xml�ļ���չ��ļ����ԣ�xmlģʽ�޸��Ժ�Ĵ���Ķ��̶�

����ѡ����һ��xmlbeans������ http://xmlbeans.apache.org/documentation/tutorial_getstarted.html
����Ҫ��һ���ļ�
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

Ȼ������һ��line-item
    <line-item>
        <description>The Bourne Ultimatum</description>
        <price>20.89</price>
        <quantity>2</quantity>
    </line-item>
�������ڸ�Ϊ<date>2011-08-09 15:20:00</date>д����һ���ļ���

��description�ֱ�ĳɵ�Ӱ����1�͵�Ӱ����2���鿴������޸����


��xml��ģʽ������չ֮������line-item������<per-unit-ounces>5</per-unit-ounces>һ�
customer����һ��attribute email�����ڵײ�����һ��shipper
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
�ٲ�����ԭ�ж�д���̴���ļ����ԣ����´�����Ҫ�ĸı�

���customer��address��Ϊcity��line-item��ΪlineItem,per-unit-ounces��ΪouncesPerUnit��per-ounce-rate��ΪratePerOunce
�ٲ�����ԭ�ж�д���̴���ļ����ԣ����´�����Ҫ�ĸı�

