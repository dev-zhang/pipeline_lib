#!groovy

@Library('pipeline_lib') _

def messageTool = new org.xiamijun.MessageTool()

pipeline {
    stages {
        stage("Pull Code") {
            steps {
                messageTool.pushToFeiShu('dddd', '新版本更新啦~')
            }
        }
    }
}