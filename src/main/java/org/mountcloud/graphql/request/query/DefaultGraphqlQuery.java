package org.mountcloud.graphql.request.query;

import org.mountcloud.graphql.request.result.ResultAttributtes;

/**
 * 2018/2/11. 默认的query
 * @author zhanghaishan
 * @version V1.0
 */
public class DefaultGraphqlQuery extends GraphqlQuery {

    private String groupRequestName;

    /**
     * 构造
     *
     * @param requestName query的名字
     */
    public DefaultGraphqlQuery(String requestName) {
        super(requestName);
    }

    public DefaultGraphqlQuery(String  groupRequestName, String requestName) {
        super(requestName);
        this.groupRequestName = groupRequestName;
    }

    @Override
    public String toString() {

        StringBuffer requestBuffer = new StringBuffer(requestName);

        //参数列表字符串
        String paramStr = getRequestParameter().toString();

        StringBuffer resultAttrBuffer = new StringBuffer("");
        boolean first = true;
        //第一个前面不拼接","
        for(ResultAttributtes ra :resultAttributes) {
            if(first) {
                first=false;
            }else{
                resultAttrBuffer.append(" ");
            }
            resultAttrBuffer.append(ra.toString());
        }

        String resultAttrStr = resultAttrBuffer.toString();

        requestBuffer.append(paramStr);
        if(!resultAttrStr.isEmpty()) {
            requestBuffer.append("{");
            requestBuffer.append(resultAttrStr);
            requestBuffer.append("}");
        }

        if(groupRequestName != null && !groupRequestName.isEmpty()) {
            final StringBuffer groupRequestBuffer = new StringBuffer(groupRequestName);
            groupRequestBuffer.append("{");
            groupRequestBuffer.append(requestBuffer);
            groupRequestBuffer.append("}");
            return groupRequestBuffer.toString();
        }

        return requestBuffer.toString();
    }
}
