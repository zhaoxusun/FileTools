package file;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * author: zhaoxu
 * date: 2020/4/17 下午12:18
 */
public class PoUtil {

    /**
     * PO类转SQL语句
     * @param clazz
     * @return
     */
    public static String poToSql(Class clazz){
        Field [] fields = clazz.getDeclaredFields();
        String [] clazzName= clazz.getName().split("\\.");
        String tableName = humpToLine(clazzName[clazzName.length-1]);
        StringBuilder sqlKeyAndValue = new StringBuilder();
        for (Field field:fields){
            String fieldName = humpToLine(field.getName());
            String fieldType= transformFieldType(field.getGenericType().toString());
            if (fieldName.equals("id")){
                sqlKeyAndValue.append(fieldName).append("\t");
                sqlKeyAndValue.append(fieldType).append("\t");
                sqlKeyAndValue.append("not null\t");
                sqlKeyAndValue.append("primary key,\n");
            } else {
                sqlKeyAndValue.append(fieldName).append("\t");
                sqlKeyAndValue.append(fieldType).append("\t");
                sqlKeyAndValue.append("null\t");
                sqlKeyAndValue.append("comment '',\n");
            }

        }
        return String.format("create table %s (\n%s\n);",tableName,sqlKeyAndValue.toString());
    }

    /**
     * PO类field类型转SQL类型
     * @param fileType
     * @return
     */
    public static String transformFieldType(String fileType){
        switch (fileType){
            case "class java.lang.String": return "VARCHAR";
            case "class java.lang.Integer": return "BIGINT";
            case "class java.lang.Short": return "INT";
            case "class java.lang.Long": return "BIGINT";
            default:return "VARCHAR";
        }
    }

    /**
     * 驼峰命名转下划线命名
     * @param str
     * @return
     */
    public static String humpToLine(String str){
        str = str.replaceAll("[A-Z]", "_$0").toLowerCase();
        if (str.charAt(0) == '_'){
            str = str.substring(1);
        }
        return str;
    }


    /**
     * PO类field类型转Map类型
     * @param clazz
     * @return
     */
    public Map<String,Type> poToMap(Class clazz){
        Field [] fields = clazz.getDeclaredFields();
        Map<String,Type> fieldsMap = new HashMap<String,Type>();
        for (Field field:fields){
            String fieldName = field.getName();
            Type fieldType= field.getGenericType();
            fieldsMap.put(fieldName,fieldType);
        }
        return fieldsMap;
    }

}
