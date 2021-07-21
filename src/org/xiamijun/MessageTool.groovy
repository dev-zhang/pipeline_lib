package org.xiamijun;

import groovy.json.JsonSlurper

class MessageTool {
    /// 推送消息到飞书机器人
    def pushToFeiShu(String webhookToken, String content) {
        try {
            def http = new URL("https://open.feishu.cn/open-apis/bot/v2/hook/$webhookToken").openConnection() as HttpURLConnection
            def json = """{"msg_type":"text","content":{"text":"$content"}}"""
            // println(json)
            def queryString = "msg"
            http.setDoOutput(true)
            http.setRequestMethod('POST')
            http.setRequestProperty('Content-Type', 'application/json')
            try {
                OutputStream outputStream = http.getOutputStream()
                byte[] inputBytes = json.getBytes('UTF-8')
                outputStream.write(inputBytes)
                outputStream.flush()
            } catch (e) {}
            http.connect()

            def response = [:]
            def jsonSlurper = new JsonSlurper()
            if (http.responseCode == 200) {
                response = jsonSlurper.parseText(http.inputStream.getText('UTF-8'))
            } else {
                response = jsonSlurper.parseText(http.errorStream.getText('UTF-8'))
            }
            println("response:$response")
        } catch (e) {
            println(e)
        }
    }

    static void main(String[] args) {
        def msgTool = new MessageTool();
        msgTool.pushToFeiShu('ddd', 'new version updated')
    }
}