package com.tjpu.property.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XmlUtil {
	
	
	private static XStream xstream;
	
	/**
	 * @Description ��xml�ĵ�ת���ɶ���
	 * @author ����
	 * @Created 2013-10-14
	 * @param xml ��ת����xml
	 * @param object ϣ��ת��Ϊ�Ķ���
	 * @return xml��Ӧ�Ķ���
	 */
	public static Object xmlToObject(String xml, Object object, String rootName){
		
		try {
			
			String name = object.getClass().getName();
			String newXml = xml.replace("<"+rootName+">", "<object-stream>"+"<"+name+">");
		    newXml = newXml.replace("</"+rootName+">", "</"+name+">" + "</object-stream>") ;
			StringReader reader = new StringReader(newXml);
			return getReadXStream().createObjectInputStream(reader).readObject();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * @Description ������ת����xml
	 * @author ����
	 * @Created 2013-10-14
	 * @param object ��ת������
	 * @return xml
	 */ 
    public static String objectToXml(Object object, String root) {  
    	
    	String temp = getWriteXStream(object).toXML(object);
    	String fullname = object.getClass().getName();
    	return temp.replace(fullname, root);
    }
	
    
    
    private static XStream getWriteXStream(Object object){
    	
    	xstream = getCDATAXStream();
    //	xstream.processAnnotations(object.getClass()); 
    	
		return xstream;
    	
    }
    
    private static XStream getReadXStream(){
		return xstream = new XStream(new DomDriver());
    }
    
    private static XStream getCDATAXStream(){
    	return xstream = new XStream(new XppDriver() {  
            public HierarchicalStreamWriter createWriter(Writer out) {  
                return new PrettyPrintWriter(out) {  
                    // ������xml�ڵ��ת��������CDATA���  
                    boolean cdata = true;  
      
                    public void startNode(String name, Class clazz) {  
                        super.startNode(name, clazz);  
                    }  
      
                    protected void writeText(QuickWriter writer, String text) {  
                        if (cdata) {  
                            writer.write("<![CDATA[");  
                            writer.write(text);  
                            writer.write("]]>");  
                        } else {  
                            writer.write(text);  
                        }  
                    }  
                };  
            }  
        });  
    }
        
    
}
