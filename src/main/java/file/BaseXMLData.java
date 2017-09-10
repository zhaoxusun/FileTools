package file;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BaseXMLData {
    int rowSize;
    int dataSize;
    String caseName = null;

    public BaseXMLData() {
    }

    public Object[][] getData(String dataFile) {
        return this.getData(dataFile, 0);
    }

    public Object[][] getData(String dataFile, int rowNum) {
        Object[][] data = this.addList(dataFile, rowNum);
        return data;
    }

    public Object [] getDataKey(String dataFile, int rowNum){
        ArrayList<Object> list = new ArrayList();
        NodeList caseList = this.getNodeList(dataFile);
        if (caseList != null) {
            for(int i = 0; i < caseList.getLength(); ++i) {
                Node caseNode = caseList.item(i);
                if (caseNode.getNodeType() == 1 && caseNode.getNodeName() == "case") {
                    caseName = caseNode.getAttributes().getNamedItem("name").getNodeValue();
                        for(Node n = caseNode.getFirstChild(); n != null; n = n.getNextSibling()) {
                            if (n.getNodeType() == 1 && n.getNodeName().equals("row")) {
                                ++this.rowSize;
                                this.dataSize = 0;

                                for(Node m = n.getFirstChild(); m != null; m = m.getNextSibling()) {
                                    if (m.getNodeName().equals("data")) {
                                        ++this.dataSize;
                                        Node typevalue = m.getAttributes().getNamedItem("type");
                                        String s;
                                        if (typevalue != null) {
                                            String type = m.getAttributes().getNamedItem("type").getNodeValue();
                                            if (type.equalsIgnoreCase("String")) {
                                                s = m.getAttributes().getNamedItem("key").getNodeValue();
                                                list.add(s);
                                            } else if (type.equalsIgnoreCase("array")) {
                                                s = m.getAttributes().getNamedItem("key").getNodeValue();
                                                String[] ss = s.split(",");
                                                list.add(ss);
                                            }
                                        } else {
                                            s = m.getAttributes().getNamedItem("key").getNodeValue();
                                            list.add(s);
                                        }
                                    }
                                }
                            }
                        }

                }
            }
        }

        Object[][] data = new Object[this.rowSize][this.dataSize];
        int k;
        int i;
        int j;
        if (rowNum > 0 && rowNum < this.rowSize) {
            k = -1;
            data = new Object[rowNum][this.dataSize];

            for(i = 0; i < rowNum; ++i) {
                for(j = 0; j < this.dataSize; ++j) {
                    if (k < list.size()) {
                        ++k;
                    }

                    if (i <= rowNum - 1) {
                        data[i][j] = list.get(k);
                    }
                }
            }
        } else {
            k = -1;
            for(i = 0; i < this.rowSize; ++i) {
                for(j = 0; j < this.dataSize; ++j) {
                    if (k < list.size()) {
                        ++k;
                    }
                    data[i][j] = list.get(k);
                }
            }
        }
        return data [0];
    }

    private Object[][] addList(String dataFile, int rowNum) {
        ArrayList<Object> list = new ArrayList();
        NodeList caseList = this.getNodeList(dataFile);
        if (caseList != null) {
            for(int i = 0; i < caseList.getLength(); ++i) {
                Node caseNode = caseList.item(i);
                if (caseNode.getNodeType() == 1 && caseNode.getNodeName() == "case") {
                    caseName = caseNode.getAttributes().getNamedItem("name").getNodeValue();
                        for(Node n = caseNode.getFirstChild(); n != null; n = n.getNextSibling()) {
                            if (n.getNodeType() == 1 && n.getNodeName().equals("row")) {
                                ++this.rowSize;
                                this.dataSize = 0;

                                for(Node m = n.getFirstChild(); m != null; m = m.getNextSibling()) {
                                    if (m.getNodeName().equals("data")) {
                                        ++this.dataSize;
                                        Node typevalue = m.getAttributes().getNamedItem("type");
                                        String s;
                                        if (typevalue != null) {
                                            String type = m.getAttributes().getNamedItem("type").getNodeValue();
                                            if (type.equalsIgnoreCase("String")) {
                                                s = m.getAttributes().getNamedItem("value").getNodeValue();
                                                list.add(s);
                                            } else if (type.equalsIgnoreCase("array")) {
                                                s = m.getAttributes().getNamedItem("value").getNodeValue();
                                                String[] ss = s.split(",");
                                                list.add(ss);
                                            }
                                        } else {
                                            s = m.getAttributes().getNamedItem("value").getNodeValue();
                                            list.add(s);
                                        }
                                    }
                                }
                            }
                        }

                }
            }
        }

        Object[][] data = new Object[this.rowSize][this.dataSize];
        int k;
        int i;
        int j;
        if (rowNum > 0 && rowNum < this.rowSize) {
            k = -1;
            data = new Object[rowNum][this.dataSize];

            for(i = 0; i < rowNum; ++i) {
                for(j = 0; j < this.dataSize; ++j) {
                    if (k < list.size()) {
                        ++k;
                    }

                    if (i <= rowNum - 1) {
                        data[i][j] = list.get(k);
                    }
                }
            }
        } else {
            k = -1;

            for(i = 0; i < this.rowSize; ++i) {
                for(j = 0; j < this.dataSize; ++j) {
                    if (k < list.size()) {
                        ++k;
                    }

                    data[i][j] = list.get(k);
                }
            }
        }

        return data;
    }

    private NodeList getNodeList(String dataFile) {
        DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder dombuilder = domfac.newDocumentBuilder();
            InputStream is = new FileInputStream(dataFile);
            Document doc = dombuilder.parse(is);
            Element cases = doc.getDocumentElement();
            NodeList caseList = cases.getChildNodes();
            return caseList;
        } catch (FileNotFoundException var9) {
            var9.printStackTrace();
        } catch (ParserConfigurationException var10) {
            var10.printStackTrace();
        } catch (SAXException var11) {
            var11.printStackTrace();
        } catch (IOException var12) {
            var12.printStackTrace();
        }

        return null;
    }
}

