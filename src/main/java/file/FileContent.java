package file;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by zhaoxu on 2017/7/7.
 */
public class FileContent {
    /**
     * content编码方式
     */
    private String contentCodeType;
    /**
     * content头部
     */
    private String contentHeader;
    /**
     * content主体
     */
    private TreeMap<Integer ,ArrayList> contentBody;
    /**
     * content主体信息条数
     */
    private int contentBodyInfoCount;
    /**
     * content尾部
     */
    private String contentFooter;
    /**
     * contentBody属性值
     */
    private ArrayList contentBodyKey;

    public FileContent(){
        this.contentBody = new TreeMap<Integer, ArrayList>();
        this.contentBodyKey = new ArrayList();
    }
    /**
     * 设置contentBody的属性值
     * @param contentBodyKey
     */
    public void setContentBodyKey(ArrayList contentBodyKey){
        this.contentBodyKey = contentBodyKey;
    }
    /**
     * 获取contentBody的属性值
     * @return
     */
    public ArrayList getContentBodyKey(){
        return this.contentBodyKey;
    }
    /**
     * 增加contentBody信息
     * @param contentBodyInfoNumber
     * @param contentBodyInfo
     */
    public void addContentBodyInfo(int contentBodyInfoNumber,ArrayList contentBodyInfo){
        this.contentBody.put(contentBodyInfoNumber,contentBodyInfo);
    }

    /**
     * 删除contentBody信息
     * @param contentBodyInfoNumber
     */
    public void deleteContentBodyInfo(int contentBodyInfoNumber){
        contentBody.remove(contentBodyInfoNumber);
    }
    /**
     * 获取contentBody对象
     * @return
     */
    public TreeMap<Integer ,ArrayList> getContentBody(){
        return this.contentBody;
    }

    /**
     * 设置contentBodyInfo条数
     * @param contentBodyInfoCount
     */
    public void setContentBodyInfoCount(int contentBodyInfoCount){
        this.contentBodyInfoCount = contentBodyInfoCount;
    }

    /**
     * 获取contentBodyInfo条数
     * @return
     */
    public int getContentBodyInfoCount(){
        return this.contentBodyInfoCount;
    }
}
